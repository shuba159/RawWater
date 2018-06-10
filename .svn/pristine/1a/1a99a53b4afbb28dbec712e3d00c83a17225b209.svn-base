/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.myscore.web;

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
import com.jeeplus.modules.myscore.entity.YsMyScore;
import com.jeeplus.modules.myscore.service.YsMyScoreService;

/**
 * 自测Controller
 * @author mx
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/myscore/ysMyScore")
public class YsMyScoreController extends BaseController {

	@Autowired
	private YsMyScoreService ysMyScoreService;
	
	@ModelAttribute
	public YsMyScore get(@RequestParam(required=false) String id) {
		YsMyScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysMyScoreService.get(id);
		}
		if (entity == null){
			entity = new YsMyScore();
		}
		return entity;
	}
	
	/**
	 * 自测列表页面
	 */
	@RequiresPermissions("myscore:ysMyScore:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsMyScore ysMyScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsMyScore> page = ysMyScoreService.findPage(new Page<YsMyScore>(request, response), ysMyScore); 
		model.addAttribute("page", page);
		return "modules/myscore/ysMyScoreList";
	}

	/**
	 * 查看，增加，编辑自测表单页面
	 */
	@RequiresPermissions(value={"myscore:ysMyScore:view","myscore:ysMyScore:add","myscore:ysMyScore:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsMyScore ysMyScore, Model model) {
		model.addAttribute("ysMyScore", ysMyScore);
		return "modules/myscore/ysMyScoreForm";
	}

	/**
	 * 保存自测
	 */
	@RequiresPermissions(value={"myscore:ysMyScore:add","myscore:ysMyScore:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsMyScore ysMyScore, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysMyScore)){
			return form(ysMyScore, model);
		}
		if(!ysMyScore.getIsNewRecord()){//编辑表单保存
			YsMyScore t = ysMyScoreService.get(ysMyScore.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysMyScore, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysMyScoreService.save(t);//保存
		}else{//新增表单保存
			ysMyScoreService.save(ysMyScore);//保存
		}
		addMessage(redirectAttributes, "保存自测成功");
		return "redirect:"+Global.getAdminPath()+"/myscore/ysMyScore/?repage";
	}
	
	/**
	 * 删除自测
	 */
	@RequiresPermissions("myscore:ysMyScore:del")
	@RequestMapping(value = "delete")
	public String delete(YsMyScore ysMyScore, RedirectAttributes redirectAttributes) {
		ysMyScoreService.delete(ysMyScore);
		addMessage(redirectAttributes, "删除自测成功");
		return "redirect:"+Global.getAdminPath()+"/myscore/ysMyScore/?repage";
	}
	
	/**
	 * 批量删除自测
	 */
	@RequiresPermissions("myscore:ysMyScore:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysMyScoreService.delete(ysMyScoreService.get(id));
		}
		addMessage(redirectAttributes, "删除自测成功");
		return "redirect:"+Global.getAdminPath()+"/myscore/ysMyScore/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("myscore:ysMyScore:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsMyScore ysMyScore, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "自测"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsMyScore> page = ysMyScoreService.findPage(new Page<YsMyScore>(request, response, -1), ysMyScore);
    		new ExportExcel("自测", YsMyScore.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出自测记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/myscore/ysMyScore/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("myscore:ysMyScore:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsMyScore> list = ei.getDataList(YsMyScore.class);
			for (YsMyScore ysMyScore : list){
				try{
					ysMyScoreService.save(ysMyScore);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条自测记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条自测记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入自测失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/myscore/ysMyScore/?repage";
    }
	
	/**
	 * 下载导入自测数据模板
	 */
	@RequiresPermissions("myscore:ysMyScore:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "自测数据导入模板.xlsx";
    		List<YsMyScore> list = Lists.newArrayList(); 
    		new ExportExcel("自测数据", YsMyScore.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/myscore/ysMyScore/?repage";
    }
	
	
	

}