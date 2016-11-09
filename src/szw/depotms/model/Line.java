package szw.depotms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
//线路
public class Line {
	private int lineId ;				//线路ID
	private String lineName ; 			//线路名称
	private int population ;			//总人数
	private double rate ;				//乘坐率
	
	private Set<StationOfLine> stationOfLines = new HashSet<StationOfLine>() ;
	
	private Set<Classes> classes = new HashSet<Classes>() ;
	
	@Id
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	@Column(nullable=true)
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	@Column(nullable=true)
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	@OneToMany(targetEntity=StationOfLine.class, mappedBy="line")
	public Set<StationOfLine> getStationOfLines() {
		return stationOfLines;
	}
	public void setStationOfLines(Set<StationOfLine> stationOfLines) {
		this.stationOfLines = stationOfLines;
	}
	
	@OneToMany(targetEntity=Classes.class, mappedBy="line")
	public Set<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}
	
}
