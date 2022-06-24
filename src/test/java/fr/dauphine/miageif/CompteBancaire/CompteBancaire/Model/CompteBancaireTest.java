package fr.dauphine.miageif.CompteBancaire.CompteBancaire.Model;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CompteBancaireTest {
    CompteBancaire tc;

    @Before
    public void setUp() {
        tc = new CompteBancaire();
    }

    @Test
    public void testGetSetID(){
        tc.setId(1L);
        assertEquals(Long.valueOf(1), tc.getId());
    }

    @Test
    public void testGetSetIban(){
        tc.setIban("FR7630004000031234567890143");
        assertEquals("FR7630004000031234567890143", tc.getIban());
    }

    @Test
    public void testGetSetType(){
        tc.setType("Compte courant");
        assertEquals("Compte_courant", tc.getType());
    }

    @Test
    public void testGetSetInteret(){
        tc.setInteret(new BigDecimal(100));
        assertEquals(new BigDecimal(100), tc.getInteret());
    }

    @Test
    public void testGetSetFrais(){
        tc.setFrais("gratuit");
        assertEquals("gratuit", tc.getFrais());
    }

    @Test
    public void testEquals(){
        tc.setId(1L);
        tc.setIban("FR7630004000031234567890143");
        tc.setType("Compte_courant");
        tc.setInteret(new BigDecimal(100));
        tc.setFrais("gratuit");
        CompteBancaire tc2 = new CompteBancaire(1L,"FR7630004000031234567890143","Compte_courant", new BigDecimal(100), "gratuit");
        assertEquals(tc2,tc);
    }
}
