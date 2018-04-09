package com.company.Services;

import com.company.Domain.Teme;
import com.company.Repository.Repository;
import com.company.Utils.ObservableP;
import com.company.Utils.ObserverP;
import com.company.Validatoare.ValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.company.Utils.SendMailSSL;

public class TemeServices implements Services<Teme,Integer>,ObservableP<Teme> {
    private Repository<Teme,Integer> repoTeme;
    protected List<ObserverP<Teme>> observers=new ArrayList<>();
    public TemeServices(Repository<Teme, Integer> repoTeme) {
        this.repoTeme = repoTeme;
    }

    /**
     *
     * @param tema de tip Teme care urmeaza a fi adaugat
     * @return null daca exista si elemetnul in caz ca a fost adaugat
     * @throws ValidationException
     */
    @Override
    public Teme add(Teme element) throws ValidationException,IOException,ClassNotFoundException {
        Teme t=repoTeme.save(element);
        String mail="A fost adauga o tema "+Integer.toString(element.getNumar());
        //SendMailSSL mailSSL=new SendMailSSL(mail);
        notifyObservers();
        return t;
    }

    @Override
    public void updateOne(Teme element) {

    }

    @Override
    public Teme deleteOne(Integer integer) throws IOException, ClassNotFoundException {
        Teme tema=repoTeme.delete(integer);
        notifyObservers();
        return tema;
    }

    /**
     *
     * @param id dupa care se cauta
     * @return null daca nu a fost gasit si elemtnul in caz contrat
     */
    public Teme findOne(Integer id) throws IOException, ClassNotFoundException {
        return repoTeme.findOne(id);
    }

    /**
     *
     * @return o lista cu elementele din map
     */
    @Override
    public List<Teme> getAll() throws IOException, ClassNotFoundException {
        //trimiteLaToti("./Student1.txt");
        return repoTeme.get_all();
    }



    /**
     *
     * @param numarT numarul temei
     * @param saptamataC saptamana curenta
     * @param deadlineN noul deadline
     * @throws ValidationException daca saptamana curenta e mai mare ca 14
     */
    public void modificareDeadLine(Integer numarT,int saptamataC,int deadlineN) throws ValidationException, IOException, ClassNotFoundException {
        if(saptamataC>14)
            throw new ValidationException("Saptamana e prea mare!");
        Teme obiect=repoTeme.findOne(numarT);
        if(obiect!=null)
        {
            if(obiect.getDeadline()>saptamataC)
            {
                obiect.setDeadline(deadlineN);
                repoTeme.update(obiect);
            }
        }
        notifyObservers();
        //String mail="A fost modificat un deadline la o tema "+Integer.toString(numarT);
        //SendMailSSL mailSSL=new SendMailSSL(mail);
    }


    @Override
    public void addObserver(ObserverP<Teme> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverP<Teme> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(ObserverP<Teme> o:observers)
            o.update(this);
    }
}
