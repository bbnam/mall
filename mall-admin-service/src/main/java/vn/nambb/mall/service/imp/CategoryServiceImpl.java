package vn.nambb.mall.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.nambb.mall.common.contant.CommonResponseCode;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.dto.CategoryParam;
import vn.nambb.mall.dto.ImageUploadDTO;
import vn.nambb.mall.repository.CategoryRepository;
import vn.nambb.mall.entity.CategoryEntity;
import vn.nambb.mall.service.CategoryService;
import vn.nambb.mall.service.ImageService;
import vn.nambb.mall.util.Snowflake;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Snowflake snowflake;
    private final ImageService imageService;

    @Override
    public CategoryEntity getById(Long id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
        if (categoryEntityOptional.isEmpty()) {
            throw new CRuntimeException(CommonResponseCode.CATEGORY_NOT_FOUND);
        }
        return categoryEntityOptional.get();
    }

    boolean checkExist(String name) {
        return categoryRepository.existsByCategoryName(name);
    }

    @Override
    public int create(CategoryParam categoryParam, MultipartFile file) {
        int count;
        if (checkExist(categoryParam.getName())) {
            return 1;
        }

        if (file.isEmpty()) {
            throw new CRuntimeException(CommonResponseCode.IMAGE_NOT_FOUND);
        }
        ImageUploadDTO imageUploadDTO = imageService.create(file);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(snowflake.nextId());
        categoryEntity.setCategoryName(categoryParam.getName());
        categoryEntity.setCategoryImage(imageUploadDTO.getUrl());
        categoryRepository.save(categoryEntity);
        count = 1;
        return count;
    }

    @Override
    public int update(CategoryParam categoryParam) {
        int count;
        CategoryEntity categoryEntity = getById(categoryParam.getId());
        if (!Objects.equals(categoryEntity.getCategoryName(), categoryParam.getName())) {
            categoryEntity.setCategoryName(categoryParam.getName());
        }

//        if (!Objects.equals(categoryEntity.getCategoryImage(), categoryParam.getImage())) {
//            categoryEntity.setCategoryImage(categoryParam.getImage());
//        }

        count = 1;
        return count;
    }

    @Override
    public int delete(Long id) {
        int count;
        categoryRepository.updateCategoryEntityById(id);
        count = 1;
        return count;
    }
}
