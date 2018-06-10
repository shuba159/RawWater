<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/myCss/ysProject.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/common/myJs/ysProject.js" type="text/javascript"></script>
	<script type="text/javascript">
		// 考试课件参数
		var ysTestPojoId = 0;
	
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  // 获取课件和考试id集合  赋值给表单
			  courseIdsAndtestIds();
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
		
			//
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
			
			
			//时间插件
			//外部js调用
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
		<form:form id="inputForm" modelAttribute="ysProject" action="${ctx}/project/ysProject/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>项目名称：</label></td>
					<td class="width-35">
						<form:input path="proName" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>项目分类：</label></td>
					<td class="width-35">
						<form:select path="proClassId" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getYsProClassList()}" itemLabel="className" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>创建人：</label></td>
					<td class="width-35">
						<form:input path="createName" htmlEscape="false"    class="form-control required"/>
					</td>
		   			<td class="width-15 active"><label class="pull-right"><font color="red">*</font>学分：</label></td>
					<td class="width-35">
						<form:input path="totalScore" htmlEscape="false"    class="form-control required"/>
					</td>
		  		</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>培训时间：</label></td>
					<td class="width-35">
						<input id="startTime" name="startTime" type="text" maxlength="20" class="laydate-icon form-inline layer-date input-sm"   
			    		value="<fmt:formatDate value="${startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
						<label>&nbsp;~&nbsp;</label>
						<input id="endTime" name="endTime" type="text" maxlength="20" class="laydate-icon form-inline layer-date input-sm"   
			    		value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>所属部门：</label></td>
					<td class="width-35">
						<select id="depIds" name="depIds" class="selectpicker form-control required" multiple>
							<c:forEach items="${fns:getYsDepartmentList()}" var="dep" >
								<option value="${dep.id }" label="${dep.depName }">${dep.depName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>培训学员：</label></td>
					<td class="width-35">
						<input id="strNames" name="" value="" class="form-control" readonly="readonly"/>
						<input id="strIds" name="userIds" type="hidden" value="">
						<button id="btn_add" class="btn btn-primary" >添加</button>
						<button id="btn_remove" class="btn btn-primary" >清空</button>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>进修选项：</label></td>
					<td class="width-35">
						<input name="courseType" type="radio" checked="checked" value="1" class="i-checks"/>必修
						<input name="courseType" type="radio" checked="checked" value="2" class="i-checks"/>选修
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>课程目标：</label></td>
					<td class="width-35">
						<form:textarea path="goal"  htmlEscape="false" rows="4" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>课程介绍：</label></td>
					<td class="width-35">
						<form:textarea path="introduce" htmlEscape="false" rows="4"    class="form-control required"/>
					</td>
				</tr>
		 	</tbody>
		 </table>
		 <!-- 课件ids -->
		 <input type="hidden" id="courseIds" name="courseIds">
		 <!-- 考试ids -->
		 <input type="hidden" id="testIds" name="testIds">
	</form:form>
		<hr>
	<br>
	<h3>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="openCourse('查看课程', '${ctx}/course/addcourse/ysCourse?Id=${ysProject.id}','1200px', '600px')" title="培训课件">培训课件</button>
		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="openTest('查看考试', '${ctx}/project/ysTest/addForm','1100px', '600px','_self')" title="考试试卷">考试试卷</button>
	</h3>
	<div >
	
	</div>
		
	<!-- 学员列表 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLabel">学员通讯录</h4>
            	</div>
				<div class="ibox-content">
					<input class="form-control required" placeholder="输入关键字进行过滤"/>			
				</div>
  
               <div class="modal-body">
                    <div class="form-group">
                        <div class="ibox-content">
						<ul id="" class="list-unstyled">
						<c:forEach items="${fns:getYsDepartmentList()}" var="ysDep" varStatus="ysDepList">
							<li class="depLi" style="list-style-type:none;font-size:16px;margin:10px auto" value="${ysDep.id }">
							<span id="caretA" class="caretR"  style="margin:auto 5px" ></span><input value="${ysDep.id }" class="checkAll" type="checkbox"> ${ysDep.depName }</li>
								<ul id="" class="user${ysDep.id }" class="list-unstyled" style="display:none">
								<c:forEach items="${fns:getYsUserListByDepId(ysDep.id) }" var="ysUser" varStatus="ysUserList">
									<li class="" style="list-style-type:none;font-size:14px;margin:10px 20px"><input class="ysChecks${ysDep.id } ysChecks" value="${ysUser.id }" type="checkbox" ><input type="hidden" value="${ysUser.fullName }"> ${ysUser.fullName }</li>
								</c:forEach>
								</ul>
						</c:forEach>
						</ul>
					</div>
                    </div>
        	   	</div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                   <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
               </div>
           </div>
        </div>
    </div>
   <!-- 培训课件列表 -->
   <div class="course_list">
   
   </div>
   <!-- 培训考试列表 -->
   <div class="test_list">
	 	<div class='wrapper wrapper-content'>
	 		<table id='contentTable' class='table table-striped table-bordered table-hover table-condensed dataTables-example dataTable'> 
	 			<thead>
	 				<tr><th> <input type='checkbox' class=''>
	 					</th><th  class='sort-column id'>编号</th>
	 					<th  class='sort-column courseName'>考试名称</th>
	 					<th  class='sort-column courseType'>开始时间</th>
	 					<th  class='sort-column courseClass'>考试次数</th>
	 					<th  class='sort-column createId'>答卷时长</th>
	 					<th>操作</th></tr>
	 			</thead>
	 			<tbody id="test-tbody">
	 				
	 			</tbody>
	 		</table>
	 	</div>
   </div>
   
   <div class="ibox-content">
		<button id="savePro" onclick="doSubmit()" class="btn btn-primary" >&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;</button>
	</div>
<script>
	$(function(){
		
		// 添加学员
		$("#btn_add").click(function () {
			$("#myModalLabel").text("学员通讯录");
			$('#myModal').modal();
			});
		// 清空
		$("#btn_remove").click(function(){
			$("#strNames").val("");
			$("#strIds").val("");
		})
		
		//添加学员确定按钮
		$("#btn_submit").click(function(){
			
			var ids = [];
			var names = [];
			
			$("#strNames").val("");
			$("#strIds").val("");
			$(".ysChecks").each(function(){
				if(this.checked){
					ids.push(this.value);
					names.push($(this).next().val());
					
				
				}
			})
			var strIds = ids.join(",");
			var strNames = names.join(", ");
			$("#strNames").val(strNames);
			$("#strIds").val(strIds);
			ids = [];
			names = [];
		})

		// 树形菜单点击
		$(".depLi span").click(function(){
			if($(this).attr('class') == "caretD"){ 
				$(this).attr("class","caretR");
			} else {
				$(this).attr("class","caretD");
			}
			 
			
			var UL = $(this).parent().next("ul"); 
			if(UL.css("display")=="none"){ 
				UL.css("display","block"); 
			} 
			else{ 
				UL.css("display","none"); 
			} 
		}); 
	
		// 复选框点击事件
		$('.checkAll').click(function(){
			var ysChecks = ".ysChecks"+this.value;
			if (this.checked){ 
				
	            $(ysChecks).each(function(){   
	                  $(this).attr("checked", true)
	            });  
	        } else {     
	            $(ysChecks).each(function() {     
	                  $(this).attr("checked", false);
	            });
	        }
		})
		
		$(".ysChecks").click(function(){
			if (!this.checked){
				$(this).parent().parent().prev().children(".checkAll").attr("checked", false);
			}
			
		})
		
	})
	
	// 课件div显示
	function course_show(courseIds){
		$(".course_list").show();
		$(".test_list").hide();
		$.ajax({
            type: "POST",
            url: "${ctx}/project/ysProject/courseListByIds",
            data: {"courseIds":courseIds},
            success: function(data){
            	if(data != null){
            		$(".course_list").html(data);
            	}else{
            		$(".course_list").html("");
            	}
            }
        });
	}
	
	
	// 考试div显示
	function test_show(str){
		$(".course_list").hide();
		$(".test_list").show();
		$("#test-tbody").append(str);		
	}
	
	// 课件点击删除
	function del_course(data){
		var par = data.parentNode.parentNode.parentNode;
		var chi = data.parentNode.parentNode;
		par.removeChild(chi);
	}
	
	// 获取课件 和 考试的id集合 并赋值给表单
	function courseIdsAndtestIds(){
		var courseIdsArr = [];
		var testIdsArr = [];
		$(".course_checked").each(function(){
			if($(this).is(":checked")){
				var courseId = $(this).attr('id');
				courseIdsArr.push(courseId);
			}
		})
		
		$(".test_checked").each(function(){
			if($(this).is(":checked")){
				var testId = $(this).val();
				testIdsArr.push(testId);
			}
		})
		var courseIds = courseIdsArr.join(',');
		var testIds = testIdsArr.join(',');
		$("#courseIds").val(courseIds);
		$("#testIds").val(testIds);
	}
	
		
</script>
	
</body>
</html>