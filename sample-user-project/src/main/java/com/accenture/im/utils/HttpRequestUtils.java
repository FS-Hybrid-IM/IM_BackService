package com.accenture.im.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class HttpRequestUtils {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DEVICE_ID_HEADER = "DeviceId";

    public static String getDeviceId() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return getDeviceId(request);
    }

    public static String getDeviceId(HttpServletRequest request) {
        return request.getHeader(DEVICE_ID_HEADER);
    }

    public static String getAuthToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return getAuthToken(request);
    }

    public static String getAuthToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }
}
