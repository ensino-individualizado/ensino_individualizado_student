package Controle;

import DAO.AlunoDAO;
import DAO.AvaliacaoDAO;
import Visao.Principal.PrincipalController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class MainController {

    private static MainController instance = new MainController();

    public void loadAlunos(PrincipalController controller){
        controller.setAlunos(AlunoDAO.getInstance().obterTodos());
    }

    public void loadAvaliacoes(PrincipalController controller){
        controller.setAvaliacoes(AvaliacaoDAO.getInstance().obterTodos());
    }

    public boolean startAvaliacao(PrincipalController main){

        if(main.getAvaliacao() != null && main.getAluno() != null){
            try {
                Stage temp = new Stage();
                Pane root = new Pane();

                ProgressIndicator indicador = new ProgressIndicator();
                Label label = new Label("Carregando avaliação...");

                indicador.setLayoutX(50);
                indicador.setLayoutY(15);
                label.setLayoutX(15);
                label.setLayoutY(70);

                root.getChildren().addAll(indicador, label);
                Scene cena = new Scene(root, 150, 100);
                temp.setScene(cena);
                temp.setAlwaysOnTop(true);
                temp.show();

                AplicadorController aplicador = new AplicadorController(main.getAvaliacao().getId(), main.getAluno(), main);
                temp.close();
                aplicador.startAvaliacao();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return (true);
    }

    public static MainController getInstance() {
        return instance;
    }
}
