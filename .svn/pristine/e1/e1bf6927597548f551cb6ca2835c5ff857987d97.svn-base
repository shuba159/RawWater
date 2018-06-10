/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.major.service.majro_ad;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;

/**
 * 专业类型管理Service
 * @author hjm
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class YsMajorService extends CrudService<YsMajorDao, YsMajor> {

	public YsMajor get(String id) {
		return super.get(id);
	}
	
	public List<YsMajor> findList(YsMajor ysMajor) {
		return super.findList(ysMajor);
	}
	
	public Page<YsMajor> findPage(Page<YsMajor> page, YsMajor ysMajor) {
		return super.findPage(page, ysMajor);
	}
	
	@Transactional(readOnly = false)
	public void save(YsMajor ysMajor) {
		super.save(ysMajor);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsMajor ysMajor) {
		super.delete(ysMajor);
	}
	
	
	
	
}