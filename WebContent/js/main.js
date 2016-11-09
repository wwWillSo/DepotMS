$(document).ready(function(){
	//现在页面上用于交互的DIV有：
	//addStaffForm
	//info
	//main
	//error
	//showAllStaff
	
	//关闭所有用于交互的DIV块
	function closeAllDiv(flag) {
		if (flag == "hide") {
			$("#addStaffForm").hide() ;
			$("#error").hide() ;
			$("#info").hide() ;
			$("#main").hide() ;
			$("#showAllStaff").hide() ;
		} else if (flag == "slideUp"){
			$("#addStaffForm").slideUp() ;
			$("#error").slideUp() ;
			$("#info").slideUp() ;
			$("#main").slideUp() ;
			$("#showAllStaff").slideUp() ;
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
					+ "&pageSize=" + "5"
					+ "&pageNo=" + "1" ,
			success: function (result) {
				$("#showAllStaff").html(result) ;
				
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		
		$("#showAllStaff").slideDown("slow") ;
		
	})
	
	$("#check").click(function() {
		
		closeAllDiv("slideUp") ;
		
		
		$.ajax({
			url:"showAllStaff.do?keyword=" + $("#keyword").val()
					+ "&deptName=" + $("#deptName").val()
					+ "&pageSize=" + $("#pageSize").val()
					+ "&pageNo=" + $("#pageNo").val() ,
			success: function (result) {
				$("#showAllStaff").html(result) ;
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#showAllStaff").slideDown("slow") ;
		
	})
	
	$(".pagination >> a").click(function () {
		closeAllDiv("slideUp") ;
		
		$.ajax({
			url:"showAllStaff.do?keyword=" + $("#keyword").val()
					+ "&deptName=" + $("#deptName").val()
					+ "&pageSize=" + $("#pageSize").val()
					+ "&pageNo=" + this.id ,
			success: function (result) {
				$("#showAllStaff").html(result) ;
			},
			error:function () {
				alert("error!") ;
			}
		})
		
		$("#pageNo").val(this.id) ;
		
		$("#showAllStaff").slideDown("slow") ;
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
});

function change(deptNo) {
	document.getElementById("deptNo").value=""+deptNo ;
	//document.getElementById("staffNo").value=""+deptNo ;
}