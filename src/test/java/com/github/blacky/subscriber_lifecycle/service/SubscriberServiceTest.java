package com.github.blacky.subscriber_lifecycle.service;


import com.github.blacky.subscriber_lifecycle.exception.SubscriberBlockedException;
import com.github.blacky.subscriber_lifecycle.exception.SubscriberEntityNotFoundException;
import com.github.blacky.subscriber_lifecycle.exception.SubscriberLimitExceededException;
import com.github.blacky.subscriber_lifecycle.jooq.enums.Status;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.CallDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.SubscriberDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Subscriber;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import com.github.blacky.subscriber_lifecycle.web.transfer.Deposit;
import com.github.blacky.subscriber_lifecycle.web.transfer.Sms;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
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
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertThrows;


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
    @Autowired
    private SubscriberService service;

    @Before
    public void init() {
        log.info("Connection URL: {}", postgreSQLContainer.getJdbcUrl());
    }

    @After
    public void clean() {
        subscriberRepository.deleteById(LongStream.range(1, 100).boxed().collect(Collectors.toList()));
        callRepository.deleteById(LongStream.range(1, 100).boxed().collect(Collectors.toList()));
    }

    @Test
    public void onCallUnknownMsisdnTest() {
        com.github.blacky.subscriber_lifecycle.web.transfer.Call call = new com.github.blacky.subscriber_lifecycle.web.transfer.Call();
        call.setFrom("+12025008081-unknown-msisdn");
        call.setTo("+12025008080");

        assertThrows(SubscriberEntityNotFoundException.class,
                () -> service.onCall(call),
                "Unknown msisdn");
    }

    @Test
    public void onCallBlockedTest() {
        Subscriber s = of("Bender", "Rodríguez", "+12025008081", 500L, Status.Blocked);
        subscriberRepository.insert(s);

        com.github.blacky.subscriber_lifecycle.web.transfer.Call call = new com.github.blacky.subscriber_lifecycle.web.transfer.Call();
        call.setFrom(s.getMsisdn());
        call.setTo("+12025008080");

        assertThrows(SubscriberBlockedException.class,
                () -> service.onCall(call),
                "Subscriber blocked");
    }

    @Test
    public void onCallLimitTest() {
        Subscriber subs = of("Philip", "Fry", "+12025008080", 300L, Status.Active);
        subscriberRepository.insert(subs);
        callRepository.insert(of(subs.getId()), of(subs.getId()), of(subs.getId()), of(subs.getId()), of(subs.getId()));

        com.github.blacky.subscriber_lifecycle.web.transfer.Call call = new com.github.blacky.subscriber_lifecycle.web.transfer.Call();
        call.setFrom(subs.getMsisdn());
        call.setTo("+12025008085");

        assertThrows(SubscriberLimitExceededException.class,
                () -> service.onCall(call),
                "Limit exceeded");
    }

    @Test
    public void onLastCallBeforeBlockedTest() {
        Subscriber subscriber = of("Leela", "Turanga", "+12025008085", 10L, Status.Active);
        subscriberRepository.insert(subscriber);
        com.github.blacky.subscriber_lifecycle.web.transfer.Call call = new com.github.blacky.subscriber_lifecycle.web.transfer.Call();
        call.setFrom("+12025008085");
        call.setTo("+12025008080");

        service.onCall(call);
        long actualBalance = subscriberRepository.fetchOneById(subscriber.getId()).getBalance();
        Status actualStatus = subscriberRepository.fetchOneById(subscriber.getId()).getStatus();

        Assert.assertEquals(-40L, actualBalance);
        Assert.assertEquals(Status.Blocked, actualStatus);
    }

    @Test
    public void onSmsUnknownMsisdnTest() {
        Sms sms = new Sms();
        sms.setFrom("+12025008085-unknown-msisdn");
        sms.setTo("+12025008080");
        sms.setText("some text");

        assertThrows(SubscriberEntityNotFoundException.class,
                () -> service.onSms(sms),
                "Unknown msisdn");
    }

    @Test
    public void onSmsBlockedMsisdnTest() {
        Subscriber s = of("Bender", "Rodríguez", "+12025008081", 500L, Status.Blocked);
        subscriberRepository.insert(s);
        Sms sms = new Sms();
        sms.setFrom(s.getMsisdn());
        sms.setTo("+12025008080");
        sms.setText("some text");

        assertThrows(SubscriberBlockedException.class,
                () -> service.onSms(sms),
                "Blocked msisdn");
    }

    @Test
    public void onLastSmsBeforeBlockedTest() {
        Subscriber subscriber = of("Leela", "Turanga", "+12025008085", 0L, Status.Active);
        subscriberRepository.insert(subscriber);
        Sms sms = new Sms();
        sms.setFrom("+12025008085");
        sms.setTo("+12025008080");
        sms.setText("Well, it wasn't a bad life, if only I could get back that time I spent watching Tron: Legacy");

        service.onSms(sms);
        long actualBalance = subscriberRepository.fetchOneById(subscriber.getId()).getBalance();
        Status actualStatus = subscriberRepository.fetchOneById(subscriber.getId()).getStatus();

        Assert.assertEquals(-1L, actualBalance);
        Assert.assertEquals(Status.Blocked, actualStatus);
    }

    @Test
    public void getAccountTest() {
        Subscriber subscriber = of("Leela", "Turanga", "+12025008085", 100L, Status.Active);
        subscriberRepository.insert(subscriber);

        Account account = service.getAccount(subscriber.getMsisdn());
        long actualBalance = subscriber.getBalance();
        String actualStatus = subscriber.getStatus().toString();

        Assert.assertEquals(account.getBalance(), actualBalance);
        Assert.assertEquals(account.getStatus().toString(), actualStatus);
    }

    @Test
    public void getAccountSubscriberNotFoundTest() {
        String msisdn = "+12025008085-any-wrong-msisdn";
        assertThrows(SubscriberEntityNotFoundException.class,
                () -> service.getAccount(msisdn),
                "Exception expected here");
    }

    @Test
    public void makeDepositTest() {
        Subscriber subscriber = of("Leela", "Turanga", "+12025008085", 100L, Status.Active);
        subscriberRepository.insert(subscriber);

        Deposit deposit = new Deposit();
        deposit.setAmount(100);
        deposit.setMsisdn("+12025008085");

        service.makeDeposit(deposit);
        long actualBalance = subscriberRepository.fetchOneById(subscriber.getId()).getBalance();

        Assert.assertEquals(200L, actualBalance);
    }

    @Test
    public void makeDepositAndUnblockTest() {
        Subscriber subscriber = of("Leela", "Turanga", "+12025008085", -10L, Status.Blocked);
        subscriberRepository.insert(subscriber);

        Deposit deposit = new Deposit();
        deposit.setAmount(100);
        deposit.setMsisdn("+12025008085");

        service.makeDeposit(deposit);
        long actualBalance = subscriberRepository.fetchOneById(subscriber.getId()).getBalance();
        Status actualStatus = subscriberRepository.fetchOneById(subscriber.getId()).getStatus();

        Assert.assertEquals(Status.Active, actualStatus);
        Assert.assertEquals(90L, actualBalance);
    }

    @Test
    public void makeDepositSubscriberNotFoundTest() {
        Deposit deposit = new Deposit();
        deposit.setAmount(100);
        deposit.setMsisdn("+12025008085-any-wrong-msisdn");

        assertThrows(SubscriberEntityNotFoundException.class,
                () -> service.makeDeposit(deposit),
                "Exception expected here");
    }

    public static Subscriber of(String firstName, String lastName, String msisdn, Long balance, Status status) {
        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(firstName);
        subscriber.setLastName(lastName);
        subscriber.setMsisdn(msisdn);
        subscriber.setBalance(balance);
        subscriber.setStatus(status);
        return subscriber;
    }

    public static Call of(Long subscriberId) {
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
