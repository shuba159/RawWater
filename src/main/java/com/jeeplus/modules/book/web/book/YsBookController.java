/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.book.web.book;

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
import com.jeeplus.modules.book.dao.book.YsBookDao;
import com.jeeplus.modules.book.entity.book.DTO;
import com.jeeplus.modules.book.entity.book.YsBook;

import com.jeeplus.modules.book.service.book.YsBookService;


/**
 * 推荐书目Controller
 * @author mx
 * @version 2018-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/book/book/ysBook")
public class YsBookController extends BaseController {

	@Autowired
	private YsBookService ysBookService;
	@Autowired
	private  YsBookDao Ys;
	@ModelAttribute
	public YsBook get(@RequestParam(required=false) String id) {
		YsBook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysBookService.get(id);
		}
		if (entity == null){
			entity = new YsBook();
		}
		return entity;
	}
	
	/**
	 * 推荐书目列表页面
	 */
	//@RequiresPermissions("book:book:ysBook:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsBook ysBook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsBook> page = ysBookService.findPage(new Page<YsBook>(request, response), ysBook); 
		model.addAttribute("page", page);
		return "modules/book/book/ysBookList";
	}
	@RequestMapping(value = "/shouye")
    @ResponseBody
    public DTO shouye(HttpServletRequest request,HttpServletResponse response) {
        DTO dto = new DTO();
        List<YsBook> listbook=Ys.selectAllHomePage();
                      
           dto.setCode("100");
           dto.setData(listbook);
           dto.setMsg("获取首页推荐书目信息成功");           
          return  dto;
    }

	/**
	 * 查看，增加，编辑推荐书目表单页面
	 */
	@RequiresPermissions(value={"book:book:ysBook:view","book:book:ysBook:add","book:book:ysBook:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsBook ysBook, Model model) {
		model.addAttribute("ysBook", ysBook);
		return "modules/book/book/ysBookForm";
	}

	/**
	 * 保存推荐书目
	 */
	@RequiresPermissions(value={"book:book:ysBook:add","book:book:ysBook:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsBook ysBook, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysBook)){
			return form(ysBook, model);
		}
		if(!ysBook.getIsNewRecord()){//编辑表单保存
			YsBook t = ysBookService.get(ysBook.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysBook, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysBookService.save(t);//保存
		}else{//新增表单保存
			ysBookService.save(ysBook);//保存
		}
		addMessage(redirectAttributes, "保存推荐书目成功");
		return "redirect:"+Global.getAdminPath()+"/book/book/ysBook/?repage";
	}
	
	/**
	 * 删除推荐书目
	 */
	@RequiresPermissions("book:book:ysBook:del")
	@RequestMapping(value = "delete")
	public String delete(YsBook ysBook, RedirectAttributes redirectAttributes) {
		ysBookService.delete(ysBook);
		addMessage(redirectAttributes, "删除推荐书目成功");
		return "redirect:"+Global.getAdminPath()+"/book/book/ysBook/?repage";
	}
	
	/**
	 * 批量删除推荐书目
	 */
	@RequiresPermissions("book:book:ysBook:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysBookService.delete(ysBookService.get(id));
		}
		addMessage(redirectAttributes, "删除推荐书目成功");
		return "redirect:"+Global.getAdminPath()+"/book/book/ysBook/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("book:book:ysBook:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsBook ysBook, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "推荐书目"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsBook> page = ysBookService.findPage(new Page<YsBook>(request, response, -1), ysBook);
    		new ExportExcel("推荐书目", YsBook.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出推荐书目记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/book/book/ysBook/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("book:book:ysBook:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsBook> list = ei.getDataList(YsBook.class);
			for (YsBook ysBook : list){
				try{
					ysBookService.save(ysBook);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条推荐书目记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条推荐书目记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入推荐书目失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/book/book/ysBook/?repage";
    }
	
	/**
	 * 下载导入推荐书目数据模板
	 */
	@RequiresPermissions("book:book:ysBook:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "推荐书目数据导入模板.xlsx";
    		List<YsBook> list = Lists.newArrayList(); 
    		new ExportExcel("推荐书目数据", YsBook.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/book/book/ysBook/?repage";
    }
	
	
	

}