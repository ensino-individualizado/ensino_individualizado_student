package Visao.Tentativas;

import Modelo.Alternativa;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class CopiaController extends TentativaRegionController {

    @FXML
    private Label palavra;

    @FXML
    private TextField resposta;

    Alternativa respostaCorreta = null;

    @FXML
    public void keyPress(ActionEvent event){

        if(((Button)event.getSource()).getId() != null && ((Button)event.getSource()).getId().equals("erase")){
            if (resposta.getText().length() > 0) {
                resposta.setText(resposta.getText().substring(0, resposta.getText().length()-1));
            }
        }
        else if(((Button)event.getSource()).getId() != null && ((Button)event.getSource()).getId().equals("end")){

        }
        else{
            resposta.setText(resposta.getText().concat(((Button)event.getSource()).getText()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setAlternativa(Alternativa alternativa) {
        this.respostaCorreta = alternativa;
        this.palavra.setText(alternativa.getPalavra().getPalavra());
    }

    @Override
    public void setAlternativa(Collection<Alternativa> alternativa) {
        throw new UnsupportedOperationException("Método não suportado");
    }

    @Override
    public void responder(Event event) {
        ((AvaliacaoAplicatorController)this.fatherController).getController().responder(this);
    }

    public String getResposta(){
        return (this.resposta.getText());
    }
}
