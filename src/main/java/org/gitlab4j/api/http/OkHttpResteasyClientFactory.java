package org.gitlab4j.api.http;

import net.ltgt.resteasy.client.okhttp3.OkHttpClientEngine;
import okhttp3.OkHttpClient;
import org.gitlab4j.api.utils.JacksonJson;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class OkHttpResteasyClientFactory {
    public static ResteasyClient getClient(OkHttpClient client) {
        ResteasyProviderFactory instance = ResteasyProviderFactory.getInstance();
        if (!instance.getClassContracts().containsKey(JacksonJson.class)) {
            instance.registerProvider(JacksonJson.class);
        }

        RegisterBuiltin.register(instance);

        return new ResteasyClientBuilder().providerFactory(instance)
            .httpEngine(new OkHttpClientEngine(client))
            .build();
    }
}
