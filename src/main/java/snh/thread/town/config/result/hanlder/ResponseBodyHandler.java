package snh.temp.springjsp.common.aop;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import snh.temp.springjsp.common.result.ApiResponse;
import snh.temp.springjsp.common.result.ErrorData;

/**
 * 응답 본문을 수정하기 위한 사용자 정의 ResponseBodyAdvice 구현
 */
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    private static final Class<MappingJackson2HttpMessageConverter> SUPPORTED_CONVERTER = MappingJackson2HttpMessageConverter.class;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return SUPPORTED_CONVERTER.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        String path = request.getURI().getPath();
        if (isErrorData(body)) {
            ErrorData errorData = (ErrorData) body;
            response.setStatusCode(errorData.getHttpStatus());
            return ApiResponse.error(path, errorData.getCode(), errorData.getMessage());
        }

        return ApiResponse.success(path, body);
    }


    private boolean isErrorData(Object body) {
        return body instanceof ErrorData;
    }
}
