/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.forum.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsForum;
import com.jeeplus.modules.forum.dao.YsForumDao;

/**
 * 论坛管理Service
 * @author hjm
 * @version 2018-06-04
 */
@Service
@Transactional(readOnly = true)
public class YsForumService extends CrudService<YsForumDao, YsForum> {

	public YsForum get(String id) {
		return super.get(id);
	}
	
	public List<YsForum> findList(YsForum ysForum) {
		return super.findList(ysForum);
	}
	
	public Page<YsForum> findPage(Page<YsForum> page, YsForum ysForum) {
		return super.findPage(page, ysForum);
	}
	
	@Transactional(readOnly = false)
	public void save(YsForum ysForum) {
		super.save(ysForum);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsForum ysForum) {
		super.delete(ysForum);
	}
	
	
	
	
}