package vn.nambb.mall.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.nambb.mall.dto.CategoryParam;
import vn.nambb.mall.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity getById(Long id);

    @Transactional
    int create(CategoryParam categoryParam, MultipartFile file);

    int update(CategoryParam categoryParam);

    int delete(Long id);
}
