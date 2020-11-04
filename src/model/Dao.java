	package model;

	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

		String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

		String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";

		String USER_ID     = "test_user";

		String USER_PASS   = "test_pass";

		public UserInfoDto doCheckUserInfo(String inputUsername, String inputPassWord) {

			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			UserInfoDto dto = new UserInfoDto();

			try {

				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer();
				buf.append(" SELECT          	  ");
				buf.append("   EMPLOYEE_NUMBER	 ,");
				buf.append("   NAME   			  ");
				buf.append(" FROM             	  ");
				buf.append("   EMPLOYEE			  ");
				buf.append(" WHERE                ");
				buf.append("   NAME		=   ? AND ");
				buf.append("   PASSWORD = ?       ");

				ps = con.prepareStatement(buf.toString());
				ps.setString( 1, inputUsername   );
				ps.setString( 2, inputPassWord );

				rs = ps.executeQuery();

				if (rs.next()) {
					dto.setUsername(   rs.getString("NAME")   );
					dto.setEmployeeNumber(rs.getInt("EMPLOYEE_NUMBER"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return dto;
		}
		public boolean doTaskInput(TaskInputDto dto) {

			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection        con = null ;
			PreparedStatement ps  = null ;

			boolean isSuccess = true ;

			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				con.setAutoCommit(false);

				StringBuffer buf = new StringBuffer();
				buf.append("INSERT INTO SALES_RECORD (  ");
				buf.append("	EMPLOYEE_NUMBER,               ");
				buf.append("	ADDRESS,               ");
				buf.append("	VISITED_NAME,                ");
				buf.append("	INTERVIEWER,                ");
				buf.append("	RESULT, 			  ");
				buf.append("	REMARKS 			");
				buf.append(") VALUES (            ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append("  ?                   ");
				buf.append(")                     ");

				ps = con.prepareStatement(buf.toString());

				ps.setInt(       1, dto.getEmployeeNumber());
				ps.setString(    2, dto.getAddress());
				ps.setString(       3, dto.getVisitedName());
				ps.setString(       4, dto.getInterviewer());
				ps.setInt(       5, dto.getResult());
				ps.setString(       6, dto.getMemo());

				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				isSuccess = false ;
			} finally {
				if(isSuccess){
					try {
						con.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					try {
						con.rollback();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return isSuccess;
		}

		public List<TaskInputDto> executeShowAllTasks(int employeeNumber) {

			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();

			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer() ;
				buf.append(" SELECT        ");
				buf.append("   ADDRESS    ,    ");
				buf.append("   VISITED_NAME    ,    ");
				buf.append("   INTERVIEWER   ,    ");
				buf.append("   RESULT ,    ");
				buf.append("   REMARKS,       ");
				buf.append("   REGIST_TIMESTAMP       ");
				buf.append(" FROM          ");
				buf.append("   SALES_RECORD ");
				buf.append("WHERE  ");
				buf.append("EMPLOYEE_NUMBER = "+ employeeNumber);

				ps = con.prepareStatement(buf.toString());
				rs = ps.executeQuery();

				while(rs.next()) {
					TaskInputDto dto = new TaskInputDto();
					dto.setAddress(rs.getString("address"));
					dto.setVisitedName(rs.getString( "visited_name"));
					dto.setInterviewer(rs.getString("interviewer"));
					dto.setResult(rs.getInt("result"));
					dto.setMemo(rs.getString("remarks"));
					dto.setRegistedAt(rs.getTimestamp("regist_timestamp"));
					dtoList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return dtoList;
		}

		public List<TaskInputDto> executeShowTodayTasks(int employeeNumber) {
			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();
			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer() ;
				buf.append(" SELECT        ");
				buf.append("   ADDRESS    ,    ");
				buf.append("   VISITED_NAME    ,    ");
				buf.append("   INTERVIEWER   ,    ");
				buf.append("   RESULT ,    ");
				buf.append("   REMARKS,       ");
				buf.append("   REGIST_TIMESTAMP       ");
				buf.append(" FROM          ");
				buf.append("   SALES_RECORD ");
				buf.append("WHERE  ");
				buf.append("EMPLOYEE_NUMBER = "+ employeeNumber);
				buf.append(" AND ");
				buf.append("REGIST_TIMESTAMP > DATE_SUB(NOW(), INTERVAL 1 DAY);");

				ps = con.prepareStatement(buf.toString());
				rs = ps.executeQuery();

				while(rs.next()) {
					TaskInputDto dto = new TaskInputDto();
					dto.setAddress(rs.getString("address"));
					dto.setVisitedName(rs.getString( "visited_name"));
					dto.setInterviewer(rs.getString("interviewer"));
					dto.setResult(rs.getInt("result"));
					dto.setMemo(rs.getString("remarks"));
					dto.setRegistedAt(rs.getTimestamp("regist_timestamp"));
					dtoList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return dtoList;
		}

		public List<TaskInputDto> executeShowWeekTasks(int employeeNumber) {

			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();

			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer() ;
				buf.append(" SELECT        ");
				buf.append("   ADDRESS    ,    ");
				buf.append("   VISITED_NAME    ,    ");
				buf.append("   INTERVIEWER   ,    ");
				buf.append("   RESULT ,    ");
				buf.append("   REMARKS,       ");
				buf.append("   REGIST_TIMESTAMP       ");
				buf.append(" FROM          ");
				buf.append("   SALES_RECORD ");
				buf.append("WHERE  ");
				buf.append("EMPLOYEE_NUMBER = "+ employeeNumber);
				buf.append(" AND ");
				buf.append("REGIST_TIMESTAMP > DATE_SUB(NOW(), INTERVAL 1 WEEK);");

				ps = con.prepareStatement(buf.toString());
				rs = ps.executeQuery();

				while(rs.next()) {
					TaskInputDto dto = new TaskInputDto();
					dto.setAddress(rs.getString("address"));
					dto.setVisitedName(rs.getString( "visited_name"));
					dto.setInterviewer(rs.getString("interviewer"));
					dto.setResult(rs.getInt("result"));
					dto.setMemo(rs.getString("remarks"));
					dto.setRegistedAt(rs.getTimestamp("regist_timestamp"));
					dtoList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return dtoList;
		}

		public List<TaskInputDto> executeShowMonthTasks(int employeeNumber) {
			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();

			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer() ;
				buf.append(" SELECT        ");
				buf.append("   ADDRESS    ,    ");
				buf.append("   VISITED_NAME    ,    ");
				buf.append("   INTERVIEWER   ,    ");
				buf.append("   RESULT ,    ");
				buf.append("   REMARKS,       ");
				buf.append("   REGIST_TIMESTAMP       ");
				buf.append(" FROM          ");
				buf.append("   SALES_RECORD ");
				buf.append("WHERE  ");
				buf.append("EMPLOYEE_NUMBER = "+ employeeNumber);
				buf.append(" AND ");
				buf.append("REGIST_TIMESTAMP > DATE_SUB(NOW(), INTERVAL 1 MONTH);");

				ps = con.prepareStatement(buf.toString());
				rs = ps.executeQuery();

				while(rs.next()) {
					TaskInputDto dto = new TaskInputDto();
					dto.setAddress(rs.getString("address"));
					dto.setVisitedName(rs.getString( "visited_name"));
					dto.setInterviewer(rs.getString("interviewer"));
					dto.setResult(rs.getInt("result"));
					dto.setMemo(rs.getString("remarks"));
					dto.setRegistedAt(rs.getTimestamp("regist_timestamp"));
					dtoList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return dtoList;
		}

		public List<TaskInputDto> executeSearchName(int employeeNumber, String searchName) {
			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();

			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer() ;
				buf.append(" SELECT        ");
				buf.append("   ADDRESS    ,    ");
				buf.append("   VISITED_NAME    ,    ");
				buf.append("   INTERVIEWER   ,    ");
				buf.append("   RESULT ,    ");
				buf.append("   REMARKS,       ");
				buf.append("   REGIST_TIMESTAMP       ");
				buf.append(" FROM          ");
				buf.append("   SALES_RECORD ");
				buf.append("WHERE  ");
				buf.append("EMPLOYEE_NUMBER = '"+ employeeNumber+ "' ");
				buf.append(" AND ");
				buf.append("VISITED_NAME LIKE '%"+ searchName +"%' ;");

				ps = con.prepareStatement(buf.toString());
				rs = ps.executeQuery();

				while(rs.next()) {
					TaskInputDto dto = new TaskInputDto();
					dto.setAddress(rs.getString("address"));
					dto.setVisitedName(rs.getString( "visited_name"));
					dto.setInterviewer(rs.getString("interviewer"));
					dto.setResult(rs.getInt("result"));
					dto.setMemo(rs.getString("remarks"));
					dto.setRegistedAt(rs.getTimestamp("regist_timestamp"));
					dtoList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return dtoList;
		}

		public List<TaskInputDto> executeSearchAddress(int employeeNumber, String searchAddress) {

			try {
				Class.forName(DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection        con = null ;
			PreparedStatement ps  = null ;
			ResultSet         rs  = null ;

			List<TaskInputDto> dtoList = new ArrayList<TaskInputDto>();

			try {
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer() ;
				buf.append(" SELECT        ");
				buf.append("   ADDRESS    ,    ");
				buf.append("   VISITED_NAME    ,    ");
				buf.append("   INTERVIEWER   ,    ");
				buf.append("   RESULT ,    ");
				buf.append("   REMARKS,       ");
				buf.append("   REGIST_TIMESTAMP       ");
				buf.append(" FROM          ");
				buf.append("   SALES_RECORD ");
				buf.append("WHERE  ");
				buf.append("EMPLOYEE_NUMBER = '"+ employeeNumber+ "' ");
				buf.append(" AND ");
				buf.append("ADDRESS LIKE '%"+ searchAddress +"%' ;");

				ps = con.prepareStatement(buf.toString());
				rs = ps.executeQuery();

				while(rs.next()) {
					TaskInputDto dto = new TaskInputDto();
					dto.setAddress(rs.getString("address"));
					dto.setVisitedName(rs.getString( "visited_name"));
					dto.setInterviewer(rs.getString("interviewer"));
					dto.setResult(rs.getInt("result"));
					dto.setMemo(rs.getString("remarks"));
					dto.setRegistedAt(rs.getTimestamp("regist_timestamp"));
					dtoList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return dtoList;
		}
}
