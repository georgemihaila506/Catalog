package com.company.Services;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Repository.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterServices {
    private Repository<Student,String> repoS;
    private Repository<Teme,Integer> repoT;
    private Repository<Nota,Pereche> repoN;

    public FilterServices(Repository<Student, String> repoS, Repository<Teme, Integer> repoT, Repository<Nota, Pereche> repoN) {
        this.repoS = repoS;
        this.repoT = repoT;
        this.repoN = repoN;
    }
    public static <E> List<E> filterAndSorter(List<E> lista, Predicate<E> pred, Comparator<E> c)
    {
        List <E> listaF=lista.stream().filter(pred).sorted(c).collect(Collectors.toList());
        return listaF;
    }
    public List FilterStudentByGrupa(int grupa) throws IOException, ClassNotFoundException
    {

        List<Student> studentList= (List<com.company.Domain.Student>) repoS.get_all();
        return filterAndSorter(studentList, x ->x.getGrupa()==grupa,(s1,s2)->{return s1.getNume().compareTo(s2.getNume());});
    }
    public List FilterStudentByTurore(String tutore) throws IOException, ClassNotFoundException {
        List<Student> studentList= (List<com.company.Domain.Student>) repoS.get_all();
        return filterAndSorter(studentList, x -> x.getCadruIndrumator().equals(tutore),(s1, s2)->{return s1.getIdStudent().compareTo(s2.getIdStudent());});

    }
    public List FilterStudentByTutoreAndGroup(int grupa,String tutore) throws IOException, ClassNotFoundException {
        List<Student> studentList= (List<com.company.Domain.Student>) repoS.get_all();
        return filterAndSorter(studentList, x -> x.getCadruIndrumator().equals(tutore)&&x.getGrupa()==grupa,(s1, s2)->{return s1.getNume().compareTo(s2.getNume());});
    }
    public List FilterTemeByDescription(String description) throws IOException, ClassNotFoundException {
        List<Teme> temeList= (List<Teme>) repoT.get_all();
        return filterAndSorter(temeList,x->x.getDescriere().equals(description),(t1,t2)->{return t1.getNumar().compareTo(t2.getNumar());});
    }
    public List FilterTemeByDeadline(int deadline) throws IOException, ClassNotFoundException {
        List<Teme> temeList= (List<Teme>) repoT.get_all();
        return filterAndSorter(temeList,x->x.getDeadline()==deadline,(t1,t2)->{return t1.getNumar().compareTo(t2.getNumar());});
    }
    public List FilterTemeByDeadlineAndDesciption(String description,int deadline) throws IOException, ClassNotFoundException {
        List<Teme> temeList= (List<Teme>) repoT.get_all();
        return filterAndSorter(temeList,x->x.getDescriere().equals(description)&& x.getDeadline()==deadline,(t1,t2)->{return t1.getNumar().compareTo(t2.getNumar());});
    }
    public List FilterNoteByStudents(String id) throws IOException, ClassNotFoundException {
        List<Nota> noteList= (List<Nota>) repoN.get_all();
        return filterAndSorter(noteList,x->x.getIdStudent().equals(id),(o1,o2)->{return o1.getNrTemei().compareTo(o2.getNrTemei());});
    }
    public List FilterNoteByHomework(Integer nrTema) throws IOException, ClassNotFoundException {
        List<Nota> noteList= (List<Nota>) repoN.get_all();
        return filterAndSorter(noteList,x->x.getNrTemei().equals(nrTema),(o1,o2)->{return o1.getIdStudent().compareTo(o2.getIdStudent());});
    }
    public List FilterNoteByHomeworkAndStudents(String id,Integer nrTema) throws IOException, ClassNotFoundException {

        List<Nota> noteList= (List<Nota>) repoN.get_all();
        return filterAndSorter(noteList,x->x.getIdStudent().equals(id)&& x.getNrTemei().equals(nrTema),(o1,o2)->{return o1.getNrTemei().compareTo(o2.getNrTemei());});
    }
}
