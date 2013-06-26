<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String)session.getAttribute("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>OnlineIDE</title>
		<link href="./css/inettuts.css" rel="stylesheet" type="text/css" />
		<link href="./css/src.css" rel="stylesheet" type="text/css" />
		<link href="./css/vscontext.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/slide.css" type="text/css" media="screen" />

		<script type="text/javascript" src="./js/jquery-1.6.js"></script>
		<script src="js/slide.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="head">
			<h1> 
				OnlineIDE <%=this.getClass().getClassLoader().getClass().getCanonicalName() %>
			</h1>
		</div>

	
    	
    	<!-- 弹出框开始 -->
    	<!-- 登陆框 -->
		<div id="toppanel">
			<div id="panel">
				<div class="content clearfix">
					<div class="left">
						<form class="clearfix" action="register.action" method="post">
							<h1 class="padlock">用户注册</h1>
							<label class="grey" for="log">用户名:</label>
							<input class="field" type="text" name="username" id="log" value="" size="16" /><br />
							<label class="grey" for="pwd">密　码:</label>
							<input class="field" type="password" name="password" id="pwd" size="16" />
		        			<div class="clear"></div>
							<input type="submit" name="submit" value="注 册" class="bt_login" />
							<input type="reset" name="cancel" value="取 消" class="bt_cancel" />
						</form>
					</div>
				</div>
			</div> 
		
		    <!-- The tab on top -->	
			<div class="tab">
				<ul class="login">
			    	<li class="left">&nbsp;</li>
			        <li>亲爱滴 游客 您好 !</li>
					<li class="sep">|</li>
					<li id="toggle">
						<a id="open" class="open" href="#">User Register</a>
						<a id="close" style="display: none;" class="close" href="#">Close Panel</a>			
					</li>
			    	<li class="right">&nbsp;</li>
				</ul> 
			</div> <!-- / top -->
			
		</div> <!-- /toppanel --><!-- 登陆框结束 -->
		
		
		<!-- 弹出框结束  -->
		
	
	</body>
</html>