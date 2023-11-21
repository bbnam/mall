package vn.nambb.mall.service;

import org.springframework.web.multipart.MultipartFile;
import vn.nambb.mall.dto.ImageUploadDTO;

public interface ImageService {
    ImageUploadDTO create(MultipartFile file);
}
