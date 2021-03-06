package ru.dvvar.topjava.desktop.controller;

import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public interface Controller {

    List<UserMealWithExceed> getAll();

    void delete(int id);

    void update(UserMeal userMeal);

    void create(UserMeal userMeal);
}
