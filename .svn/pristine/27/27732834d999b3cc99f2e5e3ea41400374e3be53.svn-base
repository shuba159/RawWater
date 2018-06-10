/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsPaper;
import com.jeeplus.modules.entity.YsQuestions;

/**
 * 试卷DAO接口
 * @author wdy
 * @version 2018-05-30
 */
@MyBatisDao
public interface YsPaperDao extends CrudDao<YsPaper> {

	List<YsQuestions> findQuesionList(Map<Object, Object> map);

	int saveYsPaper(YsPaper ysPaper);

	void saveQueToPap(Map<Object, Object> map);
	
}