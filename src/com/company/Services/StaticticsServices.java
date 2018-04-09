package com.company.Services;

import com.company.Domain.*;
import com.company.Repository.Repository;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class StaticticsServices {
    private Repository<Student,String> repoS;
    private Repository<Teme,Integer> repoT;
    private Repository<Nota,Pereche> repoN;
    private Map<Integer,Integer> intarzieri=new TreeMap<>();
    public StaticticsServices(Repository<Student, String> repoS, Repository<Teme, Integer> repoT, Repository<Nota, Pereche> repoN) {
        this.repoS = repoS;
        this.repoT = repoT;
        this.repoN = repoN;
    }
    public Map<String,Double> NotaLaboratorFiecareStudent() throws IOException, ClassNotFoundException {
        Map<String,Double> note=new TreeMap<>();
        List<Nota> allN =repoN.get_all();
        List<Teme> allT=repoT.get_all();
        List<Student> allS=repoS.get_all();
        int nrteme=allT.size();
        allS.forEach(s->
        {
            int nota=0;
            int k=0;
            for(Nota n:allN)
            {
                if(n.getIdStudent().equals(s.getIdStudent()))
                {
                    k++;
                    nota+=n.getValoare();
                }
            }
            if(k!=nrteme)
                nota=nota+nrteme-k;
            double media=(double)nota/nrteme;
            note.put(s.getIdStudent(),media);


        });
        return note;
    }
    private void readFromFile(String entity)
    {
       String filename = "./Student" + entity + ".txt";
        Path path= Paths.get(filename);
        Stream<String> lines;
        try
        {
            lines= Files.lines(path);
            lines.forEach((String s) ->{
                String[] parts=s.split("\\s+");
                Integer nrtema=Integer.parseInt(parts[3]);
                if(parts[11].equals("intarziere"))
                {
                    System.out.println("here");
                    if(intarzieri.containsKey(nrtema))
                    {

                        Integer el=intarzieri.get(nrtema);
                        el+=1;
                        intarzieri.put(nrtema,el);
                    }
                    else
                    {

                        intarzieri.put(nrtema,1);
                    }
                }

            });
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println("I am here");
        }
    }
    public Teme CeaMaiGreaTema() throws IOException, ClassNotFoundException {
        List<Student> allS=repoS.get_all();
        allS.forEach(s->
        {
            readFromFile(s.getIdStudent());
        }
        );
        Pair<Integer,Integer> nr =new Pair<>(0,0);
        for(Map.Entry<Integer,Integer> el :intarzieri.entrySet()) {
            if (nr.getValue() < el.getValue()) {
                nr = new Pair<>(el.getKey(), el.getValue());
            }
        }
        return repoT.findOne(nr.getKey());
    }
    public List<Student> StudentiAcceptati() throws IOException, ClassNotFoundException {
        Map<String,Double> studenti=this.NotaLaboratorFiecareStudent();
        List<Student> ok=new ArrayList<>();
        for(Map.Entry<String,Double> el:studenti.entrySet())
        {
            if(el.getValue()>=4)
                ok.add(repoS.findOne(el.getKey()));
        }
        return ok;
    }
    public List<Student> PredareLaTimp() throws IOException, ClassNotFoundException {
        List<Student> all=repoS.get_all();
        List<Student> allF=new ArrayList<>();
        all.forEach(s->
        {
            readFromFile(s.getIdStudent());
            if(intarzieri.isEmpty())
                allF.add(s);
            else
                intarzieri.clear();
        });
        return allF;
    }
}
