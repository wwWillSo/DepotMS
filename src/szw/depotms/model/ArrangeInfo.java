package szw.depotms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class ArrangeInfo {					//排班详情
	private long id ;						//排班详情ID
	
	private DayArrange dayArrange ;			//排班表Id
	private Driver driver ;					//司机ID
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=DayArrange.class)
	@JoinColumn(name="dayArrangeId")
	public DayArrange getDayArrange() {
		return dayArrange;
	}

	public void setDayArrange(DayArrange dayArrange) {
		this.dayArrange = dayArrange;
	}
	
	@ManyToOne(targetEntity=Driver.class)
	@JoinColumn(name="driverId")
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
