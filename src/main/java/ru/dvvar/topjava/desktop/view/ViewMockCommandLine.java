package ru.dvvar.topjava.desktop.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.controller.Controller;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Component
public class ViewMockCommandLine implements View {

    @Autowired
    private Controller controller;

    @Override
    public void run() {
        refresh();
    }

    @Override
    public void refresh() {
        controller.getAll();
    }

    @Override
    public void refresh(List<UserMealWithExceed> data) {
        data.forEach(System.out::println);
    }
}
