package vn.nambb.mall.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.nambb.mall.common.contant.CommonResponseCode;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse<T> {
    private String errorCode;
    private String message;
    private Long total;
    private Long limit;
    private Long page;
    private Map<String, Object> meta;
    private T data;

    public static <V> ResponseEntity<CommonResponse<List<V>>> of(Page<V> page) {
        CommonResponse<List<V>> response = new CommonResponse<>();
        response.data = page.getContent();
        response.total = page.getTotalElements();
        response.limit = (long) page.getPageable().getPageSize();
        response.page = (long) page.getPageable().getPageNumber() + 1; //Page spring boot start with index 0
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> of(CommonResponseCode responseCode) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(responseCode.getMessage());
        response.setErrorCode(responseCode.getCode());
        return ResponseEntity.ok(response);
    }

    public static <V> ResponseEntity<CommonResponse<V>> build(V model) {
        CommonResponse<V> response = new CommonResponse<>();
        response.data = model;
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> success(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(CommonResponseCode.SUCCESS.getCode());
        response.setErrorCode(CommonResponseCode.SUCCESS.getMessage());
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> fail() {
        CommonResponse<T> response = new CommonResponse<>();
        response.setMessage(CommonResponseCode.FAILED.getCode());
        response.setErrorCode(CommonResponseCode.FAILED.getMessage());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(CommonResponseCode.FAILED.getMessage()));
    }
}
