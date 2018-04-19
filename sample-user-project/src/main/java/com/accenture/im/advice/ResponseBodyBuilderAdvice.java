package com.accenture.im.advice;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.accenture.im.databind.ErrorResponse;
import com.accenture.im.databind.ResponseBodyWrapper;

@ControllerAdvice
@Order(0)
public class ResponseBodyBuilderAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBodyBuilderAdvice.class);
    private static final String SERVER_DATE_HEADER = "ServerDate";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType == MappingJackson2HttpMessageConverter.class;
    }

    @Override
    public ResponseBodyWrapper beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        LOGGER.info("Wrapping object returned from controller: " + returnType.getMethod());
        ResponseBodyWrapper wrappedResponse;
        if (body instanceof ResponseBodyWrapper) {
            wrappedResponse = (ResponseBodyWrapper) body;
        } else {
            wrappedResponse = createSuccessResponse(body);
            addServerDate(response);
        }

        return wrappedResponse;
    }

    private ResponseBodyWrapper createSuccessResponse(Object response) {
        return new ResponseBodyWrapper("SUCCESS", response, new ErrorResponse());
    }

    private void addServerDate(ServerHttpResponse response) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        response.getHeaders().set(SERVER_DATE_HEADER, currentDateTime.format(formatter));
    }
}
