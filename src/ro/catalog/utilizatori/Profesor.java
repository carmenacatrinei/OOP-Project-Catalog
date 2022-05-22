package ro.catalog.utilizatori;

import ro.catalog.Materie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Profesor extends Utilizator {
    private List<Materie> materiiPredate = new ArrayList<>();

    public Profesor(String nume, String prenume) {
        super(nume, prenume);
    }

    public Profesor(String nume, String prenume, List<Materie> materiiPredate) {
        super(nume, prenume);
        this.materiiPredate = materiiPredate;
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

    public List<Materie> getMateriiPredate() {
        return materiiPredate;
    }

    public void setMateriiPredate(List<Materie> materiiPredate) {
        this.materiiPredate = materiiPredate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;
        Profesor profesor = (Profesor) o;
        return getNume().equals(profesor.getNume()) && getPrenume().equals(profesor.getPrenume()) && Objects.equals(getMateriiPredate(), profesor.getMateriiPredate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNume(), getPrenume(), getMateriiPredate());
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", materiiPredate=" + materiiPredate +
                '}';
    }


}
