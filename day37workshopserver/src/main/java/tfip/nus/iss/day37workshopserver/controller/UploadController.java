package tfip.nus.iss.day37workshopserver.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import tfip.nus.iss.day37workshopserver.service.UploadService;

@Controller
public class UploadController {

    @Autowired
    private UploadService uploadSvc;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upload(
            @RequestPart MultipartFile imageFile,
            @RequestPart String title,
            @RequestPart String complain) throws IOException {

        String key = this.uploadSvc.upload(imageFile);

        JsonObject payload = Json.createObjectBuilder()
                .add("image-key", key)
                .build();
        return ResponseEntity.ok(payload.toString());

    }

}
