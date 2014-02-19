package com.messners.gitlab.api.webhook;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class WebHookApplication extends Application {

    @Override
    public Set<Class<?>> getClasses () {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(WebHookResource.class);
        return (resources);
    }
}