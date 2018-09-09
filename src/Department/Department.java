package Department;
public class Department {//部门表属性
	private String deptID;
	private String deptName;
	public Department(String deptName) {
		this.deptName=deptName;
	}
	public Department(String deptID,String deptName) {
		this.deptID=deptID;
		this.deptName=deptName;
	}
	public void setDeptID(String deptID) {
		this.deptID=deptID;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptName(String deptName) {
		this.deptName=deptName;
	}
	public String getDeptName() {
		return deptName;
	}
}
