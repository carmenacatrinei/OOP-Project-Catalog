package ro.catalog.servicii;

import ro.catalog.entitati.Catalog;
import ro.catalog.entitati.Materie;
import ro.catalog.entitati.Utilizator;
import java.io.FileWriter;
import java.io.IOException;


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
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\carme\\Desktop\\Anul 2 de chin\\Sem2\\ProiectPaoCatalogGit\\Proiect-PAO-Catalog\\data\\materii.csv", true)) {

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
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\carme\\Desktop\\Anul 2 de chin\\Sem2\\ProiectPaoCatalogGit\\Proiect-PAO-Catalog\\data\\catalog.csv", true)) {

            fileWriter.append("\n")
                    .append((char) catalog.getGrupa());

            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
