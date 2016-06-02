package ru.dvvar.topjava.desktop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.view.View;

/**
 * Created by Dmitriy_Varygin on 02.06.2016.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        View view = context.getBean(View.class);
        view.run();
    }
}
