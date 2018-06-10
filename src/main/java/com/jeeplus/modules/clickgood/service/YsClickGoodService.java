/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.clickgood.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.clickgood.entity.YsClickGood;
import com.jeeplus.modules.clickgood.dao.YsClickGoodDao;

/**
 * 点赞收藏Service
 * @author mx
 * @version 2018-05-22
 */
@Service
@Transactional(readOnly = true)
public class YsClickGoodService extends CrudService<YsClickGoodDao, YsClickGood> {

	public YsClickGood get(String id) {
		return super.get(id);
	}
	
	public List<YsClickGood> findList(YsClickGood ysClickGood) {
		return super.findList(ysClickGood);
	}
	
	public Page<YsClickGood> findPage(Page<YsClickGood> page, YsClickGood ysClickGood) {
		return super.findPage(page, ysClickGood);
	}
	
	@Transactional(readOnly = false)
	public void save(YsClickGood ysClickGood) {
		super.save(ysClickGood);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsClickGood ysClickGood) {
		super.delete(ysClickGood);
	}
	
	
	
	
}