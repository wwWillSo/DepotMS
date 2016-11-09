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
			<td>车牌</td><td>所属班次</td><td>品牌</td><td>座位</td><td>注册日期</td><td>保险日期</td><td>行驶证号码</td><td>司机列表</td><td>操作</td>
		</tr>
		<c:forEach items="${requestScope.allCars }" var="car">
			<tr>
				<td>${car.carNo }</td>
				<c:choose>
					<c:when test="${car.classes == null }">
						<td>
							<button type="button" style="display:table;margin-left:auto;margin-right:auto;" data-toggle="modal" data-target="#setClassesForCar" class="btn btn-default" data-1="${car.carNo }">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							</button>
						</td>
					</c:when>
					<c:otherwise>
						<td><a data-toggle="modal" data-target="#deleteClassesForCar" data-0="${car.carNo }" data-1="${car.classes.classesId }" data-2="${car.classes.line.lineId }" data-3="${car.classes.time }" data-4="${car.classes.note }">${car.classes.classesId }</a></td>
					</c:otherwise>
				</c:choose>
				<td>${car.brand }</td>
				<td>${car.seat }</td>
				<td>${car.date_register }</td>
				<td>${car.date_insurance }</td>
				<td>${car.drivingLicenseNo }</td>
				<td>
					<c:forEach items="${car.drivers }" var="driver">
						<a data-toggle="modal" data-target="#deleteDriverForCar" data-0="${car.carNo }" data-1="${driver.id }" data-2="${driver.name }">${driver.name }</a> 、
					</c:forEach>
				</td>
				<td>
					<button type="button" style="display:table;margin-left:auto;margin-right:auto;" data-toggle="modal" data-target="#setDriverForCar" class="btn btn-default" data-1="${car.carNo }">
						指定司机
					</button>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="9">
				<button type="button" style="display:table;margin-left:auto;margin-right:auto;" data-toggle="modal" data-target="#addCar" class="btn btn-default">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
	</table>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>