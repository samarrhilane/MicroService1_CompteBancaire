package fr.dauphine.miageif.CompteBancaire.CompteBancaire.Controller;

import java.io.IOException;
import java.math.BigDecimal;

import fr.dauphine.miageif.CompteBancaire.CompteBancaire.Model.CompteBancaire;
import fr.dauphine.miageif.CompteBancaire.CompteBancaire.CompteBancaireApplication;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CompteBancaireApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class CompteBancaireControllerTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> classe)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, classe);
    }

    @Test
    @Order(1)
    public void getAllCompteBancaire() throws Exception {
        String uri = "/COMPTEBANCAIRE";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompteBancaire[] CompteBancaires = mapFromJson(content, CompteBancaire[].class);
        assertTrue(CompteBancaires.length > 0);
    }

    @Test
    @Order(2)
    public void getCompteBancaireByIban() throws Exception {
        String uri = "/COMPTEBANCAIRE/iban/FR7630004000031234567890143";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompteBancaire[] CompteBancaire = mapFromJson(content, CompteBancaire[].class);
        assertEquals(1, CompteBancaire.length);
    }

    @Test
    @Order(3)
    public void getCompteBancaireByType() throws Exception {
        String uri = "/COMPTEBANCAIRE/type/Compte_courant";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompteBancaire[] tauxChanges = mapFromJson(content, CompteBancaire[].class);
        assertEquals(1, tauxChanges.length);
    }

    @Test
    @Order(4)
    public void createCompteBancaire() throws Exception {
        String uri = "/COMPTEBANCAIRE/";
        CompteBancaire CompteBancaire = new CompteBancaire("FR7630004000031234567890144", "compte_courant", new BigDecimal("10"), "gratuit");

        String inputJson = mapToJson(CompteBancaire);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompteBancaire _CompteBancaire = mapFromJson(content, CompteBancaire.class);
        _CompteBancaire.setId(null);
        assertEquals(CompteBancaire, _CompteBancaire);
    }

    @Test
    @Order(5)
    public void updateCompteBancaireById() throws Exception {
        String uri = "/COMPTEBANCAIRE/id/1";
        CompteBancaire CompteBancaire = new CompteBancaire("FR7630004000031234567890144", "compte_courant", new BigDecimal("10"), "gratuit");

        String inputJson = mapToJson(CompteBancaire);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        CompteBancaire _CompteBancaire = mapFromJson(content, CompteBancaire.class);
        CompteBancaire.setId(1L);
        assertEquals(200, status);
        assertEquals(CompteBancaire, _CompteBancaire);
    }

    @Test
    @AfterAll
    public void deleteCompteBancaireById() throws Exception {
        String uri = "/COMPTEBANCAIRE/id/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}
