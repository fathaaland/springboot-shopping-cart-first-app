package com.dailycodework.dreamshops.service.image;

import com.dailycodework.dreamshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageService implements IImageService {

    @Override
    public Image getImageById(Long id) {
        return null;
    }

    @Override
    public void deleteImageById(Long id) {

    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }
}
