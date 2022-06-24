package fr.dauphine.miageif.CompteBancaire.CompteBancaire.Model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
public class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="IBAN")
    private String iban;

    @Column(name="type_compte")
    private String type;

    @Column(name="interet")
    private BigDecimal interet;

    @Column(name="frais_compte")
    private String frais;


// Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getInteret() {
        return interet;
    }

    public void setInteret(BigDecimal interet) {
        this.interet = interet;
    }

    public String getFrais() {
        return frais;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }


    public CompteBancaire() {
    }

    public CompteBancaire(Long id, String iban, String type, BigDecimal interet, String frais) {
        super();
        this.id = id;
        this.iban = iban;
        this.type = type;
        this.interet = interet;
        this.frais = frais;
    }

    public CompteBancaire(String iban, String type, BigDecimal interet, String frais) {
        super();
        this.iban = iban;
        this.type = type;
        this.interet = interet;
        this.frais = frais;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompteBancaire that = (CompteBancaire) o;
        return Objects.equals(id, that.id) && Objects.equals(iban, that.iban) && Objects.equals(type, that.type) && Objects.equals(interet, that.interet) && Objects.equals(frais  , that.frais);
    }


}
