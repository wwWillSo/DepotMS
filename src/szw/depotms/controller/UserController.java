package szw.depotms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import szw.depotms.model.Staff;
import szw.depotms.service.StaffService;

/**
 * 员工模块操作集合
 * @author 镇威
 *
 */
@Controller
public class UserController {
	@Autowired
	private StaffService staffService ;
	
	/**
	 * 查看个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showSelfInfo.do", produces = "text/html; charset=UTF-8")
	public String showSelfInfo(HttpServletRequest request) {
		
		return "jsp/staff/showSelfInfo" ;
	}
	
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/changePassword.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String changePassword(HttpServletRequest request) {
		String newPwd = request.getParameter("newPwd") ;
		Staff staff = (Staff)request.getSession().getAttribute("staff") ;
		staff.setPassword(newPwd);
		staffService.update(staff);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "修改成功！") ;
		ObjectMapper mapper = new ObjectMapper() ;
		try {
			String content = mapper.writeValueAsString(map) ;
			return content ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "" ;
	}
	
	/**
	 * 跳转至修改密码页面
	 * @return
	 */
	@RequestMapping("/changeHomeForward.do")
	public String changeHomeForward() {
		return "jsp/staff/changeHome" ;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/changeHomeConfirm.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String changeHomeFonfirm(HttpServletRequest request) {
		String home_longitude = request.getParameter("home_longitude") ;
		String home_latitude = request.getParameter("home_latitude") ;
		String home = request.getParameter("home") ;
		
		Staff staff = (Staff)request.getSession().getAttribute("staff") ;
		
		staff.setHome_longitude(home_longitude);
		staff.setHome_latitude(home_latitude);
		staff.setHome(home);
		
		staffService.update(staff);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "修改成功！点击确定后此页面将被关闭！") ;
		ObjectMapper mapper = new ObjectMapper() ;
		try {
			String content = mapper.writeValueAsString(map) ;
			return content ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "" ;
	}

	public StaffService getStaffService() {
		return staffService;
	}


	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
}
