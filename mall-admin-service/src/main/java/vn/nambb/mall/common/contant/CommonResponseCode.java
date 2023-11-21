package vn.nambb.mall.common.contant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CommonResponseCode {
    SUCCESS("success", 200, "Thanh cong"),
    CATEGORY_NOT_FOUND("category_not_found", 400, "Category not found"),
    PRODUCT_NOT_FOUND("product_not_found", 400, "Product not found"),
    FAILED("failed", 500, "Internal server error"),
    MINIO_CONNECTION_ERROR("minio_connection_error", 500, "Minio connection error"),
    MINIO_UPLOAD_FILE_ERROR("minio_upload_file_error", 500, "Minio upload file error"),
    IMAGE_NOT_FOUND("image_not_found", 400, "Image not found"),
    ;

    private static final Map<String, CommonResponseCode> lookup = new HashMap<>();

    static {
        for (CommonResponseCode d : CommonResponseCode.values()) {
            lookup.put(d.code, d);
        }
    }

    private final String code;
    private final int httpCode;
    private final String message;

    public static CommonResponseCode get(String code) {
        return lookup.get(code);
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "code='" + code + '\'' +
                "httpCode='" + httpCode + '\'' +
                '}';
    }
}