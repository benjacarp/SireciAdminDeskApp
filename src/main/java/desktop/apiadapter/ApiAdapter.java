package desktop.apiadapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import desktop.model.Contenedor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Contenedor> getContenedores(String empresa) {
        String url = domain + "/empresa/" + empresa + "/contenedor";
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        String response = webResource.get(String.class);

        return buildListOfContenedores(response);
    }

    private static List<Contenedor> buildListOfContenedores(String response) {
        List<Contenedor> contenedors = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray;
        try {
            jsonArray = mapper.readTree(response);
            String status = String.valueOf(jsonArray.get("success"));
            String message = String.valueOf(jsonArray.get("message"));
            Contenedor contenedor;
            for(JsonNode node : jsonArray) {
                System.out.println(node);
                contenedor = new Contenedor();
                contenedor.setId(Integer.parseInt(String.valueOf(node.get("id"))));
                contenedor.setMaterial(String.valueOf(node.get("material")));
                contenedor.setCordX(Integer.parseInt(String.valueOf(node.get("cordX"))));
                contenedor.setCordY(Integer.parseInt(String.valueOf(node.get("cordY"))));
                contenedors.add(contenedor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenedors;
    }
}
