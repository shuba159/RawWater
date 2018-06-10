<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var count = 11111;
			
		// 全选
		$('#contentTable thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	 	$('#contentTable tbody tr td input.i-checks').iCheck('check');
    	});
	    $('#contentTable thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
	    	$('#contentTable tbody tr td input.i-checks').iCheck('uncheck');
	    });
			
			
			//时间插件
			//外部js调用
	        laydate({
	            elem: '#sTime',  //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            type: 'datetime',  
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
	        laydate({
	            elem: '#eTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>项目管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="ysProject" action="${ctx}/project/ysProject/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>项目名称：</span>
				<input id="proName" name="proName" type="text" maxlength="50" class="form-control input-sm" value=""/>
			<span>项目分类：</span>
				<form:select path="proClassId" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getYsProClassList()}" itemLabel="className" itemValue="id" htmlEscape="false"/>
				</form:select>
			<span>创建人：</span>
				<input id="createName" name="createName" type="text" maxlength="50"  class="form-control input-sm" value=""/>
			<span>操作时间：&nbsp;</span>
				<input id="sTime" name="sTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"   
			    value="<fmt:formatDate value="${ysProject.sTime}" pattern="yyyy-MM-dd"/>"/>&nbsp;&nbsp;~&nbsp;&nbsp;
			    <input id="eTime" name="eTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"   
			    value="<fmt:formatDate value="${ysProject.eTime}" pattern="yyyy-MM-dd"/>"/>	
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
				<th  class="sort-column proName">项目名称</th>
				<th  class="sort-column proClassId">项目分类</th>
				<th  class="sort-column courseNumber">课程课件数</th>
				<th  class="sort-column createName">创建人</th>
				<th  class="sort-column updateTime">操作时间</th>
				<th  class="sort-column totalScore">总学分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ysProject" varStatus="ysList">
			<tr>
				<td> <input type="checkbox" id="${ysProject.id}" class="i-checks"></td>
				<td>
					${ysList.index+1 }
				</td>
				<td>
					${ysProject.proName}
				</td>
				<td>
					${fns:getYsProClass(ysProject.proClassId)}
				</td>
				<td>
					${ysProject.courseNumber}
				</td>
				<td>
					${fns:getUserByUserId(ysProject.createId)}
				</td>
				<td>
					<fmt:formatDate value="${ysProject.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ysProject.totalScore}
				</td>
				<td>
					<a href="${ctx}/project/ysProject/courseList?proId=${ysProject.id}" class="btn btn-info btn-xs" > 查看课程</a>
    				<a href="${ctx}/project/ysProject/paperList?proId=${ysProject.id}" class="btn btn-info btn-xs" > 查看试卷</a>
    				<a href="#" onclick="openDialog('编辑', '${ctx}/project/ysProject/form?id=${ysProject.id}','800px', '500px')" class="btn btn-success btn-xs" > 编辑</a>
    				<a href="#" onclick="openDialog('', '${ctx}/inform/ysInform/form?proId=${ysProject.id}','700px', '500px')" class="btn btn-success btn-xs" > 通知</a>
    				<a href="#" onclick="openDialog('项目评价', '${ctx}/project/ysProject/evaluate?proId=${ysProject.id}','800px', '500px')" class="btn btn-success btn-xs" > 评价</a>
					<a href="${ctx}/project/ysProject/delete?id=${ysProject.id}" onclick="return confirmx('确认要删除该项目管理吗？', this.href)"   class="btn btn-danger btn-xs"> 删除</a>
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
		// 批量删除项目
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
				window.location = "${ctx}/project/ysProject/deleteAll?ids="+ids;
			    top.layer.close(index);
			});
		}
	</script>
</body>
</html>