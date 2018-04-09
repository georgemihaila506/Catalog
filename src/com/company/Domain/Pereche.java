package com.company.Domain;

import java.io.Serializable;

public class Pereche implements  Serializable, Comparable <Pereche>  {
    private String idStudent;
    private Integer nrTema;

    public Pereche(String idStudent, Integer nrTema) {
        this.idStudent = idStudent;
        this.nrTema = nrTema;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getNrTema() {
        return nrTema;
    }

    public void setNrTema(Integer nrTema) {
        this.nrTema = nrTema;
    }
    public String getIdStudent() {
        return idStudent;
    }



    @Override
    public String toString() {
        return "Pereche{" +
                "idStudent='" + idStudent + '\'' +
                ", nrTema=" + nrTema +
                '}';
    }

    @Override
    public int compareTo(Pereche o1) {
        if(this.getIdStudent().compareTo(o1.getIdStudent())==0)
            return this.getNrTema().compareTo(o1.getNrTema());
        return getIdStudent().compareTo(o1.getIdStudent());
    }

}
