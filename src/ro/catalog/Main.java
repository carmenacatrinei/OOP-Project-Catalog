package ro.catalog;

import ro.catalog.dao.repositories.CatalogRepository;
import ro.catalog.dao.repositories.MateriiRepository;
import ro.catalog.dao.repositories.ProfesoriRepository;
import ro.catalog.dao.repositories.StudentiRepository;
import ro.catalog.entitati.Catalog;
import ro.catalog.entitati.Materie;
import ro.catalog.servicii.CitireDinFisier;
import ro.catalog.servicii.ServiceCatalog;
import ro.catalog.servicii.ServiceUtilizatori;
import ro.catalog.entitati.Profesor;
import ro.catalog.entitati.Student;
import ro.catalog.servicii.Audit;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        CitireDinFisier citire = CitireDinFisier.getCitire();
        Audit audit = Audit.getAudit();

        List<Catalog> catalogs = new ArrayList<>();

        Catalog c1 = ServiceCatalog.creazaCatalog(251);
        Catalog c2 = ServiceCatalog.creazaCatalog(252);
        //c1.getMaterii().addAll(CitireDinFisier.citireMaterii());
        //c2.getMaterii().addAll(CitireDinFisier.citireMaterii());

        catalogs.addAll(CitireDinFisier.citire.citireCatalog());

        StudentiRepository studentiRepository = StudentiRepository.getStudentiRepository();
        studentiRepository.createTable();

        ProfesoriRepository profesoriRepository = ProfesoriRepository.getProfesoriRepository();
        profesoriRepository.createTable();

        MateriiRepository materiiRepository = MateriiRepository.getMateriiRepository();
        materiiRepository.createTable();

        CatalogRepository catalogRepository = CatalogRepository.getCatalogRepository();
        catalogRepository.createTable();

        studentiRepository.deleteAll();

        Student s1 = ServiceUtilizatori.creareStudent("Popescu", "Ioan", "182/2020", 251);
        studentiRepository.insert("Popescu", "Ioan", "182/2020", 251);
        Student s2 = ServiceUtilizatori.creareStudent("Ionescu", "George", "51/2020", 251);
        studentiRepository.insert("Ionescu", "George", "51/2020", 251);
        Student s3 = ServiceUtilizatori.creareStudent("Ene", "Maria", "185/2020", 251);
        studentiRepository.insert("Ene", "Maria", "185/2020", 252);
        Student s4 = ServiceUtilizatori.creareStudent("Popa", "Ioana", "200/2020", 251);
        studentiRepository.insert("Popa", "Ioana", "200/2020", 251);

        materiiRepository.deleteAll();

        Materie m1 = ServiceCatalog.creareMaterie("IA", 4);
        materiiRepository.insert("IA", 4);
        Materie m2 = ServiceCatalog.creareMaterie("AA", 5);
        materiiRepository.insert("AA", 5);
        Materie m3 = ServiceCatalog.creareMaterie("PAO", 6);
        materiiRepository.insert("PAO", 6);
        Materie m4 = ServiceCatalog.creareMaterie("MDS", 4);
        materiiRepository.insert("MDS", 4);

        profesoriRepository.deleteAll();

        Profesor p1 = ServiceUtilizatori.creareProfesor("Dragan","Ioan");
        profesoriRepository.insert("Dragan","Ioan");
        Profesor p2 = ServiceUtilizatori.creareProfesor("Matei","Delia");
        profesoriRepository.insert("Matei","Delia");
        Profesor p3 = ServiceUtilizatori.creareProfesor("Paul","Mihai");
        profesoriRepository.insert("Paul","Mihai");
        Profesor p4 = ServiceUtilizatori.creareProfesor("Iona","Ana");
        profesoriRepository.insert("Iona","Ana");

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

        ServiceCatalog.afisareCatalog(c1);

        boolean exit = Boolean.FALSE;

        while (!exit) {
            System.out.println("MENIU");
            System.out.println("-----");
            System.out.println("0.Parasire meniu");
            System.out.println("1.Adauga student");
            System.out.println("2.Adauga profesor");
            System.out.println("3.Afisati toti studentii de la o anumita grupa");
            System.out.println("4.Afisati toate materiile in ordinea descrescatoare a numarului de credite");
            System.out.println("5.Modificati numarul matricol al unui student");
            System.out.println("6.Afisati profesorii alfabetic");
            System.out.println("7.Afisati toate grupele de studenti");
            System.out.println("8.Concediere profesor");
            System.out.println("9.Afisare note student");
            System.out.println("10.Modificati numarul de credite al unei materii");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> exit = true;
                case 1 -> {

                    System.out.println("Scrieti numele de familie al studentului:");
                    String nume = scanner.nextLine();

                    System.out.println("Scrieti prenumele studentului:");
                    String prenume = scanner.nextLine();

                    System.out.println("Scrieti numarul matricol al studentului:");
                    String nrMatricol = scanner.nextLine();

                    System.out.println("Scrieti grupa studentului:");
                    Integer grupa = scanner.nextInt();

                    List<Catalog> listaCatalog = catalogRepository.select();

                    Boolean gasit = Boolean.FALSE;

                    for (Catalog catalog : listaCatalog)
                        if (catalog.getGrupa() == grupa)
                            gasit = Boolean.TRUE;

                    if (gasit == Boolean.FALSE)
                        catalogRepository.insert(grupa);

                    Student student = ServiceUtilizatori.creareStudent(nume, prenume, nrMatricol, grupa);
                    studentiRepository.insert(nume, prenume, nrMatricol, grupa);
                    System.out.println("Studentul a fost adaugat cu succes!");
                }
                case 2 -> {
                    System.out.println("Scrieti numele de familie al profesorului:");
                    String nume = scanner.nextLine();

                    System.out.println("Scrieti prenumele profesorului:");
                    String prenume = scanner.nextLine();

                    Profesor profesor = ServiceUtilizatori.creareProfesor(nume, prenume);
                    profesoriRepository.insert(nume, prenume);
                    System.out.println("Profesorul a fost adaugat cu succes!");
                }
                case 3 -> {
                    System.out.println("Scrieti grupa:");
                    Integer grupa = scanner.nextInt();

                    List<Student> studenti = studentiRepository.select().stream()
                                                .filter(student -> Integer.compare(student.getGrupa(), grupa) == 0)
                                                    .toList();

                    if (studenti.size() == 0)
                        System.out.println("Nu exista studenti la grupa mentionata!");

                    studenti.forEach(student -> System.out.println(student.toString()));
                }
                case 4 -> {
                    List<Materie> materii = materiiRepository.select();

                    Comparator<Materie> comparator = Comparator.comparingInt(Materie::getNrCredite);

                    materii = materii.stream().sorted(comparator.reversed()).collect(Collectors.toList());

                    materii.forEach(materie -> System.out.println(materie.toString()));

                }
                case 5 -> {
                    System.out.println("Scrieti numele de familie al studentului:");
                    String nume = scanner.nextLine();

                    System.out.println("Scrieti noul numar matricol:");
                    String nrMatricol = scanner.nextLine();

                    studentiRepository.update(nume, nrMatricol);

                    System.out.println("Numarul matricol a fost modificat cu succes!");
                }
                case 6 -> {
                    Comparator<Profesor> comparator = (prof1, prof2) -> prof1.getNume().compareTo(prof2.getNume());

                    List<Profesor> profesori = profesoriRepository.select().stream().sorted(comparator).toList();

                    profesori.forEach(profesor -> System.out.println(profesor.toString()));
                }
                case 7 -> {
                    Set<Integer> grupe = studentiRepository.select().stream()
                                            .map(Student::getGrupa)
                                                .collect(Collectors.toSet());

                    grupe.forEach(grupa -> System.out.println(grupa));
                }
                case 8 -> {
                    System.out.println("Scrieti numele de familie al profesorului:");
                    String nume = scanner.nextLine();

                    profesoriRepository.delete(nume);
                }
                case 9 -> {
                    System.out.println("Scrieti numele de familie al studentului:");
                    String nume = scanner.nextLine();

                    Student student = studentiRepository.select().stream()
                                        .filter(s -> s.getNume().compareTo(nume) == 0)
                                            .toList()
                                                .get(0);

                    ServiceCatalog.afisareNoteStudent(c1, student);
                }
                case 10 -> {
                    System.out.println("Scrieti numele materiei:");
                    String nume = scanner.nextLine();

                    System.out.println("Scrieti numarul nou de credite:");
                    Integer credite = scanner.nextInt();

                    materiiRepository.update(credite, nume);
                }
                default -> {
                    System.out.println("Introduceti o comanda valida!");
                }
            }
        }

        //studentiRepository.select().forEach(student -> System.out.println(student.toString()));

//        catalogRepository.insert(251);
//        catalogRepository.insert(252);
//        catalogRepository.insert(451);
//        catalogRepository.select();
//        catalogRepository.update(3, 261);
//        catalogRepository.select();
//        catalogRepository.delete(3);
//        catalogRepository.select();

    }
}
