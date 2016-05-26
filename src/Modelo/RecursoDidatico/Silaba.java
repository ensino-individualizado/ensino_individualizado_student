/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico;

import java.util.Objects;

/**
 *
 * @author Gustavo Freitas
 */
public class Silaba {
    
    private int id = -1;
    private String silaba;
    
    public Silaba(String silaba){
        this.silaba = silaba;
    }

    public Silaba(int id, String silaba){
        this.id = id;
        this.silaba = silaba;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSilaba() {
        return silaba;
    }

    public void setSilaba(String silaba) {
        this.silaba = silaba;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.silaba);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Silaba other = (Silaba) obj;
        if (!Objects.equals(this.silaba, other.silaba)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (silaba);
    }
}
