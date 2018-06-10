<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考试管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function getQuePlanDiv(){
			var pap_plan_id = $("#pap_plan").val();
			var pap_plan_name = $("#pap_plan").find("option:selected").attr("label");
			var str = '<tr><td style="width: 35px"><input class="plan_id" type="hidden" value="'+pap_plan_id+'"></td><td>出卷计划: '+pap_plan_name+'</td><td ><input type="text" class="form-control plan_input" value="100"></td><td><button onclick="del_que(this)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</button></td></tr>';
			return str;
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="">
			<span>出卷计划：</span>
				<select id="pap_plan" class="form-control required">
					<c:forEach items="${paperPlanList }" var="papPlan">
						<option value="${papPlan.id }" label="${papPlan.planName }"/>
					</c:forEach>
				</select>
		</div>	
	</div>
</body>
</html>