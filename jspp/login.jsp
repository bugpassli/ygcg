<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE HTML>
 <html>
	 <head>
		 <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		 <title>登录界面</title>
		  <meta name="renderer" content="webkit|ie-comp|ie-stand,initial-scale=1"">
		 <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		 <meta http-equiv="X-UA-Compatible" content="IE=edge">
		 <meta name="viewport" content="width=device-width, initial-scale=1">
		 <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		 <link rel="stylesheet" href= "bootstrap/css/awesome-bootstrap-checkbox.css">
		 <link rel="stylesheet" href="bootstrap/Font-Awesome/css/font-awesome.min.css"/>
         <link rel="stylesheet" href="bootstrap/css/build.css"/>
         <script src="js/jquery-2.1.4.min.js"></script>
		 <script src="js/jquery.min.js"></script>
		 <script src="bootstrap/js/bootstrap.min.js"></script>
		 <script src="bootstrap/js/bootstrap.js"></script>
		 <script src="js/jquery.cookie.js"></script>
		 <script src="s/user.cookie.js"></script>
		 <script>  
			$(function(){  
				$('${error}').modal({  
				show:true,  
				backdrop:'static',  
				keyboard:false  
				})  
			});  
		</script>
			<script language="javascript"> 
				window.load = function(){ 
				document.getElementById('inputPassword3').value=''; 
			}; 
		</script>
		 <style type="text/css">
			 body {
				background-image:url(image/goin.jpg);
				background-size:100%;
			 }
			 .form-horizontal {
				background: #fff;
				padding-top: 50px;   /*与上边的距离*/
				padding-bottom: 50px; /*高度*/
				border-radius: 15px;  /*div的角度*/
				text_align: center; /*组件居中*/
			 }
			 .form-horizontal .form-group {
			     
				 padding: 15px 35px;
				 margin: 20px 10px 25px 0;
				 position: relative;
			 }
			 .form-horizontal .form-control{
				background: #f0f0f0;
				border: none;
				border-radius: 20px;
				box-shadow: none;
				padding: 0 20px 0 45px;
				height: 40px;
				transition: all 0.3s ease 0s;
			}
			.col-sm-3 control-label a1{
					padding-top: 5px;
					height: 77px;
					padding-left: 0px;
					padding-right: 0px;
				
			}
		 </style>
	</head>
	<body>
	
	   <div class="container">
			<div class="page-header" style="text-align:center ">
				<h1>登录界面<small>相关人员</small></h1>
			</div>
			<div class="container" >
			    <div class = "row clearfix">
				    <div class= "col-md-3 column ui-sortable"></div>
					<div class= "col-md-6 column ui-sortable 2" >
					    <div class = "row_1">
							<form role="form" class="form-horizontal" action = "Login" 
							  method = "post" id = "loginForm">
							  <div class="form-group">
								<label for="inputEmail3" class="col-sm-3 control-label a1">用户名
								  <i class="glyphicon glyphicon-user"></i>
								</label>
								<div class="col-sm-9">								 
								  <input type="text" class=" form-control" value="${username}"
								   name = "username" id="exampleInputName2" placeholder="用户名">
								</div>
							  </div>
							  <div class="form-group">
								<label for="inputPassword3" class="col-sm-3 control-label">密码
								 <i class = "glyphicon glyphicon-lock"></i></label>
								<div class="col-sm-9">
								  <input type="text"  onfocus="this.type='password'"	name= "password" 
								   value= "${password}"  class="form-control" id="inputPassword3"  
								   autocomplete="off" placeholder="密码">
								</div>
							  </div>
							  <div class="form-group">
								<div class="col-sm-offset-5 col-sm-7">
								 <div class="checkbox checkbox-success">
									<input id="checkbox3" class="styled" type="checkbox">
									<label for="checkbox3">
										记住密码
									</label>
		                         </div>
								</div>
							  </div>
							  <div class="form-group">
								<div class="col-sm-offset-1 col-sm-10">
								  <button type="submit" class = "btn btn-large btn-block btn-primary
								    btn btn-primary btn-lg ">
									 登录
									</button>
								</div>
							  </div>
							</form>
						 </div>
					</div>
					<div class= "col-md-3 column ui-sortable"></div>
				</div>
			</div>
	   </div>
	   
	  <div id="footer" class="container">
		<nav class="navbar navbar-default navbar-fixed-bottom">
			<div class="navbar-inner navbar-content-center">
				<p class="text-muted credit" style="padding: 10px ;background: #333 ; text-align:center ;">
					COPYRIGHT @ 2016 - 2017 黄山学院蓝豹小组软件研发 ALL RIGHTS RESERVED
		            欢迎热爱您的使用
				</p>
			</div>
		</nav>
	 </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					</button>
					<h4 class="modal-title" id="myModalLabel">
					失败
					</h4>
				</div>
				<div class="modal-body">
					<center><h2>用户名或密码错误！</h2></center>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
					   关闭
					</button>
					<a href = "Login.jsp" class ="btn btn-primary" role="button">
					  重新登录
					</a>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>