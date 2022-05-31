package ro.catalog.servicii;

import ro.catalog.entitati.Catalog;
import ro.catalog.entitati.Materie;
import ro.catalog.entitati.Utilizator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CitireDinFisier {
    public static CitireDinFisier citire;

    private CitireDinFisier() {}

    public static CitireDinFisier getCitire() {
        if (citire == null)
            citire = new CitireDinFisier();
        return citire;
    }

    public <T extends Utilizator> List<T> citireUtilizatori(String fisier, Class<T> cls){
        List<T> utilizatori = new ArrayList<>();
        return utilizatori;
    }

    public static List<Materie> citireMaterii(){
        List<Materie> materii = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("C:\\Users\\carme\\Desktop\\Anul 2 de chin\\Sem2\\ProiectPaoCatalogGit\\Proiect-PAO-Catalog\\data\\materii.csv"));
            String line = buffer.readLine();

            while (line != null) {
                String[] arg = line.split(",");
                String denumire = arg[0];
                int nrCredite =  Integer.parseInt(arg[1]);
                Materie materie = new Materie(denumire, nrCredite);
                materii.add(materie);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return materii;
    }

    public List<Catalog> citireCatalog(){
        List<Catalog> catalogs = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("C:\\Users\\carme\\Desktop\\Anul 2 de chin\\Sem2\\ProiectPaoCatalogGit\\Proiect-PAO-Catalog\\data\\catalog.csv"));
            String line = buffer.readLine();

            while (line != null) {
                String[] arg = line.split(",");
                int grupa =  Integer.parseInt(arg[0]);
                Catalog catalog = new Catalog(grupa);
                catalogs.add(catalog);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return catalogs;
    }
}
