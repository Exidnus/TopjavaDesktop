package ru.dvvar.topjava.desktop.view;

import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public interface View {

    void refresh(List<UserMealWithExceed> data);

    void refresh();

    void run();
}
