package 智能新闻发布推送系统;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import Department.DepartmentEntrance;

public class Login implements ActionListener{
	JTextField t1;
	ImageIcon img;
	JLabel logImg;
	JTextField x;
	JPasswordField p1;
	JFrame jf=new JFrame("智能新闻发布推送系统——登陆");
	JPanel jp=new JPanel();
	JButton j1;
	JComboBox c;
	public Login(){
		jf.setSize(450,660);
		jf.setLocationRelativeTo(null);
		jp.setLayout(null);
		img = new ImageIcon(getClass().getResource("/img/neutral.png"));
		logImg=new JLabel(img);
	    
	    JLabel l1=new JLabel("登录名");
	    l1.setFont(new  Font("Dialog",   1, 18));
	    JLabel l2=new JLabel("密   码");
	    l2.setFont(new  Font("Dialog",   1, 18));
	    JLabel l3=new JLabel("智能新闻发布推送系统");
	    l3.setFont(new  Font("Dialog",   1, 25));  
	    JLabel l4=new JLabel("请选择身份");
	    l4.setFont(new  Font("Dialog",   1, 18));
	    t1=new JTextField();
	    t1.setFont(new  Font("Dialog",   1, 25));
	    t1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				jp.remove(logImg);
				img = new ImageIcon(getClass().getResource("/img/active.png"));
				logImg=new JLabel(img);
				jp.add(logImg);
				logImg.setBounds(125,20,200,200);
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}
	    	
	    });;
	    p1=new JPasswordField();
	    p1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				jp.remove(logImg);
				img = new ImageIcon(getClass().getResource("/img/shy.png"));
				logImg=new JLabel(img);
				jp.add(logImg);
				logImg.setBounds(125,20,200,200);
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String name=t1.getText();
					String pwd=new String(p1.getPassword());
					String role=(String)c.getSelectedItem();
					if(name==null||"".equals(name.trim())||pwd==null||"".equals(pwd.trim())) {
						JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
						return;
					}
					
					LgoinCheck lc=new LgoinCheck();
					try {
						boolean pwdCheck=lc.loginCheck(name, pwd, role);
						if(role=="系统用户"&&pwdCheck==true) {
							JOptionPane.showMessageDialog(null, "登陆成功!欢迎进入智能新闻发布系统!");
							jf.dispose();
							new MainWindows();
						}
						if(role=="系统管理员"&&pwdCheck==true) {
							JOptionPane.showMessageDialog(null, "登陆成功!欢迎进入新闻系统管理界面!");
							jf.dispose();
							//new AMDIN();
						}
						if(role=="部门管理员"&&pwdCheck==true) {
							JOptionPane.showMessageDialog(null, "登陆成功!欢迎进入新闻部门管理界面!");
							jf.dispose();
							new DepartmentEntrance("部门管理员界面");
						}
						if(pwdCheck==false) {
							JOptionPane.showMessageDialog(null, "用户名或密码错误!");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "登录异常!"+e1.getMessage());
						e1.printStackTrace();
					} 
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}
	    	
	    });

	    jf.setResizable(false);
	    logImg.setBounds(125,20,200,200);
	    l1.setBounds(40,300, 70, 40);
	    l2.setBounds(40,360, 70, 40);
	    l3.setBounds(100,200, 350, 100);
	    l4.setBounds(105,400, 350, 100);
	    t1.setBounds(110,300, 250, 40);
	    p1.setBounds(110,360, 250, 40);
	    
	    c=new JComboBox();
	    c.addItem("系统用户");
	    c.addItem("部门管理员");
	    c.addItem("系统管理员");
	    c.setBounds(235,430, 100, 40);
	    j1=new JButton("登录");
	    j1.setContentAreaFilled(false); 
	    j1.setFont(new java.awt.Font("黑体",1,15));
	    j1.setBorder(BorderFactory.createRaisedBevelBorder());  
	    j1.addActionListener(this);
	    
	    JButton j2=new JButton("退出");
	    j2.setContentAreaFilled(false); 
	    j2.setFont(new java.awt.Font("黑体",1,15));
	    j2.setBorder(BorderFactory.createRaisedBevelBorder()); 
	    
	    j2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==j2) {
					System.exit(1);
				}	
			}	
	    });
	    
		
	    j1.setBounds(110,510, 100, 50);
	    j2.setBounds(260,510, 100, 50);
	    jp.add(logImg);
	    jp.add(l1);
	    jp.add(l2);
	    jp.add(l3);
	    jp.add(l4);
	    jp.add(p1);
	    jp.add(t1);
	    jp.add(c);
	    jp.add(j1);
	    jp.add(j2);
	    jp.setBackground(new Color(50,200,250));
	    jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);


	}
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==j1) {
		String name=t1.getText();
		String pwd=new String(p1.getPassword());
		String role=(String)c.getSelectedItem();
		if(name==null||"".equals(name.trim())||pwd==null||"".equals(pwd.trim())) {
			JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
			return;
		}
		
		LgoinCheck lc=new LgoinCheck();
		try {
			boolean pwdCheck=lc.loginCheck(name, pwd, role);
			if(role=="系统用户"&&pwdCheck==true) {
				JOptionPane.showMessageDialog(null, "登陆成功!欢迎进入智能新闻发布系统!");
				jf.dispose();
				new MainWindows();
			}
			if(role=="系统管理员"&&pwdCheck==true) {
				JOptionPane.showMessageDialog(null, "登陆成功!欢迎进入新闻系统管理界面!");
				jf.dispose();
				//new AMDIN();
			}
			if(role=="部门管理员"&&pwdCheck==true) {
				JOptionPane.showMessageDialog(null, "登陆成功!欢迎进入新闻部门管理界面!");
				jf.dispose();
				new DepartmentEntrance("部门管理员界面");
			}
			if(pwdCheck==false) {
				JOptionPane.showMessageDialog(null, "用户名或密码错误!");
			}
		}  catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "登录异常!"+e1.getMessage());
			e1.printStackTrace();
		} 
	}
}
	

}
