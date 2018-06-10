/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsDepartment;

/**
 * 部门DAO接口
 * @author 部门
 * @version 2018-05-11
 */
@MyBatisDao
public interface YsDepartmentDao extends CrudDao<YsDepartment> {
	List<YsDepartment>  selectAllYsDepartment();

	YsDepartment getDepIdByName(YsDepartment ysDepartment);

	List<Integer> findUserIdsByDepIdAndUserIds(Map<Object, Object> map);
}