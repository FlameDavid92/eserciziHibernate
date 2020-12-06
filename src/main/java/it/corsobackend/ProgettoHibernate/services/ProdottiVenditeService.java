package it.corsobackend.ProgettoHibernate.services;

import it.corsobackend.ProgettoHibernate.entities.Prodotto;
import it.corsobackend.ProgettoHibernate.entities.Vendita;
import it.corsobackend.ProgettoHibernate.models.RispostaVenditeProdotto;
import it.corsobackend.ProgettoHibernate.repositories.ProdottoRepository;
import it.corsobackend.ProgettoHibernate.repositories.VenditaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProdottiVenditeService {

    public RispostaVenditeProdotto getVenditeProdotto(Prodotto p){
        Integer numeroVendite = p.getVendite().stream().reduce(0,(acc,v)->acc+=v.getQuantita(), Integer::sum);
        return new RispostaVenditeProdotto(numeroVendite, p.getPrezzo().multiply(BigDecimal.valueOf(numeroVendite)));
    }

    public String addProdotto(String nome, BigDecimal prezzo, ProdottoRepository pr){
        try{
            pr.save(new Prodotto(nome, prezzo));
            return "Prodotto salvato sul DB!";
        }catch (DataIntegrityViolationException e){
            return "Nome prodotto già presente!";
        }
    }

    public String addVendita(Long idProdotto, Integer quantita, ProdottoRepository pr, VenditaRepository vr){
        Optional<Prodotto> optProd = pr.findById(idProdotto);
        if(optProd.isPresent()){
            Vendita nuovaVendita = new Vendita(optProd.get(),quantita);
            vr.save(nuovaVendita);
            return "Vendita salvata sul DB!";
        }else{
            return "Prodotto non presente!";
        }
    }

    public String getVenditeProdottoDaId(Long id, ProdottoRepository pr){
        Optional<Prodotto> optProd = pr.findById(id);
        if(optProd.isPresent()){
            RispostaVenditeProdotto rvp = this.getVenditeProdotto(optProd.get());
            return "Numero di vendite: "+rvp.getNumeroVendite()+". Ammontare: "+rvp.getAmmontare();
        }else{
            return "Prodotto non presente!";
        }
    }
    public String getVenditeProdottoDaNome(String nome, ProdottoRepository pr){
        Optional<Prodotto> optProd = pr.findByNome(nome);
        if(optProd.isPresent()){
            RispostaVenditeProdotto rvp = this.getVenditeProdotto(optProd.get());
            return "Numero di vendite: "+rvp.getNumeroVendite()+". Ammontare: "+rvp.getAmmontare();
        }else{
            return "Prodotto non presente!";
        }
    }

}
