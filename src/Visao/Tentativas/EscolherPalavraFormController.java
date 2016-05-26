package Visao.Tentativas;

import Modelo.Alternativa;
import Visao.ControllerHierarchy.ComparacaoController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EscolherPalavraFormController extends ComparacaoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setDestaque(Alternativa destaque) {
        destaque.getPalavra().getImagem().finalizarCarregamento();
        ((ImageView)this.destaque).setImage(destaque.getPalavra().getImagem().getImagem());
    }

    @Override
    public void setOpcao(Node opcao, Alternativa alternativa) {
        ((Label)opcao).setText(alternativa.getPalavra().getPalavra());
    }
}
