package fr.dauphine.miageif.CompteBancaire.CompteBancaire.Repository;
import com.sun.xml.bind.v2.model.core.ID;
import fr.dauphine.miageif.CompteBancaire.CompteBancaire.Model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Creation du JPA Repository basé sur Spring Data
//  Permet de "générer" toutes sortes de requêtes JPA, sans implementation
public interface CompteBancaireRepository extends  JpaRepository<CompteBancaire, Long> {
  // Query Method findBy


  Optional<CompteBancaire> findById(ID id);

  List<CompteBancaire> findByIban(String iban);

  List<CompteBancaire> findByType(String type);

  List<CompteBancaire> findByInteret(BigDecimal interet);

  List<CompteBancaire> findByFrais(String frais);

  List<CompteBancaire> findByIbanAndType(String iban, String type);

  CompteBancaire findByIbanAndTypeAndFrais(String iban, String type, String frais);

  void deleteByIban(String iban);

}