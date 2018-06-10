/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsProClass;
import com.jeeplus.modules.project.dao.YsProClassDao;

/**
 * 项目分类Service
 * @author wdy
 * @version 2018-05-17
 */
@Service
@Transactional(readOnly = true)
public class YsProClassService extends CrudService<YsProClassDao, YsProClass> {

	public YsProClass get(String id) {
		return super.get(id);
	}
	
	public List<YsProClass> findList(YsProClass ysProClass) {
		return super.findList(ysProClass);
	}
	
	public Page<YsProClass> findPage(Page<YsProClass> page, YsProClass ysProClass) {
		return super.findPage(page, ysProClass);
	}
	
	@Transactional(readOnly = false)
	public void save(YsProClass ysProClass) {
		super.save(ysProClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsProClass ysProClass) {
		super.delete(ysProClass);
	}
	
	
	
	
}