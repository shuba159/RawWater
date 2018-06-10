/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mien.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.mien.entity.YsMien;
import com.jeeplus.modules.mien.dao.YsMienDao;

/**
 * 教学风采Service
 * @author mx
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class YsMienService extends CrudService<YsMienDao, YsMien> {

	public YsMien get(String id) {
		return super.get(id);
	}
	
	public List<YsMien> findList(YsMien ysMien) {
		return super.findList(ysMien);
	}
	
	public Page<YsMien> findPage(Page<YsMien> page, YsMien ysMien) {
		return super.findPage(page, ysMien);
	}
	
	@Transactional(readOnly = false)
	public void save(YsMien ysMien) {
		super.save(ysMien);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsMien ysMien) {
		super.delete(ysMien);
	}
	
	
	
	
}