/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsStustate;
import com.jeeplus.modules.project.dao.YsStustateDao;

/**
 * 学习记录Service
 * @author wdy
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class YsStustateService extends CrudService<YsStustateDao, YsStustate> {

	public YsStustate get(String id) {
		return super.get(id);
	}
	
	public List<YsStustate> findList(YsStustate ysStustate) {
		return super.findList(ysStustate);
	}
	
	public Page<YsStustate> findPage(Page<YsStustate> page, YsStustate ysStustate) {
		return super.findPage(page, ysStustate);
	}
	
	@Transactional(readOnly = false)
	public void save(YsStustate ysStustate) {
		super.save(ysStustate);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsStustate ysStustate) {
		super.delete(ysStustate);
	}
	
	
	
	
}