/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.AudioRecord;
import Ferramentas.FileSaver;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.Midia.Audio;
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

import static DAO.mysql.generatedclasses.tables.Audio.AUDIO;
import static DAO.mysql.generatedclasses.tables.Palavra.PALAVRA;

/**
 *
 * @author Gustavo Freitas
 */
public class AudioDAO extends DAO<Audio>{

    private static final AudioDAO instancia = new AudioDAO();
    
    @Override
    public boolean novo(Audio novo) {
        try { 
            byte[] audio = Files.readAllBytes(Paths.get(novo.getLocal()));
            novo.setNomeOriginal(Paths.get(novo.getLocal()).getFileName().toString());
            
            AudioRecord a = GerenciadorBD.getContext().insertInto(AUDIO)
                                           .set(AUDIO.NOME_ORIGINAL, novo.getNomeOriginal())
                                           .set(AUDIO.TRANSCRICAO, novo.getTranscricao())
                                           .set(AUDIO.SOM, audio)
                                           .returning().fetchOne();
            
            if(a != null){
                novo.setId(a.getIdaudio());
                return (true);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AudioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
        
        return (false);
    }

    @Override
    public Audio obter(int id) {
        
        Audio audio = null;
        Record2<Integer, String> a = GerenciadorBD.getContext().select(AUDIO.IDAUDIO, AUDIO.TRANSCRICAO).from(AUDIO).where(AUDIO.IDAUDIO.eq(id)).fetchOne();
        
        if(a != null){
            audio = new Audio(a.value1(), a.value2());
        }
        return (audio);
    }
    
    public Audio finalizarCarregamento(Audio audio, String destino){
        ResultSet rs = null;
        OutputStream saida = null;
        PreparedStatement stmt = null;
        Connection conexao = GerenciadorBD.getConexao();

        String sql = "SELECT som, nome_original FROM audio WHERE audio.idaudio = ?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, audio.getId());
            rs = stmt.executeQuery();
            
            if(rs.next()){

                audio.setNomeOriginal(rs.getString("nome_original"));

                audio.setLocal(destino + audio.getNomeOriginal());

                Blob som = rs.getBlob("som");
                FileSaver.getInstance().save(som.getBinaryStream(), audio.getLocal(), FileSaver.BUFFER_TAMANHO_PADRAO);
                som.free();
            }
            
            rs.close();
            stmt.close();
        }
        catch (SQLException | IOException ex) {
            Logger.getLogger(AudioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            //GerenciadorBD.fecharConexao();
        }
        
        return (audio);
    }
    
    @Override
    public Collection<Audio> obterTodos() {
        
        ArrayList<Audio> audios = new ArrayList<>();

        Result<Record2<Integer, String>> cons = GerenciadorBD.getContext().select(AUDIO.IDAUDIO, AUDIO.TRANSCRICAO).from(AUDIO).fetch();
        
        for(Record2<Integer, String> a : cons){
            audios.add(new Audio(a.value1(), a.value2()));
        }
        
        return (audios);
    }

    public Audio obterPelaPalavra(int idPalavra){
        
        Audio audio = null;
        
        Record2<Integer, String> v = GerenciadorBD.getContext().select(AUDIO.IDAUDIO, AUDIO.TRANSCRICAO)
                                                               .from(AUDIO)
                                                               .join(PALAVRA).on(PALAVRA.IDPALAVRA.eq(idPalavra))
                                                               .fetchOne();
        
        if(v != null){
            audio = new Audio(v.value1(), v.value2());
        }
        
        return (audio);
    }
    
    @Override
    public boolean apagar(int id) {
        
        if(GerenciadorBD.getContext().select(AUDIO.IDAUDIO)
                                     .from(AUDIO).join(PALAVRA).on(AUDIO.IDAUDIO.eq(PALAVRA.IDAUDIO))
                                     .where(AUDIO.IDAUDIO.eq(id))
                                     .fetchOne() != null){
            GerenciadorBD.getContext().deleteFrom(AUDIO).where(AUDIO.IDAUDIO.eq(id)).execute();
            
            return (true);
        }
        
        return (false);
    }

    @Override
    public boolean apagarEmCascata(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean atualizar(Audio audio) {
        
        try { 
            byte[] som = Files.readAllBytes(Paths.get(audio.getLocal()));
            audio.setNomeOriginal(Paths.get(audio.getLocal()).getFileName().toString());
            
            GerenciadorBD.getContext().update(AUDIO)
                                      .set(AUDIO.NOME_ORIGINAL, audio.getNomeOriginal())
                                      .set(AUDIO.TRANSCRICAO, audio.getTranscricao())
                                      .set(AUDIO.SOM, som)
                                      .set(AUDIO.NOME_ORIGINAL, audio.getNomeOriginal())
                                      .where(AUDIO.IDAUDIO.eq(audio.getId()))
                                      .execute();
        
        } catch (IOException ex) {
            Logger.getLogger(AudioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
        
        return (true);
    }
    
    public static AudioDAO getInstance(){
        return (AudioDAO.instancia);
    }
    
}
