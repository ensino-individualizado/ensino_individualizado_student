package DAO;

import DAO.mysql.generatedclasses.tables.records.EnunciadoRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Enunciado;
import Modelo.RecursoDidatico.PlayList.PlayList;

import java.sql.SQLException;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Enunciado.ENUNCIADO;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class EnunciadoDAO extends DAO<Enunciado> {

    @Override
    public boolean novo(Enunciado novo) throws SQLException, ClassNotFoundException {

        if(novo.getEnunciado().getId() == -1) {
            PlayListDAO.getInstance().novo(novo.getEnunciado());
        }

        EnunciadoRecord enunciado = GerenciadorBD.getContext().insertInto(ENUNCIADO, ENUNCIADO.DESCRICAO, ENUNCIADO.IDPLAYLIST)
                                                              .values(novo.getDescricao(), novo.getEnunciado().getId())
                                                              .returning().fetchOne();

        if(enunciado != null){
            novo.setId(enunciado.getIdenunciado());
            return (true);
        }

        return (false);
    }

    @Override
    public Enunciado obter(int id) throws SQLException, ClassNotFoundException {

        Enunciado enunciado = null;

        EnunciadoRecord record = GerenciadorBD.getContext().selectFrom(ENUNCIADO).where(ENUNCIADO.IDENUNCIADO.eq(id)).fetchOne();

        if(record != null){
            PlayList playList = PlayListDAO.getInstance().obter(record.getIdplaylist());
            if(playList != null) {
                enunciado = new Enunciado(record.getDescricao(), playList);
                enunciado.setId(record.getIdenunciado());
            }
        }

        return (enunciado);
    }

    @Override
    public Collection<Enunciado> obterTodos() {
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
    public boolean atualizar(Enunciado atualizado) {
        return false;
    }

    public static EnunciadoDAO getInstance(){
        return (new EnunciadoDAO());
    }
}
