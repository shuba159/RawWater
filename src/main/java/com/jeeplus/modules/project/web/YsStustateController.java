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
import com.jeeplus.modules.entity.YsStustate;
import com.jeeplus.modules.project.service.YsStustateService;

/**
 * 学习记录Controller
 * @author wdy
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/stustate/ysStustate")
public class YsStustateController extends BaseController {

	@Autowired
	private YsStustateService ysStustateService;
	
	@ModelAttribute
	public YsStustate get(@RequestParam(required=false) String id) {
		YsStustate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysStustateService.get(id);
		}
		if (entity == null){
			entity = new YsStustate();
		}
		return entity;
	}
	
	/**
	 * 学习记录列表页面
	 */
	@RequiresPermissions("stustate:ysStustate:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsStustate ysStustate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsStustate> page = ysStustateService.findPage(new Page<YsStustate>(request, response), ysStustate); 
		model.addAttribute("page", page);
		return "modules/stustate/ysStustateList";
	}

	/**
	 * 查看，增加，编辑学习记录表单页面
	 */
	@RequiresPermissions(value={"stustate:ysStustate:view","stustate:ysStustate:add","stustate:ysStustate:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsStustate ysStustate, Model model) {
		model.addAttribute("ysStustate", ysStustate);
		return "modules/stustate/ysStustateForm";
	}

	/**
	 * 保存学习记录
	 */
	@RequiresPermissions(value={"stustate:ysStustate:add","stustate:ysStustate:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsStustate ysStustate, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysStustate)){
			return form(ysStustate, model);
		}
		if(!ysStustate.getIsNewRecord()){//编辑表单保存
			YsStustate t = ysStustateService.get(ysStustate.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysStustate, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysStustateService.save(t);//保存
		}else{//新增表单保存
			ysStustateService.save(ysStustate);//保存
		}
		addMessage(redirectAttributes, "保存学习记录成功");
		return "redirect:"+Global.getAdminPath()+"/stustate/ysStustate/?repage";
	}
	
	/**
	 * 删除学习记录
	 */
	@RequiresPermissions("stustate:ysStustate:del")
	@RequestMapping(value = "delete")
	public String delete(YsStustate ysStustate, RedirectAttributes redirectAttributes) {
		ysStustateService.delete(ysStustate);
		addMessage(redirectAttributes, "删除学习记录成功");
		return "redirect:"+Global.getAdminPath()+"/stustate/ysStustate/?repage";
	}
	
	/**
	 * 批量删除学习记录
	 */
	@RequiresPermissions("stustate:ysStustate:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysStustateService.delete(ysStustateService.get(id));
		}
		addMessage(redirectAttributes, "删除学习记录成功");
		return "redirect:"+Global.getAdminPath()+"/stustate/ysStustate/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("stustate:ysStustate:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsStustate ysStustate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学习记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsStustate> page = ysStustateService.findPage(new Page<YsStustate>(request, response, -1), ysStustate);
    		new ExportExcel("学习记录", YsStustate.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学习记录记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/stustate/ysStustate/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("stustate:ysStustate:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsStustate> list = ei.getDataList(YsStustate.class);
			for (YsStustate ysStustate : list){
				try{
					ysStustateService.save(ysStustate);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条学习记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条学习记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入学习记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/stustate/ysStustate/?repage";
    }
	
	/**
	 * 下载导入学习记录数据模板
	 */
	@RequiresPermissions("stustate:ysStustate:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学习记录数据导入模板.xlsx";
    		List<YsStustate> list = Lists.newArrayList(); 
    		new ExportExcel("学习记录数据", YsStustate.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/stustate/ysStustate/?repage";
    }
	
	
	

}