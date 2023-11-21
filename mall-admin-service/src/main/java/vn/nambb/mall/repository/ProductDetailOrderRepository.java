package vn.nambb.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.nambb.mall.entity.ProductDetailOrderEntity;

@Repository
public interface ProductDetailOrderRepository extends JpaRepository<ProductDetailOrderEntity, Long> {
}
