package vn.nambb.mall.common.exception;

import lombok.Getter;
import lombok.Setter;
import vn.nambb.mall.common.contant.CommonResponseCode;


@Getter
@Setter
public class CRuntimeException extends RuntimeException {
    private String errorCode;
    private Integer httpStatus;
    private Object data;

    public CRuntimeException() {
    }

    public CRuntimeException(String errorCode) {
        this.errorCode = errorCode;
    }

    public CRuntimeException(CommonResponseCode ghtkResponseCode) {
        this.errorCode = ghtkResponseCode.getCode();
    }

    public CRuntimeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CRuntimeException(String errorCode, String message, Integer httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CRuntimeException(String errorCode, String message, Object data) {
        super(message);
        this.errorCode = errorCode;
        this.data = data;
    }

}
