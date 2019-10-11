package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import translator.CodeWriter;
import translator.Parser;

import java.io.*;

public class Controller
{
    @FXML
    private Button btn_file;
    @FXML
    private Label lbl_note;
    private Parser parser;
    private CodeWriter codeWriter;
    @FXML
    private void btnFileClick()
    {

        File file = showDialog();
        if(file!=null)
        {
            parser = new Parser(file);
            boolean result = parser.createAsmFile();
            if(result)
                lbl_note.setText("vm文件成功翻译成asm文件");
            else
                lbl_note.setText("asm文件已存在");
        }

    }
    private File showDialog()
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Vm File");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Asm Files","*.vm")
        );
        Stage stage = new Stage();
        File file = fc.showOpenDialog(stage);
        return file;
    }
}
