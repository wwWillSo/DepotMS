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
body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
#l-map{height:75%;width:100%;}
#r-result{width:100%;}
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
	<div id="l-map"></div>
	<div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" /></div>
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	
	<div id="setFactoryStation">
		<form action="" method="post">
			<c:choose>
				<c:when test="${requestScope.factoryStation==null }">
					<h4><strong>尚未指定厂区地址</strong>，请在地图上选取</h4>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label for="oldFactoryStation">原地址：</label>
						<input type="text" name="oldFactoryStationName" id="oldFactoryStationName" placeHolder="原地址" readonly="readonly" value="${requestScope.factoryStation.stationName }"/>
						<input type="text" name="oldFactoryStationLongitude" id="oldFactoryStationLongitude" placeHolder="原地址经度" readonly="readonly" value="${requestScope.factoryStation.longitude }"/>
						<input type="text" name="oldFactoryStationLatitude" id="oldFactoryStationLatitude" placeHolder="原地址纬度" readonly="readonly" value="${requestScope.factoryStation.latitude }"/>
						<input type="text" name="oldFactoryAddress" id="oldFactoryAddress" placeHolder="原详细地址" readOnly="readonly" value="${requestScope.factoryStation.stationAddress }" size="40"/><br>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="form-group">
				<label for="newFactoryStation">新地址：</label>
				<input type="text" name="newFactoryStationName" id="newFactoryStationName" placeHolder="新地址名称" value="厂区" readonly="readonly"/>
				<input type="text" name="newFactoryStationLongitude" id="newFactoryStationLongitude" placeHolder="新地址经度" />
				<input type="text" name="newFactoryStationLatitude" id="newFactoryStationLatitude" placeHolder="新地址纬度"/>
				<input type="text" name="newFactoryAddress" id="newFactoryAddress" placeHolder="新详细地址" readonly="readonly"size="40" value="${requestScope.factoryStation.stationAddress }"/><br>
			</div>
			<button type="button" class="btn btn-default" id="confirm">保存</button>
		</form>
	</div>
	
	<script>
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}
	
	var map = new BMap.Map("l-map");          // 创建地图实例  
	
	if ("${requestScope.factoryStation==null}".toString() == "true") {
		map.centerAndZoom("广州", 15) ;			
	} else {	
		map.centerAndZoom(new BMap.Point(parseFloat("${requestScope.factoryStation.longitude}"), parseFloat("${requestScope.factoryStation.latitude}")), 15) ;
		var marker = new BMap.Marker(new BMap.Point(parseFloat("${requestScope.factoryStation.longitude}"), parseFloat("${requestScope.factoryStation.latitude}")), {
			  // 初始化五角星symbol
			  icon: new BMap.Symbol(BMap_Symbol_SHAPE_STAR, {
			    scale: 5,
			    fillColor: "pink",
			    fillOpacity: 0.8
			  })
		});
		map.addOverlay(marker) ;
	}
	
	$("#confirm").click(function () {
		$.ajax({
			url:"updateFactoryStation.do?stationName=" + $("#newFactoryStationName").val()
					+ "&longitude=" + $("#newFactoryStationLongitude").val() 
					+ "&latitude=" + $("#newFactoryStationLatitude").val()
					+ "&address=" + $("#newFactoryAddress").val() ,
			success: function (result) {
				var obj = eval('(' + result + ')'); 
				alert(obj.info) ;
				window.close() ;
			},
			error: function() {
				alert("error!") ;
			}
		})
	})
	
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	map.addControl(new BMap.NavigationControl());    
	map.addControl(new BMap.ScaleControl());    
	map.addControl(new BMap.OverviewMapControl());    
	map.addControl(new BMap.MapTypeControl());  
	
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		$("#newFactoryStationLongitude").val(e.point.lng) ;
		$("#newFactoryStationLatitude").val(e.point.lat) ;
		//alert(e.point.lng + "," + e.point.lat);

		var geoc = new BMap.Geocoder();
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			$("#newFactoryAddress").val(addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber) ;
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        

	});
	
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	//$("#suggestId").onblur(function () {
	//	$("#newFactoryAddress").val($("#suggestId").val()) ;
	//})
	</script>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>