package org.webteam.entity;
/**
 * CptClass entity. @author MyEclipse Persistence Tools
 */

public class CptClass implements java.io.Serializable {

	// Fields

	private String classId;
	private String classNo;
	private String className;
	private String classStatus;
	private String bigClassInd;
	private String classMemo;
	private String deptId;
	private String grade;
	private String majorId;
	private String courseId;
	private String courseInd;
	private String userId;
	private String bigClassId;
	

	// Constructors

	public String getBigClassId() {
		return bigClassId;
	}

	public void setBigClassId(String bigClassId) {
		this.bigClassId = bigClassId;
	}

	/** default constructor */
	public CptClass() {
	}

	/** minimal constructor */
	public CptClass(String classId) {
		this.classId = classId;
	}

	public CptClass(String classId, String classNo, String className,
			String classStatus, String bigClassInd, String classMemo,
			String deptId, String grade, String majorId, String courseId,
			String courseInd, String userId, String bigClassId) {
		super();
		this.classId = classId;
		this.classNo = classNo;
		this.className = className;
		this.classStatus = classStatus;
		this.bigClassInd = bigClassInd;
		this.classMemo = classMemo;
		this.deptId = deptId;
		this.grade = grade;
		this.majorId = majorId;
		this.courseId = courseId;
		this.courseInd = courseInd;
		this.userId = userId;
		this.bigClassId = bigClassId;
	}

	/** full constructor */
	
	// Property accessors

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassStatus() {
		return this.classStatus;
	}

	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

	public String getBigClassInd() {
		return this.bigClassInd;
	}

	public void setBigClassInd(String bigClassInd) {
		this.bigClassInd = bigClassInd;
	}

	public String getClassMemo() {
		return this.classMemo;
	}

	public void setClassMemo(String classMemo) {
		this.classMemo = classMemo;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajorId() {
		return this.majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseInd() {
		return this.courseInd;
	}

	public void setCourseInd(String courseInd) {
		this.courseInd = courseInd;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}