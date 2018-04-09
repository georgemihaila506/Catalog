package com.company.Services;

import com.company.Domain.Student;
import com.company.Repository.Repository;
import com.company.Utils.ListEvent;
import com.company.Utils.ListEventType;
import com.company.Utils.ObservableP;
import com.company.Utils.ObserverP;
import com.company.Validatoare.ValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentServices implements Services<Student,String>,ObservableP<Student> {
    private Repository<Student, String> repoStudent;
   // ArrayList<Observer<Student>> studentObservers = new ArrayList<>();
    protected List<ObserverP<Student>> observers=new ArrayList<>();

    public StudentServices(Repository<Student, String> repoStudent) {
        this.repoStudent = repoStudent;
    }


    @Override
    public Student add(Student element) throws ValidationException, IOException, ClassNotFoundException {
        Student s=repoStudent.save(element);
        if(s==null)
        {
           // ListEvent<Student> ev=createEvent(ListEventType.ADD,s,repoStudent.get_all());
            notifyObservers();
        }
        return s;
    }

    @Override
    public void updateOne(Student element) throws IOException, ClassNotFoundException {
        repoStudent.update(element);
        //ListEvent<Student> ev=createEvent(ListEventType.UPDATE,element,repoStudent.get_all());
        notifyObservers();
    }

    @Override
    public Student deleteOne(String id) throws IOException, ClassNotFoundException {
        Student s=repoStudent.delete(id);
        if(s!=null)
        {
            //ListEvent<Student> ev=createEvent(ListEventType.REMOVE,s,repoStudent.get_all());
            notifyObservers();
        }
        return s;
    }

    public Student findOne(String id) throws IOException, ClassNotFoundException {
        return repoStudent.findOne(id);
    }

    @Override
    public List<Student> getAll() throws IOException, ClassNotFoundException {
        return repoStudent.get_all();
    }

    public Student getStudent(String id) throws IOException, ClassNotFoundException {
        return repoStudent.findOne(id);
    }

//    @Override
//    public void addObserver(Observer<Student> o) {
//        studentObservers.add(o);
//    }
//
//    @Override
//    public void removeObserver(Observer<Student> o) {
//        studentObservers.remove(o);
//    }
//
//    @Override
//    public void notifyObservers(ListEvent<Student> event) {
//        studentObservers.forEach(x -> x.notifyEvent(event));
//    }

    private <E> ListEvent<E> createEvent(ListEventType type, final E elem, final Iterable<E> l) {
        return new ListEvent<E>(type) {
            @Override
            public Iterable<E> getList() {
                return l;
            }

            @Override
            public E getElement() {
                return elem;
            }
        };
    }
    public List<Student> getAllStudents()
    {
        try {
            return StreamSupport.stream(repoStudent.get_all().spliterator(), false)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public void addObserver(ObserverP<Student> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverP<Student> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(ObserverP<Student> o:observers)
        {
            o.update(this);
        }
    }
}