/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico;

import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Midia.Video;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Gustavo Freitas
 */
public class Palavra implements RecursoDidatico {

    public static enum CATEGORIA {
        DISSILABA_SIMPLES(1, "Dissílabas Simples", "Palavra simples que possui duas sílabas"),
        TRISSILABA_SIMPLES(2, "Trissílabas Simples", "'Palavra simples que possui três silabas."),
        DIFICULDADE_DA_LINGUA(3, "Dificuldades da Língua", "Palavra composta por encontro consonantal que a faz ser mais complexa que as demais.");

        int id;
        String nome;
        String descricao;

        CATEGORIA(int id, String nome, String descricao){
            this.id = id;
            this.nome = nome;
            this.descricao = descricao;
        }

        public int getId(){
            return (this.id);
        }

        public String getNome(){
            return (this.nome);
        }

        public String getDescricao() {
            return descricao;
        }
    }
    
    int id = -1;
    private String palavra = null;
    private ArrayList<Silaba> silabas = new ArrayList<>();
    private Imagem imagem = null;
    private Audio audio = null;
    private Video video = null;
    
    private CATEGORIA categoria;

    public Palavra(String palavra){
        this.palavra = palavra;
    }

    public Palavra(int id, String palavra){
        this.id = id;
        this.palavra = palavra;
    }

    public Palavra(String palavra, CATEGORIA cat, ArrayList<Silaba> silabas, Imagem imagem){
        this.palavra = palavra;
        this.categoria = cat;
        this.silabas = silabas;
        this.imagem = imagem;
    }
    
    public Palavra(String palavra, CATEGORIA cat, ArrayList<Silaba> silabas, Imagem imagem, Audio audio, Video video){
        this.palavra = palavra;
        this.categoria = cat;
        this.silabas = silabas;
        this.imagem = imagem;
        this.audio = audio;
        this.video = video;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public Collection<Silaba> getSilabas() {
        return silabas;
    }

    public void setSilabas(ArrayList<Silaba> silabas) {
        this.silabas = silabas;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public CATEGORIA getCategoria() {
        return categoria;
    }

    public void setCategoria(CATEGORIA categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString(){
        return ("Palavra: " + this.palavra + "\n" + "Silabas: " + this.silabas);
    }
}
