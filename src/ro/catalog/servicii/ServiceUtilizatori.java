package ro.catalog.servicii;

import ro.catalog.entitati.Materie;
import ro.catalog.entitati.Profesor;
import ro.catalog.entitati.Student;
import ro.catalog.entitati.Utilizator;

import java.io.IOException;

public class ServiceUtilizatori {
    private static Audit audit = Audit.getAudit();

    public static void updateNume(Utilizator utilizator, String newName) throws IOException {
        utilizator.setNume(newName);
        audit.writeAction("Update nume");
    }

    public static void updatePrenume(Utilizator utilizator, String newName) throws IOException {
        utilizator.setPrenume(newName);
        audit.writeAction("Update prenume");
    }

    public static void stergeMaterie(Profesor profesor, Materie materie) throws IOException {
        profesor.getMateriiPredate().remove(materie);
        audit.writeAction("Stergere materie");
    }

    public static void updateNrMatricol(Student student, String nrMatricol) throws IOException {
        student.setNrMatricol(nrMatricol);
        audit.writeAction("Update numar matricol");
    }

    public static void updateGrupe(Student student, int grupa) throws IOException {
        student.setGrupa(grupa);
        audit.writeAction("Update grupa");
    }

    public static Student creareStudent(String nume, String prenume, String nrMatricol, int grupa) throws IOException {
        audit.writeAction("Creare student");
        return new Student(nume, prenume, nrMatricol, grupa);
    }

    public static Profesor creareProfesor(String nume, String prenume) throws IOException {
        audit.writeAction("Creare profesor");
        return new Profesor(nume, prenume);
    }


}
