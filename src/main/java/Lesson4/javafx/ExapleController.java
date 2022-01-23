package Lesson4.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ExapleController {

    @FXML//для полей которые описанны в схеме
    public Button btnclick;

    @FXML
    public Label labelText;

    //что должно происходить при нажатии кнопки прописывается здесь
    public void click(ActionEvent actionEvent) {
        System.out.println("Нажмите!");
        //поменять текст в labelText
        labelText.setText("JavaFX Hello!!");
        //поменять текст
        btnclick.setText("Pressed!!");
        //увеличение в 5 раз
        btnclick.setScaleX(5.0);
        btnclick.setScaleY(5.0);
    }
}
