package ro.catalog.servicii;

import jdk.jshell.execution.Util;
import ro.catalog.Catalog;
import ro.catalog.Materie;
import ro.catalog.utilizatori.Profesor;
import ro.catalog.utilizatori.Student;
import ro.catalog.utilizatori.Utilizator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScriereInFisier {
    public static ScriereInFisier scriere;

    private ScriereInFisier() {}

    public static ScriereInFisier getScriere() {
        if (scriere == null)
            scriere = new ScriereInFisier();
        return scriere;
    }

    public <T extends Utilizator> void scriereUser(Utilizator user){}

    public void scriereMaterie(Materie materie){
        try (FileWriter fileWriter = new FileWriter("data/materii.csv", true)) {

            fileWriter.append("\n")
                    .append(materie.getDenumire())
                    .append(",")
                    .append((char) materie.getNrCredite());

            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void scriereCatalog(Catalog catalog){
        try (FileWriter fileWriter = new FileWriter("data/catalog.csv", true)) {

            fileWriter.append("\n")
                    .append((char) catalog.getGrupa());

            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
