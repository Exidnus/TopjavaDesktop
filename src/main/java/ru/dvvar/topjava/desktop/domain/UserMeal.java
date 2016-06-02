package ru.dvvar.topjava.desktop.domain;

import java.time.LocalDateTime;

/**
 * Created by Dmitriy_Varygin on 02.06.2016.
 */
public class UserMeal {

    private Integer id;

    private String description;

    private LocalDateTime dateTime;

    private int calories;

    public UserMeal() {

    }

    public UserMeal(Integer id, String description, LocalDateTime dateTime, int calories) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMeal userMeal = (UserMeal) o;

        if (getCalories() != userMeal.getCalories()) return false;
        if (getId() != null ? !getId().equals(userMeal.getId()) : userMeal.getId() != null) return false;
        if (!getDescription().equals(userMeal.getDescription())) return false;
        return getDateTime().equals(userMeal.getDateTime());

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getDateTime().hashCode();
        result = 31 * result + getCalories();
        return result;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", calories=" + calories +
                '}';
    }
}
