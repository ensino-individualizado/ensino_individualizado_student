package Visao.Principal;

import Controle.MainController;
import Modelo.Aluno;
import Modelo.Avaliacao;
import Modelo.Turma;
import Visao.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class PrincipalController extends RegionController {

    @FXML
    private ComboBox<Aluno> alunos;

    @FXML
    private ComboBox<Avaliacao> avaliacoes;

    @FXML
    private Button iniciar;

    public void alunoSelected(){
        if(this.avaliacoes.getSelectionModel().getSelectedItem() != null)
            this.iniciar.setDisable(false);
    }

    public void avaliacaoSelected(){
        if(this.alunos.getSelectionModel().getSelectedItem() != null)
            this.iniciar.setDisable(false);
    }

    public void iniciar(){
        if(MainController.getInstance().startAvaliacao(this)){
            this.windowController.getStage().hide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.iniciar.setDisable(true);
        MainController.getInstance().loadAlunos(this);
        MainController.getInstance().loadAvaliacoes(this);
    }

    public Aluno getAluno() {
        return alunos.getSelectionModel().getSelectedItem();
    }

    public void setAlunos(Collection<Aluno> alunos) {
        this.alunos.getItems().clear();
        this.alunos.getItems().addAll(alunos);
    }

    public Avaliacao getAvaliacao() {
        return avaliacoes.getSelectionModel().getSelectedItem();
    }

    public void setAvaliacoes(Collection<Avaliacao> avaliacoes) {
        this.avaliacoes.getItems().clear();
        this.avaliacoes.getItems().addAll(avaliacoes);
    }
}
