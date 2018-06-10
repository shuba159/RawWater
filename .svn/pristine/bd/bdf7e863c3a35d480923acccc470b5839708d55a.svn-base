package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeplus.modules.entity.YsUserPapAnswer;
import com.jeeplus.modules.project.dao.YsUserPapAnswerDao;
import com.jeeplus.modules.project.entity.IsEvaAndNotEva;

import scala.collection.generic.BitOperations.Int;

@Service
public class YsUserPapAnswerService {

	@Autowired
	private YsUserPapAnswerDao ysUserPapAnswerDao;
	public List<YsUserPapAnswer> ysUserPapAnswerSer(Integer papId,Integer userId){
		return ysUserPapAnswerDao.papIdUserIdSelectAll(papId, userId);
	}
	
	public void updateScoreSer(Integer papId,Integer userId,Integer score){
		 ysUserPapAnswerDao.updateScore(papId, userId, score);
	}
	
	public IsEvaAndNotEva idSelectYsTestAllSer(Integer papId2){
		return ysUserPapAnswerDao.idSelectYsTestAll(papId2);
	}
	
	public void idUpdateTestSer(Integer isEva,Integer notEva,Integer id){
		ysUserPapAnswerDao.idUpdateTest(isEva, notEva, id);
	}
}
