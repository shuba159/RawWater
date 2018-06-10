/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.dynamic.entity.YsDynamic;
import com.jeeplus.modules.book.dao.book.YsBookDao;
import com.jeeplus.modules.dynamic.dao.YsDynamicDao;

/**
 * 动态浏览Service
 * @author mx
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class YsDynamicService extends CrudService<YsDynamicDao, YsDynamic> {
	
	public YsDynamic get(String id) {
		return super.get(id);
	}
	
	public List<YsDynamic> findList(YsDynamic ysDynamic) {
		return super.findList(ysDynamic);
	}
	
	public Page<YsDynamic> findPage(Page<YsDynamic> page, YsDynamic ysDynamic) {
		return super.findPage(page, ysDynamic);
	}
	
	@Transactional(readOnly = false)
	public void save(YsDynamic ysDynamic) {
		super.save(ysDynamic);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsDynamic ysDynamic) {
		super.delete(ysDynamic);
	}
	
	
	
	
}