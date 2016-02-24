package com.ostdlabs.bank_account.core.service.impl;

import com.ostdlabs.bank_account.core.service.SystemUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * {@inheritDoc}
 */
public class SystemUtilImpl implements SystemUtil {
    /**
     * Название системного свойства, в котором хранится имя учетной записи, под которой пользователь вошел в систему
     */
    public static final String NIX_USER = "USER";
    public static final String WIN_USER = "USERNAME";

    /**
     * {@inheritDoc}
     */
    public String getCurrentUserName() {
        return getEnvValue(WIN_USER);
    }

    /**
     * {@inheritDoc}
     */
    public String getCurrentHostName() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    public final String getEnvValue(String key) {
        String value = null;
        try {
            final Properties p = getEnvVars();

            value = p.getProperty(key);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }

    private Properties getEnvVars() throws Throwable {
        Process p = null;
        final Properties envVars = new Properties();
        final Runtime r = Runtime.getRuntime();
        final String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") > -1) {
            p = r.exec("cmd.exe /c set");
        } else {
            // it is Unix os.
            p = r.exec("env");
        }
        try (InputStream inputStream = p.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                final int idx = line.indexOf('=');
                final String key = line.substring(0, idx);
                final String value = line.substring(idx + 1);
                envVars.setProperty(key, value);
            }
        }
        return envVars;
    }
}
