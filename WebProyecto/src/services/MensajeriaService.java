package services;

import com.google.gson.Gson;
import Request.MensajeRequest;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MensajeriaService {

    private static final String ENDPOINT_URL = "http://localhost:5092/api/Email";

    public boolean enviarMensaje(MensajeRequest mensajeRequest) {
    	boolean funciono = false;
        try {
            // Convertir el objeto MensajeRequest a JSON
            Gson gson = new Gson();
            String mensajeJson = gson.toJson(mensajeRequest);

            // Crear la conexión HTTP
            URL url = new URL(ENDPOINT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Escribir el JSON en el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = mensajeJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificar el código de respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Mensaje enviado correctamente");
                funciono = true;
            } else {
                System.err.println("Error al enviar el mensaje. Código de respuesta: " + responseCode);
                funciono = false;
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
        }
        return funciono;
    }
}
