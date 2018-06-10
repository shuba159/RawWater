/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsInform;
import com.jeeplus.modules.project.dao.YsInformDao;

/**
 * 通知Service
 * @author wdy
 * @version 2018-05-22
 */
@Service
@Transactional(readOnly = true)
public class YsInformService extends CrudService<YsInformDao, YsInform> {

	public YsInform get(String id) {
		return super.get(id);
	}
	
	public List<YsInform> findList(YsInform ysInform) {
		return super.findList(ysInform);
	}
	
	public Page<YsInform> findPage(Page<YsInform> page, YsInform ysInform) {
		return super.findPage(page, ysInform);
	}
	
	@Transactional(readOnly = false)
	public void save(YsInform ysInform) {
		super.save(ysInform);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsInform ysInform) {
		super.delete(ysInform);
	}
	
	
	
	
}