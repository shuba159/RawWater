/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsDepartment;
import com.jeeplus.modules.project.dao.YsDepartmentDao;

/**
 * 部门Service
 * @author 部门
 * @version 2018-05-11
 */
@Service
@Transactional(readOnly = true)
public class YsDepartmentService extends CrudService<YsDepartmentDao, YsDepartment> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected YsDepartmentDao dao;
	
	public YsDepartment get(String id) {
		return super.get(id);
	}
	
	public List<YsDepartment> findList(YsDepartment ysDepartment) {
		return super.findList(ysDepartment);
	}
	

	
	public Page<YsDepartment> findPage(Page<YsDepartment> page, YsDepartment ysDepartment) {
		return super.findPage(page, ysDepartment);
	}
	
	@Transactional(readOnly = false)
	public void save(YsDepartment ysDepartment) {
		super.save(ysDepartment);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsDepartment ysDepartment) {
		super.delete(ysDepartment);
	}

	public YsDepartment getDepIdByName(YsDepartment ysDepartment) {
		ysDepartment = dao.getDepIdByName(ysDepartment);
		return ysDepartment;
	}

	@Transactional(readOnly = false)
	public List<Integer> findUserIdsByDepIdAndUserIds(Map<Object, Object> map) {
		List<Integer> testUserIdList = dao.findUserIdsByDepIdAndUserIds(map);
		return testUserIdList;
	}	
	
}