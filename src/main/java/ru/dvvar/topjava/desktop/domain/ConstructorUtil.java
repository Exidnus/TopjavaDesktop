package ru.dvvar.topjava.desktop.domain;

/**
 * Created by Dmitriy_Varygin on 04.06.2016.
 */
public class ConstructorUtil {

    private ConstructorUtil() {

    }

    public static UserMeal toUserMeal(UserMealWithExceed withExceed) {
        return new UserMeal(withExceed.getId(), withExceed.getDescription(), withExceed.getDateTime(),
                withExceed.getCalories());
    }
}
