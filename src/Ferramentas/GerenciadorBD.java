/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gustavo Freitas
 */
public class GerenciadorBD {
    
    private static Connection conexao = null;
    private static DSLContext dslContext = null;
    
    public static Connection conectar() throws NullPointerException, ClassNotFoundException, SQLException, IOException, ConnectException {

        Configuracao.carregarDadosDoBanco();

        if(Configuracao.CONTROLADOR == null){
            throw (new NullPointerException("Os dados do BD não foram carregados."));
        }

        if(GerenciadorBD.conexao == null){
            Class.forName(Configuracao.CONTROLADOR);
            GerenciadorBD.conexao = DriverManager.getConnection(Configuracao.URL, Configuracao.USUARIO, Configuracao.SENHA);
            GerenciadorBD.dslContext = DSL.using(GerenciadorBD.conexao, SQLDialect.MYSQL);
        }
        return (GerenciadorBD.conexao);
    }
    
    public static DSLContext getContext() {
        
        if(GerenciadorBD.conexao == null){
            try {
                GerenciadorBD.conectar();
            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        
        return (GerenciadorBD.dslContext);
    }
    
    public static void fecharConexao(){
        
        if(GerenciadorBD.conexao != null){
            try {
                GerenciadorBD.conexao.close();
            } 
            catch (SQLException ex) {
                System.out.println("ERRO AO FECHAR A CONEXÃO!");
            }
            
            GerenciadorBD.conexao = null;
        }
    }

    public static Connection getConexao() {
        return conexao;
    }
}
