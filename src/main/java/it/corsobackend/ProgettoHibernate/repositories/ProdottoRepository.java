package it.corsobackend.ProgettoHibernate.repositories;

import it.corsobackend.ProgettoHibernate.entities.Prodotto;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ProdottoRepository extends CrudRepository<Prodotto,Long> {
    Optional<Prodotto> findByNome(String nome);
}
