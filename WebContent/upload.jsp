<%@ page language="java"  pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link href="jquery.uploadify/default.css"  rel="stylesheet" type="text/css"/>
	<link href="jquery.uploadify/uploadify.css"  rel="stylesheet" type="text/css"/>
	
	<script type="text/javascript" src="./js/jquery-1.6.js"></script>
	<script type="text/javascript" src="jquery.uploadify/swfobject.js" ></script>
	<script type="text/javascript" src="jquery.uploadify/jquery.uploadify.v2.0.3.min.js" ></script>
	<script type="text/javascript"><!--
	
		$(document).ready(function(){
			$("#uploadTest").uploadify({
				'uploader'       : 'jquery.uploadify/uploadify.swf',
				'script'         : 'upload.action',
				'cancelImg'      : 'jquery.uploadify/cancel.png',
				'fileDataName'   : 'myFile',
				'queueID'        : 'fileQueue',
				'auto'           : false,
				'multi'          : true,
				'button Text'    : " 浏 览 ",
				'simUploadLimit' : 20,
				'sizeLimit'      : 999999999999,
				'queueSizeLimit' : 20,
				'fileDesc'       : '支持格式：zip',
				'fileExt'         : '*.zip'
			});
		});
// --></script>
   
  </head>
  
  <body>
  
  <div id="fileQueue"></div>
  			<input type="file"  name="myFile" id="uploadTest" >
  			<a href="javascript:$('#uploadTest').uploadifyUpload()" >上传</a>
			
  </body>
</html>