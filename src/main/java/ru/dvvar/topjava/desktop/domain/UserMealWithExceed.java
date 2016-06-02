package ru.dvvar.topjava.desktop.domain;

import java.time.LocalDateTime;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
public class UserMealWithExceed {

    private Integer id;

    private String description;

    private LocalDateTime dateTime;

    private int calories;

    private boolean exceed;

    public UserMealWithExceed() {

    }

    public UserMealWithExceed(Integer id, String description, LocalDateTime dateTime, int calories, boolean exceed) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
        this.calories = calories;
        this.exceed = exceed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public boolean isExceed() {
        return exceed;
    }

    public void setExceed(boolean exceed) {
        this.exceed = exceed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMealWithExceed that = (UserMealWithExceed) o;

        if (getCalories() != that.getCalories()) return false;
        if (isExceed() != that.isExceed()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        return getDateTime().equals(that.getDateTime());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getDateTime().hashCode();
        result = 31 * result + getCalories();
        result = 31 * result + (isExceed() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
