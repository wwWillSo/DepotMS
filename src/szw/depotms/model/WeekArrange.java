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
//���Ű��
public class WeekArrange {
	private int weekArrangeId ;				//�Ű��ID
	private Date date_begin ;				//��ʼ����
	private Date date_end ;					//��������
	
	private Set<DayArrange> dayArranges = new HashSet<DayArrange>() ;
	
	private MonthArrange monthArrange ;		//�����·�
	
	@Id
	public int getWeekArrangeId() {
		return weekArrangeId;
	}
	public void setWeekArrangeId(int weekArrangeId) {
		this.weekArrangeId = weekArrangeId;
	}
	public Date getDate_begin() {
		return date_begin;
	}
	public void setDate_begin(Date date_begin) {
		this.date_begin = date_begin;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	
	@OneToMany(targetEntity=DayArrange.class, mappedBy="weekArrange", cascade=CascadeType.ALL)
	public Set<DayArrange> getDayArranges() {
		return dayArranges;
	}
	public void setDayArranges(Set<DayArrange> dayArranges) {
		this.dayArranges = dayArranges;
	}
	
	@ManyToOne(targetEntity=MonthArrange.class)
	@JoinColumn(name="monthArrangeId", nullable=true)
	public MonthArrange getMonthArrange() {
		return monthArrange;
	}
	public void setMonthArrange(MonthArrange monthArrange) {
		this.monthArrange = monthArrange;
	}
	
}
