/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author Gustavo Freitas
 */
public class Tentativa{

    int id = -1;

    private Enunciado enunciado;
    private Collection<Alternativa> alternativas = null;

    public Tentativa(Alternativa alternativa, Enunciado enunciado){
        this.enunciado = enunciado;
        if(this.alternativas == null){
            this.alternativas = new ArrayList<>();
        }
        this.alternativas.add(alternativa);
    }

    public Tentativa(Collection<Alternativa> alternativas, Enunciado enunciado){
        this.enunciado = enunciado;
        this.alternativas = alternativas;
    }
    
    public Enunciado getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(Enunciado enunciado) {
        this.enunciado = enunciado;
    }

    public Alternativa getResposta() {
        for(Alternativa alternativa : this.alternativas){
            if(alternativa.isResposta()){
                return (alternativa);
            }
        }
        return (null);
    }

    public void setResposta(Alternativa resposta) {
        for(Alternativa alternativa : this.alternativas){
            alternativa.setResposta(false);
        }
        resposta.setResposta(true);
        this.alternativas.add(resposta);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(Collection<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    @Override
    public String toString(){
        String str = "  Tentativa " + this.id + "\n";
        str = str.concat("   Alternativas:\n");

        for(Alternativa alternativa : this.alternativas){
            str = str.concat("    " + alternativa.toString() + "\n");
        }

        return (str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tentativa tentativa = (Tentativa) o;

        if (!enunciado.equals(tentativa.enunciado)) return false;
        return alternativas.equals(tentativa.alternativas);

    }

    @Override
    public int hashCode() {
        int result = enunciado.hashCode();
        result = 31 * result + alternativas.hashCode();
        return result;
    }
}
