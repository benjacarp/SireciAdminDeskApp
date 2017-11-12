package desktop.apiadapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import desktop.model.Contenedor;
import desktop.model.Recolector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
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

    public static List<Recolector> getRecolectores(String empresa) {
        String url = domain + "/empresa/" + empresa + "/recolector";
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        String response = webResource.get(String.class);

        return buildListOfRecolectores(response);
    }

    private static List<Recolector> buildListOfRecolectores(String response) {
        List<Recolector> recolectores = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray;
        try {
            jsonArray = mapper.readTree(response);
            Recolector recolector;
            for(JsonNode node : jsonArray) {
                System.out.println(node);
                recolector = new Recolector();
                recolector.setId(Integer.parseInt(String.valueOf(node.get("id"))));
                recolector.setNombre(String.valueOf(node.get("nombre")));
                recolector.setDni(Integer.parseInt(String.valueOf(node.get("dni"))));
                recolectores.add(recolector);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return recolectores;
    }

    public static String createContenedor(Contenedor contenedor, String empresa) {
        String url = domain + "/empresa/" + empresa + "/contenedor";
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("material", contenedor.getMaterial());
        formData.add("cordX", String.valueOf(contenedor.getCordX()));
        formData.add("cordY", String.valueOf(contenedor.getCordY()));

        webResource
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        return webResource.post(String.class, formData);
    }

    public static String createRecolector(Recolector recolector, String empresa) {
        String url = domain + "/empresa/" + empresa + "/recolector";
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("nombre", recolector.getNombre());
        formData.add("dni", String.valueOf(recolector.getDni()));

        webResource
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        return webResource.post(String.class, formData);
    }
}
