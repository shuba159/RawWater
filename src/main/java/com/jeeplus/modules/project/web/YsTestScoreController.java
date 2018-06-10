package com.jeeplus.modules.project.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.entity.YsTest;
import com.jeeplus.modules.entity.YsUserPapAnswer;
import com.jeeplus.modules.project.dao.YsTestScoreDao;
import com.jeeplus.modules.project.entity.IntAndString;
import com.jeeplus.modules.project.entity.YsTestScore;
import com.jeeplus.modules.project.entity.IsEvaAndNotEva;
import com.jeeplus.modules.project.service.YsTestScoreService;
import com.jeeplus.modules.project.service.YsUserPapAnswerService;

/**
 * 按人评卷Controller
 * @author hjm
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/project/ysTestScore")
public class YsTestScoreController extends BaseController {

	@Autowired
	private YsTestScoreService ysTestScoreService;
	@Autowired
	private YsTestScoreDao ysTestScoreDao;
	@Autowired
	private YsUserPapAnswerService ysUserPapAnswerService;
	
	@ModelAttribute
	public YsTestScore get(@RequestParam(required=false) String id) {
	
		YsTestScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysTestScoreService.get(id);
		}
		if (entity == null){
			entity = new YsTestScore();
		}
		return entity;
	}
	
	/**
	 * 按人评卷列表页面
	 */
//	@RequiresPermissions("project:ysTestScore:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsTestScore ysTestScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.err.println("进入YsTestScore  con");
		if(ysTestScore.getUserIds() != null || ysTestScore.getUserIds() !=""){
			String userIds  = ysTestScore.getUserIds();  // 查询时前台输入的用户名数据
			Integer in =ysTestScoreDao.userNameSelectId(userIds);   // 利用用户名查询出用户Id
			ysTestScore.setUserId(in);
		}
		if (ysTestScore.getDepIds()!=null || ysTestScore.getUserIds() !=""){
			String depIds = ysTestScore.getDepIds();    // 查询时前台输入的部门数据
			Integer in = ysTestScoreDao.depNameSelectDepId(depIds);  // 利用部门名查看部门的Id
			ysTestScore.setDepId(in);	
		}
		
		List<YsTest> list1 = null;
		String id =request.getParameter("id");    // 接收试卷的id
		if (id == null) {
			String aaid = request.getParameter("aaid");  // 接收试卷第二次的id
			if (aaid!=null || aaid!="") {
				list1 = ysTestScoreDao.idSelectYsTest(Integer.parseInt(aaid));
				ysTestScore.setTestId(Integer.parseInt(aaid));
			}
		}else{
			 list1 = ysTestScoreDao.idSelectYsTest(Integer.parseInt(id));
			 ysTestScore.setTestId(Integer.parseInt(id));
		}
		
		Page<YsTestScore> page = ysTestScoreService.findPage(new Page<YsTestScore>(request, response), ysTestScore); 
		
		for (YsTest ysTest : list1) {
			System.err.println("<>++++<<"+ysTest.toString());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("list1", list1);
		
		return "modules/project/ysTestScoreList";
	}
	
	@RequestMapping(value="ysPaper")
	public String ysPaper(HttpServletRequest request, HttpServletResponse response,Model model){
		
		String papId=request.getParameter("id");  // 试卷id
		String userId=request.getParameter("userId");  // 用户id
		System.err.println("id++"+papId);
		System.err.println("userId++"+userId);

		// 通过试卷id和用户id来获取题id和用户正确答案
		List<YsUserPapAnswer> list1=ysUserPapAnswerService.ysUserPapAnswerSer(Integer.parseInt(papId),Integer.parseInt(userId));
		
		List<IntAndString> iasList = new ArrayList<IntAndString>();  // 存储的都是用户答得每道题的id 和 用户答得每到题的答案
		
		if (list1!=null) {
			for (YsUserPapAnswer ysUserPapAnswer : list1) {
				IntAndString ias = new IntAndString();
				ias.setInteger(ysUserPapAnswer.getQueId());  // 题的id
				// ysUserPapAnswer.getQueId() 这是题的id 我要通过题的id来获取题的正确答案和 题的分数
				ias.setString(ysUserPapAnswer.getAnswer());  // 题的答案
				iasList.add(ias);
			}
		}
		model.addAttribute("iasList", iasList);  // 将题的信息和用户答的答案信息传到前台页面
		model.addAttribute("papId", papId);  // 试卷id
		model.addAttribute("userId", userId);  // 用户id
		
		return "modules/project/ysPaper";
	}
	
	@RequestMapping(value="ysPaperClose")
	public void  ysPaperClose(HttpServletRequest request, HttpServletResponse response){
		System.err.println("+++++++++++进入ysPaperClose了");
		
		String papId2=request.getParameter("papId2");  // 试卷id
		String userId2=request.getParameter("userId2");  // 用户id
		String testScore=request.getParameter("testScore");  // 用户的这张卷分数
		
		System.err.println("papId2:"+papId2);
		System.err.println("userId2:"+userId2);
		System.err.println("testScore:"+testScore);
		
		// 通过试卷id和用户id 来更新 用户考试分数
		ysUserPapAnswerService.updateScoreSer(Integer.parseInt(papId2), Integer.parseInt(userId2), Integer.parseInt(testScore));
		
		
		IsEvaAndNotEva iii= ysUserPapAnswerService.idSelectYsTestAllSer(Integer.parseInt(papId2));
		Integer isEva=iii.getIsEva();  // 获取已评人数
		Integer notEva=iii.getNotEva();  // 获取未评人数
		
		if (iii.getNotEva()!=0 || iii.getNotEva()!= null) {
			// 开始更改已评未评人数
			isEva++;
			notEva--;
			ysUserPapAnswerService.idUpdateTestSer(isEva, notEva, Integer.parseInt(papId2));
		}
		
	}

}