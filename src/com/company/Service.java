package com.company;

import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Repository.Repository;
import com.company.Validatoare.ValidationException;

import java.io.IOException;

public class Service {
    private Repository<Teme,Integer> repoTeme;
    private Repository<Student,String> repoStudent;

    public Service(Repository<Teme, Integer> repoTeme, Repository<Student, String> repoStudent) {
        this.repoTeme = repoTeme;
        this.repoStudent = repoStudent;
    }
    public Student addStudent(Student student) throws ValidationException,IOException,ClassNotFoundException {
        return repoStudent.save(student);
    }
    public Student findOne(String id) throws IOException, ClassNotFoundException {
        return repoStudent.findOne(id);
    }
    public Iterable<Student> get_allStudents() throws IOException, ClassNotFoundException {
        return repoStudent.get_all();
    }

    public Teme addTema(Teme tema) throws ValidationException,IOException,ClassNotFoundException {
        return repoTeme.save(tema);
    }
    public Teme findOne(Integer id) throws IOException, ClassNotFoundException {
        return repoTeme.findOne(id);
    }
    public Iterable<Teme> get_allH() throws IOException, ClassNotFoundException {
        return repoTeme.get_all();
    }
    public void modificareDeadLine(Integer numarT,int saptamataC,int deadlineN) throws IOException, ClassNotFoundException {
        Teme obiect=repoTeme.findOne(numarT);
        if(obiect!=null)
        {
            if(obiect.getDeadline()>saptamataC)
            {
                obiect.setDeadline(deadlineN);
                repoTeme.update(obiect);
            }
        }
    }
    public void updateStudent(Student student) throws IOException, ClassNotFoundException {
        repoStudent.update(student);
    }
    public Student deleteStudent(String id) throws IOException, ClassNotFoundException {
        return repoStudent.delete(id);
    }
    public Student getStudent(String id) throws IOException, ClassNotFoundException {
        return repoStudent.findOne(id);
    }





}
