package model;

import java.sql.Timestamp;

public class TaskInputDto {
	private int employeeNumber;
	private String address;
	private String visitedName;
	private String interviewer;
	private int result;
	private String memo;
	private Timestamp registedAt;

	public int getEmployeeNumber() {return employeeNumber;}
	public void setEmployeeNumber(int employeeNumber) {this.employeeNumber = employeeNumber;}

	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	public String getVisitedName() {return visitedName;}
	public void setVisitedName(String visitedName) {this.visitedName = visitedName;}

	public String getInterviewer() {return interviewer;}
	public void setInterviewer(String interviewer) {this.interviewer = interviewer;}

	public int getResult() {return result;}
	public void setResult(int result) {this.result = result;}

	public String getMemo() {return memo;}
	public void setMemo(String memo) {this.memo = memo;}

	public Timestamp getRegistedAt() {return registedAt;}
	public void setRegistedAt(Timestamp registedAt) {this.registedAt = registedAt;}
}