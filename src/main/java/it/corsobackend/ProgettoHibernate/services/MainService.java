package it.corsobackend.ProgettoHibernate.services;

import it.corsobackend.ProgettoHibernate.entities.Prodotto;
import it.corsobackend.ProgettoHibernate.views.RispostaVenditeProdotto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MainService {
    public RispostaVenditeProdotto getVenditeProdotto(Prodotto p){
        Integer numeroVendite = p.getVendite().stream().reduce(0,(acc,v)->acc+=v.getQuantita(), Integer::sum);
        return new RispostaVenditeProdotto(numeroVendite, p.getPrezzo().multiply(BigDecimal.valueOf(numeroVendite)));
    }
}
