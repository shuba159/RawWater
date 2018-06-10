<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考试管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/myCss/ysProject.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/common/myJs/ysProject.js" type="text/javascript"></script>
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
			
			// 加载部门select
			var query = window.location.search.substring(1);
			query = decodeURI(query);
			if(query != "Nothing selected"){
				var depNameArr = query.split(", ");
				for (var i = 0; i < depNameArr.length; i++) {
					var option_dep = '<option value="'+depNameArr[i]+'" label="">'+depNameArr[i]+'</option>';
					$("#depNames").append(option_dep);
				}
			}
			
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
			
					laydate({
			            elem: '#startTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
					laydate({
			            elem: '#endTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="ysTestPojo" action="${ctx}/project/ysTest/testSave" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>考试名称：</label></td>
					<td class="width-35">
						<form:input path="ysTest.testName" htmlEscape="false" class="form-control required test_name"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>考试状态：</label></td>
					<td class="width-35">
						<form:select path="ysTest.testState" class="form-control required">
							<form:option value="1" label="启用"/>
							<form:option value="2" label="禁用"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>考试开始时间：</label></td>
					<td class="width-35">
						<input id="startTime" name="ysTest.startTime" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
							value="<fmt:formatDate value="${ysTest.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>考试结束时间：</label></td>
					<td class="width-35">
						<input id="endTime" name="ysTest.endTime" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
							value="<fmt:formatDate value="${ysTest.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>考试次数：</label></td>
					<td class="width-35">
						<form:input path="ysTest.testNumber" htmlEscape="false" class="form-control required test_num"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>答卷时长：</label></td>
					<td class="width-35">
						<form:input path="ysTest.testTime" htmlEscape="false"    class="form-control required test_long"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门：</label></td>
					<td class="width-35">
						<select id="depNames" name="ysTest.depNames" class="selectpicker form-control required" multiple>
							
						</select>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>考试须知：</label></td>
					<td class="width-65">
						<form:textarea path="ysTest.describes" htmlEscape="false" rows="3" class="form-control required"/>
					</td>
				</tr>
		 	</tbody>
		</table>
		<!-- 历史试卷id -->
		<input id="papId_input" name="papId" type="hidden">
		<!-- 出卷计划id -->
		<input id="planId_input" name="planId" type="hidden">
		<!-- 随机出卷 -->
		<input id="ran_input" name="ranIds" type="hidden">
		<!-- 选择出卷 -->
		<input id="que_input" name="queIds" type="hidden">
		<!-- YsTestPojo的id -->
		<input id="testPojoId_input" name="id" type="hidden">
		
	</form:form>
	<div class="ibox-content">
		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="add_choose()">添加试卷</button>
		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="pap_btn(1,5)">历史试卷</button>
	</div>
	
	<!-- 添加试题  -->
	<div id="que_div" class="ibox-content">
		<table id="contentTable" class="table table-bordered table-hover table-condensed dataTables-example dataTable">
			<thead>
				<tr>
					<th style="width: 35px">排序</th>
					<th style="width: 750px">试题描述</th>
					<th >分数/题</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="que_tbody">
				
			</tbody>
		</table>	
	</div>

	<!-- 历史试卷 -->
	<div id="pap_div" style="display: none;" class="ibox-content">
		
	</div>
	
	<script type="text/javascript">
	
		// 添加试题
		var i = 0;
		function add_choose(){
			$("#pap_div").hide();
			$("#que_div").show();
			i = i + 1;
			var value = '<tr><td style="width: 35px"></td><td><div class="form-inline col-sm-20"><input type="text" class="form-control" style="width: 500px"><button class="btn btn-white" onclick="openQuesion( '+
					"'选择试题', '${ctx}/project/ysTest/quesionList','1200px', '600px', this, 'que_score"+i+"')" + 
					'">选择试题</button><button onclick="openQuesion2( ' + 
					"'随机抽题', '${ctx}/project/ysTest/quesionRandom','400px', '300px', this" +
					'  )" class="btn btn-white">随机抽题</button><button onclick="openQuesion3( ' +
					"'随机出卷', '${ctx}/project/ysTest/paperRandom','300px', '200px', this" +
					' )" class="btn btn-white">随机出卷</button></div></td><td style="width: 80px"><input id="que_score'+i+'" type="text" onblur="que_score(this)" class="form-control" onkeyup="value=value.replace(/[^\\d]/g,\'\')"></td><td style="width: 55px"></td></tr>';
			$("#que_tbody").append(value);
		}
		
		function add_que(str, item){
			$(item).parent().parent().parent().after(str);
		}
		
		/* 设置分数 */
		function que_score(item){
			var val = $(item).val();
			var inp_class = '.' + $(item).attr('id');
			$(inp_class).val(val);
		}
		
		function add_que_ran(str, item){
			$(item).parent().parent().parent().after(str);
		}
		
		function add_que_plan(str, item){
			$(item).parent().parent().parent().after(str);
		}
		
		function del_que(item){
			var par = item.parentNode.parentNode.parentNode;
			var chi = item.parentNode.parentNode;
			par.removeChild(chi);
		}
		
		
		// 历史试卷
		function pap_btn(n,s){
			$("#que_div").hide();
			$("#pap_div").show();
			
			$.ajax({
	            type: "POST",
	            url: "${ctx}/project/ysTest/papList",
	            data: {"pageNo":n, "pageSize":s},
	            success: function(data){
	            	$("#pap_div").html(data);
	            }
	        });
		}
		
		// 历史试卷
		function pap_btn1(n,s){
			$.ajax({
	            type: "POST",
	            url: "${ctx}/project/ysTest/papList",
	            data: {"pageNo":n, "pageSize":s},
	            success: function(data){
	            	$("#pap_div").html(data);
	            }
	        });
		}
		
		// 历史试卷  复选框
		function pap_checked(item){
			$(".pap_checked").each(function() {     
                 $(this).attr("checked", false);    
           	});
			$(item).attr("checked", 'checked');
			 
		}
		
		function test_submit(ysTestPojoId){
			var que_id_arr = [];
			var que_ran_arr = [];
			var a = 0;
			var b = 0;
			var c = 0;
			if($("#pap_div").is(":hidden")){
				$(".que_input").each(function(){
					a = a + ($(this).val()-0);
				})
				$(".ran_input").each(function(){
					b = b + ($(this).val()-0) * ($(this).parent().prev().prev().find("input").eq(2).val()-0);
				})
				$(".plan_input").each(function(){
					c = c + ($(this).val()-0);
				})
				// 判断总分
				if((a+b+c) != 100){
					alert("试题总分为 100分")
					return false;
				}
				
				// 类型
				if($(".plan_input").length > 0){
					var plan_id = $(".plan_id").val();
					$("#planId_input").val(plan_id);
				}else{
					// 试题
					$(".que_id").each(function(){
						var id = $(this).val();
						var score = $(this).parent().next().next().find("input").eq(0).val();
						var str = id + ":" + score;
						que_id_arr.push(str);
					});
					// 随机
					$(".que_level").each(function(){
						var que_level = $(this).val();
						var que_type = $(this).next().val();
						var que_num = $(this).next().next().val();
						var score = $(this).parent().next().next().find("input").eq(0).val();
						var que_ran = que_level+ ':' + que_type + ':' + que_num + ':' + score;
						que_ran_arr.push(que_ran);
					});
					$("#que_input").val(que_id_arr.join(","));
					$("#ran_input").val(que_ran_arr.join(","));
				}
			}else{
				// 未隐藏
				$(".pap_checked").each(function() {     
	                 if($(this).attr('checked')){
	                	 var papId = $(this).attr('id');
	                	 $("#papId_input").val(papId);
	                 }    
	           	});
			}
			
			// 拼接
			var session_testId = '${empty sessionScope.sessionTestId? "1" : sessionScope.sessionTestId}';
			ysTestPojoId = ysTestPojoId + 1;
			var test_name = $(".test_name").val();
			var test_time = $("#startTime").val();
			var test_num = $(".test_num").val();
			var test_long = $(".test_long").val();
			var test_tr = "<tr class='test_tr'>" + 
				  "<td><input type='checkbox' class='i-checks ysTestPojoId test_checked' value='"+session_testId+"'></td>" + 
				  "<td class='td_num'>"+ysTestPojoId+"</td><td>"+test_name+"</td><td>"+test_time+"</td>" + 
				  "<td>"+test_num+"</td><td>"+test_long+"</td>" + 
				  "<td><a href='#' onclick='windowOpen(\"${ctx}/project/ysTest/showPaper1?id="+session_testId+"\", \"考试卷纸\", \"1300px\", \"700px\")' class='btn btn-success btn-xs' ><i class='fa fa-edit'></i> 查看</a>" + "&nbsp;&nbsp;" + 
				  "<button onclick='del_course(this)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i> 删除</button></td></tr>";
			$("#testPojoId_input").val(session_testId);
			return test_tr;
		}
		
	</script>
</body>
</html>