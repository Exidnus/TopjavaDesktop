package ru.dvvar.topjava.desktop.view;

import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Component
public class ViewMockCommandLine implements View {

    private static final String username = "user";
    private static final String password = "password";

    @Override
    public void refresh(List<UserMealWithExceed> data) {
        data.forEach(System.out::println);
    }
}
