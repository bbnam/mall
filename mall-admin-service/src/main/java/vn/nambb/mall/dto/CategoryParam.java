package vn.nambb.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class CategoryParam {
    @ApiModelProperty("category id")
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    @ApiModelProperty("category name")
    private String name;
}
