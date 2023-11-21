package vn.nambb.mall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
    private String categoryName;
    private String categoryImage;
}
