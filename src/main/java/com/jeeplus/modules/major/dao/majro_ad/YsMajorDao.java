/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.major.dao.majro_ad;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsMajor;

/**
 * 专业类型管理DAO接口
 * @author hjm
 * @version 2018-05-15
 */
@MyBatisDao
public interface YsMajorDao extends CrudDao<YsMajor> {

	public List<YsMajor> YsMajorSelectAll ();
	

}