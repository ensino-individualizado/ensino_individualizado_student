package Principal;

import DAO.mysql.generatedclasses.tables.Aluno;
import Ferramentas.GerenciadorBD;
import Visao.ControllerHierarchy.ApplicationController;
import Visao.Mensagens.JanelaDeMensagem;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends ApplicationController {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            GerenciadorBD.conectar();
            GerenciadorBD.getContext().select(Aluno.ALUNO.IDALUNO).from(Aluno.ALUNO).execute();
        }
        catch(SQLException CE){
            System.out.println(CE.getClass() + " ---------------------------------------------------------------------------------- ");
            JanelaDeMensagem.mostrarExcessao("Erro ao conectar com o banco de dados", "Erro de conexão com o banco de dados.", CE);
            System.exit(0);
        }
        catch(FileNotFoundException FNF){
            JanelaDeMensagem.mostrarExcessao("Erro ao carregar dados", "Ocorreu um erro ao abir o arquivo de configurações.", FNF);
            System.exit(0);
        }
        catch (IOException IOE) {
            JanelaDeMensagem.mostrarExcessao("Erro ao carregar dados", "Ocorreu um erro ao ler o arquivo de configurações.", IOE);
            System.exit(0);
        }

        super.start(primaryStage, "/Visao/Principal/Principal.fxml", "Aluno");
        super.show();
    }


    public static void main(String[] args) {
        //Start the applocation
        launch(args);
    }
}
