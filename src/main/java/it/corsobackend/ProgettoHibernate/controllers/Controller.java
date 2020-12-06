package it.corsobackend.ProgettoHibernate.controllers;

import it.corsobackend.ProgettoHibernate.entities.CookieDB;
import it.corsobackend.ProgettoHibernate.repositories.CookieRepository;
import it.corsobackend.ProgettoHibernate.repositories.ProdottoRepository;
import it.corsobackend.ProgettoHibernate.repositories.VenditaRepository;
import it.corsobackend.ProgettoHibernate.services.ProdottiVenditeService;
import it.corsobackend.ProgettoHibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired private ProdottoRepository pr;
    @Autowired private VenditaRepository vr;
    @Autowired private CookieRepository cr;

    @GetMapping("/venditedaid/{id}")
    public String getVenditeFromId(@CookieValue(value = "auth", defaultValue = "") String auth,
                                   @PathVariable("id") Long id,
                                   @Autowired ProdottiVenditeService pvs,
                                   @Autowired UserService us){
        CookieDB cookieDB = us.isLogged(auth, cr);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.getVenditeProdottoDaId(id, pr);
    }
    @GetMapping("/venditedanome/{nome}")
    public String getVenditeFromNome(@CookieValue(value = "auth", defaultValue = "") String auth,
                                     @PathVariable("nome") String nome,
                                     @Autowired ProdottiVenditeService pvs,
                                     @Autowired UserService us){
        CookieDB cookieDB = us.isLogged(auth, cr);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.getVenditeProdottoDaNome(nome, pr);
    }

    @GetMapping("/aggiungivendita/{idProdotto}/{quantita}")
    public String aggiungiVendita(@CookieValue(value = "auth", defaultValue = "") String auth,
                                  @PathVariable("idProdotto") Long idProdotto,
                                  @PathVariable("quantita") Integer quantita,
                                  @Autowired ProdottiVenditeService pvs,
                                  @Autowired UserService us){
        CookieDB cookieDB = us.isLogged(auth, cr);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.addVendita(idProdotto,quantita, pr, vr);
    }

    @GetMapping("/aggiungiprodotto/{nome}/{prezzo}")
    public String aggiungiProdotto(@CookieValue(value = "auth", defaultValue = "") String auth,
                                   @PathVariable("nome") String nome,
                                   @PathVariable("prezzo") BigDecimal prezzo,
                                   @Autowired ProdottiVenditeService pvs,
                                   @Autowired UserService us){
        CookieDB cookieDB = us.isLogged(auth, cr);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.addProdotto(nome, prezzo, pr);
    }
}
