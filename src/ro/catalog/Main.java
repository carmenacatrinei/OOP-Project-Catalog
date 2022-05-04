package ro.catalog;

import ro.catalog.servicii.ServiceCatalog;
import ro.catalog.servicii.ServiceUtilizatori;
import ro.catalog.utilizatori.Profesor;
import ro.catalog.utilizatori.Student;

public class Main {

    public static void main(String[] args) {
        Student s1 = ServiceUtilizatori.creazaStudent("Popescu", "Ioan", "182/2020", 251);
        Student s2 = ServiceUtilizatori.creazaStudent("Ionescu", "George", "51/2020", 251);
        Student s3 = ServiceUtilizatori.creazaStudent("Ene", "Maria", "185/2020", 251);
        Student s4 = ServiceUtilizatori.creazaStudent("Popa", "Ioana", "200/2020", 251);

        Materie m1 = ServiceCatalog.creazaMaterie("IA", 4);
        Materie m2 = ServiceCatalog.creazaMaterie("AA", 5);
        Materie m3 = ServiceCatalog.creazaMaterie("PAO", 6);
        Materie m4 = ServiceCatalog.creazaMaterie("MDS", 4);

        Profesor p1 = ServiceUtilizatori.creazaProfesor("Dragan","Ioan");
        Profesor p2 = ServiceUtilizatori.creazaProfesor("Matei","Delia");
        Profesor p3 = ServiceUtilizatori.creazaProfesor("Paul","Mihai");
        Profesor p4 = ServiceUtilizatori.creazaProfesor("Iona","Ana");

        Catalog c1 = ServiceCatalog.creazaCatalog(251);
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

        //System.out.println(c1.afisareCatalog());
        // System.out.println(ServiceCatalog.afisareNoteStudent(c1, s1));
        System.out.println(ServiceCatalog.afisareMediiDesc(c1));

        for (Student student:ServiceCatalog.getStudentiDupaNumeCrescator(c1)) {
            System.out.println(student.toString());
        }
    }
}
