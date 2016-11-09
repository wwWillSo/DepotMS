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
	
	$("#deptName").find("option[id='"+ $("#selectedDept").val() +"']").attr("selected", true) ;
	
	$("#check").click(function() {
		window.location.href = "showAllStaff_bigger.do?keyword=" + $("#keyword").val()
								+ "&deptName=" + $("#deptName").val()
								+ "&pageSize=" + $("#pageSize").val()
								+ "&pageNo=" + 1
	})
	
	$(".pagination >> a").click(function () {
		
		$("#pageNo").val(this.id) ;
		
		window.location.href = "showAllStaff_bigger.do?keyword=" + $("#keyword").val()
								+ "&deptName=" + $("#deptName").val()
								+ "&pageSize=" + $("#pageSize").val()
								+ "&pageNo=" + $("#pageNo").val() ;
	})
	
})
</script>
<body>
<div id="showAllStaff" class="center" style="width:100%"> 
	<form action="" method="post">
			关键字：<input type="text" name="keyword" id="keyword" value="${requestScope.keyWord }"/>
		
		<input type="hidden" id="selectedDept" value="${requestScope.deptName }"/>
		
		部门：<select id="deptName">
   				<option value="" id="">请选择...</option>
   				<c:forEach items="${requestScope.allDepts }" var="dept">
					<option value="${dept.name }" id="${dept.name }">${dept.name }</option>
				</c:forEach>
			</select>
			
			每页显示条数：<input type="text" name="pageSize" id="pageSize" value="${requestScope.pageSize }"/> 
			页码：<input type="text" name="pageNo" id="pageNo" value="${requestScope.pageNo }"/>
			<button type="button" id="check" class="btn btn-default">查询</button>
			<strong>${requestScope.pageNo } / ${requestScope.pageCount }</strong>
	</form>

		<table style="width:100%;" border="1" class="table table-hover table-striped table-responsive">
		<tr>
			<td>工号</td><td>姓名</td><td>部门</td><td>组别</td><td>密码</td><td>家庭住址</td><td>经纬度</td><td>安排站点</td><td>操作</td>
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
				<td><a href="#">修改信息</a></td>		<!-- 这里 用AJAX做吧，返回一个Json显示结果就好了 5-24 -->
			</tr>
		</c:forEach>
	</table>
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
</div>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>