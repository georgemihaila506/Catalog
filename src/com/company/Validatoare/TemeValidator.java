package com.company.Validatoare;

import com.company.Domain.Teme;



public  class TemeValidator implements Validator<Teme> {

    @Override
    public void validate(Teme obj) throws ValidationException {
        String err="";
        if(obj.getNumar()<0)
            err=err.concat("Numar incorect!\n");
        if(obj.getDeadline()>14 || obj.getDeadline()<1)
            err=err.concat("Deadline incorect!\n");
        if(obj.getDescriere()=="")
            err=err.concat("Descriere vida!\n");
        if(err.length()!=0)
            throw new ValidationException(err);

    }
}
