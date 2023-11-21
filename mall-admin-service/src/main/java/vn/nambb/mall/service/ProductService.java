package vn.nambb.mall.service;

import org.springframework.transaction.annotation.Transactional;
import vn.nambb.mall.dto.ProductParam;

public interface ProductService {
    @Transactional
    int create(ProductParam productParam);

    @Transactional
    int update(ProductParam productParam);

    int delete(Long id);


}
