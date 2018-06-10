/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mien.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.book.entity.book.YsBook;
import com.jeeplus.modules.mien.entity.YsMien;

/**
 * 教学风采DAO接口
 * @author mx
 * @version 2018-05-18
 */
@MyBatisDao
public interface YsMienDao extends CrudDao<YsMien> {
	 List<YsMien> selectAllHomePage();
	
}