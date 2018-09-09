package Department;
public class Classify {//类别表属性
	private String classifyID;
	private String classifyName;
	public Classify(String classifyName) {
		this.classifyName=classifyName;
	}
	public Classify(String classifyID,String classifyName) {
		this.classifyID=classifyID;
		this.classifyName=classifyName;
	}
	public void setClassifyID(String classifyID) {
		this.classifyID=classifyID;
	}
	public String getClassifyID() {
		return classifyID;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName=classifyName;
	}
	public String getClassifyName() {
		return classifyName;
	}
}