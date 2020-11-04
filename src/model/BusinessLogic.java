package model;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
	boolean dbConnection = false;

	public UserInfoDto checkUserInfo(String username, String password) {

		UserInfoDto userDto = new UserInfoDto();
		Dao dao = new Dao();
		userDto = dao.doCheckUserInfo(username,password);

		return userDto;
	}

	public boolean doTaskInput(TaskInputDto taskDto) {
		TaskInputDto inputDto = new TaskInputDto();
		inputDto = taskDto;
		Dao dao = new Dao();
		boolean successInput = dao.doTaskInput(inputDto);
		return successInput;
	}

	public List<TaskInputDto> doShowAllTasks(int employeeNumber){
		List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
		Dao dao = new Dao();
		dtoList = dao.executeShowAllTasks(employeeNumber);
		return dtoList;
	}

	public List<TaskInputDto> doShowTodayTasks(int employeeNumber){
		List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
		Dao dao = new Dao();
		dtoList = dao.executeShowTodayTasks(employeeNumber);
		return dtoList;
	}

	public List<TaskInputDto> doShowWeekTasks(int employeeNumber){
		List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
		Dao dao = new Dao();
		dtoList = dao.executeShowWeekTasks(employeeNumber);
		return dtoList;
	}

	public List<TaskInputDto> doShowMonthTasks(int employeeNumber){
		List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
		Dao dao = new Dao();
		dtoList = dao.executeShowMonthTasks(employeeNumber);
		return dtoList;
	}
	
	public List<TaskInputDto> doSearchName(int employeeNumber, String searchName){
		List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
		Dao dao = new Dao();
		dtoList = dao.executeSearchName(employeeNumber,searchName);
		return dtoList;
	}
	
	public List<TaskInputDto> doSearchAddress(int employeeNumber, String searchAddress){
		List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
		Dao dao = new Dao();
		dtoList = dao.executeSearchAddress(employeeNumber,searchAddress);
		return dtoList;
	}

}
