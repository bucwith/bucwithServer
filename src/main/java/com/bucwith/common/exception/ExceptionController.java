package com.bucwith.common.exception;


import com.bucwith.common.CommController;
import com.bucwith.common.code.ApiCode;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;


@Slf4j
@RestControllerAdvice
public class ExceptionController extends CommController {

    // 알 수 없는 에러 체크
    @ExceptionHandler
    public ResponseEntity exception(Exception e) {
        e.printStackTrace();
        return ErrorReturn(ApiCode.UNKNOWN_ERROR);
    }

    // Repository 조회 실패시 에러 체크
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity illegalArgumentException(Exception e) {
        e.printStackTrace();
        return ErrorReturn(ApiCode.UNKNOWN_ERROR.getCode(),e.getMessage());
    }

    // @RequestParam Param Error Check
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ErrorReturn(ApiCode.PARAM_ERROR);
    }

    // @RequestParam File Error Check
    @ExceptionHandler({MissingServletRequestPartException.class})
    public ResponseEntity missingServletRequestPartException(MissingServletRequestPartException e) {
        return ErrorReturn(ApiCode.PARAM_ERROR);
    }

    // @RequestBody DTO Param Error Check
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorReturn(ApiCode.PARAM_ERROR);
    }

    // @PathVariable ERROR - 1
    @ExceptionHandler({MissingPathVariableException.class})
    public ResponseEntity missingPathVariableException(MissingPathVariableException e) {
        return ErrorReturn(ApiCode.PARAM_ERROR);
    }

    // @PathVariable ERROR - 2
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ErrorReturn(ApiCode.PARAM_ERROR);
    }

}
