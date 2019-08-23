package UI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import parser.MyParser;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Timer;


public class Controller
{
    @FXML
    public Button btn_file,btn_run,btn_runFast,btn_stop;
    @FXML
    private ListView<String> lv_destination,lv_source;
    private MyParser parser = new MyParser();
    private Timeline timeline;
    @FXML
    public void btnFileClick()
    {
        lv_destination.getItems().clear();
        String filePath = showDialog();
        LinkedList<String> content = parser.setSource(filePath);

        ObservableList<String> contentList = FXCollections.observableArrayList();
        contentList.addAll(content);
        lv_source.setItems(contentList);
    }
    @FXML
    public void btnRunClick()
    {
        if(lv_source.getSelectionModel().isEmpty())
            lv_source.getSelectionModel().selectFirst();
        else
            lv_source.getSelectionModel().selectNext();
        String result = parser.getBinary();
        if(!result.equals(""))
            lv_destination.getItems().add(result);
    }
    @FXML
    public void btnRFClick()
    {
        if(btn_stop.isDisable())
            btn_stop.setDisable(false);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
        {
            if(parser.hasMoreCommands())
                btnRunClick();
        }));
        timeline.setCycleCount(parser.contentSize());
        timeline.play();
    }
    @FXML
    public void btnStopClick()
    {
        btn_stop.setDisable(true);
        timeline.pause();
    }
    private String showDialog()
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Asm File");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Asm Files","*.asm")
        );
        Stage stage = new Stage();
        File file = fc.showOpenDialog(stage);
        return file.getPath();
    }


}
