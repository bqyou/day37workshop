package tfip.nus.iss.day37workshopserver.service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadService {

    @Autowired
    private AmazonS3 s3Client;

    public String upload(MultipartFile file) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("name", "Bq");
        userData.put("uploadTime", Instant.now().toString());
        userData.put("originalFilename", file.getOriginalFilename());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        metadata.setUserMetadata(userData);

        String key = UUID.randomUUID().toString().substring(0, 8);

        String finalFileUpload = key + ".png";

        PutObjectRequest putRequest = new PutObjectRequest(
                "bqday37workshop",
                "myobject/%s.%s".formatted(key, finalFileUpload),
                file.getInputStream(),
                metadata);

        putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(putRequest);
        return "myobject/%s.%s".formatted(key, finalFileUpload);
    }

}
