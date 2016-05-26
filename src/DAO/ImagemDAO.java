/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.ImagemRecord;
import Ferramentas.FileSaver;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.Imagem;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static DAO.mysql.generatedclasses.tables.Imagem.IMAGEM;
import static DAO.mysql.generatedclasses.tables.Palavra.PALAVRA;

/**
 *
 * @author Gustavo Freitas
 */
public class ImagemDAO extends DAO<Imagem>{

    private static final ImagemDAO instancia = new ImagemDAO();
    
    @Override
    public boolean novo(Imagem novo) {
        
        try { 
            byte[] imagem = Files.readAllBytes(Paths.get(novo.getLocal()));
            novo.setNomeOriginal(Paths.get(novo.getLocal()).getFileName().toString());
            
            ImagemRecord i = GerenciadorBD.getContext().insertInto(IMAGEM)
                                                       .set(IMAGEM.NOME_ORIGINAL, novo.getNomeOriginal())
                                                       .set(IMAGEM.IMAGEM_, imagem)
                                                       .returning().fetchOne();
            
            if(i != null){
                novo.setId(i.getIdimagem());
                return (true);
            }
        
        } catch (IOException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }

        return (false);
    }

    @Override
    public Imagem obter(int id) {
        
        Imagem imagem = null;
        
        Record2<Integer, String> cons = GerenciadorBD.getContext().select(IMAGEM.IDIMAGEM, IMAGEM.NOME_ORIGINAL)
                                                          .from(IMAGEM)
                                                          .where(IMAGEM.IDIMAGEM.eq(id))
                                                          .fetchOne();
        
        if(cons != null){
            imagem  = new Imagem(cons.value1(), cons.value2());
        }
        
        return (imagem);
    }
    
    public Imagem finalizarCarregamento(Imagem imagem, String destino){
        
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = GerenciadorBD.getConexao();

        String sql = "SELECT imagem, nome_original FROM imagem WHERE imagem.idimagem = ?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, imagem.getId());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                imagem.setNomeOriginal(rs.getString("nome_original"));
                imagem.setLocal(destino + imagem.getNomeOriginal());
                Blob som = rs.getBlob("imagem");
                FileSaver.getInstance().save(som.getBinaryStream(), imagem.getLocal(), FileSaver.BUFFER_TAMANHO_PADRAO);
            }
            
            rs.close();
            stmt.close();
        }
        catch (SQLException | IOException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            //GerenciadorBD.fecharConexao();
        }
        
        return (imagem);
    }
    
    @Override
    public Collection<Imagem> obterTodos() {
        
        ArrayList<Imagem> imagens = new ArrayList<>();
        
        Result<Record1<Integer>> cons = GerenciadorBD.getContext().select(IMAGEM.IDIMAGEM)
                                                          .from(IMAGEM)
                                                          .fetch();
        
        for(Record1<Integer> i : cons){
            Imagem imagem  = new Imagem();
            imagem.setId(i.value1());
            imagens.add(imagem);
        }
        
        return (imagens);
    }

    @Override
    public boolean apagar(int id) {
        
        Record1<Integer> cons = GerenciadorBD.getContext().select(IMAGEM.IDIMAGEM)
                                                          .from(IMAGEM).join(PALAVRA).on(PALAVRA.IDIMAGEM.eq(IMAGEM.IDIMAGEM))
                                                          .fetchOne();
        
        if(cons == null){
            GerenciadorBD.getContext().deleteFrom(IMAGEM).where(IMAGEM.IDIMAGEM.eq(id)).execute();
            return (true);
        }
        
        return (false);
    
    }
    

    @Override
    public boolean apagarEmCascata(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean atualizar(Imagem imagem) {

        try { 
            byte[] img = Files.readAllBytes(Paths.get(imagem.getLocal()));
            imagem.setNomeOriginal(Paths.get(imagem.getLocal()).getFileName().toString());
            
            GerenciadorBD.getContext().update(IMAGEM)
                                      .set(IMAGEM.NOME_ORIGINAL, imagem.getNomeOriginal())
                                      .set(IMAGEM.IMAGEM_, img)
                                      .where(IMAGEM.IDIMAGEM.eq(imagem.getId()))
                                      .execute();

        
        } catch (IOException ex) {
            Logger.getLogger(AudioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }

        return (true);
    }
    
    public static ImagemDAO getInstance(){
        return (ImagemDAO.instancia);
    }
}
