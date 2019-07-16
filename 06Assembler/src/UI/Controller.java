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

import java.io.File;


public class Controller
{
    @FXML
    public Button btn_file,btn_run,btn_runFast,btn_stop;
    @FXML
    private ListView<String> lv_destination,lv_source;
    @FXML
    private void initialize()
    {
        ObservableList<String> test = FXCollections.observableArrayList("A","B","C");
        lv_source.setItems(test);
    }
    @FXML
    public void btnFileClick(MouseEvent mouseEvent)
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Asm File");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Asm Files","*.asm")
        );
        Stage stage = new Stage();
        File file = fc.showOpenDialog(stage);
    }
    @FXML
    public void btnRunClick(MouseEvent mouseEvent)
    {
    }
    @FXML
    public void btnRFClick(MouseEvent mouseEvent)
    {
    }
    @FXML
    public void btnStopClick(MouseEvent mouseEvent)
    {
    }
}
