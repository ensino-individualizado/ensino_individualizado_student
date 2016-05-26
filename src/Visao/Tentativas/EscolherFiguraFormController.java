package Visao.Tentativas;

import DAO.ImagemDAO;
import Ferramentas.FileSaver;
import Modelo.Alternativa;
import Visao.ControllerHierarchy.ComparacaoController;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class EscolherFiguraFormController extends ComparacaoController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setDestaque(Alternativa destaque) {
        ((Label)this.destaque).setText(destaque.getPalavra().getPalavra());
    }

    @Override
    public void setOpcao(Node opcao, Alternativa alternativa) {
        alternativa.getPalavra().getImagem().finalizarCarregamento();
        ((ImageView)opcao).setImage(alternativa.getPalavra().getImagem().getImagem());
    }
}
