package it.corsobackend.ProgettoHibernate.controllers;

import it.corsobackend.ProgettoHibernate.entities.Prodotto;
import it.corsobackend.ProgettoHibernate.entities.Vendita;
import it.corsobackend.ProgettoHibernate.repositories.ProdottoRepository;
import it.corsobackend.ProgettoHibernate.repositories.VenditaRepository;
import it.corsobackend.ProgettoHibernate.services.MainService;
import it.corsobackend.ProgettoHibernate.views.RispostaVenditeProdotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired private ProdottoRepository pr;
    @Autowired private VenditaRepository vr;

    @GetMapping("/venditedaid/{id}")
    public String getVenditeFromId(@PathVariable("id") Long id, @Autowired MainService ms){
        Optional<Prodotto> optProd = pr.findById(id);
        if(optProd.isPresent()){
            RispostaVenditeProdotto rvp = ms.getVenditeProdotto(optProd.get());
            return "Numero di vendite: "+rvp.getNumeroVendite()+". Ammontare: "+rvp.getAmmontare();
        }else{
            return "Prodotto non presente!";
        }
    }
    @GetMapping("/venditedanome/{nome}")
    public String getVenditeFromNome(@PathVariable("nome") String nome,
                                     @Autowired MainService ms){
        Optional<Prodotto> optProd = pr.findByNome(nome);
        if(optProd.isPresent()){
            RispostaVenditeProdotto rvp = ms.getVenditeProdotto(optProd.get());
            return "Numero di vendite: "+rvp.getNumeroVendite()+". Ammontare: "+rvp.getAmmontare();
        }else{
            return "Prodotto non presente!";
        }
    }

    @GetMapping("/aggiungivendita/{idProdotto}/{quantita}")
    public String aggiungiVendita(@PathVariable("idProdotto") Long idProdotto,
                                  @PathVariable("quantita") Integer quantita,
                                  @Autowired MainService ms){

        Optional<Prodotto> optProd = pr.findById(idProdotto);
        if(optProd.isPresent()){
            Vendita nuovaVendita = new Vendita(optProd.get(),quantita);
            vr.save(nuovaVendita);
            return "Vendita salvata sul DB!";
        }else{
            return "Prodotto non presente!";
        }
    }

    @GetMapping("/aggiungiprodotto/{nome}/{prezzo}")
    public String aggiungiProdotto(@PathVariable("nome") String nome,
                                   @PathVariable("prezzo") BigDecimal prezzo){
        try{
            pr.save(new Prodotto(nome, prezzo));
            return "Prodotto salvato sul DB!";
        }catch (DataIntegrityViolationException e){
            return "Nome prodotto già presente!";
        }
    }
}
