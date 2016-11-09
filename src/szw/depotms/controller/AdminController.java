package szw.depotms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import szw.depotms.calculator.LogLatDistance;
import szw.depotms.model.ArrangeInfo;
import szw.depotms.model.Car;
import szw.depotms.model.Classes;
import szw.depotms.model.DayArrange;
import szw.depotms.model.Dept;
import szw.depotms.model.Driver;
import szw.depotms.model.Line;
import szw.depotms.model.MonthArrange;
import szw.depotms.model.Staff;
import szw.depotms.model.Station;
import szw.depotms.model.StationOfLine;
import szw.depotms.model.WeekArrange;
import szw.depotms.service.ArrangeInfoService;
import szw.depotms.service.CarService;
import szw.depotms.service.ClassesService;
import szw.depotms.service.DayArrangeService;
import szw.depotms.service.DeptService;
import szw.depotms.service.DriverService;
import szw.depotms.service.LineService;
import szw.depotms.service.MonthArrangeService;
import szw.depotms.service.StaffService;
import szw.depotms.service.StationOfLineService;
import szw.depotms.service.StationService;
import szw.depotms.service.WeekArrangeService;

/**
 * ����Աģ���������
 * @author ����
 *
 */
@Controller
public class AdminController {
	@Autowired
	private StaffService staffService ;
	@Autowired
	private DeptService deptService ;
	@Autowired
	private ClassesService classesService ;
	@Autowired
	private ArrangeInfoService arrangeInfoService ;
	@Autowired
	private DayArrangeService dayArrangeService ;
	@Autowired
	private WeekArrangeService weekArrangeService ;
	@Autowired
	private MonthArrangeService monthArrangeService ;
	@Autowired
	private CarService carService ;
	@Autowired
	private LineService lineService ;
	@Autowired
	private StationService stationService ;
	@Autowired
	private StationOfLineService stationOfLineService ;
	@Autowired
	private DriverService driverService ;
	
	/**
	 * ��ӹ�Ա
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin_addStaff.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String adminAddStuff(HttpServletRequest request) {
		String staffNo = request.getParameter("staffNo") ;
		String staffName = request.getParameter("staffName") ;
		String password = request.getParameter("password") ;
		int deptNo = Integer.parseInt(request.getParameter("deptNo")) ;
		int newStaffNo = Integer.parseInt(deptNo + staffNo) ;
		
		Dept dept = deptService.get(Dept.class, deptNo) ;
		Staff staff = new Staff() ;
		staff.setStaffNo(newStaffNo);
		staff.setName(staffName);
		staff.setPassword(password);
		staff.setDept(dept);
		dept.getStaffs().add(staff) ;
		
		//System.out.println(staffNo + staffName + password + deptNo);
		
		if ((int)staffService.save(staff) == newStaffNo) {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��Ա��ӳɹ���") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��Ա���ʧ�ܣ�") ;
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
	 * ��ӹ�Ա-��֤��Ա�Ƿ����
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin_addStaff_validate.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addStaffValidate(HttpServletRequest request) {
		int staffNo = Integer.parseInt(request.getParameter("deptNo") + request.getParameter("staffNo")) ;
		
		Staff staff = staffService.get(Staff.class, staffNo) ;
		
		if (staff != null) {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "�˹�Ա����Ѵ��ڣ�") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "�˹�Ա��ſ��ã�") ;
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
	 * �������й�Ա
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showAllStaff.do", produces = "text/html;charset=UTF-8")
	public String showAllStaff(HttpServletRequest request) {
		
		String hql = "select en from " + Staff.class.getSimpleName() + " en where en.name like ?0 and en.dept.name like ?1" ;
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		String keyWord = request.getParameter("keyword") ;
		
		String deptName = request.getParameter("deptName") ;
		
		String[] params = {"%" + keyWord + "%", "%" + deptName + "%"} ;
				
		List<Staff> allStaffs = staffService.findByPage(hql, pageNo, pageSize, params) ;
		
		request.setAttribute("allStaffs", allStaffs) ;
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("keyWord", keyWord);
		request.setAttribute("deptName", deptName);		
		//����һ��������
		long allStaffsCount = 0 ;
		List<?> list = staffService.find("select count(*) from " + Staff.class.getSimpleName() + " en where en.name like ?0 and en.dept.name like ?1", params);
		if (list!=null && list.size() == 1) {
			allStaffsCount = (long)list.get(0) ;
		}
		
		//����һ����ҳ��
		long pageCount = 0 ;
		if (allStaffsCount >= pageSize) {
			pageCount = allStaffsCount % pageSize == 0 ? allStaffsCount / pageSize : allStaffsCount / pageSize + 1 ;
		} else {
			pageCount = 1 ;
		}
		
		//ȡ�����в���
		List<Dept> allDepts = deptService.findAll(Dept.class) ;
		request.setAttribute("allDepts", allDepts);
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("allStaffsCount", allStaffsCount);
		
		return "jsp/admin/showAllStaff" ;
	}
	
	/**
	 * �������й�Ա
	 * @param request
	 * @return
	 */
	@RequestMapping("/showAllStaff_bigger")
	public String showAllStaffBigger(HttpServletRequest request) {
		
		String hql = "select en from " + Staff.class.getSimpleName() + " en where en.name like ?0 and en.dept.name like ?1" ;
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo")) ;
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
		
		String keyWord = request.getParameter("keyword") ;
		
		String deptName = request.getParameter("deptName") ;
		
		String[] params = {"%" + keyWord + "%", "%" + deptName + "%"} ;
				
		List<Staff> allStaffs = staffService.findByPage(hql, pageNo, pageSize, params) ;
		
		request.setAttribute("allStaffs", allStaffs) ;
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("keyWord", keyWord);
		request.setAttribute("deptName", deptName);
		
		//����һ��������
		long allStaffsCount = 0 ;
		List<?> list = staffService.find("select count(*) from " + Staff.class.getSimpleName() + " en where en.name like ?0 and en.dept.name like ?1", params);
		if (list!=null && list.size() == 1) {
			allStaffsCount = (long)list.get(0) ;
		}
		
		//����һ����ҳ��
		long pageCount = 0 ;
		if (allStaffsCount >= pageSize) {
			pageCount = allStaffsCount % pageSize == 0 ? allStaffsCount / pageSize : allStaffsCount / pageSize + 1 ;
		} else {
			pageCount = 1 ;
		}
		
		request.setAttribute("pageCount", pageCount);
		
		//ȡ�����в���
		List<Dept> allDepts = deptService.findAll(Dept.class) ;
		request.setAttribute("allDepts", allDepts);
		request.setAttribute("allStaffsCount", allStaffsCount);
		
		return "jsp/admin/showAllStaff_bigger" ;
	}
	
	/**
	 * �������а����Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping(value="showAllClasses.do" , produces = "text/html;charset=UTF-8")
	public String showAllClasses(HttpServletRequest request) {
		List<Classes> allClasses = classesService.findAll(Classes.class) ;
		
		request.setAttribute("allClasses", allClasses) ;
		
		return "jsp/admin/showAllClasses" ;
	}
	
	/**
	 * ���������Ű�
	 */
	@RequestMapping(value="showArrange.do" , produces = "text/html;charset=UTF-8")
	public String showArrange(HttpServletRequest request) {
		
		
		List<MonthArrange> monthArranges = monthArrangeService.findAll(MonthArrange.class) ;
		MonthArrange monthArrange = monthArranges.get(0) ;
		
		List<WeekArrange> weekArranges = weekArrangeService.findAll(WeekArrange.class) ;
		
		List<DayArrange> dayArranges = dayArrangeService.findAll(DayArrange.class) ;
		
		request.setAttribute("monthArrange", monthArrange);
		request.setAttribute("weekArranges", weekArranges);
		request.setAttribute("dayArranges", dayArranges);
		
		return "jsp/admin/showArrange" ;
	}
	
	/**
	 * �������г���
	 * @param request
	 * @return
	 */
	@RequestMapping(value="showAllCars.do", produces = "text/html;charset=UTF-8")
	public String showAllCars(HttpServletRequest request) {
		
		List<Car> allCars = carService.findAll(Car.class) ;
		
		request.setAttribute("allCars", allCars);
		
		return "jsp/admin/showAllCars" ;
	}
	
	/**
	 * ����������·
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showAllLines.do", produces = "text/html;charset=UTF-8")
	public String showAllLines(HttpServletRequest request) {
		
		List<Line> allLines = lineService.findAll(Line.class) ;
		
		Iterator<Line> iter = allLines.iterator() ;
		
		while (iter.hasNext()) {
			Line line = iter.next() ;
			
			int population_old = line.getPopulation() ;
			int population_new = 0 ;
			
			String hql = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId = ?0" ;
			Object[] params = {line.getLineId()} ;
			List<StationOfLine> allStationOfThisLines = stationOfLineService.find(hql, params) ;
			Iterator<StationOfLine> iter2 = allStationOfThisLines.iterator() ;
			
			while (iter2.hasNext()) {
				StationOfLine sol = iter2.next() ;
				int station_pop = sol.getStation().getStaffs().size() ;
				population_new += station_pop ;
			}
			
			if (population_old != population_new) {
				line.setPopulation(population_new);
				lineService.update(line);
			}
		}
		
		request.setAttribute("allLines", allLines);
		
		return "jsp/admin/showAllLines" ;
	}
	
	/**
	 * ��ת����ͼչʾҳ��
	 * @param request
	 * @param lineId
	 * @return
	 */
	@RequestMapping(value="/showTheMap.do", produces = "text/html;charset=UTF-8")
	public String showTheMap(HttpServletRequest request, String lineId) {
		
//		List<Staff> allStaffs = staffService.findAll(Staff.class) ;
//		
//		request.setAttribute("allStaffs", allStaffs);
		
		Line line_needed = lineService.get(Line.class, Integer.parseInt(lineId)) ;
		
		request.setAttribute("line", line_needed);
		
		String hql = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0" ;
		Object[] params = {line_needed.getLineId()} ;
		List<StationOfLine> allStationsOfThisLine = stationOfLineService.find(hql, params) ;
		
		request.setAttribute("allStationsOfThisLine", allStationsOfThisLine) ;
		
		return "jsp/admin/showTheMap" ;
	}
	
	/**
	 * Ϊ��·���Ӱ��
	 * @param request
	 * @param lineId
	 * @param classesId
	 * @param time
	 * @param note
	 * @return
	 */
	@RequestMapping(value="/addClassesForLine.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addClassesForLine(HttpServletRequest request, String lineId, String classesId, String time, String note) {
		
		Line line_needed = lineService.get(Line.class, Integer.parseInt(lineId)) ;
		
		Classes classes = new Classes() ;
		
		classes.setClassesId(Integer.parseInt(classesId));
		classes.setLine(line_needed);
		classes.setNote(note);
		classes.setTime(time) ;
		
		if (classesService.get(Classes.class, Integer.parseInt(classesId)) == null) {
			classesService.save(classes) ;
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��ӳɹ���") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "�˰��ID�Ѿ����ڣ��뻻һ��ID��") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//line_needed.getClasses().add(classes) ;
		
		
		return "" ;
	}
	
	/**
	 * Ϊ��·�Ƴ����
	 * @param request
	 * @param classesId
	 * @return
	 */
	@RequestMapping(value="/deleteClassesForLine.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteClassesForLine(HttpServletRequest request, String classesId) {
		
		List<Car> cars = carService.findAll(Car.class) ;
		Iterator<Car> iter = cars.iterator() ;
		while (iter.hasNext()) {
			Car car = iter.next() ;
			if (car.getClasses()!=null && car.getClasses().getClassesId() == Integer.parseInt(classesId)) {
				car.setClasses(null);
			}
			carService.update(car);
		}

		classesService.delete(Classes.class, Integer.parseInt(classesId));
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "��ɾ����") ;
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
	 * ��ӳ���
	 * @param request
	 * @param carNo
	 * @param classesId
	 * @param brand
	 * @param seat
	 * @param date_register
	 * @param date_insurance
	 * @param drivingLicenseNo
	 * @return
	 */
	@RequestMapping(value="/addCar.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addCar(HttpServletRequest request, String carNo, String classesId, String brand, String seat, String date_register, String date_insurance, String drivingLicenseNo) {

		Car car = new Car() ;
		car.setCarNo(carNo);
		car.setBrand(brand);
		car.setSeat(seat) ;
		car.setDate_register(date_register);
		car.setDate_insurance(date_insurance);
		car.setDrivingLicenseNo(drivingLicenseNo);
		
		if (classesId != "") {
			Classes classes = classesService.get(Classes.class, Integer.parseInt(classesId)) ;
			if (classes == null) {
				Map<String, String> map = new HashMap<String, String>() ;
				map.put("info", "����İ�β������ڣ�") ;
				ObjectMapper mapper = new ObjectMapper() ;
				try {
					String content = mapper.writeValueAsString(map) ;
					return content ;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				car.setClasses(classes);
			}
		}
		
		carService.save(car) ;
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "��ӳɹ���") ;
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
	 * Ϊ����ָ�����
	 * @param request
	 * @param classesId_selected
	 * @return
	 */
	@RequestMapping(value="/getTheClasses_setClassesForCar.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getClasses(HttpServletRequest request, String classesId_selected) {
		
		Classes classes = classesService.get(Classes.class, Integer.parseInt(classesId_selected));
		
		String str[] = {""+classes.getLine().getLineId(), classes.getTime(), classes.getNote()} ;
		
		System.out.println(str[0] + "," + str[1] + "," + str[2]);
		
		Map<String, String[]> map = new HashMap<String, String[]>() ;
		map.put("classes_selected", str) ;
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
	 * Ϊ���ָ������
	 * @param request
	 * @param carNo
	 * @param classesId
	 * @return
	 */
	@RequestMapping(value="/setClassesForCar.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setClassesForCar(HttpServletRequest request, String carNo, String classesId) {
		
		Classes classes = classesService.get(Classes.class, Integer.parseInt(classesId));
		
		Car car = carService.get(Car.class, carNo) ;
		
		car.setClasses(classes);
		
		carService.update(car);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "���óɹ���") ;
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
	 * Ϊ��������˾��
	 * @param request
	 * @param carNo
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value="/setDriverForCar.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setDriverForCar(HttpServletRequest request, String carNo, String driverId) {
		
		Driver driver = driverService.get(Driver.class, Integer.parseInt(driverId));
		
		Car car = carService.get(Car.class, carNo) ;
		
		driver.setCar(car);
		
		driverService.update(driver);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "���óɹ���") ;
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
	 * Ϊ�����Ƴ�˾��
	 * @param request
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value="/deleteDriverForCar.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteDriverForCar(HttpServletRequest request, String driverId) {
		
		Driver driver = driverService.get(Driver.class, Integer.parseInt(driverId));
		
		
		driver.setCar(null);
		
		driverService.update(driver);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�Ƴ��ɹ���") ;
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
	 * Ϊ˾��ָ������
	 * @param request
	 * @param carNo
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value="/setCarForDriver.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setCarForDriver(HttpServletRequest request, String carNo, String driverId) {
		
		Driver driver = driverService.get(Driver.class, Integer.parseInt(driverId));
		
		Car car = carService.get(Car.class, carNo) ;
		
		driver.setCar(car);
		
		driverService.update(driver);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "���óɹ���") ;
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
	 * Ϊ˾���Ƴ�����
	 * @param request
	 * @param driverId
	 * @return
	 */
	@RequestMapping(value="/deleteCarForDriver.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteCarForDriver(HttpServletRequest request, String driverId) {
		
		Driver driver = driverService.get(Driver.class, Integer.parseInt(driverId));

		driver.setCar(null);
		
		driverService.update(driver);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�Ƴ��ɹ���") ;
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
	 * Ϊ�����Ƴ����
	 * @param request
	 * @param carNo
	 * @param classesId
	 * @return
	 */
	@RequestMapping(value="/deleteClassesForCar.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteClassesForCar(HttpServletRequest request, String carNo,String classesId) {
		
		Car car = carService.get(Car.class, carNo) ;
		
		car.setClasses(null);
		
		carService.update(car);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�ɹ����������"+car.getCarNo()+"�����Σ�"+classesId+"��֮��Ĺ�ϵ��") ;
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
	 * ��ת������վ��ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setStationForward.do", produces = "text/html;charset=UTF-8")
	public String setStationForward(HttpServletRequest request) {
		
		String lineId = request.getParameter("lineId") ;
		
		Line line_needed = lineService.get(Line.class, Integer.parseInt(lineId)) ;
		
		request.setAttribute("line", line_needed);
		
		String hql = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0" ;
		Object[] params = {line_needed.getLineId()} ;
		List<StationOfLine> allStationsOfThisLine = stationOfLineService.find(hql, params) ;
		
		request.setAttribute("allStationsOfThisLine", allStationsOfThisLine) ;
		
//		
//		List<Station> allStations = stationService.findAll(Station.class) ;
//		
//		request.setAttribute("allStations", allStations);
		
		return "jsp/admin/addStation" ;
		
	}
	
	/**
	 * ��������վ��
	 * @param request
	 * @param lineId
	 * @return
	 */
	@RequestMapping(value="/getAllStations.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getAllStations(HttpServletRequest request, int lineId) {
		
		List<String[]> allPoints = new ArrayList<String[]>() ;
		
		String hql = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0" ;
		Object[] params = {lineId} ;
		List<StationOfLine> allStationsOfThisLine = stationOfLineService.find(hql, params) ;
		Iterator<StationOfLine> iter = allStationsOfThisLine.iterator() ;
		while (iter.hasNext()) {
			StationOfLine stationOfLine = iter.next() ;
			Station station = stationOfLine.getStation() ;
			
			String[] str = {station.getStationName(), station.getLongitude(), station.getLatitude(), ""+station.getStationId(), station.getStationAddress(), ""+stationOfLine.getStep(), ""+station.getStaffs().size()} ;
			allPoints.add(str) ;
		}
		
//=====================================================================================================================================
		
		String hql2 = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId not in (?0)" ;
		List<StationOfLine> allStationsOfOtherLine = stationOfLineService.find(hql2, params) ;
		Iterator<StationOfLine> iter2 = allStationsOfOtherLine.iterator() ;
			
		List<String[]> allPoints2 = new ArrayList<String[]>() ;
		while (iter2.hasNext()) {
			StationOfLine stationOfLine = iter2.next() ;
			Station station = stationOfLine.getStation() ;
			
			String[] str = {station.getStationName(), station.getLongitude(), station.getLatitude(), ""+station.getStationId(), station.getStationAddress(), "-1", ""+station.getStaffs().size()} ;
			allPoints2.add(str) ;
		}
		
//		Map<String, Object> map = new HashMap<String, Object>() ;
//		map.put("allPoints", allPoints) ;
		
		Map<String, List<String[]>> map = new HashMap<String, List<String[]>>() ;
		map.put("allStations", allPoints) ;
		map.put("allStationsOfOtherLine", allPoints2) ;
		ObjectMapper mapper = new ObjectMapper() ;
		try {
			String content = mapper.writeValueAsString(map) ;
			System.out.println(content);
			return content ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "" ;
	}
	
	/**
	 * ��������Ա��סַ
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAllPoints.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getAllPoints(HttpServletRequest request) {
		List<Staff> allStaffs = staffService.findAll(Staff.class) ;
		Iterator<Staff> iter = allStaffs.iterator() ;
		
		List<String[]> allPoints = new ArrayList<String[]>() ;
		while (iter.hasNext()) {
			Staff staff = iter.next() ;
			if (staff.getHome_longitude()==null) {
				continue ;
			}
			String[] str = {staff.getName(), staff.getHome_longitude(), staff.getHome_latitude()} ;
			allPoints.add(str) ;
		}
		
//		Map<String, Object> map = new HashMap<String, Object>() ;
//		map.put("allPoints", allPoints) ;
		
		Map<String, List<String[]>> map = new HashMap<String, List<String[]>>() ;
		map.put("allPoints", allPoints) ;
		ObjectMapper mapper = new ObjectMapper() ;
		try {
			String content = mapper.writeValueAsString(map) ;
			System.out.println(content);
			return content ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "" ;
	}
	
	/**
	 * ��ת�����ù�����ַҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="setFactoryStationForward.do", produces="text/html;charset=UTF-8")
	public String setFactoryStationForward(HttpServletRequest request) {
		String hql = "select en from " + Station.class.getSimpleName() + " en where en.stationName = ?0" ;
		String[] params = {"����"} ;
		List<Station> factoryStations = stationService.find(hql, params) ;
		if (factoryStations.size() != 0) {
			request.setAttribute("factoryStation", factoryStations.get(0));
			return "jsp/admin/setFactoryStation" ;
		} else {
			request.setAttribute("factoryStation", null);
			return "jsp/admin/setFactoryStation" ;
		}
	}
	
	/**
	 * ���µĳ�����ַ
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updateFactoryStation.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String updateFactoryStation(HttpServletRequest request) {
		String stationName = request.getParameter("stationName") ;
		String address = request.getParameter("address") ;
		String longtitude = request.getParameter("longitude") ;
		String latitude = request.getParameter("latitude") ;
		
		String hql = "select en from " + Station.class.getSimpleName() + " en where en.stationName = ?0" ;
		String[] params = {"����"} ;
		List<Station> factoryStations = stationService.find(hql, params) ;
		
		if (factoryStations.size() != 0) {
			Station station = factoryStations.get(0) ;
			station.setStationName(stationName);
			station.setStationAddress(address);
			station.setLongitude(longtitude);
			station.setLatitude(latitude);
			
			stationService.update(station);
			
			String hql2 = "select en from " + StationOfLine.class.getSimpleName() + " en where en.station.stationId = ?0" ;
			Object[] params2 = {station.getStationId()} ;
			List<StationOfLine> factoryStationsOfAllLines = stationOfLineService.find(hql2, params2) ;
			Iterator<StationOfLine> iter = factoryStationsOfAllLines.iterator() ;
			while (iter.hasNext()) {
				StationOfLine stationOfLine = iter.next() ;
				stationOfLine.setStation(station);
				
				stationOfLineService.update(stationOfLine);
			}
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "���³ɹ������ȷ�����ҳ�潫���رգ�") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			Station station = new Station() ;
			station.setStationName(stationName);
			station.setStationAddress(address);
			station.setLongitude(longtitude);
			station.setLatitude(latitude);
			
			stationService.save(station) ;
			
			List<Line> allLines = lineService.findAll(Line.class) ;
			Iterator<Line> iter = allLines.iterator() ;
			while (iter.hasNext()) {
				Line line = iter.next() ;
				StationOfLine stationOfLine = new StationOfLine() ;
				stationOfLine.setStation(station);
				stationOfLine.setLine(line);
				
				stationOfLineService.save(stationOfLine) ;
			}
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "���óɹ������ȷ�����ҳ�潫���رգ�") ;
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
	 * ���վ��
	 * @param request
	 * @param station
	 * @param lineId
	 * @return
	 */
	@RequestMapping(value="/setStation.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String setStation (HttpServletRequest request, Station station, int lineId) {
		String hql = "select en from " + Station.class.getSimpleName() + " en where en.stationAddress = ?0" ;
		String[] params = {station.getStationAddress()} ;
		List<Station> stations = stationService.find(hql, params) ;
		
		if (stations.size() == 0) {
			
			stationService.save(station) ;
			
			StationOfLine stationOfLine = new StationOfLine() ;
			
			Line line = lineService.get(Line.class, lineId) ;
			stationOfLine.setLine(line);
			
			Station station2 = stationService.find(hql, params).get(0) ;
			stationOfLine.setStation(station2);
			
			//********************************************************************************
			//������������˰
			
			int step = 0 ;
			int step_max = 0 ;
			
			String hql2 = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0" ;
			Object[] params2 = {lineId} ;
			List<StationOfLine> allStationsOfThisLine = stationOfLineService.find(hql2, params2) ;
			Iterator<StationOfLine> iter = allStationsOfThisLine.iterator() ;
			while (iter.hasNext()) {
				int step_now = iter.next().getStep() ;
				if (step_now > step_max) {
					step_max = step_now ;
				}
			}
			step = step_max+1 ;
			
			stationOfLine.setStep(step);
			
			stationOfLineService.save(stationOfLine) ;
			
			//*********************************************************************************
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "վ��("+station.getStationName()+")���óɹ���") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ;
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "վ��("+station.getStationName()+")�Ѵ��ڣ��벻Ҫ�ظ�����ͬ�ĵص����ö��վ�㣡") ;
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
	 * ɾ��վ��
	 * @param request
	 * @param stationId
	 * @param stationName
	 * @param lineId
	 * @param step
	 * @return
	 */
	@RequestMapping(value="/deleteStation.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String deleteStation(HttpServletRequest request, int stationId, String stationName, int lineId, int step) {
		
		String hql2 = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0 AND en.station.stationId=?1" ;
		Object[] params2 = {lineId, stationId} ;
		
		if (stationOfLineService.find(hql2, params2).isEmpty()) {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "վ��("+ stationName +")�������ڴ���·��ɾ��ʧ�ܣ�") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ; 
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			Station station = stationService.get(Station.class, stationId) ;
			
			Set<Staff> allStaffsOfThisStation = station.getStaffs() ;
			
			Iterator<Staff> iter2 = allStaffsOfThisStation.iterator() ;
			
			while (iter2.hasNext()) {
				Staff staff = iter2.next() ;
				staff.setStation(null);
				
				staffService.update(staff);
			}
			
			String hql = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0" ;
			Object[] params = {lineId} ;
			List<StationOfLine> allStationsOfThisLine = stationOfLineService.find(hql, params) ;
			Iterator<StationOfLine> iter = allStationsOfThisLine.iterator() ;
			
			while (iter.hasNext()) {
				StationOfLine stationOfLine = iter.next() ;
				if (stationOfLine.getStep() > step) {
					stationOfLine.setStep(stationOfLine.getStep()-1);		
					stationOfLineService.update(stationOfLine);
				}
			}
			
			stationOfLineService.delete(StationOfLine.class, lineId, stationId);
			
			stationService.delete(Station.class, stationId);
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "վ��("+ stationName +")��ɾ����") ;
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
	 * �������в���
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showAllDepts.do", produces = "text/html;charset=UTF-8")
	public String showAllDept(HttpServletRequest request) {
		
		List<Dept> allDepts = deptService.findAll(Dept.class) ;
		
		request.setAttribute("allDepts", allDepts);
		
		return "jsp/admin/showAllDepts" ;
	}
	
	/**
	 * ��������˾��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showAllDrivers.do", produces = "text/html;charset=UTF-8")
	public String showAllDrivers(HttpServletRequest request) {
		
		List<Driver> allDrivers = driverService.findAll(Driver.class) ;
		
		request.setAttribute("allDrivers", allDrivers);
		
		return "jsp/admin/showAllDrivers" ;
	}
	
	/**
	 * ��Ӳ���
	 * @param request
	 * @param deptNo
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/addDept.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String addDept(HttpServletRequest request, String deptNo, String name) {

		if (deptService.get(Dept.class, Integer.parseInt(deptNo)) == null) {
			Dept dept = new Dept() ;
			dept.setDeptNo(Integer.parseInt(deptNo));
			dept.setName(name);
			
			deptService.save(dept) ;
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��ӳɹ���") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ; 
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��ID�Ѵ��ڣ�") ;
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
	 * ���˾��
	 * @param request
	 * @param id
	 * @param name
	 * @param driverLicenseNo
	 * @return
	 */
	@RequestMapping(value="/addDriver.do", produces="text/html;charset=UTF-8")
	public @ResponseBody String addDriver(HttpServletRequest request, String id, String name, String driverLicenseNo) {

		if (driverService.get(Driver.class, Integer.parseInt(id)) == null) {
			Driver driver = new Driver() ;
			driver.setId(Integer.parseInt(id));
			driver.setName(name);
			driver.setDriverLicenseNo(driverLicenseNo);
			
			driverService.save(driver) ;
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��ӳɹ���") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ; 
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��ID�Ѵ��ڣ�") ;
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
	 * ��ת����ͼչʾҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showTheMap_bigger.do", produces = "text/html;charset=UTF-8")
	public String showTheMap_bigger(HttpServletRequest request) {
		
		List<Line> allLines = lineService.findAll(Line.class) ;

		Iterator<Line> iter = allLines.iterator() ;
		
		Map<String, List<StationOfLine>> map = new HashMap<String, List<StationOfLine>>() ;
		
		List<Map<String, List<StationOfLine>>> list = new ArrayList<Map<String, List<StationOfLine>>>() ;
		
		while (iter.hasNext()) {
			Line line = iter.next() ;
			String hql = "select en from " + StationOfLine.class.getSimpleName() + " en where en.line.lineId=?0" ;
			Object[] params = {line.getLineId()} ;
			List<StationOfLine> allStationsOfThisLine = stationOfLineService.find(hql, params) ;
			
			map.put(""+line.getLineId(), allStationsOfThisLine) ;		//ͨ��ÿ��lineId�����ҵ����ڴ�lineId��վ��
			
			list.add(map) ;
		}
		
		request.setAttribute("allStationsOfAllLine", list);
		
		request.setAttribute("allLines", allLines);
		
		return "jsp/admin/showTheMap_bigger" ;
	}
	
	/**
	 * �Զ�Ϊ����Ա��ָ���ϳ��ص�
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setStationForAllStaff.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setStationForAllStaff(HttpServletRequest request) {
		List<Staff> allStaffs = staffService.findAll(Staff.class) ;
		Iterator<Staff> iter = allStaffs.iterator() ;
		
		List<Station> allStations = stationService.findAll(Station.class) ;
		
		
		while (iter.hasNext()) {
			Staff staff = iter.next() ;
			
			if (staff.getHome_longitude() != null && !staff.getHome_longitude().equals("")) {
				if (allStations.size() == 1) {
					staff.setStation(allStations.get(0));
					
					Map<String, String> map = new HashMap<String, String>() ;
					map.put("info", "ȫ����Ա�ϳ�վ�㶼��ɣ�" + allStations.get(0)) ;
					ObjectMapper mapper = new ObjectMapper() ;
					try {
						String content = mapper.writeValueAsString(map) ; 
						return content ;
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					//���������˼���Ȼ�ù�Ա�������ľ���
					Station station_flag = allStations.get(0) ;
					double distance_flag = LogLatDistance.LantitudeLongitudeDist(Double.parseDouble(station_flag.getLongitude()), Double.parseDouble(station_flag.getLatitude()), Double.parseDouble(staff.getHome_longitude()), Double.parseDouble(staff.getHome_latitude())) ;
					
					//Ȼ���ټ����վ�����Ա�ľ��룬���ֱ�flagС�Ļ������滻��flag
					for (int i = 1; i < allStations.size(); i ++) {
						Station station_query = allStations.get(i) ;
						
						double distance_query = LogLatDistance.LantitudeLongitudeDist(Double.parseDouble(station_query.getLongitude()), Double.parseDouble(station_query.getLatitude()), Double.parseDouble(staff.getHome_longitude()), Double.parseDouble(staff.getHome_latitude())) ;
						
						if (distance_query < distance_flag) {
							station_flag = station_query ;
							distance_flag = distance_query ;
						}
					}
					
					staff.setStation(station_flag) ;
					staffService.update(staff);
					
					System.out.println(staff.getStaffNo() + "-" + staff.getName() + "�鲢����" + station_flag.getStationName());
					
				}
			} else {
				System.out.println(staff.getStaffNo() + "-" + staff.getName() + "��û������סַ��γ��");
			}
			
		}
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�����ɹ���") ;
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
	 * Ϊ����Ƴ�����
	 * @param request
	 * @param carNo
	 * @param classesId
	 * @return
	 */
	@RequestMapping(value="/deleteCarForClasses.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteCarForClasses(HttpServletRequest request, String carNo, String classesId) {
		
		Car car = carService.get(Car.class, carNo);

		car.setClasses(null);
		
		carService.update(car);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�ɹ����������"+car.getCarNo()+"�����Σ�"+classesId+"��֮��Ĺ�ϵ��") ;
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
	 * ���س���
	 * @param request
	 * @param carNo_selected
	 * @return
	 */
	@RequestMapping(value="/getTheCar_setCarForClasses.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getCar(HttpServletRequest request, String carNo_selected) {
		
		Car car = carService.get(Car.class, carNo_selected);
		
		Set<Driver> drivers = car.getDrivers() ;
		
		Iterator<Driver> iter = drivers.iterator() ;
		
		String str_driver = "";
		
		while(iter.hasNext()) {
			str_driver += (iter.next().getName() + "��") ;
		}
		
		String str[] = {car.getBrand(), car.getSeat(), str_driver} ;
		
		System.out.println(str[0] + "," + str[1] + "," + str[2]);
		
		Map<String, String[]> map = new HashMap<String, String[]>() ;
		map.put("car_selected", str) ;
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
	 * �Զ�������·��վ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setLineAndStationByIntel.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setLineAndStationByIntel(HttpServletRequest request) {
		List<Station> allStations = stationService.findAll(Station.class) ;
		
		if (allStations.size() == 1) {
			System.out.println("վ�����ֻ���ڳ���һ����׼������Ա��סַ����վ��...");
			
			
		}
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�����ɹ���") ;
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
	//setLineAndStationByIntel.doҪ�õ��ķ���
//	public void setStationByStaff() {
//		
//		int lineId = lineService.findAll(Line.class).size() + 1;
//		 
//		List<Staff> allStaffs = staffService.findAll(Staff.class) ;
//		//(�ȱ�ʵ�����)��������ʹ�þ����㷨�����й�Աסַ�ֲ�ת��Ϊ��С��һ�Ĵ�
//		int allStaffs_count = allStaffs.size() ;
//		if (allStaffs_count != 0) {
//			for (int i = 0 ; i < allStaffs_count; i ++) {
//				Staff staff = allStaffs.get(i) ;
//				if (i == 1) {			//��һ��ɨ�赽��Ա��ֱ��������λ������һ��station
//					if (staff.getHome() != "" && staff.getHome() != null) {
//						Line line = new Line () ;
//						line.setLineId(lineId) ;
//						line.setLineName(lineId + "����");
//						
//						Station station = new Station() ;
//						station.set
//					}
//					
//				}
//			}
//		}
//	}
	
	/**
	 * �����·
	 * @param request
	 * @param lineId
	 * @param lineName
	 * @return
	 */
	@RequestMapping(value="/addLine.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addLine(HttpServletRequest request, String lineId, String lineName) {
		
		if (lineService.get(Line.class, Integer.parseInt(lineId)) == null) {
			Line line = new Line() ;
			line.setLineId(Integer.parseInt(lineId));
			line.setLineName(lineName);
			line.setPopulation(0);
			line.setRate(0);
			
			lineService.save(line) ;
			
			StationOfLine sol = new StationOfLine() ;
			
			String hql = "select en from " + Station.class.getSimpleName() + " en where en.stationName = ?0" ;
			Object[] params = {"����"} ;
			Station station = stationService.find(hql, params).get(0) ;
			
			sol.setStation(station);
			sol.setLine(line);
			sol.setStep(1);
			
			stationOfLineService.save(sol) ;
			
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��ӳɹ���") ;
			ObjectMapper mapper = new ObjectMapper() ;
			try {
				String content = mapper.writeValueAsString(map) ; 
				return content ;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("info", "��lineId�Ѵ��ڣ��뻻��ID��") ;
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
	 * ɾ����·
	 * @param request
	 * @param lineId
	 * @return
	 */
	@RequestMapping(value="/deleteLine.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteLine(HttpServletRequest request, int lineId) {
		//׼����Ҫɾ����line
		Line line = lineService.get(Line.class, lineId) ;
		
		Set<StationOfLine> stationOfLines = line.getStationOfLines() ;
		Iterator<StationOfLine> iter = stationOfLines.iterator() ;
		while (iter.hasNext()) {
			StationOfLine sol = iter.next() ;
			Station station = sol.getStation() ;
//			sol.setStation(null);
//			stationOfLineService.update(sol);
			
			Set<Staff> staffs = station.getStaffs() ;
			Iterator<Staff> iter2 = staffs.iterator() ;
			while (iter2.hasNext()) {
				Staff staff = iter2.next() ;
				staff.setStation(null);
				staffService.update(staff);
			}
			
			stationOfLineService.delete(StationOfLine.class, lineId, station.getStationId());
			if (station.getStationName() != "����" && !station.getStationName().equals("����")) {
				stationService.delete(station);
			}
		}
		
		//׼����ɾ����Classes
		Set<Classes> allClassesOfThisLine = line.getClasses() ;
		Iterator<Classes> iter3 = allClassesOfThisLine.iterator() ;
		while (iter3.hasNext()) {
			Classes classes = iter3.next() ;
			//���Ƴ�classes��line�Ĺ�ϵ
			classes.setLine(null);
			
			//������
			Set<Car> allCarsOfThisClasses = classes.getCars() ;
			Iterator<Car> iter4 = allCarsOfThisClasses.iterator() ;
			while (iter4.hasNext()) {
				Car car = iter4.next() ;
				car.setClasses(null);
				carService.update(car);
			}
			
			//����Ա��
			Set<Staff> allStaffsOfThisClasses = classes.getStaffs() ;
			Iterator<Staff> iter5 = allStaffsOfThisClasses.iterator() ;
			while (iter5.hasNext()) {
				Staff staff = iter5.next() ;
				staff.setClasses(null);
				staffService.update(staff);
			}
			
			//ɾ�����
			classesService.delete(classes);
		}
		
		lineService.delete(line);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", line.getLineName() + "�Լ������Ϣ�ѱ�ɾ����") ;
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
	 * һ������˾���Ű�
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setDriversArrageByIntel.do", produces = "text/html;charset=UTF-8")
	public @ResponseBody String setDriversArrageByIntel(HttpServletRequest request) {
		
		//��������б���
//		String hql = "delete " + ArrangeInfo.class.getSimpleName() ;
//		arrangeInfoService.find(hql) ;
//		hql = "delete " + DayArrange.class.getSimpleName() ;
//		dayArrangeService.find(hql) ;
//		hql = "delete " + WeekArrange.class.getSimpleName() ;
//		weekArrangeService.find(hql) ;
//		hql = "delete " + MonthArrange.class.getSimpleName() ;
//		monthArrangeService.find(hql) ;
		
		//��������б���
		List<ArrangeInfo> arrangeInfos = arrangeInfoService.findAll(ArrangeInfo.class) ;
		Iterator<ArrangeInfo> iter4 = arrangeInfos.iterator() ;
		while (iter4.hasNext()) {
			arrangeInfoService.delete(iter4.next());
		}
		List<DayArrange> dayArranges = dayArrangeService.findAll(DayArrange.class) ;
		Iterator<DayArrange> iter = dayArranges.iterator() ;
		while (iter.hasNext()) {
			dayArrangeService.delete(iter.next());
		}
		List<WeekArrange> weekArranges = weekArrangeService.findAll(WeekArrange.class) ;
		Iterator<WeekArrange> iter2 = weekArranges.iterator() ;
		while (iter2.hasNext()) {
			weekArrangeService.delete(iter2.next());
		}
		List<MonthArrange> monthArranges = monthArrangeService.findAll(MonthArrange.class) ;
		Iterator<MonthArrange> iter3 = monthArranges.iterator() ;
		while (iter3.hasNext()) {
			monthArrangeService.delete(iter3.next());
		}
//		
//		//��ȷ������µ���ֹ����(31��)
		Date month_date_begin = new Date() ;
		 Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(month_date_begin); 
	     calendar.add(Calendar.DATE,30);//��������������һ��.����������,������ǰ�ƶ� 
//		Date month_date_end = new Date(month_date_begin.getTime()+30*24*60*60*1000000) ;
	     Date month_date_end = calendar.getTime() ;
		MonthArrange monthArrange = new MonthArrange() ;
		monthArrange.setMonthArrangeId(month_date_begin.getMonth()+1);
		monthArrange.setDate_begin(month_date_begin);
		monthArrange.setDate_end(month_date_end);
		monthArrangeService.save(monthArrange) ;
		//ȷ���������ڵ���ֹ����
//		long week_date_begin_flag = month_date_begin.getTime() ;
		Date week_date_begin_flag = month_date_begin ;
		for (int i = 0 ; i <= 4; i ++) {
			if (i == 0) {	//��һ��
				Date week_date_begin = week_date_begin_flag ;
				 Calendar   calendar2   =   new   GregorianCalendar(); 
				 calendar2.setTime(week_date_begin_flag); 
				 calendar2.add(Calendar.DATE,6);//��������������һ��.����������,������ǰ�ƶ� 
				Date Week_date_end = calendar2.getTime() ;
				calendar2.add(Calendar.DATE, 1) ;
				week_date_begin_flag = calendar2.getTime() ;
				
				WeekArrange weekArrange = new WeekArrange() ;
				weekArrange.setWeekArrangeId(i + 1);
				weekArrange.setDate_begin(week_date_begin);
				weekArrange.setDate_end(Week_date_end);
				weekArrange.setMonthArrange(monthArrange);
				weekArrangeService.save(weekArrange) ;
				
				//ȷ��7��
				Date date_flag = week_date_begin ;
				for (int j = i*7+1 ; j <= 7 ; j ++) {
					DayArrange dayArrange = new DayArrange() ;
					dayArrange.setDayArrangeId(j);
					
					dayArrange.setDate(date_flag);
					
					 Calendar   calendar3   =   new   GregorianCalendar(); 
					 calendar3.setTime(date_flag); 
					 calendar3.add(Calendar.DATE,1);//��������������һ��.����������,������ǰ�ƶ� 
					
					 date_flag = calendar3.getTime() ;
					 
					dayArrange.setWeekArrange(weekArrange);
					dayArrangeService.save(dayArrange) ;
				}
			} else if (i == 4) {
				Date week_date_begin = week_date_begin_flag ;
				Calendar   calendar2   =   new   GregorianCalendar(); 
				calendar2.setTime(week_date_begin_flag); 
				calendar2.add(Calendar.DATE,2);//��������������һ��.����������,������ǰ�ƶ� 
				Date Week_date_end = calendar2.getTime() ;
				
				WeekArrange weekArrange = new WeekArrange() ;
				weekArrange.setWeekArrangeId(i + 1);
				weekArrange.setDate_begin(week_date_begin);
				weekArrange.setDate_end(Week_date_end);
				weekArrange.setMonthArrange(monthArrange);
				weekArrangeService.save(weekArrange) ;
				
				//ȷ��3��
				Date date_flag = week_date_begin ;
				for (int j = i*7+1 ; j <= 31 ; j ++) {
					DayArrange dayArrange = new DayArrange() ;
					dayArrange.setDayArrangeId(j);
					
					dayArrange.setDate(date_flag);
					
					 Calendar   calendar3   =   new   GregorianCalendar(); 
					 calendar3.setTime(date_flag); 
					 calendar3.add(Calendar.DATE,1);//��������������һ��.����������,������ǰ�ƶ� 
					
					 date_flag = calendar3.getTime() ;
					 
					dayArrange.setWeekArrange(weekArrange);
					dayArrangeService.save(dayArrange) ;
				}
			} else {
				Date week_date_begin = week_date_begin_flag ;
				Calendar   calendar2   =   new   GregorianCalendar(); 
				calendar2.setTime(week_date_begin_flag); 
				calendar2.add(Calendar.DATE,6);//��������������һ��.����������,������ǰ�ƶ� 
				Date Week_date_end = calendar2.getTime() ;
				calendar2.add(Calendar.DATE, 1);
				week_date_begin_flag = calendar2.getTime() ;
				
				WeekArrange weekArrange = new WeekArrange() ;
				weekArrange.setWeekArrangeId(i + 1);
				weekArrange.setDate_begin(week_date_begin);
				weekArrange.setDate_end(Week_date_end);
				weekArrange.setMonthArrange(monthArrange);
				weekArrangeService.save(weekArrange) ;
				
				//ȷ��7��
				Date date_flag = week_date_begin ;
				for (int j = i * 7 + 1; j <= i * 7 + 7; j ++) {
					DayArrange dayArrange = new DayArrange() ;
					dayArrange.setDayArrangeId(j);
					
					dayArrange.setDate(date_flag);
					
					 Calendar   calendar3   =   new   GregorianCalendar(); 
					 calendar3.setTime(date_flag); 
					 calendar3.add(Calendar.DATE,1);//��������������һ��.����������,������ǰ�ƶ� 
					
					 date_flag = calendar3.getTime() ;
					 
					dayArrange.setWeekArrange(weekArrange);
					dayArrangeService.save(dayArrange) ;
				}
			}
		}
		
		//��ʱ����List���Ǹ��¹���
		arrangeInfos = arrangeInfoService.findAll(ArrangeInfo.class) ;
		dayArranges = dayArrangeService.findAll(DayArrange.class) ;
		weekArranges = weekArrangeService.findAll(WeekArrange.class) ;
		monthArranges = monthArrangeService.findAll(MonthArrange.class) ;
		
		//��ʼ�Ű����
		List<Car> allCars = carService.findAll(Car.class) ;
		Iterator<Car> iter6 = allCars.iterator() ;
		while (iter6.hasNext()) {
			Car car = iter6.next() ;
			switch (car.getDrivers().size()) {
			case 1 : Driver d = car.getDrivers().iterator().next() ;
					 Iterator<DayArrange> iter7 = dayArranges.iterator() ;
					 while (iter7.hasNext()) {
						 DayArrange da = iter7.next() ;
						 ArrangeInfo ai = new ArrangeInfo() ;
						 ai.setDayArrange(da);
						 ai.setDriver(d);
						 arrangeInfoService.save(ai) ;
					 }
				break ;
			case 2 : Driver d1 = car.getDrivers().iterator().next() ;
					 Driver d2 = car.getDrivers().iterator().next() ;
					 for (int i = 0 ; i <= dayArranges.size()-2 ; i += 2) {
						 DayArrange da1 = dayArranges.get(i) ;
						 DayArrange da2 = dayArranges.get(i+1) ;
						 ArrangeInfo ai1 = new ArrangeInfo() ;
						 ArrangeInfo ai2 = new ArrangeInfo() ;
						 ai1.setDayArrange(da1);
						 ai1.setDriver(d1);
						 ai2.setDayArrange(da2);
						 ai2.setDriver(d2);
						 arrangeInfoService.save(ai1) ;
						 arrangeInfoService.save(ai2) ;
					 }
//					 Iterator<DayArrange> iter8 = dayArranges.iterator() ;
//					 while (iter8.hasNext()) {
//						 DayArrange da1 = iter8.next() ;
//						 DayArrange da2 = iter8.next() ;
//						 ArrangeInfo ai1 = new ArrangeInfo() ;
//						 ArrangeInfo ai2 = new ArrangeInfo() ;
//						 ai1.setDayArrange(da1);
//						 ai1.setDriver(d1);
//						 ai2.setDayArrange(da2);
//						 ai2.setDriver(d2);
//						 arrangeInfoService.save(ai1) ;
//						 arrangeInfoService.save(ai2) ;
//					 }
				break ;
			}
		}
		
//		
//		List<Driver> drivers_alone = new ArrayList<Driver>() ;				//һ��һ̨����˾��
//		List<Driver> drivers_double = new ArrayList<Driver>() ;				//����һ̨����˾��
////		
//		List<Driver> allDrivers = driverService.findAll(Driver.class) ;
//		Iterator<Driver> iter6 = allDrivers.iterator() ;
//		while (iter6.hasNext()) {
//			Driver  d = iter6.next() ;
//			if (d.getCar().getDrivers().size() == 1) {
//				drivers_alone.add(d) ;
//			} else if (d.getCar().getDrivers().size() == 2){
//				drivers_double.add(d) ;
//			}
//		}
//		//һ��һ̨����˾���İ���		
//		Iterator<Driver> iter7 = drivers_alone.iterator() ;
//		while (iter7.hasNext()) {
//			Driver d = iter7.next() ;
//			Iterator<DayArrange> iter8 = dayArranges.iterator() ;
//			while (iter8.hasNext()) {
//				DayArrange da = iter8.next() ;
//				
//				ArrangeInfo ai = new ArrangeInfo() ;
//				ai.setDayArrange(da);
//				ai.setDriver(d);
//			}
//		}
//		//		����һ̨����˾���İ���		
//		for (int i = 0 ; i < drivers_double.size(); i += 2) {
//			for (int j = 0 ; j < dayArranges.size(); j += 2) {
//				Driver d = drivers_double.get(i) ;
//				DayArrange da = dayArranges.get(j) ;
//				ArrangeInfo ai = new ArrangeInfo() ;
//				ai.setDriver(d);
//				ai.setDayArrange(da);
//				arrangeInfoService.save(ai) ;
//				
//			}
//			for (int j = 1 ; j < drivers_double.size()-2; j +=2) {
//				Driver d = drivers_double.get(i) ;
//				DayArrange da = dayArranges.get(j) ;
//				ArrangeInfo ai = new ArrangeInfo() ;
//				ai.setDriver(d);
//				ai.setDayArrange(da);
//				arrangeInfoService.save(ai) ;
//			}
//		}
		
//		List<Driver> allDrivers = driverService.findAll(Driver.class) ;
//		//Iterator<Driver> iter7 = allDrivers.iterator() ;
//		int i = 0 ;
//		while (i < allDrivers.size()) {
//			Driver driver = allDrivers.get(i) ;
//			
//			if (driver.getCar().getDrivers().size() == 1) {				//һ����һ������ֱ��ÿ�춼���ϰ�		
//				int j = 0 ;
//				while (j < dayArranges.size()) {
//					DayArrange da = dayArranges.get(j) ;
//					
//					ArrangeInfo ai = new ArrangeInfo() ;
//					ai.setDriver(driver);
//					ai.setDayArrange(da);
//					arrangeInfoService.save(ai) ;
//					
//					j++ ;
//				}
//				
//				i++ ;
//				continue ;
//			}
//			
//			if (driver.getCar().getDrivers().size() == 2) {				//������һ�����������ϰ�
//				int j = 0 ; 
//				while (j < dayArranges.size()) {
//					if (j % 2 == 0) {
//						DayArrange da = dayArranges.get(j) ;
//						ArrangeInfo ai = new ArrangeInfo() ;
//						ai.setDayArrange(da);
//						ai.setDriver(driver);
//						arrangeInfoService.save(ai) ;
//						j++ ;
//					} else {
//						DayArrange da = dayArranges.get(j) ;
//						ArrangeInfo ai = new ArrangeInfo() ;
//						Driver driver2 = new Driver() ;
//						Set<Driver> drivers = driver.getCar().getDrivers() ;
//						for (int z = 0 ; z < drivers.size() ; z ++) {
//							Driver driver_flag = (Driver)drivers.toArray()[z] ;
//							if (driver_flag.getId() != driver.getId()) {
//								driver2 = driver_flag ;
//							}
//						}
//						ai.setDayArrange(da);
//						ai.setDriver(driver2);
//						arrangeInfoService.save(ai) ;
//						j++ ;
//					}
//					
//				}
//				i ++ ;
//				continue ;
//			}
//		}
		
		//iter = dayArranges.iterator() ;
//		int i = 0 ;
//		while (i < dayArranges.size()) {
//			DayArrange dayArrange = dayArrangeService.get(DayArrange.class, i + 1) ;
//			
//			List<Car> allCars = carService.findAll(Car.class) ;
//			Iterator<Car> iter6 = allCars.iterator() ;
//			while (iter6.hasNext()) {
//				//ȡ������
//				Car car = iter6.next() ;
//				//ȡ������˾��
//				Set<Driver> allDriversOfThisCar = car.getDrivers() ;
//				if (allDriversOfThisCar.size() == 0) {
//					System.out.println(car.getCarNo() + "��û��ָ���κ�˾��!");
//				}
//				if (allDriversOfThisCar.size() == 1) {
//					System.out.println(car.getCarNo() + "ֻ��ָ����һλ˾��,������λ˾��ÿ�춼Ҫ�ϰ�");
//					Driver driver = (Driver)allDriversOfThisCar.toArray()[0] ;
//					ArrangeInfo arrangeInfo = new ArrangeInfo() ;
//					arrangeInfo.setDriver(driver);
//					arrangeInfo.setDayArrange(dayArrange) ;
//					arrangeInfoService.save(arrangeInfo) ;
//					
//					i++;
//				}
//				if (allDriversOfThisCar.size() == 2) {
//					System.out.println(car.getCarNo() + "��ָ������λ˾�������Կ��Ը����ϰ�");
//					Driver driver1 = (Driver)allDriversOfThisCar.toArray()[0] ;
//					Driver driver2 = (Driver)allDriversOfThisCar.toArray()[1] ;
//					ArrangeInfo arrangeInfo1 = new ArrangeInfo() ;
//					ArrangeInfo arrangeInfo2 = new ArrangeInfo() ;
//					arrangeInfo1.setDriver(driver1);
//					arrangeInfo1.setDayArrange(dayArrange);
//					arrangeInfoService.save(arrangeInfo1) ;
//					
//					//�ڶ���˾������Ϊ��һ��
//					DayArrange dayArrange_needed = dayArrangeService.get(DayArrange.class, dayArrange.getDayArrangeId()+1) ;
//					arrangeInfo2.setDayArrange(dayArrange_needed);
//					arrangeInfo2.setDriver(driver2) ;
//					arrangeInfoService.save(arrangeInfo2) ;
//					
//					i += 2 ;
//				}
//			}
//		}
		
//		try {
//			//��ȫ��ʱ�䶼ȥ��ʱ�䲿�֣�ֻ��������
//			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd") ;
//			//�������Ű��
//			for (int a = 0 ; a < monthArranges.size(); a ++) {
//				MonthArrange ma = monthArranges.get(a) ;
//				Date date_flag1 = ma.getDate_begin() ;
//				String date_flag1_string = sdf.format(date_flag1) ;
//				date_flag1 = sdf.parse(date_flag1_string) ;
//				ma.setDate_begin(date_flag1);
//				
//				Date date_flag2 = ma.getDate_end() ;
//				String date_flag2_string = sdf.format(date_flag2) ;
//				date_flag2 = sdf.parse(date_flag2_string) ;
//				ma.setDate_end(date_flag2);
//				
//				monthArrangeService.update(ma);
//				System.out.println(ma.getDate_begin());
//			}
//			
//			for (int b = 0 ; b < weekArranges.size(); b ++) {
//				WeekArrange wa = weekArranges.get(b) ;
//				Date date_flag1 = wa.getDate_begin() ;
//				String date_flag1_string = sdf.format(date_flag1) ;
//				date_flag1 = sdf.parse(date_flag1_string) ;
//				wa.setDate_begin(date_flag1);
//				
//				Date date_flag2 = wa.getDate_end() ;
//				String date_flag2_string = sdf.format(date_flag2) ;
//				date_flag2 = sdf.parse(date_flag2_string) ;
//				wa.setDate_end(date_flag2);
//				
//				weekArrangeService.update(wa);
//			}
//			
//			for (int c = 0 ; c < dayArranges.size(); c ++) {
//				DayArrange da = dayArranges.get(c) ;
//				Date date_flag1 = da.getDate() ;
//				String date_flag1_string = sdf.format(date_flag1) ;
//				date_flag1 = sdf.parse(date_flag1_string) ;
//				da.setDate(date_flag1);
//				
//				dayArrangeService.update(da);
//			}
//		} catch (Exception e) {
//			System.out.println("some error occured");
//		}
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("info", "�����ɹ���") ;
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
	 * ����DayArrangeId���ArrangeInfo
	 * @param request
	 * @param dayArrangeId
	 * @return
	 */
	@RequestMapping(value="/getArrangeInfoByDayArrangeId.do", produces = "text/html;charset=UTF-8")
	public String getArrangeInfoByDayArrangeId(HttpServletRequest request, int dayArrangeId) {
		String hql = "select en from " + ArrangeInfo.class.getSimpleName() + " en where en.dayArrange.dayArrangeId = ?0" ;
		Object[] params = {dayArrangeId} ;
		List<ArrangeInfo> arrangeInfos = arrangeInfoService.find(hql, params) ;
		
		DayArrange da = dayArrangeService.get(DayArrange.class, dayArrangeId) ;
		
		request.setAttribute("arrangeInfos", arrangeInfos);
		request.setAttribute("dayArrange", da);
		
		return "jsp/admin/showArrange_info" ;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public ClassesService getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

	public DayArrangeService getDayArrangeService() {
		return dayArrangeService;
	}

	public void setDayArrangeService(DayArrangeService dayArrangeService) {
		this.dayArrangeService = dayArrangeService;
	}

	public WeekArrangeService getWeekArrangeService() {
		return weekArrangeService;
	}

	public void setWeekArrangeService(WeekArrangeService weekArrangeService) {
		this.weekArrangeService = weekArrangeService;
	}

	public MonthArrangeService getMonthArrangeService() {
		return monthArrangeService;
	}

	public void setMonthArrangeService(MonthArrangeService monthArrangeService) {
		this.monthArrangeService = monthArrangeService;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public LineService getLineService() {
		return lineService;
	}

	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}

	public StationService getStationService() {
		return stationService;
	}

	public void setStationService(StationService stationService) {
		this.stationService = stationService;
	}

	public StationOfLineService getStationOfLineService() {
		return stationOfLineService;
	}

	public void setStationOfLineService(StationOfLineService stationOfLineService) {
		this.stationOfLineService = stationOfLineService;
	}
}
