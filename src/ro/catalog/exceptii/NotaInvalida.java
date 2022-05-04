package ro.catalog.exceptii;

public class NotaInvalida extends Exception {
    public NotaInvalida() {
    }

    public NotaInvalida(String mesaj){
        super(mesaj);
    }
}
