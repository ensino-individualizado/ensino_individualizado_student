/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Gustavo Freitas
 */
public class Aluno {
    
    private int id = -1;
    private String nome = null;
    //private Turma turma = null;
    private Date dataNascimento = null;
    
    public Aluno(String nome, Date dataNascimento){
        this.nome = nome;
        //this.turma = turma;
        this.dataNascimento = dataNascimento;
    }
    
    public Aluno(int id, String nome, Date dataNascimento){
        this.id = id;
        this.nome = nome;
        //this.turma = turma;
        this.dataNascimento = dataNascimento;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.dataNascimento = data_nascimento;
    }

    @Override
    public String toString(){
        return (this.id + " - " + this.nome);
    }
}
