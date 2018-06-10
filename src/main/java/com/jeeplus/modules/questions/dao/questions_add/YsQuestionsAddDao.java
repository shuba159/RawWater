/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.questions.dao.questions_add;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsQuestions;

/**
 * 添加题库DAO接口
 * @author hjm
 * @version 2018-05-18
 */
@MyBatisDao
public interface YsQuestionsAddDao extends CrudDao<YsQuestions> {
  List<YsQuestions> RandYsYsQuestionsDan(@Param("depId")String depId,@Param("majorType")String majorType);
  List<YsQuestions> RandYsYsQuestionsDuo();
  int selectYsQuestionsCount(@Param("depId")String depId,@Param("majorType")String majorType);
  YsQuestions selectRanPaper(String id);
}