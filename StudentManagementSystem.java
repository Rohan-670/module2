import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Student Management System using Java Collections.
 *
 * Features:
 * 1. Add a student
 * 2. View all students
 * 3. Search for a student by ID
 * 4. Edit student details
 * 5. Delete a student
 * 6. Exit
 *
 * Compile: javac StudentManagementSystem.java
 * Run:     java StudentManagementSystem
 */
public class StudentManagementSystem {
    // LinkedHashMap stores students by ID and preserves insertion order.
    private static final Map<Integer, Student> students = new LinkedHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("      STUDENT MANAGEMENT SYSTEM");
        System.out.println("====================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Student Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 6.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n------------- MAIN MENU ------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Edit Student Details");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");
        int id = readPositiveInt("Enter student ID: ");
        if (students.containsKey(id)) {
            System.out.println("A student with ID " + id + " already exists.");
            return;
        }

        String name = readNonEmptyString("Enter student name: ");
        int age = readPositiveInt("Enter age: ");
        String course = readNonEmptyString("Enter course/branch: ");
        String email = readNonEmptyString("Enter email: ");

        Student student = new Student(id, name, age, course, email);
        students.put(id, student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-8s %-22s %-6s %-24s %-30s%n", "ID", "NAME", "AGE", "COURSE", "EMAIL");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (Student student : students.values()) {
            student.displayRow();
        }
        System.out.println("Total students: " + students.size());
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        int id = readPositiveInt("Enter student ID to search: ");
        Student student = students.get(id);
        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
        } else {
            student.displayDetails();
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Edit Student Details ---");
        int id = readPositiveInt("Enter student ID to edit: ");
        Student student = students.get(id);
        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
            return;
        }

        System.out.println("Current details:");
        student.displayDetails();
        System.out.println("Enter the updated details below.");

        String name = readNonEmptyString("Enter new name: ");
        int age = readPositiveInt("Enter new age: ");
        String course = readNonEmptyString("Enter new course/branch: ");
        String email = readNonEmptyString("Enter new email: ");

        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        student.setEmail(email);
        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int id = readPositiveInt("Enter student ID to delete: ");
        Student removed = students.remove(id);
        if (removed == null) {
            System.out.println("No student found with ID " + id + ".");
        } else {
            System.out.println("Deleted record for " + removed.getName() + " (ID: " + id + ").");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a number greater than zero.");
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    private static class Student {
        private final int id;
        private String name;
        private int age;
        private String course;
        private String email;

        Student(int id, String name, int age, String course, String email) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.course = course;
            this.email = email;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        void setAge(int age) {
            this.age = age;
        }

        void setCourse(String course) {
            this.course = course;
        }

        void setEmail(String email) {
            this.email = email;
        }

        void displayRow() {
            System.out.printf("%-8d %-22s %-6d %-24s %-30s%n", id, name, age, course, email);
        }

        void displayDetails() {
            System.out.println("Student ID : " + id);
            System.out.println("Name       : " + name);
            System.out.println("Age        : " + age);
            System.out.println("Course     : " + course);
            System.out.println("Email      : " + email);
        }
    }
}
