package services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entities.DetallePedido;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RecomendacionService {

    public String getRecommendations(List<DetallePedido> purchaseHistory) throws Exception {
        URL url = new URL("https://artificialinteligenceapi-1.onrender.com/recommend");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JsonArray purchaseHistoryJson = new JsonArray();
        for (DetallePedido detalle : purchaseHistory) {
            JsonObject detalleJson = new JsonObject();
            detalleJson.addProperty("name", detalle.getProducto().getNombre());
            detalleJson.addProperty("price", detalle.getProducto().getPrecio());
            detalleJson.addProperty("quantity", detalle.getCantidad());
            detalleJson.addProperty("category", detalle.getProducto().getCategoria());
            detalleJson.addProperty("description", detalle.getProducto().getDescripcion());
            purchaseHistoryJson.add(detalleJson);
        }

        JsonObject requestJson = new JsonObject();
        requestJson.add("purchase_history", purchaseHistoryJson);

        String jsonInputString = new Gson().toJson(requestJson);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + code);
        }

        StringBuilder response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "utf-8"))) {
            String output;
            response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
        }
        conn.disconnect();

        return response.toString();
    }
}
