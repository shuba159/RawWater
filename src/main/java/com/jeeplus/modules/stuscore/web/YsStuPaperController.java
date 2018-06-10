/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.stuscore.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jeeplus.modules.stuscore.dao.YsStuPaperDao;
import com.jeeplus.modules.stuscore.entity.ErrQuestion;
import com.jeeplus.modules.stuscore.entity.YsStuPaper;
import com.jeeplus.modules.stuscore.service.YsStuPaperService;

/**
 * 错题Controller
 * @author mx
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/stuscore/ysStuPaper")
public class YsStuPaperController extends BaseController {

	@Autowired
	private YsStuPaperService ysStuPaperService;
	@Autowired
	private YsStuPaperDao ysStuPaperDao;
	@ModelAttribute
	public YsStuPaper get(@RequestParam(required=false) String id) {
		YsStuPaper entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysStuPaperService.get(id);
		}
		if (entity == null){
			entity = new YsStuPaper();
		}
		return entity;
	}
	@RequestMapping(value = "/SlectctByType")
	@ResponseBody
	public Map<String, Object> SubmitRanPaper(String typeId) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(typeId!=null){
			//typeId="1527648560000";
			List <ErrQuestion> liststu=ysStuPaperDao.selectByTypeid(typeId);
			jsonMap.put("code", 200);
			jsonMap.put("msg", "查看错题成功");
			jsonMap.put("data", liststu);
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "传值为空");
		return jsonMap;
		
	}
	/**
	 * 错题列表页面
	 */
	@RequiresPermissions("stuscore:ysStuPaper:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsStuPaper ysStuPaper, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsStuPaper> page = ysStuPaperService.findPage(new Page<YsStuPaper>(request, response), ysStuPaper); 
		model.addAttribute("page", page);
		return "modules/stuscore/ysStuPaperList";
	}

	/**
	 * 查看，增加，编辑错题表单页面
	 */
	@RequiresPermissions(value={"stuscore:ysStuPaper:view","stuscore:ysStuPaper:add","stuscore:ysStuPaper:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsStuPaper ysStuPaper, Model model) {
		model.addAttribute("ysStuPaper", ysStuPaper);
		return "modules/stuscore/ysStuPaperForm";
	}

	/**
	 * 保存错题
	 */
	@RequiresPermissions(value={"stuscore:ysStuPaper:add","stuscore:ysStuPaper:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsStuPaper ysStuPaper, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysStuPaper)){
			return form(ysStuPaper, model);
		}
		if(!ysStuPaper.getIsNewRecord()){//编辑表单保存
			YsStuPaper t = ysStuPaperService.get(ysStuPaper.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysStuPaper, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysStuPaperService.save(t);//保存
		}else{//新增表单保存
			ysStuPaperService.save(ysStuPaper);//保存
		}
		addMessage(redirectAttributes, "保存错题成功");
		return "redirect:"+Global.getAdminPath()+"/stuscore/ysStuPaper/?repage";
	}
	
	/**
	 * 删除错题
	 */
	@RequiresPermissions("stuscore:ysStuPaper:del")
	@RequestMapping(value = "delete")
	public String delete(YsStuPaper ysStuPaper, RedirectAttributes redirectAttributes) {
		ysStuPaperService.delete(ysStuPaper);
		addMessage(redirectAttributes, "删除错题成功");
		return "redirect:"+Global.getAdminPath()+"/stuscore/ysStuPaper/?repage";
	}
	
	/**
	 * 批量删除错题
	 */
	@RequiresPermissions("stuscore:ysStuPaper:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysStuPaperService.delete(ysStuPaperService.get(id));
		}
		addMessage(redirectAttributes, "删除错题成功");
		return "redirect:"+Global.getAdminPath()+"/stuscore/ysStuPaper/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("stuscore:ysStuPaper:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsStuPaper ysStuPaper, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "错题"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsStuPaper> page = ysStuPaperService.findPage(new Page<YsStuPaper>(request, response, -1), ysStuPaper);
    		new ExportExcel("错题", YsStuPaper.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出错题记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/stuscore/ysStuPaper/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("stuscore:ysStuPaper:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsStuPaper> list = ei.getDataList(YsStuPaper.class);
			for (YsStuPaper ysStuPaper : list){
				try{
					ysStuPaperService.save(ysStuPaper);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条错题记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条错题记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入错题失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/stuscore/ysStuPaper/?repage";
    }
	
	/**
	 * 下载导入错题数据模板
	 */
	@RequiresPermissions("stuscore:ysStuPaper:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "错题数据导入模板.xlsx";
    		List<YsStuPaper> list = Lists.newArrayList(); 
    		new ExportExcel("错题数据", YsStuPaper.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/stuscore/ysStuPaper/?repage";
    }
	
	
	

}