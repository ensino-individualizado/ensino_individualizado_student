/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.LinkedList;

/**
 *
 * @author Gustavo Freitas
 */
public class Avaliacao {

    private int id = -1;
    private String descricao;
    private LinkedList<Bloco> blocos = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Bloco> getBlocos() {
        return blocos;
    }

    public void setBlocos(LinkedList<Bloco> blocos) {
        this.blocos = blocos;
    }

    public void addBloco(Bloco bloco){
        this.blocos.add(bloco);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        String str = "Avaliacao " + this.id + "  Descricao: " + this.descricao + "\n";

        for(Bloco b : this.blocos){
            str = str.concat(b.toString());
        }

        return (str);
    }
}
