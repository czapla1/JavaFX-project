package app.model;

public class LoginModel {
	private int id;
	private String login;
	private String pass;
	private String access;

	public LoginModel() {
		super();
	}

	public LoginModel(int id, String login, String pass, String access) {
		super();
		this.id = id;
		this.login = login;
		this.pass = pass;
		this.access = access;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

}
