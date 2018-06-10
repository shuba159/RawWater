/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsUser;

/**
 * 用户信息DAO接口
 * @author wdy
 * @version 2018-05-14
 */
@MyBatisDao
public interface YsUserDao extends CrudDao<YsUser> {
	YsUser selectByPrimaryKey(Integer id);

    List<YsUser> selectAll();

   
    YsUser login(String userName,String userPwd);

	YsUser findUserId(YsUser ysUser);
}