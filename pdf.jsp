<%@ page language="java" import="java.util.*,java.io.*,java.text.*" pageEncoding="GB2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
    + request.getServerName() + ":" + request.getServerPort()
    + path + "/";
	String a = request.getParameter("path").trim();
	a = new String(a.getBytes("ISO8859-1"), "GB2312");
%>
<!DOCTYPE HTML>
 <html>
	 <head>
	     <title>阳光采配中心</title>
		 <meta name="renderer" content="webkit|ie-comp|ie-stand,initial-scale=1"">
		 <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		 <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
			 .table th, .table td { 
				text-align: center; 
				vertical-align: middle!important;
			}
		 </style>
		 <base href="<%=basePath%>">
	</head>
	<%
	   out.clear();
	   out = pageContext.pushBody();
	   response.setContentType("application/pdf");

	   try {
		//获取本地时间
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String heretime = dateFormat.format(now);  //本地时间
		System.out.println("/pdf/"+heretime+a+".pdf");
		String strPdfPath = new String("pdf/"+heretime+"/"+a+"1.pdf");
		//判断该路径下的文件是否存在
		File file = new File(strPdfPath);
		if (file.exists()) {
		 DataOutputStream temps = new DataOutputStream(response
		   .getOutputStream());
		 DataInputStream in = new DataInputStream(
		   new FileInputStream(strPdfPath));

		 byte[] b = new byte[2048];
		 while ((in.read(b)) != -1) {
		  temps.write(b);
		  temps.flush();
		 }

		 in.close();
		 temps.close();
		} else {
		 out.print(strPdfPath + " 文件不存在!");
		}

	   } catch (Exception e) {
		out.println(e.getMessage());
	   }
	%>
	<body>
	  <br>
	  <h2>${path}</h2>
   </body>
</html>