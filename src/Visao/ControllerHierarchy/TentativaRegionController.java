package Visao.ControllerHierarchy;

import Modelo.Alternativa;
import Modelo.Avaliacao;
import Modelo.Enunciado;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Collection;

/**
 * Created by Gustavo Freitas on 18/03/2016.
 */
public abstract class TentativaRegionController extends RegionController {

    //Define a repetição do enunciado como sendo de 15 em 15 segundos
    public static final int ENUNCIADO_LOOP_DEFAULT_INTERVAL = 15000;

    private boolean endRepeat = false;
    private Enunciado enunciado;

    public abstract void setAlternativa(Alternativa alternativa);
    public abstract void setAlternativa(Collection<Alternativa> alternativa);

    public final void setEnunciado(Enunciado enunciado){

        this.enunciado = enunciado;

        Thread loopEnunciado = new Thread(() -> {

            while (endRepeat == false) {
                try {
                    enunciado.getEnunciado().play();
                    Thread.sleep(ENUNCIADO_LOOP_DEFAULT_INTERVAL);
                }
                catch (NullPointerException NPE) {
                    /*
                        NullPinterException irá indicar que não é possível executar a mídia.
                        Isso só irá ocorrer quando a aplicação foi finalizada e a
                        Thread parcialmente morta.
                     */
                    return;
                }
                catch (InterruptedException IE) {
                    IE.printStackTrace();
                }
            }
        });

        loopEnunciado.start();
    }

    public final void setFimEnunciado(){
        this.endRepeat = true;
    }

    public abstract void responder(Event event);
}