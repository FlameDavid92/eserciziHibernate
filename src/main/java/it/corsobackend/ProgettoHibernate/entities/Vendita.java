package it.corsobackend.ProgettoHibernate.entities;

import javax.persistence.*;

@Entity
public class Vendita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    Prodotto prodotto;
    Integer quantita;

    public Vendita(){}
    public Vendita(Prodotto prodotto,Integer quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }
}
