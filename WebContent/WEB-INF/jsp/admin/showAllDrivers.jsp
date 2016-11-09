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
			<td>ID</td><td>姓名</td><td>驾驶证号码</td><td>驾驶车辆</td>
		</tr>
		<c:forEach items="${requestScope.allDrivers }" var="driver">
			<tr>
				<td>${driver.id }</td>
				<td>${driver.name }</td>
				<td>${driver.driverLicenseNo }</td>
				<c:choose>
					<c:when test="${driver.car == null }">
						<td>
							<button type="button" data-toggle="modal" data-target="#setCarForDriver" class="btn btn-default" data-1="${driver.id }">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							</button>
						</td>
					</c:when>
					<c:otherwise>
						<td><a data-toggle="modal" data-target="#deleteCarForDriver" data-1="${driver.id }" data-2="${driver.car.carNo }">${driver.car.carNo }</a></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				<button type="button" style="display:table;margin-left:auto;margin-right:auto;" data-toggle="modal" data-target="#addDriver" class="btn btn-default">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
	</table>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>