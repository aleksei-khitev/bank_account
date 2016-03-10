package com.ostdlabs.bank_account.jms.service.impl;

import com.ostdlabs.bank_account.jms.service.JmsSender;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

@Named
public class JmsSenderImpl implements JmsSender {

    @Inject
    JmsOperations jmsTemplate;

    @Inject
    Queue destination;

    public void send(final String text) {

        this.jmsTemplate.send((session) -> {
            Message message = session.createTextMessage(text);
            message.setJMSReplyTo(destination);
            return message;
        });
    }
}
