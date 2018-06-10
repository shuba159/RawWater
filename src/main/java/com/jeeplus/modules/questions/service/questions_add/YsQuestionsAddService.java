/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.questions.service.questions_add;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.questions.dao.questions_as.YsQuestionsDao;

/**
 * 题库管理Service
 * @author hjm
 * @version 2018-05-22
 */
@Service
@Transactional(readOnly = true)
public class YsQuestionsAddService extends CrudService<YsQuestionsDao, YsQuestions> {

	public YsQuestions get(String id) {
		return super.get(id);
	}
	
	public List<YsQuestions> findList(YsQuestions ysQuestions) {
		return super.findList(ysQuestions);
	}
	
	public Page<YsQuestions> findPage(Page<YsQuestions> page, YsQuestions ysQuestions) {
		return super.findPage(page, ysQuestions);
	}
	
	@Transactional(readOnly = false)
	public void save(YsQuestions ysQuestions) {
		super.save(ysQuestions);
	}
	
	@Transactional(readOnly = false)
	public void delete(YsQuestions ysQuestions) {
		super.delete(ysQuestions);
	}
	
	
	
	
}