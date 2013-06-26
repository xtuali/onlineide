<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="common.jsp" %>
		<div id="head">
			<h1> 
				OnlineIDE 
			</h1>
		</div>
    	
    	<!-- 弹出框开始 -->
    	<!-- 登陆框 -->
		<div id="toppanel">
			<div id="panel">
				<div class="content clearfix">
					<div class="left">
						<form class="clearfix" action="${path }user/login.do" method="post">
							<h1 class="padlock">用户登录</h1>
							<label class="grey" for="log">用户名:</label>
							<input class="field" type="text" name="username" id="log" value="" size="16" /><br />
							<label class="grey" for="pwd">密　码:</label>
							<input class="field" type="password" name="userpass" id="pwd" size="16" />
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
			    	<li class="left">&nbsp;</li>
			        <li>亲爱滴 游客 您好 !</li>
					<li class="sep">|</li>
					<li id="toggle">
						<a id="open" class="open" href="#">User Login</a>
						<a id="close" style="display: none;" class="close" href="#">Close Panel</a>			
					</li>
			    	<li class="right">&nbsp;</li>
				</ul> 
			</div> <!-- / top -->
			
		</div> <!-- /toppanel --><!-- 登陆框结束 -->
		
		<!-- 弹出框结束  -->
<%@ include file="footer.jsp" %>