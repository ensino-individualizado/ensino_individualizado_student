/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.VideoRecord;
import Ferramentas.FileSaver;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.Midia.Video;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;
import org.omg.CORBA.portable.OutputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static DAO.mysql.generatedclasses.tables.Palavra.PALAVRA;
import static DAO.mysql.generatedclasses.tables.Video.VIDEO;

/**
 *
 * @author Gustavo Freitas
 */
public class VideoDAO extends DAO<Video>{

    private static final VideoDAO instancia = new VideoDAO();

    @Override
    public boolean novo(Video novo) {
        
        try { 
            byte[] vid = Files.readAllBytes(Paths.get(novo.getLocal()));
            novo.setNomeOriginal(Paths.get(novo.getLocal()).getFileName().toString());
            
            VideoRecord a = GerenciadorBD.getContext().insertInto(VIDEO)
                                           .set(VIDEO.NOME_ORIGINAL, novo.getNomeOriginal())
                                           .set(VIDEO.DESCRICAO, novo.getDescricao())
                                           .set(VIDEO.VIDEO_, vid)
                                           .returning().fetchOne();
            
            if(a != null){
                novo.setId(a.getIdvideo());
                return (true);
            }
        } catch (IOException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
        
        return (true);
    }

    @Override
    public Video obter(int id) {
        
        Video video = null;
        
        Record2<Integer, String> cons = GerenciadorBD.getContext().select(VIDEO.IDVIDEO, VIDEO.DESCRICAO)
                                                                  .from(VIDEO)
                                                                  .where(VIDEO.IDVIDEO.eq(id))
                                                                  .fetchOne();
        
        if(cons != null){ 
            video = new Video(cons.value1(), cons.value2());
        }
        
        return (video);
    }
    
    public Video finalizarCarregamento(Video video, String destino){
        ResultSet rs = null;
        OutputStream saida = null;
        PreparedStatement stmt = null;
        Connection conexao = GerenciadorBD.getConexao();

        String sql = "SELECT video, nome_original FROM video WHERE video.idvideo = ?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, video.getId());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                video.setNomeOriginal(rs.getString("nome_original"));
                video.setLocal(destino + video.getNomeOriginal());

                Blob videoBlob = rs.getBlob("video");
                FileSaver.getInstance().save(videoBlob.getBinaryStream(), video.getLocal(), FileSaver.BUFFER_TAMANHO_PADRAO);
            }
            
            rs.close();
            stmt.close();
        }
        catch (SQLException | IOException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            //GerenciadorBD.fecharConexao();
        }
        
        return (video);
    }
    
    @Override
    public Collection<Video> obterTodos() {
        ArrayList<Video> videos = new ArrayList<>();
        
        Result<Record2<Integer, String>> cons = GerenciadorBD.getContext().select(VIDEO.IDVIDEO, VIDEO.DESCRICAO)
                                                                  .from(VIDEO)
                                                                  .fetch();
        
        for(Record2<Integer, String> v : cons){ 
            videos.add(new Video(v.value1(), v.value2()));
        }
        
        return (videos);
    }

    @Override
    public boolean apagar(int id) {

        Record1<Integer> cons = GerenciadorBD.getContext().select(VIDEO.IDVIDEO)
                                                          .from(VIDEO).join(PALAVRA).on(PALAVRA.IDVIDEO.eq(VIDEO.IDVIDEO))
                                                          .where(VIDEO.IDVIDEO.eq(id))
                                                          .fetchOne();
        
        if(cons == null){
            GerenciadorBD.getContext().deleteFrom(VIDEO).where(VIDEO.IDVIDEO.eq(id)).execute();
            return (true);
        }
        
        return (false);
    }

    @Override
    public boolean apagarEmCascata(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean atualizar(Video video) {
        
        try { 
            byte[] vid = Files.readAllBytes(Paths.get(video.getLocal()));
            video.setNomeOriginal(Paths.get(video.getLocal()).getFileName().toString());
            
            GerenciadorBD.getContext().update(VIDEO)
                                      .set(VIDEO.NOME_ORIGINAL, video.getNomeOriginal())
                                      .set(VIDEO.DESCRICAO, video.getDescricao())
                                      .set(VIDEO.VIDEO_, vid)
                                      .where(VIDEO.IDVIDEO.eq(video.getId()))
                                      .execute();
        
        } catch (IOException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
        
        return (true);
    }
    
    public static VideoDAO getInstance(){
        return (VideoDAO.instancia);
    }
    
}
