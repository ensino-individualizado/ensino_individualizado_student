package Visao.Tentativas;

import Modelo.Alternativa;
import Modelo.RecursoDidatico.PlayList.PlayList;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class LeituraController extends TentativaRegionController {

    private PlayList enunciado = null;

    public Alternativa alternativa;

    @FXML
    private Label palavra;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setAlternativa(Alternativa alternativa) {
        this.palavra.setText(alternativa.getPalavra().getPalavra());
        this.alternativa = alternativa;
    }

    @Override
    public void setAlternativa(Collection<Alternativa> alternativa) {
        throw new UnsupportedOperationException("Método não suportado.");
    }

    @FXML
    @Override
    public void responder(Event event) {
        ((AvaliacaoAplicatorController)this.fatherController).getController().responder(this);
    }
}
