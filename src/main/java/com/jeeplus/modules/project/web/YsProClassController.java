/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.entity.YsProClass;
import com.jeeplus.modules.project.service.YsProClassService;

/**
 * 项目分类Controller
 * @author wdy
 * @version 2018-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/project/ysProClass")
public class YsProClassController extends BaseController {

	@Autowired
	private YsProClassService ysProClassService;
	
	@ModelAttribute
	public YsProClass get(@RequestParam(required=false) String id) {
		YsProClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysProClassService.get(id);
		}
		if (entity == null){
			entity = new YsProClass();
		}
		return entity;
	}
	
	/**
	 * 项目分类列表页面
	 */
//	@RequiresPermissions("project:ysProClass:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsProClass ysProClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsProClass> page = ysProClassService.findPage(new Page<YsProClass>(request, response), ysProClass); 
		model.addAttribute("page", page);
		return "modules/project/ysProClassList";
	}

	/**
	 * 查看，增加，编辑项目分类表单页面
	 */
//	@RequiresPermissions(value={"project:ysProClass:view","project:ysProClass:add","project:ysProClass:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsProClass ysProClass, Model model) {
		model.addAttribute("ysProClass", ysProClass);
		return "modules/project/ysProClassForm";
	}

	/**
	 * 保存项目分类
	 */
//	@RequiresPermissions(value={"project:ysProClass:add","project:ysProClass:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsProClass ysProClass, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysProClass)){
			return form(ysProClass, model);
		}
		if(!ysProClass.getIsNewRecord()){//编辑表单保存
			YsProClass t = ysProClassService.get(ysProClass.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysProClass, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysProClassService.save(t);//保存
		}else{//新增表单保存
			ysProClassService.save(ysProClass);//保存
		}
		addMessage(redirectAttributes, "保存项目分类成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysProClass/?repage";
	}
	
	/**
	 * 删除项目分类
	 */
//	@RequiresPermissions("project:ysProClass:del")
	@RequestMapping(value = "delete")
	public String delete(YsProClass ysProClass, RedirectAttributes redirectAttributes) {
		ysProClassService.delete(ysProClass);
		addMessage(redirectAttributes, "删除项目分类成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysProClass/?repage";
	}
	
	/**
	 * 批量删除项目分类
	 */
//	@RequiresPermissions("project:ysProClass:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysProClassService.delete(ysProClassService.get(id));
		}
		addMessage(redirectAttributes, "删除项目分类成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysProClass/?repage";
	}
	

}