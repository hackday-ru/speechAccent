package hackday.speechAccent.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hackday.speechAccent.configuration.RootConfig;
import hackday.speechAccent.configuration.WebConfig;
import hackday.speechAccent.dao.LanguageDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.junit.Assert.*;

/**
 * Created by nicaraguanec on 17.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class})
@WebAppConfiguration
public class SpeechControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private LanguageDao languageDao;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).dispatchOptions(false).build();
        MockitoAnnotations.initMocks(this);
    }


    @Autowired
    private WebApplicationContext wac;

    @Test
    public void getHeadTest() throws Exception {
        mockMvc.perform(request(HttpMethod.HEAD, "/api"))
                .andExpect(status().isOk());
    }

    /**
     * Not working with annotation @ResponseBody
     *
     * @throws Exception
     */
    /*@Test
    public void getLanguages() throws Exception {
        mockMvc.perform(get("/api/language")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

    @Test
    public void createRecord() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("multipartFile", new FileInputStream("C:\\Users\\nicaraguanec\\logfile"));
        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/record")
                .file(multipartFile)
                .param("origin", "ru")
                .param("accent", "ru")
                .param("text", "properties"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void rateRecord() throws Exception {

    }
}