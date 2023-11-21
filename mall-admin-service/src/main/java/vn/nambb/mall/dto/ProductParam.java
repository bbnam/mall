package vn.nambb.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
public class ProductParam {
    @ApiModelProperty("product id")
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    @ApiModelProperty("product name")
    private String name;

    @ApiModelProperty("product description")
    private String description;

    @ApiModelProperty("category id that product belongs to")
    @NotEmpty(message = "categoryId cannot be empty")
    private Long categoryId;

}
