/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 *
 * @author Gustavo Freitas
 */
public class Bloco {

    public static enum TYPE{
        APONTAR_PALAVRA(1, "Apontar palavra."),
        APONTAR_FIGURA(2, "Apontar figura."),
        COMPARACAO_PALAVRAS_FIGURA(3, "Comparação de três palavras com uma figura."),
        COMPARACAO_PALAVRA_FIGURAS(4, "Comparação de três figuras com uma palavra."),
        COPIA(5, "Copia."),
        DITADO(6, "Ditado."),
        LEITURA(7, "leitura."),
        NOMEAR_IMAGEM(8, "Nomear imagem.");

        private final int id;
        private final String descricao;

        TYPE(int id, String descricao) {
            this.id = id;
            this.descricao = descricao;
        }

        public int getId() {
            return id;
        }

        public String getDescricao() {
            return descricao;
        }

        @Override
        public String toString(){
            return (this.descricao);
        }
    };

    private TYPE tipo;
    private int id = -1;
    private LinkedList<Tentativa> tentativas = null;
    
    public Bloco(TYPE tipo, LinkedList<Tentativa> tentativas){
        this.tipo = tipo;
        this.tentativas = tentativas;
    }

    public Bloco(TYPE tipo){
        this.tipo = tipo;
        this.tentativas = new LinkedList<>();
    }
    
    public void addTentativa(Tentativa stage){
        this.tentativas.addLast(stage);

    }

    public Tentativa removeTentativa(){
        return (this.tentativas.removeFirst());
    }
    
    public Tentativa getFirstTentativa(){
        try{
            return (this.tentativas.removeFirst());
        }
        catch(NoSuchElementException e){
            return (null);
        }
    }

    public TYPE getTipo() {
        return tipo;
    }

    public void setTipo(TYPE tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Tentativa> getTentativas() {
        return tentativas;
    }

    public void setTentativas(LinkedList<Tentativa> tentativas) {
        this.tentativas = tentativas;
    }

    @Override
    public String toString(){

        String str = "Bloco " + this.id + "  " + this.tipo.toString() + "\n";

        for(Tentativa tentativa : tentativas){
            str = str.concat(tentativa.toString());
            str = str.concat("\n");
        }

        return (str);
    }
}
