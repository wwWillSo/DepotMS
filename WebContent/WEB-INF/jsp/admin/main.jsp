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
   <!--  <script src="js/jquery.min.js"></script> -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
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
</style>
<script type="text/javascript">
$(document).ready(function(){
	//现在页面上用于交互的DIV有：
	//addStaffForm
	//info
	//main
	//error
	//showAllStaff
	//showAllClasses
	//showArrange
	//showAllCars
	//showAllLines
	//setStation
	//setFactoryStation
	//showAllDepts
	//showTheMap_bigger
	//showAllDrivers
	//intel
	//showArrange_info
	
	//关闭所有用于交互的DIV块
	function closeAllDiv(flag) {
		if (flag == "hide") {
			$("#addStaffForm").hide() ;
			$("#error").hide() ;
			$("#info").hide() ;
			$("#main").hide() ;
			$("#showAllStaff").hide() ;
			$("#showAllClasses").hide() ;
			$("#showArrange").hide() ;
			$("#showAllCars").hide() ;
			$("#showAllLines").hide() ;
			$("#setStation").hide() ;
			$("#setFactoryStation").hide() ;
			$("#showAllDepts").hide() ;
			$("#showTheMap_bigger").hide() ;
			$("#showAllDrivers").hide() ;
			$("#intel").hide() ;
			$("#showArrange_info").hide() ;
		} else if (flag == "slideUp"){
			$("#addStaffForm").slideUp() ;
			$("#error").slideUp() ;
			$("#info").slideUp() ;
			$("#main").slideUp() ;
			$("#showAllStaff").slideUp() ;
			$("#showAllClasses").slideUp() ;
			$("#showArrange").slideUp() ;
			$("#showAllCars").slideUp() ;
			$("#showAllLines").slideUp() ;
			$("#setStation").slideUp() ;
			$("#setFactoryStation").slideUp() ;
			$("#showAllDepts").slideUp() ;
			$("#showTheMap_bigger").slideUp() ;
			$("#showAllDrivers").slideUp() ;
			$("#intel").slideUp() ;
			$("#showArrange_info").slideUp() ;
		}
	}
	
	closeAllDiv("hide") ;
	$("#main").show() ;
	
	$("#addStaffForm_open").click(function() {
		
		closeAllDiv("slideUp") ;
		$("#addStaffForm").slideDown("slow") ;
	})
	
	$("#showAllStaff_open").click(function() {
		
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllStaff.do?keyword=" + ""
					+ "&deptName=" + ""
					+ "&pageSize=" + 5
					+ "&pageNo=" + 1 ,
			success: function (result) {
				$("#showAllStaff_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		
		$("#showAllStaff").slideDown("slow") ;
		
	})
	
	$("#showAllStaff").on("mouseenter", function() {
		$("#check").click(function() {
			closeAllDiv("slideUp") ;
			
			$("#pageNo").val(1) ;
			
			//$("#deptName").find("option[id='"+ $("#deptName_request").val() +"']").attr("selected","selected");
			
			$.ajax({
				url:"showAllStaff.do?keyword=" + $("#keyword").val()
						+ "&deptName=" + $("#deptName").val()
						+ "&pageSize=" + $("#pageSize").val()
						+ "&pageNo=" + $("#pageNo").val() ,
				success: function (result) {
					$("#showAllStaff_main").html(result) ;
				},
				error:function () {
					alert("error!") ;
				}
			})
			
			$("#showAllStaff").slideDown("slow") ;
			
			this.off() ;
			
		})
		
		$(".pagination >> a").click(function () {
			closeAllDiv("slideUp") ;
			
			$("#pageNo").val(this.id) ;
			
			$.ajax({
				url:"showAllStaff.do?keyword=" + $("#keyword").val()
						+ "&deptName=" + $("#deptName").val()
						+ "&pageSize=" + $("#pageSize").val()
						+ "&pageNo=" + $("#pageNo").val() ,
				success: function (result) {
					$("#showAllStaff_main").html(result) ;
				},
				error:function () {
					alert("error!") ;
				}
			})
			
			$("#showAllStaff").slideDown("slow") ;
			
			this.off() ;
		})
		
		$("#showAllStaff_bigger").click(function () {
			window.open("showAllStaff_bigger.do?keyword=" + ""
							+ "&deptName=" + ""
							+ "&pageSize=" + 10
							+ "&pageNo=" + 1) ;
			
			this.off() ;
		})
	})
	
	$("#addStaff").click(function() {
		$.ajax({
			url:"admin_addStaff_validate.do?staffNo=" + $("#staffNo").val() + "&deptNo=" + $("#deptNo").val() ,
			success: function (result) {
				var obj = eval('(' + result + ')'); 
				$("#error").text(obj.info) ;
				$("#error").show() ;
				if (obj.info == "此雇员编号可用！") {
					$.ajax({
						url:"admin_addStaff.do?staffNo=" + $("#staffNo").val()
								+ "&staffName=" + $("#staffName").val()
								+ "&deptNo=" + $("#deptNo").val() 
								+ "&password=" + $("#password").val() ,
						success: function (result) {
							var obj = eval('(' + result + ')'); 
							closeAllDiv("slideUp") ;
							
							$("#info").text(obj.info) ;
							$("#info").show() ;
						}
					})
					
				}
			}
		})
	}) 
	
	
	//查看班次
	$("#showAllClasses_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllClasses.do" ,
			success: function (result) {
				$("#showAllClasses_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showAllClasses").slideDown("slow") ;
	})
	
	$("#showAllClasses").on("mouseenter", function() {
		
		
	})
	
	//查看排班表
	$("#showArrange_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showArrange.do" ,
			success: function (result) {
				$("#showArrange_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showArrange").slideDown("slow") ;
	})
	
	$("#showArrange").on("mouseenter", function() {
		$("td > a").click(function () {
			$("#showArrange").hide("slow") ;
			
			$.ajax({
				url:"getArrangeInfoByDayArrangeId.do?dayArrangeId=" + this.id,
				success:function(result) {
					$("#showArrange_info_main").html(result) ;
				},
				error:function() {
					alert("error!!") ;
				}
			})
			
			$("#showArrange_info").slideDown("slow") ;
		})
	})
	
	$("#showArrange_info").on("mouseenter", function() {
		$("h3 > #returnButton").click(function() {
			$("#showArrange_info").hide("slow") ;
			
			$("#showArrange").slideDown("slow") ;
		})
	})
	
	//查看车辆
	$("#showAllCars_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllCars.do" ,
			success: function (result) {
				$("#showAllCars_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showAllCars").slideDown("slow") ;
	})
	
	$("#showAllCars").on("mouseenter", function() {
		
		
	})
	
	//查看线路
	$("#showAllLines_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllLines.do" ,
			success: function (result) {
				$("#showAllLines_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showAllLines").slideDown("slow") ;
	})
	
	$("#showAllLines").on("mouseenter", function() {
		$(".showAllLines_button1").click(function () {
			window.open("showTheMap.do?lineId=" + this.value) ;
		})
		$(".showAllLines_button2").click(function () {
			//window.open("addClassesForLine.do?lineId=" + this.value) ;
		})
		this.off() ;
	})
	
	//设置站点
	$("#setStation_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$("#setStation").slideDown("slow") ;
	})
	
	$("#setStation_main >> #confirm").click(function () {
		if ($("#lineId").val() != "0") {
			window.open("setStationForward.do?lineId=" + $("#lineId").val()) ;
		} else {
			alert("请选择线路！") ;
		}
	})
	
	//设置厂区所在站点
	$("#setFactoryStation_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$("#setFactoryStation").slideDown("slow") ;
	})
	
	$("#setFactoryStation_main > #confirm").click(function () {
		window.open("setFactoryStationForward.do") ;
	})
	
	//查看所有部门
	$("#showAllDepts_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllDepts.do" ,
			success: function (result) {
				$("#showAllDepts_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showAllDepts").slideDown("slow") ;
	})
	
	//查看所有司机
	$("#showAllDrivers_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllDrivers.do" ,
			success: function (result) {
				$("#showAllDrivers_main").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showAllDrivers").slideDown("slow") ;
	})
	
	//查看地图
	$("#showTheMap_bigger_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$("#showTheMap_bigger").slideDown("slow") ;
	})
	
	$("#showTheMap_bigger_main > #confirm").click(function () {
		window.open("showTheMap_bigger.do") ;
	})
	
	//开启智能按钮列表
	$("#intel_open").click(function () {
		closeAllDiv("slideUp") ;
		
		$("#intel").slideDown("slow") ;
	})
	
});

function change(deptNo) {
	document.getElementById("deptNo").value=""+deptNo ;
	//document.getElementById("staffNo").value=""+deptNo ;
}

function change_deptName_request(deptName) {
	document.getElementById("deptName_request").value=""+deptName ;	
}
</script>
<body>

<!-- 
<div id="adminOptions">
	<button type="button" id="addStaffForm_open" class="btn btn-default">添加雇员</button>
	<button type="button" id="showAllStaff_open" class="btn btn-default">查看雇员</button>
	<button type="button" id="" class="btn btn-default">管理员信息</button>
</div>
 -->
<div class="container-fluid">
	<div class="row-fluid">
		<div class="jumbotron" style="background-image: url('images/6.jpg');">
			  <h1><font class="text-center">管理员${sessionScope.admin.adminId },你好！</font></h1>
		</div>
	</div>
	<div class="row-fluid">
		<div class="btn-group btn-group-vertical col-md-1">
			<button type="button" id="addStaffForm_open" class="btn btn-default">添加雇员</button>
			<button type="button" id="showAllStaff_open" class="btn btn-default">查看雇员</button>
			<button type="button" id="showAllDepts_open" class="btn btn-default">查看部门</button>
			<button type="button" id="showAllDrivers_open" class="btn btn-default">司机列表</button>
			<button type="button" id="" class="btn btn-default">管理员信息</button>
			<button type="button" id="showArrange_open" class="btn btn-default">查看排班表</button>
			<button type="button" id="showAllClasses_open" class="btn btn-default">班次信息</button>
			<button type="button" id="showAllCars_open" class="btn btn-default">车辆信息</button>
			<button type="button" id="showAllLines_open" class="btn btn-default">线路信息</button>
			<button type="button" id="setStation_open" class="btn btn-default">设置站点</button>
			<button type="button" id="setFactoryStation_open" class="btn btn-default">设置厂区</button>
			<button type="button" id="showTheMap_bigger_open" class="btn btn-default">查看地图</button>
			<button type="button" id="intel_open" class="btn btn-default">智能按钮</button>
		</div>
		
		<div id="main" class="col-md-6 col-md-offset-4">
			<h2>请点击<strong>左边的按钮</strong>选择功能</h2>
		</div>
		
		<div class="row-fluid">
			<div id="info" class="col-md-4 col-md-offset-4">
				 
			</div>
		</div>
		
		<div id="addStaffForm" class="col-md-11">
			<form action="" method="post" id="form_addStaff">
				<div class="form-group">
			  		 <label for="deptName">部门名称：</label>
		   			<select class="form-control" onChange="change(this.value)">
		   				<option value="0">请选择...</option>
		   				<c:forEach items="${requestScope.allDepts }" var="dept">
							<option value="${dept.deptNo }">${dept.name }</option>
						</c:forEach>
					</select>
				</div> 
				<div class="form-group">
					<label for="staffNo">雇员ID：<input type="text" name="deptNo" id="deptNo"/>+</label>
					<input type="text" name="staffNo" id="staffNo" placeHolder="雇员ID"/><br>
				</div>
				<div class="form-group">
					<label for="staffName">雇员姓名：</label>
					<input type="text" name="staffName" id="staffName" placeHolder="雇员姓名"/><br>
				</div>
				<div class="form-group">
					<label for="password">登陆密码：</label>
					<input type="text" name="password" id="password" placeHolder="登陆密码" value="666666" readonly="readonly"/><br>
				</div>
				<button type="button" class="btn btn-default" id="addStaff" style="float:right;">添加</button>
			</form>
		</div>
		
		<div class="row-fluid">
			<div id="error" class="col-md-4 col-md-offset-5">
				<span id="addStaffError"> </span>
			</div>
	    </div>
	    
		<div id="showAllStaff" class="col-md-11"> 
			<div id="showAllStaff_main" >
				
			</div>
		</div>
		
		<div id="showAllClasses" class="col-md-11">
			<div id="showAllClasses_main" >
				
			</div>
		</div>
		
		<div id="showArrange" class="col-md-11">
			<div id="showArrange_main">
			
			</div>
		</div>
		
		<div id="showArrange_info" class="col-md-11">
			<div id="showArrange_info_main">

			</div>
		</div>
		
		<div id="showAllCars" class="col-md-11">
			<div id="showAllCars_main">
			
			</div>
		</div>
		
		<div id="showAllLines" class="col-md-11">
			<div id="showAllLines_main">
			
			</div>
		</div>
		
		<div id="setStation" class="col-md-11">
			<div id="setStation_main">
				<form action="" method="post">
					<div class="form-group">
			  		 	<label for="lineId">请选择需要设置站点的线路：</label>
		   				<select class="form-control" id="lineId">
			   				<option value="0">请选择...</option>
			   				<c:forEach items="${requestScope.allLines }" var="line">
								<option value="${line.lineId }">${line.lineName }</option>
							</c:forEach>
						</select>
					</div> 
					<button type="button" class="btn btn-default" id="confirm" style="float:right;">确定并进入地图</button>
				</form>
			</div>
		</div>
		
		<div id="setFactoryStation" class="col-md-11">
			<div id="setFactoryStation_main">
				<button type="button" class="btn btn-default center" id="confirm">进入地图</button>
			</div>
		</div>
		
		<div id="showAllDepts" class="col-md-11"> 
			<div id="showAllDepts_main" >
				
			</div>
		</div>
		
		<div id="showTheMap_bigger" class="col-md-11">
			<div id="showTheMap_bigger_main">
				<button type="button" class="btn btn-default center" id="confirm">进入地图</button>
			</div>
		</div>
		
		<div id="showAllDrivers" class="col-md-11">
			<div id="showAllDrivers_main">
				
			</div> 
		</div>
		
		<div id="intel" class="col-md-11">
			<div id="intel_main">
				<button type="button" class="btn default-btn" id="setStationForAllStaff">一键设置所有员工上车地点</button>
				<button type="button" class="btn default-btn" id="setLineAndStationByIntel">一键安排线路及站点</button>
				<button type="button" class="btn default-btn" id="setDriversArrangeByIntel">一键安排司机排班</button>
			</div> 
		</div>
		
		<script>
		//一键设置所有员工上车地点
		$("#setStationForAllStaff").click(function () {
			
			$.ajax({
				url:"setStationForAllStaff.do",
				
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					alert(obj.info) ;
					window.location.reload() ;
				}
			})
			
		})
		
		//一键安排线路及站点
		$("#setLineAndStationByIntel").click(function () {
			
			if (confirm("确定要进行此操作吗？") == true) {
				$.ajax({
					url:"setLineAndStationByIntel.do",
					
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						alert(obj.info) ;
						window.location.reload() ;
					}
				})
			}
			
		})
		
		//一键设置司机排班
		$("#setDriversArrangeByIntel").click(function () {
			
			if (window.confirm("确定要进行此操作吗？") == true) {
				$.ajax({
					url:"setDriversArrageByIntel.do",
					
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						alert(obj.info) ;
						window.location.reload() ;
					}
				})
			}
			
		})
		</script>
		
	</div>
</div>

<!-- 给线路安排班次的模态框 -->
	<div class="modal fade" id="addClassesForLine" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">安排班次</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="addClassesForLine_classesId" class="control-label">班次ID：(不能为空!)</label>
	            <input type="text" class="form-control" id="addClassesForLine_classesId" placeHolder="classesId">
	          </div>
	          <div class="form-group">
	            <label for="addClassesForLine_lineId" class="control-label">所属线路：</label>
	            <input type="text" class="form-control" id="addClassesForLine_lineId" placeHolder="lineId">
	          </div>
	          <div class="form-group">
	            <label for="addClassesForLine_time" class="control-label">发车时间：</label>
	            <input type="text" class="form-control" id="addClassesForLine_time" placeHolder="time" value="8:00 a.m / 5:00 p.m" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="addClassesForLine_note" class="control-label">备注：</label>
	            <input type="text" class="form-control" id="addClassesForLine_note" placeHolder="note">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="addClassesForLine_confirm">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 删除班次的模态框 -->
	<div class="modal fade" id="deleteClassesForLine" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">班次信息</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="deleteClassesForLine_classesId" class="control-label">班次ID：</label>
	            <input type="text" class="form-control" id="deleteClassesForLine_classesId" placeHolder="classesId">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForLine_lineId" class="control-label">所属线路：</label>
	            <input type="text" class="form-control" id="deleteClassesForLine_lineId" placeHolder="lineId">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForLine_time" class="control-label">发车时间：</label>
	            <input type="text" class="form-control" id="deleteClassesForLine_time" placeHolder="time">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForLine_note" class="control-label">备注：</label>
	            <input type="text" class="form-control" id="deleteClassesForLine_note" placeHolder="note">
	          </div>
	           <div class="form-group">
	            <label for="carsOfThisClasses_size" class="control-label">车辆数目：</label>
	            <input type="text" class="form-control" id="carsOfThisClasses_size" placeHolder="note" >
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="deleteClassesForLine_confirm">删除</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 添加车辆的模态框 -->
	<div class="modal fade" id="addCar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加车辆</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="addCar_carNo" class="control-label">车牌(不能为空！)：</label>
	            <input type="text" class="form-control" id="addCar_carNo" placeHolder="carNo">
	          </div>
	          <div class="form-group">
	            <label for="addCar_classesId" class="control-label">所属班次(可以先不填！)：</label>
	            <select class="form-control" id="addCar_classesId">
	            	<option value="">请选择...</option>
				  	<c:forEach items="${requestScope.allClasses }" var="classes">
				  		<option>${classes.classesId}</option>
				  	</c:forEach>
				</select>
	          </div>
	          <div class="form-group">
	            <label for="addCar_brand" class="control-label">品牌：</label>
	            <input type="text" class="form-control" id="addCar_brand" placeHolder="brand">
	          </div>
	          <div class="form-group">
	            <label for="addCar_seat" class="control-label">座位数量：</label>
	            <input type="text" class="form-control" id="addCar_seat" placeHolder="seat">
	          </div>
	          <div class="form-group">
	            <label for="addCar_date_register" class="control-label">注册日期(yyyy-MM-dd)：</label>
	            <input type="text" class="form-control" id="addCar_date_register" placeHolder="date_register">
	          </div>
	          <div class="form-group">
	            <label for="addCar_date_insurance" class="control-label">保险日期(yyyy-MM-dd)：</label>
	            <input type="text" class="form-control" id="addCar_date_insurance" placeHolder="date_insurance">
	          </div>
	          <div class="form-group">
	            <label for="addCar_drivingLicenseNo" class="control-label">行驶证号码：</label>
	            <input type="text" class="form-control" id="addCar_drivingLicenseNo" placeHolder="drivingLicenseNo">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="addCar_confirm">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!-- 给车辆设置班次的模态框 -->
	<div class="modal fade" id="setClassesForCar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">设置班次</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	  <div class="form-group">
	            <label for="setClassesForCar_carNo" class="control-label">车牌：</label>
	            <input type="text" class="form-control" id="setClassesForCar_carNo" placeHolder="carNo" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="setClassesForCar_classesId" class="control-label">选择班次：</label>
	            <select class="form-control" id="setClassesForCar_classesId" onchange="setClassesForCar_classesId_onChange(this.value)">
	            	<option value="">请选择...</option>
				  	<c:forEach items="${requestScope.allClasses }" var="classes">
				  		<option>${classes.classesId}</option>
				  	</c:forEach>
				</select>
	          </div>
	          <div class="form-group">
	            <label for="setClassesForCar_lineId" class="control-label">班次所属线路ID：</label>
	            <input type="text" class="form-control" id="setClassesForCar_lineId" placeHolder="lineId" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="setClassesForCar_time" class="control-label">发车时间：</label>
	            <input type="text" class="form-control" id="setClassesForCar_time" placeHolder="time" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="setClassesForCar_note" class="control-label">备注：</label>
	            <input type="text" class="form-control" id="setClassesForCar_note" placeHolder="note" readonly="readonly">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="setClassesForCar_confirm">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script>
	//setClassesForCar的JS
		function setClassesForCar_classesId_onChange(classesId) {
			var classesId_selected = $("#setClassesForCar_classesId").val() ;
			
			$.ajax({
				url:"getTheClasses_setClassesForCar.do?classesId_selected=" + classesId_selected ,
				dataType:"json" ,
				success: function (result) {
					$("#setClassesForCar_lineId").val(result.classes_selected[0]) ;
					$("#setClassesForCar_time").val(result.classes_selected[1]) ;
					$("#setClassesForCar_note").val(result.classes_selected[2]) ;
					
				},
				error:function() {
					alert("error!") ;
				}
			})
		}
	</script>
	
	<!-- 为班次移除指定车辆的模态框 -->
	<div class="modal fade" id="deleteClassesForCar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">班次信息</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="deleteClassesForCar_carNo" class="control-label">选中车辆的车牌：</label>
	            <input type="text" class="form-control" id="deleteClassesForCar_carNo" placeHolder="carNo">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForCar_classesId" class="control-label">所属班次ID：</label>
	            <input type="text" class="form-control" id="deleteClassesForCar_classesId" placeHolder="classesId">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForCar_lineId" class="control-label">所属线路：</label>
	            <input type="text" class="form-control" id="deleteClassesForCar_lineId" placeHolder="lineId">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForCar_time" class="control-label">发车时间：</label>
	            <input type="text" class="form-control" id="deleteClassesForCar_time" placeHolder="time">
	          </div>
	          <div class="form-group">
	            <label for="deleteClassesForCar_note" class="control-label">备注：</label>
	            <input type="text" class="form-control" id="deleteClassesForCar_note" placeHolder="note">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="deleteClassesForCar_confirm">删除</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 添加部门的模态框 -->
	<div class="modal fade" id="addDept" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加部门</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	  <div class="form-group">
	            <label for="addDept_deptNo" class="control-label">部门ID：</label>
	            <input type="text" class="form-control" id="addDept_deptNo" placeHolder="deptNo">
	          </div>
	          <div class="form-group">
	            <label for="addDept_name" class="control-label">部门名称：</label>
	            <input type="text" class="form-control" id="addDept_name" placeHolder="name">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="addDept_confirm">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 添加司机的模态框 -->
	<div class="modal fade" id="addDriver" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加司机</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	  <div class="form-group">
	            <label for="addDriver_id" class="control-label">司机ID：</label>
	            <input type="text" class="form-control" id="addDriver_id" placeHolder="id">
	          </div>
	          <div class="form-group">
	            <label for="addDriver_name" class="control-label">姓名：</label>
	            <input type="text" class="form-control" id="addDriver_name" placeHolder="name">
	          </div>
	          <div class="form-group">
	            <label for="addDriver_driverLicenseNo" class="control-label">驾驶证编号：</label>
	            <input type="text" class="form-control" id="addDriver_driverLicenseNo" placeHolder="driverLicenseNo">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="addDriver_confirm">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 为车辆添加司机的模态框 -->
	<div class="modal fade" id="setDriverForCar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">为车辆指定司机</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="setDriverForCar_carNo" class="control-label">车牌：</label>
	            <input type="text" class="form-control" id="setDriverForCar_carNo" placeHolder="carNo">
	          </div>
	       	  <div class="form-group">
	            <label for="setDriverForCar_driverId" class="control-label">司机ID：</label>
	            <select class="form-control" id="setDriverForCar_driverId">
	            	<option value="">请选择...</option>
				  	<c:forEach items="${requestScope.allDrivers }" var="driver">
				  		<c:if test="${driver.car == null }">
				  			<option value="${driver.id }">${driver.id} - ${driver.name}</option>
				  		</c:if>
				  	</c:forEach>
				</select>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="setDriverForCar_confirm">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 为车辆删除司机的模态框 -->
	<div class="modal fade" id="deleteDriverForCar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">为车辆移除司机</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="deleteDriverForCar_carNo" class="control-label">车牌：</label>
	            <input type="text" class="form-control" id="deleteDriverForCar_carNo" placeHolder="carNo">
	          </div>
	       	  <div class="form-group">
	            <label for="deleteDriverForCar_driverId" class="control-label">司机ID：</label>
	            <input type="text" class="form-control" id="deleteDriverForCar_driverId" placeHolder="driverId">
	          </div>
	           <div class="form-group">
	            <label for="deleteDriverForCar_driverName" class="control-label">姓名：</label>
	            <input type="text" class="form-control" id="deleteDriverForCar_driverName" placeHolder="driverName">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="deleteDriverForCar_confirm">移除</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 为司机添加车辆的模态框 -->
	<div class="modal fade" id="setCarForDriver" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">为司机设置车辆</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="setCarForDriver_driverId" class="control-label">司机ID：</label>
	            <input type="text" class="form-control" id="setCarForDriver_driverId" placeHolder="driverId">
	          </div>
	       	  <div class="form-group">
	            <label for="setCarForDriver_carNo" class="control-label">车牌：</label>
	            <select class="form-control" id="setCarForDriver_carNo">
	            	<option value="">请选择...</option>
				  	<c:forEach items="${requestScope.allCars }" var="car">
				  		<c:if test="${car.drivers.size() < 2 }">
				  			<option value="${car.carNo }">${car.carNo }</option>
				  		</c:if>
				  	</c:forEach>
				</select>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="setCarForDriver_confirm">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 为司机移除车辆的模态框 -->
	<div class="modal fade" id="deleteCarForDriver" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">为司机移除车辆</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="deleteCarForDriver_driverId" class="control-label">司机ID：</label>
	            <input type="text" class="form-control" id="deleteCarForDriver_driverId" placeHolder="driverId">
	          </div>
	       	  <div class="form-group">
	            <label for="deleteCarForDriver_carNo" class="control-label">车牌：</label>
	            <input type="text" class="form-control" id="deleteCarForDriver_carNo" placeHolder="carNo">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="deleteCarForDriver_confirm">移除</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 为班次移除车辆的模态框 -->
	<div class="modal fade" id="deleteCarForClasses" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">为班次移除车辆</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="deleteCarForClasses_classesId" class="control-label">班次ID：</label>
	            <input type="text" class="form-control" id="deleteCarForClasses_classesId" placeHolder="classesId">
	          </div>
	       	  <div class="form-group">
	            <label for="deleteCarForClasses_carNo" class="control-label">车牌：</label>
	            <input type="text" class="form-control" id="deleteCarForClasses_carNo" placeHolder="carNo">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="deleteCarForClasses_confirm">移除</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 给班次安排车辆的模态框 -->
	<div class="modal fade" id="setCarForClasses" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">设置班次</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	  <div class="form-group">
	            <label for="setCarForClasses_classesId" class="control-label">班次ID：</label>
	            <input type="text" class="form-control" id="setCarForClasses_classesId" placeHolder="classesId" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="setCarForClasses_carNo" class="control-label">选择车辆：</label>
	            <select class="form-control" id="setCarForClasses_carNo" onchange="setCarForClasses_carNo_onChange(this.value)">
	            	<option value="">请选择...</option>
				  	<c:forEach items="${requestScope.allCars }" var="car">
					  	<c:if test="${car.classes == null }">
					  		<option>${car.carNo}</option>
					  	</c:if>
				  	</c:forEach>
				</select>
	          </div>
	          <div class="form-group">
	            <label for="setCarForClasses_brand" class="control-label">车辆品牌：</label>
	            <input type="text" class="form-control" id="setCarForClasses_brand" placeHolder="brand" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="setCarForClasses_seat" class="control-label">核载人数：</label>
	            <input type="text" class="form-control" id="setCarForClasses_seat" placeHolder="seat" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="setCarForClasses_driver" class="control-label">司机：</label>
	            <input type="text" class="form-control" id="setCarForClasses_driver" placeHolder="driver" readonly="readonly">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="setCarForClasses_confirm">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script>
	//setCarForClasses的JS
	function setCarForClasses_carNo_onChange(carNo) {
		var carNo_selected = $("#setCarForClasses_carNo").val() ;
		
		$.ajax({
			url:"getTheCar_setCarForClasses.do?carNo_selected=" + carNo_selected ,
			dataType:"json" ,
			success: function (result) {
				$("#setCarForClasses_brand").val(result.car_selected[0]) ;
				$("#setCarForClasses_seat").val(result.car_selected[1]) ;
				$("#setCarForClasses_driver").val(result.car_selected[2]) ;
				
			},
			error:function() {
				alert("error!") ;
			}
		})
	}
	</script>
	
	<!-- 添加线路的模态框 -->
	<div class="modal fade" id="addLine" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加线路</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	  <div class="form-group">
	            <label for="addLine_lineId" class="control-label">线路ID：</label>
	            <input type="text" class="form-control" id="addLine_lineId" placeHolder="id">
	          </div>
	          <div class="form-group">
	            <label for="addLine_lineName" class="control-label">线路名称：</label>
	            <input type="text" class="form-control" id="addLine_lineName" placeHolder="name">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="addLine_confirm">添加</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 删除线路的模态框 -->
	<div class="modal fade" id="deleteLine" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">删除线路</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	       	 <div class="form-group">
	            <label for="deleteLine_lineId" class="control-label">班次ID：</label>
	            <input type="text" class="form-control" id="deleteLine_lineId" placeHolder="lineId" readonly="readonly">
	          </div>
	       	  <div class="form-group">
	            <label for="deleteLine_lineName" class="control-label">线路名称：</label>
	            <input type="text" class="form-control" id="deleteLine_lineName" placeHolder="lineName" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="deleteLine_population" class="control-label">人数：</label>
	            <input type="text" class="form-control" id="deleteLine_population" placeHolder="population" readonly="readonly">
	          </div>
	          <div class="form-group">
	            <label for="deleteLine_rate" class="control-label">乘坐率：</label>
	            <input type="text" class="form-control" id="deleteLine_rate" placeHolder="rate" readonly="readonly">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="deleteLine_confirm">移除</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script>
		//安排班次的JS
		$('#addClassesForLine').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var lineId = button.data('whatever') // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  //modal.find('.modal-title').text('New message to ' + recipient)
		  modal.find('.modal-body #addClassesForLine_lineId').val(lineId)
		  
		  $("#addClassesForLine_confirm").click(function() {
			  $.ajax({
				  url:"addClassesForLine.do?classesId=" + $("#addClassesForLine_classesId").val() 
						  + "&lineId=" + $("#addClassesForLine_lineId").val()
						  + "&time=" + $("#addClassesForLine_time").val()
						  + "&note=" + $("#addClassesForLine_note").val() ,
				  success:function (result) {
					  var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "添加成功！") {
						  window.location.reload() ;
					  }
				  }
			  })
		  })
		})
		
		//删除班次的JS
		$('#deleteClassesForLine').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var classesId = button.data('1') // Extract info from data-* attributes
		  var lineId = button.data('2') // Extract info from data-* attributes
		  var time = button.data('3') // Extract info from data-* attributes
		  var note = button.data('4') // Extract info from data-* attributes
		  var size = button.data('5')
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  //modal.find('.modal-title').text('New message to ' + recipient)
		  modal.find('.modal-body #deleteClassesForLine_classesId').val(classesId)
		  modal.find('.modal-body #deleteClassesForLine_lineId').val(lineId)
		  modal.find('.modal-body #deleteClassesForLine_time').val(time)
		  modal.find('.modal-body #deleteClassesForLine_note').val(note)
		   modal.find('.modal-body #carsOfThisClasses_size').val(size)
		  
		  $("#deleteClassesForLine_confirm").click(function() {
			  
			  if (window.confirm("确定要删除此班次吗？") == true) {
				  $.ajax({
					 url:"deleteClassesForLine.do?classesId=" + $("#deleteClassesForLine_classesId").val() ,
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						  alert(obj.info) ;
						  window.location.reload() ;
					}
				 })
			  }
		  })
		  
		})
		
		//添加车辆的JS
		$('#addCar').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  $("#addCar_confirm").click(function() {
			 $.ajax({
				 url:"addCar.do?carNo=" + $("#addCar_carNo").val() 
						+ "&classesId=" + $("#addCar_classesId").val()
						+ "&brand=" + $("#addCar_brand").val()
						+ "&seat=" + $("#addCar_seat").val()
						+ "&date_register=" + $("#addCar_date_register").val()
						+ "&date_insurance=" + $("#addCar_date_insurance").val()
						+ "&drivingLicenseNo=" + $("#addCar_drivingLicenseNo").val(),
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  window.location.reload() ;
				}
			 })
		  })
		  
		})
		
		//车辆设置班次的JS
		$('#setClassesForCar').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var carNo = button.data('1')
		  var modal = $(this)
		  
		  modal.find('.modal-body #setClassesForCar_carNo').val(carNo)
		  
		  $("#setClassesForCar_confirm").click(function() {
			 $.ajax({
				 url:"setClassesForCar.do?carNo=" + $("#setClassesForCar_carNo").val()
						 + "&classesId=" + $("#setClassesForCar_classesId").val(),
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  window.location.reload() ;
				}
			 })
		  })
		  
		})
		
		//为车辆删除班次的JS
		$('#deleteClassesForCar').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var carNo = button.data('0')
		  var classesId = button.data('1') // Extract info from data-* attributes
		  var lineId = button.data('2') // Extract info from data-* attributes
		  var time = button.data('3') // Extract info from data-* attributes
		  var note = button.data('4') // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  //modal.find('.modal-title').text('New message to ' + recipient)
		  modal.find('.modal-body #deleteClassesForCar_carNo').val(carNo)
		  modal.find('.modal-body #deleteClassesForCar_classesId').val(classesId)
		  modal.find('.modal-body #deleteClassesForCar_lineId').val(lineId)
		  modal.find('.modal-body #deleteClassesForCar_time').val(time)
		  modal.find('.modal-body #deleteClassesForCar_note').val(note)
		  
		  $("#deleteClassesForCar_confirm").click(function() {
			  
			  if (window.confirm("确定要解除车辆（"+carNo+"）与班次（"+classesId+"）之间的关系吗？") == true) {
				  $.ajax({
					 url:"deleteClassesForCar.do?classesId=" + $("#deleteClassesForCar_classesId").val()
							 + "&carNo=" + $("#deleteClassesForCar_carNo").val(),
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						  alert(obj.info) ;
						  window.location.reload() ;
					}
				 })
			  }
		  })
		  
		})
		
		//添加部门的JS
		$('#addDept').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  $("#addDept_confirm").click(function() {
			 $.ajax({
				 url:"addDept.do?deptNo=" + $("#addDept_deptNo").val() 
						+ "&name=" + $("#addDept_name").val(),
						
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "添加成功！") {
						  window.location.reload() ;
					  }
				}
			 })
		  })
		  
		})
		
		//添加司机的JS
		$('#addDriver').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  $("#addDriver_confirm").click(function() {
			 $.ajax({
				 url:"addDriver.do?id=" + $("#addDriver_id").val() 
						+ "&name=" + $("#addDriver_name").val()
						+ "&driverLicenseNo=" + $("#addDriver_driverLicenseNo").val(),
						
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "添加成功！") {
						  window.location.reload() ;
					  }
				}
			 })
		  })
		  
		})
		
		//为车辆指定司机的JS
		$('#setDriverForCar').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var carNo = button.data('1');
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  modal.find('.modal-body #setDriverForCar_carNo').val(carNo);
		  
		  $("#setDriverForCar_confirm").click(function() {
			$.ajax({
				url:"setDriverForCar.do?carNo=" + $("#setDriverForCar_carNo").val() 
						+ "&driverId=" + $("#setDriverForCar_driverId").val() ,
						
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "设置成功！") {
						  window.location.reload() ;
					  }
				}
			})				
		  })
		  
		})
		
		//为车辆移除司机的JS
		$('#deleteDriverForCar').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var carNo = button.data('0');
		  var driverId = button.data('1') ;
		  var driverName = button.data('2') ;
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  modal.find('.modal-body #deleteDriverForCar_carNo').val(carNo);
		  modal.find('.modal-body #deleteDriverForCar_driverId').val(driverId);
		  modal.find('.modal-body #deleteDriverForCar_driverName').val(driverName);
		  
		  $("#deleteDriverForCar_confirm").click(function() {
			$.ajax({
				url:"deleteDriverForCar.do?driverId=" + $("#deleteDriverForCar_driverId").val() ,
						
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "移除成功！") {
						  window.location.reload() ;
					  }
				}
			})				
		  })
		  
		})
		
		//为司机添加车辆的JS
		$('#setCarForDriver').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var driverId = button.data('1');
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  modal.find('.modal-body #setCarForDriver_driverId').val(driverId);
		  
		  $("#setCarForDriver_confirm").click(function() {
			$.ajax({
				url:"setCarForDriver.do?driverId=" + $("#setCarForDriver_driverId").val() 
						+"&carNo=" + $("#setCarForDriver_carNo").val(),
						
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "设置成功！") {
						  window.location.reload() ;
					  }
				}
			})				
		  })
		  
		})
		
		//为司机移除车辆的JS
		$('#deleteCarForDriver').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  
		  var driverId = button.data('1') ;
		  var carNo = button.data('2');
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  modal.find('.modal-body #deleteCarForDriver_carNo').val(carNo);
		  modal.find('.modal-body #deleteCarForDriver_driverId').val(driverId);
		  
		  $("#deleteCarForDriver_confirm").click(function() {
			$.ajax({
				url:"deleteCarForDriver.do?driverId=" + $("#deleteCarForDriver_driverId").val() ,
						
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "移除成功！") {
						  window.location.reload() ;
					  }
				}
			})				
		  })
		  
		})
		
		
		//为班次移除车辆的JS
		$('#deleteCarForClasses').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  
		  var classesId = button.data('1') ;
		  var carNo = button.data('2');
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  modal.find('.modal-body #deleteCarForClasses_classesId').val(classesId);
		  modal.find('.modal-body #deleteCarForClasses_carNo').val(carNo);
		  
		  $("#deleteCarForClasses_confirm").click(function() {
			if (window.confirm("确定移除此关系吗？") == true) {
				$.ajax({
					url:"deleteCarForClasses.do?carNo=" + $("#deleteCarForClasses_carNo").val()
							+ "&classesId=" + $("#deleteCarForClasses_carNo").val(),
							
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						  alert(obj.info) ;
						  window.location.reload() ;
					}
				})		
			}		
		  })
		  
		})
		
		//为班次安排车辆的JS
		$('#setCarForClasses').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var classesId = button.data('1')
		  var modal = $(this)
		  
		  modal.find('.modal-body #setCarForClasses_classesId').val(classesId)
		  
		  $("#setCarForClasses_confirm").click(function() {
			 $.ajax({
				 //直接用之前写过的setClassesForCar.do吧，反正实现出来效果一样
				 url:"setClassesForCar.do?carNo=" + $("#setCarForClasses_carNo").val()
						 + "&classesId=" + $("#setCarForClasses_classesId").val(),
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  window.location.reload() ;
				}
			 })
		  })
		})
		
		//添加线路的JS
		$('#addLine').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
		  $("#addLine_confirm").click(function() {
			 $.ajax({
				 //直接用之前写过的setClassesForCar.do吧，反正实现出来效果一样
				 url:"addLine.do?lineId=" + $("#addLine_lineId").val()
						 + "&lineName=" + $("#addLine_lineName").val(),
				success: function (result) {
					var obj = eval('(' + result + ')'); 
					  alert(obj.info) ;
					  if (obj.info == "添加成功！") {
						  window.location.reload() ;
					  }
				}
			 })
		  })
		})
		
		//删除线路的JS
		$('#deleteLine').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var lineId = button.data('1')
		  var lineName = button.data('2')
		  var population = button.data('3')
		  var rate = button.data('4')
		  var modal = $(this)
		  
		  modal.find('.modal-body #deleteLine_lineId').val(lineId)
		  modal.find('.modal-body #deleteLine_lineName').val(lineName)
		  modal.find('.modal-body #deleteLine_population').val(population)
		  modal.find('.modal-body #deleteLine_rate').val(rate)
		  
		  $("#deleteLine_confirm").click(function() {
			 if (window.confirm("此线路的所有信息将会一并被删除，确定要删除此线路吗？") == true) {
				 $.ajax({
					url:"deleteLine.do?lineId=" + $("#deleteLine_lineId").val(),
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						  alert(obj.info) ;
						  window.location.reload() ;
					}
				 })
			 } 
		  })
		})
	</script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
   <!--  <script src="js/bootstrap.min.js"></script>  -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
</body>
</html>