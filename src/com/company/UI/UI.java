package com.company.UI;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Student;
import com.company.Domain.Teme;
import com.company.Services.NoteServices;
import com.company.Services.StudentServices;
import com.company.Services.TemeServices;
import com.company.Validatoare.ValidationException;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    //private Service ctrl;
    private TemeServices ctrlT;
    private StudentServices ctrlS;
    private NoteServices ctrlN;

    public UI(TemeServices ctrlT, StudentServices ctrlS, NoteServices ctrlN) {
        this.ctrlT = ctrlT;
        this.ctrlS = ctrlS;
        this.ctrlN = ctrlN;
    }


    // public UI(Service ctrl) {
    //this.ctrl = ctrl;
    //}
    public void teste() throws IOException, ClassNotFoundException {
        Student s1 = new Student("1234", "george", 225, "geohasda@ahsdasj.com", "prof1");
        Student s2 = new Student("1334", "alex", 226, "alexoha@ahsdasj.com", "prof2");
        Student s3 = new Student("", "george", -225, "geohasda@ahsdasj.com", "prof1");
        Student s10 = new Student("2234", "george", 225, "geohasda@ahsdasj.com", "prof1");
        try {
            ctrlS.add(s1);
            ctrlS.add(s2);
            ctrlS.add(s10);
            ctrlS.add(s3);
        } catch (ValidationException e) {
            System.out.println(e);
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Student s4 = new Student("1334", "george-alex", 225, "geohasda@ahsdasj.com", "prof3");
        ctrlS.updateOne(s4);
        Iterable<Student> all = ctrlS.getAll();
        for (Student s : all)
            System.out.println(s);
        Student s5 = new Student("1335", "ionut", 221, "ionut@ahsdasj.com", "prof4");
        try {
            ctrlS.add(s5);
        } catch (ValidationException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ctrlS.deleteOne("1335");
        System.out.println(ctrlS.findOne("1234"));
        Iterable<Student> all2 = ctrlS.getAll();
        for (Student s : all2)
            System.out.println(s);

        Teme t1 = new Teme(1, "exercitiu1", 6);
        Teme t2 = new Teme(2, "exercitiu2", 4);
        Teme t3 = new Teme(3, "exercitiu3", 12);
        Teme t4 = new Teme(-1, "", 15);

        try {
            ctrlT.add(t4);
        } catch (ValidationException e) {
            //e.printStackTrace();
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ctrlT.add(t1);
            ctrlT.add(t2);
            ctrlT.add(t3);
            // ctrl.addTema(t4);
        } catch (ValidationException e) {
            System.out.println(e);
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ctrlT.modificareDeadLine(3, 10, 13);
            ctrlT.modificareDeadLine(3, 15, 13);
        } catch (ValidationException e) {
            System.out.println(e);
        }
        Iterable<Teme> allT = ctrlT.getAll();
        for (Teme t : allT)
            System.out.println(t);
    }

    public void showOptions() {
        System.out.println("1.Adaugare student!");
        System.out.println("2.Adaugare tema!");
        System.out.println("3.Modificare deadline tema!");
        System.out.println("4.Afisare studenti!");
        System.out.println("5.Afisare teme!");
        System.out.println("6.Update student!");
        System.out.println("7.Stergere student!");
        System.out.println("8.Cauta student!");
        System.out.println("9.Adauga nota!");
        System.out.println("10.Arata note!");
        System.out.println("0.Exit!");
        System.out.println("Alegeti optiunea:");
    }

    public void run() throws IOException, ClassNotFoundException {
        int opt;
        Scanner sc = new Scanner(System.in);

        while (true) {

            this.showOptions();
            opt = sc.nextInt();
            if (opt == 10) {
                System.out.println(ctrlN.getAll());
            }
            if (opt == 9) {
                String Id;
                Integer nrtema;
                int nota;
                System.out.println("Da id");
                Id = sc.next();
                System.out.println("Da nr tema");
                nrtema = sc.nextInt();
                System.out.println("Da nota");
                nota = sc.nextInt();
                Pereche el = new Pereche(Id, nrtema);
                Nota note = new Nota(el, nota);
                String observatii;
                int saptamanaC;
                System.out.println("Da saptamana");
                saptamanaC = sc.nextInt();
                System.out.println("Da observatii");
                observatii = sc.next();
                if (ctrlS.findOne(Id) != null && ctrlT.findOne(nrtema) != null)
                    try {
                        ctrlN.addNota(note, saptamanaC, observatii);
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                if (opt == 8) {
                    String id;
                    System.out.println("Numar matricol:");
                    id = sc.next();
                    System.out.println(ctrlS.getStudent(id));
                }
                if (opt == 7) {
                    String id;
                    System.out.println("Numar matricol:");
                    id = sc.next();
                    ctrlS.deleteOne(id);
                }
                if (opt == 6) {
                    String numarMatricol;
                    String email;
                    String diriginte;
                    String nume;
                    int grupa;

                    System.out.println("Dati numarul matricol:");
                    numarMatricol = sc.next();

                    System.out.println("Dati nume:");
                    nume = sc.next();

                    System.out.println("Dati grupa:");
                    grupa = sc.nextInt();

                    System.out.println("Dati email:");
                    email = sc.next();

                    System.out.println("Dati nume indrumator:");
                    diriginte = sc.next();
                    ctrlS.updateOne(new Student(numarMatricol, nume, grupa, email, diriginte));
                }
                if (opt == 1) {
                    String numarMatricol;
                    String email;
                    String diriginte;
                    String nume;
                    int grupa;

                    System.out.println("Dati numarul matricol:");
                    numarMatricol = sc.next();

                    System.out.println("Dati nume:");
                    nume = sc.next();

                    System.out.println("Dati grupa:");
                    grupa = sc.nextInt();

                    System.out.println("Dati email:");
                    email = sc.next();

                    System.out.println("Dati nume indrumator:");
                    diriginte = sc.next();

                    try {
                        ctrlS.add(new Student(numarMatricol, nume, grupa, email, diriginte));
                    } catch (ValidationException e) {
                        System.out.println(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (opt == 2) {
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
                if (opt == 3) {
                    Integer numar;
                    int saptamanaC1;
                    int newDeadline;
                    System.out.println("Dati numarul temei:");
                    numar = sc.nextInt();
                    System.out.println("Dati saptamana curenta:");
                    saptamanaC1 = sc.nextInt();
                    System.out.println("Dati noul deadline:");
                    newDeadline = sc.nextInt();
                    //ctrlT.modificareDeadLine(numar,saptamanaC1,newDeadline);
                }
                if (opt == 4) {
                    System.out.println(ctrlS.getAll());
                }
                if (opt == 5) {
                    System.out.println(ctrlT.getAll());
                }
                if (opt == 0) {
                    break;
                }
            }


        }
    }
}
