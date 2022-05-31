package ro.catalog.servicii;

import ro.catalog.entitati.Catalog;
import ro.catalog.entitati.Materie;
import ro.catalog.exceptii.NotaInvalida;
import ro.catalog.entitati.Student;
import ro.catalog.comparator.StudentComparator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ServiceCatalog implements ServiceCatalogInterface{
    private static Audit audit = Audit.getAudit();

    public static Catalog creazaCatalog(int grupa) throws IOException {
        audit.writeAction("Creare catalog");
        return new Catalog(grupa);
    }

    static public Materie creareMaterie(String denumire, int nrCredite) throws IOException {
        audit.writeAction("Creare materie");
        return new Materie(denumire, nrCredite);
    }

    public static void adaugaStudentInCatalog(Catalog catalog, Student student) throws IOException {
        audit.writeAction("Adaugare student in catalog");
        catalog.adaugareStudent(student);
    }

    public static void adaugaMaterieInCatalog(Catalog catalog, Materie materie) throws IOException {
        audit.writeAction("Adaugare materie in catalog");
        catalog.adaugareMaterie(materie);
    }

    /**
     * Metoda ce initializeaza catalogul dupa ce sunt adaugati toti studentii si toate materiile
     *
     * @param catalog Catalogul ce trebuile initializat
     */
    public static void initializareCatalog(Catalog catalog) throws IOException {

        List<List<Integer>> catalogNote = new ArrayList<>();
        for(int i = 0; i < catalog.getStudentiSize(); i++){
            List<Integer> arrayNoteStudent = new ArrayList<>();
            for (int j =0; j < catalog.getMateriiSize(); j++) {
                arrayNoteStudent.add(1);
            }
            catalogNote.add(arrayNoteStudent);
        }
        catalog.setNote(catalogNote);
        audit.writeAction("Initializare catalog");
    }

    static public void adaugareNotaStudent(Catalog catalog, Student student, int nota, Materie materie) throws IOException {
        try {
            if(nota<0 || nota>10) {
                throw new NotaInvalida("Nota nu poate fi negativa sau mai mare decat 10!");
            }
            catalog.setNota(nota, student, materie);
        }
        catch (NotaInvalida e) {
            System.out.println(e.getMessage());
        }
        audit.writeAction("Adaugare nota student");
    }

    static public String afisareCatalog(Catalog catalog) throws IOException {
        audit.writeAction("Afisare catalog");
        return catalog.afisareCatalog();
    }

    static public void afisareNoteStudent(Catalog catalog, Student student) throws IOException {
        audit.writeAction("Afisare note student");
        catalog.afisareNoteStudent(student);
    }

    static public String afisareMediiDesc(Catalog catalog) throws IOException {
        Map<String, Float> medii = new HashMap<>();
        List<List<Integer>> note = catalog.getNote();
        for(int i = 0; i < catalog.getStudentiSize(); i++){
            int sumaNote = 0;
            List<Integer> noteStudent = note.get(i);
            for (Integer nota: noteStudent) {
                sumaNote += nota;
            }
            float medie;
            medie = (float) sumaNote / catalog.getMateriiSize();
            medii.put(catalog.getStudentFromIndex(i).getNrMatricol(), medie);

        }

        Map<String, Float> mediiSortate = medii
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new));
        StringBuilder afisare = new StringBuilder();
        for (Map.Entry<String, Float> entry:mediiSortate.entrySet()) {
            afisare.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        audit.writeAction("Afisare medii descrescator");
        return afisare.toString();
    }

    public static List<Student> getStudentiDupaNumeCrescator(Catalog catalog) throws IOException {
        List<Student> listaReturn = new ArrayList<>();
        listaReturn.addAll(catalog.getStudenti());
        listaReturn.sort(StudentComparator.getComparatorNumeCrescator());
        audit.writeAction("Afisare studenti dupa nume alfabetic");
        return listaReturn;
    }

    public static List<Student> getStudentiDupaNumeDescrescator(Catalog catalog) throws IOException {
        List<Student> listaReturn = new ArrayList<>();
        listaReturn.addAll(catalog.getStudenti());
        listaReturn.sort(StudentComparator.getComparatorNumeDescrescator());
        audit.writeAction("Adaugare studenti dupa nume de la Z la A");
        return listaReturn;
    }

    public static void afisareStudentiGrupa(Catalog catalog, int grupa) throws IOException {
        audit.writeAction("Afisare studenti dupa grupa");
        System.out.println(catalog.getStudenti()
                .stream()
                .filter(student -> student.getGrupa() == grupa).collect(Collectors.toList()));
    }

}
