package DAO;

import DAO.mysql.generatedclasses.tables.records.AlternativaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Alternativa;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Alternativa.ALTERNATIVA;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class AlternativaDAO extends DAO<Alternativa> {

    @Override
    public boolean novo(Alternativa novo) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Não suportado.");
    }

    public boolean novo(Alternativa novo, int idTentativa) throws SQLException, ClassNotFoundException {
        System.out.println("IDPALAVRA: " + novo.getPalavra().getId() + "\n IDCATEGORIA: " + novo.getPalavra().getCategoria().getId() + "\n IDAUDIO: " + novo.getPalavra().getAudio().getId() + "\n IDIMAGEM: " + novo.getPalavra().getImagem().getId());
        AlternativaRecord nova = GerenciadorBD.getContext().insertInto(ALTERNATIVA, ALTERNATIVA.IDTENTATIVA,
                                                                       ALTERNATIVA.PALAVRA_IDPALAVRA,
                                                                       ALTERNATIVA.PALAVRA_IDCATEGORIA,
                                                                       ALTERNATIVA.PALAVRA_IDAUDIO,
                                                                       ALTERNATIVA.PALAVRA_IDIMAGEM,
                                                                       ALTERNATIVA.RESPOSTA)
                .values(idTentativa, novo.getPalavra().getId(), novo.getPalavra().getCategoria().getId(),
                        novo.getPalavra().getAudio().getId(), novo.getPalavra().getImagem().getId(),
                        (byte) (novo.isResposta() ? 1 : 0))
                .returning().fetchOne();

        if(nova != null){
            novo.setId(nova.getIdalternativa());
            return (true);
        }

        return (false);
    }

    @Override
    public Alternativa obter(int id) throws SQLException, ClassNotFoundException {
        Alternativa alternativa = null;

        AlternativaRecord result = GerenciadorBD.getContext().selectFrom(ALTERNATIVA).where(ALTERNATIVA.IDALTERNATIVA.eq(id)).fetchOne();

        if(result != null){
            boolean isResposta = result.getResposta() == 0 ? false : true;
            Alternativa tmp = new Alternativa(isResposta, PalavraDAO.getInstance().obter(result.getPalavraIdpalavra()));
            tmp.setId(result.getIdalternativa());
        }

        return (alternativa);
    }

    @Override
    public Collection<Alternativa> obterTodos() {
        return null;
    }

    public Collection<Alternativa> obterTodosByIdTentativa(int idTentativa){

        ArrayList<Alternativa> alternativas = null;

        Result<AlternativaRecord> result = GerenciadorBD.getContext().selectFrom(ALTERNATIVA).where(ALTERNATIVA.IDTENTATIVA.eq(idTentativa)).fetch();

        if(result != null){
            alternativas = new ArrayList<>();

            for(AlternativaRecord a : result){

                boolean isResposta = a.getResposta() == 0 ? false : true;

                Alternativa tmp = new Alternativa(isResposta, PalavraDAO.getInstance().obter(a.getPalavraIdpalavra()));
                tmp.setId(a.getIdalternativa());
                alternativas.add(tmp);
            }

        }

        return (alternativas);
    }

    @Override
    public boolean apagar(int id) {
        return false;
    }

    @Override
    public boolean apagarEmCascata(int id) {
        return false;
    }

    @Override
    public boolean atualizar(Alternativa atualizado) {
        return false;
    }

    public static AlternativaDAO getIntance(){
        return (new AlternativaDAO());
    }
}
