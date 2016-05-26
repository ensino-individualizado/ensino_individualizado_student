/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.TurmaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.Turma;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.Collection;

import static DAO.mysql.generatedclasses.tables.Turma.TURMA;

/**
 *
 * @author Gustavo Freitas
 */
public class TurmaDAO extends DAO<Turma>{

    private static final TurmaDAO instancia = new TurmaDAO();
    
    @Override
    public boolean novo(Turma novo) {
        
        TurmaRecord t = GerenciadorBD.getContext().insertInto(TURMA)
                                                  .set(TURMA.NOME, novo.getNome())
                                                  .set(TURMA.DESCRICAO, novo.getDescricao())
                                                  .returning().fetchOne(); 
        
        if(t != null){
            novo.setId(t.getIdturma());
            return (true);
        }
        
        return (false);
    }

    @Override
    public Turma obter(int id) {
        
        Turma turma = null;
        TurmaRecord cons = GerenciadorBD.getContext().selectFrom(TURMA).where(TURMA.IDTURMA.eq(id)).fetchOne();
        
        if(cons != null){
            turma = new Turma(cons.getIdturma(), cons.getNome(), cons.getDescricao());
        }
        
        return (turma);
    }

    @Override
    public Collection<Turma> obterTodos() {

        ArrayList<Turma> turmas = new ArrayList<>();
        Result<TurmaRecord> resultado = GerenciadorBD.getContext().selectFrom(TURMA).fetch();
        
        for(TurmaRecord t : resultado){
            turmas.add(new Turma(t.getIdturma(), t.getNome(), t.getDescricao()));
        }
        
        return (turmas);
    }

    @Override
    public boolean apagar(int id) {

//        //Verifica se existem alunos na turma
//        Record turma = GerenciadorBD.getContext().select(TURMA.IDTURMA)
//                                                 .from(TURMA).join(ALUNO).on(TURMA.IDTURMA.eq(ALUNO.IDTURMA))
//                                                 .where(TURMA.IDTURMA.eq(id)).fetchOne();
//
//        if(turma == null){
            GerenciadorBD.getContext().deleteFrom(TURMA).where(TURMA.IDTURMA.eq(id)).execute();
            return (true);
        //}
        
        //return (false);
    }

    @Override
    public boolean apagarEmCascata(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean atualizar(Turma turma) {
        
        GerenciadorBD.getContext().update(TURMA)
                                  .set(TURMA.DESCRICAO, turma.getDescricao())
                                  .set(TURMA.NOME, turma.getNome())
                                  .where(TURMA.IDTURMA.eq(turma.getId()))
                                  .execute();
        
        return (true);
    }
    
    public static TurmaDAO getInstance(){
        return (TurmaDAO.instancia);
    }
}
