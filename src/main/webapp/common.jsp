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
		<title>OnlineIDE--login</title>
		<link href="./css/inettuts.css" rel="stylesheet" type="text/css" />
		<link href="./css/src.css" rel="stylesheet" type="text/css" />
		<link href="./css/vscontext.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/slide.css" type="text/css" media="screen" />

		<script type="text/javascript" src="./js/jquery-1.6.js"></script>
		<script src="js/slide.js" type="text/javascript"></script>
	</head>
	<body>