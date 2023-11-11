package bg.softuni.vikuslugivratsa.service;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException;
}
