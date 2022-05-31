package ro.catalog.entitati;

abstract public class Utilizator {
    private int id;
    protected String nume;
    protected String prenume;

    public Utilizator(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
