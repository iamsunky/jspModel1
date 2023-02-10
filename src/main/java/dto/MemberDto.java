package dto;

import java.io.Serializable;

public class MemberDto implements Serializable {

	private String id, name, pwd, email;
	private int auth; // 사용자: 3 관리자: 1

	public MemberDto() {
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String id, String name, String pwd, String email, int auth) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", name=" + name + ", pwd=" + pwd + ", email=" + email + ", auth=" + auth + "]";
	}

}