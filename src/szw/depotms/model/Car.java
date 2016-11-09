package szw.depotms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Car {
	private String carNo ;					//车牌
	private String brand ;					//车辆品牌
	private String seat ;					//座位数
	private String date_register ;			//注册日期
	private String date_insurance ;			//保险日期（到期日期）
	private String drivingLicenseNo ;		//行驶证号码
	private String drivingLicensePhoto ;	//行驶证照片
	
	private Classes classes ;						//班次ID
	
	private Set<Driver> drivers = new HashSet<Driver>() ;
	
	@Id
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getDate_register() {
		return date_register;
	}
	public void setDate_register(String date_register) {
		this.date_register = date_register;
	}
	public String getDate_insurance() {
		return date_insurance;
	}
	public void setDate_insurance(String date_insurance) {
		this.date_insurance = date_insurance;
	}
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}
	public String getDrivingLicensePhoto() {
		return drivingLicensePhoto;
	}
	public void setDrivingLicensePhoto(String drivingLicensePhoto) {
		this.drivingLicensePhoto = drivingLicensePhoto;
	}
	
	@ManyToOne(targetEntity=Classes.class)
	@JoinColumn(name="classesId", nullable=true)			//某些情况可以为空
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	@OneToMany(targetEntity=Driver.class, mappedBy="car")
	public Set<Driver> getDrivers() {
		return drivers;
	}
	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}
	
	
	
}
