/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.major.web.majro_ad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.book.entity.book.DTO;
import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;
import com.jeeplus.modules.major.service.majro_ad.YsMajorService;

/**
 * 专业类型管理Controller
 * @author hjm
 * @version 2018-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/major/majro_ad/ysMajor")
public class YsMajorController extends BaseController {

	@Autowired
	private YsMajorService ysMajorService;
	@Autowired
	private YsMajorDao ysMajorDao;
	@ModelAttribute
	public YsMajor get(@RequestParam(required=false) String id) {
		YsMajor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysMajorService.get(id);
		}
		if (entity == null){
			entity = new YsMajor();
		}
		return entity;
	}
	
	/**
	 * 专业类型管理列表页面
	 */
	@RequiresPermissions("major:majro_ad:ysMajor:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsMajor ysMajor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsMajor> page = ysMajorService.findPage(new Page<YsMajor>(request, response), ysMajor); 
		model.addAttribute("page", page);
		return "modules/major/majro_ad/ysMajorList";
	}

	/**
	 * 查看，增加，编辑专业类型管理表单页面
	 */
	@RequiresPermissions(value={"major:majro_ad:ysMajor:view","major:majro_ad:ysMajor:add","major:majro_ad:ysMajor:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsMajor ysMajor, Model model) {
		model.addAttribute("ysMajor", ysMajor);
		return "modules/major/majro_ad/ysMajorForm";
	}

	/**
	 * 保存专业类型管理
	 */
	@RequiresPermissions(value={"major:majro_ad:ysMajor:add","major:majro_ad:ysMajor:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsMajor ysMajor, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysMajor)){
			return form(ysMajor, model);
		}
		if(!ysMajor.getIsNewRecord()){//编辑表单保存
			YsMajor t = ysMajorService.get(ysMajor.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysMajor, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysMajorService.save(t);//保存
		}else{//新增表单保存
			ysMajorService.save(ysMajor);//保存
		}
		addMessage(redirectAttributes, "保存专业类型管理成功");
		return "redirect:"+Global.getAdminPath()+"/major/majro_ad/ysMajor/?repage";
	}
	
	/**
	 * 删除专业类型管理
	 */
	@RequiresPermissions("major:majro_ad:ysMajor:del")
	@RequestMapping(value = "delete")
	public String delete(YsMajor ysMajor, RedirectAttributes redirectAttributes) {
		ysMajorService.delete(ysMajor);
		addMessage(redirectAttributes, "删除专业类型管理成功");
		return "redirect:"+Global.getAdminPath()+"/major/majro_ad/ysMajor/?repage";
	}
	
	/**
	 * 批量删除专业类型管理
	 */
	@RequiresPermissions("major:majro_ad:ysMajor:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysMajorService.delete(ysMajorService.get(id));
		}
		addMessage(redirectAttributes, "删除专业类型管理成功");
		return "redirect:"+Global.getAdminPath()+"/major/majro_ad/ysMajor/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("major:majro_ad:ysMajor:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsMajor ysMajor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专业类型管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsMajor> page = ysMajorService.findPage(new Page<YsMajor>(request, response, -1), ysMajor);
    		new ExportExcel("专业类型管理", YsMajor.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出专业类型管理记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/major/majro_ad/ysMajor/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("major:majro_ad:ysMajor:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsMajor> list = ei.getDataList(YsMajor.class);
			for (YsMajor ysMajor : list){
				try{
					ysMajorService.save(ysMajor);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条专业类型管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条专业类型管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入专业类型管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/major/majro_ad/ysMajor/?repage";
    }
	
	/**
	 * 下载导入专业类型管理数据模板
	 */
	@RequiresPermissions("major:majro_ad:ysMajor:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "专业类型管理数据导入模板.xlsx";
    		List<YsMajor> list = Lists.newArrayList(); 
    		new ExportExcel("专业类型管理数据", YsMajor.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/major/majro_ad/ysMajor/?repage";
    }
	
	@RequestMapping(value = "/findallmajor")
    @ResponseBody
    public DTO findallmajor(HttpServletRequest request,HttpServletResponse response) {
        DTO dto = new DTO();
        List<YsMajor> listmien=ysMajorDao.YsMajorSelectAll();
                      
           dto.setCode("100");
           dto.setData(listmien);
           dto.setMsg("获取专业类型信息成功");           
          return  dto;
    }
	
	

}