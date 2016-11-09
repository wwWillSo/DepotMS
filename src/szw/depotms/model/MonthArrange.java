package szw.depotms.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
//���Ű��
public class MonthArrange {
	private int monthArrangeId ;				//�·�
	private Date date_begin ;					//��ʼ����
	private Date date_end ; 					//��������
	
	private Set<WeekArrange> weekArranges = new HashSet<WeekArrange>() ;
	@Id
	public int getMonthArrangeId() {
		return monthArrangeId;
	}
	public void setMonthArrangeId(int monthArrangeId) {
		this.monthArrangeId = monthArrangeId;
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
	
	@OneToMany(targetEntity=WeekArrange.class, mappedBy="monthArrange", cascade=CascadeType.ALL)
	public Set<WeekArrange> getWeekArranges() {
		return weekArranges;
	}
	public void setWeekArranges(Set<WeekArrange> weekArranges) {
		this.weekArranges = weekArranges;
	}
}
