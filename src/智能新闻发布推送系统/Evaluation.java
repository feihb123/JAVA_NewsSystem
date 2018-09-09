package 智能新闻发布推送系统;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Evaluation {
	JLabel title;
	JButton exit,publish;
	private JTable tabDemo;
	private JTableHeader jth;
	private JScrollPane scpDemo;

	public Evaluation(String a){
		JFrame jf=new JFrame("新闻评价");
		jf.setSize(1000, 800);
		jf.setLocationRelativeTo(null);
		JPanel jp=new JPanel();
		scpDemo = new JScrollPane();
		title=new JLabel("当前新闻还没有评价");
		try {
			Base b1=new Base();
			Connection conn=b1.getConnection();
			String sql = "select  top 10 *  from Evaluation ,news\r\n" + 
					"where NEWS.NEWSID=? AND Evaluation.newsID =news.newsID \r\n" + 
					"order by evaluDate "; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, a);
			ResultSet rs = pstm.executeQuery();

			Object[][] info = new Object[10][3];
			int count=0;
			while(rs.next()){
				info[count][0] = rs.getInt("userID");
				info[count][1] = rs.getString("evaluContent");
				info[count][2] = rs.getString("evaluDate");
				title=new JLabel(rs.getString("newtitle"));
				count++;
			}
			    title.setFont(new java.awt.Font("黑体",1,15));
				String[] title = {"用户ID","新闻评价","评价时间"};
				this.tabDemo = new JTable(info,title);
				this.jth = this.tabDemo.getTableHeader();
				jth.setFont(new java.awt.Font("黑体",1,15));
				this.scpDemo.getViewport().add(tabDemo);
				tabDemo.setRowHeight(50);
				tabDemo.setFont(new java.awt.Font("黑体",1,18));
				tabDemo.getColumn("用户ID").setPreferredWidth(100);
				tabDemo.getColumn("新闻评价").setPreferredWidth(400);
				tabDemo.getColumn("评价时间").setPreferredWidth(100);
				conn.close();
			}catch(ClassNotFoundException cnfe){
				JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
				}catch(SQLException sqle){
					JOptionPane.showMessageDialog(null,"当前没有评价","错误",JOptionPane.ERROR_MESSAGE);
					sqle.printStackTrace();
				}
		publish=new JButton("发布评价");
		publish.setFont(new java.awt.Font("黑体",0,16));
		publish.setContentAreaFilled(false); 
		publish.addActionListener(e->{
        	if(e.getSource()==publish) {
        		new Pubish_Evaluation(a);
        	}
        });
		exit=new JButton("返回上页");
		exit.setFont(new java.awt.Font("黑体",0,16));
		exit.setContentAreaFilled(false); 
		exit.addActionListener(e->{
        	if(e.getSource()==exit) {
        		jf.dispose();
        	}
        });
		jp.setLayout(null);
		jp.add(scpDemo);
		jp.add(title);
		jp.add(exit);
		jp.add(publish);
		exit.setBounds(600,650,200,50);
		publish.setBounds(250,650,200,50);
		scpDemo.setBounds(100,100,800,525);
		title.setBounds(400,30,400,50);
		jf.add(jp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		}
}
