package szw.depotms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//����Ա
public class Admin {
	private String adminId ;				//����ԱID
	private String password ;				//����
	
	
	@Id
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
