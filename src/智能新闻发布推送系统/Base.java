package 智能新闻发布推送系统;

import java.sql.*;

public class Base {
	private static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL="jdbc:sqlserver://localhost:1433;databaseName=NEWSSYSTEM";
	private static final String UID="sa";
	private static final String PWD="666666";
	protected Connection conn=null;
	protected PreparedStatement pstmt=null;
	protected ResultSet rs=null;
    public Connection getConnection() throws ClassNotFoundException,SQLException{
		
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, UID, PWD);
			//System.out.println("Connection Successful!");

		return conn;
	}
    public void close(ResultSet rs,Statement stmt,Connection conn) {
    	if(rs!=null) {
    		try {
    			rs.close();
    		}
    	catch(SQLException e) {
    		e.printStackTrace();
    		}
    	}
    	
    	if(stmt!=null) {
    		try {
    			stmt.close();
    		}catch(SQLException e) {
        		e.printStackTrace();
        		}
    	}
    	
    	if(conn!=null) {
    		try {
    			conn.close();
    		}catch(SQLException e) {
        		e.printStackTrace();
        		}
    	}
    }
      
}
