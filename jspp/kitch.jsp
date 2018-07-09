
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %> 
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>登录界面</title>
   <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">  
   <script src="js/jquery.min.js"></script>
   <script src="bootstrap/js/bootstrap.min.js"></script>
 <style>
#tp{
	right:24%;
	margin:0;
	position:fixed;
	top:0px;
	}
#nav {
	font-size:25px;
    line-height:50px;
    float:left;
    padding-top:10px;
	padding-bottom:10px;
	width:120px;
	margin-top:10px; 
	margin-left:10px;
}
#section {
	line-height:10px;
    float:left;
	margin-top:30px;
	padding:0px;
	margin-left:0px;
	margin-right:0px;
	width:170px;
}
#mb{
width:50%;
height:50%;
position:relative;	/*设置相对定位*/
}

  </style>
<script>
function myCheck()
{
	var x=document.getElementById("zh").value;
	var y=document.getElementById("mm").value;
	if(x=="")
	{
		window.alert("账号为空,请输入账号!");
		return false;
	}
	if(y==""){
		window.alert("密码为空,请输入密码!");
		return false;
	}
	return true;
}
</script>
</head>
  <div  style="position:fixed; width:100%; height:100%; z-index:-1;">    
			<img src="images/xk.jpg" style="height:100%;width:100%;max-width:100%; max-height:100%; display: block;"/>  
		</div>
<body>

		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		<div><p>&nbsp;</p></div>
		
	<div class="container">
<center>
	<div class="panel panel-info" style="width:30%"  id="mb">
    <div class="panel-heading">
        <h3 class="panel-title" style="color:#337ab7"><b>登录页面</b></h3>
    </div>
    <div class="panel-body">
        <form action="CheckDataBase"  method="POST" id="form1" onsubmit="return myCheck()">
		<div class="row">
			<div class="col-md-2 column-sm-2" id="nav">
					<p style="color:#337ab7">用户名:</p>
			</div>
			<div  class="col-md-8 column-sm-2" id="section">
				<input type="text" name="zh" class="form-control" form="form1" id="zh" autofocus style="margin:0;width:70%; height:40px; background:rgba(255, 255, 255, 0.5);">
			</div>
				</div>
				<div class="row">
					<div class="col-md-2 column-sm-2" id="nav">
							<p style="color:#337ab7">密码:</p>
					</div>
					<div  class="col-md-8 column-sm-4" id="section">
						 <input type="password" name="pa"  class="form-control"  form="form1" id="mm" style="width:70%; height:40px;background:rgba(255, 255, 255, 0.5);">
					</div>
				</div>
				</br></br>
				<div class="row" align="center"  >
					 <input type="submit"  onclick="myCheck()"  class="btn btn-info btn-lg "  value="登录" style="width:40%; " ></button>
				</form>	

				 </div>
		</div>
    
	</div>
		</center>
		</div>	
		<div  >
			<nav class="navbar-fixed-bottom" style="height:18px;">
				
					<p  style="padding-bottom:0px ;background:rgba(220,220,220,0.5); text-align:center; ">
						COPYRIGHT @ 2016 - 2017 黄山学院蓝豹小组软件研发 ALL RIGHTS RESERVED
						欢迎您的使用
					</p>
			</nav>
	    </div>

	
</body>
</html>