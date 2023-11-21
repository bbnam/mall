package vn.nambb.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.nambb.mall.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("Update ProductEntity p set p.status = 0 where p.id = ?1")
    void updateProductEntityById(Long id);
}
