package app.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminChoiceController {
    
    @FXML
    private ComboBox<String> cbChoice;

    @FXML
    private Button btnChoose;

    @FXML
    private Label lblChoose;
    
    @FXML
    private Button btnLogOut;
    
    ObservableList<String> tables= FXCollections.observableArrayList("employees","logins","rates","payments");
    
    public void initialize(){
    	cbChoice.setItems(tables);
    }
    
    
    @FXML
    void choose(MouseEvent event) throws IOException {
    	if(cbChoice.getSelectionModel().getSelectedItem().toString().equals("employees")){
    		System.out.println("wybrano tab employees");
    		Stage stageTable = new Stage();
			Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminEmployeesView.fxml"));
			Scene sceneTable= new Scene(root);
			stageTable.setScene(sceneTable);
			stageTable.setTitle("Employess page");
			stageTable.show();
			((Stage)btnChoose.getScene().getWindow()).close();
    	}else if(cbChoice.getSelectionModel().getSelectedItem().toString().equals("logins")){
    		System.out.println("wybrano tab logins");
    		Stage stageTable = new Stage();
			Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminLoginsView.fxml"));
			Scene sceneTable= new Scene(root);
			stageTable.setScene(sceneTable);
			stageTable.setTitle("Logins page");
			stageTable.show();
			((Stage)btnChoose.getScene().getWindow()).close();
    	}else if(cbChoice.getSelectionModel().getSelectedItem().toString().equals("rates")){
    		System.out.println("wybrano tab rates");
    		Stage stageTable = new Stage();
			Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminRatesView.fxml"));
			Scene sceneTable= new Scene(root);
			stageTable.setScene(sceneTable);
			stageTable.setTitle("Rates page");
			stageTable.show();
			((Stage)btnChoose.getScene().getWindow()).close();
    	}else if(cbChoice.getSelectionModel().getSelectedItem().toString().equals("payments")){
    		System.out.println("wybrano payments");
    		Stage stageTable = new Stage();
			Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminPaymentsView.fxml"));
			Scene sceneTable= new Scene(root);
			stageTable.setScene(sceneTable);
			stageTable.setTitle("Payments page");
			stageTable.show();
			((Stage)btnChoose.getScene().getWindow()).close();
    	}
    	
    }
    @FXML
    void logOut(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.show();
        ((Stage)btnChoose.getScene().getWindow()).close();
    }

}