package vn.nambb.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {
    @ApiModelProperty("product name")
    private String name;

    @ApiModelProperty("product status = 1 -> available, = 0 -> out of stock")
    private String status;

    @ApiModelProperty("product price")
    private String price;

    @ApiModelProperty("product image list")
    private List<String> imageList;
}
