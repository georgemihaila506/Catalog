package com.company.Services;

import com.company.Validatoare.ValidationException;

import java.io.IOException;
import java.util.List;

public interface Services<E,ID> {
    E add(E element) throws ValidationException,IOException,ClassNotFoundException;
    void updateOne(E element) throws IOException, ClassNotFoundException;
    E deleteOne(ID id) throws IOException, ClassNotFoundException;
    E findOne(ID id) throws IOException, ClassNotFoundException;
    List<E> getAll() throws IOException, ClassNotFoundException;
}
