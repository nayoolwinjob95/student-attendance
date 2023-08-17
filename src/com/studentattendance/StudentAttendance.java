package com.studentattendance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentAttendance {
	public static void main(String[] args) {
		StudentAttendance stuAtt = new StudentAttendance();

		List<Student> students = stuAtt.initializeStudents();

		stuAtt.displayOptions(students);
	}

	// 学生一覧を追加する
	public List<Student> initializeStudents() {
		List<Student> students = Arrays.asList(new Student(1, "Taro", ' '), new Student(2, "Hanako", ' '),
				new Student(3, "Sato", ' '), new Student(4, "Nanase", ' '));
		return students;
	}

	// オプションの表示
	public void displayOptions(List<Student> students) {
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Welcome to Student Attendance Management System. Please choose an option:");
			System.out.println("1. View all students' attendance\n2. Mark attendance\n3. Exit\nEnter your choice:");
			switch (scan.nextLine()) {
			case "1":
				this.viewAllAttendance(students);
				System.out.println();
				break;
			case "2":
				this.markAttendance(students, scan);
				System.out.println();
				break;
			case "3":
				exit();
				break;
			default:
				System.out.println("Invalid choice. Please enter a number from 1-3.\n");
			}
		} while (true);
	}

	// システムの終了
	public static void exit() {
		System.out.println("Exiting Student Attendance Management System. Goodbye!");
		System.exit(0);
	}

	// 学生の出席の表示
	public void viewAllAttendance(List<Student> students) {
		String data;

		System.out.println("Name\t\t\tAttendance Status");
		try {
			File file = new File("resource/attendance.txt");
			Scanner myReader = new Scanner(file);
			for (Student student : students) {
				System.out.print(student.getStudentName() + "\t\t\t");
				if (myReader.hasNextLine()) {
					data = myReader.nextLine();
					System.out.println(data);
				}
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("File does not exist.");
		}
	}

	// 学生の出席を変更する
	public void markAttendance(List<Student> students, Scanner scan) {
		int studentId;
		String attendance;
		String data;

		// 学生IDを取得する
		do {
			System.out.println("Enter the student ID:");
			try {
				studentId = scan.nextInt();
				scan.nextLine();

				if (validateStudentId(studentId, students)) {
					break;
				} else {
					System.out.println("Please enter the correct student ID");
					continue;
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("Please enter the valid input.");
			}
		} while (true);

		// 出席を取得する
		do {
			System.out.println("Enter the attendance status (p or a):");
			attendance = scan.nextLine();
			if (attendance.equals("p") || attendance.equals("a")) {
				break;
			} else {
				System.out.println("Please enter the input as \"p\" for present or \"a\" for absent.");
			}
		} while (true);

		// 出席の変更
		try {
			File tmpFile = new File("resource/tmp.txt");
			tmpFile.createNewFile();

			File file = new File("resource/attendance.txt");
			Scanner myReader = new Scanner(file);

			FileWriter myWriter = new FileWriter(tmpFile);

			for (int i = 0; i < students.size(); i++) {
				if (myReader.hasNextLine()) {
					data = myReader.nextLine();
					if (i == studentId - 1) {
						myWriter.write(attendance + System.lineSeparator());
						continue;
					}

					myWriter.write(data + System.lineSeparator());
				}
			}

			myReader.close();
			myWriter.flush();
			myWriter.close();

			file.delete();
			tmpFile.renameTo(file);
			System.out.println("Attendance data has been written to file.");
		} catch (IOException e) {
			System.out.println("Could not update the attendance.txt file.");
		}
	}

	// 学生IDの確認
	public static boolean validateStudentId(int studentId, List<Student> students) {
		for (Student student : students) {
			if (student.getStudentId() == studentId)
				return true;
		}
		return false;
	}
}
