package it.corsobackend.ProgettoHibernate.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "nome", unique = true)
    private String nome;
    @Column(name = "prezzo", nullable = false)
    private BigDecimal prezzo;
    @OneToMany(
            mappedBy = "prodotto",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Vendita> vendite = new ArrayList<>();
    public Prodotto(){}
    public Prodotto(String nome, BigDecimal prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }
    public String getNome() {
        return nome;
    }
    public BigDecimal getPrezzo() {
        return prezzo;
    }
    public List<Vendita> getVendite() {
        return vendite;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
