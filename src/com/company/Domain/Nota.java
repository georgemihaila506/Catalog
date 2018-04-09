package com.company.Domain;


public class Nota  extends NotaS implements HasID<Pereche> {

    public Nota(Pereche pereche , int valoare)
    {
        super(pereche,valoare);
    }

    @Override
    public Pereche getId() {

            return getKey();
    }


    @Override
    public void setId(Pereche s) {
    }

}
