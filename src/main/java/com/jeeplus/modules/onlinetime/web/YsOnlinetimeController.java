/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.onlinetime.web;

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
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.onlinetime.entity.YsOnlinetime;
import com.jeeplus.modules.onlinetime.service.YsOnlinetimeService;

/**
 * 在线时长Controller
 * @author mx
 * @version 2018-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/onlinetime/ysOnlinetime")
public class YsOnlinetimeController extends BaseController {

	@Autowired
	private YsOnlinetimeService ysOnlinetimeService;
	
	@ModelAttribute
	public YsOnlinetime get(@RequestParam(required=false) String id) {
		YsOnlinetime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysOnlinetimeService.get(id);
		}
		if (entity == null){
			entity = new YsOnlinetime();
		}
		return entity;
	}
	
	/**
	 * 在线时长列表页面
	 */
	@RequiresPermissions("onlinetime:ysOnlinetime:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsOnlinetime ysOnlinetime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsOnlinetime> page = ysOnlinetimeService.findPage(new Page<YsOnlinetime>(request, response), ysOnlinetime); 
		model.addAttribute("page", page);
		return "modules/onlinetime/ysOnlinetimeList";
	}

	/**
	 * 查看，增加，编辑在线时长表单页面
	 */
	@RequiresPermissions(value={"onlinetime:ysOnlinetime:view","onlinetime:ysOnlinetime:add","onlinetime:ysOnlinetime:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsOnlinetime ysOnlinetime, Model model) {
		model.addAttribute("ysOnlinetime", ysOnlinetime);
		return "modules/onlinetime/ysOnlinetimeForm";
	}

	/**
	 * 保存在线时长
	 */
	@RequiresPermissions(value={"onlinetime:ysOnlinetime:add","onlinetime:ysOnlinetime:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsOnlinetime ysOnlinetime, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysOnlinetime)){
			return form(ysOnlinetime, model);
		}
		if(!ysOnlinetime.getIsNewRecord()){//编辑表单保存
			YsOnlinetime t = ysOnlinetimeService.get(ysOnlinetime.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysOnlinetime, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysOnlinetimeService.save(t);//保存
		}else{//新增表单保存
			ysOnlinetimeService.save(ysOnlinetime);//保存
		}
		addMessage(redirectAttributes, "保存在线时长成功");
		return "redirect:"+Global.getAdminPath()+"/onlinetime/ysOnlinetime/?repage";
	}
	
	/**
	 * 删除在线时长
	 */
	@RequiresPermissions("onlinetime:ysOnlinetime:del")
	@RequestMapping(value = "delete")
	public String delete(YsOnlinetime ysOnlinetime, RedirectAttributes redirectAttributes) {
		ysOnlinetimeService.delete(ysOnlinetime);
		addMessage(redirectAttributes, "删除在线时长成功");
		return "redirect:"+Global.getAdminPath()+"/onlinetime/ysOnlinetime/?repage";
	}
	
	/**
	 * 批量删除在线时长
	 */
	@RequiresPermissions("onlinetime:ysOnlinetime:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysOnlinetimeService.delete(ysOnlinetimeService.get(id));
		}
		addMessage(redirectAttributes, "删除在线时长成功");
		return "redirect:"+Global.getAdminPath()+"/onlinetime/ysOnlinetime/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("onlinetime:ysOnlinetime:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsOnlinetime ysOnlinetime, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "在线时长"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsOnlinetime> page = ysOnlinetimeService.findPage(new Page<YsOnlinetime>(request, response, -1), ysOnlinetime);
    		new ExportExcel("在线时长", YsOnlinetime.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出在线时长记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/onlinetime/ysOnlinetime/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("onlinetime:ysOnlinetime:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsOnlinetime> list = ei.getDataList(YsOnlinetime.class);
			for (YsOnlinetime ysOnlinetime : list){
				try{
					ysOnlinetimeService.save(ysOnlinetime);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条在线时长记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条在线时长记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入在线时长失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/onlinetime/ysOnlinetime/?repage";
    }
	
	/**
	 * 下载导入在线时长数据模板
	 */
	@RequiresPermissions("onlinetime:ysOnlinetime:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "在线时长数据导入模板.xlsx";
    		List<YsOnlinetime> list = Lists.newArrayList(); 
    		new ExportExcel("在线时长数据", YsOnlinetime.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/onlinetime/ysOnlinetime/?repage";
    }
	
	
	

}