/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.questions.dao.questions_as;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsQuestions;

/**
 * 题库管理DAO接口
 * @author hjm
 * @version 2018-05-22
 */
@MyBatisDao
public interface YsQuestionsDao extends CrudDao<YsQuestions> {
	public String majorTypeSelectQues(@Param("aa")Integer a,@Param("bb")Integer b);

	public int findTotalNum();
	
}