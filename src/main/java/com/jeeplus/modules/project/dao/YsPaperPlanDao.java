/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsPaper;
import com.jeeplus.modules.entity.YsPaperPlan;
import com.jeeplus.modules.entity.YsQuestions;

/**
 * 出卷计划DAO接口
 * @author wdy
 * @version 2018-05-24
 */
@MyBatisDao
public interface YsPaperPlanDao extends CrudDao<YsPaperPlan> {

	List<YsQuestions> findQuesionByRandom(Map<Object, Object> map);

	List<YsQuestions> findQuesionByPlanId(Map<Object, Object> map);

	YsPaper findPaperByPlanId(Map<Object, Object> map);
	
}