package bg.softuni.vikuslugivratsa.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApplicationBeanConfiguration {

    private final CloudinaryConfig cloudinaryConfig;

    public ApplicationBeanConfiguration(CloudinaryConfig cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public Cloudinary cloudinary() {
//        return new Cloudinary(
//                Map.of(
//                        "cloud_name", cloudinaryConfig.getCloudName(),
//                        "api_key", cloudinaryConfig.getApiKey(),
//                        "api_secret", cloudinaryConfig.getApiSecret()
//                )
//        );
//    }
}
