<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考试管理</title>
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
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>考试列表 </h5>
		<div class="ibox-tools">
			<a href="${ctx}/project/ysProject/courseList?proId=${proId}" class="" >返回上一级</a>
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
	
	<div class="form-group">
			<span>课件名称： &nbsp;${page.list[0].courseName }</span>&nbsp;&nbsp;&nbsp;
			<span>项目名称： &nbsp;${page.list[0].proName }</span>&nbsp;&nbsp;&nbsp;
			<span>培训时间： &nbsp;<fmt:formatDate value="${page.list[0].startTime }" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;~&nbsp;<fmt:formatDate value="${page.list[0].endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
	</div>
		
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="ysCourseUserPojo" action="${ctx}/project/ysProject/userStudy/${ysCourseUserPojo.couId}/${ysCourseUserPojo.proId}" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->	
		<div class="form-group">
			<span>所属部门：</span>
				<form:select path="depId"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getYsDepartmentList()}" itemValue="id" itemLabel="depName"/>
				</form:select>
			<span>用户名/姓名：</span>
				<form:input path="searchName" htmlEscape="false" maxlength="4000"  class=" form-control input-sm"/>
		</div>	
	</form:form>
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
				<th class="">用户名</th>
				<th class="">姓名</th>
				<th class="">所属部门</th>
				<th class="">学习进度</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysCourseUserPojo">
			<tr>
				<td> <input type="checkbox" id="${ysCourseUserPojo.id}" class="i-checks"></td>
				
				<td>
					${ysCourseUserPojo.userName}
				</td>
				<td>
					${ysCourseUserPojo.fullName}
				</td>
				<td>
					${ysCourseUserPojo.depName}
				</td>
				<td>
					${ysCourseUserPojo.progress}%
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		<button class="btn btn-danger btn-xs" onclick="delAll()" >批量删除</button>
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
	<script type="text/javascript">
		//批量删除课件
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
				window.location = "${ctx}/project/ysProject/delAllCourseUser/"+ids+"/${ysCourseUserPojo.couId}/${ysCourseUserPojo.proId}";
			    top.layer.close(index);
			});
		}
	</script>
	</body>
</html>