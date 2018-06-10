/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.stuscore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.stuscore.entity.YsStuPaper;
import com.jeeplus.modules.stuscore.dao.YsStuPaperDao;

/**
 * 错题Service
 * @author mx
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class YsStuPaperService extends CrudService<YsStuPaperDao, YsStuPaper> {

	public YsStuPaper get(String id) {
		return super.get(id);
	}
	
	public List<YsStuPaper> findList(YsStuPaper ysStuPaper) {
		return super.findList(ysStuPaper);
	}
	
	public Page<YsStuPaper> findPage(Page<YsStuPaper> page, YsStuPaper ysStuPaper) {
		return super.findPage(page, ysStuPaper);
	}
	
	@Transactional(readOnly = false)
	public void save(YsStuPaper ysStuPaper) {
		super.save(ysStuPaper);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsStuPaper ysStuPaper) {
		super.delete(ysStuPaper);
	}
	
	
	
	
}