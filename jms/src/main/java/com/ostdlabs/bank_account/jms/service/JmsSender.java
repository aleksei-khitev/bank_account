package com.ostdlabs.bank_account.jms.service;

/** For sending message to queue. */
public interface JmsSender {
    public void send(String message);
}
