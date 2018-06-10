/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.questions.web.questions_as;

import java.sql.Timestamp;
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
import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;
import com.jeeplus.modules.questions.service.questions_as.YsQuestionsService;

/**
 * 题库管理Controller
 * @author hjm
 * @version 2018-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/questions/questions_as/ysQuestions")
public class YsQuestionsController extends BaseController {

	@Autowired
	private YsQuestionsService ysQuestionsService;
	@Autowired
	private YsMajorDao ysMajorDao;
	
	@ModelAttribute
	public YsQuestions get(@RequestParam(required=false) String id) {
		YsQuestions entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysQuestionsService.get(id);
		}
		if (entity == null){
			entity = new YsQuestions();
		}
		return entity;
	}
	
	/**
	 * 题库管理列表页面
	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsQuestions ysQuestions, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsQuestions> page = ysQuestionsService.findPage(new Page<YsQuestions>(request, response), ysQuestions); 
		List<YsMajor> list=ysMajorDao.YsMajorSelectAll();   // 专业类型
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "modules/questions/questions_as/ysQuestionsList";
	}

	/**
	 * 查看，增加，编辑题库管理表单页面
	 */
	@RequiresPermissions(value={"questions:questions_as:ysQuestions:view","questions:questions_as:ysQuestions:add","questions:questions_as:ysQuestions:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsQuestions ysQuestions, Model model) {
		List<YsMajor> list=ysMajorDao.YsMajorSelectAll();  // 专业类型
		model.addAttribute("list", list);
		model.addAttribute("ysQuestions", ysQuestions);
		return "modules/questions/questions_as/ysQuestionsForm";
	}

	/**
	 * 保存题库管理
	 */
	@RequiresPermissions(value={"questions:questions_as:ysQuestions:add","questions:questions_as:ysQuestions:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsQuestions ysQuestions, Model model, RedirectAttributes redirectAttributes) throws Exception{
		ysQuestions.setQuestionClass(1);  // 试题分类已经删除，但是要来一个假数据省着改动太多
		java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());
		ysQuestions.setUpdateTime(timeStamp);  // 修改日期，为添加的时间
		
		if (!beanValidator(model, ysQuestions)){
			return form(ysQuestions, model);
		}
		if(!ysQuestions.getIsNewRecord()){//编辑表单保存
			YsQuestions t = ysQuestionsService.get(ysQuestions.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysQuestions, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysQuestionsService.save(t);//保存
		}else{//新增表单保存
			ysQuestionsService.save(ysQuestions);//保存
		}
		addMessage(redirectAttributes, "保存题库管理成功");
		return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
	}
	
	/**
	 * 删除题库管理
	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:del")
	@RequestMapping(value = "delete")
	public String delete(YsQuestions ysQuestions, RedirectAttributes redirectAttributes) {
		try {
			ysQuestionsService.delete(ysQuestions);
		} catch (Exception e) {
			addMessage(redirectAttributes, "删除试题失败，试题正在被使用");
			return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
		}
		addMessage(redirectAttributes, "删除题库管理成功");
		return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
	}
	
	/**
	 * 批量删除题库管理
	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysQuestionsService.delete(ysQuestionsService.get(id));
		}
		addMessage(redirectAttributes, "删除题库管理成功");
		return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsQuestions ysQuestions, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "题库管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsQuestions> page = ysQuestionsService.findPage(new Page<YsQuestions>(request, response, -1), ysQuestions);
    		new ExportExcel("题库管理", YsQuestions.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出题库管理记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsQuestions> list = ei.getDataList(YsQuestions.class);
			for (YsQuestions ysQuestions : list){
				try{
					ysQuestionsService.save(ysQuestions);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条题库管理记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条题库管理记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入题库管理失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
    }
	
	/**
	 * 下载导入题库管理数据模板
	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "题库管理数据导入模板.xlsx";
    		List<YsQuestions> list = Lists.newArrayList(); 
    		new ExportExcel("题库管理数据", YsQuestions.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/questions/questions_as/ysQuestions/?repage";
    }
	
	
	

}