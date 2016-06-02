package ru.dvvar.topjava.desktop.model;

import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public interface Model {

    List<UserMealWithExceed> getAll();

    UserMeal getOne(int id);

    boolean delete(int id);

    boolean update(UserMeal meal);

    boolean create(UserMeal meal);
}
