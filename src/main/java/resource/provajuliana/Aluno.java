package resource.provajuliana;

import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;

public class Aluno {
    private String nome;
    private int cpf;
    private float altura;
    private float peso;
    private int id_aluno;
    private Date dataNasc;
    private float imc;


    //CONSTRUCTOR
    public Aluno(String nome, int cpf, float altura, float peso, Date dataNasc, float imc) {
        this.nome = nome;
        this.cpf = cpf;
        this.altura = altura;
        this.peso = peso;
        this.dataNasc = dataNasc;
        this.imc = imc;
    }


    //GETTERS E SETTERS
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}



    public int getCpf(){return cpf;}
    public void setCpf(int cpf){this.cpf = cpf;}



    public float getAltura(){return altura;}
    public void setAltura(float altura){this.altura = altura;}



    public float getPeso(){return peso;}
    public void setPeso(float peso){this.peso = peso;}



    public Date getDataNasc(){return dataNasc;}
    public void setDataNasc(Date dataNasc){this.dataNasc = dataNasc;}


    public float getImc(){return imc;}
    public void setImc(float imc){this.imc = imc;}


    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
}
