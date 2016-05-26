/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.mysql.generatedclasses.tables.records.SilabaRecord;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.Silaba;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static DAO.mysql.generatedclasses.Tables.PALAVRA_SILABA;
import static DAO.mysql.generatedclasses.tables.Silaba.SILABA;

/**
 *
 * @author Gustavo Freitas
 */
public class SilabaDAO extends DAO<Silaba>{
    
   private static final SilabaDAO instancia = new SilabaDAO();
    
    @Override
    public boolean novo(Silaba novo) {
        
        SilabaRecord s = GerenciadorBD.getContext().insertInto(SILABA)
                                                   .set(SILABA.SILABA_, novo.getSilaba())
                                                   .returning().fetchOne();
        
        if(s != null){
            novo.setId(s.getIdsilaba());
            return (true);
        }
        
        return (false);
    }

    @Override
    public Silaba obter(int id) {
        
        Silaba silaba = null;
        
        SilabaRecord cons = GerenciadorBD.getContext().selectFrom(SILABA).where(SILABA.IDSILABA.eq(id)).fetchOne();
        
        if(cons != null){
            silaba = new Silaba(cons.getIdsilaba(), cons.getSilaba());
        }
        
        return (silaba);
    }
 
    @Override
    public Collection<Silaba> obterTodos() {

        ArrayList<Silaba> silabas = new ArrayList<>();
        
        Result<SilabaRecord> cons = GerenciadorBD.getContext().selectFrom(SILABA).fetch();
        
        for(SilabaRecord s : cons){
            silabas.add(new Silaba(s.getIdsilaba(), s.getSilaba()));
        }
        
        return (silabas);
    }

    public Collection<Silaba> obterPelaPalavra(int idPalavra){
        
        ArrayList<Silaba> silabas = new ArrayList<>();

        Result<Record2<Integer, String>> cons = GerenciadorBD.getContext().selectDistinct(SILABA.IDSILABA, SILABA.SILABA_)
                                                              .from(SILABA).join(PALAVRA_SILABA).on(SILABA.IDSILABA.eq(PALAVRA_SILABA.SILABA_IDSILABA))
                                                              .where(PALAVRA_SILABA.PALAVRA_IDPALAVRA.eq(idPalavra))
                                                              .fetch();
        
        for(Record2<Integer, String> s : cons){
            silabas.add(new Silaba(s.value1(), s.value2()));
        }

        return (silabas);
    }

    public Collection<Silaba> obterRandom(int qtd){

        ArrayList<Silaba> silabas = null;
        Record1<Integer> r = GerenciadorBD.getContext()
                .select(SILABA.IDSILABA)
                .from(SILABA)
                .orderBy(SILABA.IDSILABA.desc())
                .limit(1)
                .fetchOne();

        if(r != null) {
            silabas = new ArrayList<>();
            for (qtd = qtd; qtd > 0; qtd--) {
                Silaba s = this.obter(new Random().nextInt(r.value1() + 1) + 1);
                while(s == null){
                    s = this.obter(new Random().nextInt(r.value1() + 1) + 1);
                }
                silabas.add(s);
            }
        }
        return (silabas);
    }

    @Override
    public boolean apagar(int id) {
        
        GerenciadorBD.getContext().deleteFrom(SILABA).where(SILABA.IDSILABA.eq(id)).execute();
        
        return (true);
    }

    @Override
    public boolean apagarEmCascata(int id) {
        throw new UnsupportedOperationException("Não suportado.");
    }

    @Override
    public boolean atualizar(Silaba silaba) {
        
        if(silaba.getId() != -1){
            GerenciadorBD.getContext().update(SILABA)
                                      .set(SILABA.SILABA_, silaba.getSilaba())
                                      .where(SILABA.IDSILABA.eq(silaba.getId()))
                                      .execute();
            return (true);
        }
        
        else{
            return (this.novo(silaba));
        }
    }
    
    public static SilabaDAO getInstance(){
        return (SilabaDAO.instancia);
    }
}
