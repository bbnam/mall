package vn.nambb.mall.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.nambb.mall.common.contant.CommonResponseCode;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.dto.ProductParam;
import vn.nambb.mall.entity.ProductEntity;
import vn.nambb.mall.repository.ProductRepository;
import vn.nambb.mall.service.CategoryService;
import vn.nambb.mall.service.ProductService;
import vn.nambb.mall.util.Snowflake;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final Snowflake snowflake;

    @Override
    public int create(ProductParam productParam) {
        int count;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(snowflake.nextId());
        productEntity.setName(productParam.getName());
        productEntity.setCategoryEntity(categoryService.getById(productParam.getCategoryId()));
        productRepository.save(productEntity);
        count = 1;
        return count;
    }

    @Override
    public int update(ProductParam productParam) {
        int count;
        ProductEntity productEntity = getById(productParam.getId());

        if (!Objects.equals(productParam.getName(), productEntity.getName())) {
            productEntity.setName(productParam.getName());
        }

        if (!Objects.equals(productParam.getCategoryId(), productEntity.getCategoryEntity().getId())) {
            productEntity.setCategoryEntity(categoryService.getById(productParam.getCategoryId()));
        }
        count = 1;
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = 0;
        productRepository.updateProductEntityById(id);
        return count;
    }

    ProductEntity getById(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()) {
            throw new CRuntimeException(CommonResponseCode.PRODUCT_NOT_FOUND);
        }
        return productEntityOptional.get();
    }


}
