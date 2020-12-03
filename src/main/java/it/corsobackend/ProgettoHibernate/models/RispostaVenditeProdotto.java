package it.corsobackend.ProgettoHibernate.models;

import java.math.BigDecimal;

public class RispostaVenditeProdotto {
    private final Integer numeroVendite;
    private final BigDecimal ammontare;
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
