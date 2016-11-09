package szw.depotms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
//班次表
public class Classes {
	private int classesId ;					//班次ID
	private String time ; 					//出发时间
	private String note ;					//备注
	
	private Line line ;						//线路ID
	
	private Set<Staff> staffs = new HashSet<Staff>() ;
	
	private Set<Car> cars = new HashSet<Car>() ;
	
	@Id
	public int getClassesId() {
		return classesId;
	}
	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@ManyToOne(targetEntity=Line.class)
	@JoinColumn(name="lineId")
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	
	@OneToMany(targetEntity=Staff.class, mappedBy="classes")
	public Set<Staff> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	
	@OneToMany(targetEntity=Car.class, mappedBy="classes")
	public Set<Car> getCars() {
		return cars;
	}
	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
}
