package ru.dvvar.topjava.desktop.view;

import javafx.application.Application;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.controller.Controller;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 04.06.2016.
 */
@Component
//@Primary
public class ViewJavaFX extends Application implements View {

    @Autowired
    private Controller controller;

    @Override
    public void refresh(List<UserMealWithExceed> data) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void run() {
        launch();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        controller = context.getBean(Controller.class);

        primaryStage.setTitle("Meal's manager");
        Button button = new Button("Say 'Hello' to your meal's manager!");
        Label label = new Label();
        button.setOnAction(event -> label.setText("Hello!"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        TableView<UserMealWithExceed> table = new TableView<>();
        TableColumn<UserMealWithExceed, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(param -> {
            try {
                return new JavaBeanObjectPropertyBuilder<UserMealWithExceed>()
                        .bean(param.getValue())
                        .name("Description")
                        .build();
            } catch (NoSuchMethodException e) {
                return null;
            }
        });
        TableColumn<UserMealWithExceed, String> dateTime = new TableColumn<>("DateTime");
        dateTime.setCellValueFactory(param -> {
            try {
                return new JavaBeanObjectPropertyBuilder<UserMealWithExceed>()
                        .bean(param.getValue())
                        .name("DateTime")
                        .build();
            } catch (NoSuchMethodException e) {
                return null;
            }
        });
        TableColumn<UserMealWithExceed, String> calories = new TableColumn<>("Calories");
        calories.setCellValueFactory(param -> {
            try {
                return new JavaBeanObjectPropertyBuilder<UserMealWithExceed>()
                        .bean(param.getValue())
                        .name("Calories")
                        .build();
            } catch (NoSuchMethodException e) {
                return null;
            }
        });
        table.getColumns().addAll(description, dateTime, calories);

        table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println(observable);
            System.out.println(oldValue);
            System.out.println(newValue);
        }));

        ObservableList<UserMealWithExceed> data = FXCollections.observableArrayList(controller.getAll());
        table.setItems(data);

        grid.add(table, 0, 0);
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
