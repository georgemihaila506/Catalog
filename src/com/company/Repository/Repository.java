package com.company.Repository;

import com.company.Validatoare.ValidationException;

import java.io.IOException;
import java.util.List;


public interface Repository<E,ID>  {
    long size();
    E save(E element) throws ValidationException, IOException,ClassNotFoundException;
    E delete(ID id) throws IOException, ClassNotFoundException;
    E findOne(ID id) throws IOException, ClassNotFoundException;
    void update(E element) throws IOException, ClassNotFoundException;
    List<E> get_all() throws IOException, ClassNotFoundException;

}
