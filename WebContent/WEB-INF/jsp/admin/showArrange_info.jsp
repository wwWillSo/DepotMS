<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>
<h3>这里是${dayArrange.date.month }月${dayArrange.date.date }日的排班表<button type="button" id="returnButton" style="float:right;">返回</button></h3>
<body>
	<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>司机编号</td><td>司机姓名</td><td>车辆</td><td>班次</td><td>线路</td><td>时间</td>
		</tr>
		<c:forEach items="${requestScope.arrangeInfos }" var="arrangeInfo">
			<tr>
				<td>${arrangeInfo.driver.id }</td>
				<td>${arrangeInfo.driver.name }</td>
				<td>${arrangeInfo.driver.car.carNo }</td>
				<td>${arrangeInfo.driver.car.classes.classesId }</td>
				<td>${arrangeInfo.driver.car.classes.line.lineName }</td>
				<td>${arrangeInfo.driver.car.classes.time }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>