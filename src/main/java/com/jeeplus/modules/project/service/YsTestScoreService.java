/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.project.entity.YsTestScore;
import com.jeeplus.modules.project.dao.YsTestScoreDao;

/**
 * 按人评卷Service
 * @author hjm
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class YsTestScoreService extends CrudService<YsTestScoreDao, YsTestScore> {

	public YsTestScore get(String id) {
		return super.get(id);
	}
	
	public List<YsTestScore> findList(YsTestScore ysTestScore) {
		return super.findList(ysTestScore);
	}
	
	public Page<YsTestScore> findPage(Page<YsTestScore> page, YsTestScore ysTestScore) {
		return super.findPage(page, ysTestScore);
	}
	
	@Transactional(readOnly = false)
	public void save(YsTestScore ysTestScore) {
		super.save(ysTestScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsTestScore ysTestScore) {
		super.delete(ysTestScore);
	}
	
	
	
	
}