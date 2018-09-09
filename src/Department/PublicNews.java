package Department;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

import 智能新闻发布推送系统.Base;
public class PublicNews extends JFrame implements ActionListener {//发布新闻界面
	JLabel idLbl,titleLbl,classifyLbl,departmentLbl,contentLbl,dateLbl;
	JTextField idField,titleField,dateField;
	JTextArea contentField;
	JComboBox classifyCombox,departmentCombox;
	JButton enterBtn,returnBtn,exitBtn;
	Base base=new Base();
	Date day=new Date();
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public PublicNews(String title) {
		setTitle(title);
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		idLbl=new JLabel("新闻编号");
		idField=new JTextField();
		titleLbl=new JLabel("新闻标题");
		titleField=new JTextField();
		classifyLbl=new JLabel("新闻类别");
		classifyCombox=new JComboBox();
		try {
			List<Classify> cList=findCName();
			int num=cList.size();
			Object[] object=new Object[num];
			int index=0;
			for(Classify classify:cList) {
				object[index]=classify.getClassifyName();
				classifyCombox.addItem(object[index]);
				index++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		departmentLbl=new JLabel("来源部门");
		departmentCombox=new JComboBox();
		try {
			List<Department> dList=findDName();
			int num=dList.size();
			Object[] object=new Object[num];
			int index=0;
			for(Department department:dList) {
				object[index]=department.getDeptName();
				departmentCombox.addItem(object[index]);
				index++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		contentLbl=new JLabel("新闻内容");
		contentField=new JTextArea();
		dateLbl=new JLabel("发布时间");
		dateField=new JTextField();
		dateField.setText(df.format(day));
		enterBtn=new JButton("确定发布");
		enterBtn.addActionListener(this);
		returnBtn=new JButton("返回功能界面");
		returnBtn.addActionListener(this);
		exitBtn=new JButton("退出");
		exitBtn.addActionListener(this);
		setLayout(null);
		idLbl.setBounds(10,10,80,30);
		idField.setBounds(90,10,400,30);
		titleLbl.setBounds(510,10,80,30);
		titleField.setBounds(590,10,400,30);
		classifyLbl.setBounds(10,50,80,30);
		classifyCombox.setBounds(90,50,400,30);
		departmentLbl.setBounds(510,50,80,30);
		departmentCombox.setBounds(590,50,400,30);
		contentLbl.setBounds(10,90,80,30);
		contentField.setBounds(10,130,980,340);
		dateLbl.setBounds(10,480,120,30);
		dateField.setBounds(130,480,400,30);
		enterBtn.setBounds(280,520,120,30);
		returnBtn.setBounds(430,520,160,30);
		exitBtn.setBounds(630,520,80,30);
		add(idLbl);
		add(idField);
		add(titleLbl);
		add(titleField);
		add(classifyLbl);
		add(classifyCombox);
		add(departmentLbl);
		add(departmentCombox);
		add(contentLbl);
		add(contentField);
		add(dateLbl);
		add(dateField);
		add(enterBtn);
		add(returnBtn);
		add(exitBtn);
		setVisible(true);
	    idField.addKeyListener(new KeyListener(){
	    	public void keyTyped(KeyEvent e) {}
		    public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode()==KeyEvent.VK_ENTER){
			    	titleField.requestFocus();
			    }
		    }
		    public void keyReleased(KeyEvent e) {}   
	    });
	    titleField.addKeyListener(new KeyListener(){
	    	public void keyTyped(KeyEvent e) {}
		    public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode()==KeyEvent.VK_ENTER){
			    	contentField.requestFocus();
			    }
		    }
		    public void keyReleased(KeyEvent e) {}   
	    });
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==enterBtn) {
			String classifyName=(String) classifyCombox.getItemAt(classifyCombox.getSelectedIndex());
	        String deptName=(String) departmentCombox.getItemAt(departmentCombox.getSelectedIndex());
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
		    	save(n);
		    	JOptionPane.showMessageDialog(null,"新闻发布成功");
		    }catch(Exception ec) {
		    	JOptionPane.showMessageDialog(null,"保存出现异常。异常原因为："+ec.getMessage());
		    	ec.printStackTrace();
		    }
		}else {
			this.dispose();
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
	public List<Classify> findCName()throws Exception{
		List<Classify> cList=new ArrayList<Classify>();
		String sql="select classifyName from Classify";
		Connection conn=base.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			String classifyName=rs.getString("classifyName");
			Classify c=new Classify(classifyName);
			cList.add(c);
		}
		base.close(rs,pstmt,conn);
		return cList;    
	}
	public List<Department> findDName()throws Exception{
		List<Department> dList=new ArrayList<Department>();
		String sql="select deptName from Department";
		Connection conn=base.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			String deptName=rs.getString("deptName");
			Department d=new Department(deptName);
			dList.add(d);
		}
		base.close(rs, pstmt, conn);
		return dList;
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
	public void save(News n)throws Exception{
		String sql="insert into News(newsID,classifyID,deptID,newTitle,newContent,newDate)values(?,?,?,?,?,?)";
		Connection conn=base.getConnection();//获得数据库连接对象
		PreparedStatement pstmt=conn.prepareStatement(sql);//创建PreparedStatement对象
		pstmt.setString(1,n.getNewsID());//为动态参数赋值
		pstmt.setString(2,n.getClassifyID());
		pstmt.setString(3,n.getDeptID());
		pstmt.setString(4,n.getNewTitle());
		pstmt.setString(5,n.getNewContent());
		pstmt.setTimestamp(6,n.getNewDate());
		pstmt.executeUpdate();//提交数据
		base.close(null,pstmt,conn);//关闭数据库连接
	}
}