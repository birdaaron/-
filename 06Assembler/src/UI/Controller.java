package UI;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.MyParser;

import java.io.*;
import java.util.Stack;


public class Controller
{
    @FXML
    public Button btn_file,btn_run,btn_runFast,btn_stop;
    @FXML
    private ListView<String> lv_destination,lv_source;
    private MyParser parser;
    @FXML
    private void initialize()
    {
        parser = new MyParser();
    }
    @FXML
    public void btnFileClick()
    {
        String filePath = showDialog();
        Stack<String> content = parser.setSource(filePath);
        ObservableList<String> contentList = FXCollections.observableArrayList();
        contentList.addAll(content);
        lv_source.setItems(contentList);
    }
    @FXML
    public void btnRunClick()
    {
        lv_source.getSelectionModel().selectNext();
    }
    @FXML
    public void btnRFClick()
    {
    }
    @FXML
    public void btnStopClick()
    {

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
