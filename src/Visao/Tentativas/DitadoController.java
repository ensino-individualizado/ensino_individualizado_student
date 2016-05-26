package Visao.Tentativas;

import DAO.SilabaDAO;
import Modelo.Alternativa;
import Modelo.RecursoDidatico.PlayList.PlayList;
import Modelo.RecursoDidatico.Silaba;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.Principal.AvaliacaoAplicatorController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.*;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class DitadoController extends TentativaRegionController {

    @FXML
    private TextField resposta;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    private LinkedList<Button> clicked = new LinkedList<>();

    private PlayList enunciado;
/*
    private void updateResposta(){
        String str = "";
        for(Button b : clicked){
            str = str.concat(b.getText());
        }
        resposta.setText(str);
    }
*/
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
/*
    private void randomSet(Collection<Silaba> silabas){
        for(Silaba s : silabas){
            this.randomSet(s);
        }
    }

    private void randomSet(Silaba s){
        Button novo = null;
        switch(new Random().nextInt(3) + 1){
            case 1:
                if(hbox1.getChildren().size() < 6) {
                    novo = new Button(s.getSilaba());
                    hbox1.getChildren().addAll(novo);
                }
                else {
                    randomSet(s);
                }
                break;
            case 2:
                if(hbox2.getChildren().size() < 5) {
                    novo = new Button(s.getSilaba());
                    hbox2.getChildren().addAll(novo);
                }
                else {
                    randomSet(s);
                }
                break;
            case 3:
                if(hbox3.getChildren().size() < 4) {
                    novo = new Button(s.getSilaba());
                    hbox3.getChildren().addAll(novo);
                }
                else {
                    randomSet(s);
                }
                break;
        }

        if(novo != null){
            novo.getStyleClass().addAll("button-raised", "button-blue");

            novo.setOnAction(event -> {
                keyPress(event);
            });
        }
    }
*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setAlternativa(Alternativa alternativa) {
        /*
            ArrayList<Silaba> silabas = (ArrayList<Silaba>) SilabaDAO.getInstance().obterRandom(15 - alternativa.getPalavra().getSilabas().size());

            for(Silaba s : alternativa.getPalavra().getSilabas()){
                silabas.add(new Random().nextInt(silabas.size()) + 1, s);
            }

            randomSet(silabas);
        */

    }

    @Override
    public void setAlternativa(Collection<Alternativa> alternativa) {
        throw new UnsupportedOperationException("Método não suportado.");
    }

    @Override
    public void responder(Event event) {
        ((AvaliacaoAplicatorController)this.fatherController).getController().responder(this);
    }

    public String getResposta(){
        //this.updateResposta();
        return (this.resposta.getText());
    }
}
