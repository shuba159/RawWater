/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.myscore.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.myscore.entity.YsMyScore;

/**
 * 自测DAO接口
 * @author mx
 * @version 2018-05-28
 */
@MyBatisDao
public interface YsMyScoreDao extends CrudDao<YsMyScore> {

	
}