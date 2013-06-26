$(document).ready(function(){
	$(".resultconsole").bind('keyup',function(event){
		if(event.keyCode == 13){	//一旦用户按下回车键，则代表输入完成
			afterlength=getStrLength($(".resultconsole").val());
			var console = $(".resultconsole").val();
			console = console.substring(beforelength,afterlength);	//截取Console用户输入的数据
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
});


function run(){
	saveall();
	var currentFile = $("#editing").text();
	var classname = currentFile;
	var packagename = fileArray[classname].package;
	var projectname = fileArray[classname].project;
	clearConsole();	
	isrunover = "false";	//设置程序是否运行完毕的标志，默认是false
	//发出运行程序的指令
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"<%=basePath%>compiler.action",
		data:"projectname="+projectname+"&packagename="+packagename+"&classname="+currentFile,
		success:function(res){

		}
	});
	runlistener();	//开始监听服务器上的运行结果
}
function runlistener(){
	$.ajax({
		type:"POST",
		dataType:"text",
		url:"<%=basePath%>compiler.action",
		data:"username=<%=username%>&isgetResults=true",
		success:function(data){
			var result = data.split(",");
			var info = "listener:\n";
			isrunover = result[1];
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
	if(isrunover == "true"){
		setTimeout(runlistener,999999);	//程序运行结束，关闭监听
	}else{
		setTimeout(runlistener,500);
	}
}
