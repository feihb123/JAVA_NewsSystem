package Department;
import java.sql.Timestamp;
public class News {//新闻表属性
	private String newsID;
	private String deptID;
	private String classifyID;
	private String newTitle;
	private String newContent;
	private Timestamp newDate;
	public News(String newsID,String classifyID,String deptID,String newTitle,String newContent) {
		this.newsID=newsID;
		this.classifyID=classifyID;
		this.deptID=deptID;
		this.newTitle=newTitle;
		this.newContent=newContent;
	}
	public News(String newsID,String newTitle,String newContent,Timestamp newDate) {
		this.newsID=newsID;
		this.newTitle=newTitle;
		this.newContent=newContent;
		this.newDate=newDate;
	}
	public News(String newsID,String classifyID,String deptID,String newTitle,String newContent,Timestamp newDate) {
		this.newsID=newsID;
		this.classifyID=classifyID;
		this.deptID=deptID;
		this.newTitle=newTitle;
		this.newContent=newContent;
		this.newDate=newDate;
	}
	public void setNewsID(String newsID) {
		this.newsID=newsID;
	}
	public String getNewsID() {
		return newsID;
	}
	public void setClassifyID(String classifyID) {
		this.classifyID=classifyID;
	}
	public String getClassifyID() {
		return classifyID;
	}
	public void setDeptID(String deptID) {
		this.deptID=deptID;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setNewTitle(String newTitle) {
		this.newTitle=newTitle;
	}
	public String getNewTitle() {
		return newTitle;
	}
	public void setNewContent(String newContent) {
		this.newContent=newContent;
	}
	public String getNewContent() {
		return newContent;
	}
	public void setNewDate(Timestamp newDate) {
		this.newDate=newDate;
	}
	public Timestamp getNewDate() {
		return newDate;
	}
}
