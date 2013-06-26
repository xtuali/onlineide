function new_pro() {
	var html = "<span class=\"box\">Please input project name:<input type = 'text' id=\"new_pro_name\"/></span>";
	var result = "";
	art.dialog({
		title : "Create New Project",
		content : html,
		yesFn : function() {
			$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?", {
				username : '<%=username%>',
				method : 'addProject',
				parameters : $("#new_pro_name").val()
			}, function(data) {
				result = data.Result;
				// alert(result);
				showResult(result);
				getProjectlist();
				return false;
			});
			return true;

		},
		noFn : true
	});
	// getProjectlist();

}
function new_pack() {
	var html = "<p class=\"box\">Project : "
			+ $(".jstree-clicked").text()
			+ "</p><span class=\"box\">Please input package name:<input type = 'text' id=\"new_pack_name\" /></span>";

	art.dialog({
		title : "Create New Package",
		content : html,
		yesFn : function() {
			var parmeter = "parameters=" + $.trim($(".jstree-clicked").text())
					+ ',' + $("#new_pack_name").val();
			// var value = parameter.serializeArray();
			$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?&"
					+ parmeter, {
				username : '<%=username%>',
				method : 'addPackage'
			}, function(data) {
				result = data.Result;
				// alert(result);
				showResult(result);
				getProjectlist();
				return false;
			});
			// html = "<ul><li class='jstree-closed'><ins class='jstree-icon
			// jstree-ocl'></ins><a href='#'><ins class='jstree-icon
			// jstree-themeicon'></ins>"+$("#new_pack_name").val()+"</a>";
			// $(".jstree-clicked").after(html);
			return true;
		},
		noFn : true
	});

}
function new_class() {
	var html = "<p class=\"box\">Package : "
			+ $(".jstree-clicked").text()
			+ "</p><span class=\"box\">Please input Class name:<input type = 'text' id = \"new_class_name\" /></span>";
	var activepro = $(".jstree-clicked").parents("ul").parents(".inproject")
			.find(".pro");
	activepro = $.trim(activepro.text());

	art.dialog({
		title : "Create New Class",
		content : html,
		yesFn : function() {
			var parmeter = "parameters=" + activepro + ","
					+ $(".jstree-clicked").text() + ","
					+ $("#new_class_name").val() + "";
			// alert(parmeter);
			$.getJSON("<%=basePath%>xmloperation.action?jsoncallback=?&"
					+ parmeter, {
				username : '<%=username%>',
				method : 'addClass'
			}, function(data) {
				result = data.Result;
				// alert(result);
				showResult(result);
				getProjectlist();
				return false;
			});
			// html = "<ul><li><a href='#'><ins class='jstree-icon
			// jstree-themeicon'
			// style='display:none'></ins>"+$("#new_class_name").val()+"</a></li></ul>";

			// $(".jstree-clicked").after(html);

			// return true;
		},
		noFn : true
	});
}

function upload_pro() {
	var html = "<div class='box'><form action='upload.action' method='POST' enctype='multipart/form-data'><input type='file' name='upload' id='upload' /><input type='submit' value='上传' /></form></div>";
	art.dialog({
		title : "Upload Project",
		content : html,
		yesFn : function() {

		},
		noFn : false
	});
}
function download() {
	var pj = $(".jstree-clicked").text().trim();
	var html = "<p class='box'><input type='text' value="
			+ pj
			+ " name='upload_pj' id='upload_pj' /><input type='button' name='down_btn' id='down_btn' value='下载' /><p id='process'></p></p>";
	art.dialog({
		title : "Upload Project",
		content : html,
		yesFn : function() {

		},
		noFn : false
	});
	$("#down_btn").click(function() {
		pj = $("#upload_pj").val();
		// alert(pj);
		$.ajax({
			type : "POST",
			dataType : "text",
			url : "<%=basePath %>download.action",
			data : "username=<%=username %>" + "&project=" + pj,
			success : function(data) {

			}
		});
		zipstatus = -1;
		processInfo = setInterval("getProcess()", 500);
	});

}
function getProcess() {
	$
			.ajax({
				type : "POST",
				dataType : "text",
				url : "<%=basePath %>download.action",
				data : "username=<%=username %>" + "&getProcess=yes",
				success : function(data) {
					var status = data.split(",")[0];
					var url = data.split(",")[1];
					// alert(status);
					if (status == 0 && status > zipstatus) {
						$("#process").append("<p class='box'>请稍等...</p>");
						zipstatus = status;
					} else if (status == 1 && status > zipstatus) {
						$("#process").append("<p class='box'>正在打包...</p>");
						zipstatus = status;
					} else if (status == 2 && status > zipstatus) {
						$("#process").append("<p class='box'>正在生成路径...</p>");
						zipstatus = status;
					} else if (status == 3 && status > zipstatus) {
						$("#process")
								.append(
										"<p class='box'>您现在可以：<a href='"
												+ url
												+ "' target='_blank' style='color:blue;'>下载项目</a>有效时间:60秒</p>");
						zipstatus = status;
						clearInterval(processInfo);
					}

				}
			});
}
function open() {

	if ($(".jstree-clicked").attr("class").indexOf("file") >= 0) { // 判断是否为文件
		var nodename = $.trim($(".jstree-clicked").text());
		getCodeFromJqueryArray(nodename);
	} else {
		alert("haha");
	}
}
function del() {
	var menu_id = $('a').attr('id');
	alert(menu_id + ' :: Delete Function fired from menu_action.js file');
}

// 讲用户在前台编辑的文件发送到后台保存，默认只保存当前文件
function save() {
	var currentFile = $("#editing").text();
	if (fileArray[currentFile].isEdit == "yes") {
		// 此文件已被用户编辑过，需上传
		var code = vchar(encodeURI($("#c2").val()));
		// alert(code);
		var parameters = "parameters=" + fileArray[currentFile].project + ","
				+ fileArray[currentFile].package + "," + currentFile
				+ "&username=<%=username%>&method=saveOneFile&code=" + code;
		clearConsole();
		$.ajax({
			type : "POST",
			url : "<%=basePath%>fileoperation.action",
			data : parameters,
			success : function(data) {
				// showResult(data);
				$(".resultconsole").val(data);
				fileArray[currentFile].isEdit = "no";
				return false;
			}
		});
	} else {
		$(".resultconsole").val(
				$(".resultconsole").val() + "\n" + currentFile + "已保存");
	}
}

// 讲用户在前台编辑过的文件发送到后台保存，1.先全部上传到服务器上,2.编译
function saveall() {
	// 上传
	for ( var currentFile in fileArray) {
		// alert(fileArray[key].filename);
		if (fileArray[currentFile].isEdit == "yes") {
			var parameters = "parameters=" + fileArray[currentFile].project
					+ "," + fileArray[currentFile].package + "," + currentFile
					+ "," + fileArray[currentFile].code
					+ "&username=<%=username%>&method=saveOneFile";
			$.ajax({
				type : "POST",
				url : "<%=basePath%>fileoperation.action",
				data : parameters,
				success : function(data) {
					$(".resultconsole").val($(".resultconsole").val() + data);
					fileArray[currentFile].isEdit = "no";
					return false;
				}
			});
		}
	}
}

function run() {
	saveall();
	var currentFile = $("#editing").text();
	var classname = currentFile;
	var packagename = fileArray[classname].package;
	var projectname = fileArray[classname].project;
	clearConsole();
	isrunover = "false";
	$.ajax({
		type : "POST",
		dataType : "text",
		url : "<%=basePath%>compiler.action",
		data : "projectname=" + projectname + "&packagename=" + packagename
				+ "&classname=" + currentFile,
		success : function(res) {

			// var info = "run:运行结果为：\n";
			// var msg = data.split(",");
			// $(".resultconsole").val($(".resultconsole").val()+msg[0]);
		}
	});
	runlistener();
	// $.getJSON("<%=basePath%>compiler.action?jsoncallback=?&"+parameters,
	// function(data) {
	// var info = "run:运行结果为：<br />";
	// info = info + data.Result;
	// // $('.resultarea').append(info);
	// return false;
	// });
}
function runlistener() {
	// alert("runlistener()");
	$.ajax({
		type : "POST",
		dataType : "text",
		url : "<%=basePath%>compiler.action",
		data : "username=<%=username%>&isgetResults=true",
		success : function(data) {
			// alert(data);
			var result = data.split(",");
			var info = "listener:\n";
			isrunover = result[1];
			// alert(result[0]);
			if (result[0] == "") {
				// 程序暂时没有取到结果
				info = "";
			} else {
				info = result[0];
				$(".resultconsole").val($(".resultconsole").val() + info);
				beforelength = getStrLength($(".resultconsole").val());
			}
		}
	});
	// 程序运行结果出来，需要结束监听;
	// alert(isrunover);
	if (isrunover == "true") {
		setTimeout(runlistener, 999999);
	} else {
		setTimeout(runlistener, 500);
	}
}

function showResult(rs) {
	// alert(rs);
	art.dialog({
		icon : 'success',
		time : 1,
		content : '<span class= \"box\"> ' + rs + '</span>'
	});
}
function getCodeFromJqueryArray(filename) {
	var file = fileArray[filename];
	// var file = $("#filedatalist").data(filename);
	// alert(file)
	if (file == undefined) {
		// 从服务器下载代码
		var activepackage = $(".jstree-clicked").parents("ul").parents(
				".inpackage").find(".pac");
		var activeproject = activepackage.parents("ul").parents(".inproject")
				.find(".pro");

		// alert(activepackage.text());
		// alert(activeproject.text());

		var pro = $.trim(activeproject.text());
		var pac = $.trim(activepackage.text());
		var file = filename;
		$.ajax({
			type : "post",
			url : "<%=basePath%>fileoperation.action",
			data : "parameters=" + pro + "," + pac + "," + file
					+ "&username=<%=username%>&method=getClassFileContent",
			success : function(data) {
				var html = "<a href='#' onclick ='showCodebyFilename(\""
						+ filename + "\")' class='isSave " + filename + "'>"
						+ filename + "</a>";
				$(".filelist").append(html);
				var code = data;
				// alert(data);
				fileArray[filename] = {
					"filename" : filename,
					"code" : code,
					"isEdit" : "no",
					"package" : pac,
					"project" : pro
				};
				showCodebyFilename(filename);
				return false;
			}
		});
	} else {
		showCodebyFilename(filename);
	}
}
