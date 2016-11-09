package szw.depotms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Driver {
	private int id ;				//˾��ID
	private String name ;			//����
	private String driverLicenseNo ;		//��ʻ֤����
	private String driverLicensePhoto ;		//��ʻ֤��Ƭ
	
	private Car car ;				//����
	
	private Set<ArrangeInfo> arrangeInfo = new HashSet<ArrangeInfo>() ;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}
	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}
	public String getDriverLicensePhoto() {
		return driverLicensePhoto;
	}
	public void setDriverLicensePhoto(String driverLicensePhoto) {
		this.driverLicensePhoto = driverLicensePhoto;
	}
	
	@ManyToOne(targetEntity=Car.class)
	@JoinColumn(name="carId")
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	@OneToMany(targetEntity=ArrangeInfo.class, mappedBy="driver", cascade = CascadeType.ALL)
	public Set<ArrangeInfo> getArrangeInfo() {
		return arrangeInfo;
	}
	public void setArrangeInfo(Set<ArrangeInfo> arrangeInfo) {
		this.arrangeInfo = arrangeInfo;
	}
}
