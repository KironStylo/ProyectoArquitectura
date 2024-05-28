package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

public class PodService {

    private static final String ENDPOINT_URL_OBTENER = "http://10.43.101.36:8080/api/pods?";
    private static final String ENDPOINT_URL_CREAR = "http://10.43.101.36:8080/clientData/update";

    public String obtenerPodUrl(String webID) throws Exception {
        String urlString = ENDPOINT_URL_OBTENER + "webid=" + webID;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            String[] urlsArray = gson.fromJson(content.toString(), String[].class);
            if (urlsArray.length > 0) {
                return urlsArray[0];
            } else {
                throw new Exception("No URLs returned from the POD service.");
            }
        } else {
            throw new Exception("Failed to fetch POD URL. HTTP response code: " + responseCode);
        }
    }

    public void crearDireccionPod(String identifier, String address) throws Exception {
        URL url = new URL(ENDPOINT_URL_CREAR);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = new Gson().toJson(new CrearDireccionRequest(identifier+"/DataClient/20230315/ClientData1", address));
        
        System.out.println("El json" + jsonInputString);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to create POD address. HTTP response code: " + responseCode);
        }
    }

    private class CrearDireccionRequest {
        @SuppressWarnings("unused")
		private String identifier;
        @SuppressWarnings("unused")
		private String address;

        public CrearDireccionRequest(String identifier, String address) {
            this.identifier = identifier;
            this.address = address;
        }
    }
}
