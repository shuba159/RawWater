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
			width:290px;
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
			margin:20px 40px;
		}
		.ques_list span{
			display: inline-block;
			width:70px;
			height:36px;
			text-align: center;
			line-height: 36px;
			font-size: 14px;
			border:1px solid #999;
			border-radius: 10px;
		}
		.line{
			display: inline-block;
			width:88px;
			height:1px;
			background:#333;
			vertical-align: middle;
		}
		.ques_degree{
			width:50px !important;
			color:#fff;
			border:0 !important;
		}
		.bg_a{
			background: #ff6666;
		}
		.bg_b{
			background: #33cc99;
		}
		.bg_c{
			background: #9966cc;
		}
		.bg_d{
			background: #cccccc;
		}
	</style>
	<script type="text/javascript">
		
	</script>
</head>
<body class="hideScroll">
	<div class="modify_container">
		<div class="plan_container">
		<div class="plan_content">
			<label for="" class="plan_list">计划名称：<input type="text" name="planName" value="${ysPaperPlanPojo.planName }"></label>
			<label for="" class="plan_list">专业名称：<input type="text" name="majorId" value="${fns:getYsMajor(ysPaperPlan.majorId)}"></label>
			<label for="" class="plan_list">试卷总分：<input type="text" value="${ysPaperPlan.totalScore }" class="all_score">分</label>
		</div>
		<div class="question_score">
			<div class="ques_list">
				<span class="ques_type">单选题</span>
				<div class="line"></div>
				<c:if test="${ysPaperPlanPojo.singleDif == 1}">
					<span class="ques_degree bg_b">简单</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.singleDif == 2}">
					<span class="ques_degree bg_c">一般</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.singleDif == 3}">
					<span class="ques_degree bg_a">难</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.singleDif == null}">
					<span class="ques_degree bg_d">无</span>
				</c:if>
				<span class="ques_type">${ysPaperPlanPojo.singleQueNum == null? 0:ysPaperPlanPojo.singleQueNum}题</span>
				<span class="ques_type">${ysPaperPlan.singleScore }分</span>
			</div>
			<div class="ques_list">
				<span class="ques_type">多选题</span>
				<div class="line"></div>
				<c:if test="${ysPaperPlanPojo.manyDif == 1}">
					<span class="ques_degree bg_b">简单</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.manyDif == 2}">
					<span class="ques_degree bg_c">一般</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.manyDif == 3}">
					<span class="ques_degree bg_a">难</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.manyDif == null}">
					<span class="ques_degree bg_d">无</span>
				</c:if>
				<span class="ques_type">${ysPaperPlanPojo.manyQueNum == null? 0:ysPaperPlanPojo.manyQueNum}题</span>
				<span class="ques_type">${ysPaperPlan.manyScore }分</span>
			</div>
			<div class="ques_list">
				<span class="ques_type">判断题</span>
				<div class="line"></div>
				<c:if test="${ysPaperPlanPojo.judgeDif == 1}">
					<span class="ques_degree bg_b">简单</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.judgeDif == 2}">
					<span class="ques_degree bg_c">一般</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.judgeDif == 3}">
					<span class="ques_degree bg_a">难</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.judgeDif == null}">
					<span class="ques_degree bg_d">无</span>
				</c:if>
				<span class="ques_type">${ysPaperPlanPojo.judgeQueNum ==null? 0:ysPaperPlanPojo.judgeQueNum}题</span>
				<span class="ques_type">${ysPaperPlan.judgeScore }分</span>
			</div>
			<div class="ques_list">
				<span class="ques_type">填空题</span>
				<div class="line"></div>
				<c:if test="${ysPaperPlanPojo.fillDif == 1}">
					<span class="ques_degree bg_b">简单</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.fillDif == 2}">
					<span class="ques_degree bg_c">一般</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.fillDif == 3}">
					<span class="ques_degree bg_a">难</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.fillDif == null}">
					<span class="ques_degree bg_d">无</span>
				</c:if>
				<span class="ques_type">${ysPaperPlanPojo.fillQueNum == null? 0:ysPaperPlanPojo.fillQueNum}题</span>
				<span class="ques_type">${ysPaperPlan.fillScore }分</span>
			</div>
			<div class="ques_list">
				<span class="ques_type">简答题</span>
				<div class="line"></div>
				<c:if test="${ysPaperPlanPojo.simpleDif == 1}">
					<span class="ques_degree bg_b">简单</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.simpleDif == 2}">
					<span class="ques_degree bg_c">一般</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.simpleDif == 3}">
					<span class="ques_degree bg_a">难</span>
				</c:if>
				<c:if test="${ysPaperPlanPojo.simpleDif == null}">
					<span class="ques_degree bg_d">无</span>
				</c:if>
				<span class="ques_type">${ysPaperPlanPojo.simpleQueNum == null? 0:ysPaperPlanPojo.simpleQueNum }题</span>
				<span class="ques_type">${ysPaperPlan.simpleScore }分</span>
			</div>
		</div>
	</div>
	</div>
</body>
</html>