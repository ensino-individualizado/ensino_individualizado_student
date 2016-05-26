/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico;

import DAO.ImagemDAO;
import Ferramentas.FileSaver;
import javafx.scene.image.Image;

/**
 *
 * @author Gustavo Freitas
 */
public class Imagem implements RecursoDidatico{
    
    private int id = -1;
    private String local = null;
    private Image imagem = null;
    private String nomeOriginal = null;

    public Imagem(){
    }
    
    public Imagem(int id, String nomeOriginal){
        this.id = id;
        this.nomeOriginal = nomeOriginal;
    }

    public void finalizarCarregamento() {
        Thread loadThread = new Thread(() -> {
            ImagemDAO.getInstance().finalizarCarregamento(this, FileSaver.getInstance().getTmpFolderLocation());
            imagem = new Image("file:////" + this.getLocal());
        });

        loadThread.run();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
}
