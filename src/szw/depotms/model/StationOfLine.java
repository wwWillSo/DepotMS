package szw.depotms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//��·;��վ����
public class StationOfLine {
	private int stationOfLineId ;				//����ID
	private int population ;					//�ڴ�վ�ϳ�������(ʵ������,���ڼ��������)
	private int step ;							//��վ���ڴ���·�е�˳��
	
	private Line line ;							//��·ID
	private Station station ;					//վ��ID
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
