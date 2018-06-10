/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.dynamic.web;

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
import com.jeeplus.modules.dynamic.dao.YsDynamicDao;
import com.jeeplus.modules.dynamic.entity.YsDynamic;
import com.jeeplus.modules.dynamic.service.YsDynamicService;


/**
 * 动态浏览Controller
 * @author mx
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/dynamic/ysDynamic")
public class YsDynamicController extends BaseController {
	@Autowired
	private YsDynamicDao ysdydao;
	@Autowired
	private YsDynamicService ysDynamicService;
	
	@ModelAttribute
	public YsDynamic get(@RequestParam(required=false) String id) {
		YsDynamic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysDynamicService.get(id);
		}
		if (entity == null){
			entity = new YsDynamic();
		}
		return entity;
	}
	/**
	 * 首页动态浏览方法
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/shouye")
    @ResponseBody
    public DTO shouye(HttpServletRequest request,HttpServletResponse response) {
        DTO dto = new DTO();
        List<YsDynamic> listUDynamic=ysdydao.selectAllHomePage();  
        listUDynamic.get(0).getId();
        listUDynamic.remove(0).getId();
           dto.setCode("100");
           dto.setData(listUDynamic);
           dto.setMsg("获取首页动态浏览信息成功");           
          return  dto;
    }
	
	/**
	 * 成功列表页面
	 */
	//@RequiresPermissions("dynamic:ysDynamic:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsDynamic ysDynamic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsDynamic> page = ysDynamicService.findPage(new Page<YsDynamic>(request, response), ysDynamic); 
		model.addAttribute("page", page);
		return "modules/dynamic/ysDynamicList";
	}

	/**
	 * 查看，增加，编辑成功表单页面
	 */
	//@RequiresPermissions(value={"dynamic:ysDynamic:view","dynamic:ysDynamic:add","dynamic:ysDynamic:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsDynamic ysDynamic, Model model) {
		model.addAttribute("ysDynamic", ysDynamic);
		return "modules/dynamic/ysDynamicForm";
	}

	/**
	 * 保存成功
	 */
	//@RequiresPermissions(value={"dynamic:ysDynamic:add","dynamic:ysDynamic:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsDynamic ysDynamic, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysDynamic)){
			return form(ysDynamic, model);
		}
		if(!ysDynamic.getIsNewRecord()){//编辑表单保存
			YsDynamic t = ysDynamicService.get(ysDynamic.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysDynamic, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysDynamicService.save(t);//保存
		}else{//新增表单保存
			ysDynamicService.save(ysDynamic);//保存
		}
		addMessage(redirectAttributes, "保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/dynamic/ysDynamic/?repage";
	}
	
	/**
	 * 删除成功
	 */
	//@RequiresPermissions("dynamic:ysDynamic:del")
	@RequestMapping(value = "delete")
	public String delete(YsDynamic ysDynamic, RedirectAttributes redirectAttributes) {
		ysDynamicService.delete(ysDynamic);
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/dynamic/ysDynamic/?repage";
	}
	
	/**
	 * 批量删除成功
	 */
	//@RequiresPermissions("dynamic:ysDynamic:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysDynamicService.delete(ysDynamicService.get(id));
		}
		addMessage(redirectAttributes, "删除成功成功");
		return "redirect:"+Global.getAdminPath()+"/dynamic/ysDynamic/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	//@RequiresPermissions("dynamic:ysDynamic:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsDynamic ysDynamic, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成功"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsDynamic> page = ysDynamicService.findPage(new Page<YsDynamic>(request, response, -1), ysDynamic);
    		new ExportExcel("成功", YsDynamic.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出成功记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/dynamic/ysDynamic/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	//@RequiresPermissions("dynamic:ysDynamic:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsDynamic> list = ei.getDataList(YsDynamic.class);
			for (YsDynamic ysDynamic : list){
				try{
					ysDynamicService.save(ysDynamic);
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
		return "redirect:"+Global.getAdminPath()+"/dynamic/ysDynamic/?repage";
    }
	
	/**
	 * 下载导入成功数据模板
	 */
	//@RequiresPermissions("dynamic:ysDynamic:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成功数据导入模板.xlsx";
    		List<YsDynamic> list = Lists.newArrayList(); 
    		new ExportExcel("成功数据", YsDynamic.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/dynamic/ysDynamic/?repage";
    }
	
	
	

}