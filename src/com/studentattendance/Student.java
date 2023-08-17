package com.studentattendance;

public class Student {
	private int studentId;
	private String studentName;
	private char attendanceStatus;

	public Student(int studentId, String studentName, char attendanceStatus) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.attendanceStatus = attendanceStatus;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public char getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(char attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", attendanceStatus="
				+ attendanceStatus + "]";
	}

}
