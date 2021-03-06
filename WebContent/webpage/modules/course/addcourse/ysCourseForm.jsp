<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>学习资料管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
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
		});
		
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="ysCourse" action="${ctx}/course/addcourse/ysCourse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>课件名称：</label></td>
					<td class="width-35">
						<form:input path="courseName" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门：</label></td>
					<td class="width-35">
						<form:select path="depId" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('dep_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>课件类型：</label></td>
					<td class="width-35">
						<form:select path="courseType" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('course_class')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>文件路径：</label></td>
					<td class="width-35">
						<form:hidden id="address" path="address" htmlEscape="false" maxlength="4000" class="form-control"/>
						<sys:ckfinder input="address" type="files" uploadPath="/course/addcourse/ysCourse" selectMultiple="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>课件分类：</label></td>
					<td class="width-35">
						<form:select path="courseClass" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('course_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>课件讲义：</label></td>
					<td class="width-35">
						<form:textarea path="describeA" htmlEscape="false" rows="4"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">创建人：</label></td>
					<td class="width-35">
						<form:input path="createId" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>专业类型：</label></td>
					<td class="width-35">
						<form:select path="majorType" class="form-control required">
							<form:option value="" label=""/>
							<%-- <form:options items="${fns:getDictList('major_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
							<c:forEach items="${list}" var="list">
								<form:option value="${list.id}" label="${list.majorName}"/>
							</c:forEach>
						</form:select>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>