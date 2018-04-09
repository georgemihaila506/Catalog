package com.company.Repository;

import com.company.Domain.HasID;
import com.company.Validatoare.ValidationException;
import com.company.Validatoare.Validator;

import java.util.ArrayList;
import java.util.List;

public class RepoInMemory<E extends HasID<ID>,ID> extends AbstractRepository<E,ID> {

    public RepoInMemory(Validator<E> validator) {
        super(validator);
    }

    @Override
    public long size() {
        return all.size();
    }

    /**
     *
     * @param element care umreaza a fi inserat
     * @return elementul daca exista
     * @throws javax.xml.bind.ValidationException se valideaza
     */
    @Override
    public E save(E element) throws ValidationException {
        validator.validate(element);
        if(all.containsKey(element.getId()))
            return all.get(element.getId());
        all.put(element.getId(),element);
        return null;
    }

    /**
     *
     * @param id dupa care se sterge
     * @return elemtnul stres
     */
    @Override
    public E delete(ID id) {
        return all.remove(id);

    }

    /**
     *
     * @param element care urmeaza a fi updatat
     */
    @Override
    public void update(E element)
    {
        all.replace(element.getId(),element);
    }

    /**
     * Cauta in map un element
     * @param id
     * @return elemtnul daca a fost gasit
     */
    @Override
    public E findOne(ID id) {
        return all.get(id);
    }
    /**
     *
     * @return toate elementele din map intr o lista
     */
    @Override
    public List<E> get_all() {
        List<E> lista = new ArrayList<E>(all.values());
        return lista;
    }

}
