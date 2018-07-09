<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
 <html>
	 <head>
	     <title>招标系统</title>
		 <meta name="renderer" content="webkit|ie-comp|ie-stand,initial-scale=1"">
		 <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		 <meta http-equiv="X-UA-Compatible" content="IE=edge">
		 <object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2  height="0" width="0">  
		 </object>
		 <meta name="viewport" content="width=device-width, initial-scale=1">
		 <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		 <link rel="stylesheet" href="bootstrap/css/awesome-bootstrap-checkbox.css">
		 <link rel="stylesheet" href="bootstrap/Font-Awesome/css/font-awesome.min.css"/>
         <link rel="stylesheet" href="bootstrap/css/build.css"/>
         <script src="js/jquery-2.1.4.min.js"></script>
		 <script src="js/jquery.min.js"></script>
		 <script src="bootstrap/js/bootstrap.min.js"></script>
		 <script src="bootstrap/js/bootstrap.js"></script>
		 <script src="js/jquery.cookie.js"></script>
		 <script src="js/printThis.js"></script>
		 <script language="javascript" src="js/jquery.jqprint.js"></script>
		 <style type="text/css">
			 body {
				background-image:url(image/09.jpg);
				width:100%;
				height:100%;
				background-size:100% 100%;
			}
		 </style>
		 <script type="text/javascript">
			 $(function(){
				//按钮单击时执行
				$("#fat-btn").click(function(){
					var obj = document.getElementById("selectzhi"); //定位id
					var index = obj.selectedIndex; // 选中索引
					var text = obj.options[index].text; // 选中文本
					//Ajax调用处理
					alert("${ProductID}"); 
					$.ajax({
						data:"YongHuShang="+text+"&CaiGuoYuan=${username}&ProductID=${ProductID}",
						type:"POST",
						contentType: "application/x-www-form-urlencoded; charset=GB2312",
						url:"YongHuShang.do",
						error:function(data){ 
						   alert("出错了！！:"); 
						}, 
						success:function(data){
							if (data != null) {
								 alert(data);
							}
						} 

					});
				 });
			});
		 </script>
		 <script type="text/javascript">   
		  function doPrint() {   
			bdhtml=window.document.body.innerHTML;   
			sprnstr="<!--startprint-->";   
			eprnstr="<!--endprint-->";   
			prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+245);   
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));   
			window.document.body.innerHTML=prnhtml;  
			window.print();   
		}   
		</script>
      
	</head>
	<body>
		<!--startprint-->
		<div class="container">
			<div class="row">
				 <div class="jumbotron" style=" text-align:center; height:150px; " >
					<h2>采购商查询</h2> 
				 </div>
			</div>
			<form role="form" class="form-horizontal" action = "tablevalue.do" 
						method = "get" id = "loginForm">
				<div class = "row">
					<div class="container">
						<table class =" nav nav-list well  table table-hover " 
						   value = "" contenteditable="false" >
							<thead>
							   <tr>
									<th>#</th>
									<th><i class = "glyphicon glyphicon-user"></i>商家</th>
									<th><i class = "glyphicon glyphicon-shopping-cart"></i>产品</th>
									<th><i class = "glyphicon glyphicon-yen">单价</th>
									<th><i class = "glyphicon glyphicon-inbox">单位</th>
									<th><i class = "glyphicon glyphicon-plus">需求量</th>
									<th><i class = "glyphicon glyphicon-yen">总价</th>
							   </tr>
							</thead>
							<tbody>
								<c:forEach var="zl" items="${list}">
									<tr class = "active">
										<td>${zl.id}</td>
										<td>${zl.gongYingShang}</td>
										<td>${zl.shangPin}</td>
										<td>${zl.danJia}</td>
										<td>${zl.danWei}</td>
										<td>${zl.xuQiuLiang}</td>
										<td>${zl.zongJia}</td>
									</tr>
								</c:forEach>
													
							</tbody>
						</table>
				    </div>
					<div class = "container" style="height:150px;">
						<div class="row">
						  <button type="button"  class = "btn btn-large btn-block btn-primary
						  btn btn-primary btn-lg " onclick="window.print();" />
								打印表格
						  </button>
						</div>
					</div>
				</div>
		    </form>
		</div>
		<div id="footer" class="container">
			<nav class="navbar navbar-default navbar-fixed-bottom">
				<div class="navbar-inner navbar-content-center">
					<p class="text-muted credit" style="padding: 8px ;background: #333 ; text-align:center ;">
						COPYRIGHT @ 2016 - 2017 黄山学院蓝豹小组软件研发 ALL RIGHTS RESERVED
						欢迎热爱您的使用
					</p>
				</div>
			</nav>
	    </div>
	   <!--endprint--> 
   </body>
</html>