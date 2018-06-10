<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>出卷计划管理</title>
	<meta name="decorator" content="default"/>
		<style type="text/css">
		/*.plan_container{
			width:380px;
			height: auto;
			border:1px solid #ddd;
		}*/
		.plan_list{
			display: block;
			margin:20px 40px;
		}
		.plan_list input{
			width:390px;
			height:20px;
		}
		.plan_list select{
			width:293px;
			height:26px;
		}
		.all_score{
			width:266px !important;
			margin-right: 5px;
		}
		.ques_list{
			font-size: 14px;
			margin:20px 40px;
		}
		.ques_list input{
			vertical-align: middle;
		}
		.ques_list label{
			 margin-right:25px;
		}
		.ques_num{
			width:90px;
			height: 20px;
			margin-right: 5px;
		}
		.label_score{
			margin:0px 40px;
		}
		.score_total{
			color:#ddd;
			margin-left:10px;
		}
		.totalSocre
	</style>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  if($(".score_total_input").val() == 100){
				  $("#inputForm").submit();
				  return true;
			  }
			 
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$(".que_score").blur(function(){
				  var totalScore = 0;
				  $(".que_score").each(function(){
					  totalScore = totalScore + (($(this).val()-0)*($(this).parent().parent().find("label:eq(1)").children(":first").val()-0));
				  })
				  $(".score_total_input").val(totalScore);
			});
			$(".que_num").blur(function(){
				  var totalScore = 0;
				  $(".que_score").each(function(){
					  totalScore = totalScore + (($(this).val()-0)*($(this).parent().parent().find("label:eq(1)").children(":first").val()-0));
				  })
				  $(".score_total_input").val(totalScore);
			});
			
			
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="ysPaperPlanPojo" action="${ctx}/plan/ysPaperPlan/save" method="post" class="form-horizontal">
		<%-- <form:hidden path="id"/> --%>
		<sys:message content="${message}"/>	
		<div class="modify_container">
		<div class="plan_content">
			<label for="" class="plan_list">试卷名称：<input type="text" name="planName" value="${ysPaperPlanPojo.planName }" class="required"></label>
			<label for="" class="plan_list">试卷分类：
			<form:select path="majorId" id="">
				<c:forEach items="${fns:getYsMajorList()}" var="ysMajor">
					<form:option value="${ysMajor.id }" label="${ysMajor.majorName }"></form:option>
				</c:forEach>
			</form:select>
			</label>
		</div>
		<div class="question_score">
			<div class="ques_list">
				<label style="margin-right:14px"><input type="checkbox">单选题</label>
				<label><input type="text" name="singleQueNum" value="${ysPaperPlanPojo.singleQueNum }" class="ques_num que_num" onkeyup="value=value.replace(/[^\d]/g,'')">题数</label>
				<label><input type="radio" name="singleDif" value="3" ${ysPaperPlanPojo.singleDif == '3' ? 'checked="checked"' : '' }>难</label>
				<label><input type="radio" name="singleDif" value="1" ${ysPaperPlanPojo.singleDif == '1' ? 'checked="checked"' : '' }>简单</label>
				<label><input type="radio" name="singleDif" value="2" ${ysPaperPlanPojo.singleDif == '2' ? 'checked="checked"' : '' }>一般</label>
				<label><input type="text" name="singleScore" value="${ysPaperPlanPojo.singleScore }" class="ques_num que_score" onkeyup="value=value.replace(/[^\d]/g,'')">分数</label>
			</div>
			<div class="ques_list">
				<label style="margin-right:14px"><input type="checkbox">多选题</label>
				<label><input type="text" name="manyQueNum" value="${ysPaperPlanPojo.manyQueNum }" class="ques_num que_num" onkeyup="value=value.replace(/[^\d]/g,'')">题数</label>
				<label><input type="radio" name="manyDif" value="3" ${ysPaperPlanPojo.manyDif == '3' ? 'checked="checked"' : '' }>难</label>
				<label><input type="radio" name="manyDif" value="1" ${ysPaperPlanPojo.manyDif == '1' ? 'checked="checked"' : '' }>简单</label>
				<label><input type="radio" name="manyDif" value="2" ${ysPaperPlanPojo.manyDif == '2' ? 'checked="checked"' : '' }>一般</label>
				<label><input type="text" name="manyScore" value="${ysPaperPlanPojo.manyScore }" class="ques_num que_score" onkeyup="value=value.replace(/[^\d]/g,'')">分数</label>
			</div>
			<div class="ques_list">
				<label style="margin-right:14px"><input type="checkbox">判断题</label>
				<label><input type="text" name="judgeQueNum" value="${ysPaperPlanPojo.judgeQueNum }" class="ques_num que_num" onkeyup="value=value.replace(/[^\d]/g,'')">题数</label>
				<label><input type="radio" name="judgeDif" value="3" ${ysPaperPlanPojo.judgeDif == '3' ? 'checked="checked"' : '' }>难</label>
				<label><input type="radio" name="judgeDif" value="1" ${ysPaperPlanPojo.judgeDif == '1' ? 'checked="checked"' : '' }>简单</label>
				<label><input type="radio" name="judgeDif" value="2" ${ysPaperPlanPojo.judgeDif == '2' ? 'checked="checked"' : '' }>一般</label>
				<label><input type="text" name="judgeScore" value="${ysPaperPlanPojo.judgeScore }" class="ques_num que_score" onkeyup="value=value.replace(/[^\d]/g,'')">分数</label>
			</div>
			<div class="ques_list">
				<label style="margin-right:14px"><input type="checkbox">填空题</label>
				<label><input type="text" name="fillQueNum" value="${ysPaperPlanPojo.fillQueNum }" class="ques_num que_num" onkeyup="value=value.replace(/[^\d]/g,'')">题数</label>
				<label><input type="radio" name="fillDif" value="3" ${ysPaperPlanPojo.fillDif == '3' ? 'checked="checked"' : '' }>难</label>
				<label><input type="radio" name="fillDif" value="1" ${ysPaperPlanPojo.fillDif == '1' ? 'checked="checked"' : '' }>简单</label>
				<label><input type="radio" name="fillDif" value="2" ${ysPaperPlanPojo.fillDif == '2' ? 'checked="checked"' : '' }>一般</label>
				<label><input type="text" name="fillScore" value="${ysPaperPlanPojo.fillScore }" class="ques_num que_score" onkeyup="value=value.replace(/[^\d]/g,'')">分数</label>
			</div>
			<div class="ques_list">
				<label style="margin-right:14px"><input type="checkbox">简答题</label>
				<label><input type="text" name="simpleQueNum" value="${ysPaperPlanPojo.simpleQueNum }" class="ques_num que_num" onkeyup="value=value.replace(/[^\d]/g,'')">题数</label>
				<label><input type="radio" name="simpleDif" value="3" ${ysPaperPlanPojo.simpleDif == '3' ? 'checked="checked"' : '' }>难</label>
				<label><input type="radio" name="simpleDif" value="1" ${ysPaperPlanPojo.simpleDif == '1' ? 'checked="checked"' : '' }>简单</label>
				<label><input type="radio" name="simpleDif" value="2" ${ysPaperPlanPojo.simpleDif == '2' ? 'checked="checked"' : '' }>一般</label>
				<label><input type="text" name="simpleScore" value="${ysPaperPlanPojo.simpleScore }" class="ques_num que_score" onkeyup="value=value.replace(/[^\d]/g,'')">分数</label>
			</div>
			<label for="" class="label_score">总分：<input type="text" readonly="readonly" class="ques_num score_total_input" style="margin-left:33px">分<span class="score_total">*自动计算总分 总分100</span></label>
		</div>
	</div>
	</form:form>
</body>
</html>