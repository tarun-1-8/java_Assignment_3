import java.util.*;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) {
        super(msg);
    }
}

class Student {
    long rollNumber;                     // changed to long
    String studentName;
    int marks[] = new int[3];

    Student(long r, String n, int m[]) { // changed to long
        rollNumber = r;
        studentName = n;
        marks = m;
    }

    void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < 3; i++) {
            if (marks[i] < 0 || marks[i] > 100)
                throw new InvalidMarksException("Invalid Marks: " + marks[i]);
        }
    }

    double calculateAverage() {
        return (marks[0] + marks[1] + marks[2]) / 3.0;
    }

    void displayResult() {
        System.out.println("Roll: " + rollNumber);
        System.out.println("Name: " + studentName);
        System.out.println("Marks: " + marks[0] + " " + marks[1] + " " + marks[2]);
        double avg = calculateAverage();
        System.out.println("Average: " + avg);
        System.out.println("Result: " + (avg >= 40 ? "Pass" : "Fail"));
    }
}

public class ResultManager {
    Student students[] = new Student[50];
    int count = 0;
    Scanner sc = new Scanner(System.in);

    void addStudent() {
        try {
            System.out.print("Roll No: ");
            long r = sc.nextLong();      // changed to nextLong()
            sc.nextLine();

            System.out.print("Name: ");
            String n = sc.nextLine();

            int m[] = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Marks " + (i+1) + ": ");
                m[i] = sc.nextInt();
            }

            Student s = new Student(r, n, m);
            s.validateMarks();
            students[count++] = s;
            System.out.println("Student Added!");

        } catch (InvalidMarksException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
            sc.nextLine();
        } finally {
            System.out.println("Add Student Completed.\n");
        }
    }

    void showStudentDetails() {
        try {
            System.out.print("Enter Roll No: ");
            long r = sc.nextLong();      // changed to nextLong()

            for (int i = 0; i < count; i++) {
                if (students[i].rollNumber == r) {
                    students[i].displayResult();
                    return;
                }
            }
            System.out.println("Student Not Found!");

        } catch (Exception e) {
            System.out.println("Error!");
        } finally {
            System.out.println("Search Completed.\n");
        }
    }

    public void mainMenu() {
        int ch = 0;
        try {
            do {
                System.out.println("1. Add Student\n2. Show Student\n3. Exit");
                System.out.print("Enter Choice: ");
                ch = sc.nextInt();

                if (ch == 1) addStudent();
                else if (ch == 2) showStudentDetails();
                else if (ch == 3) System.out.println("Exit...");

            } while (ch != 3);
        } finally {
            sc.close();
            System.out.println("Program Closed.");
        }
    }

    public static void main(String args[]) {
        new ResultManager().mainMenu();
    }
}