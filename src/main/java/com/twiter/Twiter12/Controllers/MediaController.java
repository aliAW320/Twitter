package com.twiter.Twiter12.Controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin
public class MediaController {

    @RequestMapping("/media/{mediaName}")
    public ResponseEntity<Resource> serveMedia(@PathVariable String mediaName) {
        try {
            // Specify the directory path before src
            Path mediaDirectory = Paths.get("../media/").toAbsolutePath().normalize();
            Path filePath = mediaDirectory.resolve(mediaName).normalize();

            // Load the file as a resource
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                // Return the resource with proper headers
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
