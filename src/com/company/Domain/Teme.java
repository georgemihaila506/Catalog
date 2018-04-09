package com.company.Domain;

public class Teme extends TemeS implements HasID<Integer> {


    public Teme(Integer numar, String descriere, int deadline) {
        super(numar,descriere,deadline);
    }

    @Override
    public String toString() {
        return "Teme{" +
                "numar=" + getNumar() +
                ", descriere='" + getDescriere() + '\'' +
                ", deadline=" + getDeadline() +
                '}';
    }



    @Override
    public Integer getId() {
        return super.getNumar();
    }

    @Override
    public void setId(Integer integer) {

        super.setNumar(integer);
    }
}
