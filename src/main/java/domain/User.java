package domain;

//userDTOの値を保存するドメイン。
public class User {
	private int id;
	private String loginId;
	private String password;
	private String name;
	private int auth;

	public User() {
	}

	public User(String loginId, String password, String name) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
	}

	public User(int id, String loginId, String password, String name, int auth) {
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
