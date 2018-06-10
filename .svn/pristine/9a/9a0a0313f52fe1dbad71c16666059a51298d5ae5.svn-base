/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsTest;
import com.jeeplus.modules.project.entity.YsTestScore;

/**
 * 按人评卷DAO接口
 * @author hjm
 * @version 2018-05-28
 */
@MyBatisDao
public interface YsTestScoreDao extends CrudDao<YsTestScore> {
	public List<YsTest> idSelectYsTest (@Param("id")Integer id);  // 根据id查询出试卷的数据
	public String depNameSelect(@Param("id")Integer id);  // 根据id获取部门名
	public String testNameSelectScoIdPapId(@Param("papid")Integer papid,@Param("scoreid")Integer scoreid);  // 根据ys_test_score.pap_id 和 ys_test_score.Id 获取 ys_test.test_name
    public Integer userNameSelectId(@Param("str")String str);  // 利用用户名查询出用户Id
    public Integer depNameSelectDepId(@Param("str")String str);  // 利用部门名查询出部门Id
    public String idSelectUserName(@Param("id")Integer id);  // 根据用户Id获取用户姓名
}