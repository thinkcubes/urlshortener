package kr.co.freeism.urlshortener.controller;

import kr.co.freeism.urlshortener.repository.UrlMapRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ShortenUrlControllerTest {
    private static final String URL = "http://localhost";
    private static final String HASH_VALUE = "296beed8";

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UrlMapRepository urlMapRepository;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
            .alwaysDo(print())
            .build();
    }

    @Test
    public void formWithoutUrl() throws Exception {
        mockMvc.perform(
            get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("form"))
            .andExpect(model().attributeDoesNotExist("hashValue"));
    }

    @Test
    public void formWithUrl() throws Exception {
        mockMvc.perform(
            get("/").param("url", URL))
            .andExpect(status().isOk())
            .andExpect(view().name("form"))
            .andExpect(model().attribute("hashValue", HASH_VALUE));
    }

    @Test
    public void formWithUrlIfExistedAlready() throws Exception {
        givenUrlMapAtDb();

        mockMvc.perform(
            get("/").param("url", URL))
            .andExpect(status().isOk())
            .andExpect(view().name("form"))
            .andExpect(model().attribute("hashValue", HASH_VALUE));
    }

    @Test
    public void formWithInvalidParams() throws Exception {
        mockMvc.perform(
            get("/").param("url", "foofoourl"))
            .andExpect(status().isOk())
            .andExpect(view().name("error"));
    }

    @Test
    public void redirect() throws Exception {
        givenUrlMapAtDb();

        mockMvc.perform(
            get("/" + HASH_VALUE))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl(URL));
    }

    @Test
    public void redirectIfHashValueNotExisted() throws Exception {
        mockMvc.perform(
            get("/" + HASH_VALUE))
            .andExpect(status().isOk())
            .andExpect(view().name("error"));
    }

    private void givenUrlMapAtDb() {
        urlMapRepository.saveIfNotExisted(URL, HASH_VALUE);
    }
}