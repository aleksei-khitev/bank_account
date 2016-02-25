package com.ostdlabs.bank_account.web.controller;

import com.ostdlabs.bank_account.web.conf.SpringWebConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringWebConfiguration.class})
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
        assertThat(result.andReturn().getResponse(), equalTo("index"));
    }

    @Test
    public void testGet() throws Exception {
        ResultActions result = mockMvc.perform(get("http://localhost/bank_accounts/get?id=5/"));
        assertThat(result, notNullValue());
        assertThat(result.andReturn().getModelAndView().getViewName(), equalTo("index"));
    }
}
