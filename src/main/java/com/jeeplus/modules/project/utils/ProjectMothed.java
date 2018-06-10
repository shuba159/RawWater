package com.jeeplus.modules.project.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.entity.YsCourse;
import com.jeeplus.modules.entity.YsPaper;
import com.jeeplus.modules.entity.YsPaperPlan;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.project.entity.YsPaperPlanPojo;
import com.jeeplus.modules.project.entity.YsTestPojo;
import com.jeeplus.modules.project.service.YsPaperPlanService;

public class ProjectMothed {
	
	/**
	 *  YsPaperPlanPojo 转为 ysPaperPlan
	 */
	public static YsPaperPlan PojoToPlan(YsPaperPlanPojo YsPaperPlanPojo, YsPaperPlan ysPaperPlan){
		String singleQueNum = YsPaperPlanPojo.getSingleQueNum();
		String singleDif = YsPaperPlanPojo.getSingleDif();
		String singleScore = YsPaperPlanPojo.getSingleScore();
		if(StringUtils.isNotBlank(singleQueNum) && StringUtils.isNotBlank(singleDif) && StringUtils.isNotBlank(singleScore)){
			ysPaperPlan.setSingleData(singleQueNum+","+singleDif+","+singleScore);
		}
		String manyQueNum = YsPaperPlanPojo.getManyQueNum();
		String manyDif = YsPaperPlanPojo.getManyDif();
		String manyScore = YsPaperPlanPojo.getManyScore();
		if(StringUtils.isNotBlank(manyQueNum) && StringUtils.isNotBlank(manyDif) && StringUtils.isNotBlank(manyScore)){
			ysPaperPlan.setManyData(manyQueNum+","+manyDif+","+manyScore);
		}
		String judgeQueNum = YsPaperPlanPojo.getJudgeQueNum();
		String judgeDif = YsPaperPlanPojo.getJudgeDif();
		String judgeScore = YsPaperPlanPojo.getJudgeScore();
		if(StringUtils.isNotBlank(judgeQueNum) && StringUtils.isNotBlank(judgeDif) && StringUtils.isNotBlank(judgeScore)){
			ysPaperPlan.setJudgeData(judgeQueNum+","+judgeDif+","+judgeScore);		
		}
		String fillQueNum = YsPaperPlanPojo.getFillQueNum();
		String fillDif = YsPaperPlanPojo.getFillDif();
		String fillScore = YsPaperPlanPojo.getFillScore();
		if(StringUtils.isNotBlank(fillQueNum) && StringUtils.isNotBlank(fillDif) && StringUtils.isNotBlank(fillScore)){
			ysPaperPlan.setFillData(fillQueNum+","+fillDif+","+fillScore);
		}
		String simpleQueNum = YsPaperPlanPojo.getSimpleQueNum();
		String simpleDif = YsPaperPlanPojo.getSimpleDif();
		String simpleScore = YsPaperPlanPojo.getSimpleScore();
		if(StringUtils.isNotBlank(simpleQueNum) && StringUtils.isNotBlank(simpleDif) && StringUtils.isNotBlank(simpleScore)){
			ysPaperPlan.setSimpleData(simpleQueNum+","+simpleDif+","+simpleScore);
		}
		
		return ysPaperPlan;
	}
	
	public static YsPaperPlanPojo PlanToPojo(YsPaperPlanPojo ysPaperPlanPojo, YsPaperPlan ysPaperPlan){
		ysPaperPlanPojo.setPlanName(ysPaperPlan.getPlanName());
		ysPaperPlanPojo.setMajorId(ysPaperPlan.getMajorId());
		
		String singleData = ysPaperPlan.getSingleData();
		String manyData = ysPaperPlan.getManyData();
		String judgeData = ysPaperPlan.getJudgeData();
		String fillData = ysPaperPlan.getFillData();
		String simpleData = ysPaperPlan.getSimpleData();
		if(StringUtils.isNotBlank(singleData)){
			String[] split = singleData.split(",");
			ysPaperPlanPojo.setSingleQueNum(split[0]);
			ysPaperPlanPojo.setSingleDif(split[1]);
			ysPaperPlanPojo.setSingleScore(split[2]);
		}
		if(StringUtils.isNotBlank(manyData)){
			String[] split = manyData.split(",");
			ysPaperPlanPojo.setManyQueNum(split[0]);
			ysPaperPlanPojo.setManyDif(split[1]);
			ysPaperPlanPojo.setManyScore(split[2]);
		}
		if(StringUtils.isNotBlank(judgeData)){
			String[] split = judgeData.split(",");
			ysPaperPlanPojo.setJudgeQueNum(split[0]);
			ysPaperPlanPojo.setJudgeDif(split[1]);
			ysPaperPlanPojo.setJudgeScore(split[2]);
		}
		if(StringUtils.isNotBlank(fillData)){
			String[] split = fillData.split(",");
			ysPaperPlanPojo.setFillQueNum(split[0]);
			ysPaperPlanPojo.setFillDif(split[1]);
			ysPaperPlanPojo.setFillScore(split[2]);
		}
		if(StringUtils.isNotBlank(simpleData)){
			String[] split = simpleData.split(",");
			ysPaperPlanPojo.setSimpleQueNum(split[0]);
			ysPaperPlanPojo.setSimpleDif(split[1]);
			ysPaperPlanPojo.setSimpleScore(split[2]);
		}
		
		return ysPaperPlanPojo;
	}
	
	/**
	 *  YsPaperPlanPojo 转为 ysPaperPlan
	 */
	public static boolean planIsEmpty(YsPaperPlan ysPaperPlan){
		if(StringUtils.isEmpty(ysPaperPlan.getSingleData()) 
				&& StringUtils.isEmpty(ysPaperPlan.getManyData())
				&& StringUtils.isEmpty(ysPaperPlan.getJudgeData())
				&& StringUtils.isEmpty(ysPaperPlan.getFillData())
				&& StringUtils.isEmpty(ysPaperPlan.getSimpleData())){
			return true;
		}
		return false;
	}
	
	/**
	 * 拼接课件id
	 */
	public static String getCourseDiv(List<YsCourse> ysCourseList, String url){
		String course_list = "";
		course_list = "<div class='wrapper wrapper-content'><table id='contentTable' class='table table-striped table-bordered table-hover table-condensed dataTables-example dataTable'> <thead><tr><th> <input type='checkbox' class='i-checks'></th><th  class='sort-column id'>编号</th><th  class='sort-column courseName'>课件名称</th><th  class='sort-column courseType'>课件类型</th><th  class='sort-column courseClass'>课件分类</th><th  class='sort-column createId'>创建人</th><th  class='sort-column createTime'>最后更新</th><th>操作</th></tr></thead><tbody>";
		for (int i = 0; i < ysCourseList.size(); i++) {
			YsCourse ysCourse = ysCourseList.get(i);
			String courseClass = (ysCourse.getCourseClass()==1? "静态":"动态");
			SimpleDateFormat ft =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = ft.format(ysCourse.getUpdateTime());
			String ysMajorName = FnsUtils.getYsMajor(ysCourse.getCourseType());
			String userName = FnsUtils.getUserByUserId(ysCourse.getCreateId());
			
			course_list = course_list + "<tr class='course_tr'>" + 
					"<td> <input type='checkbox' id="+ysCourse.getId()+" class='i-checks course_checked'></td>" + 
					"<td>"+(i+1)+"</td><td>"+ysCourse.getCourseName()+"</td><td>"+ysMajorName+"</td>" + 
					"<td>"+courseClass+"</td><td>"+userName+"</td>" + 
					"<td>"+format+"</td>" + 
					"<td><a href='#' onclick='openDialog(\"编辑学习资料管理\", \""+url+"/course/addcourse/ysCourse/form?id="+ysCourse.getId()+"\",\"800px\", \"500px\")' class='btn btn-success btn-xs' ><i class='fa fa-edit'></i> 编辑</a>" + "&nbsp;&nbsp;" + 
					"<button onclick='del_course(this)' value='"+ysCourse.getId()+"' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i> 删除</button></td></tr>";
		}
		course_list = course_list + "</tbody></table></div>";
		return course_list;
	}
	
	/**
	 * 历史试卷分页
	 */
	public static String getMyPage(Page<YsPaper> page){
		
		int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		int first = page.getFirst();
		int last = page.getLast();
		int prev = page.getPrev();
		int next = page.getNext();
		String funcName = "pap_btn1";
		int length = 8;
		int slider = 1;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"fixed-table-pagination\" style=\"display: block;\">");
		
		
		
		
		sb.append("<div class=\"pull-right pagination-roll\">");
		sb.append("<ul class=\"pagination pagination-outline\">");
		if (pageNo == first) {// 如果是首页
			sb.append("<li class=\"paginate_button previous disabled\"><a href=\"javascript:\"><i class=\"fa fa-angle-double-left\"></i></a></li>\n");
			sb.append("<li class=\"paginate_button previous disabled\"><a href=\"javascript:\"><i class=\"fa fa-angle-left\"></i></a></li>\n");
		} else {
			sb.append("<li class=\"paginate_button previous\"><a href=\"javascript:\" onclick=\""+funcName+"("+first+","+pageSize+");\"><i class=\"fa fa-angle-double-left\"></i></a></li>\n");
			sb.append("<li class=\"paginate_button previous\"><a href=\"javascript:\" onclick=\""+funcName+"("+prev+","+pageSize+");\"><i class=\"fa fa-angle-left\"></i></a></li>\n");
		}

		int begin = pageNo - (length / 2);

		if (begin < first) {
			begin = first;
		}

		int end = begin + length - 1;

		if (end >= last) {
			end = last;
			begin = end - length + 1;
			if (begin < first) {
				begin = first;
			}
		}

		if (begin > first) {
			int i = 0;
			for (i = first; i < first + slider && i < begin; i++) {
				sb.append("<li class=\"paginate_button \"><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+");\">"
						+ (i + 1 - first) + "</a></li>\n");
			}
			if (i < begin) {
				sb.append("<li class=\"paginate_button disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == pageNo) {
				sb.append("<li class=\"paginate_button active\"><a href=\"javascript:\">" + (i + 1 - first)
						+ "</a></li>\n");
			} else {
				sb.append("<li class=\"paginate_button \"><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+");\">"
						+ (i + 1 - first) + "</a></li>\n");
			}
		}

		if (last - end > slider) {
			sb.append("<li class=\"paginate_button disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = last - slider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("<li class=\"paginate_button \"><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+");\">"
					+ (i + 1 - first) + "</a></li>\n");
		}

		if (pageNo == last) {
			sb.append("<li class=\"paginate_button next disabled\"><a href=\"javascript:\"><i class=\"fa fa-angle-right\"></i></a></li>\n");
			sb.append("<li class=\"paginate_button next disabled\"><a href=\"javascript:\"><i class=\"fa fa-angle-double-right\"></i></a></li>\n");
		} else {
			sb.append("<li class=\"paginate_button next\"><a href=\"javascript:\" onclick=\""+funcName+"("+next+","+pageSize+");\">"
					+ "<i class=\"fa fa-angle-right\"></i></a></li>\n");
			sb.append("<li class=\"paginate_button next\"><a href=\"javascript:\" onclick=\""+funcName+"("+last+","+pageSize+");\">"
					+ "<i class=\"fa fa-angle-double-right\"></i></a></li>\n");
		}

		
        sb.append("</ul>");
        sb.append("</div>");
        sb.append("</div>");
		
		return sb.toString();
	}
	
	/**
	 * 添加项目  考试试卷  查看 获取试题集合
	 */
	public static boolean getQuesionList(YsTestPojo ysTestPojo){
		// 获取试卷内容
	/*	Integer papId = ysTestPojo.getPapId();
		Integer planId = ysTestPojo.getPlanId();
		String ranIds = ysTestPojo.getRanIds();
		String queIds = ysTestPojo.getQueIds();
		if(papId != null && papId != 0){
			
		}else if(planId != null && planId != 0){
			
		}else{
			
		}*/
		
		return false;
	}
	
	/**
	 * 出卷计划  随机出题
	 */
	public static List<YsQuestions> getQuesionListByRandom(YsPaperPlan ysPaperPlan, YsPaperPlanService ysPaperPlanService, Map<Object,Object> map ){
		List<YsQuestions> ysYuesionList = new ArrayList<>();
		String singleData = ysPaperPlan.getSingleData();
		String manyData = ysPaperPlan.getManyData();
		String fillData = ysPaperPlan.getFillData();
		String judgeData = ysPaperPlan.getJudgeData();
		String simpleData = ysPaperPlan.getSimpleData();
		if(StringUtils.isNotBlank(singleData)){
			String[] split = singleData.split(",");
			map.put("questionType", 1);
			map.put("level", split[1]);
			// 获取符合条件的试题
			List<YsQuestions> list = ysPaperPlanService.findQuesionByRandom(map);
			// 随机抽取指定数量
			int[] randomCommon = randomCommon(0, list.size()-1, Integer.parseInt(split[0]));
			for (int i = 0; i < randomCommon.length; i++) {
				YsQuestions ysQuestions = list.get(randomCommon[i]);
				ysQuestions.setQueScore(Integer.parseInt(split[2]));
				ysYuesionList.add(ysQuestions); 
			}
		}
		
		if(StringUtils.isNotBlank(manyData)){
			String[] split = manyData.split(",");
			map.put("questionType", 2);
			map.put("level", split[1]);
			List<YsQuestions> list = ysPaperPlanService.findQuesionByRandom(map);
			// 随机抽取指定数量
			int[] randomCommon = randomCommon(0, list.size()-1, Integer.parseInt(split[0]));
			for (int i = 0; i < randomCommon.length; i++) {
				YsQuestions ysQuestions = list.get(randomCommon[i]);
				ysQuestions.setQueScore(Integer.parseInt(split[2]));
				ysYuesionList.add(ysQuestions); 
			}
		}
		
		if(StringUtils.isNotBlank(fillData)){
			String[] split = fillData.split(",");
			map.put("questionType", 3);
			map.put("level", split[1]);
			List<YsQuestions> list = ysPaperPlanService.findQuesionByRandom(map);
			// 随机抽取指定数量
			int[] randomCommon = randomCommon(0, list.size()-1, Integer.parseInt(split[0]));
			for (int i = 0; i < randomCommon.length; i++) {
				YsQuestions ysQuestions = list.get(randomCommon[i]);
				ysQuestions.setQueScore(Integer.parseInt(split[2]));
				ysYuesionList.add(ysQuestions); 
			}
		}
		
		if(StringUtils.isNotBlank(judgeData)){
			String[] split = judgeData.split(",");
			map.put("questionType", 4);
			map.put("level", split[1]);
			List<YsQuestions> list = ysPaperPlanService.findQuesionByRandom(map);
			// 随机抽取指定数量
			int[] randomCommon = randomCommon(0, list.size()-1, Integer.parseInt(split[0]));
			for (int i = 0; i < randomCommon.length; i++) {
				YsQuestions ysQuestions = list.get(randomCommon[i]);
				ysQuestions.setQueScore(Integer.parseInt(split[2]));
				ysYuesionList.add(ysQuestions); 
			}
		}
		
		if(StringUtils.isNotBlank(simpleData)){
			String[] split = simpleData.split(",");
			map.put("questionType", 5);
			map.put("level", split[1]);
			List<YsQuestions> list = ysPaperPlanService.findQuesionByRandom(map);
			// 随机抽取指定数量
			int[] randomCommon = randomCommon(0, list.size()-1, Integer.parseInt(split[0]));
			for (int i = 0; i < randomCommon.length; i++) {
				YsQuestions ysQuestions = list.get(randomCommon[i]);
				ysQuestions.setQueScore(Integer.parseInt(split[2]));
				ysYuesionList.add(ysQuestions); 
			}
		}
		return ysYuesionList;
	}
	
	/**
	 * 生成n个不重复随机数 
	 */
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1)) {  
	           n = max;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}
	
}
