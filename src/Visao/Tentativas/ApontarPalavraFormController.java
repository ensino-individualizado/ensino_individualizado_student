package Visao.Tentativas;

import Modelo.Alternativa;
import Visao.ControllerHierarchy.ComparacaoController;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 06/04/2016.
 */
public class ApontarPalavraFormController extends ComparacaoController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setDestaque(Alternativa destaque) {
        //Este modelo não exibe o destaque, mas ele é utilziado como a resposta.
        return;
    }

    @Override
    public void setOpcao(Node opcao, Alternativa alternativa) {
        ((Label)opcao).setText(alternativa.getPalavra().getPalavra());
    }
}
