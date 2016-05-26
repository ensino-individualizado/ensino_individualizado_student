/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Mensagens;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Gustavo Freitas
 */
public class JanelaDeMensagem {
    
    public static void mostrarExcessao(String titulo, String cabecalho, Exception excessao){
            
        Alert erro = new Alert(Alert.AlertType.ERROR);
        TextArea mensagem;

        erro.setTitle(titulo);
        erro.setHeaderText(cabecalho);

        StringWriter sw = new StringWriter();
        excessao.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();

        mensagem = new TextArea();
        mensagem.setEditable(false);
        mensagem.setWrapText(true);
        mensagem.setText("Descrição: " + excessao.getMessage() + "\nRastro da Pilha:\n" + sw);

        mensagem.setMaxWidth(Double.MAX_VALUE);
        mensagem.setMaxHeight(Double.MAX_VALUE);
        mensagem.setMinWidth(1000.0); 
        mensagem.setMinHeight(500.0);

        GridPane.setVgrow(mensagem, Priority.ALWAYS);
        GridPane.setHgrow(mensagem, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(mensagem, 0, 0);

        erro.getDialogPane().setExpandableContent(expContent);
        erro.showAndWait();
    }
    
    public static void mostrarSucesso(String titulo, String cabecalho, String conteudo){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);

        alert.showAndWait();
    }
    
    public static void mostrarErro(String titulo, String cabecalho, String conteudo){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);

        alert.showAndWait();
    }
}
