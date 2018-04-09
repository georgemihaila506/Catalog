package com.company.CommandUserInterface;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Services.*;
import com.company.Validatoare.ValidationException;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Consola {
    private MenuCommand main_menu;
    private Scanner sc=new Scanner(System.in);
    private NoteServices ctrlN;
    private TemeServices ctrlT;
    private StudentServices ctrlS;
    private FilterServices ctrlF;
    private StaticticsServices ctrlSta;
    public Consola(StudentServices ctrlS,TemeServices ctrlT,NoteServices ctrlN,FilterServices ctrlF,StaticticsServices ctrlSta)
    {
        this.ctrlN=ctrlN;
        this.ctrlS=ctrlS;
        this.ctrlT=ctrlT;
        this.ctrlF=ctrlF;
        this.ctrlSta=ctrlSta;
    }
    public class ExitCommand implements Command
    {

        @Override
        public void execute() {
            System.exit(0);
        }
    }
    public class AddStudent implements Command
    {

        @Override
        public void execute() {
            String numarMatricol;
            String email;
            String diriginte;
            String nume;
            int grupa;

            System.out.println("Dati numarul matricol:");
            numarMatricol=sc.next();

            System.out.println("Dati nume:");
            nume=sc.next();

            System.out.println("Dati grupa:");
            grupa=sc.nextInt();

            System.out.println("Dati email:");
            email=sc.next();

            System.out.println("Dati nume indrumator:");
            diriginte=sc.next();

            try
            {
                ctrlS.add(new Student(numarMatricol,nume,grupa,email,diriginte));
            }
            catch (ValidationException e)
            {
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class DeleteStudent implements  Command
    {

        @Override
        public void execute() {
            String id;
            System.out.println("Numar matricol:");
            id=sc.next();
            try {
                ctrlS.deleteOne(id);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class UpdateStudent implements  Command
    {


        @Override
        public void execute() {
            String numarMatricol;
            String email;
            String diriginte;
            String nume;
            int grupa;

            System.out.println("Dati numarul matricol:");
            numarMatricol=sc.next();

            System.out.println("Dati nume:");
            nume=sc.next();

            System.out.println("Dati grupa:");
            grupa=sc.nextInt();

            System.out.println("Dati email:");
            email=sc.next();

            System.out.println("Dati nume indrumator:");
            diriginte=sc.next();
            try {
                ctrlS.updateOne(new Student(numarMatricol,nume,grupa,email,diriginte));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public class FindOne implements  Command
    {

        @Override
        public void execute() {
            String id;
            System.out.println("Numar matricol:");
            id=sc.next();
            try {
                System.out.println(ctrlS.getStudent(id));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public class ShowAllStudents implements  Command
    {

        @Override
        public void execute() {
            try {
                System.out.println(ctrlS.getAll());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class AddTema implements Command {

        @Override
        public void execute() {

            Integer numar;
            String descriere;
            int deadline;
            System.out.println("Numar tema:");
            numar = sc.nextInt();
            System.out.println("Descriere:");
            descriere = sc.next();
            System.out.println("Deadline:");
            deadline = sc.nextInt();
            try {
                ctrlT.add(new Teme(numar, descriere, deadline));
            } catch (ValidationException e) {
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class UpdateDeadline implements Command
    {

        @Override
        public void execute() {
            Integer numar;
            int saptamanaC;
            int newDeadline;
            System.out.println("Dati numarul temei:");
            numar=sc.nextInt();
            System.out.println("Dati saptamana curenta:");
            saptamanaC=sc.nextInt();
            System.out.println("Dati noul deadline:");
            newDeadline=sc.nextInt();
            try {
                ctrlT.modificareDeadLine(numar,saptamanaC,newDeadline);
            } catch (ValidationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public class ShowAllT implements Command
    {

        @Override
        public void execute() {
            try {
                System.out.println(ctrlT.getAll());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class AddNota implements Command
    {

        @Override
        public void execute() {
            String Id;
            Integer nrtema;
            int nota;
            System.out.println("Da id");
            Id=sc.next();
            System.out.println("Da nr tema");
            nrtema=sc.nextInt();
            System.out.println("Da nota");
            nota=sc.nextInt();
            Pereche el=new Pereche(Id,nrtema);
            Nota note=new Nota(el,nota);
            String observatii;
            int saptamanaC;
            System.out.println("Da saptamana");
            saptamanaC=sc.nextInt();
            System.out.println("Da observatii");
            observatii=sc.next();
            try {
                if(ctrlS.findOne(Id)!=null && ctrlT.findOne(nrtema)!=null)
                    try {
                        ctrlN.addNota(note, saptamanaC, observatii);
                    }
                     catch (ValidationException e) {
                        Alert message=new Alert(Alert.AlertType.ERROR);
                        message.setTitle("Mesaj eroare!");
                        message.setContentText("Nu a fost adaugat!");
                        message.showAndWait();
                    }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class UpdateNota implements Command
    {

        @Override
        public void execute() {
            String Id;
            Integer nrtema;
            int nota;
            System.out.println("Da id");
            Id=sc.next();
            System.out.println("Da nr tema");
            nrtema=sc.nextInt();
            System.out.println("Da nota");
            nota=sc.nextInt();
            Pereche el=new Pereche(Id,nrtema);
            Nota note=new Nota(el,nota);
            String observatii;
            int saptamanaC;
            System.out.println("Da saptamana");
            saptamanaC=sc.nextInt();
            System.out.println("Da observatii");
            observatii=sc.next();
            try {
                if(ctrlS.findOne(Id)!=null && ctrlT.findOne(nrtema)!=null) {
                    ctrlN.updateNota(note,saptamanaC,observatii);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class ShowAllNote implements Command
    {
        @Override
        public void execute() {
            try {
                System.out.println(ctrlN.getAll());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareStudentDupaGrupa implements Command
    {

        @Override
        public void execute() {
            int grupa;
            System.out.println("Grupa:");
            grupa=sc.nextInt();
            try {
                System.out.println(ctrlF.FilterStudentByGrupa(grupa));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareStudentDupaTutore implements Command
    {

        @Override
        public void execute() {
            String tutore;
            System.out.println("Tutore:");
            tutore=sc.next();
            try {
                System.out.println(ctrlF.FilterStudentByTurore(tutore));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareStudentAmbele implements Command
    {

        @Override
        public void execute() {
            String tutore;
            int grupa;
            System.out.println("Grupa:");
            grupa=sc.nextInt();
            System.out.println("Tutore:");
            tutore=sc.next();
            try {
                System.out.println(ctrlF.FilterStudentByTutoreAndGroup(grupa,tutore));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareTemeDeadline implements Command
    {

        @Override
        public void execute() {
            int deadline;
            System.out.println("Deadline:");
            deadline=sc.nextInt();
            try {
                System.out.println(ctrlF.FilterTemeByDeadline(deadline));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareTemeDescriere implements Command
    {
        @Override
        public void execute() {
            String desc;
            System.out.println("Descriere:");
            desc=sc.next();
            try {
                System.out.println(ctrlF.FilterTemeByDescription(desc));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareTemeAmbele implements Command
    {

        @Override
        public void execute() {
            String desc;
            int deadline;
            System.out.println("Descreire:");
            desc=sc.next();
            System.out.println("Deadline:");
            deadline=sc.nextInt();
            try {
                System.out.println(ctrlF.FilterTemeByDeadlineAndDesciption(desc,deadline));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareNoteNrTema implements Command
    {
        @Override
        public void execute() {
            Integer nrTema;
            System.out.println("Numar tema:");
            nrTema=sc.nextInt();
            try {
                System.out.println(ctrlF.FilterNoteByHomework(nrTema));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareNoteIdStudent implements Command
    {

        @Override
        public void execute() {
            String id;
            System.out.println("ID:");
            id=sc.next();
            try {
                System.out.println(ctrlF.FilterNoteByStudents(id));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class FiltrareNoteAmbele implements Command
    {
        @Override
        public void execute() {
            String id;
            Integer nrtema;
            System.out.println("ID:");
            id=sc.next();
            System.out.println("Numar tema:");
            nrtema=sc.nextInt();
            try {
                System.out.println(ctrlF.FilterNoteByHomeworkAndStudents(id,nrtema));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class MediaLaboratorS implements Command
    {

        @Override
        public void execute() {
            try {
                Map<String,Double> medii=ctrlSta.NotaLaboratorFiecareStudent();
                medii.forEach((String k,Double val)->
                        {
                            System.out.println("Stundetul cu id-ul "+k+" are media "+val);
                        }
                );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public  class CeaMaiGreaTema implements Command
    {

        @Override
        public void execute() {
            try {
                System.out.println(ctrlSta.CeaMaiGreaTema());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class StundetiAcceptati implements Command
    {

        @Override
        public void execute() {
            try {
                //System.out.println(ctrlSta.StudentiAcceptati());
                ctrlSta.StudentiAcceptati().forEach(s->
                {System.out.println(s);}
                );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class PredareLaTimp implements Command
    {

        @Override
        public void execute() {
            try {
                ctrlSta.PredareLaTimp().forEach(s->
                {
                    System.out.println(s);
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void createMenu()
    {
        main_menu=new MenuCommand("-----Meniu Principal-----");
        MenuCommand crudStudent= new MenuCommand("--Meniu Student--");
        crudStudent.addCommand("1.Add Student",new AddStudent());
        crudStudent.addCommand("2.Delete Student",new DeleteStudent());
        crudStudent.addCommand("3.Update Student",new UpdateStudent());
        crudStudent.addCommand("4.Find Student",new FindOne());
        crudStudent.addCommand("5.Show All",new ShowAllStudents());
        crudStudent.addCommand("0.Back",main_menu);

        MenuCommand crudTeme=new MenuCommand("--Meniu Teme--");
        crudTeme.addCommand("1.Add T",new AddTema());
        crudTeme.addCommand("2.Update Deadline",new UpdateDeadline());
        crudTeme.addCommand("3.Show All",new ShowAllT());
        crudTeme.addCommand("0.Back",main_menu);

        MenuCommand crudNote=new MenuCommand("--Meniu Note");
        crudNote.addCommand("1.Add N",new AddNota());
        crudNote.addCommand("2.Update N",new UpdateNota());
        crudNote.addCommand("3.Show All",new ShowAllNote());
        crudNote.addCommand("0.Back",main_menu);

        MenuCommand SfilterMenu=new MenuCommand("--Meniu Filtrari Studenti");
        SfilterMenu.addCommand("1.Filtrare dupa grupa",new FiltrareStudentDupaGrupa());
        SfilterMenu.addCommand("2.Filtrare dupa tutore",new FiltrareStudentDupaTutore());
        SfilterMenu.addCommand("3.Filtrare dupa grupa si tutore",new FiltrareStudentAmbele());
        SfilterMenu.addCommand("0.Back",main_menu);

        MenuCommand TfilterMenu=new MenuCommand("--Meniu Filtrari Teme");
        TfilterMenu.addCommand("1.Filtrare dupa deadline",new FiltrareTemeDeadline());
        TfilterMenu.addCommand("2.Filtrare dupa descriere",new FiltrareTemeDescriere());
        TfilterMenu.addCommand("3.Filtrare dupa descrier si deadline",new FiltrareTemeAmbele());
        TfilterMenu.addCommand("0.Back",main_menu);

        MenuCommand NfilterMenu=new MenuCommand("--Meniu Filtrari Note");
        NfilterMenu.addCommand("1.Filtrare dupa numarul temei",new FiltrareNoteNrTema());
        NfilterMenu.addCommand("2.Filtrare dupa id-ul studentului",new FiltrareNoteIdStudent());
        NfilterMenu.addCommand("3.Filtrare dupa numarul temei si id-ul studentului",new FiltrareNoteAmbele());
        NfilterMenu.addCommand("0.Back",main_menu);

        MenuCommand statistici=new MenuCommand("--Meniu Statistici");
        statistici.addCommand("1.Nota la laborator pentru fiecare student",new MediaLaboratorS());
        statistici.addCommand("2.Cea mai grea tema",new CeaMaiGreaTema());
        statistici.addCommand("3.Studenti acceptati",new StundetiAcceptati());
        statistici.addCommand("4.Stundeti care au predat la timp temele",new PredareLaTimp());
        statistici.addCommand("0.Back",main_menu);


        main_menu.addCommand("1.Meniu Student",crudStudent);
        main_menu.addCommand("2.Meniu Teme",crudTeme);
        main_menu.addCommand("3.Meniu Note",crudNote);
        main_menu.addCommand("4.Filtrare Student",SfilterMenu);
        main_menu.addCommand("5.Filtrare Teme",TfilterMenu);
        main_menu.addCommand("6.Filtrare Note",NfilterMenu);
        main_menu.addCommand("7.Statistici",statistici);
        main_menu.addCommand("0.Exit",new ExitCommand());
    }
    public void runMenu()
    {
        createMenu();
        MenuCommand current=main_menu;
        while (true)
        {
            System.out.println(main_menu.getMenuName());
            System.out.println("-------------------");
            current.execute();
            System.out.println("Introduceti optiunea:");
            int option=sc.nextInt();
            if(option>=0 && option<=current.getCommand().size())
            {
                Command selectedCommand=current.getCommand().get(option);
                if(selectedCommand instanceof MenuCommand)
                    current=(MenuCommand) selectedCommand;
                else
                    selectedCommand.execute();
            }
        }

    }
}
