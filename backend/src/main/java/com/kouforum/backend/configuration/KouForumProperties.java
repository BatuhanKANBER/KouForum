package com.kouforum.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "kouforum")
@Configuration
public class KouForumProperties {
    private Email email;

    private Client client;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static record Email(
            String username,
            String password,
            String host,
            int port,
            String from,
            String key) {
    }

    public static record Client(
            String host) {
    }
}
