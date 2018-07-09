<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> New Document </title>
  <meta name="Generator" content="EditPlus">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
 <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">  
   <script src="bootstrap/js/jquery.min.js"></script>
   <script src="bootstrap/js/bootstrap.min.js"></script>
 <script>
 function TJ(){
	var pass=document.getElementById("password").value;
	var mm=document.getElementById("mima").value;
	var p1=document.getElementById("password1").value;
	var p2=document.getElementById("password2").value;
	if(pass!=mm){
		alert("密码错误，请重新输入!");
		return false;
	}
	if(p1!=p2){
		alert("两次密码输入不一致，请重新输入!");
		return false;
	}
}
 </script>
 <style type="text/css">
	 body {
		background-image:url(image/bg-yellow.png);
		width:100%;
		height:100%;
		background-size:100% 100%;
	}
</style>
 </head>
  
 <body>
 <%
 String zh= (String)session.getAttribute("bz");
 String my= (String)session.getAttribute("pa");
 %>
 <div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">修改密码</h3>
    </div>
	<form action="XiuGaiMiMa" method="POST" id="form1"  target="hiddenFrameName" onsubmit="return TJ()">
    <div class="panel-body">
		 <input type="hidden" id="zh" name="username"  class="form-control" style="width:10%" value="<%=zh%>">
	</br>
	<div align="center">
		<p>输入密码:
			<input type="password" id="password" name="password"  class="form-control" style="width:10%">
		</p>
		<input type="hidden" id="mima" name="mima"  class="form-control" style="width:10%" value="<%=my%>">
		 
	</div></br>
	<div align="center">
		<p>输入新的密码:<input type="password" id="password1" name="password1"  class="form-control" style="width:10%"></p>
		 
	</div></br>
	<div align="center">
		<p>重复输入新的密码:<input type="password" id="password2" name="password2" class="form-control" style="width:10%"></p>
		 
	</div></br>
		<div align="center">
		<input type="submit" class="btn btn-info btn-lg btn-block" style="width:40%"></input>
		</div>
	</div>
</div>
	
	</form>
 </body>
</html>
