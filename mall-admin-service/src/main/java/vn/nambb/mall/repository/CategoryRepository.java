package vn.nambb.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.nambb.mall.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByCategoryName(String categoryName);

    @Query("Update CategoryEntity c set c.status = 0 where c.id = ?1")
    void updateCategoryEntityById(Long id);
}
