package com.github.blacky.subscriber_lifecycle.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.blacky.subscriber_lifecycle.model.Status;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SubscriberControllerTest {

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
        String expected = new ObjectMapper().writeValueAsString(account);
        mvc.perform(get("/account")).andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void makeDeposit(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/account/deposit")).andExpect(status().isOk());
    }

}