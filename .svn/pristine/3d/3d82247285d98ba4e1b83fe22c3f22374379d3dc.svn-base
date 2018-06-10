<%@page import="com.jeeplus.modules.course.dao.addcourse.YsCourseDao"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>学习资料管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			// 全选
			$('#contentTable thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
	    	 	$('#contentTable tbody tr td input.i-checks').iCheck('check');
	    	});
		    $('#contentTable thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		    	$('#contentTable tbody tr td input.i-checks').iCheck('uncheck');
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
		
		function getCourseIds(){
			var ids = [];
			$(".course_tr").each(function(i){
				var che = $(this).find("div").first();
				if(che.hasClass("checked")){
					var id = $(this).find("input").first().attr('id');
					ids.push(id);
				} 
			});
			return ids.join(",");
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>学习资料管理列表  > 查看课程 </h5>
		<div class="ibox-tools">
			<a href="${ctx}/project/ysProject" class="" >返回上一级</a>
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="ysCourse" action="${ctx}/project/ysProject/courseList?proId=${ysCourse.proId }" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>课件名称：</span>
				<form:input path="courseName" htmlEscape="false" maxlength="4000"  class=" form-control input-sm"/>
			<span>培训时间：</span>
				<input id="startTime" name="startTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysCourse.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<span>~</span>
				<input id="endTime" name="endTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysCourse.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			<span>课件状态：</span>
				<form:select path="state"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:option value="1" label="启用"/>
					<form:option value="2" label="禁用"/>
				</form:select>
			
			<span>创建人：</span>
				<form:input path="createId" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
			<span>课件分类 ：</span>
				<form:select path="courseClass"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:option value="1" label="动态"/>
					<form:option value="2" label="静态"/>
				</form:select>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column id">编号</th>
				<th  class="sort-column courseName">课件名称</th>
				<th  class="sort-column courseClass">课件分类</th>
				<th  class="sort-column state">状态</th>
				<th  class="sort-column createId">创建人</th>
				<th  class="sort-column startTime">培训时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysCourse" varStatus="ysCourseList">
			<tr class="course_tr">
				<td> <input type="checkbox" id="${ysCourse.id}" class="i-checks"></td>
				<td>
					${ysCourseList.index+1}
				</td>
				<td>
					${ysCourse.courseName}
				</td>
				<td>
					${ysCourse.courseClass == 1? "动态":"静态"}
				</td>
				<td>
					${ysCourse.state == 1? "启用":"禁用"}
				</td>
				<td>
					${fns:getUserByUserId(ysCourse.createId)}
				</td>
				<td>
					<fmt:formatDate value="${ysCourse.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="${ctx}/project/ysProject/userStudy/${ysCourse.id }/${ysCourse.proId }" class="btn btn-info btn-xs" > 学习情况</a>
					<a href="${ctx}/project/ysProject/delCourse/${ysCourse.id }/${ysCourse.proId }" onclick="return delCourse('确认要删除该课件吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		<button class="btn btn-danger btn-xs" onclick="delAll()" >批量删除</button>
		<button class="btn btn-info btn-xs" onclick="updateAllCourseState(1)" >启用</button>
		<button class="btn btn-info btn-xs" onclick="updateAllCourseState(2)" >禁用</button>
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
	<script type="text/javascript">
	
		// 批量删除课件
		function delAll(){
	
			  var str="";
			  var ids="";
			  $("#contentTable tbody tr td input.i-checks:checkbox").each(function(){
			    if(true == $(this).is(':checked')){
			      str+=$(this).attr("id")+",";
			    }
			  });
			  if(str.substr(str.length-1)== ','){
			    ids = str.substr(0,str.length-1);
			  }
			  if(ids == ""){
				top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
				return;
			  }
				top.layer.confirm('确认要彻底删除数据吗?', {icon: 3, title:'系统提示'}, function(index){
				window.location = "${ctx}/project/ysProject/delAllCourse/"+ids+"/"+${ysCourse.proId};
			    top.layer.close(index);
			});
		}
	
		// 删除课件
		function delCourse(mess, href, closed){
			top.layer.confirm(mess, {icon: 3, title:'系统提示'}, function(index){
			    //do something
				if (typeof href == 'function') {
					href();
				}else{
					resetTip(); //loading();
					location = href;
				}
			    top.layer.close(index);
			});
			return false;
		}
		
		// 批量启用课件 禁用课件
		function updateAllCourseState(state){
	
			  var str="";
			  var ids="";
			  $("#contentTable tbody tr td input.i-checks:checkbox").each(function(){
			    if(true == $(this).is(':checked')){
			      str+=$(this).attr("id")+",";
			    }
			  });
			  if(str.substr(str.length-1)== ','){
			    ids = str.substr(0,str.length-1);
			  }
			  if(ids == ""){
				top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
				return;
			  }
				top.layer.confirm('确认要修改数据吗?', {icon: 3, title:'系统提示'}, function(index){
				window.location = "${ctx}/project/ysProject/updateAllCourseState/"+ids+"/"+${ysCourse.proId}+"/"+state;
			    top.layer.close(index);
			});
		}
	
	</script>
</body>
</html>