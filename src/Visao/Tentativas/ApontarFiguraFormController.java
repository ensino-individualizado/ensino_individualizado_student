package Visao.Tentativas;

import Modelo.Alternativa;
import Visao.ControllerHierarchy.ComparacaoController;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 06/04/2016.
 */
public class ApontarFiguraFormController extends ComparacaoController {


    @Override
    public void setDestaque(Alternativa destaque) {
        //Este modelo não exibe o destaque, mas ele é utilziado como a resposta.
        return;
    }

    @Override
    public void setOpcao(Node opcao, Alternativa alternativa) {
        alternativa.getPalavra().getImagem().finalizarCarregamento();
        ((ImageView)opcao).setImage(alternativa.getPalavra().getImagem().getImagem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
