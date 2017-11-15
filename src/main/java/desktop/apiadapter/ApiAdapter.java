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

    private static String domain = "https://sci-utn.herokuapp.com/";

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
        List<Contenedor> contenedores = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray;
        try {
            jsonArray = mapper.readTree(response);
            Contenedor contenedor;
            for(JsonNode node : jsonArray) {
                System.out.println(node);
                contenedor = new Contenedor();
                contenedor.setId(Integer.parseInt(String.valueOf(node.get("id"))));
                contenedor.setMaterial(node.get("material").asText());
                contenedor.setCordX(Double.parseDouble(String.valueOf(node.get("cordX"))));
                contenedor.setCordY(Double.parseDouble(String.valueOf(node.get("cordY"))));
                contenedor.setRecolectorName(node.get("recolectorName").asText());
                contenedor.setCapacidad(Double.parseDouble(String.valueOf(node.get("capacidad"))));
//                contenedor.setRecolectorName("asdfasdf");
                contenedores.add(contenedor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenedores;
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
                recolector.setNombre(node.get("nombre").asText());
                recolector.setDni(Integer.parseInt(String.valueOf(node.get("dni"))));

                List<Contenedor> contenedores = new ArrayList<>();
                Contenedor contenedor;
                for (JsonNode contenedorNode : node.get("contenedorDTOs")) {
                    contenedor = new Contenedor();
                    contenedor.setId(Integer.parseInt(contenedorNode.get("id").asText()));
                    contenedor.setMaterial(contenedorNode.get("material").asText());
                    contenedor.setCordX(Integer.parseInt(contenedorNode.get("cordX").asText()));
                    contenedor.setCordY(Integer.parseInt(contenedorNode.get("cordY").asText()));
                    contenedores.add(contenedor);
                }
                recolector.getContenedores().addAll(contenedores);
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
        formData.add("capacidad", String.valueOf(contenedor.getCapacidad()));

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

    public static String modifyContenedor(Contenedor contenedor, String empresa, int id) {
        String url = domain + "/empresa/" + empresa + "/contenedor" + "/" + id;
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("material", contenedor.getMaterial());
        formData.add("cordX", String.valueOf(contenedor.getCordX()));
        formData.add("cordY", String.valueOf(contenedor.getCordY()));
        formData.add("capacidad", String.valueOf(contenedor.getCapacidad()));

        webResource
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        return webResource.put(String.class, formData);
    }

    public static String modifyRecolector(Recolector recolector, String empresa, int id) {
        String url = domain + "/empresa/" + empresa + "/recolector" + "/" + id;
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("nombre", recolector.getNombre());
        formData.add("dni", String.valueOf(recolector.getDni()));

        webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

        return webResource.put(String.class, formData);
    }

    public static void deleteContenedor(String empresa, int id) {
        String url = domain + "/empresa/" + empresa + "/contenedor" + "/" + id;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        webResource.delete(String.class);
    }

    public static void deleteRecolector(String empresa, int id) {
        String url = domain + "/empresa/" + empresa + "/recolector" + "/" + id;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        webResource.delete(String.class);
    }

    public static void asignarRecolector(int contenedorId, int id) {
        String url = domain + "/recolector/" + id + "/contenedor" + "/" + contenedorId;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        webResource.post(String.class);
    }
}