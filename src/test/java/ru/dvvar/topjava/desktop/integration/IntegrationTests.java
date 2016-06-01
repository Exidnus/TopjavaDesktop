package ru.dvvar.topjava.desktop.integration;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.controller.Controller;
import ru.dvvar.topjava.desktop.model.Model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public class IntegrationTests {

    @Test
    public void shouldJustGo() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Model model = context.getBean(Model.class);
    }

    @Test
    public void shouldAllWorkAndPrintOneUserMealWithExceed() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Controller controller = context.getBean(Controller.class);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(arrayOutputStream));
        controller.getAll();
        String result = arrayOutputStream.toString();
        TestCase.assertTrue(result.contains("UserMealWithExceed") && result.contains("id")
                && result.contains("description") && result.contains("dateTime") && result.contains("exceed"));
    }
}
