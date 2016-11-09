<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>
<body>
<form action="" method="post">
			关键字：<input type="text" name="keyword" id="keyword" value="${requestScope.keyWord }"/>
			<!-- 部门： <input type="text" name="deptName" id="deptName" value="${requestScope.deptName }"/>  -->
			<input type="hidden" id="selectedDept" value="${requestScope.deptName }"/>
		部门：<select id="deptName">
   				<option value="" id="">请选择...</option>
   				<c:forEach items="${requestScope.allDepts }" var="dept">
	   				<c:choose>
	   					<c:when test="${dept.name == requestScope.deptName }">
	   						<option value="${dept.name }" id="${dept.name }" selected="selected">${dept.name }</option>
	   					</c:when>
	   					<c:otherwise>
	   						<option value="${dept.name }" id="${dept.name }">${dept.name }</option>
	   					</c:otherwise>
	   				</c:choose>
				</c:forEach>
			</select> 
			每页显示条数：<input type="text" name="pageSize" id="pageSize" value="${requestScope.pageSize }"/> 
			<!-- <input type="hidden" name="pageSize" id="pageSize" value="5"/>  -->
			<!-- 页码：<input type="text" name="pageNo" id="pageNo" value="1"/>  -->
			<input type="hidden" name="pageNo" id="pageNo" value="${requestScope.pageNo }"/>
			<button type="button" id="check" class="btn btn-default">查询</button>
			<strong>${requestScope.pageNo } / ${requestScope.pageCount }</strong>
	</form>
		<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>工号</td><td>姓名</td><td>部门</td><td>组别</td><td>密码</td><td>家庭住址</td><td>经纬度</td><td>安排站点</td>
		</tr>
		<c:forEach items="${requestScope.allStaffs }" var="staff">
			<tr>
				<td>${staff.staffNo }</td>
				<td>${staff.name }</td>
				<td>${staff.dept.name }</td>
				<td>${staff.groupName }</td>
				<td>${staff.password }</td>
				<td>${staff.home }</td>
				<td>${staff.home_longitude} , ${staff.home_latitude} </td>
				<td>${staff.station.stationName }</td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" class="btn btn-default" id="showAllStaff_bigger" style="float:right;">换种方式展示员工信息</button>
	<strong>总共${requestScope.allStaffsCount }条记录</strong>
<nav>
	  <ul class="pagination">
	    <li>
	      <a href="#Previous" aria-label="Previous" id="1">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <li>
	      <a href="#pre" aria-label="Previous" id="${requestScope.pageNo == 1? 1 : requestScope.pageNo-1 }">上一页</a>
	    </li>
	    <c:forEach var="pageNum" begin="1" end="${requestScope.pageCount}">
	   	 	<li><a href="#${pageNum }" id="${pageNum }">${pageNum }</a></li>
	    </c:forEach>
	    <li>
	      <a href="#pre" aria-label="Next" id="${requestScope.pageNo == requestScope.pageCount? requestScope.pageCount : requestScope.pageNo + 1 }">下一页</a>
	    </li>
	    <li>
	      <a href="#Next" aria-label="Next" id="${requestScope.pageCount }">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
 	</nav>
 	
</body>
</html>