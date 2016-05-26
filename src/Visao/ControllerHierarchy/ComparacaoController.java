package Visao.ControllerHierarchy;

import DAO.ImagemDAO;
import Ferramentas.FileSaver;
import Modelo.Alternativa;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * Created by Gustavo Freitas on 20/03/2016.
 */
public abstract class ComparacaoController extends TentativaRegionController {

    @FXML
    protected Node destaque;

    @FXML
    protected Node opcao1;

    @FXML
    protected Node opcao2;

    @FXML
    protected Node opcao3;

    protected HashMap<Node, Alternativa> map = new HashMap<>();

    @Override
    public void setAlternativa(Alternativa alternativa) {
        throw new UnsupportedOperationException("Método não suportado");
    }

    @Override
    public void setAlternativa(Collection<Alternativa> alternativas) {

        Random rand = new Random();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(opcao1);
        nodes.add(opcao2);
        nodes.add(opcao3);

        for(Alternativa a : alternativas){

            Node opcao = nodes.remove(rand.nextInt(nodes.size()));
            setOpcao(opcao, a);
            map.put(opcao, a);

            if(a.isResposta()){
                this.setDestaque(a);
            }
        }
    }

    @Override
    public final void responder(Event event){
        System.out.println("AQUI");
        Node opcao = ((Button)event.getSource()).getChildrenUnmodifiable().get(0);
        ((AvaliacaoAplicatorController)this.fatherController).getController().responder(this, map.get(opcao));
    }

    public abstract void setDestaque(Alternativa destaque);
    public abstract void setOpcao(Node opcao, Alternativa alternativa);

}
