package introsde.resources;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.json.JSONObject;
import org.json.JSONArray;

@Path("/business") // indicates the path under which this resource will be available
public class BusinessFunctions {
    // When client wants HTML
    @GET
    @Path("/getPersonProfile") // you can pass path params to a service
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonProfile(@QueryParam("userId") int userId) {
    	// Setup connectio to the storage layer
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        
        System.out.println(userId);

        // Get User Profile
        Response resp_user = service.path("UserProfile").queryParam("userId", userId).request().accept(MediaType.APPLICATION_JSON).get();
        String user_data = resp_user.readEntity(String.class);
        JSONObject user = new JSONObject(user_data);
        System.out.println(user_data);
        
        // Get Weather information
        Response resp_weather = service.path("WeatherInformation").queryParam("city", user.getString("city")).request().accept(MediaType.TEXT_PLAIN).get();
        String weather_data = resp_weather.readEntity(String.class);
        System.out.println(user.getString("city"));
        System.out.println(weather_data);
        
        // Calculate weather text
        String[] weather = weather_data.split("\\|"); // this is a regex, we need to escape | because of it's special meaning
        
        String weather_info = "The temperature is " + weather[0] + " C outside";
        if(Integer.parseInt(weather[1]) > 0) {
        	weather_info += ", the wind blows with " + weather[1] + " m/s";
        }
        if(Integer.parseInt(weather[2]) > 0) {
        	weather_info += ", rain is possible.";
        }
        
        user.put("weather_info", weather_info);
        
        // Calculate step text
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(today);
        
        user.put("steps_today", "No steps for today yet, but it is never late to start!");
        JSONArray steps = user.getJSONArray("steps");
        for (int i = 0; i < steps.length(); i++) {
        	JSONObject step = steps.getJSONObject(i);
        	//System.out.println(step.getString("date"));
        	if(step.getString("date").equals(today)) {
        		//System.out.println(step.getInt("number"));
        		//System.out.println(user.getInt("stepGoal"));
        		if(step.getInt("number") >= user.getInt("stepGoal")) {
        			user.put("steps_today", "Your already reached the goal today, nice work!");
        		} else {
        			user.put("steps_today", "You only need to do a little more to reach your goal, you already made " + step.getInt("number") + " steps today!");
        		}
        		break;
        	}
    	}
        
        //System.out.println("Ok?");
        System.out.println(user.toString());
        
		return user.toString();
    }
    
    private static URI getBaseURI() {
        //return UriBuilder.fromUri("http://localhost:9001/StorageService").build();
		return UriBuilder.fromUri("http://final-storage-service.herokuapp.com/StorageService").build();
    }
}