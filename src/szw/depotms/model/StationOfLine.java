package szw.depotms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//线路途径站详情
public class StationOfLine {
	private int stationOfLineId ;				//主键ID
	private int population ;					//在此站上车的人数(实际人数,用于计算乘坐率)
	private int step ;							//该站点在此线路中的顺序
	
	private Line line ;							//线路ID
	private Station station ;					//站点ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStationOfLineId() {
		return stationOfLineId;
	}
	public void setStationOfLineId(int stationOfLineId) {
		this.stationOfLineId = stationOfLineId;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
	@ManyToOne(targetEntity=Line.class)
	@JoinColumn(name="lineId", nullable=false)
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	
	@ManyToOne(targetEntity=Station.class)
	@JoinColumn(name="stationId", nullable=false)
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	
	@Column(nullable=false, columnDefinition="INT default 1")
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
	
}
