package ru.dvvar.topjava.desktop.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Component
@Primary
public class ModelHttp implements Model {

    private static final String URL = "http://localhost:808/topjava/rest/profile/meals";
    private static final String USERNAME = "user@yandex.ru";
    private static final String PASSWORD = "password";

    @Autowired
    private RestTemplate template;

    @Autowired
    private JsonFactory factory;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<UserMealWithExceed> getAll() {
        final ResponseEntity<UserMealWithExceed[]> resultGetHttpRequest = template.exchange(URL, HttpMethod.GET,
                new HttpEntity<>(createHeadersHttpBasic(USERNAME, PASSWORD)), UserMealWithExceed[].class);

        return Arrays.asList(resultGetHttpRequest.getBody());
    }

    @Override
    public UserMeal getOne(int id) {
        final ResponseEntity<UserMeal> resultGetHttpRequest = template.exchange(URL + "/" + id, HttpMethod.GET,
                new HttpEntity<>(createHeadersHttpBasic(USERNAME, PASSWORD)), UserMeal.class);
        return resultGetHttpRequest.getBody();
    }

    @Override
    public boolean delete(int id) {
        final ResponseEntity<String> resultDeleteHttpRequest = template.exchange(URL + "/" + id, HttpMethod.DELETE,
                new HttpEntity<>(createHeadersHttpBasic(USERNAME, PASSWORD)), String.class);
        return resultDeleteHttpRequest.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public boolean update(UserMeal meal) {
        if (meal.getId() == null) throw new IllegalArgumentException("Meal's id must not be null");
        HttpHeaders headers = createHeadersHttpBasic(USERNAME, PASSWORD);
        headers.set("Content-Type", "application/json;charset=UTF-8");
        final ResponseEntity<String> resultPutHttpRequest = template.exchange(URL + "/" + meal.getId(), HttpMethod.PUT,
                new HttpEntity<>(meal, headers), String.class);
        return resultPutHttpRequest.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public boolean create(UserMeal meal) {
        HttpHeaders headers = createHeadersHttpBasic(USERNAME, PASSWORD);
        headers.set("Content-Type", "application/json;charset=UTF-8");
        final ResponseEntity<String> resultPostHttpRequest = template.exchange(URL, HttpMethod.POST,
                new HttpEntity<>(meal, headers), String.class);
        return resultPostHttpRequest.getStatusCode() == HttpStatus.CREATED;
    }

    private HttpHeaders createHeadersHttpBasic(String username, String password) {
        final String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        final String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        return headers;
    }
}
