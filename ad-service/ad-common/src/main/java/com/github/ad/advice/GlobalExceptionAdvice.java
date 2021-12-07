package com.github.ad.advice;

import com.github.ad.exception.AdException;
import com.github.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException adException) {
        CommonResponse<String> response = new CommonResponse<>(-1, "error");
        response.setData(adException.getMessage());
        return response;
    }
}
