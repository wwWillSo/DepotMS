package szw.depotms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
//站点
public class Station {
	private int stationId ; 			//站点ID
	private String stationName ;		//站点名称
	private String stationAddress ;		//站点详细地址
	private String longitude ;			//经度
	private String latitude ;			//纬度
	
	private Set<StationOfLine> statonOfLines = new HashSet<StationOfLine>() ;
	
	private Set<Staff> staffs = new HashSet<Staff>() ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationAddress() {
		return stationAddress;
	}
	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}
	
	@OneToMany(targetEntity=StationOfLine.class, mappedBy="station", cascade=CascadeType.ALL)
	public Set<StationOfLine> getStatonOfLines() {
		return statonOfLines;
	}
	public void setStatonOfLines(Set<StationOfLine> statonOfLines) {
		this.statonOfLines = statonOfLines;
	}
	
	@OneToMany(targetEntity=Staff.class, mappedBy="station")
	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	
	@Column(nullable = true)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(nullable = true)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
}
