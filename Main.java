import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Show Topper");
            System.out.println("7. Count Students");
            System.out.println("8. Sort by Marks");
            System.out.println("9. Export to Text File");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    int id;
                    double marks;
                    String name;

                    while (true) {
                        System.out.print("Enter ID: ");
                        while (!sc.hasNextInt()) {
                            System.out.print("Invalid input. Enter a valid ID: ");
                            sc.next();
                        }
                        id = sc.nextInt();
                        if (id > 0) break;
                        System.out.println("ID must be positive.");
                    }

                    sc.nextLine(); // clear buffer

                    while (true) {
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        if (!name.trim().isEmpty()) break;
                        System.out.println("Name cannot be empty.");
                    }

                    while (true) {
                        System.out.print("Enter Marks (0-100): ");
                        while (!sc.hasNextDouble()) {
                            System.out.print("Invalid input. Enter numeric marks: ");
                            sc.next();
                        }
                        marks = sc.nextDouble();
                        if (marks >= 0 && marks <= 100) break;
                        System.out.println("Marks must be between 0 and 100.");
                    }

                    FileHandler.addStudent(new Student(id, name, marks));
                    break;

                case 2:
                    FileHandler.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    FileHandler.searchStudent(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();

                    System.out.print("Enter New Marks: ");
                    double umarks = sc.nextDouble();

                    FileHandler.updateStudent(uid, uname, umarks);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    FileHandler.deleteStudent(sc.nextInt());
                    break;

                case 6:
                    FileHandler.showTopper();
                    break;

                case 7:
                    FileHandler.countStudents();
                    break;

                case 8:
                    FileHandler.sortByMarks();
                    break;

                case 9:
                    FileHandler.exportToText();
                    break;

                case 10:
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 10);

        sc.close();
    }
}