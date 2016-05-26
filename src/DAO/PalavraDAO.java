/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.PalavraRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Construtores.PalavraBuilder;
import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.Silaba;
import org.jooq.Record6;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Palavra.PALAVRA;
import static DAO.mysql.generatedclasses.tables.PalavraSilaba.PALAVRA_SILABA;

/**
 *
 * @author Gustavo Freitas
 */
public class PalavraDAO extends DAO<Palavra>{

    private static final PalavraDAO instancia = new PalavraDAO();
    
    @Override
    public boolean novo(Palavra novo) {
        
        if(!AudioDAO.getInstance().novo(novo.getAudio()) || !VideoDAO.getInstance().novo(novo.getVideo()) || !ImagemDAO.getInstance().novo(novo.getImagem())){
            return (false);
        }

        for(Silaba s : novo.getSilabas()){
            if(!SilabaDAO.getInstance().novo(s)){
                return (false);
            }
        }
        
        PalavraRecord p = GerenciadorBD.getContext().insertInto(PALAVRA)
                                                    .set(PALAVRA.PALAVRA_, novo.getPalavra())
                                                    .set(PALAVRA.IDAUDIO, novo.getAudio().getId())
                                                    .set(PALAVRA.IDVIDEO, novo.getVideo().getId())
                                                    .set(PALAVRA.IDIMAGEM, novo.getImagem().getId())
                                                    .set(PALAVRA.IDCATEGORIA, novo.getCategoria().getId())
                                                    .returning().fetchOne();

        if(p != null){
            novo.setId(p.getIdpalavra());

            for(Silaba s : novo.getSilabas()){
                GerenciadorBD.getContext().insertInto(PALAVRA_SILABA)
                        .set(PALAVRA_SILABA.PALAVRA_IDPALAVRA, novo.getId())
                        .set(PALAVRA_SILABA.SILABA_IDSILABA, s.getId())
                        .execute();
            }

            return (true);
        }

        return (false);
    }

    public Palavra.CATEGORIA getCategoria(int id){
        switch (id) {
            case (1):
                return Palavra.CATEGORIA.DISSILABA_SIMPLES;

            case (2):
                return Palavra.CATEGORIA.TRISSILABA_SIMPLES;

            case (3):
                return Palavra.CATEGORIA.DIFICULDADE_DA_LINGUA;
        }

        return (null);
    }

    @Override
    public Palavra obter(int id) {
        
        Palavra palavra = null;
        
        PalavraRecord p = GerenciadorBD.getContext().selectFrom(PALAVRA)
                                                    .where(PALAVRA.IDPALAVRA.eq(id))
                                                    .fetchOne();
        
        if(p != null){

            PalavraBuilder palavraBuilder = new PalavraBuilder();
            palavraBuilder.novaPalavra(p.getIdpalavra(), p.getPalavra());
            palavraBuilder.adicionarSilabas(SilabaDAO.getInstance().obterPelaPalavra(id));
            if(p.getIdvideo() != null){
                palavraBuilder.adicionarVideo(VideoDAO.getInstance().obter(p.getIdvideo()));
            }
            palavraBuilder.adicionarAudio(AudioDAO.getInstance().obter(p.getIdaudio()));
            palavraBuilder.adicionarImagem(ImagemDAO.getInstance().obter(p.getIdimagem()));
            palavraBuilder.setCategoria(this.getCategoria(p.getIdcategoria()));
            palavra = palavraBuilder.getInstance();
        }

        return (palavra);
    }

    @Override
    public Collection<Palavra> obterTodos() {

        ArrayList<Palavra> palavras = new ArrayList<>();
        
        Result<PalavraRecord> cons = GerenciadorBD.getContext().selectFrom(PALAVRA)
                                                               .fetch();

        for(PalavraRecord p : cons){
            PalavraBuilder palavraBuilder = new PalavraBuilder();
            palavraBuilder.novaPalavra(p.getIdpalavra(), p.getPalavra());
            palavraBuilder.adicionarSilabas(SilabaDAO.getInstance().obterPelaPalavra(p.getIdpalavra()));
            palavraBuilder.adicionarVideo(VideoDAO.getInstance().obter(p.getIdvideo()));
            palavraBuilder.adicionarAudio(AudioDAO.getInstance().obter(p.getIdaudio()));
            palavraBuilder.adicionarImagem(ImagemDAO.getInstance().obter(p.getIdimagem()));
            palavraBuilder.setCategoria(this.getCategoria(p.getIdcategoria()));
            palavras.add(palavraBuilder.getInstance());
        }

        return (palavras);
    }

    public Collection<Palavra> obterByCategoria(Palavra.CATEGORIA categoria) {

        ArrayList<Palavra> palavras = new ArrayList<>();

        Result<Record6<Integer, Integer, Integer, Integer, Integer, String>> cons = GerenciadorBD.getContext()
                .select(PALAVRA.IDPALAVRA, PALAVRA.IDCATEGORIA, PALAVRA.IDAUDIO, PALAVRA.IDIMAGEM, PALAVRA.IDVIDEO, PALAVRA.PALAVRA_)
                .from(PALAVRA)
                .where(PALAVRA.IDCATEGORIA.eq(categoria.getId()))
                .fetch();

        for(Record6<Integer, Integer, Integer, Integer, Integer, String> p : cons){
            PalavraBuilder palavraBuilder = new PalavraBuilder();
            palavraBuilder.novaPalavra(p.value1(), p.value6());
            palavraBuilder.adicionarSilabas(SilabaDAO.getInstance().obterPelaPalavra(p.value1()));
            if(p.value5() != null){
                palavraBuilder.adicionarVideo(VideoDAO.getInstance().obter(p.value5()));
            }
            palavraBuilder.adicionarAudio(AudioDAO.getInstance().obter(p.value3()));
            palavraBuilder.adicionarImagem(ImagemDAO.getInstance().obter(p.value4()));
            palavraBuilder.setCategoria(this.getCategoria(p.value2()));
            palavras.add(palavraBuilder.getInstance());
        }

        return (palavras);
    }

    @Override
    public boolean apagar(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean apagarEmCascata(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean atualizar(Palavra palavra) {
        
        if(!AudioDAO.getInstance().atualizar(palavra.getAudio()) || !VideoDAO.getInstance().atualizar(palavra.getVideo()) || !ImagemDAO.getInstance().atualizar(palavra.getImagem())){
            return (false);
        }

        for(Silaba s : palavra.getSilabas()){
            if(!SilabaDAO.getInstance().atualizar(s)){
                return (false);
            }
        }

        GerenciadorBD.getContext().update(PALAVRA)
                                  .set(PALAVRA.PALAVRA_, palavra.getPalavra())
                                  .set(PALAVRA.IDCATEGORIA, palavra.getCategoria().ordinal() + 1)
                                  .execute();

        return (true);
    }

    public boolean carregarImagem(Palavra palavra, String destino){
        return (ImagemDAO.getInstance().finalizarCarregamento(palavra.getImagem(), destino) != null) ? true : false;
    }

    public boolean carregarVideo(Palavra palavra, String destino){
        return (VideoDAO.getInstance().finalizarCarregamento(palavra.getVideo(), destino) != null) ? true : false;
    }

    public boolean carregarAudio(Palavra palavra, String destino){
        return (AudioDAO.getInstance().finalizarCarregamento(palavra.getAudio(), destino) != null) ? true : false;
    }

    public static PalavraDAO getInstance(){
        return (PalavraDAO.instancia);
    }
}
