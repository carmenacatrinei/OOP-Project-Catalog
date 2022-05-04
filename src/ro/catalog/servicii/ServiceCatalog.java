package ro.catalog.servicii;

import ro.catalog.Catalog;
import ro.catalog.Materie;
import ro.catalog.exceptii.NotaInvalida;
import ro.catalog.utilizatori.Student;
import ro.catalog.utilizatori.StudentComparator;

import java.util.*;
import java.util.stream.Collectors;

//import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ServiceCatalog implements ServiceCatalogInterface{

    public static Catalog creazaCatalog(int grupa){
        return new Catalog(grupa);
    }

    static public Materie creareMaterie(String denumire, int nrCredite){
        return new Materie(denumire, nrCredite);
    }

    public static void adaugaStudentInCatalog(Catalog catalog, Student student){
        catalog.adaugareStudent(student);
    }

    public static void adaugaMaterieInCatalog(Catalog catalog, Materie materie){
        catalog.adaugareMaterie(materie);
    }

    /**
     * Metoda ce initializeaza catalogul dupa ce sunt adaugati toti studentii si toate materiile
     *
     * @param catalog Catalogul ce trebuile initializat
     */
    public static void initializareCatalog(Catalog catalog){
        List<List<Integer>> catalogNote = new ArrayList<>();
        for(int i = 0; i < catalog.getStudentiSize(); i++){
            List<Integer> arrayNoteStudent = new ArrayList<>();
            for (int j =0; j < catalog.getMateriiSize(); j++) {
                arrayNoteStudent.add(1);
            }
            catalogNote.add(arrayNoteStudent);
        }
        catalog.setNote(catalogNote);
    }

    static public void adaugareNotaStudent(Catalog catalog, Student student, int nota, Materie materie){
        try {
            if(nota<0 || nota>10) {
                throw new NotaInvalida("Nota nu poate fi negativa sau mai mare decat 10!");
            }
            catalog.setNota(nota, student, materie);
        }
        catch (NotaInvalida e) {
            System.out.println(e.getMessage());
        }
    }

    static public String afisareCatalog(Catalog catalog) {
        return catalog.afisareCatalog();
    }

    static public String afisareNoteStudent(Catalog catalog, Student student) {
        return catalog.afisareNoteStudent(student);
    }

    static public String afisareMediiDesc(Catalog catalog){
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
        return afisare.toString();
    }

    public static List<Student> getStudentiDupaNumeCrescator(Catalog catalog){
        List<Student> listaReturn = new ArrayList<>();
        listaReturn.addAll(catalog.getStudenti());
        listaReturn.sort(StudentComparator.getComparatorNumeCrescator());

        return listaReturn;
    }

    public static List<Student> getStudentiDupaNumeDescrescator(Catalog catalog){
        List<Student> listaReturn = new ArrayList<>();
        listaReturn.addAll(catalog.getStudenti());
        listaReturn.sort(StudentComparator.getComparatorNumeDescrescator());

        return listaReturn;
    }

    public static void afisareStudentiGrupa(Catalog catalog, int grupa){
        System.out.println(catalog.getStudenti()
                .stream()
                .filter(student -> student.getGrupa() == grupa).collect(Collectors.toList()));
    }

}
