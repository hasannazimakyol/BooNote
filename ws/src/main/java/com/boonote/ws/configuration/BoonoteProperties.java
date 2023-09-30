package com.boonote.ws.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "boonote")
@Configuration
public class BoonoteProperties {
    private Email email;

    private Client client;

    public static record Email(
            String username,
            String password,
            String host,
            int port,
            String from) {
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public static record Client(
            String host) {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
