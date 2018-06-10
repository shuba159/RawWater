/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.forum.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsForum;
import com.jeeplus.modules.entity.YsReply;

/**
 * 论坛管理DAO接口
 * @author hjm
 * @version 2018-06-04
 */
@MyBatisDao
public interface YsForumDao extends CrudDao<YsForum> {
	public Integer idSelectForum(@Param("id")Integer id);  // 利用id查询回复次数
	public List<YsReply> idSelectReplay(@Param("id")Integer id);  // 利用id查询回复内容
	public YsForum idSelectYsForum(@Param("id")Integer id);  // 利用id获取帖子所有内容
}