package tk.sweetvvck.domain;

public class SystemAdmin implements java.io.Serializable{
	private static final long serialVersionUID = 5037740044259003950L;
	Integer id;
	String username = null;
	String password = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
