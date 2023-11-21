package vn.nambb.mall.common.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.nambb.mall.common.contant.CommonResponseCode;
import vn.nambb.mall.common.exception.CRuntimeException;
import vn.nambb.mall.common.response.CommonResponse;


import java.util.Objects;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CRuntimeException.class)
    public ResponseEntity<?> handleGRuntimeException(CRuntimeException ex) {

        return Objects.isNull(ex.getData()) ? createResponse(ex.getErrorCode(), ex.getMessage(), ex.getHttpStatus()) : createResponse(ex.getErrorCode(), ex.getMessage(), ex.getHttpStatus(), ex.getData());
    }

    private ResponseEntity<?> createResponse(String statusCode, String message, Integer httpStatus) {
        return createResponse(statusCode, message, httpStatus, null);
    }

    private <T> ResponseEntity<?> createResponse(String statusCode, String message, Integer httpStatus, T data) {
        CommonResponse<T> responseObject = new CommonResponse<>();
        responseObject.setErrorCode(statusCode);
        responseObject.setMessage(message);

        if (CommonResponseCode.get(statusCode) == null) {
            return new ResponseEntity<>(responseObject, HttpStatus.valueOf(httpStatus));
        }

        CommonResponseCode ghtkResponseCode = CommonResponseCode.get(statusCode);
        String description = ghtkResponseCode.getMessage();
        httpStatus = ghtkResponseCode.getHttpCode();
        if (StringUtils.isNotBlank(message)) {
            responseObject.setMessage(message);
        } else {
            responseObject.setMessage(description);
        }

        if (data != null) {
            responseObject.setData(data);
        }

        return new ResponseEntity<>(responseObject, HttpStatus.valueOf(httpStatus));
    }


}
