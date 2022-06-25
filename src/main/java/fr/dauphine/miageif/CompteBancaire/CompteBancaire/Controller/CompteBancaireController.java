package fr.dauphine.miageif.CompteBancaire.CompteBancaire.Controller;
import fr.dauphine.miageif.CompteBancaire.CompteBancaire.Model.CompteBancaire;
import fr.dauphine.miageif.CompteBancaire.CompteBancaire.Repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CompteBancaireController {


  @Autowired
  private CompteBancaireRepository repository;


  // POST
    @PostMapping(value = "/COMPTEBANCAIRE")
    public ResponseEntity<CompteBancaire> createCompteBancaire(@RequestBody CompteBancaire CompteBancaire){
        return ResponseEntity.ok().body(this.repository.save(CompteBancaire));
    }

  // GET
    // "http://localhost:8080/COMPTEBANCAIRE"
  @GetMapping("/COMPTEBANCAIRE")
  public ResponseEntity<List<CompteBancaire>> getAllCompteBancaire(){
      try {
          List<CompteBancaire> CompteBancaires = new ArrayList<CompteBancaire>();
          repository.findAll().forEach(CompteBancaires::add);

          if (CompteBancaires.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(CompteBancaires, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    // "http://localhost:8080/COMPTEBANCAIRE/iban/FR7630004000031234567891011"
    @GetMapping("/COMPTEBANCAIRE/iban/{iban}")
    public ResponseEntity<List<CompteBancaire>> getCompteBancaireByIban
          (@PathVariable String iban){
      try{
        List<CompteBancaire> CompteBancaire = repository.findByIban(iban);

        if (CompteBancaire.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(CompteBancaire, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    // "http://localhost:8080/COMPTEBANCAIRE/type/Compte_courant"
    @GetMapping("/COMPTEBANCAIRE/type/{type}")
    public ResponseEntity<List<CompteBancaire>> getCompteBancaireByType
            (@PathVariable String type){
        try{
            List<CompteBancaire> CompteBancaire = repository.findByType(type);

            if (CompteBancaire.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(CompteBancaire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  "http://localhost:8080/COMPTEBANCAIRE/frais/gratuit"
    @GetMapping("/COMPTEBANCAIRE/frais/{frais}")
    public ResponseEntity<List<CompteBancaire>> getCompteBancaireByFrais
            (@PathVariable String frais){
        try{
            List<CompteBancaire> CompteBancaire = repository.findByFrais(frais);

            if (CompteBancaire.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(CompteBancaire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  "http://localhost:8080/COMPTEBANCAIRE/interet/10"
    @GetMapping("/COMPTEBANCAIRE/interet/{interet}")
    public ResponseEntity<List<CompteBancaire>> getCompteBancaireByInteret
    (@PathVariable BigDecimal interet){
        try{
            List<CompteBancaire> CompteBancaire = repository.findByInteret(interet);

            if (CompteBancaire.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(CompteBancaire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  "http://localhost:8080/COMPTEBANCAIRE/iban/FR7630004000031234567891011/type/Compte_courant"
    @GetMapping("/COMPTEBANCAIRE/iban/{iban}/type/{type}")
    public ResponseEntity<List<CompteBancaire>> getCompteBancaireByIbanAndType
    (@PathVariable String iban, @PathVariable String type){
      try{
          List<CompteBancaire> CompteBancaire = repository.findByIbanAndType(iban, type);

          if (CompteBancaire == null) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(CompteBancaire, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //  "http://localhost:8080/COMPTEBANCAIRE/iban/FR7630004000031234567891011/type/Compte_courant/frais/gratuit"
    @GetMapping("/COMPTEBANCAIRE/iban/{iban}/type/{type}/frais/{frais}")
    public ResponseEntity<CompteBancaire> getCompteBancaireByibanAndtypeAndfrais
    (@PathVariable String iban, @PathVariable String type, @PathVariable String frais){
        try{
            CompteBancaire CompteBancaire = repository.findByIbanAndTypeAndFrais(iban, type, frais);

            if (CompteBancaire == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(CompteBancaire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  "http://localhost:8080/COMPTEBANCAIRE/id/1"
    @GetMapping("/COMPTEBANCAIRE/id/{id}")
    public ResponseEntity<CompteBancaire> getCompteBancaireByID
            (@PathVariable Long id){
        Optional<CompteBancaire> CompteBancaire = repository.findById(id);

        if (CompteBancaire.isPresent()) {
            return new ResponseEntity<>(CompteBancaire.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    


    // DELETE
    //  "http://localhost:8080/COMPTEBANCAIRE/id/1"
    @DeleteMapping("/COMPTEBANCAIRE/id/{id}")
    public ResponseEntity<HttpStatus> deleteCompteBancaire(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/COMPTEBANCAIRE/iban/{iban}")
    public ResponseEntity<HttpStatus> deleteCompteBancaireByIban(@PathVariable String iban) {
        try {
            repository.deleteByIban(iban);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // PUT
    
    @PutMapping("/COMPTEBANCAIRE/id/{id}")
    public ResponseEntity<CompteBancaire> updateCompteBancaire(@PathVariable long id, @RequestBody CompteBancaire CompteBancaire) {

        Optional<CompteBancaire> CompteBancaireData = repository.findById(id);

        if (CompteBancaireData.isPresent()) {
            CompteBancaire _CompteBancaire = CompteBancaireData.get();
            _CompteBancaire.setIban(CompteBancaire.getIban());
            _CompteBancaire.setType(CompteBancaire.getType());
            _CompteBancaire.setInteret(CompteBancaire.getInteret());
            _CompteBancaire.setFrais(CompteBancaire.getFrais());
            return new ResponseEntity<>(repository.save(_CompteBancaire), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //  "http://localhost:8080/COMPTEBANCAIRE/id/1/frais/gratuit"
    @PutMapping("/COMPTEBANCAIRE/id/{id}/frais/{frais}")
    public ResponseEntity<CompteBancaire> updateCompteBancaireForType(@PathVariable long id, @PathVariable String type) {
        Optional<CompteBancaire> CompteBancaire = repository.findById(id);

        if (CompteBancaire.isPresent()) {
            CompteBancaire _CompteBancaire = CompteBancaire.get();
            _CompteBancaire.setType(type);
            return new ResponseEntity<>(repository.save(_CompteBancaire), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  "http://localhost:8080/COMPTEBANCAIRE/id/1/interet/100"
    @PutMapping("/COMPTEBANCAIRE/id/{id}/interet/{interet}")
    public ResponseEntity<CompteBancaire> updateCompteBancaireForInteret(@PathVariable long id, @PathVariable BigDecimal interet) {
        Optional<CompteBancaire> CompteBancaire = repository.findById(id);

        if (CompteBancaire.isPresent()) {
            CompteBancaire _CompteBancaire = CompteBancaire.get();
            _CompteBancaire.setInteret(interet);
            return new ResponseEntity<>(repository.save(_CompteBancaire), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
