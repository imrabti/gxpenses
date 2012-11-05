package com.nuvola.gxpenses.client.security;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

public class SecurityUtils {

    private final StorageMap sessionStorage;

    public SecurityUtils() {
        sessionStorage = new StorageMap(Storage.getSessionStorageIfSupported());
    }

    public void setCredentials(String username, String password) {
        sessionStorage.put(Credentials.USERNAME.name(), username);
        sessionStorage.put(Credentials.PASSWORD.name(), password);
    }

    public void clearCredentials() {
        sessionStorage.remove(Credentials.USERNAME.name());
        sessionStorage.remove(Credentials.PASSWORD.name());
    }

    public String getUsername() {
        return sessionStorage.get(Credentials.USERNAME.name());
    }

    public String getPassword() {
        return sessionStorage.get(Credentials.PASSWORD.name());
    }

    public Boolean isLoggedIn() {
        return sessionStorage.containsKey(Credentials.USERNAME.name()) &&
                sessionStorage.containsKey(Credentials.PASSWORD.name());
    }

}
