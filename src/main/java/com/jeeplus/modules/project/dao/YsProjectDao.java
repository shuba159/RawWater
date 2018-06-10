/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsCourse;
import com.jeeplus.modules.entity.YsEvaluate;
import com.jeeplus.modules.entity.YsProject;
import com.jeeplus.modules.entity.YsTest;
import com.jeeplus.modules.project.entity.YsCourseUserPojo;

/**
 * 项目管理DAO接口
 * @author wdy
 * @version 2018-05-17
 */
@MyBatisDao
public interface YsProjectDao extends CrudDao<YsProject> {

	YsEvaluate findEvaluate(YsEvaluate ysEvaluate);

	List<YsEvaluate> findEva(YsEvaluate ysEvaluate);

	void saveCourse(Map<Object, Object> map);

	int saveYsPro(YsProject ysProject);

	void saveUser(Map<Object, Object> map);

	void saveDep(Map<Object, Object> map);

	List<YsCourse> findPageCourseByProId(YsCourse ysCourse);

	void delCoursePro(Map<Object, Object> map);

	void delCourseUser(Map<Object, Object> map);

	void updateCourseState(Map<Object, Object> map);

	List<YsCourseUserPojo> findPageCourseUser(YsCourseUserPojo ysCourseUserPojo);

	void delCourseUserById(Map<Object, Object> map);

	List<YsTest> findPageYsTestByProId(YsTest ysTest);

	void delTest(Map<Object, Object> map);

	void saveCourseUser(Map<Object, Object> map);

	
}