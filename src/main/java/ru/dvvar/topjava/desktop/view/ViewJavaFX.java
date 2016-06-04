package ru.dvvar.topjava.desktop.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.dvvar.topjava.desktop.domain.UserMealWithExceed;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 04.06.2016.
 */
@Component
@Primary
public class ViewJavaFX extends Application implements View {

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Meal's manager");
        Button button = new Button("Say 'Hello' to your meal's manager!");
        Label label = new Label();
        button.setOnAction(event -> label.setText("Hello!"));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);



        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
