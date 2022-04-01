package ro.catalog.servicii;

import ro.catalog.Materie;
import ro.catalog.utilizatori.Profesor;
import ro.catalog.utilizatori.Student;
import ro.catalog.utilizatori.Utilizator;

public class ServiceUtilizatori {
    public static void updateNume(Utilizator utilizator, String newName){
        utilizator.setNume(newName);
    }

    public static void updatePrenume(Utilizator utilizator, String newName){
        utilizator.setPrenume(newName);
    }

    public static void stergeMaterie(Profesor profesor, Materie materie){
        profesor.getMateriiPredate().remove(materie);
    }

    public static void updateNrMatricol(Student student, String nrMatricol){
        student.setNrMatricol(nrMatricol);
    }

    public static void updateGrupe(Student student, int grupa){
        student.setGrupa(grupa);
    }

    public static Student creazaStudent(String nume, String prenume, String nrMatricol, int grupa){
        return new Student(nume, prenume, nrMatricol, grupa);
    }

    public static Profesor creazaProfesor(String nume, String prenume){
        return new Profesor(nume, prenume);
    }
}
