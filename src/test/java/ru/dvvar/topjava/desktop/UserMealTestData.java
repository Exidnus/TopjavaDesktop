package ru.dvvar.topjava.desktop;

import ru.dvvar.topjava.desktop.domain.UserMeal;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 02.06.2016.
 */
public class UserMealTestData {

    private UserMealTestData() {

    }

    public static final int MEAL_ID_1 = 100_007;

    public static final UserMeal MEAL_1 = new UserMeal(MEAL_ID_1, "Supper", LocalDateTime.of(2015, 5, 31, 20, 0), 510);

    public static final List<UserMealWithExceed> MEALS_WITH_EXCEEDS = Arrays.asList(
            new UserMealWithExceed(100_007, "Supper", LocalDateTime.of(2015, 5, 31, 20, 0), 510, true),
            new UserMealWithExceed(100_006, "Dinner", LocalDateTime.of(2015, 5, 31, 13, 0), 1000, true),
            new UserMealWithExceed(100_005, "Breakfast", LocalDateTime.of(2015, 5, 31, 10, 0), 500, true),
            new UserMealWithExceed(100_004, "Supper", LocalDateTime.of(2015, 5, 30, 20, 0), 500, false),
            new UserMealWithExceed(100_003, "Dinner", LocalDateTime.of(2015, 5, 30, 13, 0), 1000, false),
            new UserMealWithExceed(100_002, "Breakfast", LocalDateTime.of(2015, 5, 30, 10, 0), 500, false)
    );
}
