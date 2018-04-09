package com.company.Validatoare;

import com.company.Domain.Nota;



public class NotaValidator implements Validator<Nota> {
    @Override
    public void validate(Nota obj) throws ValidationException {
        String err="";
        if(obj.getIdStudent()=="")
            err=err.concat("IDSTUDENT e vid!");

        if(obj.getNrTemei()<=0)
            err=err.concat("Numarul temei e incorect\n");
        if(obj.getValoare()<0 || obj.getValoare()>10 )
            err=err.concat("Valoarea e incorecta!\n");
        if(err.length()!=0)
            throw new ValidationException(err);
    }
}
