package ru.dvvar.topjava.desktop.model;

import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Component
public class ModelMock implements Model {

    @Override
    public List<UserMealWithExceed> getAll() {
        return Collections.singletonList(new UserMealWithExceed(null, "Some meal", LocalDateTime.now(), 500, false));
    }

    @Override
    public UserMeal getOne(int id) {
        return new UserMeal(id, "Supper", LocalDateTime.of(2015, 5, 31, 20, 0), 510);
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public boolean update(UserMeal meal) {
        return true;
    }
}
