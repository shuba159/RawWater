/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsDepartment;
import com.jeeplus.modules.entity.YsTest;

/**
 * 人工评卷DAO接口
 * @author hjm
 * @version 2018-05-28
 */
@MyBatisDao
public interface YsTestDao extends CrudDao<YsTest> {

	void savePapId(Map<Object, Object> map);

	void saveYsTest(YsTest ysTest);

	void saveYsTestDep(Map<Object, Object> map);

	void saveYsTestUser(Map<Object, Object> map);

	List<YsDepartment> findYsTestDepById(YsTest ysTest2);

	
}