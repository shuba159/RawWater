/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.course.web.addcoursetwo;

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
import com.jeeplus.modules.course.service.addcoursetwo.YsCourseTwoService;
import com.jeeplus.modules.entity.YsCourse;
import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;

/**
 * 添加课件Controller
 * @author hjm
 * @version 2018-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/course/addcoursetwo/ysCourse")
public class YsCourseTwoController extends BaseController {

	@Autowired
	private YsCourseTwoService ysCourseService;
	@Autowired
	private YsMajorDao ysMajorDao;
	
	@ModelAttribute
	public YsCourse get(@RequestParam(required=false) String id) {
		YsCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysCourseService.get(id);
		}
		if (entity == null){
			entity = new YsCourse();
		}
		return entity;
	}
	
	/**
	 * 添加学习资料列表页面
	 */
	@RequiresPermissions("course:addcoursetwo:ysCourse:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsCourse ysCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsCourse> page = ysCourseService.findPage(new Page<YsCourse>(request, response), ysCourse); 
		List<YsMajor> list=ysMajorDao.YsMajorSelectAll();
		model.addAttribute("list", list);  // 专业类型
		model.addAttribute("page", page);
//		return "modules/course/addcoursetwo/ysCourseList";
		return "modules/course/addcoursetwo/ysCourseForm";  // 这里就都跳form的表单页面了
	}

	/**
	 * 查看，增加，编辑添加学习资料表单页面
	 */
	@RequiresPermissions(value={"course:addcoursetwo:ysCourse:view","course:addcoursetwo:ysCourse:add","course:addcoursetwo:ysCourse:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsCourse ysCourse, Model model) {
		
		List<YsMajor> list=ysMajorDao.YsMajorSelectAll();
		
		model.addAttribute("ysCourse", ysCourse);
		model.addAttribute("list", list);
		return "modules/course/addcoursetwo/ysCourseForm";
	}

	/**
	 * 保存添加学习资料
	 */
	@RequiresPermissions(value={"course:addcoursetwo:ysCourse:add","course:addcoursetwo:ysCourse:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsCourse ysCourse, Model model, RedirectAttributes redirectAttributes) throws Exception{
		java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());
		ysCourse.setCreateTime(timeStamp);    // 创建时间
        ysCourse.setUpdateTime(timeStamp);    // 修改时间
		String s = ysCourse.getAddress(); // 更改文件的路径问题
		
		if(s.length()!=0){
			String s1 = s.substring(1);
			ysCourse.setAddress("http://192.168.10.138:8080"+s1);
		}else{
			addMessage(redirectAttributes, "添加课件失败，请选择文件");
			return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse";
		}
		
		if (!beanValidator(model, ysCourse)){
			return form(ysCourse, model);
		}
		if(!ysCourse.getIsNewRecord()){//编辑表单保存
			YsCourse t = ysCourseService.get(ysCourse.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysCourse, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysCourseService.save(t);//保存
		}else{//新增表单保存
			ysCourseService.save(ysCourse);//保存
		}
		addMessage(redirectAttributes, "保存添加学习资料成功");
		
		return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse";
	}
	
	/**
	 * 删除添加学习资料
	 */
//	@RequiresPermissions("course:addcoursetwo:ysCourse:del")
//	@RequestMapping(value = "delete")
//	public String delete(YsCourse ysCourse, RedirectAttributes redirectAttributes) {
//		ysCourseService.delete(ysCourse);
//		addMessage(redirectAttributes, "删除添加学习资料成功");
//		return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse/?repage";
//	}
	
	/**
	 * 批量删除添加学习资料
	 */
//	@RequiresPermissions("course:addcoursetwo:ysCourse:del")
//	@RequestMapping(value = "deleteAll")
//	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
//		String idArray[] =ids.split(",");
//		for(String id : idArray){
//			ysCourseService.delete(ysCourseService.get(id));
//		}
//		addMessage(redirectAttributes, "删除添加学习资料成功");
//		return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse/?repage";
//	}
	
	/**
	 * 导出excel文件
	 */
//	@RequiresPermissions("course:addcoursetwo:ysCourse:export")
//    @RequestMapping(value = "export", method=RequestMethod.POST)
//    public String exportFile(YsCourse ysCourse, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "添加学习资料"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//            Page<YsCourse> page = ysCourseService.findPage(new Page<YsCourse>(request, response, -1), ysCourse);
//    		new ExportExcel("添加学习资料", YsCourse.class).setDataList(page.getList()).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导出添加学习资料记录失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse/?repage";
//    }

	/**
	 * 导入Excel数据

	 */
//	@RequiresPermissions("course:addcoursetwo:ysCourse:import")
//    @RequestMapping(value = "import", method=RequestMethod.POST)
//    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
//		try {
//			int successNum = 0;
//			int failureNum = 0;
//			StringBuilder failureMsg = new StringBuilder();
//			ImportExcel ei = new ImportExcel(file, 1, 0);
//			List<YsCourse> list = ei.getDataList(YsCourse.class);
//			for (YsCourse ysCourse : list){
//				try{
//					ysCourseService.save(ysCourse);
//					successNum++;
//				}catch(ConstraintViolationException ex){
//					failureNum++;
//				}catch (Exception ex) {
//					failureNum++;
//				}
//			}
//			if (failureNum>0){
//				failureMsg.insert(0, "，失败 "+failureNum+" 条添加学习资料记录。");
//			}
//			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条添加学习资料记录"+failureMsg);
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导入添加学习资料失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse/?repage";
//    }
	
	/**
	 * 下载导入添加学习资料数据模板
	 */
//	@RequiresPermissions("course:addcoursetwo:ysCourse:import")
//    @RequestMapping(value = "import/template")
//    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//            String fileName = "添加学习资料数据导入模板.xlsx";
//    		List<YsCourse> list = Lists.newArrayList(); 
//    		new ExportExcel("添加学习资料数据", YsCourse.class, 1).setDataList(list).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
//		}
//		return "redirect:"+Global.getAdminPath()+"/course/addcoursetwo/ysCourse/?repage";
//    }

}