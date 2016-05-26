package Modelo.Construtores;

import Modelo.*;
import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.PlayList.PlayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class AvaliacaoBuilder {

    private Avaliacao avaliacao = new Avaliacao();


    public void setId(int id){
        this.avaliacao.setId(id);
    }

    public void setDescricao(String descricao){
        this.avaliacao.setDescricao(descricao);
    }


    public void addBloco(Bloco novo){
        this.avaliacao.addBloco(novo);
    }

    public void addBloco(Bloco.TYPE tipo, LinkedList<Tentativa> tentativas){
        avaliacao.addBloco(new Bloco(tipo, tentativas));
    }

    public void addBloco(Bloco.TYPE tipo, Collection<Palavra> palavras, Palavra.CATEGORIA catPalavras){

        Bloco novo = null;

        switch (tipo){
            case COMPARACAO_PALAVRA_FIGURAS:
            case COMPARACAO_PALAVRAS_FIGURA:
                novo = this.novoBlocoComparacao(tipo, palavras, catPalavras);
                break;
            default:{
                novo = this.novoBlocoOutro(tipo, palavras, catPalavras);
            }

        }
        this.avaliacao.addBloco(novo);
    }

    public Bloco novoBlocoComparacao(Bloco.TYPE tipo, Collection<Palavra> palavras, Palavra.CATEGORIA categoria){
        Bloco novo = new Bloco(tipo);

        for(Palavra resposta : palavras){
            for(Palavra palavra1 : palavras){
                if(palavra1 != resposta) {
                    for (Palavra palavra2 : palavras) {
                        if(palavra2 != resposta && palavra2 != palavra1){
                            Collection<Alternativa> alternativas = new ArrayList<>();
                            alternativas.add(new Alternativa(false, palavra1));
                            alternativas.add(new Alternativa(false, palavra2));
                            alternativas.add(new Alternativa(true, resposta));

                            Audio inicio = new Audio();
                            inicio.setId(tipo.getId());

                            PlayList enunciado = new PlayList(PlayList.TYPE.AUDIO, inicio);

                            novo.addTentativa(new Tentativa(alternativas, new Enunciado("", enunciado)));
                        }
                    }
                }
            }
        }

        return (novo);
    }

    public Bloco novoBlocoOutro(Bloco.TYPE tipo, Collection<Palavra> palavras, Palavra.CATEGORIA categoria){
        Bloco novo = new Bloco(tipo);

        for(Palavra palavra : palavras){
            Alternativa alternativa = new Alternativa(true, palavra);

            Audio inicio = new Audio();
            inicio.setId(tipo.getId());

            Enunciado enunciado;

            if(!palavra.getCategoria().equals(novo.getTipo().equals(Bloco.TYPE.NOMEAR_IMAGEM)) &&
               !palavra.getCategoria().equals(novo.getTipo().equals(Bloco.TYPE.LEITURA))) {
                enunciado = new Enunciado("", new PlayList(PlayList.TYPE.AUDIO, inicio, palavra.getAudio()));
            }
            else{
                enunciado = new Enunciado("", new PlayList(PlayList.TYPE.AUDIO, inicio));
            }

            novo.addTentativa(new Tentativa(alternativa, enunciado));
        }
        return (novo);
    }

    public Avaliacao getInstance(){
        return (this.avaliacao);
    }

}
