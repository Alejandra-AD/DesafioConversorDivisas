package AppPrincipal;


import Modelos.Moneda;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lecturaBusqueda = new Scanner(System.in);
        System.out.println("Indique su moneda");
        String busqueda = lecturaBusqueda.nextLine();
        String urlBusqueda = "https://v6.exchangerate-api.com/v6/5f75da2536d656f918b82ded/latest/"+busqueda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlBusqueda))
                .build();

        try{
            HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                //System.out.println("conexión existosa");

                Gson gson = new Gson();
                String responseBody = response.body();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                double monedaOrigen = jsonObject.getAsJsonObject("conversion_rates").get(busqueda.toUpperCase()).getAsDouble();
                System.out.println(monedaOrigen);
                double monedaDestino = jsonObject.getAsJsonObject("conversion_rates").get("USD").getAsDouble();
                System.out.println(monedaDestino);
                System.out.println("indique cantidad que quiere convertir");
                Scanner scanCantidad = new Scanner(System.in);
                double cantidadMonedaOrigen = scanCantidad.nextDouble()*monedaOrigen;
                double calculoConversion = (cantidadMonedaOrigen*monedaDestino);
                System.out.println(calculoConversion);




            }

        }catch (IOException | InterruptedException e){
            System.out.println("error conexión");
        }



    }
}
