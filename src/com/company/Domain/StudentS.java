package com.company.Domain;

import java.io.Serializable;

public class StudentS implements  Serializable {


    private String idStudent;
    private String nume;
    private int grupa;
    private String email;
    private String cadruIndrumator;

    /**
     *
     * @param idStudent de tip string
     * @param nume de tip string
     * @param grupa de tip int
     * @param email de tip string
     * @param cadruIndrumator de tip string
     */
    public StudentS(String idStudent, String nume, int grupa, String email, String cadruIndrumator) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email = email;
        this.cadruIndrumator = cadruIndrumator;
    }
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCadruIndrumator(String cadruIndrumator) {
        this.cadruIndrumator = cadruIndrumator;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public String getNume() {
        return nume;
    }

    public int getGrupa() {
        return grupa;
    }

    public String getEmail() {
        return email;
    }

    public String getCadruIndrumator() {
        return cadruIndrumator;
    }
}
