package ro.catalog.comparator;

import ro.catalog.entitati.Student;

import java.util.Comparator;

public class StudentComparator {

    public static Comparator<Student> getComparatorNumeCrescator(){
        return new Comparator<Student>() {
            @Override
            public int compare(Student stud1, Student stud2) {
                String nume1 = stud1.getNume();
                String nume2 = stud2.getNume();
                return nume1.compareTo(nume2);
            }
        };
    }

    public static Comparator<Student> getComparatorNumeDescrescator(){
        return new Comparator<Student>() {
            @Override
            public int compare(Student stud1, Student stud2) {
                String nume1 = stud1.getNume();
                String nume2 = stud2.getNume();
                return nume2.compareTo(nume1);
            }
        };
    }
}
