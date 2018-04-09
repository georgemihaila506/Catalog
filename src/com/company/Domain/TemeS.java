package com.company.Domain;

import java.io.Serializable;

public class TemeS implements Serializable {
    private Integer numar;
    private String descriere;
    private int deadline;

    public TemeS(Integer numar, String descriere, int deadline) {
        this.numar = numar;
        this.descriere = descriere;
        this.deadline = deadline;
    }
    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
}
