/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.notice.entity.YsNotice;
import com.jeeplus.modules.notice.dao.YsNoticeDao;

/**
 * 培训通知Service
 * @author mx
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class YsNoticeService extends CrudService<YsNoticeDao, YsNotice> {

	public YsNotice get(String id) {
		return super.get(id);
	}
	
	public List<YsNotice> findList(YsNotice ysNotice) {
		return super.findList(ysNotice);
	}
	
	public Page<YsNotice> findPage(Page<YsNotice> page, YsNotice ysNotice) {
		return super.findPage(page, ysNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(YsNotice ysNotice) {
		super.save(ysNotice);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsNotice ysNotice) {
		super.delete(ysNotice);
	}
	
	
	
	
}