package DAO;

import DAO.mysql.generatedclasses.tables.records.AvaliacaoRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Avaliacao;
import Modelo.Bloco;
import Modelo.Construtores.AvaliacaoBuilder;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Avaliacao.AVALIACAO;
import static DAO.mysql.generatedclasses.tables.AvaliacaoBloco.AVALIACAO_BLOCO;

/**
 * Created by Gustavo Freitas on 30/10/2015.
 */
public class AvaliacaoDAO extends DAO<Avaliacao> {
    @Override
    public boolean novo(Avaliacao novo) throws SQLException, ClassNotFoundException {

        AvaliacaoRecord nova = GerenciadorBD.getContext().insertInto(AVALIACAO, AVALIACAO.DESCRICAO)
                                                         .values(novo.getDescricao())
                                                         .returning().fetchOne();

        if(nova != null){
            novo.setId(nova.getIdavaliacao());

            for(Bloco b : novo.getBlocos()){
                if(!BlocoDAO.getInstance().novo(b)){
                    System.out.println("Erro ao gravar bloco. " + b);
                }
                if(GerenciadorBD.getContext().insertInto(AVALIACAO_BLOCO, AVALIACAO_BLOCO.IDAVALIACAO, AVALIACAO_BLOCO.IDBLOCO)
                                          .values(novo.getId(), b.getId())
                                          .returning().fetchOne() == null){
                    System.out.println("Erro ao associar bloco com a avaliação. " + b);
                }
            }

            return (true);
        }

        return false;
    }

    @Override
    public Avaliacao obter(int id) throws SQLException, ClassNotFoundException {
        Avaliacao avaliacao = null;

        AvaliacaoRecord loaded = GerenciadorBD.getContext().selectFrom(AVALIACAO).where(AVALIACAO.IDAVALIACAO.eq(id)).fetchOne();

        if(loaded != null){
            AvaliacaoBuilder builder = new AvaliacaoBuilder();
            builder.setId(loaded.getIdavaliacao());
            builder.setDescricao(loaded.getDescricao());

            avaliacao = builder.getInstance();
        }

        return avaliacao;
    }

    @Override
    public Collection<Avaliacao> obterTodos() {

        ArrayList<Avaliacao> avaliacoes = null;

        Result<AvaliacaoRecord> result = GerenciadorBD.getContext().selectFrom(AVALIACAO).fetch();

        if(result != null) {

            avaliacoes = new ArrayList<>();

            for (AvaliacaoRecord loaded : result) {
                if (loaded != null) {
                    AvaliacaoBuilder builder = new AvaliacaoBuilder();
                    builder.setId(loaded.getIdavaliacao());
                    builder.setDescricao(loaded.getDescricao());
                    avaliacoes.add(builder.getInstance());
                }
            }
        }
        return avaliacoes;

    }

    public Avaliacao finalizarCarregamento(Avaliacao avaliacao) throws SQLException, ClassNotFoundException {
        for(Bloco b : BlocoDAO.getInstance().obterTodosByAvaliacao(avaliacao.getId())){
            avaliacao.addBloco(b);
        }
        return (avaliacao);
    }

    public ArrayList<Avaliacao> finalizarCarregamento(ArrayList<Avaliacao> avaliacoes) throws SQLException, ClassNotFoundException {
        for(Avaliacao a : avaliacoes) {
            this.finalizarCarregamento(a);
        }

        return (avaliacoes);
    }

    public Avaliacao restaurarAvaliacao(Avaliacao avaliacao, int idAluno) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Não é possível restaurar a avaliação.");
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
    public boolean atualizar(Avaliacao atualizado) {
        return false;
    }

    public static AvaliacaoDAO getInstance(){
        return (new AvaliacaoDAO());
    }
}
