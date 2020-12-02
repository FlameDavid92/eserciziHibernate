package it.corsobackend.ProgettoHibernate.views;

import java.math.BigDecimal;

public class RispostaVenditeProdotto {
    private Integer numeroVendite;
    private BigDecimal ammontare;
    public RispostaVenditeProdotto(Integer numeroVendite, BigDecimal ammontare){
        this.numeroVendite = numeroVendite;
        this.ammontare = ammontare;
    }
    public Integer getNumeroVendite() {
        return numeroVendite;
    }
    public BigDecimal getAmmontare() {
        return ammontare;
    }
}
