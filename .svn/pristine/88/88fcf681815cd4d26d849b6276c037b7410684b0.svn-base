<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>题库管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
	            elem: '#createTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
			laydate({
	            elem: '#createTime2', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>题库管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="ysQuestions" action="${ctx}/questions/questions_as/ysQuestions/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>试题类型：</span>
				<form:select path="questionType"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<span>试题难度：</span>
				<form:select path="level"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
		<%-- 	<span>试题分类：</span>
				<form:select path="questionClass"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_class')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
			<span>试题状态：</span>
				<form:select path="questionState"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<span>审批状态：</span>
				<form:select path="ispath"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ispath')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<span>所属部门：</span>
				<form:select path="depId"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dep_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<span>专业类型：</span>
				<form:select path="majorType" class="form-control required">
							<form:option value="" label=""/>
							<c:forEach items="${list}" var="list">
								<form:option value="${list.id}" label="${list.majorName}"/>
							</c:forEach>
						</form:select>
			<span>创建人：</span>
				<form:input path="createId" htmlEscape="false" maxlength="11"  class=" form-control input-sm"/>
		    <span>最后修改日期：</span>
				<input id="createTime" name="updateTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysCourse.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<span>~</span>
				<input id="createTime2" name="updateTime2" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${ysCourse.createTime2}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<%-- <shiro:hasPermission name="questions:questions_as:ysQuestions:add">
				<table:addRow url="${ctx}/questions/questions_as/ysQuestions/form" title="题库管理"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="questions:questions_as:ysQuestions:edit">
			    <table:editRow url="${ctx}/questions/questions_as/ysQuestions/form" title="题库管理" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="questions:questions_as:ysQuestions:del">
				<table:delRow url="${ctx}/questions/questions_as/ysQuestions/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="questions:questions_as:ysQuestions:import">
				<table:importExcel url="${ctx}/questions/questions_as/ysQuestions/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="questions:questions_as:ysQuestions:export">
	       		<table:exportExcel url="${ctx}/questions/questions_as/ysQuestions/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column questionType">试题类型</th>
				<th  class="sort-column level">试题难度</th>
<!-- 				<th  class="sort-column questionClass">试题分类</th> -->
				<th  class="sort-column questionState">试题状态</th>
				<th  class="sort-column ispath">审批状态</th>
				<th  class="sort-column depId">所属部门</th>
				<th  class="sort-column majorType">专业类型</th>
				<th  class="sort-column updateTime">修改时间</th>
				<th  class="sort-column createId">创始人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysQuestions">
			<tr>
				<td> <input type="checkbox" id="${ysQuestions.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看题库管理', '${ctx}/questions/questions_as/ysQuestions/form?id=${ysQuestions.id}','800px', '500px')">
					${fns:getDictLabel(ysQuestions.questionType, 'question_type', '')}
				</a></td>
				<td>
					${fns:getDictLabel(ysQuestions.level, 'level', '')}
				</td>
				<%-- <td>
					${fns:getDictLabel(ysQuestions.questionClass, 'question_class', '')}
				</td> --%>
				<td>
					${fns:getDictLabel(ysQuestions.questionState, 'question_state', '')}  <!-- 试题状态 -->
				</td>
				<td>
					${fns:getDictLabel(ysQuestions.ispath, 'ispath', '')}   <!-- 审批状态 -->
				</td>
				<td>
					${fns:getDictLabel(ysQuestions.depId, 'dep_id', '')}
				</td>
				<td>
                    ${fns:getMajorTypeSelectQues(ysQuestions.majorType,ysQuestions.id)}  <!-- 专业类型 -->
				</td>
				<td>
					<fmt:formatDate value="${ysQuestions.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ysQuestions.createId}
				</td>
				<td>
					<shiro:hasPermission name="questions:questions_as:ysQuestions:view">
						<a href="#" onclick="openDialogView('查看题库管理', '${ctx}/questions/questions_as/ysQuestions/form?id=${ysQuestions.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="questions:questions_as:ysQuestions:edit">
    					<a href="#" onclick="openDialog('修改题库管理', '${ctx}/questions/questions_as/ysQuestions/form?id=${ysQuestions.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="questions:questions_as:ysQuestions:del">
						<a href="${ctx}/questions/questions_as/ysQuestions/delete?id=${ysQuestions.id}" onclick="return confirmx('确认要删除该题库管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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