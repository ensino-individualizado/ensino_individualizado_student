package Visao.Tentativas;

import DAO.ImagemDAO;
import Ferramentas.FileSaver;
import Modelo.Alternativa;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class NomearImagemController extends TentativaRegionController {

    @FXML
    private ImageView figura;

    public void setOpcao(Alternativa alternativa){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setAlternativa(Alternativa alternativa) {
        ImagemDAO.getInstance().finalizarCarregamento(alternativa.getPalavra().getImagem(), FileSaver.getInstance().getTmpFolderLocation());
        this.figura.setImage(new Image("file:////" + alternativa.getPalavra().getImagem().getLocal()));
    }

    @Override
    public void setAlternativa(Collection<Alternativa> alternativa) {
        throw new UnsupportedOperationException("Método não suportado.");
    }

    @Override
    public void responder(Event event) {
        ((AvaliacaoAplicatorController)this.fatherController).getController().responder(this);
    }

}
