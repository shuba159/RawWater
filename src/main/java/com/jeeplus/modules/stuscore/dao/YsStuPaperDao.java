/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.stuscore.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.stuscore.entity.ErrQuestion;
import com.jeeplus.modules.stuscore.entity.YsStuPaper;

/**
 * 错题DAO接口
 * @author mx
 * @version 2018-05-28
 */
@MyBatisDao
public interface YsStuPaperDao extends CrudDao<YsStuPaper> {
        List<ErrQuestion> selectByTypeid(String typeId);
	
}