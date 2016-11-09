<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>DepotMS</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
   
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
	.jumbotron .center {
		
	}
	
	body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
	#l-map{height:75%;width:100%;}
	#r-result{width:100%;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	alert("请在地图上搜寻到您的住址并单击，确保地图下方输入框有记录，可用左下方的关键字输入框") ;
	$("#confirm").click(function() {
		$.ajax({
			url:"changeHomeConfirm.do?home_longitude=" + $("#home_longitude").val()
					+ "&home_latitude=" + $("#home_latitude").val()
					+ "&home=" + $("#home").val(),
			success:function (result) {
				var obj = eval('(' + result + ')'); 
				alert(obj.info) ;
				window.close() ;
			}
		})
	})
});
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wncwP3BG6QRz6eQG9s78kCrIHBBDpxHj"></script>
<body>

	<div id="l-map"></div>
	<div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" /></div>
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	
	<div id="info" class="center" style="margin-top:10px;">
		<form action="" method="post" id="changeHomeForm">
			<div class="form-group">
				<label for="home">地址：</label>
				<input type="text" name="home" id="home" placeholder="地址" value="${sessionScope.staff.home }" readonly="readonly"/><br>
			</div>
			<div class="form-group">
				<label for="home_longitude">经度：</label>
				<input type="text" name="home_longitude" id="home_longitude" placeholder="经度" value="${sessionScope.staff.home_longitude }" readonly="readonly"/><br>
			</div>
			<div class="form-group">
				<label for="home_latitude">纬度：</label>
				<input type="text" name="home_latitude" id="home_latitude" placeHolder="纬度" value="${sessionScope.staff.home_latitude }" readonly="readonly"/><br>
			</div>
			<button type="button" class="btn btn-default" id="confirm" style="float:right;">确认</button>
		</form>
	</div>
	
	<script type="text/javascript">
		
		// 百度地图API功能
		function G(id) {
			return document.getElementById(id);
		}
	
		// 百度地图API功能
		var map = new BMap.Map("l-map");            
		map.centerAndZoom("广州",12);         
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());   
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		//单击获取点击的经纬度
		map.addEventListener("click",function(e){
			$("#home_longitude").val(e.point.lng) ;
			$("#home_latitude").val(e.point.lat) ;
			//alert(e.point.lng + "," + e.point.lat);
			var geoc = new BMap.Geocoder();
			var pt = e.point;
			geoc.getLocation(pt, function(rs){
				var addComp = rs.addressComponents;
				$("#home").val(addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber) ;
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
	</script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>
