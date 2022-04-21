package ro.catalog.servicii;

import ro.catalog.Catalog;
import ro.catalog.Materie;
import ro.catalog.utilizatori.Student;
import ro.catalog.utilizatori.StudentComparator;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class ServiceCatalog implements ServiceCatalogInterface{

    public static Catalog creazaCatalog(int grupa){
        return new Catalog(grupa);
    }

    static public Materie creazaMaterie(String denumire, int nrCredite){
        return new Materie(denumire, nrCredite);
    }

    public static void adaugaStudentInCatalog(Catalog catalog, Student student){
        adaugareStudent(catalog, student);
    }

    public static void adaugaMaterieInCatalog(Catalog catalog, Materie materie){
        adaugareMaterie(catalog, materie);
    }

    /**
     * Metoda ce initializeaza catalogul dupa ce sunt adaugati toti studentii si toate materiile
     *
     * @param catalog Catalogul ce trebuile initializat
     */
    public static void initializareCatalog(Catalog catalog){
        List<List<Integer>> catalogNote = new ArrayList<>();
        for(int i = 0; i < getStudentiSize(catalog); i++){
            List<Integer> arrayNoteStudent = new ArrayList<>();
            for (int j =0; j < getMateriiSize(catalog); j++) {
                arrayNoteStudent.add(1);
            }
            catalogNote.add(arrayNoteStudent);
        }
        catalog.setNote(catalogNote);
    }

    static public void adaugareNotaStudent(Catalog catalog, Student student, int nota, Materie materie){
        catalog.setNota(nota, student, materie);
    }

    static public String afisareCatalog(Catalog catalog) {
        return catalog.afisareCatalog();
    }

    static public String afisareMediiDesc(Catalog catalog){
        Map<String, Float> medii = new HashMap<>();
        List<List<Integer>> note = catalog.getNote();
        for(int i = 0; i < getStudentiSize(catalog); i++){
            int sumaNote = 0;
            List<Integer> noteStudent = note.get(i);
            for (Integer nota: noteStudent) {
                sumaNote += nota;
            }
            float medie;
            medie = (float) sumaNote / getMateriiSize(catalog);
            medii.put(getStudentFromIndex(catalog, i).getNrMatricol(), medie);

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

    static public void adaugareMaterie(Catalog catalog, Materie materie){
        catalog.getMaterii().add(materie);
    }

    static public void adaugareStudent(Catalog catalog, Student student){
        if(student.getGrupa() == catalog.getGrupa()) {
            catalog.getStudenti().add(student);
        }
    }

    static public String afisareNoteStudent(Catalog catalog, Student student){
        StringBuilder buffer = new StringBuilder();
        List<Integer> noteStudent = catalog.getNote().get(catalog.getStudenti().indexOf(student));
        buffer.append(student.getNume()).append(" ").append(student.getPrenume()).append("\n");
        for (int i = 0; i < getMateriiSize(catalog); i++) {
            buffer.append(catalog.getMaterii().get(i).getDenumire()).append(": ").append(noteStudent.get(i)).append("\n");
        }
        return buffer.toString();
    }

    static private int getMateriiSize(Catalog catalog){
        return catalog.getMaterii().size();
    }

    static public int getStudentiSize(Catalog catalog){

        return catalog.getStudenti().size();
    }

    /*    public int getStudentIndex(Student student){
            return studenti.indexOf(student);
        }*/
    static public Student getStudentFromIndex(Catalog catalog, int index){
        return catalog.getStudenti().get(index);
    }

}
