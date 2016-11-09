<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

});
</script>
<body>
	<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>工号</td><td>姓名</td><td>部门</td><td>组别</td><td>密码</td><td>家庭住址</td><td>经纬度</td><td>安排站点</td>
		</tr>
		<tr>
			<td>${sessionScope.staff.staffNo }</td>
			<td>${sessionScope.staff.name }</td>
			<td>${sessionScope.staff.dept.name }</td>
			<td>${sessionScope.staff.groupName }</td>
			<td>${sessionScope.staff.password }</td>
			<td>${sessionScope.staff.home }</td>
			<td>${sessionScope.staff.home_longitude} , ${sessionScope.staff.home_latitude} </td>
			<td>${sessionScope.staff.station.stationAddress }</td>
			
		</tr>
	</table>
	
	<c:if test="${sessionScope.staff.home_longitude == null }" >
		<h4 style="float:right"><font color="red">尚未指定家庭地址经纬度，请尽快进行设置!</font></h4>
	</c:if>
	
	
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>