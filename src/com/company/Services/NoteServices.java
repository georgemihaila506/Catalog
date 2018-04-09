package com.company.Services;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Domain.Teme;
import com.company.Repository.Repository;
import com.company.Utils.ObservableP;
import com.company.Utils.ObserverP;
import com.company.Validatoare.ValidationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class NoteServices implements Services<Nota,Pereche>,ObservableP<Nota> {
    private Repository<Nota,Pereche> repoNote;
    private Repository<Teme,Integer> repoTeme;
    private Map<Integer,Integer> penalizari=new TreeMap<>();
    protected List<ObserverP<Nota>> observers=new ArrayList<>();
    public NoteServices(Repository<Nota,Pereche> repoNote,Repository<Teme,Integer> repoTeme)
    {
        this.repoNote=repoNote;
        this.repoTeme=repoTeme;
    }

    @Override
    public Nota add(Nota element) throws ValidationException, IOException, ClassNotFoundException {
        return repoNote.save(element);
    }
    public Nota addNota(Nota element,int saptamanaC,String observatii) throws ClassNotFoundException, IOException, ValidationException, com.company.Validatoare.ValidationException {
        Teme tema=repoTeme.findOne(element.getNrTemei());
        if(tema==null)
            throw new com.company.Validatoare.ValidationException("asd");
        if(saptamanaC>tema.getDeadline()){
            if(saptamanaC-tema.getDeadline()==1)
            {
                element.setValoare(element.getValoare()-2);
            }
            if(saptamanaC-tema.getDeadline()==2)
                element.setValoare(element.getValoare()-4);
        }
        if(element.getValoare()<=0)
            element.setValoare(1);
        if(saptamanaC-tema.getDeadline()>2)
            element.setValoare(1);
        Nota e=add(element);
        if(e!=null) {
            return null;
        }
        writeToStudentFile("Adaugare nota",element,saptamanaC,observatii);
        notifyObservers();
        return e;
    }
    @Override
    public void updateOne(Nota element) throws IOException, ClassNotFoundException {
        repoNote.update(element);
        notifyObservers();
    }
    public void updateNota(Nota element,int saptamanaC,String observatii) throws IOException, ClassNotFoundException {
        Teme tema=repoTeme.findOne(element.getNrTemei());
        if(saptamanaC>tema.getDeadline()){
            if(saptamanaC-tema.getDeadline()==1)
                element.setValoare(element.getValoare()-2);
            if(saptamanaC-tema.getDeadline()==2)
                element.setValoare(element.getValoare()-4);
        }
        if(element.getValoare()<=0)
            element.setValoare(1);
        if(saptamanaC-tema.getDeadline()>2)
            element.setValoare(1);
        if(repoNote.findOne(element.getKey()).getValoare()<element.getValoare())
        {   updateOne(element);
            writeToStudentFile("Modificare nota",element,saptamanaC,observatii);

        }
        notifyObservers();
    }

    @Override
    public Nota deleteOne(Pereche s) throws IOException, ClassNotFoundException {
        Nota nota=repoNote.delete(s);
        notifyObservers();
        return nota;
    }

    @Override
    public Nota findOne(Pereche s) throws IOException, ClassNotFoundException {
        return repoNote.findOne(s);
    }

    @Override
    public List<Nota> getAll() throws IOException, ClassNotFoundException {
        return repoNote.get_all();
    }
    private void writeToStudentFile(String action, Nota entity,int saptamanaC,String observatii) {
        String file_name = "Student" + entity.getIdStudent() + ".txt";

        try (FileWriter out = new FileWriter(file_name, true);
             BufferedWriter bw = new BufferedWriter(out);
             PrintWriter pw = new PrintWriter(bw)) {

            String data = action +" tema: "+entity.getNrTemei() +
                    " saptamana: " + saptamanaC + " deadline: " +
                    repoTeme.findOne(entity.getNrTemei()).getDeadline() +
                    " nota: "+ entity.getValoare()+
                    " observatii: "+ observatii;

            pw.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String s=trimiteLaToti(file_name);
        //SendMailSSL mail=new SendMailSSL(s);
    }
    public String trimiteLaToti(String filename)
    {
        Path path= Paths.get(filename);
        Stream<String> lines;
        //Pereche pereche=new Pereche("1",2);
        //Nota nota=new Nota(pereche,3);
        try
        {
            lines= Files.lines(path);
            List<String> allS=new ArrayList<>();
            lines.forEach((String s) ->{
                allS.add(s);
            });
            String trimis=new String();
            for(String el:allS)
            {
                trimis=trimis+el+"\n";

            }
            return trimis;
        }
        catch(IOException e)
        {
            System.out.println("I am here");
        }
        return null;
    }

    @Override
    public void addObserver(ObserverP<Nota> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverP<Nota> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(ObserverP<Nota> o:observers)
            o.update(this);

    }
}
