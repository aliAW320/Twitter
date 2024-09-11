package com.twiter.Twiter12.Utils;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class Media {

    public static String downloadMedia(String mediaName, String mediaData) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(mediaData);

            // Specify the directory before src
            String directoryPath = "../media/";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            // Handle duplicate media names by appending a unique identifier
            File file = new File(directoryPath + mediaName);
            if (file.exists()) {
                // Generate a unique name by appending a timestamp and UUID
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String uniqueID = UUID.randomUUID().toString();
                String extension = "";

                int dotIndex = mediaName.lastIndexOf(".");
                if (dotIndex != -1) {
                    extension = mediaName.substring(dotIndex); // Get the file extension
                    mediaName = mediaName.substring(0, dotIndex); // Get the name without extension
                }

                mediaName = mediaName + "_" + timestamp + "_" + uniqueID + extension;
                file = new File(directoryPath + mediaName);
            }

            // Save the image in the specified directory
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(imageBytes);
                System.out.println("Media saved as: " + file.getAbsolutePath());
            }

            // Return the relative path of the saved media
            return "media/" + mediaName;

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if an error occurs
        }
    }



        public static String uploadMedia(String mediaName) {
            String encodedString = null;
            try {
                // Specify the directory path before src
                String directoryPath = "../";
                File file = new File(directoryPath + mediaName);

                // Check if the file exists
                if (!file.exists()||mediaName==null) {
                    System.err.println("File not found: " + file.getAbsolutePath());
                    return null;
                }

                // Read the file into a byte array
                byte[] fileBytes = new byte[(int) file.length()];
                try (FileInputStream fis = new FileInputStream(file)) {
                    fis.read(fileBytes);
                }

                // Encode the byte array to a Base64 string
                encodedString = Base64.getEncoder().encodeToString(fileBytes);
                System.out.println("Media uploaded from: " + file.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return encodedString;
        }
    }


