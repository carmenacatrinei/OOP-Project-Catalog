package ro.catalog;

import ro.catalog.servicii.CitireDinFisier;
import ro.catalog.servicii.ServiceCatalog;
import ro.catalog.servicii.ServiceUtilizatori;
import ro.catalog.utilizatori.Profesor;
import ro.catalog.utilizatori.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        CitireDinFisier citire = CitireDinFisier.getCitire();

        List<Catalog> catalogs = new ArrayList<>();

        Catalog c1 = ServiceCatalog.creazaCatalog(251);
        Catalog c2 = ServiceCatalog.creazaCatalog(252);
        c1.getMaterii().addAll(CitireDinFisier.citireMaterii());
        c2.getMaterii().addAll(CitireDinFisier.citireMaterii());

        catalogs.addAll(CitireDinFisier.citire.citireCatalog());


        Student s1 = ServiceUtilizatori.creareStudent("Popescu", "Ioan", "182/2020", 251);
        Student s2 = ServiceUtilizatori.creareStudent("Ionescu", "George", "51/2020", 251);
        Student s3 = ServiceUtilizatori.creareStudent("Ene", "Maria", "185/2020", 251);
        Student s4 = ServiceUtilizatori.creareStudent("Popa", "Ioana", "200/2020", 251);

        Materie m1 = ServiceCatalog.creareMaterie("IA", 4);
        Materie m2 = ServiceCatalog.creareMaterie("AA", 5);
        Materie m3 = ServiceCatalog.creareMaterie("PAO", 6);
        Materie m4 = ServiceCatalog.creareMaterie("MDS", 4);

        Profesor p1 = ServiceUtilizatori.creareProfesor("Dragan","Ioan");
        Profesor p2 = ServiceUtilizatori.creareProfesor("Matei","Delia");
        Profesor p3 = ServiceUtilizatori.creareProfesor("Paul","Mihai");
        Profesor p4 = ServiceUtilizatori.creareProfesor("Iona","Ana");


        ServiceCatalog.adaugaStudentInCatalog(c1, s1);
        ServiceCatalog.adaugaStudentInCatalog(c1, s2);
        ServiceCatalog.adaugaStudentInCatalog(c1, s3);
        ServiceCatalog.adaugaStudentInCatalog(c1, s4);
        ServiceCatalog.adaugaMaterieInCatalog(c1, m1);
        ServiceCatalog.adaugaMaterieInCatalog(c1, m2);
        ServiceCatalog.adaugaMaterieInCatalog(c1, m3);
        ServiceCatalog.adaugaMaterieInCatalog(c1, m4);

        ServiceCatalog.initializareCatalog(c1);
        ServiceCatalog.adaugareNotaStudent(c1, s1, 9, m1);
        ServiceCatalog.adaugareNotaStudent(c1, s1, 7, m2);
        ServiceCatalog.adaugareNotaStudent(c1, s1, 10, m3);
        ServiceCatalog.adaugareNotaStudent(c1, s1, 10, m4);

        System.out.println(ServiceCatalog.afisareMediiDesc(c1));

        for (Student student:ServiceCatalog.getStudentiDupaNumeCrescator(c1)) {
            System.out.println(student.toString());
        }

        System.out.println(c1);
        System.out.println(c2);
    }
}
