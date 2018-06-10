/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.notice.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.mien.entity.YsMien;
import com.jeeplus.modules.notice.entity.YsNotice;

/**
 * 培训通知DAO接口
 * @author mx
 * @version 2018-05-18
 */
@MyBatisDao
public interface YsNoticeDao extends CrudDao<YsNotice> {

	 List<YsNotice> selectAllHomePage();
}