/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico.Midia;

import DAO.AudioDAO;
import Ferramentas.FileSaver;

/**
 *
 * @author Gustavo Freitas
 */
public class Audio extends Midia{
    
    private String transcricao;

    public Audio(){
        super();
    }

    @Override
    public void finalizarCarregamento() {
        AudioDAO.getInstance().finalizarCarregamento(this, FileSaver.getInstance().getTmpFolderLocation());
        this.media = FileSaver.loadMedia(this.getLocal());
    }

    public Audio(int id, String transcricao){
        super(id);
        this.transcricao = transcricao;
    }
    
    public Audio(int id, String transcricao, String local, String nomeOriginal){
        this.id = id;
        this.local = local;
        this.transcricao = transcricao;
        this.nomeOriginal = nomeOriginal;
    }
    
    public String getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(String transcricao) {
        this.transcricao = transcricao;
    }
}
