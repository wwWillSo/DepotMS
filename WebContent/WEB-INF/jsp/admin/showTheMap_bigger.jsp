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
	
			
	<script>
	var map = new BMap.Map("container");          // 创建地图实例  
	map.centerAndZoom("广州", 13) ;
	
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
	
	</script>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>