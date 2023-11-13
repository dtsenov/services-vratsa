package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PictureService {

    PictureEntity uploadPicture(MultipartFile file, String title) throws IOException;

    PictureEntity findPictureById(Long pictureId);
}
