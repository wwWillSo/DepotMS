package szw.depotms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="[Staff]")
public class Staff {
	private int staffNo ;				//工号
	private String name ;				//姓名
	private String groupName ;				//组别
	private String password ;			//登陆密码 
	private String home ;				//家庭住址
	private String home_longitude ;		//经度
	private String home_latitude ;		//纬度
	
	private Station station ;			//站点ID
	
	private Dept dept ;					//部门ID
	
	private Classes classes ;			//班次ID
	
	@Id
	public int getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToOne(targetEntity=Station.class)
	@JoinColumn(name="stationId", nullable=true)			//某些情况可以为空
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	
	@ManyToOne(targetEntity=Dept.class)
	@JoinColumn(name="deptNo", nullable=false)
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
	@ManyToOne(targetEntity=Classes.class)
	@JoinColumn(name="classesId")
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	@Column(nullable = true)
	public String getHome_longitude() {
		return home_longitude;
	}
	public void setHome_longitude(String home_longitude) {
		this.home_longitude = home_longitude;
	}
	
	@Column(nullable = true)
	public String getHome_latitude() {
		return home_latitude;
	}
	public void setHome_latitude(String home_latitude) {
		this.home_latitude = home_latitude;
	}
}
