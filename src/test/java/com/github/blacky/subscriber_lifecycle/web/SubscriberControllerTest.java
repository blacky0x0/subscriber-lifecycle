package com.github.blacky.subscriber_lifecycle.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.CallDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.SubscriberDao;
import com.github.blacky.subscriber_lifecycle.web.transfer.*;
import com.github.blacky.subscriber_lifecycle.service.SubscriberService;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
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
        Call call = new Call();
        doNothing().when(service).onCall(call);
        String body = new ObjectMapper().writeValueAsString(call);
        mvc.perform(post("/call").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void onSms(@Autowired MockMvc mvc) throws Exception {
        Sms sms = new Sms();
        doNothing().when(service).onSms(sms);
        String body = new ObjectMapper().writeValueAsString(sms);
        mvc.perform(post("/sms").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getAccount(@Autowired MockMvc mvc) throws Exception {
        Account account = new Account(0L, Status.Active);
        doReturn(account).when(service).getAccount("+12025008080");
        String expected = new ObjectMapper().writeValueAsString(account);
        mvc.perform(get("/account/+12025008080")).andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void makeDeposit(@Autowired MockMvc mvc) throws Exception {
        Deposit deposit = new Deposit();
        doNothing().when(service).makeDeposit(deposit);
        String body = new ObjectMapper().writeValueAsString(deposit);
        mvc.perform(post("/account/deposit").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @AutoConfigureMockMvc
    static class Conf {
        @MockBean
        DSLContext dsl;

        @MockBean
        SubscriberDao subscriberDao;

        @MockBean
        CallDao callDao;
    }

}