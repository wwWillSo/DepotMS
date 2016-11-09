<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
	$("#adminLogin").click(function() {
		$("#adminIdError").text("") ;
		$("#passwordError").text("") ;
		if ($("#adminId").val() == "") {
			$("#adminIdError").text("账号不能为空！") ;
			return false ;
		}
		if ($("#password").val() == "") {
			$("#passwordError").text("密码不能为空！") ;
			return false ;
		}
		var regex = /^[A-Za-z0-9]+$/ ;
		if (regex.test($("#adminId").val())) {			
			
			//格式正确的话继续验证数据库是否存在此用户
			$.ajax({
				url:"adminValidate.do?adminId=" + $("#adminId").val() + "&password=" + $("#password").val() ,
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					$("#adminIdError").text(obj.info) ;
					if (obj.info == "验证成功") {
						$("#adminLoginForm").submit() ;
					}
				}
			})
			
		} else {
			$("#adminIdError").text("账号格式有误！") ;
			return false ;
		}
	})
});
</script>
<body>

<div class="jumbotron" style="height:20%; background-image: url('images/6.jpg');">
<div class="container-fluid">
  <h1><font class="text-center">车厂管理系统</font></h1>
</div>
</div>
 
<nav class="nnavbar navbar-inverse navbar-fixed-bottom">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="toUserLogin.do">用户登陆 <span class="sr-only">(current)</span></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

	<div class="center">
		<form action="adminLogin.do" method="post" id="adminLoginForm">
			<div class="form-group">
				<label for="adminId">管理员ID：</label>
				<input type="text" name="adminId" id="adminId" placeholder="管理员ID"/><br>
			</div>
			<div class="form-group">
				<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				<input type="password" name="password" id="password" placeHolder="密码"/><br>
			</div>
				<button type="button" class="btn btn-default" id="adminLogin" style="float:right;">登录</button>
		</form>
	</div>
	<div id="loginError" class="center">
		<span id="adminIdError"></span>
		<span id="passwordError"> </span>
    </div>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>