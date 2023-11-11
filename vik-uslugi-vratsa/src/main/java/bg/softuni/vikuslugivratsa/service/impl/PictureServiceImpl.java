package bg.softuni.vikuslugivratsa.service.impl;

import bg.softuni.vikuslugivratsa.model.entity.PictureEntity;
import bg.softuni.vikuslugivratsa.repository.PictureRepository;
import bg.softuni.vikuslugivratsa.service.CloudinaryImage;
import bg.softuni.vikuslugivratsa.service.CloudinaryService;
import bg.softuni.vikuslugivratsa.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {

        CloudinaryImage uploaded = this.cloudinaryService.upload(file);

       PictureEntity picture = new PictureEntity();
       picture.setPublicId(uploaded.getPublicId());
       picture.setUrl(uploaded.getUrl());
       picture.setTitle(title);

       return picture;
    }
}
