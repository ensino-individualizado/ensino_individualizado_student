package DAO;

import DAO.mysql.generatedclasses.tables.records.BlocoRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Bloco;
import Modelo.Tentativa;
import org.jooq.Record1;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import static DAO.mysql.generatedclasses.tables.AvaliacaoBloco.AVALIACAO_BLOCO;
import static DAO.mysql.generatedclasses.tables.Bloco.BLOCO;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class BlocoDAO extends DAO<Bloco> {
    @Override
    public boolean novo(Bloco novo) throws SQLException, ClassNotFoundException {
        BlocoRecord bloco = GerenciadorBD.getContext().insertInto(BLOCO, BLOCO.IDTIPOBLOCO)
                                                      .values(novo.getTipo().getId())
                                                      .returning().fetchOne();

        if(bloco != null){
            novo.setId(bloco.getIdbloco());

            for(Tentativa tentativa : novo.getTentativas()){
                TentativaDAO.getInstance().novo(tentativa, novo.getId());
            }

            return (true);
        }

        return (false);
    }

    public Bloco.TYPE getTypeById(int id){
        switch (id) {
            case (1):
                return Bloco.TYPE.APONTAR_PALAVRA;
            case (2):
                return Bloco.TYPE.APONTAR_FIGURA;
            case (3):
                return Bloco.TYPE.COMPARACAO_PALAVRA_FIGURAS;
            case (4):
                return Bloco.TYPE.COMPARACAO_PALAVRAS_FIGURA;
            case (5):
                return Bloco.TYPE.COPIA;
            case (6):
                return Bloco.TYPE.DITADO;
            case (7):
                return Bloco.TYPE.LEITURA;
            case (8):
                return Bloco.TYPE.NOMEAR_IMAGEM;
        }

        return (null);
    }

    @Override
    public Bloco obter(int id) throws SQLException, ClassNotFoundException {

        BlocoRecord record = GerenciadorBD.getContext().selectFrom(BLOCO).where(BLOCO.IDBLOCO.eq(id)).fetchOne();

        if(record != null){
            Bloco loaded = new Bloco(getTypeById(record.getIdtipobloco()), TentativaDAO.getInstance().obterTodosByIdBloco(record.getIdbloco()));
            loaded.setId(record.getIdbloco());
            return (loaded);
        }

        return null;
    }

    public Collection<Bloco> obterTodosByAvaliacao(int id) throws SQLException, ClassNotFoundException {

        LinkedList<Bloco> blocos = null;

        Result<Record1<Integer>> ids = GerenciadorBD.getContext().select(AVALIACAO_BLOCO.IDBLOCO).from(AVALIACAO_BLOCO)
                                                                 .where(AVALIACAO_BLOCO.IDAVALIACAO.eq(id))
                                                                 .fetch();

        if(ids != null){

            blocos = new LinkedList<>();

            for(Record1<Integer> b : ids){
                Bloco loaded = this.obter(b.value1());
                blocos.add(loaded);
            }

            return (blocos);
        }

        return blocos;
    }


    @Override
    public Collection<Bloco> obterTodos() {
        return null;
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
    public boolean atualizar(Bloco atualizado) {
        return false;
    }

    public static BlocoDAO getInstance(){
        return (new BlocoDAO());
    }
}
