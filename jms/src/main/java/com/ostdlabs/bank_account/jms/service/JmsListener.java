package com.ostdlabs.bank_account.jms.service;

public interface JmsListener {
    void handleMessage(String message);
}
