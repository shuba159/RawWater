package com.jeeplus.modules.project.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.clickgood.dao.YsClickGoodDao;
import com.jeeplus.modules.clickgood.entity.YsClickGood;
import com.jeeplus.modules.course.dao.addcourse.YsCourseDao;
import com.jeeplus.modules.course.service.addcourse.YsCourseService;
import com.jeeplus.modules.entity.YsCourse;
import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.entity.YsProject;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;
import com.jeeplus.modules.project.dao.YsProjectVueDao;
import com.jeeplus.modules.project.entity.YsPaPerScoreVue;
import com.jeeplus.modules.project.entity.YsProPaperVue;
import com.jeeplus.modules.project.entity.YsTestPojo;
import com.jeeplus.modules.project.entity.YsyProclassVue;
import com.jeeplus.modules.project.service.YsPaperPlanService;
import com.jeeplus.modules.project.service.YsPaperService;
import com.jeeplus.modules.project.service.YsProjectService;
import com.jeeplus.modules.project.utils.ProjectMothed;
import com.jeeplus.modules.questions.service.questions_as.YsQuestionsService;

/**
 * 前台项目管理Controller
 * 
 * @author wdy
 * @version 2018-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/projectvue")
public class YsProjectVueController extends BaseController {
	@Autowired
	YsProjectVueDao ysProjectVueDao;
	@Autowired
	private YsCourseService ysCourseService;
	@Autowired
	private YsCourseDao ysCourseDao;
	@Autowired
	private YsMajorDao ysMajorDao;
	@Autowired
	private YsProjectService ysProjectService;
	@Autowired
	private YsPaperPlanService ysPaperPlanService;
	@Autowired
	private YsQuestionsService ysQuestionsService;
	@Autowired
	private YsPaperService ysPaperService;
	/**
	 * 查看项目管理页
	 * 
	 * @param page
	 * @param limit
	 * @param createId
	 * @param depId
	 * @param proName
	 * @param response
	 * @param request
	 * @param create_time
	 * @param eTime
	 * @param proClassName
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findAllByRole")
	@ResponseBody
	public Map<String, Object> findAllByRole(Integer page, Integer limit, String createId, String depId, String proName,
			HttpServletResponse response, HttpServletRequest request, String create_time, String eTime,
			String proClassName, HttpSession session) throws IOException {
		System.out.println("项目名称" + proName);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (page == 0 || page == null) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目管理页数错误");
			return jsonMap;
		} else if (limit == 0 || limit == null) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目管理显示条数错误");
			return jsonMap;
		} else if (StringUtils.isBlank(createId)) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "用户id为空");
			return jsonMap;
		} else {
			// int couid=24;
			int start = 0;
			start = (page - 1) * limit;
			List<YsProject> listYsCourses = ysProjectVueDao.findAllByRole(start, limit, createId, depId, proName,
					create_time, eTime, proClassName);

			jsonMap.put("code", 200);
			jsonMap.put("msg", "获取项目管理成功");
			jsonMap.put("count",
					ysProjectVueDao.findAllByRoleCount(createId, depId, proName, create_time, eTime, proClassName));
			jsonMap.put("data", listYsCourses);
			return jsonMap;

		}
	}

	/**
	 * 项目管理-查看试卷
	 * 
	 * @param page
	 * @param limit
	 * @param response
	 * @param request
	 * @param proid
	 * @param testName
	 * @param depId
	 * @param testNumber
	 * @param startTime
	 * @param eTime
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/selectByProPaper")
	@ResponseBody
	public Map<String, Object> selectByProPaper(Integer page, Integer limit, HttpServletResponse response,
			HttpServletRequest request, String proid, String testName, String depId, String testNumber,
			String startTime, String eTime, HttpSession session) throws IOException {
		System.out.println("项目名称" + proid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (page == 0 || page == null) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目管理页数错误");
			return jsonMap;
		} else if (limit == 0 || limit == null) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目管理显示条数错误");
			return jsonMap;
		} else if (StringUtils.isBlank(proid)) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "用户id为空");
			return jsonMap;
		} else {
			// int couid=24;
			int start = 0;
			start = (page - 1) * limit;
			List<YsProPaperVue> listYsCourses = ysProjectVueDao.selectByProPaper(start, limit, proid, testName, depId,
					testNumber, startTime, eTime);

			jsonMap.put("code", 200);
			jsonMap.put("msg", "获取项目管理成功");
			jsonMap.put("count",
					ysProjectVueDao.selectByProPaperCount(proid, testName, depId, testNumber, startTime, eTime));
			jsonMap.put("data", listYsCourses);
			return jsonMap;

		}
	}

	/**
	 * 根据试卷查看学生分数
	 * 
	 * @param page
	 * @param limit
	 * @param response
	 * @param request
	 * @param papId
	 * @param depId
	 * @param ispath
	 * @param xingming
	 * @param ordeykey
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findByPapScore")
	@ResponseBody
	public Map<String, Object> findByPapScore(Integer page, Integer limit, HttpServletResponse response,
			HttpServletRequest request, String papId, String depId, String ispath, String xingming, String ordeykey,
			HttpSession session) throws IOException {
		System.out.println("项目名称" + papId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (page == 0 || page == null) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目管理页数错误");
			return jsonMap;
		} else if (limit == 0 || limit == null) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "项目管理显示条数错误");
			return jsonMap;
		} else if (StringUtils.isBlank(papId)) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "用户id为空");
			return jsonMap;
		} else {
			// int couid=24;
			int start = 0;
			start = (page - 1) * limit;
			List<YsPaPerScoreVue> listYsCourses = ysProjectVueDao.findByPapScore(start, limit, papId, depId, ispath,
					xingming, ordeykey);

			jsonMap.put("code", 200);
			jsonMap.put("msg", "获取项目管理成功");
			jsonMap.put("count", ysProjectVueDao.findByPapScoreCount(papId, depId, ispath, xingming, ordeykey));
			jsonMap.put("data", listYsCourses);
			return jsonMap;

		}
	}

	/**
	 * 删除项目
	 * 
	 * @param page
	 * @param limit
	 * @param createId
	 * @param depId
	 * @param proName
	 * @param response
	 * @param request
	 * @param create_time
	 * @param eTime
	 * @param proClassName
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteByid")
	@ResponseBody
	public Map<String, Object> deleteByid(HttpServletResponse response, HttpServletRequest request, String proid,
			String proids, HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// proids="1,2,3";
		if (proid != null) {
			int a = ysProjectVueDao.deleteByid(proid);
			if (a > 0) {
				jsonMap.put("code", 200);
				jsonMap.put("msg", "删除单个项目成功");
				return jsonMap;
			}
		} else if (proids != null) {
			String[] array = proids.split(",");
			System.out.println("字符串转化为数组：");
			for (int i = 0; i < array.length; i++) {
				ysProjectVueDao.deleteByid(array[i]);
				System.out.print(array[i] + "  ");
			}
			jsonMap.put("code", 200);
			jsonMap.put("msg", "删除多个项目成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "删除失败");
		return jsonMap;
	}

	/**
	 * 删除课程
	 * 
	 * @param page
	 * @param limit
	 * @param createId
	 * @param depId
	 * @param proName
	 * @param response
	 * @param request
	 * @param create_time
	 * @param eTime
	 * @param proClassName
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteProCouseByid")
	@ResponseBody
	public Map<String, Object> deleteProCouseByid(HttpServletResponse response, HttpServletRequest request,
			String courid, String courids, HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// proids="1,2,3";
		if (courid != null) {
			int a = ysProjectVueDao.deleteProCouseByid(courid);
			if (a > 0) {
				jsonMap.put("code", 200);
				jsonMap.put("msg", "删除单个课程成功");
				return jsonMap;
			}
		} else if (courids != null) {
			String[] array = courids.split(",");
			System.out.println("字符串转化为数组：");
			for (int i = 0; i < array.length; i++) {
				// ysProjectVueDao.deleteByid(array[i]);
				ysProjectVueDao.deleteProCouseByid(array[i]);
				System.out.print(array[i] + "  ");
			}
			jsonMap.put("code", 200);
			jsonMap.put("msg", "删除多个课程成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "删除失败");
		return jsonMap;
	}

	/**
	 * 删除课程-删除分数
	 * 
	 * @param page
	 * @param limit
	 * @param createId
	 * @param depId
	 * @param proName
	 * @param response
	 * @param request
	 * @param create_time
	 * @param eTime
	 * @param proClassName
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteProCouseStuByid")
	@ResponseBody
	public Map<String, Object> deleteProCouseStuByid(HttpServletResponse response, HttpServletRequest request,
			String userid, String userids, HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// proids="1,2,3";
		if (userid != null) {
			int a = ysProjectVueDao.deleteProCouseStuByid(userid);
			if (a > 0) {
				jsonMap.put("code", 200);
				jsonMap.put("msg", "删除单个课程成功");
				return jsonMap;
			}
		} else if (userids != null) {
			String[] array = userids.split(",");
			System.out.println("字符串转化为数组：");
			for (int i = 0; i < array.length; i++) {
				// ysProjectVueDao.deleteByid(array[i]);
				ysProjectVueDao.deleteProCouseStuByid(array[i]);
				System.out.print(array[i] + "  ");
			}
			jsonMap.put("code", 200);
			jsonMap.put("msg", "删除多个课程成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "删除失败");
		return jsonMap;
	}

	/**
	 * 删除课程-删除分数
	 * 
	 * @param page
	 * @param limit
	 * @param createId
	 * @param depId
	 * @param proName
	 * @param response
	 * @param request
	 * @param create_time
	 * @param eTime
	 * @param proClassName
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteProTestByid")
	@ResponseBody
	public Map<String, Object> deleteProTestByid(HttpServletResponse response, HttpServletRequest request,
			String testid, String testids, HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// proids="1,2,3";
		if (testid != null) {
			int a = ysProjectVueDao.deleteProTestByid(testid);
			if (a > 0) {
				jsonMap.put("code", 200);
				jsonMap.put("msg", "删除单个考试成功");
				return jsonMap;
			}
		} else if (testids != null) {
			String[] array = testids.split(",");
			System.out.println("字符串转化为数组：");
			for (int i = 0; i < array.length; i++) {
				// ysProjectVueDao.deleteByid(array[i]);
				ysProjectVueDao.deleteProTestByid(array[i]);
				System.out.print(array[i] + "  ");
			}
			jsonMap.put("code", 200);
			jsonMap.put("msg", "删除多个考试成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "删除失败");
		return jsonMap;
	}

	/**
	 * 删除课程-删除分数
	 * 
	 * @param page
	 * @param limit
	 * @param createId
	 * @param depId
	 * @param proName
	 * @param response
	 * @param request
	 * @param create_time
	 * @param eTime
	 * @param proClassName
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteProTestScoreByid")
	@ResponseBody
	public Map<String, Object> deleteProTestScoreByid(HttpServletResponse response, HttpServletRequest request,
			String testscore, String testscores, HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// proids="1,2,3";
		if (testscore != null) {
			int a = ysProjectVueDao.deleteProTestScoreByid(testscore);
			if (a > 0) {
				jsonMap.put("code", 200);
				jsonMap.put("msg", "删除单个考试成功");
				return jsonMap;
			}
		} else if (testscores != null) {
			String[] array = testscores.split(",");
			System.out.println("字符串转化为数组：");
			for (int i = 0; i < array.length; i++) {
				// ysProjectVueDao.deleteByid(array[i]);
				ysProjectVueDao.deleteProTestScoreByid(array[i]);
				System.out.print(array[i] + "  ");
			}
			jsonMap.put("code", 200);
			jsonMap.put("msg", "删除多个考试成功");
			return jsonMap;
		}
		jsonMap.put("code", 400);
		jsonMap.put("msg", "删除失败");
		return jsonMap;
	}

	@RequestMapping("/findProFen")
	@ResponseBody
	public Map<String, Object> findProFen(HttpServletResponse response, HttpServletRequest request, String testscore,
			String testscores, HttpSession session) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
         List<YsyProclassVue> listproclass=ysProjectVueDao.findProFen();
		jsonMap.put("code", 200);
		jsonMap.put("msg", "获取项目分类成功");
		jsonMap.put("data", listproclass);
		return jsonMap;
	}
	/**
	 * 获取添加项目培训课件
	 * @param ysCourse
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("findAllCou")
	@ResponseBody
	public Map<String, Object> findAllCou(YsCourse ysCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//List<YsMajor> list=ysMajorDao.YsMajorSelectAll();  // 查询专业类型里面的值
		
		Page<YsCourse> page1 = ysCourseService.findPage(new Page<YsCourse>(request, response), ysCourse); 
		List<YsCourse> page = page1.getList();
		//page1.getCount();
		jsonMap.put("code", 200);
		jsonMap.put("msg", "获取培训课件成功");
		jsonMap.put("count", page1.getCount());
		jsonMap.put("data", page);
		return jsonMap;
	}
	/**
	 *  添加项目- 选择试题
	 * @param ysCourse
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("findAllQuesttions")
	@ResponseBody
	public Map<String, Object> findAllQuesttions(YsCourse ysCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//List<YsMajor> list=ysMajorDao.YsMajorSelectAll();  // 查询专业类型里面的值
		
		Page<YsCourse> page1 = ysCourseService.findPage(new Page<YsCourse>(request, response), ysCourse); 
		List<YsCourse> page =page1.getList();
		//page1.getCount();
		jsonMap.put("code", 200);
		jsonMap.put("msg", "获取题库");
		jsonMap.put("count", page1.getCount());
		jsonMap.put("data", page);
		return jsonMap;
	}
	/**
	 * 随机出题
	 */
	@RequestMapping("quesionRandom")
	@ResponseBody
	public Map<String, Object> quesionRandom(YsQuestions ysQuestions, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int totalNum = ysQuestionsService.findTotalNum();
		request.setAttribute("totalNum", totalNum);
	//	return "modules/project/ysQueRandom";
		jsonMap.put("code", 200);
		jsonMap.put("msg", "获取题库");
		jsonMap.put("data", totalNum);
		return jsonMap;
	}
	/*@RequestMapping("AddProJectVue")
	@ResponseBody
	public Map<String, Object> list(YsCourse ysCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
	 return jsonMap;
	}*/
	/**
	 * 保存项目管理
	 */
	@RequestMapping("AddProJectVue")
	@ResponseBody
	public  Map<String, Object> save(YsProject ysProject, Model model, RedirectAttributes redirectAttributes, Map<Object, Object> map, HttpSession session) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			/*if (!beanValidator(model, ysProject)){
				return form(ysProject, model);
			}*/
			if(!ysProject.getIsNewRecord()){//编辑表单保存
				YsProject t = ysProjectService.get(ysProject.getId());//从数据库取出记录的值
				MyBeanUtils.copyBeanNotNull2Bean(ysProject, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
				ysProjectService.save(t);//保存
			}else{//新增表单保存
				ysProjectService.saveYsProject(ysProject, map, session);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//addMessage(redirectAttributes, "保存项目管理失败");
			jsonMap.put("code", 400);
			jsonMap.put("msg", "保存项目管理失败");
			return  jsonMap;
		}
		
		//addMessage(redirectAttributes, "保存项目管理成功");
		//return "redirect:"+Global.getAdminPath()+"/project/ysProject/form";
		jsonMap.put("code", 200);
		jsonMap.put("msg", "保存项目管理成功");
		return  jsonMap;
	}
	/**
	 * 临时保存
	 */
	@RequestMapping("testSave1")
	@ResponseBody
	public Map<String, Object>  testSave(YsTestPojo ysTestPojo, Model model, RedirectAttributes redirectAttributes, HttpSession session, Map<Object, Object> map) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Integer sessionTestId = (Integer) session.getAttribute("sessionTestId");
		if(sessionTestId == null || sessionTestId == 0){
			sessionTestId = 1;
		}
		sessionTestId += 1;
		session.setAttribute("sessionTestId", sessionTestId);
		
		Integer papId = ysTestPojo.getPapId();
		Integer planId = ysTestPojo.getPlanId();
		String queIds = ysTestPojo.getQueIds();
		String ranIds = ysTestPojo.getRanIds();
		if(papId != null && papId != 0){
			map.put("papId", papId);
			List<YsQuestions> findQuesionList = ysPaperService.findQuesionList(map);
			ysTestPojo.setYsQuestionsList(findQuesionList);
		}else if(planId != null && planId != 0){
			map.put("planId", planId);
			List<YsQuestions> findQuesionList = ysPaperPlanService.findQuesionByPlanId(map);
			ysTestPojo.setYsQuestionsList(findQuesionList);
		}else{
			List<YsQuestions> ysQuestionsList = new ArrayList<>();
			if(StringUtils.isNotBlank(queIds)){
				String[] split = queIds.split(",");
				for (int i = 0; i < split.length; i++) {
					String[] split2 = split[i].split(":");
					YsQuestions ysQuestions = ysQuestionsService.get(split2[0]);
					ysQuestions.setQueScore(Integer.parseInt(split2[1]));
					ysQuestionsList.add(ysQuestions);
				}
			}
			if(StringUtils.isNotBlank(ranIds)){
				String[] split = ranIds.split(",");
				for (int i = 0; i < split.length; i++) {
					String[] split2 = split[i].split(":");
					// 根据条件随机抽题 
					map.put("questionType", split2[1]);
					map.put("level", split2[0]);
					List<YsQuestions> findQuesionByRandom = ysPaperPlanService.findQuesionByRandom(map);
					int[] randomCommon = ProjectMothed.randomCommon(0, findQuesionByRandom.size()-1, Integer.parseInt(split2[2]));
					for (int j = 0; j < randomCommon.length; j++) {
						YsQuestions ysQuestions = findQuesionByRandom.get(randomCommon[j]);
						ysQuestions.setQueScore(Integer.parseInt(split2[3]));
						ysQuestionsList.add(ysQuestions);
					}
				}
			}
			ysTestPojo.setYsQuestionsList(ysQuestionsList);
		}
		
		// 存入session中
		ArrayList<YsTestPojo> ysTestPojoList = (ArrayList<YsTestPojo>) session.getAttribute("ysTestPojoList");
		if(ysTestPojoList == null || ysTestPojoList.size() == 0){
			ArrayList<YsTestPojo> ysTestPojoList1 = new ArrayList<>();
			ysTestPojoList1.add(ysTestPojo);
			session.setAttribute("ysTestPojoList", ysTestPojoList1);
		}else{
			ysTestPojoList.add(ysTestPojo);
			session.setAttribute("ysTestPojoList", ysTestPojoList);
		}
		//addMessage(redirectAttributes, "考试已添加");jsonMap.put("code", 200);
		jsonMap.put("code", 200);
		jsonMap.put("msg", "考试已添加");
		return  jsonMap;
		
		//return "modules/project/ysTestForm";
	}
	
	

}
