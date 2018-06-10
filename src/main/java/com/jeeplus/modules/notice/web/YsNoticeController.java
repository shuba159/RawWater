/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.notice.web;

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
import com.jeeplus.modules.notice.dao.YsNoticeDao;
import com.jeeplus.modules.notice.entity.YsNotice;
import com.jeeplus.modules.notice.service.YsNoticeService;
/**
 * 培训通知Controller
 * @author mx
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/ysNotice")
public class YsNoticeController extends BaseController {

	@Autowired
	private YsNoticeService ysNoticeService;
	@Autowired
	private YsNoticeDao ysNoticeDao;
	/**
	 * 首页培训通知方法
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/shouye", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DTO shouye(HttpServletRequest request,HttpServletResponse response) {
        DTO dto = new DTO();
        List<YsNotice> listnotice=ysNoticeDao.selectAllHomePage(); 
                      
           dto.setCode("200");
           dto.setData(listnotice);
           dto.setMsg("获取通知信息成功");           
          return  dto;
    }
	@ModelAttribute
	public YsNotice get(@RequestParam(required=false) String id) {
		YsNotice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysNoticeService.get(id);
		}
		if (entity == null){
			entity = new YsNotice();
		}
		return entity;
	}
	
	/**
	 * 培训通知列表页面
	 */
	//@RequiresPermissions("notice:ysNotice:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsNotice ysNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsNotice> page = ysNoticeService.findPage(new Page<YsNotice>(request, response), ysNotice); 
		model.addAttribute("page", page);
		return "modules/notice/ysNoticeList";
	}

	/**
	 * 查看，增加，编辑培训通知表单页面
	 */
	@RequiresPermissions(value={"notice:ysNotice:view","notice:ysNotice:add","notice:ysNotice:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsNotice ysNotice, Model model) {
		model.addAttribute("ysNotice", ysNotice);
		return "modules/notice/ysNoticeForm";
	}

	/**
	 * 保存培训通知
	 */
	@RequiresPermissions(value={"notice:ysNotice:add","notice:ysNotice:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsNotice ysNotice, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysNotice)){
			return form(ysNotice, model);
		}
		if(!ysNotice.getIsNewRecord()){//编辑表单保存
			YsNotice t = ysNoticeService.get(ysNotice.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysNotice, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysNoticeService.save(t);//保存
		}else{//新增表单保存
			ysNoticeService.save(ysNotice);//保存
		}
		addMessage(redirectAttributes, "保存培训通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/ysNotice/?repage";
	}
	
	/**
	 * 删除培训通知
	 */
	@RequiresPermissions("notice:ysNotice:del")
	@RequestMapping(value = "delete")
	public String delete(YsNotice ysNotice, RedirectAttributes redirectAttributes) {
		ysNoticeService.delete(ysNotice);
		addMessage(redirectAttributes, "删除培训通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/ysNotice/?repage";
	}
	
	/**
	 * 批量删除培训通知
	 */
	@RequiresPermissions("notice:ysNotice:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysNoticeService.delete(ysNoticeService.get(id));
		}
		addMessage(redirectAttributes, "删除培训通知成功");
		return "redirect:"+Global.getAdminPath()+"/notice/ysNotice/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("notice:ysNotice:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsNotice ysNotice, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "培训通知"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsNotice> page = ysNoticeService.findPage(new Page<YsNotice>(request, response, -1), ysNotice);
    		new ExportExcel("培训通知", YsNotice.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出培训通知记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/notice/ysNotice/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("notice:ysNotice:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsNotice> list = ei.getDataList(YsNotice.class);
			for (YsNotice ysNotice : list){
				try{
					ysNoticeService.save(ysNotice);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条培训通知记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条培训通知记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入培训通知失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/notice/ysNotice/?repage";
    }
	
	/**
	 * 下载导入培训通知数据模板
	 */
	@RequiresPermissions("notice:ysNotice:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "培训通知数据导入模板.xlsx";
    		List<YsNotice> list = Lists.newArrayList(); 
    		new ExportExcel("培训通知数据", YsNotice.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/notice/ysNotice/?repage";
    }
	
	
	

}