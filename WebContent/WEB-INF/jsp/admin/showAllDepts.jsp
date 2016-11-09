<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="css/admin_main.css" rel="stylesheet">
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
	
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
})
</script>
<body>
	<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>部门编号</td><td>部门名称</td>
		</tr>
		<c:forEach items="${requestScope.allDepts }" var="dept">
			<tr>
				<td>${dept.deptNo }</td>
				<td>${dept.name }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">
				<button type="button" style="display:table;margin-left:auto;margin-right:auto;" data-toggle="modal" data-target="#addDept" class="btn btn-default">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
	</table>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>