<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>按人评卷管理</title>
	<meta name="decorator" content="default"/>
	
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>按人评卷列表 </h5>
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
	<a href="${ctx}/project/ysTest">返回人工评卷&crarr;</a>
	<div style="width: 100% ; height: 80px; text-align:center;">
	<h2>考试名称：${list1.get(0).testName}</h2>
	<h3>考试时间:<span id="span01"><fmt:formatDate value="${list1.get(0).startTime}" type="both"/></span>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      答卷时间：${list1.get(0).testTime}</h3>
	</div>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="ysTestScore" action="${ctx}/project/ysTestScore/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>用户外键：</span>
			
			<input type="hidden" name="aaid" value="${list1.get(0).id}">
				<form:input path="userIds" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
			<span>部门外键：</span>
				<form:input path="depIds" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<%-- <shiro:hasPermission name="project:ysTestScore:add">
				<table:addRow url="${ctx}/project/ysTestScore/form" title="按人评卷"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="project:ysTestScore:edit">
			    <table:editRow url="${ctx}/project/ysTestScore/form" title="按人评卷" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="project:ysTestScore:del">
				<table:delRow url="${ctx}/project/ysTestScore/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="project:ysTestScore:import">
				<table:importExcel url="${ctx}/project/ysTestScore/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="project:ysTestScore:export">
	       		<table:exportExcel url="${ctx}/project/ysTestScore/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		 --%>
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column papId">考试名称</th>
				<th  class="sort-column user.name">用户姓名</th>
				<th  class="sort-column depId">部门名字</th>
				<th  class="sort-column score">考试分数</th>
				<th  class="sort-column commitTime">交卷时间</th>
				<th  class="sort-column evaluateTime">评卷时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysTestScore">
			<tr>
				<td> <input type="checkbox" id="${ysTestScore.id}" class="i-checks"></td>
				<td>
				<%-- <a  href="#" onclick="openDialogView('查看按人评卷', '${ctx}/project/ysTestScore/form?id=${ysTestScore.id}','800px', '500px')"> --%>
<%-- 					${ysTestScore.papId} --%>
					${fns:getTestNameSelectScoIdPapId(ysTestScore.papId,ysTestScore.id)}
<!-- 				</a> -->
				</td>
				<td>
<%-- 					 ${ysTestScore.userId} --%>
					 ${fns:getIdSelectUserName(ysTestScore.userId)} 
				</td>
				<td>
<%-- 					${ysTestScore.depId} --%>
					${fns:getDepNameSelect(ysTestScore.depId)}
				</td>
				<td>
					${ysTestScore.score}
				</td>
				<td>
					<fmt:formatDate value="${ysTestScore.commitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ysTestScore.evaluateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%-- <shiro:hasPermission name="project:ysTestScore:view">
						<a href="#" onclick="openDialogView('查看按人评卷', '${ctx}/project/ysTestScore/form?id=${ysTestScore.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="project:ysTestScore:edit">
    					<a href="#" onclick="openDialog('修改按人评卷', '${ctx}/project/ysTestScore/form?id=${ysTestScore.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="project:ysTestScore:del">
						<a href="${ctx}/project/ysTestScore/delete?id=${ysTestScore.id}" onclick="return confirmx('确认要删除该按人评卷吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission> --%>
					<a href="#" onclick="open1('按人评卷', '${ctx}/project/ysTestScore/ysPaper?id=${ysTestScore.id}&userId=${ysTestScore.userId}','1200px', '600px')" class="btn btn-success btn-xs" ><i class="fa fa-check"></i> 人工评卷</a>
<%-- 					<a href="${ctx}/project/ysTestScore/ysPaper?id=${ysTestScore.id}&userId=${ysTestScore.userId}" class="btn btn-success btn-xs" ><i class="fa fa-check"></i> 人工评卷</a> --%>
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

	<script type="text/javascript">
		$(document).ready(function() {
			function Todate(num) { //Fri Oct 31 18:00:00 UTC+0800 2008
	            num = num + "";
	            var date = "";
	            var month = new Array();
	            month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jan"] = 6;
	            month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
	            var week = new Array();
	            week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
	            str = num.split(" ");
	            date = str[5] + "-";
	            date = date + month[str[1]] + "-" + str[2];
	            return date;
	        }
		})
			function open1(title,url,width,height){
				
				top.layer.open({
				    type: 2,  
				    area: [width, height],
				    title: title,
				    content: url ,
 				    btn: ['关闭'],
				    btn2: function(index, layero){
				    	
				    },
				    cancel: function(index){ 
				       }
				}); 
			}
	</script>
</body>
</html>