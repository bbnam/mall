package vn.nambb.mall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
public class UserEntity extends BaseEntity {
    private String userName;
    private String password;
    private String email;
    private String phone;
}
