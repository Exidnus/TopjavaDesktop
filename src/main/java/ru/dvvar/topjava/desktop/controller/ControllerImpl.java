package ru.dvvar.topjava.desktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;
import ru.dvvar.topjava.desktop.model.Model;
import ru.dvvar.topjava.desktop.view.View;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Component
public class ControllerImpl implements Controller {

    @Autowired
    private View view;

    @Autowired
    private Model model;

    @Override
    public List<UserMealWithExceed> getAll() {
        return model.getAll();
    }

    @Override
    public void delete(int id) {
        model.delete(id);
        view.refresh(model.getAll());
    }

    @Override
    public void update(UserMeal userMeal) {
        model.update(userMeal);
        view.refresh(model.getAll());
    }

    @Override
    public void create(UserMeal userMeal) {
        model.create(userMeal);
        view.refresh(model.getAll());
    }
}
