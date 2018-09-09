package 智能新闻发布推送系统;

import java.sql.*;


public class LgoinCheck {
	Base b1=new Base();
	public boolean loginCheck(String name,String PWD,String role) throws ClassNotFoundException, SQLException {
		String sql=" ";
		if(role.equals("系统管理员")) {
			sql="select * from Admin where adminName=? and adminPW=?";
		}
			else if(role.equals("部门管理员")) {
				sql="select * from DeptUser where DuserName=? and DuserPW=?";
			}else if(role.equals("系统用户")) {
				sql="select * from Users where userName=? and userPW=?";	
		}
		return query(sql,name,PWD);
	}
	private boolean query(String sql, String name, String PWD) throws ClassNotFoundException, SQLException {
		boolean pwdCheck=false;
		Connection conn=b1.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,name);
		pstmt.setString(2,PWD);
		
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())  pwdCheck=true;
		b1.close(rs, pstmt, conn);
		conn.close();
		return pwdCheck;
	}
	
}


