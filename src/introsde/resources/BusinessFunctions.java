package introsde.resources;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

@Path("/business") // indicates the path under which this resource will be available
public class BusinessFunctions {
    // When client wants HTML
    @GET
    @Path("/getPersonProfile") // you can pass path params to a service
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonProfile(@QueryParam("userId") int userId) {
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        
        System.out.println(userId);

        //Response resp = service.path("UserProfile?userId=" + userId).request().accept(MediaType.APPLICATION_JSON).get();
        Response resp = service.path("UserProfile").queryParam("userId", userId).request().accept(MediaType.APPLICATION_JSON).get();
        String user_data = resp.readEntity(String.class);
        System.out.println(user_data);
		return user_data;
    }
    
    private static URI getBaseURI() {
        //return UriBuilder.fromUri("http://localhost:9001/StorageService").build();
		return UriBuilder.fromUri("http://final-storage-service.herokuapp.com/StorageService").build();
    }
}