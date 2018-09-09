package 智能新闻发布推送系统;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Department.DepartmentEntrance;


public class MainWindows implements ActionListener{
	Base b1=new Base();
	JFrame jf=new JFrame("智能新闻发布推送系统");
	JPanel jp=new JPanel();
	JButton c1,c2,c3,c4,c5,c6,c7;
	JButton /*j1,j2,*/j3,j4;//部门入口和管理员入口去除
	JButton describe;
	JLabel jl;
	JTextField jt1;
	private JTable tabDemo;
	private JTableHeader jth;
	private JScrollPane scpDemo;
	
	public MainWindows() {
		jf.setSize(1200,800);
		jf.setResizable(false);
		jp.setLayout(null);//JP!
		jf.setLocationRelativeTo(null);
		describe=new JButton("智能新闻发布推送系统V1.0");
		describe.setFont(new java.awt.Font("黑体",1,15));
		describe.setContentAreaFilled(false); 
		/*j1=new JButton("部门入口");
		j1.setFont(new java.awt.Font("黑体",1,15));
		j1.setContentAreaFilled(false); 
		j1.addActionListener(e->{
			if(e.getSource()==j1) {
				jf.dispose();
				new DepartmentEntrance("部门用户功能界面");
			}
		});
		j2=new JButton("管理员入口");
		j2.setFont(new java.awt.Font("黑体",1,15));
		j2.setContentAreaFilled(false); */
		describe.addActionListener(e->{
			if(e.getSource()==describe) {
				new Introduction();
			}
		});
		j3=new JButton("查看新闻");
		j3.setFont(new java.awt.Font("黑体",1,15));
		j3.setContentAreaFilled(false); 
		j3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==j3) {
					
					String a=jt1.getText();
					new News_View(a);

				}
				
			}
			
		});
		j4=new JButton("返回登录界面");
		j4.setFont(new java.awt.Font("黑体",1,15));
		j4.setContentAreaFilled(false); 
		j4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==j4) {
					jf.dispose();
					new Login();
				}
			}
			
		});
		
		setButton(c7,"热门推荐",100);
		setButton(c1,"国内",200);
		setButton(c2,"国际",300);
		setButton(c3,"军事",400);
		setButton(c4,"财经",500);
		setButton(c5,"娱乐",600);
		setButton(c6,"体育",700);
		
		
		
		jl=new JLabel("请输入要查看的新闻ID:");
		jl.setFont(new java.awt.Font("黑体",1,18));
		jl.setBounds(940,200,300,100);
		jt1=new JTextField();
		jt1.setHorizontalAlignment(JTextField.CENTER);
		jt1.setFont(new java.awt.Font("黑体",1,30));
		jt1.setBounds(940,300,200,100);
		
		//jp.add(j1);
		//jp.add(j2);
		jp.add(describe);
		jp.add(j3);
		jp.add(j4);
		jp.add(jl);
		jp.add(jt1);
		
		
		//j1.setBounds(920,50,100,50);
		//j2.setBounds(1050,50,120,50);
		describe.setBounds(900,50,250,60);
		j3.setBounds(940,440,200,50);
		j4.setBounds(990,630,150,50);
		scpDemo = new JScrollPane();
		scpDemo.setBounds(100,150,800,525);
		jp.add(scpDemo);
		
		newsShow();
	
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
	public void newsShow() {
		try {

			Connection conn=b1.getConnection();
			String sql = "select * from news"; 
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			showClassfy(rs);
				
			}catch(ClassNotFoundException cnfe){
				JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
				}catch(SQLException sqle){
					JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
					sqle.printStackTrace();
				}
			
		}	
	public void setButton(JButton j,String s,int x) {
		j=new JButton(s);
		j.setFont(new java.awt.Font("黑体",1,15));
		j.setContentAreaFilled(false); 
		j.setBorder(BorderFactory.createRaisedBevelBorder()); 
		jp.add(j);
		j.setBounds(x, 60, 80, 50);
		j.addActionListener(this);
	}
	public void showClassfy(ResultSet rs) {
		try {
			Object[][] info = new Object[200][4];
			int count=0;
			while(rs.next()){
				info[count][0] = rs.getString("newsID");
				info[count][1] = rs.getString("newTitle");
				info[count][2] = rs.getString("newDate");
				info[count][3] = rs.getInt("newVisits");
				count++;
				
			}
			String[] title = {"新闻ID","新闻标题","新闻日期","点击量"};
			this.tabDemo = new JTable(info,title);
			this.jth = this.tabDemo.getTableHeader();
			jth.setFont(new java.awt.Font("黑体",1,15));
			this.scpDemo.getViewport().add(tabDemo);
			tabDemo.setRowHeight(50);
			tabDemo.setFont(new java.awt.Font("黑体",1,18));
			tabDemo.getColumn("新闻ID").setPreferredWidth(100);
			tabDemo.getColumn("新闻标题").setPreferredWidth(400);
			tabDemo.getColumn("新闻日期").setPreferredWidth(120);
			tabDemo.getColumn("点击量").setPreferredWidth(100);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {//根据按钮功能分类新闻
		Connection conn;
		try {
			conn = b1.getConnection();
			if(e.getActionCommand()=="热门推荐") {
				String sql = "select * from News ORDER BY newDate DESC,newWeight DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}
			if(e.getActionCommand()=="国内") {
				String sql = "select  * from News Where classifyID=1 ORDER BY newDate DESC, newVisits DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}
			if(e.getActionCommand()=="国际") {
				String sql = "select  * from News Where classifyID=2 ORDER BY newDate DESC,newVisits DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}

			if(e.getActionCommand()=="军事") {
				String sql = "select  * from News Where classifyID=3 ORDER BY newDate DESC,newVisits DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}

			if(e.getActionCommand()=="财经") {
				String sql = "select * from News Where classifyID=4 ORDER BY newDate DESC,newVisits DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}

			if(e.getActionCommand()=="娱乐") {
				String sql = "select * from News Where classifyID=5 ORDER BY newDate DESC,newVisits DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}

			if(e.getActionCommand()=="体育") {
				String sql = "select * from News Where classifyID=6 ORDER BY newDate DESC,newVisits DESC";
				PreparedStatement pstm=conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				showClassfy(rs);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		
	}
	


}
