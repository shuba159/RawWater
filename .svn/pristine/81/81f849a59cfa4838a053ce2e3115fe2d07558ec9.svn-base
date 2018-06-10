package com.jeeplus.modules.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsEvaluate;
import com.jeeplus.modules.entity.YsProject;
import com.jeeplus.modules.project.entity.YsPaPerScoreVue;
import com.jeeplus.modules.project.entity.YsProPaperVue;
import com.jeeplus.modules.project.entity.YsyProclassVue;

@MyBatisDao
public interface YsProjectVueDao  extends CrudDao<YsProject>{
	List<YsProject> findAllByRole(@Param("page") int page,@Param("rows") int rows,@Param("createId")String createId,@Param("depId")String depId,@Param("proName")String proName,@Param("create_time")String create_time,@Param("eTime")String eTime,@Param("proClassName")String proClassName);
	int findAllByRoleCount(@Param("createId")String createId,@Param("depId")String depId,@Param("proName")String proName,@Param("create_time")String create_time,@Param("eTime")String eTime,@Param("proClassName")String proClassName);
    List<YsProPaperVue> selectByProPaper(@Param("page") int page,@Param("limit") int rows,@Param("proid")String proid,@Param("testName")String testName,
    		@Param("depId")String depId,@Param("testNumber")String testNumber,@Param("startTime")String startTime,@Param("eTime")String eTime);
    int selectByProPaperCount(@Param("proid")String proid,@Param("testName")String testName,
    		@Param("depId")String depId,@Param("testNumber")String testNumber,@Param("startTime")String startTime,@Param("eTime")String eTime);
    List<YsPaPerScoreVue> findByPapScore(@Param("page") int page,@Param("limit") 
    int rows,@Param("papId")String papId,@Param("depId")String depId,@Param("ispath")String ispath,@Param("xingming")String xingming,@Param("ordeykey")String ordeykey);
    int findByPapScoreCount(@Param("papId")String papId,@Param("depId")String depId,@Param("ispath")String ispath,@Param("xingming")String xingming,@Param("ordeykey")String ordeykey);
    int deleteByid(@Param("id")String id);//--删除项目
    int deleteProCouseByid(@Param("id")String id);//--项目--删除课程
    int deleteProCouseStuByid(@Param("id")String id);//项目--删除个人进度
    int deleteProTestByid(@Param("id")String id);  //项目-删除试卷
    int deleteProTestScoreByid(@Param("id")String id);//--项目--删除个人成绩
    List<YsyProclassVue> findProFen(); //项目分类
}
