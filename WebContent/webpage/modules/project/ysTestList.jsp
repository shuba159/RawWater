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
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>考试列表 </h5>
		<div class="ibox-tools">
			<a href="JavaScript:window.location.href=document.referrer;" class="" >返回上一级</a>
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
	<form:form id="searchForm" modelAttribute="ysTest" action="${ctx}/project/ysTest/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>考试名称：</span>
				<form:input path="testName" htmlEscape="false" maxlength="4000"  class=" form-control input-sm"/>
			<span>所属部门：</span>
				<form:select path="depId"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getYsDepartmentList()}" itemValue="id" itemLabel="depName"/>
				</form:select>
			<span>考试次数 ：</span>
				<form:select path="testNumber"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:option value="1" label="一次"/>
					<form:option value="2" label="两次"/>
					<form:option value="" label="不限"/>
				</form:select>
			<span>考试日期：</span>
				<input id="startTime" name="startTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysCourse.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<span>~</span>
				<input id="endTime" name="endTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysCourse.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
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
				<th  class="sort-column testName">考试名称</th>
				<th  class="sort-column depNames">所属部门</th>
				<th  class="sort-column testNumber">考试次数</th>
				<th  class="sort-column startTime">考试日期</th>
				<th  class="sort-column testTime">答卷时长</th>
				<th  class="sort-column isEva">总分</th>
				<th  class="sort-column notEva">及格分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysTest" varStatus="ysTestList">
			<tr>
				<td> <input type="checkbox" id="${ysTest.id}" class="i-checks"></td>
				<td>
					${ysTestList.index+1 }
				</td>
				<td>
					${ysTest.testName}
				</td>
				<td>
					${ysTest.depNames}
				</td>
				<td>
					<fmt:formatDate value="${ysTest.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ysTest.testTime}
				</td>
				<td>
					100
				</td>
				<td>
					60
				</td>
				<td>
					<a href="#" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 成绩</a>
					<a href="${ctx}/project/ysProject/delTest/${ysTest.id}/${ysTest.proId}" onclick="return confirmx('确认要删除该考试吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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

</body>
</html>