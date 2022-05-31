package ro.catalog.servicii;

import java.io.*;
import java.time.LocalDateTime;

public class Audit {
    public static Audit auditService;

    private Audit() {}

    public static Audit getAudit() {
        if (auditService == null)
            auditService = new Audit();
        return auditService;
    }

    public void writeAction(String action){
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\carme\\Desktop\\Anul 2 de chin\\Sem2\\ProiectPaoCatalogGit\\Proiect-PAO-Catalog\\data\\data.csv", true)) {
            File file = new File("C:\\Users\\carme\\Desktop\\Anul 2 de chin\\Sem2\\ProiectPaoCatalogGit\\Proiect-PAO-Catalog\\data\\data.csv");

            if (file.length() == 0) {
                fileWriter.append("NumeActiune").append(",").append("Timestamp").append("\n");
            }

            LocalDateTime date = LocalDateTime.now();
            fileWriter.append(action).append(",").append(String.valueOf(date)).append("\n");
            fileWriter.flush();

        } catch (IOException e) {
            System.out.println("\n\tException: " + e.getMessage());
        }
    }

}
