package vn.nambb.mall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product_detail_order")
public class ProductDetailOrderEntity extends BaseEntity {
    private Long price;
    private String quantity;
    @OneToOne
    @JoinColumn(name = "product_detail_id")
    private ProductEntity productEntity;
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

}
