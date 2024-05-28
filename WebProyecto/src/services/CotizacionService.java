package services;

import com.google.gson.Gson;
import Request.CotizacionRequest;
import Response.CotizacionResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CotizacionService {

    private static final String API_URL = "http://localhost:5076/api/GeoCode/cotizacion";

    public CotizacionResponse obtenerCotizacion(CotizacionRequest request) throws Exception {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);

        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonRequest.getBytes());
            os.flush();
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder responseBuilder = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            responseBuilder.append(output);
        }

        conn.disconnect();

        return gson.fromJson(responseBuilder.toString(), CotizacionResponse.class);
    }
}
