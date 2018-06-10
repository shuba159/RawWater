package com.jeeplus.modules.project.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.modules.course.dao.addcourse.YsCourseDao;
import com.jeeplus.modules.entity.YsDepartment;
import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.entity.YsProClass;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.entity.YsUser;
import com.jeeplus.modules.forum.dao.YsForumDao;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;
import com.jeeplus.modules.project.dao.YsDepartmentDao;
import com.jeeplus.modules.project.dao.YsProClassDao;
import com.jeeplus.modules.project.dao.YsTestScoreDao;
import com.jeeplus.modules.project.dao.YsUserDao;
import com.jeeplus.modules.project.dao.YsUserPapAnswerDao;
import com.jeeplus.modules.questions.dao.questions_as.YsQuestionsDao;

public class FnsUtils {
	
	private static YsProClassDao ysProClassDao = SpringContextHolder.getBean(YsProClassDao.class);
	private static YsDepartmentDao ysDepartmentDao = SpringContextHolder.getBean(YsDepartmentDao.class);
	private static YsUserDao ysUserDao = SpringContextHolder.getBean(YsUserDao.class);
	private static YsCourseDao ysCourseDao = SpringContextHolder.getBean(YsCourseDao.class);
	private static YsQuestionsDao ysQuestionsDao = SpringContextHolder.getBean(YsQuestionsDao.class);
	private static YsMajorDao ysMajorDao = SpringContextHolder.getBean(YsMajorDao.class);
	private static YsTestScoreDao ysTestScoreDao = SpringContextHolder.getBean(YsTestScoreDao.class);
	private static YsUserPapAnswerDao ysUserPapAnswerDao = SpringContextHolder.getBean(YsUserPapAnswerDao.class);
	private static YsForumDao ysForumDao = SpringContextHolder.getBean(YsForumDao.class);
	/*
	 * 根据项目id获取项目分类
	 */
	public static String getYsProClass(String value){
		YsProClass ysProClass = new YsProClass(value);
		if (StringUtils.isNotBlank(value)){			
			ysProClass = ysProClassDao.findName(ysProClass);
		}
		return ysProClass.getClassName();
	}
	
	/*
	 * 获取项目分类列表
	 */
	public static List<YsProClass> getYsProClassList(){
		YsProClass ysProClass = new YsProClass();
		List<YsProClass> findList = ysProClassDao.findList(ysProClass);
		return findList;
	}

	/*
	 * 获取部门列表
	 */
	public static List<YsDepartment> getYsDepartmentList(){
		YsDepartment ysDepartment = new YsDepartment();
		List<YsDepartment> findList = ysDepartmentDao.findList(ysDepartment);
		return findList;
	}
	
	/*
	 * 根据部门id获取用户列表
	 */
	public static List<YsUser> getYsUserListByDepId(Integer depId){
		YsUser ysUser = new YsUser();
		ysUser.setDepId(depId);
		List<YsUser> findList = ysUserDao.findList(ysUser);
		return findList;
	}
	/*
	 * 根据ys_course.major_type和ys_course.Id获取ys_major.major_name
	 * a:ys_course.major_type
	 * b:ys_course.Id
	 */
	public static String getMajorTypeSelect(Integer a,Integer b){
		return ysCourseDao.majorTypeSelect(a,b);
	}
	
	/*
	 * 根据ys_questions.major_type和ys_questions.Id获取ys_major.major_name
	 * a:ys_questions.major_type
	 * b:ys_questions.Id
	 */
	public static String getMajorTypeSelectQues(Integer a,Integer b){
		return ysQuestionsDao.majorTypeSelectQues(a,b);
	}
	/*
	 * 获取课件专业类型
	 */
	public static List<YsMajor> getYsMajorList(){
		YsMajor ysMajor = new YsMajor();
		List<YsMajor> findList = ysMajorDao.findList(ysMajor);
		return findList;
	}
	/*
	 * 根据id获取部门
	 */
	public static String getDepNameSelect(Integer id){
		return ysTestScoreDao.depNameSelect(id);
	}
	/*
	 * 根据ys_test_score.pap_id 和 ys_test_score.Id 获取 ys_test.test_name
	 */
	public static String getTestNameSelectScoIdPapId(Integer papid,Integer scoreid){
		return ysTestScoreDao.testNameSelectScoIdPapId(papid,scoreid);
	}
	/* 
	 * 根据用户Id获取用户姓名
	 */
	public static String getIdSelectUserName(Integer id){
		return ysTestScoreDao.idSelectUserName(id);
	}
	/*
	 * 根据id获取题库的题的信息
	 */
	public static YsQuestions getIdSelectYsQuestions(String id){
		return ysUserPapAnswerDao.idSelectYsQuestions(Integer.parseInt(id));
	}
	/*
	 * 获取课件专业类型
	 */
	public static String getYsMajor(Integer id){
		YsMajor ysMajor = ysMajorDao.get(String.valueOf(id));
		return ysMajor.getMajorName();
	}
	
	// 根据id获取用户
	public static String getUserByUserId(Integer id){
		YsUser ysUser = ysUserDao.get(String.valueOf(id));
		return ysUser.getFullName();
	}
	/*
	 * 获取课件专业类型
	 */
	public static Integer getPapidAndQueidSelectScore(Integer papId,Integer queId){
		return ysUserPapAnswerDao.papidAndQueidSelectScore(papId, queId);
	}
	/*
	 * 通过id 获取 回复次数
	 */
	public static Integer getIdSelectForum(Integer id){
		return ysForumDao.idSelectForum(id);
	}

}
