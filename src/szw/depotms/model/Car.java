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
	private String carNo ;					//����
	private String brand ;					//����Ʒ��
	private String seat ;					//��λ��
	private String date_register ;			//ע������
	private String date_insurance ;			//�������ڣ��������ڣ�
	private String drivingLicenseNo ;		//��ʻ֤����
	private String drivingLicensePhoto ;	//��ʻ֤��Ƭ
	
	private Classes classes ;						//���ID
	
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
	@JoinColumn(name="classesId", nullable=true)			//ĳЩ�������Ϊ��
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
