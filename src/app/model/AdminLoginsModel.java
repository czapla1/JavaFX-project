package app.model;

public class AdminLoginsModel {
	private int id_login;
	private int id_employee;
	private String login;
	private String pass;
	private String access;
	
	public AdminLoginsModel() {
		super();
	}
	public AdminLoginsModel(int id_employee, String login, String pass, String access) {
		super();
		this.id_employee = id_employee;
		this.login = login;
		this.pass = pass;
		this.access = access;
	}
	
	public AdminLoginsModel(int id_login, int id_employee, String login, String pass, String access) {
		super();
		this.id_login=id_login;
		this.id_employee = id_employee;
		this.login = login;
		this.pass = pass;
		this.access = access;
	}


	public int getId_login() {
		return id_login;
	}


	public void setId_login(int id_login) {
		this.id_login = id_login;
	}


	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
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
