<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>论坛管理管理</title>
	<meta name="decorator" content="default"/>
	 <link href="${ctxStatic}/summernote/summernote.css" rel="stylesheet">
	 <link href="${ctxStatic}/summernote/summernote-bs3.css" rel="stylesheet">
	 <script src="${ctxStatic}/summernote/summernote.min.js"></script>
	 <script src="${ctxStatic}/summernote/summernote-zh-CN.js"></script>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#forumcontent").val($("#rich_forumcontent").next().find(".note-editable").html());//取富文本的值
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
			
				//富文本初始化
			$('#rich_forumcontent').summernote({
                lang: 'zh-CN'
            });

			$("#rich_forumcontent").next().find(".note-editable").html(  $("#forumcontent").val());

			$("#rich_forumcontent").next().find(".note-editable").html(  $("#rich_forumcontent").next().find(".note-editable").text());
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="ysForum" action="${ctx}/forum/ysForum/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>帖子标题：</label></td>
					<td class="width-35">
						<form:input path="forumTitle" htmlEscape="false"    class="form-control required"/>
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>帖子的内容：</label></td>
					<td class="width-35">
						<form:hidden path="forumcontent"/>
						<div id="rich_forumcontent">
                           

                        </div>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
	
		<div class="layui-layer-btn">
			<a class="layui-layer-btn0"  onclick="doSubmit()">点击发帖</a>
		</div>
	
</body>
</html>