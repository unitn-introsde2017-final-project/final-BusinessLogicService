package introsde.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("sdelab")
public class BusinessAppConfig extends ResourceConfig {
    public BusinessAppConfig () {
        packages("introsde");
    }
}
