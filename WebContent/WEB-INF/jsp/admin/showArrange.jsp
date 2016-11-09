<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>
<h3>这里是${requestScope.monthArrange.monthArrangeId }月份的排班表</h3>
<body>
	<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>周数</td><td colspan="7">日期</td>
		</tr>
		<c:forEach items="${requestScope.weekArranges }" var="weekArrange">
			<tr>
				<td>第${weekArrange.weekArrangeId }周</td>
				<c:forEach items="${requestScope.dayArranges }" var="dayArrange">
					<c:if test="${dayArrange.weekArrange.weekArrangeId == weekArrange.weekArrangeId }">
						<td>
						<a id="${dayArrange.dayArrangeId }">${dayArrange.date.month }-${dayArrange.date.date }</a>
						</td>
					</c:if>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>