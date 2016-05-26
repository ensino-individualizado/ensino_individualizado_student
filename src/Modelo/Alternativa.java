package Modelo;

import Modelo.RecursoDidatico.Palavra;

/**
 * Created by Gustavo Freitas on 30/10/2015.
 */
public class Alternativa {

    int id = -1;

    private boolean resposta;
    private Palavra palavra;

    public Alternativa(boolean resposta, Palavra palavra) {
        this.resposta = resposta;
        this.palavra = palavra;
    }

    public boolean isResposta() {
        return resposta;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return (this.palavra.toString() + " Resposta : " + this.isResposta());
    }
}
