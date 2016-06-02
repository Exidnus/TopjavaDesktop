package ru.dvvar.topjava.desktop.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
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
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Component
@Primary
public class ModelHttp implements Model {

    private static final String URL = "http://localhost:8080/topjava/rest/profile/meals";
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
        final ResponseEntity<String> resultGetHttpRequest = template.exchange(URL, HttpMethod.GET,
                new HttpEntity<>(createHeadersHttpBasic(USERNAME, PASSWORD)), String.class);
        List<UserMealWithExceed> allMeals = new ArrayList<>();
        try {
            final JsonParser parser = factory.createParser(resultGetHttpRequest.getBody());
            parser.nextToken();
            while (parser.nextToken() == JsonToken.START_OBJECT) {
                allMeals.add(mapper.readValue(parser, UserMealWithExceed.class));
            }
        } catch (IOException e) {
            System.err.println("IOException in ModelHttp.getAll(): " + e);
        }
        return allMeals;
    }

    @Override
    public UserMeal getOne(int id) {
        final ResponseEntity<String> resultGetHttpRequest = template.exchange(URL + "/" + id, HttpMethod.GET,
                new HttpEntity<>(createHeadersHttpBasic(USERNAME, PASSWORD)), String.class);
        UserMeal meal = null;
        try {
            final JsonParser parser = factory.createParser(resultGetHttpRequest.getBody());
            meal = mapper.readValue(parser, UserMeal.class);
        } catch (IOException e) {
            System.err.println("IOException in ModelHttp.getOne(): " + e);
        }

        return meal;
    }

    @Override
    public boolean delete(int id) {
        final ResponseEntity<String> resultDeleteHttpRequest = template.exchange(URL + "/" + id, HttpMethod.DELETE,
                new HttpEntity<>(createHeadersHttpBasic(USERNAME, PASSWORD)), String.class);
        if (resultDeleteHttpRequest.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            return false;
        }
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
