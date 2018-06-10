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
import com.jeeplus.modules.entity.YsPaperPlan;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.project.dao.YsPaperPlanDao;

/**
 * 出卷计划Service
 * @author wdy
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class YsPaperPlanService extends CrudService<YsPaperPlanDao, YsPaperPlan> {
	
	// 
	@Autowired
	private YsPaperPlanDao ysPaperPlanDao;
	
	public YsPaperPlan get(String id) {
		return super.get(id);
	}
	
	public List<YsPaperPlan> findList(YsPaperPlan ysPaperPlan) {
		return super.findList(ysPaperPlan);
	}
	
	public Page<YsPaperPlan> findPage(Page<YsPaperPlan> page, YsPaperPlan ysPaperPlan) {
		return super.findPage(page, ysPaperPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(YsPaperPlan ysPaperPlan) {
		super.save(ysPaperPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsPaperPlan ysPaperPlan) {
		super.delete(ysPaperPlan);
	}

	@Transactional(readOnly = false)
	public List<YsQuestions> findQuesionByRandom(Map<Object, Object> map) {
		List<YsQuestions> ysQuesionList = ysPaperPlanDao.findQuesionByRandom(map);
		return ysQuesionList;
	}

	@Transactional(readOnly = false)
	public List<YsQuestions> findQuesionByPlanId(Map<Object, Object> map) {
		return ysPaperPlanDao.findQuesionByPlanId(map);
	}

	@Transactional(readOnly = false)
	public YsPaper findPaperByPlanId(Map<Object, Object> map) {
		YsPaper ysPaper = ysPaperPlanDao.findPaperByPlanId(map);
		return ysPaper;
	}
	
	
	
	
}