package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        CloudinaryImage result = null;
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Ffree-vector%2Ffunny-error-404-background-design_1161579.htm&psig=AOvVaw3G8bRhVv4zGHKkUuZALeyq&ust=1699788490971000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIibmd7ru4IDFQAAAAAdAAAAABAE");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            result = new CloudinaryImage();
            result.setPublicId(publicId);
            result.setUrl(url);
        } finally {
            tempFile.delete();
        }

        return result;
    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
