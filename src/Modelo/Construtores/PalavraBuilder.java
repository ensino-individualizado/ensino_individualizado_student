/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Construtores;

import Modelo.RecursoDidatico.Imagem;
import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Midia.Video;
import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.Silaba;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Gustavo Freitas
 */
public class PalavraBuilder {
    
    public Palavra palavra = null;
    
    public void novaPalavra(String palavra){
        this.palavra = new Palavra(palavra);
    }
    
    public void novaPalavra(int id, String palavra){
        this.palavra = new Palavra(id, palavra);
    }
    
    public void adicionarImagem(Imagem imagem){
        palavra.setImagem(imagem);
    }
    
    public void adicionarImagem(String local){
        palavra.setImagem(new Imagem());
        palavra.getImagem().setLocal(local);
    }

    public void adicionarImagem(int id){
        palavra.setImagem(new Imagem());
        palavra.getImagem().setId(id);
    }
    
    public void adicionarAudio(Audio audio){
        palavra.setAudio(audio);
    }
    
    public void adicionarAudio(String local, String transcricao){
        palavra.setAudio(new Audio());
        palavra.getAudio().setLocal(local);
        palavra.getAudio().setTranscricao(transcricao);
    }

    public void adicionarAudio(int id, String transcricao){
        palavra.setAudio(new Audio());
        palavra.getAudio().setId(id);
        palavra.getAudio().setTranscricao(transcricao);
    }
    
    public void adicionarVideo(Video video){
        palavra.setVideo(video);
    }
    
    public void adicionarVideo(String local, String descricao){
       palavra.setVideo(new Video());
       palavra.getVideo().setLocal(local);
       palavra.getVideo().setDescricao(descricao);
    }
 
    public void adicionarVideo(int id, String descricao){
        palavra.setVideo(new Video());
        palavra.getVideo().setId(id);
        palavra.getVideo().setDescricao(descricao);
    }
    
    public void adicionarSilaba(Silaba silaba){
        palavra.getSilabas().add(silaba);
    }
    
    public void adicionarSilabas(Collection<Silaba> silabas){
        palavra.setSilabas(new ArrayList<>());
        
        palavra.getSilabas().addAll(silabas);
        
        definirCategoria();
    }

    private boolean verificaEncontroConsonantal(){
        for(int i = 0; i < (palavra.getPalavra().getBytes().length - 1); i++){
            if(isConsoante(palavra.getPalavra().getBytes()[i]) && isConsoante(palavra.getPalavra().getBytes()[i+1])){
                return (true);
            }
        }

        return (false);
    }

    private boolean isConsoante(byte c){
        switch (c){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return (false);

            default:
                return (true);
        }
    }

    public void definirCategoria(){

        if(palavra.getPalavra().contains("ch") || palavra.getPalavra().contains("x") || palavra.getPalavra().contains("rr") ||
           palavra.getPalavra().contains("ss") || palavra.getPalavra().contains("ç") || verificaEncontroConsonantal() ||
           palavra.getPalavra().contains("CH") || palavra.getPalavra().contains("X") || palavra.getPalavra().contains("RR") ||
           palavra.getPalavra().contains("SS") || palavra.getPalavra().contains("Ç")){
            palavra.setCategoria(Palavra.CATEGORIA.DIFICULDADE_DA_LINGUA);
        }
        else {
            switch (palavra.getSilabas().size()) {
                case 2:
                    palavra.setCategoria(Palavra.CATEGORIA.DISSILABA_SIMPLES);
                    break;
                case 3:
                    palavra.setCategoria(Palavra.CATEGORIA.TRISSILABA_SIMPLES);
                    break;
                default:
                    palavra.setCategoria(Palavra.CATEGORIA.DIFICULDADE_DA_LINGUA);
            }
        }
    }

    public void setCategoria(Palavra.CATEGORIA cat){
        this.palavra.setCategoria(cat);
    }

    public Palavra getInstance(){
        return (palavra);
    }
}
