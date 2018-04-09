package com.company.Repository;

import com.company.Domain.Nota;
import com.company.Domain.Pereche;
import com.company.Validatoare.ValidationException;
import com.company.Validatoare.Validator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RepoFileNote extends AbstractRepository<Nota,Pereche>  {
    private String filename;
    public RepoFileNote(String filename,Validator<Nota> validator)
    {
        super(validator);
        this.filename=filename;
    }
    @Override
    public long size() {
        return all.size();
    }
    @Override
    public Nota save(Nota element) throws ValidationException, IOException, ClassNotFoundException {


        validator.validate(element);
        all = readObject();
        if (all.containsKey(element.getId()))
        {
            Nota el=all.get(element.getId());
            writeObject();
            return el;}
        all.put(element.getId(), element);
        writeObject();
        return null;
    }

    /**
     * @param id dupa care se sterge
     * @return elemtnul stres
     */
    @Override
    public Nota delete(Pereche id) throws IOException, ClassNotFoundException {
        all = readObject();
        Nota el = all.remove(id);
        writeObject();
        return el;

    }

    /**
     * @param element care urmeaza a fi updatat
     */
    @Override
    public void update(Nota element) throws IOException, ClassNotFoundException {
        all = readObject();
        all.replace(element.getId(), element);
        writeObject();
    }

    /**
     * Cauta in map un element
     *
     * @param id
     * @return elemtnul daca a fost gasit
     */
    @Override
    public Nota findOne(Pereche id) throws IOException, ClassNotFoundException {
        all = readObject();
        Nota el = all.get(id);
        writeObject();
        return el;
    }
    public  Map<Pereche,Nota> readObject()
    {
        Path path= Paths.get(filename);
        Stream<String> lines;
        all.clear();
        //Pereche pereche=new Pereche("1",2);
        //Nota nota=new Nota(pereche,3);
        try
        {
            lines= Files.lines(path);
            lines.forEach((String s) ->{
                String[] parts=s.split(";");
                String id=parts[0];
                Integer nrTema=Integer.parseInt(parts[1]);
                int valoare=Integer.parseInt(parts[2]);
                all.put(new Pereche(id,nrTema), new Nota(new Pereche(id,nrTema),valoare));
            });
        }
        catch(IOException e)
        {
            System.out.println("I am here");
        }
        return all;
    }
    public void writeObject() throws IOException, ClassNotFoundException {
        Path path=Paths.get(filename);
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING))
        {
            //bufferedWriter.write(student.toString());
            //bufferedWriter.newLine();
            //List<Nota> note=get_all();
            Iterable<Nota> note=all.values();
            note.forEach(el->{
                try {
                    bufferedWriter.write(el.getIdStudent()+";"+el.getNrTemei()+";"+el.getValoare()+";");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
    @Override
    public List<Nota> get_all() throws IOException, ClassNotFoundException {
        all = readObject();
        Map<Pereche,Nota> all2=all;
        writeObject();
        List<Nota> lista = new ArrayList<Nota>(all2.values());
        return lista;
    }
}
