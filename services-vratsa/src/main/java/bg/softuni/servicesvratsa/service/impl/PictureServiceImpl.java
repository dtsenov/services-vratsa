package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.repository.PictureRepository;
import bg.softuni.servicesvratsa.service.CloudinaryImage;
import bg.softuni.servicesvratsa.service.CloudinaryService;
import bg.softuni.servicesvratsa.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void initPictures() {

        if (pictureRepository.count() != 0) {
            return;
        }

        PictureEntity picture = new PictureEntity();

        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    picture.setId(1L);
                    picture.setTitle("смяна-водомер");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699961300/services-vratsa/ugoybke3xzvzraht0ejf.jpg");
                    picture.setPublicId("services-vratsa/ugoybke3xzvzraht0ejf");
                    break;
                case 1:
                    picture.setId(2L);
                    picture.setTitle("смяна-кран");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699961300/services-vratsa/ennejtg5itajqg7vtw83.jpg");
                    picture.setPublicId("services-vratsa/ennejtg5itajqg7vtw83");
                    break;
                case 2:
                    picture.setId(3L);
                    picture.setTitle("санитарни-прибори");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699961300/services-vratsa/oajhmxq7uannvcg3jgzq.jpg");
                    picture.setPublicId("services-vratsa/oajhmxq7uannvcg3jgzq");
                    break;
                case 3:
                    picture.setId(4L);
                    picture.setTitle("водопроводна-система");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699961300/services-vratsa/pt5iw4mv57eqfkcp1dn4.jpg");
                    picture.setPublicId("services-vratsa/pt5iw4mv57eqfkcp1dn4");
                    break;
                case 4:
                    picture.setId(5L);
                    picture.setTitle("водомер-powogaz");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699964439/services-vratsa/aonakewaogzhkgfbgq8a.jpg");
                    picture.setPublicId("services-vratsa/aonakewaogzhkgfbgq8a");
                    break;
                case 5:
                    picture.setId(6L);
                    picture.setTitle("водомер-zenner");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699964439/services-vratsa/mvo9map5fppdiikauai0.jpg");
                    picture.setPublicId("services-vratsa/mvo9map5fppdiikauai0");
                    break;
                case 6:
                    picture.setId(7L);
                    picture.setTitle("кран-sakar");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699965473/services-vratsa/znrl0xuf6d9fnep5neyd.jpg");
                    picture.setPublicId("services-vratsa/znrl0xuf6d9fnep5neyd");
                    break;
                case 7:
                    picture.setId(8L);
                    picture.setTitle("кран-arco");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699965473/services-vratsa/jgklmogphgmdfqyljklh.jpg");
                    picture.setPublicId("services-vratsa/jgklmogphgmdfqyljklh");
                    break;
                case 8:
                    picture.setId(9L);
                    picture.setTitle("ключ-rothenberger");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699966112/services-vratsa/bpofp6bdigv7asmhwybj.jpg");
                    picture.setPublicId("services-vratsa/bpofp6bdigv7asmhwybj");
                    break;
                case 9:
                    picture.setId(10L);
                    picture.setTitle("ключ-topmaster");
                    picture.setUrl("https://res.cloudinary.com/ddxk7emug/image/upload/v1699966112/services-vratsa/tefl3nafeuey13geth9b.jpg");
                    picture.setPublicId("services-vratsa/tefl3nafeuey13geth9b");
                    break;
            }

            pictureRepository.save(picture);
        }
    }

    @Override
    public PictureEntity uploadPicture(MultipartFile file, String title) throws IOException {

        CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        PictureEntity picture = new PictureEntity();
        picture.setPublicId(uploaded.getPublicId());
        picture.setUrl(uploaded.getUrl());
        picture.setTitle(title);

        pictureRepository.save(picture);

        return picture;
    }

    @Override
    public PictureEntity findPictureById(Long pictureId) {
        return pictureRepository.findById(pictureId).orElse(null);
    }

}
