package Modelo;

import Modelo.RecursoDidatico.PlayList.PlayList;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class Enunciado {

    int id = -1;

    private String descricao;
    private PlayList enunciado;

    public Enunciado(String descricao, PlayList enunciado) {
        this.descricao = descricao;
        this.enunciado = enunciado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PlayList getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(PlayList enunciado) {
        this.enunciado = enunciado;
    }
}
