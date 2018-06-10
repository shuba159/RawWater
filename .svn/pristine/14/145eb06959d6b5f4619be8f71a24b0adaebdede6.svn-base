/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.myscore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.myscore.entity.YsMyScore;
import com.jeeplus.modules.myscore.dao.YsMyScoreDao;

/**
 * 自测Service
 * @author mx
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class YsMyScoreService extends CrudService<YsMyScoreDao, YsMyScore> {

	public YsMyScore get(String id) {
		return super.get(id);
	}
	
	public List<YsMyScore> findList(YsMyScore ysMyScore) {
		return super.findList(ysMyScore);
	}
	
	public Page<YsMyScore> findPage(Page<YsMyScore> page, YsMyScore ysMyScore) {
		return super.findPage(page, ysMyScore);
	}
	
	@Transactional(readOnly = false)
	public void save(YsMyScore ysMyScore) {
		super.save(ysMyScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsMyScore ysMyScore) {
		super.delete(ysMyScore);
	}
	
	
	
	
}