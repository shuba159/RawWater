<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<title>论坛主题标题</title>
	<meta name="decorator" content="default"/>
	 <link href="${ctxStatic}/summernote/summernote.css" rel="stylesheet">
	 <link href="${ctxStatic}/summernote/summernote-bs3.css" rel="stylesheet">
	 <script src="${ctxStatic}/summernote/summernote.min.js"></script>
	 <script src="${ctxStatic}/summernote/summernote-zh-CN.js"></script>
	<style type="text/css">
	    *{
	        margin:0px 15px 0px 15px;
	        padding: 0px;
      	 }
      	#divContent{
	        width: 86%;
            height: 36px;
            display:block;
            overflow:hidden;
        }
      	hr{
      		margin:5px 5px 5px 0px;
      	}
	</style>
	
	<script type="text/javascript">
	$(function(){
		$("#ckqb").click(function(){
	        if($(this).text() == "︾查看全部"){
	            $("#divContent").css({'height' : 'auto','overflow': 'none' });
	            $(this).text("︽收起");
	        } else{
	            $("#divContent").css({'height' : '36px','overflow': 'hidden' });
	            $(this).text("︾查看全部");
	        }
	    });
	})
	</script>
</head>
<body>
    <h1>this is YsReply.jsp</h1>
    <hr/>
    <div>
        <span>发帖人：${fns:getIdSelectUserName(ysForum.userId)}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>
<fmt:formatDate value="${ysForum.forumTime}" type="both"/>
        
        </span>
        <div style="float:right">
            <span>查看次数：${ysForum.seeNumber}</span>&nbsp;&nbsp;
            <span>回复数：${fns:getIdSelectForum(ysForum.id)}</span>
        </div>
        <br/><br/>
        <span style="font-size:17px;font-weight:bold;">帖子标题：${ysForum.forumTitle}</span><br/><br/>
        <div id="divContent">
            <span>帖子内容：<%-- ${ysForum.forumcontent} --%>
                ${fns:unescapeHtml(ysForum.forumcontent)}
            </span><br/>
        </div>  <a id="ckqb" href="#">︾查看全部</a>
    </div>  <hr/>
    
    <c:forEach items="${listYsReply}" var="listYsReply">
        ${fns:getIdSelectUserName(listYsReply.userId)}<!-- 回复人 --> 
      <div style="float:right;margin:0px 15px 0px 0px;">
          <span style="float:left"><fmt:formatDate value="${listYsReply.replyTime}" type="both"/></span>  <!-- 回复时间 -->
          <button type="button" class="btn btn-info btn-xs" style="float:left" > 回 复 </button>&nbsp;&nbsp;
          <form action="${ctx}/forum/ysForum/deleteYsReply">
              <input type="submit" class="btn btn-danger btn-xs" value="删除"/>
          </form>
      </div>
      
      <div style="width:85%;height:auto;">${listYsReply.replyContent}  <!-- 回复内容 --></div>
      <hr/>
    </c:forEach>
    
</body>
</html>