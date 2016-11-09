<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>DepotMS</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
   
</head>
<style>
	.center {
	  width: auto;
	  display: table;
	  margin-left: auto;
	  margin-right: auto;
	  margin-top:100px;
	}
	.text-center {
	 display: table;
	   	width: auto;
	 	 margin-left: auto;
	 	 margin-right: auto;
	}
	.jumbotron .center {
		
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//main
	//showSelfInfo
	//changePassword
	//changeHome
	//info
	
	//关闭所有用于交互的DIV块
	function closeAllDiv(flag) {
		if (flag == "hide") {
			$("#main").hide() ;
			$("#showSelfInfo").hide() ;
			$("#changePassword").hide() ;
			$("#changeHome").hide() ;
			$("#info").hide() ;
		} else if (flag == "slideUp"){
			$("#main").slideUp() ;
			$("#showSelfInfo").slideUp() ;
			$("#changePassword").slideUp() ;
			$("#changeHome").slideUp() ;
			$("#info").slideUp() ;
		}
	}
	
	closeAllDiv("hide") ;
	$("#main").show() ;
	
	$("#showSelfInfo_open").click(function() {
		
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showSelfInfo.do" ,
			success: function (result) {
				$("#showSelfInfo_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		$("#showSelfInfo").slideDown("slow") ;
	})
	
	
	//修改密码
	$("#changePassword_open").click(function() {
		
		closeAllDiv("slideUp") ;
		
		$("#changePassword").slideDown("slow") ;
	})
	
	$("#changePasswordConfirm").click(function() {
		
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"changePassword.do?newPwd=" + $("#newPwd").val() ,
			success: function (result) {
				var obj = eval('(' + result + ')'); 
				$("#info").text(obj.info) ;
				$("#info").slideDown("slow") ;
			}
		})
	})
	
	//修改住址
	$("#changeHome_open").click(function() {
		
		closeAllDiv("slideUp") ;
		
		
		$("#changeHome").slideDown("slow") ;
		
	})
	
	$("#changeHomeForward").click(function () {
		window.open("changeHomeForward.do") ;
	})
	
	
});
</script>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="jumbotron" style="background-image: url('images/6.jpg');">
			  <h1><font class="text-center">用户${sessionScope.staff.staffNo },你好！</font></h1>
		</div>
	</div>
	<div class="row-fluid">
		<div class="btn-group btn-group-vertical col-md-1">
			<button type="button" id="showSelfInfo_open" class="btn btn-default">查看信息</button>
			<button type="button" id="changePassword_open" class="btn btn-default">修改密码</button>
			<button type="button" id="changeHome_open" class="btn btn-default">修改住址</button>
		</div>
		
		<div id="main" class="col-md-6 col-md-offset-4">
			<h2>请点击<strong>左边的按钮</strong>选择功能</h2>
		</div>
		
		<div id="showSelfInfo" class="col-md-11">
			<div id="showSelfInfo_main">
			
			</div>
		</div>
		
		<div id="changePassword" class="col-md-11">
			<div id="changePassword_main" class="center">
				<form action="" method="post" id="changePasswordForm">
				<div class="form-group">
					<label for="oldPwd">原密码：</label>
					<input type="text" name="oldPwd" id="oldPwd" placeholder="原密码" value="${sessionScope.staff.password }" readonly="readonly"/><br>
				</div>
				<div class="form-group">
					<label for="newPwd">新密码：</label>
					<input type="text" name="newPwd" id="newPwd" placeHolder="新密码"/><br>
				</div>
					<button type="button" class="btn btn-default" id="changePasswordConfirm" style="float:right;">确认</button>
				</form>
			</div>
		</div>
		
		<div id="changeHome" class="col-md-11">
			<div id="changeHome_main">
				<button type="button" class="btn btn-default center" id="changeHomeForward">进入地图</button>
			</div>
		</div>
		
		
		<!-- 用于显示错误信息 -->
		<div id="info" class="col-md-4 col-md-offset-4">

		</div>
		
	</div>
</div>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>
