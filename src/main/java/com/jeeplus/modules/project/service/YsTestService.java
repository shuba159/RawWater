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
import com.jeeplus.modules.entity.YsTest;
import com.jeeplus.modules.project.dao.YsTestDao;

/**
 * 人工评卷Service
 * @author hjm
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class YsTestService extends CrudService<YsTestDao, YsTest> {
	
	@Autowired
	private YsTestDao ysTestDao;

	public YsTest get(String id) {
		return super.get(id);
	}
	
	public List<YsTest> findList(YsTest ysTest) {
		return super.findList(ysTest);
	}
	
	public Page<YsTest> findPage(Page<YsTest> page, YsTest ysTest) {
		return super.findPage(page, ysTest);
	}
	
	@Transactional(readOnly = false)
	public void save(YsTest ysTest) {
		super.save(ysTest);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsTest ysTest) {
		super.delete(ysTest);
	}

	@Transactional(readOnly = false)
	public void saveYsTest(YsTest ysTest) {
		ysTestDao.saveYsTest(ysTest);
	}

	@Transactional(readOnly = false)
	public void saveYsTestDep(Map<Object, Object> map) {
		ysTestDao.saveYsTestDep(map);
	}

	@Transactional(readOnly = false)
	public void saveYsTestUser(Map<Object, Object> map) {
		ysTestDao.saveYsTestUser(map);
	}
	
	
	
	
}