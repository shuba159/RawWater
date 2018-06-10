<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>人工评卷</title>
<style type="text/css">
*{
  margin:0px;
  padding:0px;
}
hr{
  margin:15px 0px 5px 0px;
}
.spans{
font-size: 17px;
font-weight:bold;
}

.hr01 {
    height: 12px;
    border: 0;
    box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
}
</style>
<script type="text/javascript">
$(function(){
	var linshi = 0;
	$("#but01").click(function(){
		$(".inp").each(function(){
			linshi = linshi + Number($(this).val());
		});
		$("#ts").val(linshi);
		$("#form01").submit();
		alert("该卷分数为："+linshi+"分。 \r\n 判卷成功，请您关闭窗口！");
		$("#but01").attr("disabled",true);  
	})
	
})
</script>
</head>
<body style="background: #E5E5E5">
	<hr class="hr01"/>
<%--    试卷id:${papId}    --%>
<!-- ${userId} -->


<c:forEach items="${iasList}" var="iasList">
<%-- 	 ${iasList.integer}  题的id  --%>
	<div style="margin : 0px 0px 0px 100px;background: #FFFFFF;box-shadow: 10px 10px 5px #888888;">
		<span class="spans">答题类型：</span><span class="sp01">
		<c:if test="${fns:getIdSelectYsQuestions(iasList.integer).questionType == 1}">单选 <br/>
		<span class="spans">答题题目：</span>${fns:getIdSelectYsQuestions(iasList.integer).describes}<br/>
		<span class="spans">答题选项：</span>${fns:getIdSelectYsQuestions(iasList.integer).options} <br/>
		<span class="spans">正确答案：</span><span class="sp02${iasList.integer}">${fns:getIdSelectYsQuestions(iasList.integer).analysis}</span> <br/>
		<span class="spans">学员答案：</span><span class="sp03">${iasList.string}</span> <br/>
		<span class="spans">题目分数：</span><span class="sp04">${fns:getPapidAndQueidSelectScore(papId,iasList.integer)}</span> <br/>
		
		<span class="spans">教师给分：</span><input readonly="readonly" class="inp" type="text" value="${iasList.string == fns:getIdSelectYsQuestions(iasList.integer).analysis ? fns:getPapidAndQueidSelectScore(papId,iasList.integer):'0' }" placeholder="教师给分不能超过提题目分数">
		</c:if>
		<c:if test="${fns:getIdSelectYsQuestions(iasList.integer).questionType == 2}">多选 <br/>
		<span class="spans">答题题目：</span>${fns:getIdSelectYsQuestions(iasList.integer).describes}<br/>
		<span class="spans">答题选项：</span>${fns:getIdSelectYsQuestions(iasList.integer).options} <br/>
		<span class="spans">正确答案：</span><span class="sp02${iasList.integer}">${fns:getIdSelectYsQuestions(iasList.integer).analysis}</span> <br/>
		<span class="spans">学员答案：</span><span class="sp03">${iasList.string}</span> <br/>
		<span class="spans">题目分数：</span><span class="sp04">${fns:getPapidAndQueidSelectScore(papId,iasList.integer)}</span> <br/>
		
		<span class="spans">教师给分：</span><input readonly="readonly" class="inp" type="text" value="${iasList.string == fns:getIdSelectYsQuestions(iasList.integer).analysis ? fns:getPapidAndQueidSelectScore(papId,iasList.integer):'0' }" placeholder="教师给分不能超过提题目分数">
		</c:if>
		<c:if test="${fns:getIdSelectYsQuestions(iasList.integer).questionType == 3}">填空 <br/>
		<span class="spans">答题题目：</span>${fns:getIdSelectYsQuestions(iasList.integer).describes}<br/>
		<span class="spans">答题选项：</span>${fns:getIdSelectYsQuestions(iasList.integer).options} <br/>
		<span class="spans">正确答案：</span><span class="sp02${iasList.integer}">${fns:getIdSelectYsQuestions(iasList.integer).analysis}</span> <br/>
		<span class="spans">学员答案：</span><span class="sp03">${iasList.string}</span> <br/>
		<span class="spans">题目分数：</span><span class="sp04">${fns:getPapidAndQueidSelectScore(papId,iasList.integer)}</span> <br/>
		
		<span class="spans">教师给分：</span><input  class="inp" type="text" value="${iasList.string == fns:getIdSelectYsQuestions(iasList.integer).analysis ? fns:getPapidAndQueidSelectScore(papId,iasList.integer):'0' }" placeholder="教师给分不能超过提题目分数">
		</c:if>
		<c:if test="${fns:getIdSelectYsQuestions(iasList.integer).questionType == 4}">判断 <br/>
		<span class="spans">答题题目：</span>${fns:getIdSelectYsQuestions(iasList.integer).describes}<br/>
		<span class="spans">答题选项：</span>${fns:getIdSelectYsQuestions(iasList.integer).options} <br/>
		<span class="spans">正确答案：</span><span class="sp02${iasList.integer}">${fns:getIdSelectYsQuestions(iasList.integer).analysis}</span> <br/>
		<span class="spans">学员答案：</span><span class="sp03">${iasList.string}</span> <br/>
		<span class="spans">题目分数：</span><span class="sp04">${fns:getPapidAndQueidSelectScore(papId,iasList.integer)}</span> <br/>
		
		<span class="spans">教师给分：</span><input readonly="readonly" class="inp" type="text" value="${iasList.string == fns:getIdSelectYsQuestions(iasList.integer).analysis ? fns:getPapidAndQueidSelectScore(papId,iasList.integer):'0' }" placeholder="教师给分不能超过提题目分数">
		</c:if>
		<c:if test="${fns:getIdSelectYsQuestions(iasList.integer).questionType == 5}">简答 <br/>
		<span class="spans">答题题目：</span>${fns:getIdSelectYsQuestions(iasList.integer).describes}<br/>
		<span class="spans">答题选项：</span>${fns:getIdSelectYsQuestions(iasList.integer).options} <br/>
		<span class="spans">正确答案：</span><span class="sp02${iasList.integer}">${fns:getIdSelectYsQuestions(iasList.integer).analysis}</span> <br/>
		<span class="spans">学员答案：</span><span class="sp03">${iasList.string}</span> <br/>
		<span class="spans">题目分数：</span><span class="sp04">${fns:getPapidAndQueidSelectScore(papId,iasList.integer)}</span> <br/>
		
		<span class="spans">教师给分：</span><input class="inp" type="text" value="${iasList.string == fns:getIdSelectYsQuestions(iasList.integer).analysis ? fns:getPapidAndQueidSelectScore(papId,iasList.integer):'0' }" placeholder="教师给分不能超过提题目分数">
		</c:if>
		</span>
		<br/>
		
	</div>
	<hr/>
</c:forEach>

<form action="${ctx}/project/ysTestScore/ysPaperClose" method="post" id="form01">
	<input type="hidden" name="papId2" value="${papId}">
	<input type="hidden" name="userId2" value="${userId}">
	<input type="hidden" name="testScore" id="ts">
</form>
<button id="but01" style="float:right;margin : 0px 30px 0px 0px;">点击提交</button> 
<hr class="hr01"/>
</body>
</html>