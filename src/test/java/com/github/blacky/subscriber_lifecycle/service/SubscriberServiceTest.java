package com.github.blacky.subscriber_lifecycle.service;


import com.github.blacky.subscriber_lifecycle.jooq.enums.Status;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.CallDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.SubscriberDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = {SubscriberServiceTest.Initializer.class})
public class SubscriberServiceTest {

    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:10-alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("schema-postgresql.sql")
            .withReuse(false);

    @Autowired
    private SubscriberDao subscriberRepository;
    @Autowired
    private CallDao callRepository;

    @Test
    public void simpleInsertTest() {
        log.info("Connection URL: {}", postgreSQLContainer.getJdbcUrl());

        subscriberRepository.insert(of("Philip", "Fry", "+12025008080", 100L, Status.Active));
        subscriberRepository.insert(of("Bender", "Rodríguez", "+12025008081", 200L, Status.Active));
        callRepository.insert(of(1L));
        callRepository.insert(of(2L));
        callRepository.insert(of(1L));
    }

    public Subscriber of(String firstName, String lastName, String msisdn, Long balance, Status status) {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(firstName);
        subscriber.setLastName(lastName);
        subscriber.setMsisdn(msisdn);
        subscriber.setBalance(balance);
        subscriber.setStatus(status);
        return subscriber;
    }

    public Call of(Long subscriberId) {
        Call call = new Call();
        call.setSubscriberId(subscriberId);
        return call;
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            List<String> pairs = new ArrayList<>();

            pairs.add("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl());
            pairs.add("spring.datasource.username=" + postgreSQLContainer.getUsername());
            pairs.add("spring.datasource.password=" + postgreSQLContainer.getPassword());

            TestPropertyValues.of(pairs).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
