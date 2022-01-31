package Lesson4.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExampleApplication extends Application {

    public static void main(String[] args) {
        launch(args);// от него не запускается только через public class Launcher {

    }

    @Override
    public void start(Stage primayStage) throws Exception {
        //для того что бы загружать FXML и показывать как окно
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/example.fxml"));
        //загрузить
        Parent parent = loader.load();
        //создаем сцену- окно
        Scene scene = new Scene(parent);
        primayStage.setScene(scene);
        primayStage.show();
    }
}
