/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsUser;
import com.jeeplus.modules.project.dao.YsUserDao;

/**
 * 用户信息Service
 * @author wdy
 * @version 2018-05-14
 */
@Service
@Transactional(readOnly = true)
public class YsUserService extends CrudService<YsUserDao, YsUser> {
	
	@Autowired
	private YsUserDao ysUserDao;

	public YsUser get(String id) {
		return super.get(id);
	}
	
	public List<YsUser> findList(YsUser ysUser) {
		return super.findList(ysUser);
	}
	
	public Page<YsUser> findPage(Page<YsUser> page, YsUser ysUser) {
		return super.findPage(page, ysUser);
	}
	
	@Transactional(readOnly = false)
	public void save(YsUser ysUser) {
		super.save(ysUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsUser ysUser) {
		super.delete(ysUser);
	}

	public YsUser findUserId(YsUser ysUser) {
		ysUser = ysUserDao.findUserId(ysUser);
		return ysUser;
	}
	
}