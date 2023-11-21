package vn.nambb.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ImageUploadDTO {
    @ApiModelProperty("image response url")
    private String url;

    @ApiModelProperty("image file name")
    private String name;
}
