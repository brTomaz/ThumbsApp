package br.ufop.brTomaz;

import br.ufop.brTomaz.controller.Screen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScreen(Screen screen) throws IOException {
        Parent parent = FXMLLoader.load(MainApplication.class.getResource(screen.getFXMLPath()));
        Scene oldScene = stage.getScene();
        //double width = oldScene != null ? oldScene.getWidth() : 638.0;
        //double height = oldScene != null ? oldScene.getHeight() : 400.0;
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setResizable(false);
        stage = primaryStage;
        setScreen(Screen.LOGIN);
    }

}
