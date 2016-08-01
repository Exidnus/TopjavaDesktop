package ru.dvvar.topjava.desktop.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.domain.Constructor;
import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Dmitriy_Varygin on 04.06.2016.
 */
@Component
//@Primary
public class ModelInMemory implements Model {

    private final Map<Integer, UserMealWithExceed> meals = new HashMap<>();
    private int lastId = 100_007;

    @PostConstruct
    private void init() {
        meals.put(100_007, new UserMealWithExceed(100_007, "Supper", LocalDateTime.of(2015, 5, 31, 20, 0), 510, true));
        meals.put(100_006, new UserMealWithExceed(100_006, "Dinner", LocalDateTime.of(2015, 5, 31, 13, 0), 1000, true));
        meals.put(100_005, new UserMealWithExceed(100_005, "Breakfast", LocalDateTime.of(2015, 5, 31, 10, 0), 500, true));
        meals.put(100_004, new UserMealWithExceed(100_004, "Supper", LocalDateTime.of(2015, 1, 30, 19, 0), 400, false));
    }


    @Override
    public List<UserMealWithExceed> getAll() {
        List<UserMealWithExceed> listMeals = new ArrayList<>(meals.values());
        Collections.sort(listMeals, ((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())));
        return listMeals;
    }

    @Override
    public UserMeal getOne(int id) {
        return Constructor.toUserMeal(meals.get(id));
    }

    @Override
    public boolean delete(int id) {
        return meals.remove(id) == null;
    }

    @Override
    public boolean update(UserMeal meal) {
        if (!meals.containsKey(meal.getId())) return false;
        UserMealWithExceed withExceed = meals.get(meal.getId());
        withExceed.setDescription(meal.getDescription());
        withExceed.setDateTime(meal.getDateTime());
        withExceed.setCalories(meal.getCalories());
        return true;
    }

    @Override
    public boolean create(UserMeal meal) {
        UserMealWithExceed created = new UserMealWithExceed(++lastId, meal.getDescription(), meal.getDateTime(),
                meal.getCalories(), false);
        meals.put(created.getId(), created);
        return true;
    }
}
