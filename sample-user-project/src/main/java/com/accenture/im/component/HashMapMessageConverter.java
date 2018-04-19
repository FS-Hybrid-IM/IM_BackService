package com.accenture.im.component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class HashMapMessageConverter extends AbstractHttpMessageConverter<Map<String, String>> {

    public HashMapMessageConverter() {
        super(MediaType.APPLICATION_FORM_URLENCODED);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Arrays.asList(clazz.getInterfaces()).contains(Map.class);
    }

    @Override
    protected Map<String, String> readInternal(Class<? extends Map<String, String>> clazz,
            HttpInputMessage inputMessage) throws IOException {
        throw new UnsupportedOperationException("Method Not Supported");
    }

    @Override
    protected void writeInternal(Map<String, String> map, HttpOutputMessage outputMessage)
            throws IOException {
        outputMessage.getHeaders().setAcceptCharset(new ArrayList<>(Charset.availableCharsets().values()));
        List<String> paramList = new ArrayList<>();
        map.forEach((key, value) -> {
            try {
                paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
        });

        String joinedParam = String.join("&", paramList);

        StreamUtils.copy(joinedParam, StandardCharsets.UTF_8, outputMessage.getBody());
    }
}
