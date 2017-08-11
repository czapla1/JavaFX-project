package app.model;

public class EmployeeModel {

	
	int month;
	double total;
	
	
	
	public EmployeeModel() {
		super();
	}
	
	
	public EmployeeModel(int month, double total) {
		super();
		this.month = month;
		this.total = total;
	}


	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
