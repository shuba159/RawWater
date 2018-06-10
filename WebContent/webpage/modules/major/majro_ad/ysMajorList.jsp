<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专业类型管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>专业类型管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="ysMajor" action="${ctx}/major/majro_ad/ysMajor/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<%-- <span>id：</span>
				<form:input path="id" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/> --%>
			<span>专业名称：</span>
				<form:input path="majorName" htmlEscape="false" maxlength="120"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="major:majro_ad:ysMajor:add">
				<table:addRow url="${ctx}/major/majro_ad/ysMajor/form" title="专业类型管理"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="major:majro_ad:ysMajor:edit">
			    <table:editRow url="${ctx}/major/majro_ad/ysMajor/form" title="专业类型管理" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="major:majro_ad:ysMajor:del">
				<table:delRow url="${ctx}/major/majro_ad/ysMajor/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="major:majro_ad:ysMajor:import">
				<table:importExcel url="${ctx}/major/majro_ad/ysMajor/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="major:majro_ad:ysMajor:export">
	       		<table:exportExcel url="${ctx}/major/majro_ad/ysMajor/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
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
				<th  class="sort-column id">编号</th>
				<th  class="sort-column majorName">专业名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<% int linshi=1; %>
		<c:forEach items="${page.list}" var="ysMajor">
			<tr>
				<td> <input type="checkbox" id="${ysMajor.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看专业类型管理', '${ctx}/major/majro_ad/ysMajor/form?id=${ysMajor.id}','800px', '500px')">
<%-- 					${ysMajor.id} --%>
                    <%=linshi++ %>
				</a></td>
				<td>
					${ysMajor.majorName}
				</td>
				<td>
					<shiro:hasPermission name="major:majro_ad:ysMajor:view">
						<a href="#" onclick="openDialogView('查看专业类型管理', '${ctx}/major/majro_ad/ysMajor/form?id=${ysMajor.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="major:majro_ad:ysMajor:edit">
    					<a href="#" onclick="openDialog('修改专业类型管理', '${ctx}/major/majro_ad/ysMajor/form?id=${ysMajor.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="major:majro_ad:ysMajor:del">
						<a href="${ctx}/major/majro_ad/ysMajor/delete?id=${ysMajor.id}" onclick="return confirmx('确认要删除该专业类型管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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