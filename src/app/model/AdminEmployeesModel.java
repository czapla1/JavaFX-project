package app.model;

public class AdminEmployeesModel {
	private int id_employee;
	private String firstname;
	private String lastname;
	
	public AdminEmployeesModel() {
		super();
	}
	
	public AdminEmployeesModel(int id_employee, String firstname, String lastname) {
		super();
		this.id_employee = id_employee;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	
	
}
