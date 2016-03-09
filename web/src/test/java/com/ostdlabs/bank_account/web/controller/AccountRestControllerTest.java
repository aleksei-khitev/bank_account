package com.ostdlabs.bank_account.web.controller;

import com.ostdlabs.bank_account.web.conf.SpringWebConfiguration;
import com.ostdlabs.bank_account.web.vo.AccountVO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class AccountRestControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void testList() throws Exception {
        ResultActions result = mockMvc.perform(get("http://localhost/bank_accounts/list/"));
        assertThat(result, notNullValue());
        String jsonResultObject = "[{\"id\":0,\"iban\":\"12345678912345678800001\",\"bic\":\"122001\"}," +
                "{\"id\":1,\"iban\":\"12345678912345678800002\",\"bic\":\"122002\"}," +
                "{\"id\":2,\"iban\":\"12345678912345678800003\",\"bic\":\"122003\"}," +
                "{\"id\":3,\"iban\":\"12345678912345678800004\",\"bic\":\"122004\"}," +
                "{\"id\":4,\"iban\":\"12345678912345678800005\",\"bic\":\"122005\"}," +
                "{\"id\":5,\"iban\":\"12345678912345678800006\",\"bic\":\"122006\"}," +
                "{\"id\":6,\"iban\":\"12345678912345678900001\",\"bic\":\"123001\"}," +
                "{\"id\":7,\"iban\":\"12345678912345678900002\",\"bic\":\"123002\"}," +
                "{\"id\":8,\"iban\":\"12345678912345678900003\",\"bic\":\"123003\"}," +
                "{\"id\":9,\"iban\":\"12345678912345678900004\",\"bic\":\"123004\"}," +
                "{\"id\":10,\"iban\":\"12345678912345678900005\",\"bic\":\"123005\"}," +
                "{\"id\":11,\"iban\":\"12345678912345678900006\",\"bic\":\"123006\"}]";
        assertThat(result.andReturn().getResponse().getContentAsString(),
                equalTo(jsonResultObject));
    }

    @Test
    public void correctGet() throws Exception {
        checkGet("http://localhost/bank_accounts/get?id=5", "{\"id\":5,\"iban\":\"12345678912345678800006\",\"bic\":\"122006\"}");
    }

    @Test
    public void cannotFindGet() throws Exception {
        ResultActions result = mockMvc.perform(get("http://localhost/bank_accounts/get?id=200"));
        assertThat(result, notNullValue());
        assertThat(result.andReturn().getModelAndView().getViewName(), equalTo("error"));
    }

    @Test
    public void illegalArgumentGet() throws Exception {
        ResultActions result = mockMvc.perform(get("http://localhost/bank_accounts/get?id=wrongType"));
        assertThat(result, notNullValue());
        assertThat(result.andReturn().getModelAndView().getViewName(), equalTo("error"));
    }

    @Test
    public void correctEdit() throws Exception {
        mockMvc.perform(get("http://localhost/bank_accounts/save?id=5&iban=123000&bic=321000"));
        checkGet("http://localhost/bank_accounts/get?id=5", "{\"id\":5,\"iban\":\"123000\",\"bic\":\"321000\"}");
    }

    @Test
    public void correctCreate() throws Exception {
        mockMvc.perform(get("http://localhost/bank_accounts/save?id=&iban=123000&bic=321000"));
        checkGet("http://localhost/bank_accounts/get?id=20", "{\"id\":20,\"iban\":\"123000\",\"bic\":\"321000\"}");
    }

    @Test
    public void correctRemove() throws Exception {
        mockMvc.perform(get("http://localhost/bank_accounts/remove?id=5"));
        ResultActions result = mockMvc.perform(get("http://localhost/bank_accounts/list/"));
        String jsonResultObject = "[{\"id\":0,\"iban\":\"12345678912345678800001\",\"bic\":\"122001\"}," +
                "{\"id\":1,\"iban\":\"12345678912345678800002\",\"bic\":\"122002\"}," +
                "{\"id\":2,\"iban\":\"12345678912345678800003\",\"bic\":\"122003\"}," +
                "{\"id\":3,\"iban\":\"12345678912345678800004\",\"bic\":\"122004\"}," +
                "{\"id\":4,\"iban\":\"12345678912345678800005\",\"bic\":\"122005\"}," +
                "{\"id\":6,\"iban\":\"12345678912345678900001\",\"bic\":\"123001\"}," +
                "{\"id\":7,\"iban\":\"12345678912345678900002\",\"bic\":\"123002\"}," +
                "{\"id\":8,\"iban\":\"12345678912345678900003\",\"bic\":\"123003\"}," +
                "{\"id\":9,\"iban\":\"12345678912345678900004\",\"bic\":\"123004\"}," +
                "{\"id\":10,\"iban\":\"12345678912345678900005\",\"bic\":\"123005\"}," +
                "{\"id\":11,\"iban\":\"12345678912345678900006\",\"bic\":\"123006\"}]";
        assertThat(result.andReturn().getResponse().getContentAsString(),
                equalTo(jsonResultObject));
    }

    private void checkGet(String url, String resultJson) throws Exception {
        ResultActions result = mockMvc.perform(get(url));
        assertThat(result, notNullValue());
        assertThat(result.andReturn().getResponse().getContentAsString(),
                equalTo(resultJson));
    }
}
