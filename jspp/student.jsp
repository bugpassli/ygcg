<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" href="Content/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="Content/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="Content/font/font-awesome.min.css"/>
<title>订单查询界面</title>
</head>
	<body > 
	<nav class="Hui-breadcrumb"><i class="icon-home"></i>首页  <span class="c-gray en">&gt;</span> 订单管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i></a></nav>
	
	<div class="pd-20">
	  <div class="text-c"> 
	  	
	    <form action="ChaXunTable.do" method="post">
	    <span>查询格式为：2017-08-08</span>
	    <input type="text" class="input-text" style="width:250px" placeholder="输入查询时间" id="time" name="time">
		<input type="hidden" name="username" id="username" value="${username}">
	   <button type="submit" class="btn btn-success" id="time" name="time"><i class="icon-search"></i> 搜索订单</button>
	 </form>
	 </div>
	 <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="r">共有数据：<strong>${studentCount }</strong> 条</span> </div>
	 <table class="table table-border table-bordered table-hover table-bg table-sort">
	   <thead>
	     <tr class="text-c">			
					<th>用户名</th>				
					<th>供应商</th>					
					<th>商品</th>
					<th>提供量</th>
					<th>单价</th>										
					<th>需求量</th>
					<th>总价</th>
					<th>采购方</th>
					<th>时间</th>
					<th>采购员</th>
			
	     </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${list}" var="student">
	   	<tr class="text-c">
	       <td>${student.yongHuMing }</td>
	       <td>${student.gongYingShang }</td>	      	      
	       <td>${student.shangPin }</td>
	       <td>${student.tiGongLiang }</td>	      
	       <td>${student.danJia }</td>	       	      
	       <td>${student.xuQiuLiang }</td>
	       <td>${student.zongJia }</td>
	      	    	      
	    	<%//用标签实现判断
	    	String biaoJi=null;
	    	%>
	    	<c:choose>
	    		<c:when test="${student.biaoJi==0 }">
	    		
	    			<%
	    	    biaoJi="一食堂";
	    	   %>
	       <td><%=biaoJi %></td>
	    		</c:when>
	    		<c:when test="${student.biaoJi==1 }">
	    		
	    			<%
	    	   biaoJi="二食堂";%>
	       <td><%=biaoJi %></td>
	    		</c:when>
	    		
	    	</c:choose>	       	      	      
	    	<td>${student.time }</td>
	       <td>${student.caiGouYuan }</td>	 
	    	
	     </tr>
	   
	   </c:forEach>
	     
	    </tbody>
	  </table>
	  <div id="pageNav" class="pageNav"></div>
	</div>
	<script type="text/javascript" src="Content/js/jquery.min.js"></script> 
	<script type="text/javascript" src="Content/layer/layer.min.js"></script> 
	<script type="text/javascript" src="Content/js/pagenav.cn.js"></script> 
	<script type="text/javascript" src="Content/js/H-ui.js"></script> 
	<script type="text/javascript" src="Content/plugin/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="Content/js/jquery.dataTables.min.js"></script> 
	<script type="text/javascript" src="Content/js/H-ui.admin.js"></script> 
	<script type="text/javascript">
	
	</script>
	</body>
</html>