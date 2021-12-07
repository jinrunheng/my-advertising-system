package com.github.ad.advice;

import com.github.ad.annotation.IgnoreResponseAdvice;
import com.github.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * RestControllerAdvice 注解通常用于对响应进行拦截，实现统一的返回处理
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice {
    /**
     * 响应是否应该拦截
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        // 如果一个类或方法被 @IgnoreResponseAdvice 注解标识则不被拦截处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class) ||
                methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    /**
     * 对响应进行拦截，在写入响应之前，做一些操作
     * 将 Body 封装成 CommonResponse
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        CommonResponse<Object> response = new CommonResponse<>(0, "");
        if (o == null) {
            return response;
        } else if (o instanceof CommonResponse) {
            return o;
        } else {
            response.setData(o);
            return response;
        }
    }
}
