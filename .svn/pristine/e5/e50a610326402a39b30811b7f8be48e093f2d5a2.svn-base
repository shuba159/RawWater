/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.onlinetime.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.onlinetime.entity.YsOnlinetime;
import com.jeeplus.modules.onlinetime.dao.YsOnlinetimeDao;

/**
 * 在线时长Service
 * @author mx
 * @version 2018-05-22
 */
@Service
@Transactional(readOnly = true)
public class YsOnlinetimeService extends CrudService<YsOnlinetimeDao, YsOnlinetime> {

	public YsOnlinetime get(String id) {
		return super.get(id);
	}
	
	public List<YsOnlinetime> findList(YsOnlinetime ysOnlinetime) {
		return super.findList(ysOnlinetime);
	}
	
	public Page<YsOnlinetime> findPage(Page<YsOnlinetime> page, YsOnlinetime ysOnlinetime) {
		return super.findPage(page, ysOnlinetime);
	}
	
	@Transactional(readOnly = false)
	public void save(YsOnlinetime ysOnlinetime) {
		super.save(ysOnlinetime);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsOnlinetime ysOnlinetime) {
		super.delete(ysOnlinetime);
	}
	
	
	
	
}