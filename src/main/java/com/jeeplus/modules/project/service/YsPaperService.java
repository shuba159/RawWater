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
import com.jeeplus.modules.entity.YsPaper;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.project.dao.YsPaperDao;

/**
 * 试卷Service
 * @author wdy
 * @version 2018-05-30
 */
@Service
@Transactional(readOnly = true)
public class YsPaperService extends CrudService<YsPaperDao, YsPaper> {
	
	@Autowired
	private YsPaperDao ysPaperDao;

	public YsPaper get(String id) {
		return super.get(id);
	}
	
	public List<YsPaper> findList(YsPaper ysPaper) {
		return super.findList(ysPaper);
	}
	
	public Page<YsPaper> findPage(Page<YsPaper> page, YsPaper ysPaper) {
		return super.findPage(page, ysPaper);
	}
	
	@Transactional(readOnly = false)
	public void save(YsPaper ysPaper) {
		super.save(ysPaper);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsPaper ysPaper) {
		super.delete(ysPaper);
	}

	// 根据试卷id查询试题
	@Transactional(readOnly = false)
	public List<YsQuestions> findQuesionList(Map<Object, Object> map) {
		List<YsQuestions> ysQuestions = ysPaperDao.findQuesionList(map);
		return ysQuestions;
	}

	@Transactional(readOnly = false)
	public int saveYsPaper(YsPaper ysPaper) {
		int count = ysPaperDao.saveYsPaper(ysPaper);
		return count;
	}

	@Transactional(readOnly = false)
	public void saveQueToPap(Map<Object, Object> map) {
		ysPaperDao.saveQueToPap(map);
	}
	
	
	
	
}