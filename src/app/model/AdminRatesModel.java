package app.model;

public class AdminRatesModel {
	
	private int id_rate;
	private int id_employee;
	private double rateX;
	private double rateY;
	private double rateZ;
	
	public AdminRatesModel() {
		super();
	}
	
	public AdminRatesModel(int id_employee, double rateX, double rateY, double rateZ) {
		super();
		this.id_employee = id_employee;
		this.rateX = rateX;
		this.rateY = rateY;
		this.rateZ = rateZ;
	}
	
	public AdminRatesModel(int id_rate, int id_employee, double rateX, double rateY, double rateZ) {
		super();
		this.id_rate = id_rate;
		this.id_employee = id_employee;
		this.rateX = rateX;
		this.rateY = rateY;
		this.rateZ = rateZ;
	}

	public int getId_rate() {
		return id_rate;
	}
	public void setId_rate(int id_rate) {
		this.id_rate = id_rate;
	}
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public double getRateX() {
		return rateX;
	}
	public void setRateX(double rateX) {
		this.rateX = rateX;
	}
	public double getRateY() {
		return rateY;
	}
	public void setRateY(double rateY) {
		this.rateY = rateY;
	}
	public double getRateZ() {
		return rateZ;
	}
	public void setRateZ(double rateZ) {
		this.rateZ = rateZ;
	}
	

}
