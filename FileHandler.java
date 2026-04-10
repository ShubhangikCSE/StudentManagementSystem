import java.io.*;
import java.util.*;

public class FileHandler {

    static String FILE = "students.dat";

    static ArrayList<Student> read() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    static void write(ArrayList<Student> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(list);
        } catch (Exception e) {
            System.out.println("File error");
        }
    }

    static void addStudent(Student s) {
        ArrayList<Student> list = read();
        for (Student x : list)
            if (x.getId() == s.getId()) {
                System.out.println("Duplicate ID");
                return;
            }
        list.add(s);
        write(list);
        System.out.println("Added");
    }

    static void viewStudents() {
        ArrayList<Student> list = read();
        if (list.isEmpty()) System.out.println("No records");
        else for (Student s : list) System.out.println(s);
    }

    static void searchStudent(int id) {
        for (Student s : read())
            if (s.getId() == id) {
                System.out.println(s);
                return;
            }
        System.out.println("Not found");
    }

    static void updateStudent(int id, String name, double marks) {
        ArrayList<Student> list = read();
        for (Student s : list)
            if (s.getId() == id) {
                s.setName(name);
                s.setMarks(marks);
                write(list);
                System.out.println("Updated");
                return;
            }
        System.out.println("Not found");
    }

    static void deleteStudent(int id) {
        ArrayList<Student> list = read();
        if (list.removeIf(s -> s.getId() == id)) {
            write(list);
            System.out.println("Deleted");
        } else System.out.println("Not found");
    }

    static void showTopper() {
        ArrayList<Student> list = read();
        if (!list.isEmpty())
            System.out.println(Collections.max(list, Comparator.comparing(Student::getMarks)));
    }

    static void countStudents() {
        System.out.println("Total: " + read().size());
    }

    static void sortByMarks() {
        ArrayList<Student> list = read();
        list.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        for (Student s : list) System.out.println(s);
    }

    static void exportToText() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student s : read()) bw.write(s + "\n");
            System.out.println("Exported");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}