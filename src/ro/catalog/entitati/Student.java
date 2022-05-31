package ro.catalog.entitati;

import java.util.Objects;

public class Student extends Utilizator {
    private String nrMatricol;
    private int grupa;


    public Student(String nume, String prenume, String nrMatricol, int grupa) {
        super(nume, prenume);
        this.nrMatricol = nrMatricol;
        this.grupa = grupa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNrMatricol() {
        return nrMatricol;
    }

    public void setNrMatricol(String nrMatricol) {
        this.nrMatricol = nrMatricol;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", nrMatricol='" + nrMatricol + '\'' +
                ", grupa=" + grupa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getGrupa() == student.getGrupa() && getNume().equals(student.getNume()) &&
                getPrenume().equals(student.getPrenume()) && getNrMatricol().equals(student.getNrMatricol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getPrenume(), getNrMatricol(), getGrupa());
    }
}
