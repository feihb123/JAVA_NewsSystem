package Department;
import java.awt.event.*;
import javax.swing.*;

import 智能新闻发布推送系统.Login;
import 智能新闻发布推送系统.MainWindows;
public class DepartmentEntrance extends JFrame implements ActionListener {//部门用户功能界面
	JButton see_newsBtn,see_newsBtn1;
	JButton public_newsBtn,public_newsBtn1;
	JButton edit_newsBtn,edit_newsBtn1;
	JButton exitBtn,exitBtn1;

	public DepartmentEntrance(String title) {

		setTitle(title);
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		JToolBar jt=new JToolBar("部门用户功能");
		see_newsBtn=new JButton();
		see_newsBtn.setIcon(new ImageIcon(getClass().getResource("/img/see_news.png")));
		see_newsBtn.setToolTipText("查看新闻");
		see_newsBtn.addActionListener(this);
		public_newsBtn=new JButton();
		public_newsBtn.setIcon(new ImageIcon(getClass().getResource("/img/public_news.png")));
		public_newsBtn.setToolTipText("发布新闻");
		public_newsBtn.addActionListener(this);
		edit_newsBtn=new JButton();
		edit_newsBtn.setIcon(new ImageIcon(getClass().getResource("/img/edit_news.png")));
		edit_newsBtn.setToolTipText("编辑新闻");
		edit_newsBtn.addActionListener(this);
		exitBtn=new JButton();
		exitBtn.setIcon(new ImageIcon(getClass().getResource("/img/exit.png")));
		exitBtn.setToolTipText("返回登录界面");
		exitBtn.addActionListener(this);
		see_newsBtn1=new JButton();
		see_newsBtn1.setText("查看新闻");
		see_newsBtn1.addActionListener(this);
		public_newsBtn1=new JButton();
		public_newsBtn1.setText("发布新闻");
		public_newsBtn1.addActionListener(this);
		edit_newsBtn1=new JButton();
		edit_newsBtn1.setText("编辑新闻");
		edit_newsBtn1.addActionListener(this);
		exitBtn1=new JButton();
		exitBtn1.setText("返回登录界面");
		exitBtn1.addActionListener(this);
		jt.setLayout(null);
		see_newsBtn.setBounds(20,155,230,230);
		see_newsBtn1.setBounds(20,400,230,30);
		public_newsBtn.setBounds(260,155,230,230);
		public_newsBtn1.setBounds(260,400,230,30);
		edit_newsBtn.setBounds(500,155,230,230);
		edit_newsBtn1.setBounds(500,400,230,30);
		exitBtn.setBounds(740,155,230,230);
		exitBtn1.setBounds(740,400,230,30);
		jt.add(see_newsBtn);
		jt.addSeparator();
		jt.add(public_newsBtn);
		jt.addSeparator();
		jt.add(edit_newsBtn);
		jt.addSeparator();
		jt.add(exitBtn);
		jt.add(see_newsBtn1);
		jt.add(public_newsBtn1);
		jt.add(edit_newsBtn1);
		jt.add(exitBtn1);
		setLayout(null);
		jt.setBounds(10,10,980,540);
		add(jt);
		setVisible(true);
	}
	public static void main(String[] args) {
		new DepartmentEntrance("部门用户功能界面");
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==see_newsBtn||e.getSource()==see_newsBtn1) {
		    new MainWindows();
		}
		if(e.getSource()==public_newsBtn||e.getSource()==public_newsBtn1) {
			this.dispose();
		    new PublicNews("发布新闻界面");
		}
		if(e.getSource()==edit_newsBtn||e.getSource()==edit_newsBtn1) {
			this.dispose();
			new EditNews("编辑新闻界面");
		}
		if(e.getSource()==exitBtn||e.getSource()==exitBtn1) {
			this.dispose();
		    new Login();
		}
	}
}