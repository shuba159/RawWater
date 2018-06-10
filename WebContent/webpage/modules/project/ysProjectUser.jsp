<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">	
	
	// 查询表单提交
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		   	return false;
		   }
		
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>项目管理 </h5>
		<div class="ibox-tools">
			<a>首页</a>
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="ysNotice" action="${ctx}/project/ysProject/" method="post" class="form-inline">
		<h2>通知</h2>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>通知主题：</span>
				<input id="noticeName" name="noticeName" type="text" maxlength="50" class="form-control input-sm" value=""/>
			<span>通知人员：</span>
				<input id="proName" name="" type="text" maxlength="50" class="form-control input-sm" value=""/>
			<span>通知内容：</span>
				<form:textarea path="content" htmlEscape="false" rows="3"    class="form-control required"/>
			<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<!-- onclick="search()"在static/common/jeeplus.js -->
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 确定</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" id="reset"><i class="fa fa-refresh"></i> 取消</button>	
		</div>	
	</form:form>
	<br/>
	</div>
	</div>
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>