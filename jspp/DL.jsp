<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow-y:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<link href="Content/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="Content/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="Content/font/font-awesome.min.css"/>
<title>欢迎进入阳关采购系统</title>
<%
 String zh= (String)session.getAttribute("zh");
 String bz= (String)session.getAttribute("bz");
 if(zh==null||bz==null){
	response.sendRedirect("DL.jsp");
 }
%>
</head>
<body style="overflow:hidden">

<header class="Hui-header cl"> 
	<a class="Hui-logo l"  href="#">黄山学院后勤集团阳光采购报价系统</a>
    <a class="Hui-logo-m l" href="#" >黄山学院后勤集团阳光采购报价系统</a> 
    <span class="Hui-subtitle l">1.0</span> 
    <span class="Hui-userbox"><span class="c-white">用户:<%=bz%></span> 
    	<a class="btn btn-out radius ml-10" href="kitch.jsp" title="退出">
    		<i class="icon-off"></i> 退出
    	</a>
    </span>
    <a aria-hidden="false" class="Hui-nav-toggle" id="nav-toggle" href="#"></a>
</header>

<div class="cl Hui-main">
  <aside class="Hui-aside" style="">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
      <dl id="menu-article">
	  <%
	  String dex=bz.substring(0,2);
	  String ht="";
	  java.util.Date date=new java.util.Date();
	  int day=2;//date.getDay();
	  int hours = 2;//date.getHours();
	  if(bz.length()==4&&(dex.equals("10"))||dex.equals("70")||dex.equals("13")||dex.equals("14"))
	  {
		  ht="tgbj.jsp";
		}
	  else {
		if(day==2)
		  {ht="tgbj2.jsp";}
		else
		  {ht="tx.jsp";}
		 
	  }
	  if(hours>=12){
			ht="cg12.jsp";//超过12点无法报价
		}
	  %>
        <dt><a _href=<%=ht%> href="javascript:;"><i class="icon-edit"></i> 提供报价<b></b></a></dt>
      </dl>
      <dl id="menu-comments">
        <dt><a _href="HistorySearch.jsp" href="javascript:;"><i class="icon-book"></i> 历史查询<b></b></a></dt>
      </dl>
      <dl id="menu-article">
        <dt><a  _href="thcx.jsp" href="javascript:void(0)"><i class="icon-user"></i> 同行询价<b></b></a></dt>
        
      </dl>

	  <dl id="menu-article">
        <dt><a  _href="xiugaimima.jsp" href="javascript:void(0)"><i class="icon-key"></i>修改密码<b></b></a></dt>
        
      </dl>
	  <dl id="menu-article">
        <dt><a  href="kitch.jsp" ><i class="icon-key"></i>退出系统<b></b></a></dt>
        
      </dl>
    </div>
  </aside>
  <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);"></a></div>
  <section class="Hui-article">
    <div id="Hui-tabNav" class="Hui-tabNav">
      <div class="Hui-tabNav-wp">
        <ul id="min_title_list" class="acrossTab cl">
          <li class="active"><span title="我的桌面" data-href="welcomegy.jsp">欢迎来到黄山学院后勤集团阳光采购报价系统</span><em></em></li>
        </ul>
      </div>
      <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default btn-small" href="javascript:;"><i class="icon-step-backward"></i></a><a id="js-tabNav-next" class="btn radius btn-default btn-small" href="javascript:;"><i class="icon-step-forward"></i></a></div>
    </div>
    
    <!-- iframe小型框架  -->
    <div id="iframe_box" class="Hui-articlebox">
      <div class="show_iframe">
        <div style="display:none" class="loading"></div>
        <iframe scrolling="yes" frameborder="0" src="welcomegy.jsp"></iframe>
      </div>
    </div>
  </section>
</div>
<script type="text/javascript" src="Content/js/jquery.min.js"></script>
<script type="text/javascript" src="Content/js/Validform_v5.3.2_min.js"></script> 
<script type="text/javascript" src="Content/layer/layer.min.js"></script>
<script type="text/javascript" src="Content/js/H-ui.js"></script>
<script type="text/javascript" src="Content/js/H-ui.admin.js"></script>

</body>
</html>