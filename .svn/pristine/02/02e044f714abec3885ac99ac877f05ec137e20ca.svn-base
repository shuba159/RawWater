/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.clickgood.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jeeplus.modules.clickgood.dao.YsClickGoodDao;
import com.jeeplus.modules.clickgood.entity.YsClickGood;
import com.jeeplus.modules.clickgood.service.YsClickGoodService;
import com.jeeplus.modules.course.dao.addcourse.YsCourseDao;

/**
 * 点赞收藏Controller
 * 
 * @author mx
 * @version 2018-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/clickgood/ysClickGood")
public class YsClickGoodController extends BaseController {

	@Autowired
	private YsClickGoodService ysClickGoodService;
	@Autowired
	YsClickGoodDao ysClickGoodDao;
	@Autowired
	YsCourseDao ysCourseDao;
	@ModelAttribute
	public YsClickGood get(@RequestParam(required = false) String id) {
		YsClickGood entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ysClickGoodService.get(id);
		}
		if (entity == null) {
			entity = new YsClickGood();
		}
		return entity;
	}

	@RequestMapping(value = "UpdateClieckGood")
	@ResponseBody
	public Map<String, Object> UpdateClieckGood(String userid, String courid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (userid == null || userid == "" || courid == null || courid == "") {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "点赞失败,用户id或者课件id为空");
			return jsonMap;
		}
		YsClickGood a = ysClickGoodDao.findclieckByUser(courid, userid);
		System.out.println(userid + "课件" + courid + "表");
		if (a != null) {
			Integer cleck = a.getGoodState();
			if (cleck != null) {
				// cleck=0;

				boolean b = cleck.equals(1);
				// boolean q =cleck.equals("1");
				// System.out.println("收藏状态"+cleck+b+q);
				if (b == true) {
					a.setGoodState(0);
					Integer avg=ysClickGoodDao.findclieckByUserByCouid(courid);
                        System.out.println("取消点赞+点赞数"+avg);
                     
                        	avg=avg-1;
                        	 ysCourseDao.updateCleckGood(avg, courid);
                        	 ysClickGoodDao.update(a);

                        
                      

					
				} else {
					Integer avg=ysClickGoodDao.findclieckByUserByCouid(courid);
                 //   System.out.println("点赞数"+avg);
                 
                    	avg=avg+1;
                    	 ysCourseDao.updateCleckGood(avg, courid);
					a.setGoodState(1);
				//	int avg=ysClickGoodDao.findclieckByUserByCouid(courid);
                    System.out.println("点赞数"+avg);
					ysClickGoodDao.update(a);

				}
				jsonMap.put("code", 200);
				jsonMap.put("msg", "点赞成功");
				return jsonMap;
			} else {
				cleck = 0;
				a.setGoodState(1);
				Integer avg=ysClickGoodDao.findclieckByUserByCouid(courid);
                //   System.out.println("点赞数"+avg);
                
                   	avg=avg+1;
                   	 ysCourseDao.updateCleckGood(avg, courid);
				ysClickGoodDao.update(a);
				jsonMap.put("code", 200);
				jsonMap.put("msg", "点赞成功");
				return jsonMap;
			}
		}
		// User
		YsClickGood b = new YsClickGood();
		b.setUserid(userid);
		b.setGoodState(1);
		
		int avg=ysClickGoodDao.findclieckByUserByCouid(courid);
		
		System.out.println("点赞数"+avg);
        
           	avg=avg+1;
           	 ysCourseDao.updateCleckGood(avg, courid);
		int d = Integer.parseInt(courid);
		b.setCouId(d);
		int c = ysClickGoodDao.insertclieckByUser(b);
		if (c > 0) {
			jsonMap.put("code", 200);
			jsonMap.put("msg", "点赞成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "点赞失败");
		return jsonMap;
	}

	@RequestMapping(value = "UpdateSignState")
	@ResponseBody
	public Map<String, Object> UpdateSignState(String userid, String courid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (userid == null || userid == "" || courid == null || courid == "") {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "收藏失败,用户id或者课件id为空");
			return jsonMap;
		}
		YsClickGood a = ysClickGoodDao.findclieckByUser(courid, userid);
		System.out.println(userid + "课件" + courid + "表");
		if (a != null) {
			Integer cleck = a.getSignState();
			if (cleck != null) {
				// cleck=0;

				boolean b = cleck.equals(1);
				boolean q = cleck.equals("1");
				System.out.println("收藏状态" + cleck + b + q);
				if (b == true) {
					a.setSignState(0);

					ysClickGoodDao.update(a);

				} else {
					a.setSignState(1);
					;
					ysClickGoodDao.update(a);

				}
				jsonMap.put("code", 200);
				jsonMap.put("msg", "收藏成功");
				return jsonMap;
			} else {
				cleck = 0;
				a.setSignState(1);
				;
				ysClickGoodDao.update(a);
				jsonMap.put("code", 200);
				jsonMap.put("msg", "收藏成功");
				return jsonMap;
			}
		}
		// User
		YsClickGood b = new YsClickGood();
		b.setUserid(userid);
		b.setSignState(1);
		;
		int d = Integer.parseInt(courid);
		b.setCouId(d);
		int c = ysClickGoodDao.insertclieckByUser(b);
		if (c > 0) {
			jsonMap.put("code", 200);
			jsonMap.put("msg", "收藏成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "收藏失败");
		return jsonMap;
	}

	/**
	 * 点赞收藏列表页面
	 */
	@RequiresPermissions("clickgood:ysClickGood:list")
	@RequestMapping(value = { "list", "" })
	public String list(YsClickGood ysClickGood, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsClickGood> page = ysClickGoodService.findPage(new Page<YsClickGood>(request, response), ysClickGood);
		model.addAttribute("page", page);
		return "modules/clickgood/ysClickGoodList";
	}

	/**
	 * 查看，增加，编辑点赞收藏表单页面
	 */
	@RequiresPermissions(value = { "clickgood:ysClickGood:view", "clickgood:ysClickGood:add",
			"clickgood:ysClickGood:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsClickGood ysClickGood, Model model) {
		model.addAttribute("ysClickGood", ysClickGood);
		return "modules/clickgood/ysClickGoodForm";
	}

	/**
	 * 保存点赞收藏
	 */
	@RequiresPermissions(value = { "clickgood:ysClickGood:add", "clickgood:ysClickGood:edit" }, logical = Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsClickGood ysClickGood, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, ysClickGood)) {
			return form(ysClickGood, model);
		}
		if (!ysClickGood.getIsNewRecord()) {// 编辑表单保存
			YsClickGood t = ysClickGoodService.get(ysClickGood.getId());// 从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysClickGood, t);// 将编辑表单中的非NULL值覆盖数据库记录中的值
			ysClickGoodService.save(t);// 保存
		} else {// 新增表单保存
			ysClickGoodService.save(ysClickGood);// 保存
		}
		addMessage(redirectAttributes, "保存点赞收藏成功");
		return "redirect:" + Global.getAdminPath() + "/clickgood/ysClickGood/?repage";
	}

	/**
	 * 删除点赞收藏
	 */
	@RequiresPermissions("clickgood:ysClickGood:del")
	@RequestMapping(value = "delete")
	public String delete(YsClickGood ysClickGood, RedirectAttributes redirectAttributes) {
		ysClickGoodService.delete(ysClickGood);
		addMessage(redirectAttributes, "删除点赞收藏成功");
		return "redirect:" + Global.getAdminPath() + "/clickgood/ysClickGood/?repage";
	}

	/**
	 * 批量删除点赞收藏
	 */
	@RequiresPermissions("clickgood:ysClickGood:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] = ids.split(",");
		for (String id : idArray) {
			ysClickGoodService.delete(ysClickGoodService.get(id));
		}
		addMessage(redirectAttributes, "删除点赞收藏成功");
		return "redirect:" + Global.getAdminPath() + "/clickgood/ysClickGood/?repage";
	}

	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("clickgood:ysClickGood:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(YsClickGood ysClickGood, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "点赞收藏" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<YsClickGood> page = ysClickGoodService.findPage(new Page<YsClickGood>(request, response, -1),
					ysClickGood);
			new ExportExcel("点赞收藏", YsClickGood.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出点赞收藏记录失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/clickgood/ysClickGood/?repage";
	}

	/**
	 * 导入Excel数据
	 * 
	 */
	@RequiresPermissions("clickgood:ysClickGood:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsClickGood> list = ei.getDataList(YsClickGood.class);
			for (YsClickGood ysClickGood : list) {
				try {
					ysClickGoodService.save(ysClickGood);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureNum++;
				} catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条点赞收藏记录。");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条点赞收藏记录" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入点赞收藏失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/clickgood/ysClickGood/?repage";
	}

	/**
	 * 下载导入点赞收藏数据模板
	 */
	@RequiresPermissions("clickgood:ysClickGood:import")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "点赞收藏数据导入模板.xlsx";
			List<YsClickGood> list = Lists.newArrayList();
			new ExportExcel("点赞收藏数据", YsClickGood.class, 1).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/clickgood/ysClickGood/?repage";
	}

}