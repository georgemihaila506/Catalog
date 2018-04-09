package com.company.Repository;

import com.company.Domain.*;
import com.company.Validatoare.*;

import java.io.*;
import java.util.*;

public class RepoInFile<E extends HasID<ID>,ID> extends AbstractRepository<E,ID> {


    private String filename;
    public RepoInFile(String filename, Validator<E> validator) {
        super(validator);
        this.filename=filename;
    }

    @Override
    public long size() {
        return all.size();
    }

    /**
     * @param element care umreaza a fi inserat
     * @return elementul daca exista
     * @throws javax.xml.bind.ValidationException se valideaza
     */
    @Override
    public E save(E element) throws ValidationException, IOException, ClassNotFoundException {


        validator.validate(element);
        all = readObject();
        if (all.containsKey(element.getId()))
        {
            E el=all.get(element.getId());
            writeObject();
            return el;}
        all.put(element.getId(), element);
        writeObject();
        return null;
    }

    /**
     * @param id dupa care se sterge
     * @return elemtnul stres
     */
    @Override
    public E delete(ID id) throws IOException, ClassNotFoundException {
        all = readObject();
        E el = all.remove(id);
        writeObject();
        return el;

    }

    /**
     * @param element care urmeaza a fi updatat
     */
    @Override
    public void update(E element) throws IOException, ClassNotFoundException {
        all = readObject();
        all.replace(element.getId(), element);
        writeObject();
    }

    /**
     * Cauta in map un element
     *
     * @param id
     * @return elemtnul daca a fost gasit
     */
    @Override
    public E findOne(ID id) throws IOException, ClassNotFoundException {
        all = readObject();
        E el = all.get(id);
        writeObject();
        return el;
    }


    private void writeObject()
            throws IOException {
        FileOutputStream f= new FileOutputStream(filename);
        ObjectOutputStream streamO=new ObjectOutputStream(f);
        all.forEach((key, value) -> {
            try {
                if(value!=null)
                    streamO.writeObject(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        streamO.close();
        f.close();

    }

    private Map<ID, E> readObject() throws IOException, ClassNotFoundException {

        FileInputStream f=new FileInputStream(filename);
        ObjectInputStream streamI=new ObjectInputStream(f);
        all.clear();
        Map<ID, E> another = new TreeMap<>();
        while (true) {
            try {
                E obj = (E) streamI.readObject();
                if(obj==null)
                    System.out.println("IA");
                another.put(obj.getId(), obj);
            } catch (EOFException e) {
                streamI.close();
                f.close();
                return another;
            }
        }


    }

    /**
     * @return toate elementele din map intr o lista
     */
    @Override
    public List<E> get_all() throws IOException, ClassNotFoundException {
        all = readObject();
        Map<ID,E> all2=all;
        writeObject();
        List<E> lista = new ArrayList<E>(all2.values());
        return lista;
    }
}

