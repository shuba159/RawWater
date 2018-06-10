<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考试管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.que_ans{
			display: block;
			margin-left:75px;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		
		.que_div{
			margin-left:300px;
		}
		
		.que_des{
			margin-left:50px;
		}
		
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<h2 align="center">${ysPaper.papName }</h2>
		<div class="que_div">
			<h3>一.单选题</h3>
			<c:forEach items="${ysQuestionsList }" var="ysQuestions" varStatus="ysList1">
				<c:if test="${ysQuestions.questionType == 1 }">
					<span class="que_des">${ysList1.index+1}.${ysQuestions.describes }(${ysQuestions.queScore }分)</span>
					<c:forEach items="${fn:split(ysQuestions.options,';')}" var="ans">
						<div class="que_ans"><input type="radio" name="${ysQuestions.id }">${ans }</div>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
		<div class="que_div">
			<h3>二.多选题</h3>
			<c:forEach items="${ysQuestionsList }" var="ysQuestions" varStatus="ysList2">
				<c:if test="${ysQuestions.questionType == 2 }">
					<span class="que_des">${ysList2.index+1}.${ysQuestions.describes }(${ysQuestions.queScore }分)</span>
					<c:forEach items="${fn:split(ysQuestions.options,';')}" var="ans">
						<div class="que_ans"><input type="checkbox">${ans }</div>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
		<div class="que_div">
			<h3>三.填空题</h3>
			<c:forEach items="${ysQuestionsList }" var="ysQuestions" varStatus="ysList3">
				<c:if test="${ysQuestions.questionType == 3 }">
					<span class="que_des">${ysList3.index+1}.${ysQuestions.describes }(${ysQuestions.queScore }分)</span>
					<div class="que_ans"><textarea rows="3" cols="60"></textarea></div>
				</c:if>
			</c:forEach>
		</div>
		<div class="que_div">
			<h3>四.判断题</h3>
			<c:forEach items="${ysQuestionsList }" var="ysQuestions" varStatus="ysList4">
				<c:if test="${ysQuestions.questionType == 4 }">
					<span class="que_des">${ysList4.index+1}.${ysQuestions.describes }(${ysQuestions.queScore }分)</span>
					<div class="que_ans"><input type="radio" name="${ysQuestions.id }" value=""> <span>正确</span></div>
					<div class="que_ans"><input type="radio" name="${ysQuestions.id }" value=""> <span>错误</span></div>
				</c:if>
			</c:forEach>
		</div>
		<div class="que_div">
			<h3>五.简答题</h3>
			<c:forEach items="${ysQuestionsList }" var="ysQuestions" varStatus="ysList5">
				<c:if test="${ysQuestions.questionType == 5 }">
					<span class="que_des">${ysList5.index+1}.${ysQuestions.describes }(${ysQuestions.queScore }分)</span>
					<div class="que_ans"><textarea rows="3" cols="60"></textarea></div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</body>
</html>