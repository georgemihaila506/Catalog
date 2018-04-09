package com.company.Validatoare;

import com.company.Domain.Student;



public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student obj) throws ValidationException {
        String err="";
        if(obj.getNume()=="")
            err=err.concat("Numele e vid!");

        if(obj.getIdStudent()=="")
            err=err.concat("Id-ul e vid!\n");
        if(obj.getGrupa()<200 || obj.getGrupa()>1000)
            err=err.concat("Grupa e incorecta!\n");
        if(err.length()!=0)
            throw new ValidationException(err);
}}
