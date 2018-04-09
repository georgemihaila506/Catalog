package com.company.Validatoare;


public interface Validator<E> {
    public void validate(E obj) throws ValidationException;
}
