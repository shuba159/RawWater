package com.jeeplus.modules.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.entity.YsUserPapAnswer;
import com.jeeplus.modules.project.entity.IsEvaAndNotEva;

@MyBatisDao
public interface YsUserPapAnswerDao {
	public List<YsUserPapAnswer> papIdUserIdSelectAll(@Param("papId")Integer papId,@Param("userId")Integer userId);  // 通过试卷id和用户id获取ys_user_pap_answer表的所有信息
    public YsQuestions idSelectYsQuestions(@Param("id")Integer id);    // 通过id获取题库的题的信息
    public Integer papidAndQueidSelectScore(@Param("papId")Integer papId,@Param("queId")Integer queId);  // 通过试卷id和题的id获取题的分数
    public void updateScore(@Param("papId")Integer papId,@Param("userId")Integer userId,@Param("score")Integer score);  // 通过试卷id和用户id 来更新 用户考试分数
    public IsEvaAndNotEva idSelectYsTestAll(@Param("papId2")Integer papId2);  // 通过id获取已评人数 和 未评人数
    public void idUpdateTest(@Param("isEva")Integer isEva,@Param("notEva")Integer notEva,@Param("id")Integer id);  // 通过id更新test表的已评未评人数
}
