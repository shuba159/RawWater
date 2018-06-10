/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;


import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 专业类型管理Entity
 * @author hjm
 * @version 2018-05-15
 */
public class YsMajor extends DataEntity<YsMajor> {
	
	private static final long serialVersionUID = 1L;
	private String majorName;		// 专业名称
	
	public YsMajor() {
		super();
	}

	public YsMajor(String id){
		super(id);
	}

	@ExcelField(title="专业名称", align=2, sort=1)
	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Override
	public String toString() {
		return "YsMajor [majorName=" + majorName + "]";
	}

	
	
}