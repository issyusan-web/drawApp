package dto;

//DBから取得したユーザー情報を保存するDTO。
public class UserDTO {
	private int id;
	private String loginId;
	private String password;
	private String name;
	private int auth;

	public UserDTO() {
	}

	public UserDTO(String loginId, String password, String name) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
	}

	public UserDTO(int id, String loginId, String password, String name, int auth) {
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.auth = auth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

}
