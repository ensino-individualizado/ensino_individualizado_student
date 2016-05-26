/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Gustavo Freitas
 */
public abstract class DAO <Objeto>{
    
    public abstract boolean novo(Objeto novo) throws SQLException, ClassNotFoundException;
    
    public abstract Objeto obter(int id) throws SQLException, ClassNotFoundException;
    
    public abstract Collection<Objeto> obterTodos();
        
    public abstract boolean apagar(int id);
    
    public abstract boolean apagarEmCascata(int id);
    
    public abstract boolean atualizar(Objeto atualizado);
    
}
