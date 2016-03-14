package com.ostdlabs.bank_account.jms.service;

/** For real time handling messagies from queue. */
public interface JmsListener {
    void handleMessage(String message);
}
