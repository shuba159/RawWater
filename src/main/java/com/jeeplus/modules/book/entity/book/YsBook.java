/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.book.entity.book;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 推荐书目Entity
 * @author mx
 * @version 2018-05-18
 */
public class YsBook extends DataEntity<YsBook> {
	
	private static final long serialVersionUID = 1L;
	private String bookName;		// 推荐书目名称
	private Date publishTime;		// 推荐书目发布时间
	private String content;		// 推荐书目内容
	private Integer publishState;		// 推荐书目状态，1已发布，2未发布
	private Integer depId;		// 部位外键
	
	public YsBook() {
		super();
	}

	public YsBook(String id){
		super(id);
	}

	@ExcelField(title="推荐书目名称", align=2, sort=1)
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="推荐书目发布时间不能为空")
	@ExcelField(title="推荐书目发布时间", align=2, sort=2)
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	@ExcelField(title="推荐书目内容", align=2, sort=3)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@NotNull(message="推荐书目状态，1已发布，2未发布不能为空")
	@ExcelField(title="推荐书目状态，1已发布，2未发布", align=2, sort=4)
	public Integer getPublishState() {
		return publishState;
	}

	public void setPublishState(Integer publishState) {
		this.publishState = publishState;
	}
	
	@ExcelField(title="部位外键", align=2, sort=5)
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
}