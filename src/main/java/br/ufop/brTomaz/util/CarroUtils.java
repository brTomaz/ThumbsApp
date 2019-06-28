package br.ufop.brTomaz.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarroUtils {

    public List<Marca> obterMarcas() throws IOException {

        HttpClient client = HttpClients.createSystem();
        HttpUriRequest request = new HttpGet("http://fipeapi.appspot.com/api/1/carros/marcas.json");
        HttpResponse response = client.execute(request);
        return preencherArray(response);

    }

    public List<Marca> preencherArray(HttpResponse response) throws IOException {
        List<Marca> elementos = new ArrayList<>();

        if(response.getStatusLine().getStatusCode() == 200){
            String strJson = EntityUtils.toString(response.getEntity());
            JSONArray jsonResponse = new JSONArray(strJson);
            for(int i = 0; i < jsonResponse.length(); i++){
                JSONObject elem = jsonResponse.getJSONObject(i);
                String marca = elem.getString("name");
                int id = elem.getInt("id");
                Marca obj = new Marca(marca, id);
                elementos.add(obj);
            }
        } else{
            System.out.println("An error occurred. Status code: "+ response.getStatusLine().getStatusCode());
            System.out.println("Error message: " + response.getStatusLine().getReasonPhrase());
            return null;
        }

        return elementos;
    }

    public List<Marca> obterModelos(Marca marca) throws IOException {

        HttpClient client = HttpClients.createSystem();
        HttpUriRequest request = new HttpGet("http://fipeapi.appspot.com/api/1/carros/marcas/veiculos/" + marca.getId() + ".json");
        HttpResponse response = client.execute(request);
        return preencherArray(response);
    }
}
