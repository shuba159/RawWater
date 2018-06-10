/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.clickgood.dao;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.clickgood.entity.YsClickGood;

/**
 * 点赞收藏DAO接口
 * @author mx
 * @version 2018-05-22
 */
@MyBatisDao
public interface YsClickGoodDao extends CrudDao<YsClickGood> {
	YsClickGood findclieckByUser(String userid,String courid);
	int insertclieckByUser(YsClickGood ysclieckgood);
         int findclieckByUserByCouid(@Param("couid")String couid);
	
	 
}