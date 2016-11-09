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

	<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive" id="allLines">
		<tr>
			<td>线路ID</td><td>线路名称</td><td>总人数</td><td>乘坐率</td><td>此线路的班次</td><td>操作</td>
		</tr>
		<c:forEach items="${requestScope.allLines }" var="line">
			<tr>
				<td>${line.lineId }</td>
				<td>${line.lineName }</td>
				<td>${line.population }</td>
				<td>${line.rate }</td>
				<td>
					<c:forEach items="${line.classes }" var="classes">
						<a data-toggle="modal" data-target="#deleteClassesForLine" data-1="${classes.classesId }" data-2="${classes.line.lineId }" data-3="${classes.time }" data-4="${classes.note }" data-5="${classes.cars.size() }">${classes.classesId }</a> 、
					</c:forEach>
				</td>
				<td>
					<button type="button" value="${line.lineId }" class="showAllLines_button1">查看详情</button>
					<button type="button" data-toggle="modal" data-target="#addClassesForLine" data-whatever="${line.lineId }">安排班次</button>
					<button type="button" data-toggle="modal" data-target="#deleteLine" data-1="${line.lineId }" data-2="${line.lineName }" data-3="${line.population }" data-4="${line.rate }">删除线路</button>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<button type="button" style="display:table;margin-left:auto;margin-right:auto;" data-toggle="modal" data-target="#addLine" class="btn btn-default">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
	</table>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>