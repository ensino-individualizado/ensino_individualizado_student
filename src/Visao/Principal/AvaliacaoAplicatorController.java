package Visao.Principal;

import Controle.AplicadorController;
import Modelo.Alternativa;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 19/10/2015.
 */
public class AvaliacaoAplicatorController extends RegionController{

    int painelId = 1;

    private AplicadorController controller = null;
    private TentativaRegionController tentativaAtual;

    @FXML
    Pane painel;

    @FXML
    public void proximo(){

        //Finaliza a repetição do enunciado
        if(this.tentativaAtual != null){
            this.tentativaAtual.setFimEnunciado();
        }

        //Carrega a próxima tentativa
        this.tentativaAtual = this.controller.proximaTentativa(this);
        this.painel.getChildren().clear();

        if(this.tentativaAtual != null) {
            this.painel.getChildren().add(this.tentativaAtual.getRegion());
            painelId++;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public AplicadorController getController() {
        return controller;
    }

    public void setController(AplicadorController controller) {
        this.controller = controller;
    }
}