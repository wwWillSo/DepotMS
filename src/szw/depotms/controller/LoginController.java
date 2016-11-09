package szw.depotms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import szw.depotms.model.Admin;
import szw.depotms.model.Car;
import szw.depotms.model.Classes;
import szw.depotms.model.Dept;
import szw.depotms.model.Driver;
import szw.depotms.model.Line;
import szw.depotms.model.Staff;
import szw.depotms.service.AdminService;
import szw.depotms.service.CarService;
import szw.depotms.service.ClassesService;
import szw.depotms.service.DeptService;
import szw.depotms.service.DriverService;
import szw.depotms.service.LineService;
import szw.depotms.service.StaffService;

/**
 * 员工登录接口与管理员登录接口
 * @author 镇威
 *
 */
@Controller
public class LoginController {
	@Autowired
	private StaffService staffService ;
	@Autowired
	private AdminService adminService ;
	@Autowired
	private DeptService deptService ;
	@Autowired
	private LineService lineService ;
	@Autowired
	private ClassesService classesService ;
	@Autowired
	private DriverService driverService ;
	@Autowired
	private CarService carService ;
	
	private Staff staff ;
	
	private Admin admin ;
	
	/**
	 * 用于验证员工工号与密码是否正确
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/staffValidate.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String staffValidate(HttpServletRequest request) {
		int staffNo = Integer.parseInt(request.getParameter("staffNo")) ;
		String password = request.getParameter("password") ;
		
		staff = staffService.get(Staff.class, staffNo) ;
		
		if (staff != null) {			//用户存在
			if (!staff.getPassword().equals(password)) {
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "用户名或密码错误！") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {				//账号密码都符合
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "验证成功") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "该用户不存在！") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "" ;
	}
	
	/**
	 * 记录员工登录状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/staffLogin.do")
	public String staffLogin(HttpServletRequest request) {
		int staffNo = Integer.parseInt(request.getParameter("staffNo")) ;
		
		staff = staffService.get(Staff.class, staffNo) ;
		
		request.getSession().setAttribute("staff", staff);
		
		return "jsp/staff/main" ;
	}
	
	/**
	 * 跳转至管理员登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toAdminLogin.do")
	public String toAdminLogin(HttpServletRequest request) {
		return "jsp/admin/index" ;
	}
	
	/**
	 * 跳转至员工登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toUserLogin.do")
	public String toUsernLogin(HttpServletRequest request) {
		return "forward:index.jsp" ;
	}
	
	/**
	 * 管理员账号与密码的验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adminValidate.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String adminValidate(HttpServletRequest request) {
		String adminId = request.getParameter("adminId") ;
		String password = request.getParameter("password") ;
		
		admin = adminService.get(Admin.class, adminId) ;
		
		if (admin != null) {			//用户存在
			if (!admin.getPassword().equals(password)) {
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "管理员账号或密码错误！") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {				//账号密码都符合
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "验证成功") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "该管理员账号不存在！") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "" ;
	}
	
	/**
	 * 记录管理员登录的状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adminLogin.do")
	public String adminLogin(HttpServletRequest request) {
		String adminId = request.getParameter("adminId") ;
		
		admin = adminService.get(Admin.class, adminId) ;
		
		request.getSession().setAttribute("admin", admin);
		
		//取得所有雇员
		//List<Staff> allStaffs = staffService.findAll(Staff.class) ;
		//request.setAttribute("allStaffs", allStaffs);
	
		//取得所有部门
		List<Dept> allDepts = deptService.findAll(Dept.class) ;
		request.setAttribute("allDepts", allDepts);
		
		//取得所有线路
		List<Line> allLines = lineService.findAll(Line.class) ;
		request.setAttribute("allLines", allLines);
		
		//取得所有班次
		List<Classes> allClasses = classesService.findAll(Classes.class) ;
		request.setAttribute("allClasses", allClasses);
		
		//取得所有司机
		List<Driver> allDrivers = driverService.findAll(Driver.class) ;
		request.setAttribute("allDrivers", allDrivers);
		
		//取得所有车辆
		List<Car> allCars = carService.findAll(Car.class) ;
		request.setAttribute("allCars", allCars);
		
		return "jsp/admin/main" ;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public LineService getLineService() {
		return lineService;
	}

	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}
	
}
