/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 部门Entity
 * @author 部门
 * @version 2018-05-11
 */
public class YsDepartment extends DataEntity<YsDepartment> {
	
	private static final long serialVersionUID = 1L;
	private String depName;		// 部门名称
	
	public YsDepartment() {
		super();
	}

	public YsDepartment(String id){
		super(id);
	}

	@ExcelField(title="部门名称", align=2, sort=1)
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	
}