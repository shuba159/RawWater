/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mien.web;

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
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.book.entity.book.DTO;
import com.jeeplus.modules.mien.dao.YsMienDao;
import com.jeeplus.modules.mien.entity.YsMien;
import com.jeeplus.modules.mien.service.YsMienService;


/**
 * 教学风采Controller
 * @author mx
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/mien/ysMien")
public class YsMienController extends BaseController {

	@Autowired
	private YsMienService ysMienService;
	@Autowired
	private YsMienDao ysMienDao;
	@ModelAttribute
	public YsMien get(@RequestParam(required=false) String id) {
		YsMien entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysMienService.get(id);
		}
		if (entity == null){
			entity = new YsMien();
		}
		return entity;
	}
	/**
	 * 首页教学风采方法
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/shouye")
    @ResponseBody
    public DTO shouye(HttpServletRequest request,HttpServletResponse response) {
        DTO dto = new DTO();
        List listmien=ysMienDao.selectAllHomePage();
                      
           dto.setCode("100");
           dto.setData(listmien);
           dto.setMsg("获取教学风采信息成功");           
          return  dto;
    }
	
	/**
	 * 成功列表页面
	 */
	//@RequiresPermissions("mien:ysMien:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsMien ysMien, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsMien> page = ysMienService.findPage(new Page<YsMien>(request, response), ysMien); 
		model.addAttribute("page", page);
		return "modules/mien/ysMienList";
	}

	/**
	 * 查看，增加，编辑成功表单页面
	 */
	@RequiresPermissions(value={"mien:ysMien:view","mien:ysMien:add","mien:ysMien:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsMien ysMien, Model model) {
		model.addAttribute("ysMien", ysMien);
		return "modules/mien/ysMienForm";
	}

	/**
	 * 保存成功
	 */
	@RequiresPermissions(value={"mien:ysMien:add","mien:ysMien:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsMien ysMien, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysMien)){
			return form(ysMien, model);
		}
		if(!ysMien.getIsNewRecord()){//编辑表单保存
			YsMien t = ysMienService.get(ysMien.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysMien, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysMienService.save(t);//保存
		}else{//新增表单保存
			ysMienService.save(ysMien);//保存
		}
		addMessage(redirectAttributes, "保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/mien/ysMien/?repage";
	}
	
	/**
	 * 删除成功
	 */
	@RequiresPermissions("mien:ysMien:del")
	@RequestMapping(value = "delete")
	public String delete(YsMien ysMien, RedirectAttributes redirectAttributes) {
		ysMienService.delete(ysMien);
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/mien/ysMien/?repage";
	}
	
	/**
	 * 批量删除成功
	 */
	@RequiresPermissions("mien:ysMien:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysMienService.delete(ysMienService.get(id));
		}
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/mien/ysMien/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("mien:ysMien:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsMien ysMien, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsMien> page = ysMienService.findPage(new Page<YsMien>(request, response, -1), ysMien);
    		new ExportExcel("成功", YsMien.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出成功记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mien/ysMien/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("mien:ysMien:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsMien> list = ei.getDataList(YsMien.class);
			for (YsMien ysMien : list){
				try{
					ysMienService.save(ysMien);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条成功记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条成功记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入成功失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mien/ysMien/?repage";
    }
	
	/**
	 * 下载导入成功数据模板
	 */
	@RequiresPermissions("mien:ysMien:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成功数据导入模板.xlsx";
    		List<YsMien> list = Lists.newArrayList(); 
    		new ExportExcel("成功数据", YsMien.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mien/ysMien/?repage";
    }
	
	
	

}