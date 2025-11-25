package domain;

//メンバー情報を格納するdomainクラス
public class Member {
	private int id;
	private String name;
	private int userId;

	public Member() {
	}

	//INSERT用コンストラクタ
	public Member(String name, int userId) {
		this.name = name;
		this.userId = userId;
	}
	
	//SELECT用コンストラクタ
    public Member(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
