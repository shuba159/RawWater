<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>论坛管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
	            elem: '#forumTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>论坛管理列表 </h5>
		<div class="ibox-tools">
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
	<form:form id="searchForm" modelAttribute="ysForum" action="${ctx}/forum/ysForum/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>帖子标题：</span>
				<form:input path="forumTitle" htmlEscape="false" maxlength="4000"  class=" form-control input-sm"/>
			<span>发帖日期：</span>
				<input id="forumTime" name="forumTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysForum.forumTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<%-- <shiro:hasPermission name="forum:ysForum:add">
				<table:addRow url="${ctx}/forum/ysForum/form" title="论坛管理"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="forum:ysForum:edit">
			    <table:editRow url="${ctx}/forum/ysForum/form" title="论坛管理" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="forum:ysForum:del">
				<table:delRow url="${ctx}/forum/ysForum/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			 </shiro:hasPermission>
			<%--<shiro:hasPermission name="forum:ysForum:import">
				<table:importExcel url="${ctx}/forum/ysForum/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="forum:ysForum:export">
	       		<table:exportExcel url="${ctx}/forum/ysForum/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
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
				<th  class="sort-column userId">用户外键</th>
				<th  class="sort-column forumTitle">帖子标题</th>
				<th  class="sort-column forumTime">发帖时间</th>
				<th>回复</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<%int linshi = 1; %>
		<c:forEach items="${page.list}" var="ysForum">
			<tr>
				<td><%=linshi %><%linshi++;%><%--  <input type="checkbox" id="${ysForum.id}" class="i-checks"> --%></td>
				<td>
<%-- 				<a  href="#" onclick="openDialogView('查看论坛管理', '${ctx}/forum/ysForum/form?id=${ysForum.id}','800px', '500px')"> --%>
					${ysForum.userId}
<!-- 				</a> -->
				</td>
				<td>
					${ysForum.forumTitle}
				</td>
				<td>
					<fmt:formatDate value="${ysForum.forumTime}" type="both"/>	
				</td>
				<td>
					${fns:getIdSelectForum(ysForum.id)}
				</td>
				<td>
<%-- 					<shiro:hasPermission name="forum:ysForum:view"> --%>
						<a href="#" onclick="openDialogView('查看论坛管理', '${ctx}/forum/ysForum/ysReply?id=${ysForum.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
<%-- 					</shiro:hasPermission> --%>
				<%-- 	<shiro:hasPermission name="forum:ysForum:edit">
    					<a href="#" onclick="openDialog('修改论坛管理', '${ctx}/forum/ysForum/form?id=${ysForum.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission> --%>
    				<shiro:hasPermission name="forum:ysForum:del">
						<a href="${ctx}/forum/ysForum/delete?id=${ysForum.id}" onclick="return confirmx('确认要删除该论坛管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>