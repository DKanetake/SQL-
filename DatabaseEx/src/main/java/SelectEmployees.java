import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmployees {
	public static void main(String[] args) {
	
	try {
		Class.forName("org.h2.Driver");
	}catch(ClassNotFoundException e) {
		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
	

	try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/DatabaseEx","sa","")){
		String sql = "SELECT ID,NAME,AGE FROM EMPLOYEES";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			int age = rs.getInt("AGE");
			
			System.out.println("ID:" + id);
			System.out.println("名前:" + name);
			System.out.println("年齢:" + age + "\n");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		}
	
	
	}

}
