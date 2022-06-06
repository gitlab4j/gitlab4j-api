package org.gitlab4j.api.http;

import net.ltgt.resteasy.client.okhttp3.OkHttpClientEngine;
import okhttp3.OkHttpClient;
import org.gitlab4j.api.utils.JacksonJson;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.client.jaxrs.internal.ClientConfiguration;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class OkHttpResteasyClientFactory {
    public static ResteasyClient getClient(OkHttpClient client) {
        ResteasyProviderFactory instance = ResteasyProviderFactory.getInstance();
        instance.registerProvider(JacksonJson.class);
        RegisterBuiltin.register(instance);

        ClientConfiguration clientConfig = new ClientConfiguration(instance);
        clientConfig.property(ClientProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        clientConfig.property(ClientProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);

        return new ResteasyClientBuilder().providerFactory(instance)
            .register(JacksonFeature.class)
            .register(MultiPartFeature.class)
            .httpEngine(new OkHttpClientEngine(client))
            .withConfig(clientConfig)
            .build();
    }
}
