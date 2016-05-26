/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico.PlayList;

import DAO.AudioDAO;
import DAO.PlayListDAO;
import DAO.VideoDAO;
import Ferramentas.FileSaver;
import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Midia.Midia;
import Modelo.RecursoDidatico.Midia.Video;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import sun.plugin2.jvm.CircularByteBuffer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;

/**
 *
 * @author Gustavo Freitas
 */
public class PlayList{

    public static enum TYPE {
        AUDIO(1, "PlayList de audio."), VIDEO(2, "PlayList de video");

        int id;
        String descricao;

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
    };

    private int id = -1;

    private TYPE tipo;

    //Indica o MediaPlayer atual. Útil para parar a reprodução a qualquer momento.
    private MediaPlayer atual = null;

    //Lista de mídias a serem reproduzidas
    private LinkedList<Midia> midias = new LinkedList<>();
    private Iterator<Midia> iterador = null;

    public PlayList(TYPE tipo){
        this.tipo = tipo;
    }
    
    public PlayList(TYPE tipo, Collection<Midia> midias){
        this.tipo = tipo;
        this.midias.addAll(midias);
    }
    
    public PlayList(TYPE tipo, Midia ... midias){
        this.tipo = tipo;
        for(Midia midia : midias){
            this.midias.addLast(midia);
        }
    }

    private void play(Midia midia) throws NullPointerException{

        //Se o carregamento não foi finalizado
        if(midia.getMedia() == null){
            midia.finalizarCarregamento();
        }

        //Cria um novo player
        atual = new MediaPlayer(midia.getMedia());

        //Quando a reprodução terminar
        atual.setOnEndOfMedia(() -> {
            //Para o player atual
            atual.stop();

            //Faz uma transição
            PauseTransition pause = new PauseTransition(Duration.millis(100));

            //Quando a transição acabar
            pause.setOnFinished(event -> {
                //Se ainda existem itens para reproduzir
                if(iterador.hasNext()){
                    //Reproduz
                    play(iterador.next());
                }
                //Se não
                else{
                    //Limpa o lixo
                    iterador = null;
                    atual = null;
                }
            });
            pause.play();
        });

        atual.play();
    }

    public void play() throws NullPointerException{

        if(this.atual != null){
            this.atual.stop();
            this.iterador = null;
        }

        //Obtém o iterador da lista
        if(this.midias != null)
            this.iterador = midias.iterator();

        //Se existem mídias para reproduzir
        if(iterador.hasNext()) {
            this.play(iterador.next());
        }
    }
    
    public void pause(){
        atual.pause();
    }
    
    public void stop() throws NullPointerException{
        atual.stop();
        atual = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TYPE getTipo() {
        return tipo;
    }

    public void setTipo(TYPE tipo) {
        this.tipo = tipo;
    }

    public LinkedList<Midia> getMidias() {
        return midias;
    }

    public void setMidias(LinkedList<Midia> midias) {
        this.midias = midias;
    }
}
