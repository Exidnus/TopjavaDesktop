package ru.dvvar.topjava.desktop.integration;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.model.Model;

import static ru.dvvar.topjava.desktop.UserMealTestData.MEAL_1;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public class IntegrationTests {

    @Test
    public void shouldJustGo() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Model model = context.getBean(Model.class);
        model.getAll().forEach(System.out::println);
        model.update(MEAL_1);
        System.out.println("+++++++++++++++++++++++++++");
        model.getAll().forEach(System.out::println);
    }
}
