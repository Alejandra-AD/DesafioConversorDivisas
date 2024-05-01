package Calculos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * La clase ConversorMonedasAPI implementa la interfaz ConversorMonedas y proporciona
 * funcionalidades para convertir una cantidad de una moneda a otra utilizando una API de
 * tasas de cambio.
 */

public class ConversorMonedasAPI implements ConversorMonedas {

    @Override
    public void convertir(double cantidad, String monedaOrigen, String monedaDestino) {

        double resultado = -1;

        String busqueda = monedaOrigen.toUpperCase();
        String url = "https://v6.exchangerate-api.com/v6/5f75da2536d656f918b82ded/latest/"+busqueda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                //System.out.println("conexión existosa");

                String monedaOrigenFormato = monedaOrigen.toUpperCase();
                String monedaDestinoFormato = monedaDestino.toUpperCase();

                Gson gson = new Gson();
                String responseBody = response.body();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                System.out.println("Cantidad: $" + cantidad + "\n***********");
                double tasaOrigen = jsonObject.getAsJsonObject("conversion_rates").get(monedaOrigenFormato).getAsDouble();
                System.out.println(monedaOrigenFormato + ":" + tasaOrigen);
                double tasaDestino = jsonObject.getAsJsonObject("conversion_rates").get(monedaDestinoFormato).getAsDouble();
                System.out.println(monedaDestinoFormato + ":" +tasaDestino);


                resultado = cantidad*tasaDestino/tasaOrigen;
                System.out.println("$" + cantidad + " " + monedaOrigenFormato + " equivale a $" +resultado + " " + monedaDestinoFormato);


            }

        }catch (IOException | InterruptedException e){
            e.printStackTrace();// Imprime en orden el seguimiento de los métodos utilizados hasta el punto donde se encontró la excepción.

        }

    }
}
