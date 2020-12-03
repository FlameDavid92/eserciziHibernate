package it.corsobackend.ProgettoHibernate.controllers;

import it.corsobackend.ProgettoHibernate.entities.CookieDB;
import it.corsobackend.ProgettoHibernate.services.ProdottiVenditeService;
import it.corsobackend.ProgettoHibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired private UserService us;
    @Autowired private ProdottiVenditeService pvs;

    @GetMapping("/venditedaid/{id}")
    public String getVenditeFromId(@CookieValue(value = "auth", defaultValue = "") String auth,
                                   @PathVariable("id") Long id){
        CookieDB cookieDB = us.isLogged(auth);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.getVenditeProdottoDaId(id);
    }
    @GetMapping("/venditedanome/{nome}")
    public String getVenditeFromNome(@CookieValue(value = "auth", defaultValue = "") String auth,
                                     @PathVariable("nome") String nome){
        CookieDB cookieDB = us.isLogged(auth);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.getVenditeProdottoDaNome(nome);
    }

    @GetMapping("/aggiungivendita/{idProdotto}/{quantita}")
    public String aggiungiVendita(@CookieValue(value = "auth", defaultValue = "") String auth,
                                  @PathVariable("idProdotto") Long idProdotto,
                                  @PathVariable("quantita") Integer quantita,
                                  @Autowired ProdottiVenditeService ms){
        CookieDB cookieDB = us.isLogged(auth);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.addVendita(idProdotto,quantita);
    }

    @GetMapping("/aggiungiprodotto/{nome}/{prezzo}")
    public String aggiungiProdotto(@CookieValue(value = "auth", defaultValue = "") String auth,
                                   @PathVariable("nome") String nome,
                                   @PathVariable("prezzo") BigDecimal prezzo){
        CookieDB cookieDB = us.isLogged(auth);
        if(cookieDB == null) return "Accesso protetto, effettua il login!";
        return pvs.addProdotto(nome, prezzo);
    }
}
