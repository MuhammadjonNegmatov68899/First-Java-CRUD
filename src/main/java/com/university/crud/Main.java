package com.university.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Student {
        int id;
        String name;
        String email;

        Student(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Name: " + name + " | Email: " + email;
        }
    }

    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student CRUD Application ===");
            System.out.println("1. Create student");
            System.out.println("2. Read students");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createStudent();
                case "2" -> readStudents();
                case "3" -> updateStudent();
                case "4" -> deleteStudent();
                case "0" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
        System.out.println("Application closed.");
    }

    private static void createStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        students.add(new Student(nextId++, name, email));
        System.out.println("Student created successfully.");
    }

    private static void readStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        Student student = findStudent();
        if (student == null) {
            return;
        }

        System.out.print("Enter new name: ");
        student.name = scanner.nextLine();

        System.out.print("Enter new email: ");
        student.email = scanner.nextLine();

        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        Student student = findStudent();
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        }
    }

    private static Student findStudent() {
        System.out.print("Enter student ID: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            for (Student student : students) {
                if (student.id == id) {
                    return student;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric ID.");
            return null;
        }

        System.out.println("Student not found.");
        return null;
    }
}
