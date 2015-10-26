package org.asuki.springboot.controller.api;

import org.asuki.springboot.controller.api.model.Hoge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("demo")
public class JerseyResource {
    @GET
    @Produces(APPLICATION_JSON)
    public Hoge get() {
        return new Hoge(456, "Andy");
    }
}
