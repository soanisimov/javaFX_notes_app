package sample;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button b_sub;

    @FXML
    private TextField f_task;

    @FXML
    private TextField f_time;

    @FXML
    private DatePicker date;

    @FXML
    private CheckBox ch_done;

    @FXML
    private TextArea text_area;

    @FXML
    private Button b_clear;

    @FXML
    private Button btn_open;


    @FXML
    void initialize() {
        File file = new File("src/sample/notes.txt");
        Desktop desktop = Desktop.getDesktop();
        String format = "dd-MM-yyyy"; // формат даты
        date.setValue(LocalDate.now()); // сег.дата по дефолту


        b_sub.setOnAction(event -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String tdone; // для checkbox-a
            if (ch_done.isSelected()) {
                tdone = " Выполнено ✓ ";
            }
            else {
                tdone = " Не выполнено";
            }
            LocalDate date1 = date.getValue();

            try {
                FileWriter myWriter = new FileWriter("src/sample/notes.txt",true);
                myWriter.write( "Задание:" + f_task.getText()+" | " + " Выполнить до : " + f_time.getText()+" | "+" Дата ввода : "+ date1.format(DateTimeFormatter.ofPattern(format)) +" | "+ tdone + "\n");
                if(f_task.getLength() <= 0) {
                    text_area.setText("Введите данные!");
                    text_area.setStyle("-fx-text-fill: #DE1D1D; -fx-font-weight: bold;");
                }
                if(f_task.getLength() > 0){
                    myWriter.close();
                    text_area.setText("Текст был успешно записан." + "\n"+"Последняя запись: " +dtf.format(now));
                    text_area.setStyle("-fx-text-fill: #000000;");
                }

            } catch (IOException e) {
                System.out.println("Ошибка!");
                e.printStackTrace();
            }

        });

        b_clear.setOnAction(event -> {
            f_task.setText(null);
            f_time.setText(null);
        });

        btn_open.setOnAction(event -> {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
