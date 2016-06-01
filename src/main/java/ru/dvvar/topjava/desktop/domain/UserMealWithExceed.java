package ru.dvvar.topjava.desktop.domain;

import java.time.LocalDateTime;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public class UserMealWithExceed {

    private LocalDateTime dateTime;

    private String description;

    private int calories;



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
