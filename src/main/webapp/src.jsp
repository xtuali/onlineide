
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>OnlineIDE--<%=username%></title>
		<link href="./css/inettuts.css" rel="stylesheet" type="text/css" />
		<link href="./css/src.css" rel="stylesheet" type="text/css" />
		<link href="./css/jquery.contextMenu.css" rel="stylesheet"
			type="text/css" />
		<link href="./css/editor.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/slide.css" type="text/css"
			media="screen" />

		<!-- PNG FIX for IE6 -->
		<!-- http://24ways.org/2007/supersleight-transparent-png-in-ie6 -->
		<!--[if lte IE 6]>
			<script type="text/javascript" src="js/pngfix/supersleight-min.js"></script>
		<![endif]-->
		<script type="text/javascript" src="./js/jquery-1.6.js"></script>
		<script type="text/javascript" src="./js/jquery.contextMenu.js"></script>
		<script src="js/slide.js" type="text/javascript"></script>
		<script src="artDialog.js" type="text/javascript"></script>
		<script type="text/javascript" src="./js/vakata.js"></script>
		<script type="text/javascript" src="./js/jstree.core.js"></script>
		<script type="text/javascript" src="./js/jstree.themes.js"></script>
		<script type="text/javascript" src="./js/jstree.ui.js"></script>
		<script type="text/javascript" src="./js/jstree.state.js"></script>
		<script type="text/javascript" src="./js/jstree.json.js"></script>

		<script type="text/javascript">
	  		$(document).ready(function(){
	  			getProjectlist();
	  			//为页面添加keyup事件,修改当前文件的isEdit属性
	  			$("#c2").keyup(function(){
	  				var editfile= $("#editing").text();
	  				fileArray[editfile].isEdit = "yes";
	  				fileArray[editfile].code = $("#c2").val();
	  			//	alert(fileArray[editfile].isEdit);
	  			});
	  			$(".resultconsole").bind('keyup',function(event){
	  				if(event.keyCode == 13){
	  					afterlength=getStrLength($(".resultconsole").val());
	  					var console = $(".resultconsole").val();
	  					console = console.substring(beforelength,afterlength);
	  					alert(console);
	  					$.ajax({
	  						type:"post",
	  						url:"<%=basePath%>compiler.action",
	  						data:"inputdata="+console,
	  						success:function(data){
	  						}
	  					});
	  				}
	  			});
	  			$('#run').click(function(){
	  				alert(active_project);
	  				alert($("#c2").val());
			  		$.getJSON("<%=basePath%>compiler.action?jsoncallback=?", {code:encodeURI($("#c2").val())}, function(data) {
						var info = "运行结果为：<br />";
						info = info + data.Result;
						$('.resultarea').html(info);
						return false;
					});
				});

				$("#column1").contextMenu({
					menu: 'project'
				},
					function(action){
						if(action == "new_pro")
							return new_pro();
						else if(action == "new_pack")
							return new_pack();
						else if(action == "new_class")
							return new_class();
						else if(action == "upload_pro")
							return upload_pro();
						else if(action == "download")
							return download();
						else if(action == "open")
							return open();
						else if(action == "delete")
							return del();
				},"projectMenu");
				
				
				$("#codecontent").contextMenu({
					menu: 'codeMenu'
				},
					function(action){
					if(action == "run")
							return run();
					else if(action == "save")
						return save();
					else if(action == "saveall")
						return saveall();
				},"editor-menu");
				var d = document.getElementById("column1");
	  			d.oncontextmenu = function(){return false};
	  			d = document.getElementById("project");
	  			d.oncontextmenu = function(){return false};
			});
		</script>
		<script language="javascript" type="text/javascript">
			String.prototype.trim2 = function(){
			    return this.replace(/(^\s*)|(\s*$)/g, "");
			};
			function F(objid){
				return document.getElementById(objid).value;
			}
			function G(objid){
				return document.getElementById(objid);
			}
			function changeview(){
				jQuery(function($){
					$("#switchBox").switchTab({defaultIndex: "1", titOnClassName: "active", titCell: "dt em", mainCell: "dd ul", effect: "slide"});
				}); 
			}
		</script>
		<script type="text/javascript">
		//设置jquery缓存用来存储用户编辑的文件，以便上传
		var fileArray={};
		var active_project;		
		var isrunover = "false";
		var isaddlistener = "false";
		var beforelength;
		var afterlength;
		var processInfo = null;
		var zipstatus = -1;
		function showCodebyFilename(name){
	//		alert(name);
			var codeinfo = fileArray[name];
	//		var codeinfo = $("#filedatalist").data(name);
	//		alert('name:'+codeinfo.filename);
			$("#editing").html(codeinfo.filename);
			$("#c2").val(codeinfo.code);
		}
		function getStrLength(str){
			var realLength = 0, len = str.length, charCode = -1;
		    for (var i = 0; i < len; i++) {
		        charCode = str.charCodeAt(i);
		        if (charCode >= 0 && charCode <= 128) realLength += 1;
		        else realLength += 2;
		    }
		    return realLength;
		}
		
		function vchar(str) {
		    str = str.replace(/\+/g, "%2B");
		    str = str.replace(/\&/g, "%26");
		    return str;
		}
		function clearConsole(){
			$(".resultconsole").val("");
		}
	</script>
	</head>
	<body>
		<div id="head">
			<h1>
				OnlineIDE
			</h1>
		</div>

		<div id="columns">
			<ul id="column1" class="column">
				<li class="widget color-green" id="intro">
					<div class="widget-head">
						<h3>
							项目浏览
						</h3>
					</div>
					<div class="widget-content">
						<div id="context">
							<div id="jstree">

							</div>
						</div>

					</div>
					<!--end of widget-content -->
				</li>
				<!-- end of intro -->
			</ul>


			<ul id="column2" class="column">
				<li class="widget color-blue">
					<div class="divRight">
						<!-- 程序叶签显示开始 -->
						<div class="widget-head">
							<h3>
								程序文件
							</h3>
						</div>
						<!-- 程序页签显示结束--------程序代码区开始 -->
						<div class="widget-content">
							<dl class="switchBox" id="switchBox5">
								<dt class="filelist">
								</dt>
							</dl>
							<span id="editing"> </span>
							<ul>
								<li>
									<div class="codecontent" id="codecontent">
										<textarea cols="2" rows="10" id="li" disabled></textarea>
										<textarea name="co" rows="10" wrap="off" id="c2"
											onblur="check('2')" onkeyup="keyUp()"
											onFocus="clearValue('2')"
											onscroll="G('li').scrollTop = this.scrollTop;"
											oncontextmenu="return false" class="grey">
										</textarea>
									</div>
								</li>
							</ul>

							<div class="buttonarea">
								<input type="button" class="review" name="run" id="run"
									value="运行" />
							</div>
						</div>
					</div>
				</li>
				<!-- end of 程序代码区 -->

				<li class="widget color-white">
					<div class="widget-head">
						<h3>
							Console
						</h3>
					</div>
					<div class="widget-content">
						<div class="resultarea">
							<textarea class="resultconsole"></textarea>
						</div>
					</div>
				</li>
				<!-- 程序输出框结束 -->
			</ul>
		</div>


		<div id="filedatalist"></div>
		<!-- 菜单开始  -->
		<div id="project" class="projectMenu">
			<ul>
				<li class="project seprator">
					<a href="#new_pro" id="menu_1">New Project</a>
				</li>
				<li class="package">
					<a href="#new_pack" id="menu_2">New Pack</a>
				</li>
				<li class="class seprator">
					<a href="#new_class" id="menu_3">New Class</a>
				</li>
				<li class="upload seprator">
					<a href="#upload_pro" id="menu_6">Upload Project</a>
				</li>
				<li class="download">
					<a href="#download" id="menu_7">Down thisPJ</a>
				</li>
				<li class="open">
					<a href="#open" id="menu_4">Open</a>
				</li>
				<li class="delete">
					<a href="#delete" id="menu_5">Delete</a>
				</li>
			</ul>
		</div>
		<div>
			<ul id="codeMenu" class="editor-menu">
				<li class="project seprator">
					<a href="#run" id="menu_6">Run</a>
				</li>
				<li class="open">
					<a href="#save" id="menu_8">Save</a>
				</li>
				<li class="delete">
					<a href="#saveall" id="menu_9">SaveAll</a>
				</li>
			</ul>
		</div>
		<!-- 菜单结束  -->

		<!-- 弹出框开始 -->
		<!-- 登陆框 -->
		<div id="toppanel">
			<div id="panel">
				<div class="content clearfix">
					<div class="left">
						<form class="clearfix" action="login.action" method="post">
							<h1 class="padlock">
								用户登录
							</h1>
							<label class="grey" for="log">
								用户名:
							</label>
							<input class="field" type="text" name="username" id="log"
								value="" size="16" />
							<br />
							<label class="grey" for="pwd">
								密 码:
							</label>
							<input class="field" type="password" name="password" id="pwd"
								size="16" />
							<div class="clear"></div>
							<input type="submit" name="submit" value="登 录" class="bt_login" />
							<input type="reset" name="cancel" value="取 消" class="bt_cancel" />
						</form>
					</div>
				</div>
			</div>

			<!-- The tab on top -->
			<div class="tab">
				<ul class="login">
					<li class="left">
						&nbsp;
					</li>
					<li>
						亲爱滴
						<%=username%>
						您好 !
					</li>
					<li class="sep">
						|
					</li>
					<li id="toggle">
						<a id="open" class="open" href="#">Change User</a>
						<a id="close" style="display: none;" class="close" href="#">Close
							Panel</a>
					</li>
					<li class="right">
						&nbsp;
					</li>
				</ul>
			</div>
			<!-- / top -->

		</div>
		<!-- /toppanel -->
		<!-- 登陆框结束 -->


		<!-- 弹出框结束  -->


		<script type="text/javascript"
			src="./js/jquery-ui-personalized-1.6rc2.min.js"></script>
		<script type="text/javascript" src="./js/inettuts.js"></script>
		<script language="javascript" type="text/javascript">
			/*
			* 使左边的textarea显示行号
			*/
			var msgA = [ "msg1", "msg2", "msg3", "msg4" ];
			var c = [ "c1", "c2", "c3", "c4" ];
			var slen = [ 50, 20000, 20000, 60 ];//允许最大字数
			var num = "";
			var isfirst = [ 0, 0, 0, 0, 0, 0 ];
			function isEmpty(strVal) {
				if (strVal == "")
					return true;
				else
					return false;
			}
			function isBlank(testVal) {
				var regVal = /^\s*$/;
				return (regVal.test(testVal));
			}
			function chLen(strVal) {
				strVal = strVal.trim2();
				var cArr = strVal.match(/[^\x00-\xff]/ig);
				return strVal.length + (cArr == null ? 0 : cArr.length);
			}
			function check(i) {
				var iValue = F("c" + i);
				var iObj = G("msg" + i);
				var n = (chLen(iValue) > slen[i - 1]);
				if ((isBlank(iValue) == true) || (isEmpty(iValue) == true) || n == true) {
					iObj.style.display = "block";
				} else {
					iObj.style.display = "none";
				}
			}
			function checkAll() {
				for ( var i = 0; i < msgA.length; i++) {
					check(i + 1);
					if (G(msgA[i]).style.display == "none") {
						continue;
					} else {
						alert("填写错误,请查看红色提示信息！");
						return;
					}
				}
				G("form1").submit();
			}
			function clearValue(i) {
				G(c[i - 1]).style.color = "#000";
				keyUp();
				if (isfirst[i] == 0) {
					G(c[i - 1]).value = "";
				}
				isfirst[i] = 1;
			}
			function keyUp() {
				var obj = G("c2");
				var str = obj.value;
				str = str.replace(/\r/gi, "");
				str = str.split("\n");
				n = str.length;
				line(n);
			}
			function line(n) {
				var lineobj = G("li");
				for ( var i = 1; i <= n; i++) {
					if (document.all) {
						num += i + "\r\n";
					} else {
						num += i + "\n";
					}
				}
				lineobj.value = num;
				num = "";
			}
			function autoScroll() {
				var nV = 0;
				if (!document.all) {
					nV = G("c2").scrollTop;
					G("li").scrollTop = nV;
					setTimeout("autoScroll()", 20);
				}
			}
			if (!document.all) {
				window.addEventListener("load", autoScroll, true);
				
			}
		
		</script>
		<script type="text/javascript">
		//菜单选项的方法设计
			(function($){
				// 改变默认配置
				var d = $.dialog.defaults;
				// 预缓存皮肤，数组第一个为默认皮肤
				d.skin = ['aero', 'chrome', 'facebook', 'default'];
				// 是否开启特效
				d.effect = true;
				// 指定超过此面积的对话框拖动的时候用替身
				//d.showTemp = 100000;
				//d.fixed = true;
				
			})(art);
			
			
			function new_pro(){
				var html = "<span class=\"box\">Please input project name:<input type = 'text' id=\"new_pro_name\"/></span>";
				var result = "";
				art.dialog({
					title:"Create New Project",
					content:html,
					yesFn: function(){
						$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?", {username:'<%=username%>',method:'addProject',parameters:$("#new_pro_name").val()},function(data) {
							result = data.Result;
							//alert(result);
							showResult(result);
							getProjectlist();
							return false;
						}); 
						return true;
						
					},
					noFn: true
				});
			//	getProjectlist();
			
			}
			function new_pack(){
			   var html = "<p class=\"box\">Project : "+$(".jstree-clicked").text()+"</p><span class=\"box\">Please input package name:<input type = 'text' id=\"new_pack_name\" /></span>";
			  
				art.dialog({
					title:"Create New Package",
					content:html,
					yesFn: function(){
						var parmeter ="parameters="+$.trim($(".jstree-clicked").text())+','+$("#new_pack_name").val();
					//	var value  = parameter.serializeArray();
						$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?&"+parmeter, {username:'<%=username%>',method:'addPackage'},function(data) {
							result = data.Result;
							//alert(result);
							showResult(result);
							getProjectlist();
							return false;
						}); 
		//				html = "<ul><li class='jstree-closed'><ins class='jstree-icon jstree-ocl'></ins><a href='#'><ins class='jstree-icon jstree-themeicon'></ins>"+$("#new_pack_name").val()+"</a>";
		//				$(".jstree-clicked").after(html);
					     return true;
					},
					noFn: true
				});
			    
			}
			function new_class(){
			    var html = "<p class=\"box\">Package : "+$(".jstree-clicked").text()+"</p><span class=\"box\">Please input Class name:<input type = 'text' id = \"new_class_name\" /></span>";
			  	var activepro = $(".jstree-clicked").parents("ul").parents(".inproject").find(".pro");
			  	activepro = $.trim(activepro.text());
			  
				art.dialog({
					title:"Create New Class", 
					content:html,
					yesFn: function(){
						var parmeter = "parameters="+activepro+","+$(".jstree-clicked").text()+","+$("#new_class_name").val()+"";
					//	alert(parmeter);
						$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?&"+parmeter, {username:'<%=username%>',method:'addClass'},function(data) {
							result = data.Result;
							//alert(result);
							showResult(result);
							getProjectlist();
							return false;
						}); 
				//		html = "<ul><li><a href='#'><ins class='jstree-icon jstree-themeicon' style='display:none'></ins>"+$("#new_class_name").val()+"</a></li></ul>";
						
				//		$(".jstree-clicked").after(html);
						
				//		 return true;
					},
					noFn: true
				});
			}
			
			function upload_pro(){
			    var html = "<div class='box'><form action='upload.action' method='POST' enctype='multipart/form-data'><input type='file' name='upload' id='upload' /><input type='submit' value='上传' /></form></div>";
				art.dialog({
					title:"Upload Project",
					content:html,
					yesFn: function(){
						
						
					},
					noFn: false
				});
			}
			function download(){
				var pj = $(".jstree-clicked").text().trim();
				var html = "<p class='box'>需要下载的项目：<input type='text' value="+pj+" name='upload_pj' id='upload_pj' /><input type='button' name='down_btn' id='down_btn' value='下载' /><p id='process'></p></p>";
				art.dialog({
					title:"Upload Project",
					content:html,
					yesFn: function(){
						
						
					},
					noFn: false
				});
				$("#down_btn").click(function(){
					pj = $("#upload_pj").val();
					//alert(pj);
					$.ajax({
						type:"POST",
						dataType:"text",
						url:"<%=basePath %>download.action",
						data:"username=<%=username %>"+"&project="+pj,
						success:function(data){
					
						}
					});
					zipstatus = -1;
					processInfo = setInterval("getProcess()",500);
				});
			}
			function getProcess(){
				$.ajax({
						type:"POST",
						dataType:"text",
						url:"<%=basePath %>download.action",
						data:"username=<%=username %>"+"&getProcess=yes",
						success:function(data){
							var status = data.split(",")[0];
							var url = data.split(",")[1];
						//	alert(status);
							if(status == 0 && status > zipstatus){
								$("#process").append("<p class='box'>请稍等...</p>");
								zipstatus = status;
							}else if(status == 1 && status > zipstatus){
								$("#process").append("<p class='box'>正在打包...</p>");
								zipstatus = status;
							}else if(status == 2 && status > zipstatus){
								$("#process").append("<p class='box'>正在生成路径...</p>");
								zipstatus = status;
							}else if(status == 3 && status > zipstatus){
								$("#process").append("<p class='box'>您现在可以：<a href='"+url+"' target='_blank' style='color:blue;'>下载项目</a>有效时间:60秒</p>");
								zipstatus = status;
								clearInterval(processInfo);
							}
							
						}
					});
			}
			function open(){
				if($(".jstree-clicked").attr("class").indexOf("file") >= 0){	//判断是否为文件
					var nodename = $.trim($(".jstree-clicked").text());
					getCodeFromJqueryArray(nodename);
			    }else{
			    	alert("haha");
			    }
			}
			function del(){
				var info="";
				var parameter="";
				var method="";
				var detail="";
				if($(".jstree-clicked").attr("class").indexOf("pro") >= 0){
					var pro = $.trim($(".jstree-clicked").text());
					//直接删除pro的项目
					info = pro+"项目";
					parameter="parameters="+pro;
					method="deleteProject";
					detail = "删除项目时，将会删除该项目下的所有文件！";
				}else if($(".jstree-clicked").attr("class").indexOf("pac") >= 0){
					var pac = $.trim($(".jstree-clicked").text());
					var pro = getProject();
					//删除pro项目下的pac包
					info = pro+"项目下"+pac;
					parameter="parameters="+pro+","+pac;
					method="deletePackage";
					detail = "删除包时，将会删除该包下的所有文件！";
				}else if($(".jstree-clicked").attr("class").indexOf("file") >= 0){
					var clazz = $.trim($(".jstree-clicked").text());
					var pac = getPackage();
					var pro = getProject();
					//删除pro项目下的pac包下的clazz文件
					info = pro+"项目下"+pac+"包下"+clazz;
					parameter="parameters="+pro+","+pac+","+clazz;
					method="deleteClass";
				}
				saveall();
				
				var html="<p class='box'>"+detail+"一旦删除就不可恢复！！！<br /><br />确定要删除:"+info+"吗？<br /></p>";
				art.dialog({
					title:"确认删除信息", 
					content:html,
					yesFn: function(){
						$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?&"+parameter, {username:'<%=username%>',method:method},function(data) {
							result = data.Result;
							showResult(result);
							
							return false;
						}); 
					},
					noFn: true
				});
				
			}
			
			//讲用户在前台编辑的文件发送到后台保存，默认只保存当前文件
			function save(){
				var currentFile = $("#editing").text();
				if(fileArray[currentFile].isEdit == "yes"){
					//此文件已被用户编辑过，需上传
					var code = vchar(encodeURI($("#c2").val()));
				//	alert(code);
					var parameters = "parameters="+fileArray[currentFile].project+","+fileArray[currentFile].package+","+currentFile+"&username=<%=username%>&method=saveOneFile&code="+code;
					clearConsole();
					$.ajax({
						type:"POST",
						url:"<%=basePath%>fileoperation.action",
						data:parameters,
						success:function(data){
						//	showResult(data);
							$(".resultconsole").val(data);
							fileArray[currentFile].isEdit = "no";
							return false;
						}
					});
				}else{
					$(".resultconsole").val($(".resultconsole").val()+"\n"+currentFile+"已保存");
				}
			}
			
			//讲用户在前台编辑过的文件发送到后台保存，1.先全部上传到服务器上,2.编译
			var saveResult = true;
			function saveall(){
				//上传
				for(var currentFile in fileArray){
				//	alert(fileArray[key].filename);
					if(fileArray[currentFile].isEdit == "yes"){
						var parameters = "parameters="+fileArray[currentFile].project+","+fileArray[currentFile].package+","+currentFile+","+fileArray[currentFile].code+"&username=<%=username%>&method=saveOneFile";
						$.ajax({
							type:"POST",
							url:"<%=basePath%>fileoperation.action",
							data:parameters,
							success:function(data){
								$(".resultconsole").val($(".resultconsole").val()+data);
								fileArray[currentFile].isEdit = "no";
								saveResult = saveResult && 
							}
						});
					}
				}
				return saveResult;
			}
			
			function run(){
				saveall();
				var currentFile = $("#editing").text();
				var classname = currentFile;
				var packagename = fileArray[classname].package;
				var projectname = fileArray[classname].project;
				clearConsole();
				isrunover = "false";
				$.ajax({
					type:"POST",
					dataType:"text",
					url:"<%=basePath%>compiler.action",
					data:"projectname="+projectname+"&packagename="+packagename+"&classname="+currentFile,
					success:function(res){
						
					//	var info = "run:运行结果为：\n";
					//	var msg = data.split(",");
					//	$(".resultconsole").val($(".resultconsole").val()+msg[0]);
					}
				});
				runlistener();
		//		$.getJSON("<%=basePath%>compiler.action?jsoncallback=?&"+parameters, function(data) {
		//			var info = "run:运行结果为：<br />";
		//			info = info + data.Result;
		//		//	$('.resultarea').append(info);
		//			return false;
		//		});
			}
			function runlistener(){
			//	alert("runlistener()");
				$.ajax({
					type:"POST",
					dataType:"text",
					url:"<%=basePath%>compiler.action",
					data:"username=<%=username%>&isgetResults=true",
					success:function(data){
				//	alert(data);
						var result = data.split(",");
						var info = "listener:\n";
						isrunover = result[1];
					//	alert(result[0]);
						if(result[0] == ""){
							//程序暂时没有取到结果
							info = "";
						}else{
							info = result[0];
							$(".resultconsole").val($(".resultconsole").val() + info);
							beforelength = getStrLength($(".resultconsole").val());
						}
					}
				});
				//程序运行结果出来，需要结束监听;
			//	alert(isrunover);
				if(isrunover == "true"){
					setTimeout(runlistener,999999);
				}else{
					setTimeout(runlistener,500);
				}
			}
			function getProject(){
				if($(".jstree-clicked").attr("class").indexOf("pro") >= 0){
					//如果当前节点是项目
					return $.trim($(".jstree-clicked").text());
				}else if($(".jstree-clicked").attr("class").indexOf("pac") >= 0){
					//如果当前节点是包
					var activepro = $(".jstree-clicked").parents("ul").parents(".inproject").find(".pro");
			  		activepro = $.trim(activepro.text());
					return activepro;
				}else if($(".jstree-clicked").attr("class").indexOf("file") >= 0){
					//如果当前节点是文件
					var activepackage = $(".jstree-clicked").parents("ul").parents(".inpackage").find(".pac");
					var activeproject = activepackage.parents("ul").parents(".inproject").find(".pro");
					
					var pro = $.trim(activeproject.text());
					return pro;
				}else{
					return "";
				}
			}
			
			function getPackage(){
				if($(".jstree-clicked").attr("class").indexOf("pac") >= 0){
					//如果当前节点是包
					var nodename = $.trim($(".jstree-clicked").text());
					return nodename;
				}else if($(".jstree-clicked").attr("class").indexOf("file") >= 0){
					//如果当前节点是文件
					var activepackage = $(".jstree-clicked").parents("ul").parents(".inpackage").find(".pac");
					var pac = $.trim(activepackage.text());
					return pac;
				}else{
					return "";
				}
			}
			
			function showResult(rs){
		//		alert(rs);
				art.dialog({
					icon:'success',
				    time: 1,
				    content: '<span class= \"box\"> '+rs +'</span>'
				});
			}
			function getCodeFromJqueryArray(filename){
				var file = fileArray[filename];
			//	var file = $("#filedatalist").data(filename);
			//	alert(file)
				if(file==undefined){
					//从服务器下载代码
					var activepackage = $(".jstree-clicked").parents("ul").parents(".inpackage").find(".pac");
					var activeproject = activepackage.parents("ul").parents(".inproject").find(".pro");
					
				//	alert(activepackage.text());
				//	alert(activeproject.text());
					
			//		var pro = $.trim(activeproject.text());
			//		var pac = $.trim(activepackage.text());
					var pro = getProject();
					var pac = getPackage();
					var file = filename;
					$.ajax({
						type:"post",
						url:"<%=basePath%>fileoperation.action",
						data:"parameters="+pro+","+pac+","+file+"&username=<%=username%>&method=getClassFileContent",
						success:function(data){
							var html= "<a href='#' onclick ='showCodebyFilename(\""+filename+"\")' class='isSave "+filename+"'>"+filename+"</a>";
							$(".filelist").append(html);
							var code = data;
						//	alert(data);
							fileArray[filename] = {
								"filename":filename,
								"code":code,
								"isEdit":"no",
								"package":pac,
								"project":pro
							};
							showCodebyFilename(filename);
							return false;
						}
					});
				}else{
					showCodebyFilename(filename);
				}
			}
			function getResult(){
				$.getJSON();
			}
		</script>
		<script type="text/javascript">
			var STATE = false;
			function save_state() {
				STATE = $('#jstree').jstree('get_state');
			}
			
			function getProjectlist() {
				/*
				* 向服务器请求项目列表
				*/
				$.ajax({
					url:"<%=basePath%>programfile/<%=username%>/<%=username%>_pro.xml",
					dataType:"xml",
					type:"post",
					success:function(xml){
						var projectlist = "";
				//		alert(projectlist);
						$(xml).find("project").each(function(){
							var html="";
							//遍历project
							var pro = $(this);
							html = "<ul><li class='jstree-open inproject' ><ins class='jstree-icon jstree-ocl'></ins><a href = '#' class='pro'>"+pro.attr("name")+"</a>";
							//遍历每一个project下面的package
							pro.find("package").each(function(){
								var pack = $(this);
								html = html+"<ul><li class='jstree-open inpackage'><ins class='jstree-icon jstree-ocl'></ins><a href='#' class='pac'><ins class='jstree-icon jstree-themeicon'></ins>"+pack.attr("name")+"</a>";
								 pack.find("class").each(function(){
									var classname = $(this);
									html = html +  "<ul><li><a href='#' class='file'><ins class='jstree-icon jstree-themeicon' style='display:none'></ins>"+classname.attr("name")+"</a></li></ul>";
								 });
								html = html + "</li></ul>";
							});
							html =html + "</li></ul>";
							projectlist = projectlist + html;
						});
					//	alert(projectlist);
						$("#jstree").html(projectlist);
					}
				});
				toTree();
			}
			/*
			*生成项目列表树
			*/
		
			function toTree(){
				$("#jstree")
				.jstree()
				.bind('__ready.jstree', function () {
					save_state();
				})
				.bind("create_node.jstree", function (e, data) { 
					alert("Created `" + data.inst.get_text(data.rslt.obj) + "` inside `" + (data.rslt.parent === -1 ? 'the main container' : data.inst.get_text(data.rslt.parent)) + "` at index " + data.rslt.position);
				});
			}
	</script>

	</body>
</html>