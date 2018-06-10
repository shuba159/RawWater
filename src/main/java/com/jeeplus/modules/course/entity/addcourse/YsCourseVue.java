package com.jeeplus.modules.course.entity.addcourse;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.entity.YsMajor;

public class YsCourseVue {
	private static final long serialVersionUID = 1L;
	private String courseName;		// 课件名称
	private Integer depId;		// 部门外键
	private Integer courseType;		// 课件类型1.视频音频2.pdf3.swf.
	private String address;		// 文件路径
	private Integer courseClass;		// 课件分类 1.动态2.静态
	private String describeA;		// 课件讲义
	private Integer createId;		// 创建人
	private Date createTime;		// 创建时间
	private Date createTime2;		// 用于查询时间段来使用的
	private Integer goodNumber;		// 点赞数
	private Integer clickNumber;		// 点击量
	private Integer majorType;		// 专业类型
	private Integer proId; // 仅用于传值
	private Date updateTime;   // 修改时间
	private String id;
	private String startime;
	private String state;
	private String couid;
	public String getCouid() {
		return couid;
	}
	public void setCouid(String couid) {
		this.couid = couid;
	}
	public String getStartime() {
		return startime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setStartime(String startime) {
		this.startime = startime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	private YsMajor ysMajor;    // 专业类型对象
	public void setYsMajor(YsMajor ysMajor) {
		this.ysMajor = ysMajor;
	}
	public YsMajor getYsMajor(){
		return ysMajor;
	}

	
	private Integer clieck; // 仅用于传值
	private  Integer signstate; // 仅用于传值
	public Integer getClieck() {
		return clieck;
	}

	public void setClieck(Integer clieck) {
		this.clieck = clieck;
	}

	public Integer getSignstate() {
		return signstate;
	}

	public void setSignstate(Integer signstate) {
		this.signstate = signstate;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	@ExcelField(title="课件名称", align=2, sort=1)
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@NotNull(message="部门外键不能为空")
	@ExcelField(title="部门外键", dictType="dep_id", align=2, sort=2)
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	@NotNull(message="课件类型不能为空")
	@ExcelField(title="课件类型1.视频音频2.pdf3.swf.", dictType="course_class", align=2, sort=3)
	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	
	@ExcelField(title="文件路径", align=2, sort=4)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotNull(message="课件分类不能为空")
	@ExcelField(title="课件分类 1.动态2.静态", dictType="course_type", align=2, sort=5)
	public Integer getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(Integer courseClass) {
		this.courseClass = courseClass;
	}
	
	@ExcelField(title="课件讲义", align=2, sort=6)
	public String getDescribeA() {
		return describeA;
	}

	public void setDescribeA(String describeA) {
		this.describeA = describeA;
	}
	
	@ExcelField(title="创建人", align=2, sort=7)
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	@ExcelField(title="创建时间", align=2, sort=8)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	//加上的用于查询时间段的内容
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}
	
	@ExcelField(title="点赞数", align=2, sort=9)
	public Integer getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}
	
	@ExcelField(title="点击量", align=2, sort=10)
	public Integer getClickNumber() {
		return clickNumber;
	}

	public void setClickNumber(Integer clickNumber) {
		this.clickNumber = clickNumber;
	}
	
	@NotNull(message="专业类型不能为空")
	@ExcelField(title="专业类型", dictType="major_type", align=2, sort=11)
	public Integer getMajorType() {
		return majorType;
	}

	public void setMajorType(Integer majorType) {
		this.majorType = majorType;
	}
	
	public Date getUpdateTime() {  // 修改时间
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public String toString() {
		return "YsCourse [courseName=" + courseName + ", depId=" + depId + ", courseType=" + courseType + ", address="
				+ address + ", courseClass=" + courseClass + ", describeA=" + describeA + ", createId=" + createId
				+ ", createTime=" + createTime + ", createTime2=" + createTime2 + ", goodNumber=" + goodNumber
				+ ", clickNumber=" + clickNumber + ", majorType=" + majorType + ", updateTime=" + updateTime + "]";
	}

}
