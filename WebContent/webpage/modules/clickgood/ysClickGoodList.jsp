<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>点赞收藏管理</title>
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
		<h5>点赞收藏列表 </h5>
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
	<form:form id="searchForm" modelAttribute="ysClickGood" action="${ctx}/clickgood/ysClickGood/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>编号：</span>
				<form:input path="id" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
			<span>用户外键：</span>
				<sys:treeselect id="user" name="user.id" value="${ysClickGood.user.id}" labelName="user.name" labelValue="${ysClickGood.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="form-control input-sm" allowClear="true" notAllowSelectParent="true"/>
			<span>课件外键：</span>
				<form:input path="couId" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
			<span>1.已点赞2.未点赞：</span>
				<form:input path="goodState" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
			<span>1.已收藏2.未收藏：</span>
				<form:input path="signState" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="clickgood:ysClickGood:add">
				<table:addRow url="${ctx}/clickgood/ysClickGood/form" title="点赞收藏"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="clickgood:ysClickGood:edit">
			    <table:editRow url="${ctx}/clickgood/ysClickGood/form" title="点赞收藏" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="clickgood:ysClickGood:del">
				<table:delRow url="${ctx}/clickgood/ysClickGood/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="clickgood:ysClickGood:import">
				<table:importExcel url="${ctx}/clickgood/ysClickGood/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="clickgood:ysClickGood:export">
	       		<table:exportExcel url="${ctx}/clickgood/ysClickGood/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column user.name">用户外键</th>
				<th  class="sort-column couId">课件外键</th>
				<th  class="sort-column goodState">1.已点赞2.未点赞</th>
				<th  class="sort-column signState">1.已收藏2.未收藏</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysClickGood">
			<tr>
				<td> <input type="checkbox" id="${ysClickGood.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看点赞收藏', '${ctx}/clickgood/ysClickGood/form?id=${ysClickGood.id}','800px', '500px')">
					${ysClickGood.id}
				</a></td>
				<td>
					${ysClickGood.user.name}
				</td>
				<td>
					${ysClickGood.couId}
				</td>
				<td>
					${ysClickGood.goodState}
				</td>
				<td>
					${ysClickGood.signState}
				</td>
				<td>
					<shiro:hasPermission name="clickgood:ysClickGood:view">
						<a href="#" onclick="openDialogView('查看点赞收藏', '${ctx}/clickgood/ysClickGood/form?id=${ysClickGood.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="clickgood:ysClickGood:edit">
    					<a href="#" onclick="openDialog('修改点赞收藏', '${ctx}/clickgood/ysClickGood/form?id=${ysClickGood.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="clickgood:ysClickGood:del">
						<a href="${ctx}/clickgood/ysClickGood/delete?id=${ysClickGood.id}" onclick="return confirmx('确认要删除该点赞收藏吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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