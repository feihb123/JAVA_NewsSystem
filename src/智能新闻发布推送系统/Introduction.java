package 智能新闻发布推送系统;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Introduction {
	public  Introduction() {
	JFrame jf=new JFrame("新闻评价");
	jf.setSize(600, 400);
	jf.setLocationRelativeTo(null);
	JPanel jp=new JPanel();
	jp.setLayout(null);
	ImageIcon img = new ImageIcon(getClass().getResource("/img/xzit.jpg"));
	JLabel xzit = new JLabel(img);
	JLabel l1,l2,l3;
	l1=new JLabel("智能新闻发布推送系统");
	l2=new JLabel("------信电学院------");
	l3=new JLabel("-费浩彬 周涵 陈怀幸-");
	l1.setFont(new java.awt.Font("黑体",1,20));
	l2.setFont(new java.awt.Font("黑体",1,20));
	l3.setFont(new java.awt.Font("黑体",1,20));
	jp.add(l1);
	jp.add(l2);
	jp.add(l3);
	jp.add(xzit);
	l1.setBounds(30,60,400,80);
	l2.setBounds(30,110,400,80);
	l3.setBounds(30,160,400,80);
	xzit.setBounds(320,50,200,200);
	jf.add(jp);
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	}
	
	
}
