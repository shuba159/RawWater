/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.book.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.book.entity.book.YsBook;
import com.jeeplus.modules.book.dao.book.YsBookDao;

/**
 * 推荐书目Service
 * @author mx
 * @version 2018-05-18
 */
@Service
@Transactional(readOnly = true)
public class YsBookService extends CrudService<YsBookDao, YsBook> {
	@Autowired
	YsBookDao ysBookDao;
	public YsBook get(String id) {
		return super.get(id);
		
	}
	/*public List<YsBook> selectAllHomePage( ) {
		// TODO Auto-generated method stub
				return ysBookDao.selectAllHomePage() ;
			}*/
	
	public List<YsBook> findList(YsBook ysBook) {
		return super.findList(ysBook);
	}
	
	public Page<YsBook> findPage(Page<YsBook> page, YsBook ysBook) {
		return super.findPage(page, ysBook);
	}
	
	
	@Transactional(readOnly = false)
	public void save(YsBook ysBook) {
		super.save(ysBook);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsBook ysBook) {
		super.delete(ysBook);
	}
	
	
	
	
}