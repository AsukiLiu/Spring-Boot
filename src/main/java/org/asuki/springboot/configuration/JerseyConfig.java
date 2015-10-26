package org.asuki.springboot.configuration;

import org.asuki.springboot.controller.api.JerseyResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JerseyResource.class);
    }
}
