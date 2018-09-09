package 智能新闻发布推送系统;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Pubish_Evaluation {
	JFrame jf=new JFrame("评论");
	JPanel jp=new JPanel();
	JTextArea eva;
	JButton publish;
	JTextField time,user;
	public Pubish_Evaluation(String a) {
		jf.setSize(500, 400);
		jf.setLocationRelativeTo(null);
		jp.setLayout(null);
		eva=new JTextArea();
		eva.setFont(new java.awt.Font("黑体",1,18));
		Date day=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//不能hh
		time=new JTextField(sdf.format(day));
		time.setEnabled(false);
		time.setFont(new java.awt.Font("黑体",1,15));
		user=new JTextField(20);
		user.setFont(new java.awt.Font("黑体",1,18));
		publish=new JButton("发表评论");
		publish.setFont(new java.awt.Font("黑体",1,18));
		publish.setContentAreaFilled(false); 
		publish.addActionListener(e->{
			Base b=new Base();
			try {
				Connection conn=b.getConnection();
				String sql = "select * from users where userID=?"; 
				PreparedStatement pstm = conn.prepareStatement(sql);
				pstm.setString(1, user.getText());
				ResultSet rs = pstm.executeQuery();
				if(rs.next()) {
					String s = "insert into Evaluation values(?,?,?,?)";
					PreparedStatement p = conn.prepareStatement(s);
					p.setString(1,user.getText());
					p.setString(2,a);
					p.setString(3,eva.getText());
					p.setString(4,time.getText());
					p.execute();
					conn.close();
					JOptionPane.showMessageDialog(null, "发布成功");
					jf.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "您的ID不存在!");
				}
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "评价失败!");
			}
			
		});
		
		jp.add(eva);
		jp.add(publish);
		jf.add(jp);
		jp.add(time);
		jp.add(user);
		eva.setBounds(20,20,450,250);
		publish.setBounds(270,280,200,50);
		time.setBounds(20,280,180,50);
		user.setBounds(205,280,60,50);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		
	}
}
