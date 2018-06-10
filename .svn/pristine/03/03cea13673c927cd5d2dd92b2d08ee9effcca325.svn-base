/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.questions.web.questions_add;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;

import com.jeeplus.modules.entity.YsMajor;
import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.entity.YsUser;
import com.jeeplus.modules.major.dao.majro_ad.YsMajorDao;
import com.jeeplus.modules.myscore.entity.YsMyScore;
import com.jeeplus.modules.myscore.service.YsMyScoreService;
import com.jeeplus.modules.questions.dao.questions_add.YsQuestionsAddDao;
import com.jeeplus.modules.questions.entity.questions_add.Question;
import com.jeeplus.modules.questions.service.questions_as.YsQuestionsService;
import com.jeeplus.modules.stuscore.entity.YsStuPaper;
import com.jeeplus.modules.stuscore.service.YsStuPaperService;

import groovy.json.StringEscapeUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 添加题库Controller
 * 
 * @author hjm
 * @version 2018-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/questions/questions_add/ysQuestions")
public class YsQuestionsAddController extends BaseController {

	@Autowired
	private YsQuestionsService ysQuestionsService;
	@Autowired
	private YsMajorDao ysMajorDao;
	@Autowired
	private YsQuestionsAddDao ysQuestionsAddDao;
	@Autowired
	private YsStuPaperService ysStuPaperService;
	@Autowired
	private YsMyScoreService ysMyScoreService;

	@ModelAttribute
	public YsQuestions get(@RequestParam(required = false) String id) {
		YsQuestions entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ysQuestionsService.get(id);
		}
		if (entity == null) {
			entity = new YsQuestions();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/SubmitRanPaper")
	@ResponseBody
	public Map<String, Object> SubmitRanPaper(String anow, String userid) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		String shijianchuo;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		try {
			date1 = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long ts = date1.getTime();
		shijianchuo = String.valueOf(ts);
		System.out.println(shijianchuo);
		int score = 100;
		int cuoti = 0;
		// String json =
		// "[{'id':'1','answ':'A'},{'id':'2','answ':'B'},{'id':'3','answ':'A'}]";
		JSONArray jsonarray = JSONArray.fromObject(anow);

		System.out.println(jsonarray);
		List list = (List) JSONArray.toCollection(jsonarray, Question.class);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Question p = (Question) it.next();
			System.out.println("题id" + p.getId());
			YsQuestions yq = ysQuestionsAddDao.selectRanPaper(p.getId());
			String answer = yq.getAnswer(); // 拿出答案
			System.out.println("正确答案" + answer + "试卷答案" + p.getAnsw());
			boolean a = answer.equals(p.getAnsw());
			System.out.println("是否正确" + a);
			if (a == false) {
				score = score - 2;
				cuoti = cuoti + 1;
				YsStuPaper ysStuPaper = new YsStuPaper();
				ysStuPaper.setQueId(p.getId());
				ysStuPaper.setUserid(userid);
				ysStuPaper.setDepId(yq.getDepId().toString());
				ysStuPaper.setClassId(yq.getMajorType().toString());
				ysStuPaper.setTypeId(shijianchuo);
				ysStuPaper.setAnswer(date);
				ysStuPaperService.save(ysStuPaper);
			}
		}
		System.out.println("总题数为" + 50);
		System.out.println("分数为" + score);
		cuoti = (100 - score) / 2;
		int zhengjue = 50 - cuoti;
		System.out.println("错题述为" + cuoti);
		System.out.println("正确" + zhengjue);
		YsMyScore ysMyScore = new YsMyScore();
		ysMyScore.setUserid(1);
		ysMyScore.setPapId(shijianchuo);
		ysMyScore.setTestTime(date);
		ysMyScore.setTotalScore(100);
		ysMyScore.setPathScore(70);
		ysMyScore.setResultScore(score);
		ysMyScore.setErrorNumber(cuoti);
		ysMyScoreService.save(ysMyScore);
		ysMyScore.setYesNumber(zhengjue);
		jsonMap.put("code", 200);
		jsonMap.put("msg", "考试成功");
		jsonMap.put("data", ysMyScore);
		return jsonMap;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		/*
		 * // String jsonStr =
		 * "{\"weight\":51.3,\"name\":\"露西\",\"age\":26,\"gender\":\"female\",\"grades\":\"三班\"}";
		 * String jsonStr="{1:A,2:B,3:A}";
		 * 
		 * String newJson = StringEscapeUtils.unescapeJava(jsonStr);
		 * System.out.println(newJson); Map<String, Object> map = new
		 * HashMap<String, Object>(); JSONObject jsonObject =
		 * JSONObject.fromObject(jsonStr); Iterator<String> keys =
		 * jsonObject.keys();//定义迭代器 String key = null; Object value = null;
		 * while(keys.hasNext()){ key = keys.next(); value =
		 * jsonObject.get(key); map.put(key, value); } for (String key1 :
		 * map.keySet()) {
		 * 
		 * 
		 * System.out.println("Key = " + key1+":"+ map.get(key1)); } for (Object
		 * keys2 : map.values()) { System.out.println("Key = " + keys2); }
		 * 
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"
		 * );//设置日期格式 String date = df.format(new Date());// new
		 * Date()为获取当前系统时间，也可使用当前时间戳 String res; SimpleDateFormat
		 * simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date
		 * date1 = null; try { date1 = simpleDateFormat.parse(date); } catch
		 * (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } long ts = date1.getTime(); res =
		 * String.valueOf(ts); System.out.println(res);
		 */
		/*
		 * String jsonStr="{"name":"tom","sex":"男","age":"24"}"; JSONArray json
		 * = JSONArray.fromObject(jsonStr);//userStr是json字符串 List<Question>
		 * users= (List<Question>)JSONArray.toCollection(json, Question.class);
		 * JSONObject object = JSONObject.fromObject(jsonStr);
		 * System.out.println(object);
		 */
		String json = "[{'id':'1','answ':'A'},{'id':'3','answ':'C'}]";
		JSONArray jsonarray = JSONArray.fromObject(json);
		System.out.println(jsonarray);
		List list = (List) JSONArray.toCollection(jsonarray, Question.class);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Question p = (Question) it.next();
			System.out.println(p.getId());
		}

		//
	}

	private String showUserList(List<YsUser> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/RandYsYsQuestions")
	@ResponseBody
	public Map<String, Object> RandYsYsQuestions(HttpServletRequest request, HttpServletResponse response, String depId,
			String majorType) {
		System.out.println("部门"+depId+"专业类型"+majorType);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int a = ysQuestionsAddDao.selectYsQuestionsCount(depId, majorType);
		if (a < 50) {
			jsonMap.put("code", 400);
			jsonMap.put("msg", "该专业和部门单选和多选不足50题，请添加题库");

			return jsonMap;
		} else {
			List<YsQuestions> listYsQuestions = ysQuestionsAddDao.RandYsYsQuestionsDan(depId, majorType);
			System.out.println("单选题的数量" + listYsQuestions.size());
			while (listYsQuestions.size() != 50) {
				listYsQuestions = ysQuestionsAddDao.RandYsYsQuestionsDan(depId, majorType);
				System.out.println("单选题的数量1" + listYsQuestions.size());

			}
			jsonMap.put("code", 200);
			jsonMap.put("msg", "抽题成功");
			jsonMap.put("data", listYsQuestions);
			jsonMap.put("cout", listYsQuestions.size());
			// jsonMap.put("msg", listYsQuestionsduo);
			return jsonMap;

		}

		// List<YsQuestions> listYsQuestionsduo =
		// ysQuestionsAddDao.RandYsYsQuestionsDuo();

		// System.out.println("多选题的数量1" + listYsQuestionsduo.size());
		/*
		 * while (listYsQuestions.size() != 10) { listYsQuestionsduo =
		 * ysQuestionsAddDao.RandYsYsQuestionsDuo(); System.out.println("多选题的数量"
		 * + listYsQuestionsduo.size()); }
		 */
		/*
		 * System.out.println("单选题的数量1" + listYsQuestions.size());
		 * List<YsQuestions> listYsQuestionsduo =
		 * ysQuestionsAddDao.RandYsYsQuestionsDuo();
		 */
	}

	/**
	 * 题库管理列表页面
	 */
	@RequiresPermissions("questions:questions_as:ysQuestions:list")
	@RequestMapping(value = { "list", "" })
	public String list(YsQuestions ysQuestions, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsQuestions> page = ysQuestionsService.findPage(new Page<YsQuestions>(request, response), ysQuestions);
		List<YsMajor> list = ysMajorDao.YsMajorSelectAll();
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "modules/questions/questions_add/ysQuestionsForm"; // 这里直接跳转form表单

		// return "modules/questions/questions_add/ysQuestionsList";
	}

	/**
	 * 查看，增加，编辑题库管理表单页面
	 */
	@RequiresPermissions(value = { "questions:questions_as:ysQuestions:view", "questions:questions_as:ysQuestions:add",
			"questions:questions_as:ysQuestions:edit" }, logical = Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsQuestions ysQuestions, Model model) {
		List<YsMajor> list = ysMajorDao.YsMajorSelectAll(); // 查询专业类型里面的值

		model.addAttribute("list", list);
		model.addAttribute("ysQuestions", ysQuestions);

		return "modules/questions/questions_add/ysQuestionsForm";
	}

	@RequiresPermissions(value={"questions:questions_as:ysQuestions:add","questions:questions_as:ysQuestions:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsQuestions ysQuestions, Model model, RedirectAttributes redirectAttributes) throws Exception{
		
		ysQuestions.setQuestionClass(1);  // 试题分类已经删除，但是要来一个假数据省着改动太多
		ysQuestions.setQuestionState(3);  // 试题状态默认为3，待审核
		ysQuestions.setIspath(2);  // 审批状态默认为2，未审批
		ysQuestions.setCreateId(1);  // 创始人，默认为1，后期可能改一下
		java.util.Date date = new java.util.Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());
		ysQuestions.setUpdateTime(timeStamp);  // 修改日期，为添加的时间
		
		int i = ysQuestions.getQuestionType();
		if (i==1 || i==2) {
			String ss=ysQuestions.getOptions();
			System.out.println(ss);
			ss= ss.replace("\r", ";");    // 将回车替换成;
			ysQuestions.setOptions(ss);
		}else if(i == 3){
			ysQuestions.setAnswer(" ");
			String ss=ysQuestions.getOptions();
			System.out.println(ss);
			ss= ss.replace("\r", ";");    // 将回车替换成;
			ysQuestions.setOptions(ss);
		}else if(i == 4){
			ysQuestions.setOptions(" ");
		}else if(i == 5){
			ysQuestions.setAnswer(" ");
		}
		System.out.println(ysQuestions.toString());
		
	    if (!beanValidator(model, ysQuestions)){
			return form(ysQuestions, model);
		}
		if(!ysQuestions.getIsNewRecord()){//编辑表单保存
			YsQuestions t = ysQuestionsService.get(ysQuestions.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysQuestions, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysQuestionsService.save(t);//保存
		}else{//新增表单保存
			ysQuestionsService.save(ysQuestions);//保存
		}
		addMessage(redirectAttributes, "保存题库管理成功");
		return "redirect:"+Global.getAdminPath()+"/questions/questions_add/ysQuestions";
	}


	/**
	 * 删除题库管理
	 */
	/*
	 * @RequiresPermissions("questions:questions_as:ysQuestions:del")
	 * 
	 * @RequestMapping(value = "delete") public String delete(YsQuestions
	 * ysQuestions, RedirectAttributes redirectAttributes) {
	 * ysQuestionsService.delete(ysQuestions); addMessage(redirectAttributes,
	 * "删除题库管理成功"); return "redirect:"+Global.getAdminPath()+
	 * "/questions/questions_as/ysQuestions/?repage"; }
	 * 
	 *//**
		 * 批量删除题库管理
		 */
	/*
	 * @RequiresPermissions("questions:questions_as:ysQuestions:del")
	 * 
	 * @RequestMapping(value = "deleteAll") public String deleteAll(String ids,
	 * RedirectAttributes redirectAttributes) { String idArray[]
	 * =ids.split(","); for(String id : idArray){
	 * ysQuestionsService.delete(ysQuestionsService.get(id)); }
	 * addMessage(redirectAttributes, "删除题库管理成功"); return
	 * "redirect:"+Global.getAdminPath()+
	 * "/questions/questions_as/ysQuestions/?repage"; }
	 * 
	 *//**
		 * 导出excel文件
		 */
	/*
	 * @RequiresPermissions("questions:questions_as:ysQuestions:export")
	 * 
	 * @RequestMapping(value = "export", method=RequestMethod.POST) public
	 * String exportFile(YsQuestions ysQuestions, HttpServletRequest request,
	 * HttpServletResponse response, RedirectAttributes redirectAttributes) {
	 * try { String fileName =
	 * "题库管理"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx"; Page<YsQuestions>
	 * page = ysQuestionsService.findPage(new Page<YsQuestions>(request,
	 * response, -1), ysQuestions); new ExportExcel("题库管理",
	 * YsQuestions.class).setDataList(page.getList()).write(response,
	 * fileName).dispose(); return null; } catch (Exception e) {
	 * addMessage(redirectAttributes, "导出题库管理记录失败！失败信息："+e.getMessage()); }
	 * return "redirect:"+Global.getAdminPath()+
	 * "/questions/questions_as/ysQuestions/?repage"; }
	 * 
	 *//**
		 * 导入Excel数据
		 * 
		 */
	/*
	 * @RequiresPermissions("questions:questions_as:ysQuestions:import")
	 * 
	 * @RequestMapping(value = "import", method=RequestMethod.POST) public
	 * String importFile(MultipartFile file, RedirectAttributes
	 * redirectAttributes) { try { int successNum = 0; int failureNum = 0;
	 * StringBuilder failureMsg = new StringBuilder(); ImportExcel ei = new
	 * ImportExcel(file, 1, 0); List<YsQuestions> list =
	 * ei.getDataList(YsQuestions.class); for (YsQuestions ysQuestions : list){
	 * try{ ysQuestionsService.save(ysQuestions); successNum++;
	 * }catch(ConstraintViolationException ex){ failureNum++; }catch (Exception
	 * ex) { failureNum++; } } if (failureNum>0){ failureMsg.insert(0, "，失败 "
	 * +failureNum+" 条题库管理记录。"); } addMessage(redirectAttributes, "已成功导入 "
	 * +successNum+" 条题库管理记录"+failureMsg); } catch (Exception e) {
	 * addMessage(redirectAttributes, "导入题库管理失败！失败信息："+e.getMessage()); } return
	 * "redirect:"+Global.getAdminPath()+
	 * "/questions/questions_as/ysQuestions/?repage"; }
	 * 
	 *//**
		 * 下载导入题库管理数据模板
		 *//*
		 * @RequiresPermissions("questions:questions_as:ysQuestions:import")
		 * 
		 * @RequestMapping(value = "import/template") public String
		 * importFileTemplate(HttpServletResponse response, RedirectAttributes
		 * redirectAttributes) { try { String fileName = "题库管理数据导入模板.xlsx";
		 * List<YsQuestions> list = Lists.newArrayList(); new
		 * ExportExcel("题库管理数据", YsQuestions.class,
		 * 1).setDataList(list).write(response, fileName).dispose(); return
		 * null; } catch (Exception e) { addMessage(redirectAttributes,
		 * "导入模板下载失败！失败信息："+e.getMessage()); } return
		 * "redirect:"+Global.getAdminPath()+
		 * "/questions/questions_as/ysQuestions/?repage"; }
		 * 
		 * 
		 */

}