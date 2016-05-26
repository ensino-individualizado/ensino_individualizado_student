/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas;

import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Gustavo Freitas
 */
public class Configuracao {
    
    public static String CONTROLADOR = null;
    public static String URL = null;
    public static String USUARIO = null;
    public static String SENHA = null;
    
    public static boolean carregarFontes(){
        javafx.scene.text.Font robotoBold = Font.loadFont(Configuracao.class.getResource("/resources/fonts/RobotoDraft-Bold.ttf").toExternalForm(), 10);
        javafx.scene.text.Font robotoItalic = Font.loadFont(Configuracao.class.getResource("/resources/fonts/RobotoDraft-Italic.ttf").toExternalForm(), 10);
        javafx.scene.text.Font robotoRegular = Font.loadFont(Configuracao.class.getResource("/resources/fonts/RobotoDraft-Regular.ttf").toExternalForm(), 10);
        javafx.scene.text.Font robotoBoldItalic = Font.loadFont(Configuracao.class.getResource("/resources/fonts/RobotoDraft-BoldItalic.ttf").toExternalForm(), 10);

        if(robotoBold == null || robotoBoldItalic == null || robotoItalic == null || robotoRegular == null){
            return (false);
        }
        return (true);
    }
    
    public static void carregarDadosDoBanco() throws FileNotFoundException, IOException{

        BufferedReader br = new BufferedReader(new FileReader("config.txt"));

        Configuracao.CONTROLADOR = br.readLine();
        Configuracao.URL = br.readLine();
        Configuracao.USUARIO = br.readLine();
        Configuracao.SENHA = br.readLine();

        br.close();
    }
    
}
