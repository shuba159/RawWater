/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.forum.web;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.jeeplus.modules.entity.YsForum;
import com.jeeplus.modules.entity.YsReply;
import com.jeeplus.modules.forum.dao.YsForumDao;
import com.jeeplus.modules.forum.service.YsForumService;

/**
 * 论坛管理Controller
 * @author hjm
 * @version 2018-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/forum/ysForum")
public class YsForumController extends BaseController {

	@Autowired
	private YsForumService ysForumService;
	
	@Autowired
	private YsForumDao ysForumDao;
	
	@ModelAttribute
	public YsForum get(@RequestParam(required=false) String id) {
		YsForum entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysForumService.get(id);
		}
		if (entity == null){
			entity = new YsForum();
		}
		return entity;
	}
	
	/**
	 * 论坛管理列表页面
	 */
	@RequiresPermissions("forum:ysForum:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsForum ysForum, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsForum> page = ysForumService.findPage(new Page<YsForum>(request, response), ysForum); 
		model.addAttribute("page", page);
		return "modules/forum/ysForumList";
	}

	/**
	 * 查看，增加，编辑论坛管理表单页面
	 */
	@RequiresPermissions(value={"forum:ysForum:view","forum:ysForum:add","forum:ysForum:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsForum ysForum, Model model) {
		System.out.println(ysForum);
		model.addAttribute("ysForum", ysForum);
		return "modules/forum/ysForumForm";
	}

	/**
	 * 保存论坛管理
	 */
	//@RequiresPermissions(value={"forum:ysForum:add","forum:ysForum:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsForum ysForum, Model model, RedirectAttributes redirectAttributes) throws Exception{
		System.err.println("进来了save");
        ysForum.setUserId(1);    // 发帖用户
		
		java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());
		ysForum.setForumTime(timeStamp);    // 修改日期为现在的时间
		
		ysForum.setTopState(0);    // 设置置顶
		ysForum.setSeeNumber(0);    // 设置查看人数
		
		System.err.println("ysForm de ::::"+ysForum.toString());
		
		if (!beanValidator(model, ysForum)){
			return form(ysForum, model);
		}
		if(!ysForum.getIsNewRecord()){//编辑表单保存
			YsForum t = ysForumService.get(ysForum.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysForum, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysForumService.save(t);//保存
		}else{//新增表单保存
			ysForumService.save(ysForum);//保存
		}
		addMessage(redirectAttributes, "保存论坛管理成功");
		return "redirect:"+Global.getAdminPath()+"/forum/ysForum/form";
	}
	
	/**
	 * 删除论坛管理
	 */
	@RequiresPermissions("forum:ysForum:del")
	@RequestMapping(value = "delete")
	public String delete(YsForum ysForum, RedirectAttributes redirectAttributes) {
		ysForumService.delete(ysForum);
		addMessage(redirectAttributes, "删除论坛管理成功");
		return "redirect:"+Global.getAdminPath()+"/forum/ysForum/?repage";
	}
	
	/**
	 * 批量删除论坛管理
	 */
	@RequiresPermissions("forum:ysForum:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysForumService.delete(ysForumService.get(id));
		}
		addMessage(redirectAttributes, "删除论坛管理成功");
		return "redirect:"+Global.getAdminPath()+"/forum/ysForum/?repage";
	}
	
	@RequestMapping(value="ysReply")
	public String ysReply(HttpServletRequest request,HttpServletResponse response,Model model){
		String string=request.getParameter("id");    // 帖子id
		System.err.println("已经进入  ysReply() 了。    id为："+string);
		System.err.println("已经进入  ysReply() 了。    id为："+string);
		System.err.println("已经进入  ysReply() 了。    id为："+string);
		System.err.println("已经进入  ysReply() 了。    id为："+string);
		
		List<YsReply> listYsReply = ysForumDao.idSelectReplay(Integer.parseInt(string));  // 通过id获取回复信息
		
		YsForum ysForum = ysForumDao.idSelectYsForum(Integer.parseInt(string)); // 通过id获取帖子内容
		
		System.err.println("ysForum:"+ysForum);
		
		model.addAttribute("listYsReply",listYsReply);
		model.addAttribute("ysForum", ysForum);
		
		return "modules/forum/ysReply";
	}
	
	@RequestMapping(value="deleteYsReply")
	public String deleteYsReply(){
		System.err.println("进入删除啦");
		return "redirect:"+Global.getAdminPath()+"/forum/ysForum/ysReply?id=1";
		//return "redirect:"+Global.getAdminPath()+"/forum/ysForum/?repage";
	}
}