/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.entity.YsCourse;
import com.jeeplus.modules.entity.YsDepartment;
import com.jeeplus.modules.entity.YsEvaluate;
import com.jeeplus.modules.entity.YsPaper;
import com.jeeplus.modules.entity.YsProject;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.entity.YsTest;
import com.jeeplus.modules.project.dao.YsDepartmentDao;
import com.jeeplus.modules.project.dao.YsPaperDao;
import com.jeeplus.modules.project.dao.YsPaperPlanDao;
import com.jeeplus.modules.project.dao.YsProjectDao;
import com.jeeplus.modules.project.dao.YsTestDao;
import com.jeeplus.modules.project.entity.YsCourseUserPojo;
import com.jeeplus.modules.project.entity.YsTestPojo;

/**
 * 项目管理Service
 * @author wdy
 * @version 2018-05-17
 */
@Service
@Transactional(readOnly = true)
public class YsProjectService extends CrudService<YsProjectDao, YsProject> {
	
	@Autowired
	private YsProjectDao ysProjectDao;
	
	@Autowired
	private YsTestDao ysTestDao;
	
	@Autowired
	private YsDepartmentDao ysDepartmentDao;
	
	@Autowired
	private YsPaperPlanDao ysPaperPlanDao;
	
	@Autowired
	private YsPaperDao ysPaperDao;

	public YsProject get(String id) {
		return super.get(id);
	}
	
	public List<YsProject> findList(YsProject ysProject) {
		return super.findList(ysProject);
	}
	
	public Page<YsProject> findPage(Page<YsProject> page, YsProject ysProject) {
		return super.findPage(page, ysProject);
	}
	
	@Transactional(readOnly = false)
	public void save(YsProject ysProject) {
		super.save(ysProject);
	}
	
	/**
	 * 删除项目
	 */
	@Transactional(readOnly = false)
	public void delete(YsProject ysProject) {
		super.delete(ysProject);
	}
	
	/**
	 * 获取项目评价信息
	 */
	@Transactional(readOnly = false)
	public YsEvaluate findeEvaluate(YsEvaluate ysEvaluate) {
		// 获取项目评分 (几颗星)
		List<YsEvaluate> EvaList = ysProjectDao.findEva(ysEvaluate);
		// 获取项目评价信息
		ysEvaluate = ysProjectDao.findEvaluate(ysEvaluate);
		int one = 0, two = 0, three = 0, four = 0, five = 0;
		for (int i = 0; i < EvaList.size(); i++) {
			if(EvaList.get(i).getEvaluate() == 1){
				one++;
			} else if(EvaList.get(i).getEvaluate() == 2){
				two++;
			} else if(EvaList.get(i).getEvaluate() == 3){
				three++;
			} else if(EvaList.get(i).getEvaluate() == 4){
				four++;
			} else if(EvaList.get(i).getEvaluate() == 5){
				five++;
			}
		}
		List<Integer> evaluateList = Arrays.asList(new Integer[]{one, two, three, four, five});
		ysEvaluate.setEvaluateList(evaluateList);
		return ysEvaluate;
	}

	/**
	 * 项目课件关联关系
	 */
/*	@Transactional(readOnly = false)
	public void saveCourse(Map<Object, Object> map) {
		ysProjectDao.saveCourse(map);
	}*/
	
	/**
	 * 保存并返回主键
	 */
/*	@Transactional(readOnly = false)
	public int saveYsPro(YsProject ysProject) {
		return ysProjectDao.saveYsPro(ysProject);
	}*/


	/**
	 * 项目学员关联关系
	 */
/*	@Transactional(readOnly = false)
	public void saveUser(Map<Object, Object> map) {
		ysProjectDao.saveUser(map);
	}*/

	/**
	 * 项目部门关联关系
	 */
	@Transactional(readOnly = false)
	public void saveDep(Map<Object, Object> map) {
		ysProjectDao.saveDep(map);
	}
	
	/**
	 * 根据proId查询课件
	 */
	@Transactional(readOnly = false)
	public Page<YsCourse> findPageCourseByProId(Page<YsCourse> page, YsCourse ysCourse) {
		ysCourse.setPage(page);
		page.setList(ysProjectDao.findPageCourseByProId(ysCourse));
		return page;
	}

	/**
	 * 删除课件与项目的关联关系 及 课件和学员的关联关系
	 */
	@Transactional(readOnly = false)
	public void delCourse(Map<Object, Object> map) {
		ysProjectDao.delCoursePro(map);
		ysProjectDao.delCourseUser(map);
	}

	/**
	 * 删除课件与项目的启禁用状态
	 */
	@Transactional(readOnly = false)
	public void updateCourseState(Map<Object, Object> map) {
		ysProjectDao.updateCourseState(map);
	}

	/**
	 * 根据项目id 课件id 查询用户的培训课件的信息
	 */
	@Transactional(readOnly = false)
	public Page<YsCourseUserPojo> findPageCourseUser(Page<YsCourseUserPojo> page, YsCourseUserPojo ysCourseUserPojo) {
		ysCourseUserPojo.setPage(page);
		page.setList(ysProjectDao.findPageCourseUser(ysCourseUserPojo));
		return page;
	}

	@Transactional(readOnly = false)
	public void delCourseUserById(Map<Object, Object> map) {
		ysProjectDao.delCourseUserById(map);
	}

	@Transactional(readOnly = false)
	public Page<YsTest> findPageYsTestByProId(Page<YsTest> page, YsTest ysTest) {
		ysTest.setPage(page);
		page.setList(ysProjectDao.findPageYsTestByProId(ysTest));
		List<YsTest> list = page.getList();
		for (YsTest ysTest2 : list) {
			List<YsDepartment> depList = ysTestDao.findYsTestDepById(ysTest2);
			String str = "";
			for (int i = 0; i < depList.size(); i++) {
				if(i == depList.size()-1){
					str += depList.get(i).getDepName();
				}else{
					str += depList.get(i).getDepName()+",";
				}
			}
			ysTest2.setDepNames(str);
		}
		return page;
	}

	// 删除考试
	@Transactional(readOnly = false)
	public void delTest(Map<Object, Object> map) {
		ysProjectDao.delTest(map);
	}

	// 课件和学员关联
/*	@Transactional(readOnly = false)
	public void saveCourseUser(Map<Object, Object> map) {
		ysProjectDao.saveCourseUser(map);
	}*/

	// 保存项目
	@Transactional(readOnly = false)
	public void saveYsProject(YsProject ysProject, Map<Object, Object> map, HttpSession session) {
		// 获取部门ids
		String[] depIds = ysProject.getDepIds().split(",");
		// 得到学员的id集合
		String[] users = ysProject.getUserIds().split(",");
		// 得到课件的id集合
		String[] courses = ysProject.getCourseIds().split(",");
		// 得到试卷的id集合
		String[] papers = ysProject.getTestIds().split(",");
		
		// 设置属性
		// 设置创建人?????????????
		ysProject.setCreateId(1);
		ysProject.setUpdateTime(new Date());
		ysProject.setCreateTime(new Date());
		ysProject.setStudyCount(users.length);
		ysProject.setCourseNumber(courses.length);
		// 设置创建人
		
		//保存
		ysProjectDao.saveYsPro(ysProject);
		String id = ysProject.getId();
		
		// 插入课件
		if(courses != null && courses.length != 0){
			// 插入课件  
			map.put("proId", id);
			map.put("courseIdList", Arrays.asList(courses));
			ysProjectDao.saveCourse(map);			
			// 保存课件与学员关系
			for (int i = 0; i < courses.length; i++) {
				map.put("couId", courses[i]);
				map.put("userIdList", Arrays.asList(users));
				ysProjectDao.saveCourseUser(map);
			}
		}
		
		// 插入部门
		map.put("depIdList", Arrays.asList(depIds));
		ysProjectDao.saveDep(map);
		
		// 插入学员 
		map.put("userIdList", Arrays.asList(users));
		ysProjectDao.saveUser(map);
		
		// 插入考试 
		ArrayList<YsTestPojo> ysTestPojoList = (ArrayList<YsTestPojo>) session.getAttribute("ysTestPojoList");
		YsTestPojo ysTestPojo = null;
		if(ysTestPojoList != null && ysTestPojoList.size() != 0){
			for (int i = 0; i < ysTestPojoList.size(); i++) {
				ysTestPojo = ysTestPojoList.get(i);
				for (int j = 0; j < papers.length; j++) {
					if(papers[j].equals(ysTestPojo.getId())){
						if(ysTestPojo.getPapId() != null && ysTestPojo.getPapId() != 0){
							// 保存考试 返回id
							YsTest ysTest = ysTestPojo.getYsTest();
							ysTest.setProId(Integer.parseInt(id));
							ysTest.setPapId(ysTestPojo.getPapId());
							ysTest.setNotEva(users.length);
							ysTestDao.saveYsTest(ysTest);
							// 插入考试部门关联表
							String depNames = ysTest.getDepNames();
							String[] depNameArr = depNames.split(",");
							YsDepartment ysDepartment = new YsDepartment();
							List<Integer> testDepIdList = new ArrayList<>();
							for (int k = 0; k < depNameArr.length; k++) {
								ysDepartment.setDepName(depNameArr[k]);
								ysDepartment = ysDepartmentDao.getDepIdByName(ysDepartment);
								testDepIdList.add(Integer.parseInt(ysDepartment.getId()));
							}
							map.put("testDepIdList", testDepIdList);
							map.put("testId", ysTest.getId());
							ysTestDao.saveYsTestDep(map);
							// 插入考试和学员关系表
							for (Integer testDepId : testDepIdList) {
								map.put("testDepId", testDepId);
								// 根据user id的集合 和 部门id 查询出这个部门包含的user 的 id集合
								List<Integer> testUserIdList = ysDepartmentDao.findUserIdsByDepIdAndUserIds(map);
								if(testUserIdList != null && testUserIdList.size() != 0){
									map.put("testUserIdList", testUserIdList);
									// 插入test 和 学员 的关联 
									ysTestDao.saveYsTestUser(map);
								}
							}
						}else if(ysTestPojo.getPlanId() != null && ysTestPojo.getPlanId() != 0){
							map.put("planId", ysTestPojo.getPlanId());
							YsPaper ysPaper = ysPaperPlanDao.findPaperByPlanId(map);
							// 保存考试 返回id
							YsTest ysTest = ysTestPojo.getYsTest();
							ysTest.setProId(Integer.parseInt(id));
							ysTest.setNotEva(users.length);
							ysTest.setPapId(Integer.parseInt(ysPaper.getId()));
							ysTestDao.saveYsTest(ysTest);
							// 插入考试部门关联表
							String depNames = ysTest.getDepNames();
							String[] depNameArr = depNames.split(",");
							YsDepartment ysDepartment = new YsDepartment();
							List<Integer> testDepIdList = new ArrayList<>();
							for (int k = 0; k < depNameArr.length; k++) {
								ysDepartment.setDepName(depNameArr[k]);
								ysDepartment = ysDepartmentDao.getDepIdByName(ysDepartment);
								testDepIdList.add(Integer.parseInt(ysDepartment.getId()));
							}
							map.put("testDepIdList", testDepIdList);
							map.put("testId", ysTest.getId());
							ysTestDao.saveYsTestDep(map);
							// 插入考试和学员关系表
							for (Integer testDepId : testDepIdList) {
								map.put("testDepId", testDepId);
								// 根据user id的集合 和 部门id 查询出这个部门包含的user 的 id集合
								List<Integer> testUserIdList = ysDepartmentDao.findUserIdsByDepIdAndUserIds(map);
								if(testUserIdList != null && testUserIdList.size() != 0){
									map.put("testUserIdList", testUserIdList);
									// 插入test 和 学员 的关联 
									ysTestDao.saveYsTestUser(map);
								}
							}
						}else{
							// 保存试卷
							YsPaper ysPaper = new YsPaper();
							ysPaper.setPapName(ysTestPojo.getYsTest().getTestName());
							if(StringUtils.isNotBlank(ysTestPojo.getQueIds())){
								ysPaper.setPapType(1);
							}else{
								ysPaper.setPapType(2);
							}
							// 设置创建人 ??????????
							ysPaper.setCreateId(1);
							ysPaper.setCreateTime(new Date());
							ysPaper.setUpdateTime(new Date());
							ysPaperDao.saveYsPaper(ysPaper);
							// 保存试题
							List<YsQuestions> ysQuestionsList = ysTestPojo.getYsQuestionsList();
							map.put("papId", ysPaper.getId());
							map.put("ysQuesionList", ysQuestionsList);
							ysPaperDao.saveQueToPap(map);
							// 保存考试
							YsTest ysTest = ysTestPojo.getYsTest();
							ysTest.setProId(Integer.parseInt(id));
							ysTest.setNotEva(users.length);
							ysTest.setPapId(Integer.parseInt(ysPaper.getId()));
							ysTestDao.saveYsTest(ysTest);
							// 插入考试部门关联表
							String depNames = ysTest.getDepNames();
							String[] depNameArr = depNames.split(",");
							YsDepartment ysDepartment = new YsDepartment();
							List<Integer> testDepIdList = new ArrayList<>();
							for (int k = 0; k < depNameArr.length; k++) {
								ysDepartment.setDepName(depNameArr[k]);
								ysDepartment = ysDepartmentDao.getDepIdByName(ysDepartment);
								testDepIdList.add(Integer.parseInt(ysDepartment.getId()));
							}
							map.put("testDepIdList", testDepIdList);
							map.put("testId", ysTest.getId());
							ysTestDao.saveYsTestDep(map);
							// 插入考试和学员关系表
							for (Integer testDepId : testDepIdList) {
								map.put("testDepId", testDepId);
								// 根据user id的集合 和 部门id 查询出这个部门包含的user 的 id集合
								List<Integer> testUserIdList = ysDepartmentDao.findUserIdsByDepIdAndUserIds(map);
								if(testUserIdList != null && testUserIdList.size() != 0){
									map.put("testUserIdList", testUserIdList);
									// 插入test 和 学员 的关联 
									ysTestDao.saveYsTestUser(map);
								}
							}
						}
					}
				}
			}
		}
	}
	
	
}