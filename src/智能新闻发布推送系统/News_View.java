package 智能新闻发布推送系统;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class News_View {
	JFrame jf=new JFrame();
	JPanel jp=new JPanel();
	JTextField title,time;
	JTextArea content;
	JButton jb,evaluate;
	public News_View(String a) {
		Base b1=new Base();
		try {
			
			Connection conn=b1.getConnection();
			String sql = "select * from news where newsID='"+a+"'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			PreparedStatement p = conn.prepareStatement("update news set newVisits=newVisits+1 where newsID='"+a+"'");
			p.executeUpdate();
			ResultSet rs = pstm.executeQuery();
			rs.next();//默认指向-1 向后移动一位
            title=new JTextField((rs.getString("newTitle")).trim());
            time=new JTextField(rs.getString("newDate"));
            content=new JTextArea(rs.getString("newContent")); 
            JScrollPane sco=new JScrollPane(content);
            title.setEditable(false);
            time.setEditable(false);
            content.setEditable(false);
            title.setFont(new java.awt.Font("黑体",1,16));
            time.setFont(new java.awt.Font("黑体",1,16));
            content.setFont(new java.awt.Font("黑体",1,18));
            content.setLineWrap(true);//换行
            jb=new JButton("返回");
            jb.setContentAreaFilled(false); 
            jb.setFont(new java.awt.Font("黑体",0,16));
            jb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource()==jb) {
						jf.dispose();
					}
					
				}
            	
            });
            evaluate=new JButton("查看评价");
            evaluate.setFont(new java.awt.Font("黑体",0,16));
            evaluate.setContentAreaFilled(false); 
            evaluate.addActionListener(e->{
            	if(e.getSource()==evaluate) {
            		new Evaluation(a);
            	}
            });
            
            jf.add(title);
            jf.add(time);
            jf.add(sco);
            jf.add(jb);
            jf.add(evaluate);
            title.setBounds(100,10,300,50);
            time.setBounds(100,80,300,50);
            sco.setBounds(50,150,400,500);
            jb.setBounds(100,680,100,50);
            evaluate.setBounds(250,680,150,50);
    		jf.setLayout(null);
    		jf.setSize(500,800);
    		jf.setLocationRelativeTo(null);
    		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		jf.add(jp);
    		jf.setVisible(true);
    		conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "此新闻不存在! 请检查新闻序号.");
			e.printStackTrace();
		}

	}

}

