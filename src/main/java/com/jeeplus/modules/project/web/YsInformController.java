/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.web;

import java.util.Date;

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
import com.jeeplus.modules.entity.YsInform;
import com.jeeplus.modules.project.service.YsInformService;

/**
 * 通知Controller
 * @author wdy
 * @version 2018-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/inform/ysInform")
public class YsInformController extends BaseController {

	@Autowired
	private YsInformService ysInformService;
	
	@ModelAttribute
	public YsInform get(@RequestParam(required=false) String id) {
		YsInform entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysInformService.get(id);
		}
		if (entity == null){
			entity = new YsInform();
		}
		return entity;
	}
	
	/**
	 * 通知列表页面
	 */
//	@RequiresPermissions("inform:ysInform:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsInform ysInform, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsInform> page = ysInformService.findPage(new Page<YsInform>(request, response), ysInform); 
		model.addAttribute("page", page);
		return "modules/inform/ysInformList";
	}

	/**
	 * 查看，增加，编辑通知表单页面
	 */
//	@RequiresPermissions(value={"inform:ysInform:view","inform:ysInform:add","inform:ysInform:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsInform ysInform, Model model) {
		model.addAttribute("ysInform", ysInform);
		return "modules/project/ysInformForm";
	}

	/**
	 * 保存通知
	 */
//	@RequiresPermissions(value={"inform:ysInform:add","inform:ysInform:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsInform ysInform, Model model, RedirectAttributes redirectAttributes) throws Exception{
		// 通知人员的id
		String strIds = ysInform.getStrIds();
		ysInform.setInformTime(new Date());
		// 设置用户id
		//ysInform.setCreateId(?);
		ysInformService.save(ysInform);//保存
		// 获取项目开始 结束 时间      考试开始 结束 时间     参加培训的人员
		// 需要发送通知 到前端
		addMessage(redirectAttributes, "保存通知成功");
		return "redirect:"+Global.getAdminPath()+"/project/ysProject/?repage";
	}
	
	/**
	 * 删除通知
	 */
//	@RequiresPermissions("inform:ysInform:del")
	@RequestMapping(value = "delete")
	public String delete(YsInform ysInform, RedirectAttributes redirectAttributes) {
		ysInformService.delete(ysInform);
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:"+Global.getAdminPath()+"/inform/ysInform/?repage";
	}
	
	/**
	 * 批量删除通知
	 */
//	@RequiresPermissions("inform:ysInform:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysInformService.delete(ysInformService.get(id));
		}
		addMessage(redirectAttributes, "删除通知成功");
		return "redirect:"+Global.getAdminPath()+"/inform/ysInform/?repage";
	}
	
}