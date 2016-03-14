package com.ostdlabs.bank_account.jms.service.impl;

import com.ostdlabs.bank_account.jms.config.JmsSpringConfig;
import com.ostdlabs.bank_account.jms.service.JmsSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * For testing jms.
 * It's works. For tests running test mq.
 * TODO: code all test cases.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JmsSpringConfig.class})
public class JmsSenderImplTest {

    @Inject
    private JmsSender jmsSender;

    @Test
    public void testSend() {
        jmsSender.send("test");
    }
}
