<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>
<body>
	<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>班次</td><td>所属线路</td><td>出发时间</td><td>备注</td><td>属于此班次的车辆</td><td>操作</td>
		</tr>
		<c:forEach items="${requestScope.allClasses }" var="classes">
			<tr>
				<td>${classes.classesId }</td>
				<td>${classes.line.lineName }</td>
				<td>${classes.time }</td>
				<td>${classes.note }</td>
				<td><c:forEach items="${classes.cars }" var="car">
					<a data-toggle="modal" data-target="#deleteCarForClasses" data-1="${classes.classesId }" data-2="${car.carNo }">${car.carNo }</a> 、
				</c:forEach></td>
				<td>
					<button type="button" data-toggle="modal" data-target="#setCarForClasses" data-1="${classes.classesId }">安排车辆</button>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<strong style="display:table;margin-left:auto;margin-right:auto;">添加班次请到"查看线路信息-->安排班次"进行</strong>
			</td>
		</tr>
	</table>
</body>
</html>