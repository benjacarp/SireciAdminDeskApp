package desktop.apiadapter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ApiAdapter {

    public static String logIn(String user, String pass) throws Exception {
        String url = "http://localhost:8080/empresa/login?user=" + user + "&pass=" + pass;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        return webResource.post(String.class);
    }
}
