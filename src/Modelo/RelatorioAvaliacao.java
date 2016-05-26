package Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Gustavo Freitas on 21/03/2016.
 */
public class RelatorioAvaliacao {

    private int qtdErros = 0;
    private int qtdAcertos = 0;
    private int qtdAlternativas = 0;

    public RelatorioAvaliacao(){

    }

    public void acertou(){
        this.qtdAcertos++;
        this.qtdAlternativas++;
    }

    public void errou(){
        this.qtdErros++;
        this.qtdAlternativas++;
    }

    public double calcularTaxaDeAcertos(){
        double taxa = ((double)this.qtdAcertos / (double)this.qtdAlternativas) * 100.0;
        BigDecimal bd = new BigDecimal(taxa).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    public double calcularTaxaDeErros(){
        double taxa = ((double)this.qtdErros / (double)this.qtdAlternativas) * 100.0;
        BigDecimal bd = new BigDecimal(taxa).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public int getQtdAcertos() {
        return qtdAcertos;
    }

    public int getQtdAlternativas() {
        return qtdAlternativas;
    }
}
