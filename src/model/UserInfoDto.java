package model;

public class UserInfoDto {
	private String username;
	private String password;
	private int employeeNumber = 0;

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}

	public void setPassword (String password) {this.password = password;}

	public int getEmployeeNumber() {return employeeNumber;}

	public void setEmployeeNumber (int employeeNumber) {this.employeeNumber = employeeNumber;}
}
