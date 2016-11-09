package szw.depotms.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
//�Ű��(��)
public class DayArrange {
	private int dayArrangeId ;				//�Ű��ID
	private Date date ;						//����
	
	private WeekArrange weekArrange ;		//������ID
	
	private Set<ArrangeInfo> arrangeInfo = new HashSet<ArrangeInfo>() ;
	
	@Id
	public int getDayArrangeId() {
		return dayArrangeId;
	}
	public void setDayArrangeId(int dayArrangeId) {
		this.dayArrangeId = dayArrangeId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@ManyToOne(targetEntity=WeekArrange.class)
	@JoinColumn(name="weekArrangeId", nullable=true) 
	public WeekArrange getWeekArrange() {
		return weekArrange;
	}
	public void setWeekArrange(WeekArrange weekArrange) {
		this.weekArrange = weekArrange;
	}
	
	@OneToMany(targetEntity=ArrangeInfo.class, mappedBy="dayArrange", cascade=CascadeType.ALL)
	public Set<ArrangeInfo> getArrangeInfo() {
		return arrangeInfo;
	}
	public void setArrangeInfo(Set<ArrangeInfo> arrangeInfo) {
		this.arrangeInfo = arrangeInfo;
	}
}
