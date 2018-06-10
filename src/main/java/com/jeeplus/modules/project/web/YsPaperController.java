/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.web;

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
import com.jeeplus.modules.entity.YsPaper;
import com.jeeplus.modules.project.service.YsPaperService;

/**
 * 试卷Controller
 * @author wdy
 * @version 2018-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/project/ysPaper")
public class YsPaperController extends BaseController {

	@Autowired
	private YsPaperService ysPaperService;
	
	@ModelAttribute
	public YsPaper get(@RequestParam(required=false) String id) {
		YsPaper entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysPaperService.get(id);
		}
		if (entity == null){
			entity = new YsPaper();
		}
		return entity;
	}
	
	/**
	 * 试卷列表页面
	 */
//	@RequiresPermissions("project:ysPaper:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsPaper ysPaper, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsPaper> page = ysPaperService.findPage(new Page<YsPaper>(request, response), ysPaper); 
		model.addAttribute("page", page);
		return "modules/project/ysPaperList";
	}

	/**
	 * 查看，增加，编辑试卷表单页面
	 */
//	@RequiresPermissions(value={"project:ysPaper:view","project:ysPaper:add","project:ysPaper:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsPaper ysPaper, Model model) {
		model.addAttribute("ysPaper", ysPaper);
		return "modules/project/ysPaperForm";
	}

	/**
	 * 保存试卷
	 */
//	@RequiresPermissions(value={"project:ysPaper:add","project:ysPaper:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsPaper ysPaper, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysPaper)){
			return form(ysPaper, model);
		}
		if(!ysPaper.getIsNewRecord()){//编辑表单保存
			YsPaper t = ysPaperService.get(ysPaper.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysPaper, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysPaperService.save(t);//保存
		}else{//新增表单保存
			ysPaperService.save(ysPaper);//保存
		}
		addMessage(redirectAttributes, "保存试卷成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysPaper/?repage";
	}
	
	/**
	 * 删除试卷
	 */
//	@RequiresPermissions("project:ysPaper:del")
	@RequestMapping(value = "delete")
	public String delete(YsPaper ysPaper, RedirectAttributes redirectAttributes) {
		ysPaperService.delete(ysPaper);
		addMessage(redirectAttributes, "删除试卷成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysPaper/?repage";
	}
	
	/**
	 * 批量删除试卷
	 */
//	@RequiresPermissions("project:ysPaper:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysPaperService.delete(ysPaperService.get(id));
		}
		addMessage(redirectAttributes, "删除试卷成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysPaper/?repage";
	}

}