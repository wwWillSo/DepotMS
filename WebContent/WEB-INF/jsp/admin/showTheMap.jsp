<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Baidu Map API  -->
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	 
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
<!-- Baidu Map API -->
<style type="text/css">  
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
#container{height:80%;width:80%}  
#lineInfo{height:70%;width:20%;position:absolute;left:80%;top:0;}
#stationInfo{height:30%;width:20%;position:absolute;left:80%;top:70%;}
#option{height:20%;width:80%;position:absolute;left:0%;top:80%;}
</style>  
<script type="text/javascript">
$(document).ready(function(){
	
})
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wncwP3BG6QRz6eQG9s78kCrIHBBDpxHj">
	//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
	//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>

<body>
	<!-- 地图容器 -->
	<div id="container">
		
	</div> 
	
	<!-- <div id="driverRoute"></div>  -->
	
	<!-- 线路信息页(右侧边栏) -->
	<div id="lineInfo">
		<h2 class="text-center">
			<strong>${requestScope.line.lineName }</strong>
		</h2>
		
		<table class="table table-condensed table-hover table-bordered">
			<tr>
				<td width="25%">站名</td>
				<td>地址</td>
				<td>顺序</td>
			</tr>
			
			<c:forEach items="${requestScope.allStationsOfThisLine }" var="stationOfLine">
				<tr>
					<td>${stationOfLine.station.stationName }</td>
					<td>${stationOfLine.station.stationAddress }</td>
					<td>${stationOfLine.step }</td>
				</tr>
			</c:forEach>
		</table>
		<strong style="float:right;">一条线最多允许八个站</strong>		
	</div>
	
	<!-- 站点信息页(有侧边栏) -->
	<div id="stationInfo">
		<h3 class="text-center">
			<strong>站点信息</strong>
		</h3>
		<input type="text" name="stationId" id="stationId" placeHolder="站点ID" readonly="readonly"/><br>
		<input type="text" name="stationName" id="stationName" placeHolder="站点名称" readonly="readonly"/>
		<input type="text" name="stationAddress" id="stationAddress" placeHolder="站点地址" readonly="readonly"/><br>
		<input type="text" name="longitude" id="longitude" placeHolder="经度" readonly="readonly"/>
		<input type="text" name="latitude" id="latitude" placeHolder="纬度" readonly="readonly"/><br>
		人数：<input type="text" name="latitude" id="latitude" placeHolder="人数" readonly="readonly"/><br>
		序号：<input type="text" name="step" id="step" placeHolder="站点序号" readonly="readonly"/><br>
		
		删除站点功能需要在“主菜单-->设置站点”里面进行
	</div>
	
	<!-- 操作栏 -->
	<div id="option">
		<h5>这里是<strong>${requestScope.line.lineName}</strong>，所有用户住址都已经加入点聚合</h5>
		
		<button type="button" class="btn default-btn" onClick="add_control()">添加网格</button>
		<button type="button" class="btn default-btn" onClick="delete_control()">删除网格</button>
	</div>
			
	<script>
	var map = new BMap.Map("container");          // 创建地图实例  
	//map.centerAndZoom("广州", 13) ;
	
	var stationArray = new Array() ;			//用于给所有站点添加事件
	
	$.ajax({
		url:"getAllStations.do?lineId=" + "${requestScope.line.lineId}" ,
		dataType:"json" ,
		success:function (result) {
			var flag = result.allStations.length ;
			var j = 0 ;
			for (; j < flag ; j ++) {
				stationArray[j] = result.allStations[j] ;			//用于给所有站点添加事件
			}
			
			var MAX = result.allStations.length;
			var i = 0;
			for (; i < MAX; i++) {
			   if (result.allStations[i][0] == "厂区") {
				   map.centerAndZoom(new BMap.Point(result.allStations[i][1], result.allStations[i][2]), 13) ;
				   var marker = new BMap.Marker(new BMap.Point(result.allStations[i][1], result.allStations[i][2]), {
						  // 初始化五角星symbol
						  icon: new BMap.Symbol(BMap_Symbol_SHAPE_STAR, {
						    scale: 2,
						    fillColor: "pink",
						    fillOpacity: 0.8
					  })
					});
				   
					map.addOverlay(marker) ;
					
					allListenerForMarkers(marker, result.allStations[i]) ;
					
			   } else {
				   var marker = new BMap.Marker(new BMap.Point(result.allStations[i][1], result.allStations[i][2]), {
					 //初始化四边形symbol
					 	 icon: new BMap.Symbol(BMap_Symbol_SHAPE_RECTANGLE, {
						    scale: 5,
						    fillColor: "blue",
						    fillOpacity: 0.8
						  })
					});
				   
					map.addOverlay(marker) ;
					allListenerForMarkers(marker, result.allStations[i]) ;
			   }
			}
			
			//加载其他线路的站点
			for (var i = 0 ; i < result.allStationsOfOtherLine.length; i ++) {
				if (result.allStationsOfOtherLine[i][0] != "厂区") {
					var marker = new BMap.Marker(new BMap.Point(result.allStationsOfOtherLine[i][1], result.allStationsOfOtherLine[i][2]), {
						 //初始化四边形symbol
						 	 icon: new BMap.Symbol(BMap_Symbol_SHAPE_RECTANGLE, {
							    scale: 5,
							    fillColor: "yellow",
							    fillOpacity: 0.8
							  })
						});
					   
					map.addOverlay(marker) ;
					allListenerForMarkers(marker, result.allStationsOfOtherLine[i]) ;
				}
			}
			
			
			//显示路径
			var transit = new BMap.DrivingRoute(map, {
				renderOptions: {
					map: map,
					//panel: "driverRoute",
					enableDragging : true, //起终点可进行拖拽
					autoViewport: true,
				},  
			}); 
			
			
			//2016-6-29
			var p1, p2, p3, p4, p5, p6, p7, p8 ;
			
			var length = stationArray.length ;
			
			var waypoints ;
			
			switch (length) {
			case 0 : break ;
			case 1 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						
						break ;
			case 2 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						transit.search(p1, p2, {waypoints:waypoints});
						break ;
			case 3 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
						p3 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						waypoints = [p2] ;
						transit.search(p1, p3, {waypoints:waypoints});
						break ;
			case 4 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
						p3 = new BMap.Point(stationArray[2][1], stationArray[2][2]) ;
						p4 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						waypoints = [p2, p3] ;
						transit.search(p1, p4, {waypoints:waypoints});
						break ;
			case 5 : 	
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
						p3 = new BMap.Point(stationArray[2][1], stationArray[2][2]) ;
						p4 = new BMap.Point(stationArray[3][1], stationArray[3][2]) ;
						p5 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						waypoints = [p2, p3, p4] ;
						transit.search(p1, p5, {waypoints:waypoints});
						break ;
			case 6 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
						p3 = new BMap.Point(stationArray[2][1], stationArray[2][2]) ;
						p4 = new BMap.Point(stationArray[3][1], stationArray[3][2]) ;
						p5 = new BMap.Point(stationArray[4][1], stationArray[4][2]) ;
						p6 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						waypoints = [p2, p3, p4, p5] ;
						transit.search(p1, p6, {waypoints:waypoints});
						break ;
			case 7 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
						p3 = new BMap.Point(stationArray[2][1], stationArray[2][2]) ;
						p4 = new BMap.Point(stationArray[3][1], stationArray[3][2]) ;
						p5 = new BMap.Point(stationArray[4][1], stationArray[4][2]) ;
						p6 = new BMap.Point(stationArray[5][1], stationArray[5][2]) ;
						p7 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						waypoints = [p2, p3, p4, p5, p6] ;
						transit.search(p1, p7, {waypoints:waypoints});
						break ;
			case 8 : 
						p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
						p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
						p3 = new BMap.Point(stationArray[2][1], stationArray[2][2]) ;
						p4 = new BMap.Point(stationArray[3][1], stationArray[3][2]) ;
						p5 = new BMap.Point(stationArray[4][1], stationArray[4][2]) ;
						p6 = new BMap.Point(stationArray[5][1], stationArray[5][2]) ;
						p7 = new BMap.Point(stationArray[6][1], stationArray[6][2]) ;
						p8 = new BMap.Point(stationArray[length-1][1], stationArray[length-1][2]) ;
						
						waypoints = [p2, p3, p4, p5, p6, p7] ;
						transit.search(p1, p8, {waypoints:waypoints});
						break ;
			}
			
			
			//var pointArray = new Array(p1, p2, p3, p4, p5, p6, p7, p8) ;
			
			//for (var i = 0; i <= 8; i ++) {
			//	if (i < stationArray.length-1) {
			//		pointArray[i] = new BMap.Point(stationArray[i][1], stationArray[i][2]) ;
			//	} else {
			//		pointArray[i] = new BMap.Point(stationArray[stationArray.length-1][1], stationArray[stationArray.length-1][2]) ;
			//	}
			//}
			
			//p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
			//p2 = pointArray[1] ;
			//p3 = pointArray[2] ;
			//p4 = pointArray[3] ;
			//p5 = pointArray[4] ;
			//p6 = pointArray[5] ;
			//p7 = pointArray[6] ;
			//p8 = new BMap.Point(stationArray[stationArray.length-1][1], stationArray[stationArray.length-1][2]) ;
			
			//var waypoints = [p2, p3, p4, p5, p6, p7] ;
			
			//var p1 = new BMap.Point(stationArray[0][1], stationArray[0][2]) ;
			//var p2 = new BMap.Point(stationArray[1][1], stationArray[1][2]) ;
			//var p3 = new BMap.Point(stationArray[2][1], stationArray[2][2]) ;
			//transit.search(p1, p8, {waypoints:waypoints});
			
			
		},
		error: function () {
			alert("error") ;
		}
	})
	
	var opts = {
			width : 200,     // 信息窗口宽度
			height: 60,     // 信息窗口高度
			//title : "站点详情" , // 信息窗口标题
			enableMessage:true//设置允许信息窗发送短息
	};
	
	//强行征收智商税
	function allListenerForMarkers(marker, station) {
		
		var content = "<strong>" + station[0] + "</strong>" + "<br>" + station[4] + "<br>" + station[1] + "，" + station[2] ;
		addClickHandler(content,marker);
		
		marker.addEventListener("click", function() {
			$("#stationId").val(station[3]) ;
			$("#stationName").val(station[0]) ;
			$("#stationAddress").val(station[4]) ;
			$("#longitude").val(station[1]) ;
			$("#latitude").val(station[2]) ;
			$("#step").val(station[5]) ;
		})
	}
	
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e)}
		);
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	map.addControl(new BMap.NavigationControl());    
	map.addControl(new BMap.ScaleControl());    
	map.addControl(new BMap.OverviewMapControl());    
	map.addControl(new BMap.MapTypeControl());  
	
	$.ajax({
		url:"getAllPoints.do",
		dataType:"json" ,
		success:function (result) {
			//var obj = eval('(' + result + ')'); 
			//$("#showAllPoints").text(result.allPoints.find(0)) ;
			//alert(result.allPoints[0][0]) ;
			
			var MAX = result.allPoints.length;
			var markers = [];
			var pt = null;
			var i = 0;
			for (; i < MAX; i++) {
			   pt = new BMap.Point(result.allPoints[i][1], result.allPoints[i][2]);
			   markers.push(new BMap.Marker(pt));
			}
			//最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
			var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
		},
		error:function () {
			alert("error!") ;
		}
	})
	
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		$("#newStationLongitude").val(e.point.lng) ;
		$("#newStationLatitude").val(e.point.lat) ;
		//alert(e.point.lng + "," + e.point.lat);

		var geoc = new BMap.Geocoder();
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			$("#newAddress").val(addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber) ;
			$("#newStationName").val(addComp.street) ;
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        

	});
	
	//confirl按钮
	$("#confirm").click(function () {
		if ($("#newStationName").val() == "" || $("#newStationStep").val() == "") {
			alert("站点名和顺序不能为空！") ;
		} else {
			if (stationArray.length == 8) {
				alert("一条线路的站点不可超过8个！") ;
			} else {
				$.ajax({
					url:"setStation.do?stationName=" + $("#newStationName").val()
							+ "&longitude=" + $("#newStationLongitude").val()
							+ "&latitude=" + $("#newStationLatitude").val()
							+ "&stationAddress=" + $("#newAddress").val()
							+ "&step=" + $("#newStationStep").val()
							+ "&lineId=" + "${requestScope.line.lineId}",
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						alert(obj.info) ;
						window.location.reload() ;
					},
					error: function () {
						alert("出现未知错误") ;
					}
					
				})
			}
		}
	})
	
	$("#delete").click(function () {
		if ($("#stationName").val () != "" && $("#stationName").val() != "厂区") {
			if (confirm("确定要删除站点：" + $("#stationName").val() + " 吗？") == true) {
				
				$.ajax({
					url:"deleteStation.do?stationId=" + $("#stationId").val()
							+ "&stationName=" + $("#stationName").val()
							+ "&lineId=" + "${requestScope.line.lineId}"
							+ "&step=" + $("#step").val() ,
					success: function (result) {
						var obj = eval('(' + result + ')'); 
						alert(obj.info) ;
						window.location.reload() ;
					},
					error:function() {
						alert("出现未知错误") ;
					}
						
				})
				
			}
		} else {
			if ($("#stationName").val() == "厂区") {
				alert("厂区不能删除！") ;
			} else {
				alert("请先选择站点！") ;
			}
		}
	})
	
	//添加或删除网格
	var tileLayer = new BMap.TileLayer({isTransparentPng: true});
	tileLayer.getTilesUrl = function(tileCoord, zoom) {
		var x = tileCoord.x;
		var y = tileCoord.y;
		return "http://developer.baidu.com/map/jsdemo/img/border.png";
	}
	function add_control(){
		map.addTileLayer(tileLayer);
	}
	function delete_control(){
		map.removeTileLayer(tileLayer);
	}
	
	</script>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>