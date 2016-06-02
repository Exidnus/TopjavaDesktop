package ru.dvvar.topjava.desktop.model;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.nio.charset.Charset;
import java.util.List;

import static net.jadler.Jadler.*;
import static ru.dvvar.topjava.desktop.UserMealTestData.MEALS_WITH_EXCEEDS;
import static ru.dvvar.topjava.desktop.UserMealTestData.MEAL_1;
import static ru.dvvar.topjava.desktop.UserMealTestData.MEAL_ID_1;

/**
 * Created by Dmitriy_Varygin on 02.06.2016.
 */
@ContextConfiguration(classes = Config.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTest extends TestCase {

    private static final String PATH = "/topjava/rest/profile/meals";

    @Autowired
    private Model model;

    @Before
    public void setUp() {
        initJadlerListeningOn(8080);
    }

    @After
    public void tearDown() {
        closeJadler();
    }

    //TODO resolve problem with bad encoding
    @Test
    public void shouldGetAll() throws Exception {
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo(PATH)
                .respond()
                .withStatus(200)
                .withBody("[{\"id\":100007,\"dateTime\":\"2015-05-31T20:00:00\",\"description\":\"Supper\",\"calories\":510,\"exceed\":true}," +
                        "{\"id\":100006,\"dateTime\":\"2015-05-31T13:00:00\",\"description\":\"Dinner\",\"calories\":1000,\"exceed\":true}," +
                        "{\"id\":100005,\"dateTime\":\"2015-05-31T10:00:00\",\"description\":\"Breakfast\",\"calories\":500,\"exceed\":true}," +
                        "{\"id\":100004,\"dateTime\":\"2015-05-30T20:00:00\",\"description\":\"Supper\",\"calories\":500,\"exceed\":false}," +
                        "{\"id\":100003,\"dateTime\":\"2015-05-30T13:00:00\",\"description\":\"Dinner\",\"calories\":1000,\"exceed\":false}," +
                        "{\"id\":100002,\"dateTime\":\"2015-05-30T10:00:00\",\"description\":\"Breakfast\",\"calories\":500,\"exceed\":false}]")
                .withEncoding(Charset.forName("UTF-8"));

        final List<UserMealWithExceed> result = model.getAll();
        assertEquals(result, MEALS_WITH_EXCEEDS);
    }

    @Test
    public void shouldGetOne() throws Exception {
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo(PATH + "/" + MEAL_ID_1)
                .respond()
                .withStatus(200)
                .withBody("{\"id\":100007,\"dateTime\":\"2015-05-31T20:00:00\",\"description\":\"Supper\",\"calories\":510}");

        final UserMeal result = model.getOne(MEAL_ID_1);
        assertEquals(result, MEAL_1);
    }

    @Test
    public void shouldDelete() throws Exception {
        onRequest()
                .havingMethodEqualTo("DELETE")
                .havingPathEqualTo(PATH + "/" + MEAL_ID_1)
                .respond()
                .withStatus(200);

        assertTrue(model.delete(MEAL_ID_1));
    }

    @Test
    public void shouldUpdate() throws Exception {
        onRequest()
                .havingMethodEqualTo("PUT")
                .havingPathEqualTo(PATH + "/" + MEAL_ID_1)
                .respond()
                .withStatus(200);

        assertTrue(model.update(MEAL_1));
    }
}
