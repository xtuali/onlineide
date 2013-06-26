package org.webteam.entity;

/**
 * CptTeacher entity. @author MyEclipse Persistence Tools
 */

public class CptTeacher implements java.io.Serializable {

	// Fields

	private String teacherId;
	private String teacherName;
	private String sex;
	private String phoneNo;
	private String telphoneNo;
	private String addressLine;
	private String email;
	private String collegeId;
	private String staffroomId;
	private String teachSubject;
	private String teachCourse;
	private String username;
	private String password;
	private String userStatus;

	// Constructors

	/** default constructor */
	public CptTeacher() {
	}

	/** minimal constructor */
	public CptTeacher(String teacherId) {
		this.teacherId = teacherId;
	}

	/** full constructor */
	public CptTeacher(String teacherId, String teacherName, String sex,
			String phoneNo, String telphoneNo, String addressLine,
			String email, String collegeId, String staffroomId,
			String teachSubject, String teachCourse, String username,
			String password, String userStatus) {
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.sex = sex;
		this.phoneNo = phoneNo;
		this.telphoneNo = telphoneNo;
		this.addressLine = addressLine;
		this.email = email;
		this.collegeId = collegeId;
		this.staffroomId = staffroomId;
		this.teachSubject = teachSubject;
		this.teachCourse = teachCourse;
		this.username = username;
		this.password = password;
		this.userStatus = userStatus;
	}

	// Property accessors

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTelphoneNo() {
		return this.telphoneNo;
	}

	public void setTelphoneNo(String telphoneNo) {
		this.telphoneNo = telphoneNo;
	}

	public String getAddressLine() {
		return this.addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getStaffroomId() {
		return this.staffroomId;
	}

	public void setStaffroomId(String staffroomId) {
		this.staffroomId = staffroomId;
	}

	public String getTeachSubject() {
		return this.teachSubject;
	}

	public void setTeachSubject(String teachSubject) {
		this.teachSubject = teachSubject;
	}

	public String getTeachCourse() {
		return this.teachCourse;
	}

	public void setTeachCourse(String teachCourse) {
		this.teachCourse = teachCourse;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}