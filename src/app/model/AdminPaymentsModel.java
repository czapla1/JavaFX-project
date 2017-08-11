package app.model;

public class AdminPaymentsModel {

	private int id_payment;
	private int id_employee;
	private int month_name;
	private double total_brutto;
	
	public AdminPaymentsModel() {
		super();
	}
	
	public AdminPaymentsModel(int id_employee, int month_name, double total_brutto) {
		super();
		this.id_employee = id_employee;
		this.month_name = month_name;
		this.total_brutto = total_brutto;
	}
	
	public AdminPaymentsModel(int id_payment, int id_employee, int month_name, double total_brutto) {
		super();
		this.id_payment = id_payment;
		this.id_employee = id_employee;
		this.month_name = month_name;
		this.total_brutto = total_brutto;
	}


	public int getId_payment() {
		return id_payment;
	}
	public void setId_payment(int id_payment) {
		this.id_payment = id_payment;
	}
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public int getMonth_name() {
		return month_name;
	}
	public void setMonth_name(int month_name) {
		this.month_name = month_name;
	}
	public double getTotal_brutto() {
		return total_brutto;
	}
	public void setTotal_brutto(double total_brutto) {
		this.total_brutto = total_brutto;
	}
	
	
	
	
}
