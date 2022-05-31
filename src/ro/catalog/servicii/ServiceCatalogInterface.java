package ro.catalog.servicii;

import ro.catalog.entitati.Catalog;
import ro.catalog.entitati.Materie;
import ro.catalog.entitati.Student;

public interface ServiceCatalogInterface {
    static void adaugareNotaStudent(Catalog catalog, Student student, int nota, Materie materie){}
    static String afisareCatalog(Catalog catalog){
        return null;
    }
    static String afisareNoteStudent(Catalog catalog, Student student){
        return null;
    }

}
