package szw.depotms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//管理员
public class Admin {
	private String adminId ;				//管理员ID
	private String password ;				//密码
	
	
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
