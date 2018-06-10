<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF">
<title>Insert title here</title>
</head>
 
<body>
<form action="a/projectvue/findByPapScore" method="post">
个数<input type="text" name="limit"><br/>
页数<input type="text" name="page" >
项目id<input type="text" name="papId"><br/>
课件<input type="text" name=ordeykey><br/>
部门id<input type="text" name="depId" >
用户名字<input type="text" name="xingming"><br/>
开始时间<input type="text" name="startTime" >
结束时间<input type="text" name="eTime" >
<input type="submit" value="登陆">
</form>
</html>