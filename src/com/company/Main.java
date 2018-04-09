package com.company;

import com.company.CommandUserInterface.Consola;
import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Repository.RepoFileNote;
import com.company.Repository.RepoInFile;
import com.company.Services.*;
import com.company.Validatoare.NotaValidator;
import com.company.Validatoare.StudentValidator;
import com.company.Validatoare.TemeValidator;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //SendMailSSL mailSencer=new SendMailSSL("Test");
        //RepoInMemory<Student,String> repo=new RepoInMemory<>(new StudentValidator());
        //RepoInMemory<Teme,Integer> repo2=new RepoInMemory<>(new TemeValidator());
        RepoInFile<Student,String> repo;
        RepoInFile<Teme,Integer> repo2;
        RepoInFile<Nota,Pereche> repo3;
        repo=new RepoInFile<>("file1.txt",new StudentValidator());
        repo2=new RepoInFile<>("file2.txt",new TemeValidator());
        repo3=new RepoInFile<>("file3.txt",new NotaValidator());
        RepoFileNote repo4=new RepoFileNote("./src/com/company/NoteS.txt",new NotaValidator());
        StudentServices ctrlS=new StudentServices(repo);
        TemeServices ctrlT=new TemeServices(repo2);
        NoteServices ctrlN=new NoteServices(repo4,repo2);
        FilterServices ctrlF=new FilterServices(repo,repo2,repo4);
        StaticticsServices ctrlSta=new StaticticsServices(repo,repo2,repo4);
        //UI ui=new UI(ctrlT,ctrlS,ctrlN);
        //ui.run();
        //ui.teste();
        Consola ui=new Consola(ctrlS,ctrlT,ctrlN,ctrlF,ctrlSta);
        ui.runMenu();

        //        TreeMap <Pereche , Nota> mp = new TreeMap<>();
//
//        mp.put(new Pereche("ada",2),new Nota(new Pereche("ada",2),9));
//        mp.put(new Pereche("ada",2),new Nota(new Pereche("ada",2),8));
//
//        mp.entrySet().forEach(System.out::println);



    }


}
