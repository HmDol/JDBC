package emp;

import java.sql.Date;

public class Emp {
	private int id;
	private String name;
	private int departmentId;
	private Date  hireDate;
	private double salary;
	
	public Emp() {
		
	}
	
	public Emp(int id, String name, int departmentId, Date hireDate, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.departmentId = departmentId;
		this.hireDate = hireDate;
		this.salary = salary;
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	
}
