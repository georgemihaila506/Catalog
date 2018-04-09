package com.company.Domain;

public  class Student extends StudentS implements HasID<String> {
    private String idStudent;
    private String nume;
    private int grupa;
    private String email;
    private String cadruIndrumator;

    /**
     *
     * @param idStudent de tip string
     * @param nume de tip string
     * @param grupa de tip int
     * @param email de tip string
     * @param cadruIndrumator de tip string
     */
    public Student(String idStudent, String nume, int grupa, String email, String cadruIndrumator) {
        super(idStudent,nume,grupa,email,cadruIndrumator);
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + getIdStudent() +
                ", nume='" + getNume() + '\'' +
                ", grupa=" + getGrupa() +
                ", email='" + getEmail() + '\'' +
                ", cadruIndrumator='" + getCadruIndrumator() + '\'' +
                '}';
    }


    public String getId()
    {
        return getIdStudent();
    }
    public void setId(String id)
    {
        setIdStudent(id);
    }


}
