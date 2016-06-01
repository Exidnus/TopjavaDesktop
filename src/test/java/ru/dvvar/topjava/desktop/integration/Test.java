package ru.dvvar.topjava.desktop.integration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.controller.Controller;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Controller controller = context.getBean(Controller.class);
        controller.getAll();
    }
}
