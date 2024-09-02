package com.twiter.Twiter12.Utils;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Media {

    public static void downloadMedia(HttpExchange httpExchange) {
        try {
            String jsonString = new String(httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);
            String mediaName = jsonObject.getString("mediaName");
            String mediaData = jsonObject.getString("mediaData");

            // Decode the base64 string
            byte[] imageBytes = Base64.getDecoder().decode(mediaData);

            // Save the image
            try (FileOutputStream fos = new FileOutputStream(mediaName)) {
                fos.write(imageBytes);
                System.out.println("Media saved as: " + mediaName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadMedia(HttpExchange httpExchange) {
        try {
            // Get the content type and boundary from headers
            String contentType = httpExchange.getRequestHeaders().getFirst("Content-Type");
            String boundary = contentType.split("boundary=")[1];

            // Read the body of the request
            InputStream inputStream = httpExchange.getRequestBody();
            byte[] bodyBytes = inputStream.readAllBytes();
            String body = new String(bodyBytes, StandardCharsets.UTF_8);

            // Split the body into parts based on the boundary
            String[] parts = body.split("--" + boundary);

            for (String part : parts) {
                // Skip the preamble and epilogue
                if (part.contains("Content-Disposition")) {
                    String[] headersAndBody = part.split("\r\n\r\n", 2);
                    String headers = headersAndBody[0];
                    String fileData = headersAndBody[1].trim();

                    // Get the filename from Content-Disposition header
                    String filename = null;
                    for (String headerLine : headers.split("\r\n")) {
                        if (headerLine.startsWith("Content-Disposition")) {
                            String[] elements = headerLine.split(";");
                            for (String element : elements) {
                                if (element.trim().startsWith("filename=")) {
                                    filename = element.split("=")[1].trim().replaceAll("\"", "");
                                    break;
                                }
                            }
                        }
                    }

                    if (filename != null) {
                        // Save the file data to disk
                        try (FileOutputStream fos = new FileOutputStream(filename)) {
                            fos.write(fileData.getBytes(StandardCharsets.ISO_8859_1)); // Keep the encoding as raw bytes
                            System.out.println("File uploaded and saved as: " + filename);
                        }
                    }
                }
            }

            // Send response to the client
            String response = "Media uploaded successfully!";
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
