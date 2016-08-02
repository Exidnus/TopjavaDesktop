package ru.dvvar.topjava.desktop.view;

import javafx.application.Application;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.config.Config;
import ru.dvvar.topjava.desktop.controller.Controller;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 04.06.2016.
 */
@Component
@Primary
public class ViewJavaFX extends Application implements View {

    @Autowired
    private Controller controller;

    private TableView<UserMealWithExceed> meals = new TableView<>();
    private ObservableList<UserMealWithExceed> data;
    //private List<UserMealWithExceed> oldMeals;

    private Button button = new Button("Say 'Hello'!");
    private Label label = new Label();

    private GridPane editor = new GridPane();
    private int editingMealId;
    private TextField calories = new TextField();
    private Label nameCalories = new Label("Calories");
    private TextField description = new TextField();
    private Label nameDescription = new Label("Description");
    private TextField dateTime = new TextField();
    private Label nameDateTime = new Label("Date and Time");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");

    @Override
    public void refresh(List<UserMealWithExceed> newMeals) {
        meals.getItems().clear();
        meals.refresh();
        meals.getItems().addAll(newMeals);
        meals.refresh();
        refresh();
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

        //button.setOnAction(event -> label.setText("Hello!"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        initEditor();
        initTable();

        grid.add(meals, 0, 0);
        grid.add(editor, 1, 0);
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initEditor() {
        editor.add(nameCalories, 0 , 0);
        editor.add(calories, 1, 0);
        editor.add(nameDescription, 0, 1);
        editor.add(description, 1, 1);
        editor.add(nameDateTime, 0, 2);
        editor.add(dateTime, 1, 2);

        editor.add(save, 0 , 3);
        editor.add(cancel, 1, 3);
        delete.setStyle("-fx-text-fill: red");
        editor.add(delete, 0, 4);

        delete.setOnAction(event -> controller.delete(editingMealId));
    }

    private void initTable() {
        initColumns();
        initColoursForRows();

        meals.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            final UserMealWithExceed meal = observable.getValue();
            editingMealId = meal.getId();
            calories.setText(String.valueOf(meal.getCalories()));
            description.setText(meal.getDescription());
            dateTime.setText(String.valueOf(meal.getDateTime()));
        }));

        data = FXCollections.observableArrayList(controller.getAll());
        meals.setItems(data);
    }

    private void initColumns() {
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
        meals.getColumns().addAll(description, dateTime, calories);
    }

    private void initColoursForRows() {
        meals.setRowFactory(new Callback<TableView<UserMealWithExceed>, TableRow<UserMealWithExceed>>() {
            @Override
            public TableRow<UserMealWithExceed> call(TableView<UserMealWithExceed> param) {
                return new TableRow<UserMealWithExceed>() {
                    @Override
                    protected void updateItem(UserMealWithExceed item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            if (item.isExceed()) {
                                setStyle("-fx-background-color: lightcoral");
                            } else {
                                setStyle("-fx-background-color: lightgreen");
                            }
                        }
                    }
                };
            }
        });
    }
}
