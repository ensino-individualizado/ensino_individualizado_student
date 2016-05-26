/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.AlunoRecord;
import DAO.mysql.generatedclasses.tables.records.RealizaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Aluno;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static DAO.mysql.generatedclasses.tables.Aluno.ALUNO;
import static DAO.mysql.generatedclasses.tables.Realiza.REALIZA;
import static DAO.mysql.generatedclasses.tables.Resposta.RESPOSTA;



/**
 *
 * @author Gustavo Freitas
 */
public class AlunoDAO extends DAO<Aluno>{

    private static final AlunoDAO intancia = new AlunoDAO();
    
    @Override
    public boolean novo(Aluno novo) {

        //AlunoRecord aluno = GerenciadorBD.getContext().insertInto(ALUNO, ALUNO.NOME, ALUNO.IDTURMA, ALUNO.NASCIMENTO)
        AlunoRecord aluno = GerenciadorBD.getContext().insertInto(ALUNO, ALUNO.NOME, ALUNO.NASCIMENTO)
                                  //.values(novo.getNome(), novo.getTurma().getId(), new java.sql.Date(novo.getDataNascimento().getTime()))
                                  .values(novo.getNome(), new java.sql.Date(novo.getDataNascimento().getTime()))
                                  .returning().fetchOne();

        if(aluno != null){
            novo.setId(aluno.getIdaluno());
            return (true);
        }
        
        return (false);
    }

    @Override
    public Aluno obter(int id) {

        Aluno aluno = null;
        
        AlunoRecord resultado = GerenciadorBD.getContext().selectFrom(ALUNO).where(ALUNO.IDALUNO.eq(id)).fetchOne();
        
        if(resultado != null){
            aluno = new Aluno(resultado.getIdaluno(), resultado.getNome(),
                              //TurmaDAO.getInstance().obter(resultado.getIdturma()),
                              resultado.getNascimento());
        }
        
        return (aluno);
    }

    @Override
    public Collection<Aluno> obterTodos() {

        ArrayList<Aluno> alunos = new ArrayList<>();
        
        Result<AlunoRecord> resultado = GerenciadorBD.getContext().selectFrom(ALUNO).fetch();
        
        for(AlunoRecord a : resultado){
            //alunos.add(new Aluno(a.getIdaluno(), a.getNome(), TurmaDAO.getInstance().obter(a.getIdturma()), a.getNascimento()));
            alunos.add(new Aluno(a.getIdaluno(), a.getNome(), a.getNascimento()));
        }
        
        return (alunos);
    }
/*
    public Collection<Aluno> obterTodosByIdTurma(int id){

        ArrayList<Aluno> alunos = new ArrayList<>();

        Result<AlunoRecord> resultado = GerenciadorBD.getContext().selectFrom(ALUNO).where(ALUNO.IDTURMA.eq(id)).fetch();

        for(AlunoRecord a : resultado){
            alunos.add(new Aluno(a.getIdaluno(), a.getNome(), a.getNascimento()));
        }

        return (alunos);
    }
*/

    @Override
    public boolean apagar(int id) {
        
        Record aluno = GerenciadorBD.getContext().select(ALUNO.IDALUNO)
                                                 .from(ALUNO).join(REALIZA).on(ALUNO.IDALUNO.eq(REALIZA.IDALUNO))
                                                 .where(REALIZA.IDAVALIACAO.eq(id)).fetchOne();
        
        if(aluno == null){
            GerenciadorBD.getContext().deleteFrom(ALUNO).where(ALUNO.IDALUNO.eq(id)).execute();
            return (true);
        }
         
        return (false);
    }

    @Override
    public boolean apagarEmCascata(int id){
        throw new UnsupportedOperationException("Não suportado.");
    }
    
    @Override
    public boolean atualizar(Aluno aluno) {

        GerenciadorBD.getContext().update(ALUNO)
                                  .set(ALUNO.NOME, aluno.getNome())
                                  //.set(ALUNO.IDTURMA, aluno.getTurma().getId())
                                  .set(ALUNO.NASCIMENTO, new java.sql.Date(aluno.getDataNascimento().getTime()))
                                  .where(ALUNO.IDALUNO.eq(aluno.getId()))
                                  .execute();
        
        return (true);
    }

    public boolean verificarContinuacaoAvaliacao(Aluno aluno, int idAvaliacao){

        RealizaRecord realizaRecord = GerenciadorBD.getContext().selectFrom(REALIZA)
                                        .where(REALIZA.IDALUNO.eq(aluno.getId()))
                                            .and(REALIZA.IDAVALIACAO.eq(idAvaliacao))
                                        .fetchOne();

        if(realizaRecord != null){
            return(true);
        }

        return (false);
    }

    public boolean iniciarAvaliacao(Aluno aluno, int idAvaliacao) throws DataAccessException{

        GerenciadorBD.getContext()
                            .insertInto(REALIZA, REALIZA.IDALUNO, REALIZA.IDAVALIACAO, REALIZA.DATAREALIZACAO, REALIZA.ESTADO)
                            .values(aluno.getId(), idAvaliacao, new java.sql.Date((new Date()).getTime()), new Byte((byte)0))
                            .execute();

//        if(re == null){
//            throw new DataAccessException("Erro ao iniciar a avaliação: Não é possível se comunicar com o Banco de Dados.");
//        }

        return (true);
    }

    public boolean responder(Aluno aluno, int idAvaliacao, int idTentativa, int idAlternativa, boolean acertou){
        GerenciadorBD.getContext()
                .insertInto(RESPOSTA, RESPOSTA.REALIZA_IDALUNO, RESPOSTA.REALIZA_IDAVALIACAO, RESPOSTA.IDALTERNATIVA, RESPOSTA.IDTENTATIVA, RESPOSTA.ACERTO)
                .values(aluno.getId(), idAvaliacao, idAlternativa, idTentativa, new Byte((byte)(acertou ? 1 : 0)))
                .execute();

        return (true);
    }
    
    public static AlunoDAO getInstance() {
        return (AlunoDAO.intancia);
    }
}
