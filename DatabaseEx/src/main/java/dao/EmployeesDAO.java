package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeesDAO {
	
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/DatabaseEx";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Employee> findAll(){
		List<Employee> empList = new ArrayList<>();
		
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			
		}
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID,NAME,AGE FROM EMPLOYEES";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				Employee employee = new Employee(id,name,age);
				empList.add(employee);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empList;
	}
	
	public boolean remove(String id) {
		
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "DELETE FROM EMPLOYEE WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			
			int result = pStmt.executeUpdate();
			return(result == 1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
