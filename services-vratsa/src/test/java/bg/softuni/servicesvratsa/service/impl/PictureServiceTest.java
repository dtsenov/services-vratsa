package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.PictureEntity;
import bg.softuni.servicesvratsa.repository.PictureRepository;
import bg.softuni.servicesvratsa.service.CloudinaryService;
import bg.softuni.servicesvratsa.service.PictureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PictureServiceTest {

    private PictureService serviceToTest;

    @Mock
    private PictureRepository mockPictureRepository;

    @Mock
    private CloudinaryService mockCloudinaryService;

    @Mock
    private MultipartFile mockMultipartFile;

    @BeforeEach
    void setUp() {
        serviceToTest = new PictureServiceImpl(mockPictureRepository, mockCloudinaryService);
    }

    @Test
    void testInitPicturesNotInit() {
        PictureEntity picture = new PictureEntity();

        when(mockPictureRepository.count())
                .thenReturn(2L);

        serviceToTest.initPictures();

        verify(mockPictureRepository, times(0)).save(picture);
    }

    @Test
    void testInitPicturesSuccess() {
        when(mockPictureRepository.count())
                .thenReturn(0L);

        serviceToTest.initPictures();

        verify(mockPictureRepository, times(10)).save(any());
    }

    @Test
    void testUploadPicture() throws IOException {
        String title = "testPicture";

        CloudinaryImage testCloudinaryImage = new CloudinaryImage();
        testCloudinaryImage.setUrl("https://gosho.com");
        testCloudinaryImage.setPublicId("gosho");

        when(mockCloudinaryService.upload(any(MultipartFile.class)))
                .thenReturn(testCloudinaryImage);

        PictureEntity pictureEntity = serviceToTest.uploadPicture(mockMultipartFile, title);

        assertNotNull(pictureEntity);
        assertEquals("gosho", pictureEntity.getPublicId());
        assertEquals("https://gosho.com", pictureEntity.getUrl());


    }

    @Test
    void testFindPictureById() {
        Long pictureId = 1L;
        serviceToTest.findPictureById(pictureId);
        verify(mockPictureRepository, times(1)).findById(pictureId);
    }


}
