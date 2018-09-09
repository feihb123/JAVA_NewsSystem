package Department;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import 智能新闻发布推送系统.Base;
public class EditNews extends JFrame implements ActionListener {//编辑新闻界面
	JLabel idLbl,titleLbl,classifyLbl,departmentLbl,contentLbl,dateLbl;
	JTextField idField,titleField,dateField,classifyField,departmentField;
	JTextArea contentField;
	JButton modifyBtn,enterBtn,returnBtn,exitBtn;
	Base base=new Base();
	Date day=new Date();
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public EditNews(String title) {
		setTitle(title);
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		idLbl=new JLabel("新闻编号");
		idField=new JTextField();
		titleLbl=new JLabel("新闻标题");
		titleField=new JTextField();
		titleField.setEditable(false);
		classifyLbl=new JLabel("新闻类别");
		classifyField=new JTextField();
		classifyField.setEditable(false);
		departmentLbl=new JLabel("来源部门");
		departmentField=new JTextField();
		departmentField.setEditable(false);
		contentLbl=new JLabel("新闻内容");
		contentField=new JTextArea();
		JScrollPane sco=new JScrollPane(contentField);
		contentField.setEditable(false);
		dateLbl=new JLabel("新闻发布日期");
		modifyBtn=new JButton("修改新闻");
		modifyBtn.addActionListener(this);
		enterBtn=new JButton("确定修改");
		enterBtn.addActionListener(this);
		returnBtn=new JButton("返回功能界面");
		returnBtn.addActionListener(this);
		exitBtn=new JButton("退出");
		exitBtn.addActionListener(this);
		dateField=new JTextField();
		dateField.setText(df.format(day));
		setLayout(null);
		idLbl.setBounds(10,10,80,30);
		idField.setBounds(90,10,400,30);
		titleLbl.setBounds(510,10,80,30);
		titleField.setBounds(590,10,400,30);
		classifyLbl.setBounds(10,50,80,30);
		classifyField.setBounds(90,50,400,30);
		departmentLbl.setBounds(510,50,80,30);
		departmentField.setBounds(590,50,400,30);
		contentLbl.setBounds(10,90,80,30);
		sco.setBounds(10,130,980,340);
		dateLbl.setBounds(10,480,120,30);
		dateField.setBounds(130,480,400,30);
		modifyBtn.setBounds(160,520,120,30);
		enterBtn.setBounds(310,520,120,30);
		returnBtn.setBounds(460,520,160,30);
		exitBtn.setBounds(650,520,80,30);
		add(idLbl);
		add(idField);
		add(titleLbl);
		add(titleField);
		add(classifyLbl);
		add(classifyField);
		add(departmentLbl);
		add(departmentField);
		add(contentLbl);
		add(sco);
		add(dateLbl);
		add(dateField);
		add(modifyBtn);
		add(enterBtn);
		add(returnBtn);
		add(exitBtn);
		setVisible(true);
	    idField.addKeyListener(new KeyListener(){
	    	public void keyTyped(KeyEvent e) {}
		    public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode()==KeyEvent.VK_ENTER){
			    	String newsID=idField.getText();
					String classifyID = null;
					String deptID = null;
					String newTitle = null;
					String newContent = null;
					String classifyName = null;
					String deptName = null;
					Classify c=new Classify(classifyName); 
					Department d=new Department(deptName);
					News n=new News(newsID,classifyID,deptID,newTitle,newContent);
			    	try {
			    		find(c,d,n);
			    		classifyField.setText(c.getClassifyName());
			    		departmentField.setText(d.getDeptName());
			    		titleField.setText(n.getNewTitle());
			    		contentField.setText(n.getNewContent().trim());
			    		contentField.setFont(new java.awt.Font("黑体",1,18));
			    		contentField.setLineWrap(true);//换行
			    	}catch(Exception ec) {
			    		JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为"+ec.getMessage());
			    		ec.printStackTrace();
			    	}
			    }
		    }
		    public void keyReleased(KeyEvent e) {}   
	    });
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==modifyBtn) {
			titleField.setEditable(true);
			classifyField.setEditable(true);
			departmentField.setEditable(true);
			contentField.setEditable(true);
		}
		if(e.getSource()==enterBtn) {
			String classifyName=classifyField.getText();
	        String deptName=departmentField.getText();
			Classify c=new Classify(classifyName); 
			Department d=new Department(deptName);
			try {
				findID(c,d);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String classifyID=c.getClassifyID();
	    	String deptID=d.getDeptID();
			String newsID=idField.getText();
			String newTitle=titleField.getText();
			String newContent=contentField.getText();
			Timestamp newDate=new java.sql.Timestamp(day.getTime());			
			News n=new News(newsID,classifyID,deptID,newTitle,newContent,newDate);
			try {
				edit(n);
				JOptionPane.showMessageDialog(null,"成功修改新闻！");
			}catch(Exception ec) {
	    		JOptionPane.showMessageDialog(null,"修改时出现异常。异常原因为"+ec.getMessage());
	    		ec.printStackTrace();
	    	}
		}
		if(e.getSource()==returnBtn) {
			this.dispose();
			new DepartmentEntrance("部门用户功能界面");
		}
    	if(e.getSource()==exitBtn) {
    		System.exit(1);
		}
	}
	//连接数据库实现功能
	public void find(Classify c,Department d,News n)throws Exception{
		String sql="select classifyName,deptName,newTitle,newContent from News,Classify,Department "
				+ "where Classify.classifyID=News.classifyID and Department.deptID=News.deptID and newsID=?";
		Connection conn=base.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,n.getNewsID());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			c.setClassifyName(rs.getString("classifyName"));
			d.setDeptName(rs.getString("deptName"));
			n.setNewTitle(rs.getString("newTitle"));
			n.setNewContent(rs.getString("newContent"));
		}
		base.close(rs,pstmt,conn);
	}
	public void findID(Classify c,Department d)throws Exception{
		String sql="select classifyID,deptID from Classify,Department where classifyName=? and deptName=?";
		Connection conn=base.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,c.getClassifyName());
		pstmt.setString(2,d.getDeptName());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			c.setClassifyID(rs.getString("classifyID"));
			d.setDeptID(rs.getString("deptID"));
		}
	}
	public void edit(News n)throws Exception{
		String sql="update News set newTitle=?,classifyID=?,deptID=?,newContent=?,newDate=? where newsID=?";
		Connection conn=base.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,n.getNewTitle());
		pstmt.setString(2,n.getClassifyID());
		pstmt.setString(3,n.getDeptID());
		pstmt.setString(4,n.getNewContent());
		pstmt.setTimestamp(5,n.getNewDate());
		pstmt.setString(6,n.getNewsID());
		pstmt.executeUpdate();
		base.close(null,pstmt,conn);
	}
}
