package Controle;

import java.awt.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gustavo Freitas on 21/03/2016.
 */
@Deprecated
public class ScreenResolutionManager {

    public enum RESOLUTIONS{
        HD, FULLHD
    }

    public static ScreenResolutionManager manager = null;

    GraphicsDevice device = null;

    //Resolução original da tela
    private DisplayMode oldMode = null;

    //Resolução a ser utilizada pela aplicação
    private DisplayMode newMode = null;

    public void redimencionarTela(int width, int height){

        if(oldMode != null || newMode != null){
            throw new RuntimeException("Nunca utiliza resize screen duas vezes sem restaurar a resolução original.");
        }

        //Obtém o monitor primário
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = env.getScreenDevices()[0];

        //Obtém o modo atual
        this.oldMode = device.getDisplayMode();

        //Cria um novo modo
        this.newMode = new DisplayMode(width,height,oldMode.getBitDepth(),oldMode.getRefreshRate());

        device.setDisplayMode(newMode);
    }

    public void redimencionarTela(RESOLUTIONS resolucao){

        if(resolucao == RESOLUTIONS.HD){
            this.redimencionarTela(1360, 768);
        }
        else if(resolucao == RESOLUTIONS.HD){
            this.redimencionarTela(1920, 1080);
        }
    }

    public void restaurarModo(){
        if(this.device == null){
            throw new RuntimeException("Não é possível restaurar por que o modo nunca foi alterado.");
        }

        this.device.setDisplayMode(this.oldMode);
    }

    public static ScreenResolutionManager getInstance(){

        if(manager == null){
            manager = new ScreenResolutionManager();
        }

        return (manager);
    }
}
