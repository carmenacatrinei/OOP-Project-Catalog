package ro.catalog.servicii;

import ro.catalog.Catalog;
import ro.catalog.Materie;
import ro.catalog.utilizatori.Student;

public interface ServiceCatalogInterface {
    static void adaugareNotaStudent(Catalog catalog, Student student, int nota, Materie materie){}
    static String afisareCatalog(Catalog catalog){
        return null;
    }
    static String afisareNoteStudent(Catalog catalog, Student student){
        return null;
    }

}
