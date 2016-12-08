package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class pollController implements Initializable {

    @FXML
    Label feedbackMsg;

    @FXML
    Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startBtnClicked(ActionEvent event) throws IOException {

        if(!(Context.getInstance().currentTally().getCandidates().size() >=1)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Missing Candidates");
            alert.setContentText("You must add at least one candidate!");
            alert.show();
        }else{
            Context.getInstance().currentOfficial().startPoll();
            feedbackMsg.setText("Poll Started!");
        }

    }

    public void endBtnClicked(ActionEvent event) throws IOException {
        Context.getInstance().currentOfficial().endPoll();
        feedbackMsg.setText("Poll Ended!");
        //TODO Aaron Transfer local to total
        //TODO Aaron create tally string
    }

    //TODO: (Aaron) if you want (I don't think we need it) print out the total number of people who voted or whatever
    public void totalBtnClicked(ActionEvent event) throws IOException{

    }

    public void tallyBtnClicked(ActionEvent event) throws IOException {
        Context.getInstance().currentOfficial().tallyResults();
        //TODO: (Aaron) can we use your query for all the local votes and print that in the console here?
    }

    public void exitBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void addBtnClicked(ActionEvent event) throws IOException {
        Candidate insert = new Candidate();

        //Name
        TextInputDialog dialog = new TextInputDialog("Name");
        dialog.setTitle("Candidate Name");
        dialog.setHeaderText("New Candidate Part 1");
        dialog.setContentText("Candidate Name: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setName(result.get());
        }

        //Office
        dialog = new TextInputDialog("Office");
        dialog.setTitle("Candidate Office");
        dialog.setHeaderText("New Candidate Part 2");
        dialog.setContentText("What they are running for: ");

        result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setOffice(result.get());
        }

        //Party
        dialog = new TextInputDialog("Party");
        dialog.setTitle("Candidate Party");
        dialog.setHeaderText("New Candidate Part 3");
        dialog.setContentText("Party: ");

        result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setParty(result.get());
        }

        //Bio
        dialog = new TextInputDialog("Bio");
        dialog.setTitle("Candidate Bio");
        dialog.setHeaderText("New Candidate Part 4");
        dialog.setContentText("Candidate Bio: ");

        result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setBio(result.get());
        }

        Context.getInstance().currentTally().addCandidate(insert);

    }
}
