package com.jeeplus.modules.course.web.addcourse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.course.dao.addcourse.YsCourseVueDao;
import com.jeeplus.modules.course.entity.addcourse.YsCourseUser;
import com.jeeplus.modules.course.entity.addcourse.YsCourseVue;

/**
 * 课件管理Controller
 * @author mx
 * @version 2018-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/coursevue")
public class YsCourseVueController extends BaseController {
	@Autowired
	YsCourseVueDao ysCourseVueDao;
	@RequestMapping("/selectByPro")
	@ResponseBody
	public Map<String, Object> selectByPro(Integer page, Integer limit,Integer proid,
			String coursename,Integer state,String courseclass,
			  String coursetype,String starttime,String eTime,	
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		System.out.println(proid+"项目名称");
		if(page==0||page==null){
			jsonMap.put("code", 400);
			jsonMap.put("msg", "课程页数错误");
			return jsonMap;
		}else if(limit==0||limit==null){
			jsonMap.put("code", 400);
			jsonMap.put("msg", "课程显示条数错误");
			return jsonMap;
		}else if(proid==0||proid==null){
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目idid为空");
			return jsonMap;
		}else{
		int start = 0;
		start = (page - 1) * limit;
		
		List<YsCourseVue> listysvue=ysCourseVueDao.selectByPro(start, limit, proid,state,courseclass,coursetype,starttime,eTime,coursename);
		jsonMap.put("code", 200);
		jsonMap.put("msg", "获取课程成功");
		jsonMap.put("count", ysCourseVueDao.selectByProCount(proid,  state, courseclass, coursetype, starttime, eTime,coursename));
		jsonMap.put("data", listysvue);	
		return jsonMap;
		}
	}
	@RequestMapping("/selectByUser")
	@ResponseBody
	public Map<String, Object> selectByPro(Integer page, Integer limit,Integer proid,
			Integer couid,String username,String depId,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(page==0||page==null){
			jsonMap.put("code", 400);
			jsonMap.put("msg", "课程页数错误");
			return jsonMap;
		}else if(limit==0||limit==null){
			jsonMap.put("code", 400);
			jsonMap.put("msg", "课程显示条数错误");
			return jsonMap;
		}else{
			
		
		int start = 0;
		start = (page - 1) * limit;
		List<YsCourseUser> listyscour= ysCourseVueDao.selectByProCou(start, limit, proid, couid,depId,username);
		jsonMap.put("code", 200);
		jsonMap.put("msg", "获取课程成功");
		jsonMap.put("count", ysCourseVueDao.selectByUserCount(proid, couid,depId,username));
		jsonMap.put("data", listyscour);	
		return jsonMap;
		}
	}
}
