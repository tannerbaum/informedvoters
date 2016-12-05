package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class officialController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void authBtnClicked(ActionEvent event) throws IOException {
        String idField = "Pothering"; // TODO: (Tanner) Get user input
        Official official = new Official();
        if(official.authenticate(idField) == true){
            System.out.println("auth confirmed");

            Parent parent = FXMLLoader.load(getClass().getResource("pollTools.fxml"));
            Scene candidateScene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(candidateScene);
            //appStage.setFullScreen(true);
            appStage.show();
        }
        else{
            //Return to start?
        }
    }
    // NOTE: ESC key combination no match, string empty for popup message. string adapting. free CSS from Oracle. Last slide with tips and tricks
    // recount prints out data to console
}