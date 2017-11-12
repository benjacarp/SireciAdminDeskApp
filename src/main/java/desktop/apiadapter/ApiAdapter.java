package desktop.apiadapter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ApiAdapter {

    private static String domain = "http://localhost:8080";

    public static String logIn(String user, String pass) throws Exception {
        String url = domain + "/empresa/login?user=" + user + "&pass=" + pass;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        return webResource.get(String.class);
    }

    public static String infoEmpresa(String empresa) {
        String url = domain + "/empresa/" + empresa;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        return webResource.get(String.class);
    }
}
