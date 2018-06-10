<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>题库管理管理</title>
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
	<script type="text/javascript">
	
	$(document).ready(function() { 
		function ls(a){
   		 if(a == 3){    // 3为填空题
                  $('#inp1').attr("disabled",false);   
                  $('#inp2').attr("disabled",false);    // <!--  为false是可以选取  -->
                  $('#inp3').attr("disabled",false);
                  $('#inp4').attr("disabled",true);
                  $('#options').attr("disabled",false);
                  $('#options').attr('placeholder',"请您输入填空题的答案，每个答案用回车隔开。");
                  $('#inp4').attr('placeholder'," ");
              }else if(a == 4){   // 4为判断题
           	   $('#inp1').attr("disabled",true);
                  $('#inp2').attr("disabled",true);  
                  $('#inp3').attr("disabled",true);
                  $('#inp4').attr("disabled",false);
                  $('#options').attr("disabled",true);
                  $('#options').attr('placeholder'," ");
                  $('#inp4').attr('placeholder',"请输入判断题正确答案，正确为A，错误为B");
              }else if(a == 5){  // 5为简答题
           	   $('#inp1').attr("disabled",true);
                  $('#inp2').attr("disabled",true);  
                  $('#inp3').attr("disabled",true);
                  $('#inp4').attr("disabled",true);
                  $('#options').attr("disabled",false);
                  $('#inp4').attr('placeholder'," ");
                  $('#options').attr('placeholder',"请输入简答题的答案");
              }else if(a == 1){    // 1为单选题
           	   $('#inp1').attr("disabled",true);   // <!--  为true是不可以选取  -->
                  $('#inp2').attr("disabled",true);  
                  $('#inp3').attr("disabled",true);
                  $('#inp4').attr("disabled",false);
                  $('#options').attr("disabled",false);
                  $('#options').attr('placeholder',"请您输入选择题的选项，每个选项用回车隔开。");
                  $('#inp4').attr('placeholder',"请您输入试题的正确答案选项，如：A");
              }else{
           	   $('#inp1').attr("disabled",true);   // <!--  为true是不可以选取  -->
                  $('#inp2').attr("disabled",true);  
                  $('#inp3').attr("disabled",true);
                  $('#inp4').attr("disabled",false);
                  $('#options').attr("disabled",false);
                  $('#inp4').attr('placeholder',"请您输入试题的正确答案选项，如：AB");
                  $('#options').attr('placeholder',"请您输入选择题的选项，每个选项用回车隔开。");
              }
   	};
            ls($("#sel1").val());
		});
	
	</script>
	<script type="text/javascript">
	    	
	    $(function(){
	    	function ls(a){
	      		 if(a == 3){    // 3为填空题
	                     $('#inp1').attr("disabled",false);   
	                     $('#inp2').attr("disabled",false);    // <!--  为false是可以选取  -->
	                     $('#inp3').attr("disabled",false);
	                     $('#inp4').attr("disabled",true);
	                     $('#options').attr("disabled",false);
	                     $('#options').attr('placeholder',"请您输入填空题的答案，每个答案用回车隔开。");
	                     $('#inp4').attr('placeholder'," ");
	                 }else if(a == 4){   // 4为判断题
	              	   $('#inp1').attr("disabled",true);
	                     $('#inp2').attr("disabled",true);  
	                     $('#inp3').attr("disabled",true);
	                     $('#inp4').attr("disabled",false);
	                     $('#options').attr("disabled",true);
	                     $('#options').attr('placeholder'," ");
	                     $('#inp4').attr('placeholder',"请输入判断题正确答案，正确为A，错误为B");
	                 }else if(a == 5){  // 5为简答题
	              	   $('#inp1').attr("disabled",true);
	                     $('#inp2').attr("disabled",true);  
	                     $('#inp3').attr("disabled",true);
	                     $('#inp4').attr("disabled",true);
	                     $('#options').attr("disabled",false);
	                     $('#inp4').attr('placeholder'," ");
	                     $('#options').attr('placeholder',"请输入简答题的答案");
	                 }else if(a == 1){    // 1为单选题
	              	   $('#inp1').attr("disabled",true);   // <!--  为true是不可以选取  -->
	                     $('#inp2').attr("disabled",true);  
	                     $('#inp3').attr("disabled",true);
	                     $('#inp4').attr("disabled",false);
	                     $('#options').attr("disabled",false);
	                     $('#options').attr('placeholder',"请您输入选择题的选项，每个选项用回车隔开。");
	                     $('#inp4').attr('placeholder',"请您输入试题的正确答案选项，如：A");
	                 }else{
	              	   $('#inp1').attr("disabled",true);   // <!--  为true是不可以选取  -->
	                     $('#inp2').attr("disabled",true);  
	                     $('#inp3').attr("disabled",true);
	                     $('#inp4').attr("disabled",false);
	                     $('#options').attr("disabled",false);
	                     $('#inp4').attr('placeholder',"请您输入试题的正确答案选项，如：AB");
	                     $('#options').attr('placeholder',"请您输入选择题的选项，每个选项用回车隔开。");
	                 }
	      	};
	        $("#sel1").click(function(){
	               
                var a = this.options[this.selectedIndex].value;    // 获取下拉框的值  
                ls(a);
	    })
	    })
	    
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="ysQuestions" action="${ctx}/questions/questions_as/ysQuestions/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题类型：</label></td>
					<td class="width-35">
						<form:select path="questionType" class="form-control required" id="sel1" >
							<form:option value="0" label=""/>
<%-- 							<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
							<form:option value="1" label="单选"/>
							<form:option value="2" label="多选"/>
							<form:option value="3" label="填空"/>
							<form:option value="4" label="判断"/>
							<form:option value="5" label="简答"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题难度：</label></td>
					<td class="width-35">
						<form:select path="level" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<%-- <td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题分类：</label></td>
					<td class="width-35">
						<form:select path="questionClass" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('question_class')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td> --%>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门：</label></td>
					<td class="width-35">
						<form:select path="depId" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('dep_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						
					</td>
				</tr>
				<!-- 试题状态，审批状态 -->
				<tr>
				<td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题状态：</label></td>
					<td class="width-35">
						<form:select path="questionState" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('question_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>审批状态：</label></td>
					<td class="width-35">
						<form:select path="ispath" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ispath')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<!--  -->
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>专业类型：</label></td>
					<td class="width-35">
                        <form:select path="majorType" class="form-control required">
							<form:option value="" label=""/>
							<c:forEach items="${list}" var="list">
								<form:option value="${list.id}" label="${list.majorName}"/>
							</c:forEach>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">判分规则一：</label></td>
					<td class="width-35">
						<form:select path="ruleOne" class="form-control" id="inp1">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('rule_one')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">判分规则二：</label></td>
					<td class="width-35">
						<form:select path="ruleTwo" class="form-control" id="inp2">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('rule_two')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">判分规则三：</label></td>
					<td class="width-35">
						<form:select path="ruleThree" class="form-control " id="inp3">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('rule_three')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题描述：</label></td>
					<td class="width-35">
						<form:textarea path="describes" htmlEscape="false" rows="4"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题正确答案：</label></td>
					<td class="width-35">
						<form:input path="answer" htmlEscape="false" rows="4"    class="form-control required" id="inp4" placeholder="请您输入试题的正确答案选项，如：A"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>试题选项/简答题答案：</label></td>
					<td class="width-35">
						<form:textarea path="options" htmlEscape="false" rows="4"    class="form-control required" id="options" placeholder="请您输入选择题的选项，每个选项用回车隔开。"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>答案解析：</label></td>
					<td class="width-35">
						<form:textarea path="analysis" htmlEscape="false" rows="4"    class="form-control required" placeholder="请您输入答案解析。"/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
	
<!-- 		<div class="layui-layer-btn">
			<a class="layui-layer-btn0"  onclick="doSubmit()">确定</a>
		</div>
	 -->
</body>
</html>