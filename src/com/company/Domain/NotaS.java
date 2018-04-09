package com.company.Domain;

import java.io.Serializable;

public class NotaS implements Serializable{
    private String idStudent;
    private Integer nrTemei;
    protected Pereche pereche;
    private int valoare;

    public NotaS(Pereche el, int valoare) {
        this.pereche=el;
        this.valoare = valoare;
    }

    @Override
    public String toString() {
        return "NotaS{" +
                "idStudent='" + pereche.getIdStudent() + '\'' +
                ", nrTemei=" + pereche.getNrTema() +
                ", valoare=" + valoare +
                '}';
    }

    public String getIdStudent() {
        return pereche.getIdStudent();
    }
    public Pereche getKey()
    {
        return pereche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotaS notaS = (NotaS) o;

        if (!getIdStudent().equals(notaS.getIdStudent())) return false;
        if (!getNrTemei().equals(notaS.getNrTemei())) return false;
      //  return pereche.equals(notaS.pereche);
        return true;
    }

    @Override
    public int hashCode() {
        int result = idStudent.hashCode();
        result = 31 * result + nrTemei.hashCode();
        result = 31 * result + pereche.hashCode();
        result = 31 * result + valoare;
        return result;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getNrTemei() {
        return pereche.getNrTema();
    }

    public void setNrTemei(Integer nrTemei) {
        this.nrTemei = nrTemei;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }
}
