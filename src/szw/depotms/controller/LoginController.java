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
 * Ա����¼�ӿ������Ա��¼�ӿ�
 * @author ����
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
	 * ������֤Ա�������������Ƿ���ȷ
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/staffValidate.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String staffValidate(HttpServletRequest request) {
		int staffNo = Integer.parseInt(request.getParameter("staffNo")) ;
		String password = request.getParameter("password") ;
		
		staff = staffService.get(Staff.class, staffNo) ;
		
		if (staff != null) {			//�û�����
			if (!staff.getPassword().equals(password)) {
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "�û������������") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {				//�˺����붼����
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "��֤�ɹ�") ;
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
			map.put("info", "���û������ڣ�") ;
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
	 * ��¼Ա����¼״̬
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
	 * ��ת������Ա��¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toAdminLogin.do")
	public String toAdminLogin(HttpServletRequest request) {
		return "jsp/admin/index" ;
	}
	
	/**
	 * ��ת��Ա����¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toUserLogin.do")
	public String toUsernLogin(HttpServletRequest request) {
		return "forward:index.jsp" ;
	}
	
	/**
	 * ����Ա�˺����������֤
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adminValidate.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String adminValidate(HttpServletRequest request) {
		String adminId = request.getParameter("adminId") ;
		String password = request.getParameter("password") ;
		
		admin = adminService.get(Admin.class, adminId) ;
		
		if (admin != null) {			//�û�����
			if (!admin.getPassword().equals(password)) {
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "����Ա�˺Ż��������") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {				//�˺����붼����
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "��֤�ɹ�") ;
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
			map.put("info", "�ù���Ա�˺Ų����ڣ�") ;
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
	 * ��¼����Ա��¼��״̬
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/adminLogin.do")
	public String adminLogin(HttpServletRequest request) {
		String adminId = request.getParameter("adminId") ;
		
		admin = adminService.get(Admin.class, adminId) ;
		
		request.getSession().setAttribute("admin", admin);
		
		//ȡ�����й�Ա
		//List<Staff> allStaffs = staffService.findAll(Staff.class) ;
		//request.setAttribute("allStaffs", allStaffs);
	
		//ȡ�����в���
		List<Dept> allDepts = deptService.findAll(Dept.class) ;
		request.setAttribute("allDepts", allDepts);
		
		//ȡ��������·
		List<Line> allLines = lineService.findAll(Line.class) ;
		request.setAttribute("allLines", allLines);
		
		//ȡ�����а��
		List<Classes> allClasses = classesService.findAll(Classes.class) ;
		request.setAttribute("allClasses", allClasses);
		
		//ȡ������˾��
		List<Driver> allDrivers = driverService.findAll(Driver.class) ;
		request.setAttribute("allDrivers", allDrivers);
		
		//ȡ�����г���
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
