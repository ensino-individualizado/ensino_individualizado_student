package DAO;

import DAO.mysql.generatedclasses.tables.records.TentativaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Alternativa;
import Modelo.Tentativa;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import static DAO.mysql.generatedclasses.tables.Tentativa.TENTATIVA;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class TentativaDAO extends DAO<Tentativa> {

    private static final TentativaDAO instance = new TentativaDAO();

    @Override
    public boolean novo(Tentativa novo) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Não suportado.");
    }

    public boolean novo(Tentativa novo, int idBloco) throws SQLException, ClassNotFoundException {

        EnunciadoDAO.getInstance().novo(novo.getEnunciado());

        TentativaRecord nova = GerenciadorBD.getContext().insertInto(TENTATIVA, TENTATIVA.IDBLOCO, TENTATIVA.IDENUNCIADO)
                                            .values(idBloco, novo.getEnunciado().getId())
                                            .returning().fetchOne();

        if(nova != null){
            novo.setId(nova.getIdtentativa());

            for(Alternativa alternativa : novo.getAlternativas()){
                AlternativaDAO.getIntance().novo(alternativa, novo.getId());
            }

            return (true);
        }

        return (false);
    }

    @Override
    public Tentativa obter(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Collection<Tentativa> obterTodos() {
        return null;
    }


    public LinkedList<Tentativa> obterTodosByIdBloco(int id) throws SQLException, ClassNotFoundException {

        LinkedList<Tentativa> tentativas = null;
        Result<TentativaRecord> result = GerenciadorBD.getContext().selectFrom(TENTATIVA).where(TENTATIVA.IDBLOCO.eq(id)).orderBy(TENTATIVA.IDTENTATIVA).fetch();

        if(result != null) {

            tentativas = new LinkedList<>();

            for (TentativaRecord t : result) {
                Tentativa loaded = new Tentativa(AlternativaDAO.getIntance().obterTodosByIdTentativa(t.getIdtentativa()), EnunciadoDAO.getInstance().obter(t.getIdenunciado()));
                loaded.setId(t.getIdtentativa());
                tentativas.add(loaded);
            }
        }
        return (tentativas);
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
    public boolean atualizar(Tentativa atualizado) {
        return false;
    }

    public static TentativaDAO getInstance(){
        return (instance);
    }
}
