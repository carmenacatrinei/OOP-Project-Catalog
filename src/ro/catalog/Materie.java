package ro.catalog;

import java.util.Objects;

public class Materie {
    private int id;
    private String denumire;
    private int nrCredite;

    public Materie(String denumire, int nrCredite) {
        this.denumire = denumire;
        this.nrCredite = nrCredite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNrCredite() {
        return nrCredite;
    }

    public void setNrCredite(int nrCredite) {
        this.nrCredite = nrCredite;
    }

    @Override
    public String toString() {
        return "Materie{" +
                "denumire='" + denumire + '\'' +
                ", nrCredite=" + nrCredite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materie)) return false;
        Materie materie = (Materie) o;
        return getNrCredite() == materie.getNrCredite() && getDenumire().equals(materie.getDenumire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDenumire(), getNrCredite());
    }
}
