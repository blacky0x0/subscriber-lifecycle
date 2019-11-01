package com.github.blacky.subscriber_lifecycle.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.SubscriberDao;
import com.github.blacky.subscriber_lifecycle.model.Status;
import com.github.blacky.subscriber_lifecycle.service.SubscriberService;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ImportAutoConfiguration(value = SubscriberControllerTest.Conf.class, exclude = {DataSourceAutoConfiguration.class})
class SubscriberControllerTest {

    @SpyBean
    SubscriberService service;

    @Test
    void onCall(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/call")).andExpect(status().isOk());
    }

    @Test
    void onSms(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/sms")).andExpect(status().isOk());
    }

    @Test
    void getAccount(@Autowired MockMvc mvc) throws Exception {
        Account account = new Account(0L, Status.Active);
        doReturn(account).when(service).getAccount();
        String expected = new ObjectMapper().writeValueAsString(account);
        mvc.perform(get("/account")).andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void makeDeposit(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/account/deposit")).andExpect(status().isOk());
    }


    @AutoConfigureMockMvc
    static class Conf {
        @MockBean
        DSLContext dsl;

        @MockBean
        SubscriberDao dao;
    }

}