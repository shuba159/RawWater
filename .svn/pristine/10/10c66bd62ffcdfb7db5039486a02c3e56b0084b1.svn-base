/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.course.service.addcourse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.course.dao.addcourse.YsCourseDao;
import com.jeeplus.modules.entity.YsCourse;

/**
 * 课件管理Service
 * @author hjm
 * @version 2018-05-16
 */
@Service
@Transactional(readOnly = true)
public class YsCourseService extends CrudService<YsCourseDao, YsCourse> {
	
	@Autowired
	private YsCourseDao ysCourseDao;

	public YsCourse get(String id) {
		return super.get(id);
	}
	
	public List<YsCourse> findList(YsCourse ysCourse) {
		return super.findList(ysCourse);
	}
	
	public Page<YsCourse> findPage(Page<YsCourse> page, YsCourse ysCourse) {
		return super.findPage(page, ysCourse);
	}
	
	public Page<YsCourse> findPageByProId(Page<YsCourse> page, YsCourse ysCourse) {
		return super.findPageByProId(page, ysCourse);
	}
	
	@Transactional(readOnly = false)
	public void save(YsCourse ysCourse) {
		super.save(ysCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsCourse ysCourse) {
		super.delete(ysCourse);
	}
	
	
	
	
}