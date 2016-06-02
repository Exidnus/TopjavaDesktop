package ru.dvvar.topjava.desktop.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.controller.Controller;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 02.06.2016.
 */
@Component
@Primary
public class ViewSwing extends JFrame implements View {

    @Autowired
    private Controller controller;

    private JTable table;

    @Override
    public void run() {
        initTable();
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initTable() {
        String[] columnNames = {"Id", "Description", "Date and time", "Calories", "Exceed"};
        final List<UserMealWithExceed> meals = controller.getAll();
        Object[][] data = new Object[meals.size()][5];
        for (int i = 0; i <meals.size(); i++) {
            UserMealWithExceed meal = meals.get(i);
            data[i][0] = meal.getId();
            data[i][1] = meal.getDescription();
            data[i][2] = meal.getDateTime();
            data[i][3] = meal.getCalories();
            data[i][4] = meal.isExceed();
        }
        table = new JTable(data, columnNames);
        JScrollPane scrollPaneTable = new JScrollPane(table);
        getContentPane().add(scrollPaneTable, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        controller.getAll();
    }

    @Override
    public void refresh(List<UserMealWithExceed> data) {

    }
}
