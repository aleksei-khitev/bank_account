package com.ostdlabs.bank_account.jms.service.impl;

import com.ostdlabs.bank_account.jms.service.JmsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
public class JmsListenerImpl implements JmsListener {
    private static final Logger logger = LoggerFactory.getLogger(JmsListenerImpl.class);

    @Override
    public void handleMessage(String message) {
        logger.info("Received: " + message);
    }
}
