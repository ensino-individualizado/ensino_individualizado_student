package Controle;

import DAO.AlunoDAO;
import DAO.AvaliacaoDAO;
import Modelo.*;
import Visao.ControllerHierarchy.ComparacaoController;
import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.TentativaRegionController;
import Visao.ControllerHierarchy.WindowController;
import Visao.Mensagens.JanelaDeMensagem;
import Visao.Principal.AvaliacaoAplicatorController;
import Visao.Principal.PrincipalController;
import Visao.RegionLoader;
import Visao.Tentativas.*;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Created by Gustavo Freitas on 02/11/2015.
 */
public class AplicadorController {

    //Para controle da avaliação e do aluno atuais
    Aluno aluno = null;
    Avaliacao avaliacao = null;
    RelatorioAvaliacao relatorioFinal = null;

    //Para controle da janela principal
    PrincipalController main = null;

    //Para controle da execução
    Bloco blocoAtual = null;
    Tentativa tentativaAtual = null;
    Iterator<Bloco> blocoIterator = null;
    Iterator<Tentativa> tentativaIterator = null;

    public AplicadorController(int idAvaliacao, Aluno aluno, PrincipalController main) throws SQLException, ClassNotFoundException {
        this.avaliacao = AvaliacaoDAO.getInstance().obter(idAvaliacao);
        this.main = main;
        this.aluno = aluno;
        this.relatorioFinal = new RelatorioAvaliacao();
    }

    public void startAvaliacao(){

        try {
            //Se não tiver começado a avaliação
            if(!AlunoDAO.getInstance().verificarContinuacaoAvaliacao(aluno, avaliacao.getId())) {
                //Define que o aluno iniciou a avaliação no BD
                AlunoDAO.getInstance().iniciarAvaliacao(this.aluno, this.avaliacao.getId());

                //Termina de carregar a avaliação considerando que a avaliação nunca foi começada
                this.avaliacao = AvaliacaoDAO.getInstance().finalizarCarregamento(this.avaliacao);
            }
            //Carrega as tentativas que ainda não foram respondidas
            else{
                this.avaliacao = AvaliacaoDAO.getInstance().restaurarAvaliacao(this.avaliacao, aluno.getId());
            }

            //Obtém o iterador dos blocos
            blocoIterator = avaliacao.getBlocos().iterator();

            //Se existem blocos
            if(blocoIterator.hasNext()){
                //Obtém o próximo bloco
                blocoAtual = blocoIterator.next();

                //Obtém o iterador das tentativas
                tentativaIterator = blocoAtual.getTentativas().iterator();
            }

            //Abre uma janela para a avaliação
            Stage stage = new Stage();
            RegionController controlador = RegionLoader.getInstance().load(main, "/Visao/Principal/AvaliacaoAplicator.fxml");
            controlador.setInterfaceController(new WindowController(stage, controlador));
            ((AvaliacaoAplicatorController) controlador).setController(this);
            Scene scene = new Scene(controlador.getRegion());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setFullScreen(true);
            stage.setWidth(1360);
            stage.setHeight(830);
            stage.show();

            ((AvaliacaoAplicatorController)controlador).proximo();

            //Oculta a janela principal.
            main.getWindowController().getStage().hide();

        } catch (Exception e) {
            JanelaDeMensagem.mostrarExcessao("Erro", "Ocorreu um problema ao inciar a avaliação.", e);
        }
    }

    public TentativaRegionController proximaTentativa(AvaliacaoAplicatorController panel){

        TentativaRegionController reg = null;

        //Se acabaram as tentativas do bloco atual e ainda existem blocos para processar,
        //obtém o iterador das tentativas do próximo bloco
        if(!tentativaIterator.hasNext() && blocoIterator.hasNext()){
            blocoAtual = blocoIterator.next();
            tentativaIterator = blocoAtual.getTentativas().iterator();
        }

        if(tentativaIterator.hasNext()){
            //Obtém a próxima tentativa
            tentativaAtual = tentativaIterator.next();

            try {
                switch (blocoAtual.getTipo().getId()) {
                    case 1:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/ApontarFiguraForm.fxml");
                        reg.setAlternativa(tentativaAtual.getAlternativas());
                        break;
                    case 2:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/ApontarPalavraForm.fxml");
                        reg.setAlternativa(tentativaAtual.getAlternativas());
                        break;
                    case 3:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/EscolherFiguraForm.fxml");
                        reg.setAlternativa(tentativaAtual.getAlternativas());
                        break;
                    case 4:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/EscolherPalavraForm.fxml");
                        reg.setAlternativa(tentativaAtual.getAlternativas());
                        break;
                    case 5:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/Copia.fxml");
                        reg.setAlternativa(tentativaAtual.getResposta());
                        break;
                    case 6:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/Ditado.fxml");
                        reg.setAlternativa(tentativaAtual.getResposta());
                        break;
                    case 7:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/Leitura.fxml");
                        reg.setAlternativa(tentativaAtual.getResposta());
                        break;
                    case 8:
                        reg = (TentativaRegionController) RegionLoader.getInstance().load(panel, "/Visao/Tentativas/NomearImagem.fxml");
                        reg.setAlternativa(tentativaAtual.getResposta());
                        break;
                }
                reg.setEnunciado(tentativaAtual.getEnunciado());
            }
            catch (Exception e){
                JanelaDeMensagem.mostrarExcessao("Erro", "Ocorreu um problema ao carregar a próxima tentativa.", e);
            }
        }
        //Terminou a avaliação
        else{
            //Fecha a janela do aplicador e mostra a janela principal
            panel.getWindowController().hide();

            //Informa o fim da avaliação
            JanelaDeMensagem.mostrarSucesso("Avaliação finalizada!", "Todas as questões da avaliação foram finalizadas.",
                            "Relatório simplificado:\n"
                                    + "Acertos: " + relatorioFinal.getQtdAcertos() + " - Taxa: " + relatorioFinal.calcularTaxaDeAcertos() + "%\n"
                                    + "Erros: " + relatorioFinal.getQtdErros() + " - Taxa: " + relatorioFinal.calcularTaxaDeErros() + "%\n"
                                    + "Total: " + relatorioFinal.getQtdAlternativas());

            //Exibe a janela principal
            this.main.getWindowController().show();
        }

        return (reg);
    }

    public void responder(NomearImagemController controller){
        System.out.println("Nomear Imagem - Respondido");
        AlunoDAO.getInstance().responder(aluno, avaliacao.getId(), tentativaAtual.getId(), tentativaAtual.getResposta().getId(), true);
        ((AvaliacaoAplicatorController) controller.getFatherController()).proximo();
        relatorioFinal.acertou();
    }

    public void responder(LeituraController controller){
        System.out.println("Leitura - Respondido");
        AlunoDAO.getInstance().responder(aluno, avaliacao.getId(), tentativaAtual.getId(), tentativaAtual.getResposta().getId(), true);
        ((AvaliacaoAplicatorController) controller.getFatherController()).proximo();
        relatorioFinal.acertou();
    }

    public void responder(CopiaController controller){

        boolean acertou = false;

        if(controller.getResposta().compareToIgnoreCase(tentativaAtual.getResposta().getPalavra().getPalavra()) == 0){
            acertou = true;
            relatorioFinal.acertou();
        }
        else{
            relatorioFinal.errou();
        }

        System.out.println("Cópia - Respondido - " + acertou);
        AlunoDAO.getInstance().responder(aluno, avaliacao.getId(), tentativaAtual.getId(), tentativaAtual.getResposta().getId(), acertou);
        ((AvaliacaoAplicatorController) controller.getFatherController()).proximo();
    }

    public void responder(DitadoController controller){

        boolean acertou = false;

        if(controller.getResposta().compareToIgnoreCase(tentativaAtual.getResposta().getPalavra().getPalavra()) == 0){
            acertou = true;
            relatorioFinal.acertou();
        }
        else {
            relatorioFinal.errou();
        }

        System.out.println("Ditado - Respondido - " + acertou);
        AlunoDAO.getInstance().responder(aluno, avaliacao.getId(), tentativaAtual.getId(), tentativaAtual.getResposta().getId(), acertou);
        ((AvaliacaoAplicatorController) controller.getFatherController()).proximo();
    }

    public void responder(ComparacaoController controller, Alternativa resposta){
        boolean acertou = false;

        if(this.tentativaAtual.getResposta().equals(resposta)) {
            acertou = true;
            relatorioFinal.acertou();
        }
        else{
            relatorioFinal.errou();
        }

        System.out.println("Escolher Figura - Respondido - " + acertou);
        AlunoDAO.getInstance().responder(aluno, avaliacao.getId(), tentativaAtual.getId(), tentativaAtual.getResposta().getId(), acertou);
        ((AvaliacaoAplicatorController) controller.getFatherController()).proximo();
    }
}
