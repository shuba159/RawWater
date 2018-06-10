package com.jeeplus.modules.course.dao.addcourse;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.course.entity.addcourse.YsCourseUser;
import com.jeeplus.modules.course.entity.addcourse.YsCourseVue;
import com.jeeplus.modules.entity.YsCourse;
/**
 * 前台课件管理DAO接口
 * @author 
 * @version 2018-05-16
 */
@MyBatisDao
public interface YsCourseVueDao extends CrudDao<YsCourseVue> {
	//课程详情
  List<YsCourseVue> selectByPro(@Param("page") int page, @Param("limit") int rows,@Param("proid") int proid
		 , @Param("state1")Integer state,@Param("courseclass")String courseclass,@Param("coursetype")String coursetype,@Param("starttime")String starttime,@Param("eTime")String eTime	,@Param("coursename")String coursename   );
  int selectByProCount(@Param("proid") int proid
			 , @Param("state1")Integer state,@Param("courseclass")String courseclass,@Param("coursetype")String coursetype,@Param("starttime")String starttime,@Param("eTime")String eTime	,@Param("coursename")String coursename 	);
        List<YsCourseUser> selectByProCou(@Param("page") int page, @Param("limit")int limit,@Param("proid") int proid,@Param("couid") int couid,@Param("depId") String depId,@Param("userName1") String userName);
        int selectByUserCount(@Param("proid") int proid,@Param("couid") int couid,@Param("depId") String depId,@Param("userName1") String userName);
} 
