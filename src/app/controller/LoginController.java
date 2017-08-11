package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import app.model.LoginModel;
import database.DBConnector;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {
	static String zmienna;

	@FXML
    private Label lblLoginAndPass;

    @FXML
	private TextField txtLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Button btnSignIn;
    DBConnector db;
  	public ObservableList<LoginModel>data=FXCollections.observableArrayList();
    @FXML
    
    
    void signIn(MouseEvent event) throws IOException, ClassNotFoundException, SQLException{
    		zmienna=txtLogin.getText();
        	Connection conn= db.connection();
        	data= FXCollections.observableArrayList();
        	java.sql.PreparedStatement preparedStatement=null;
        	String sql="select * from logins where login=? and pass=?";
        	preparedStatement=conn.prepareStatement(sql);
        	preparedStatement.setString(1,txtLogin.getText() );
        	preparedStatement.setString(2, txtPass.getText());
        	ResultSet rs2=preparedStatement.executeQuery();
    		
        	if (rs2.next()) {
                do {
                    // successfully in. do the right things.
                	String query= "select access from logins where login= '"+txtLogin.getText()+ "'";
        			ResultSet rs1= conn.createStatement().executeQuery(query);
        			while(rs1.next()){
        			if(rs1.getString(1).equals("employee")){
        				
        				System.out.println("poprawne");
        				Stage stageTable = new Stage();
        				Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/EmployeeView.fxml"));
        				Scene sceneTable= new Scene(root);
        				stageTable.setScene(sceneTable);
        				stageTable.setTitle("Table page");
        				stageTable.show();
        				((Stage)btnSignIn.getScene().getWindow()).close();
        				
        				
        			}else{
            				System.out.println("poprawne");
            				Stage stageTable = new Stage();
            				Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminChoiceView.fxml"));
            				Scene sceneTable= new Scene(root);
            				stageTable.setScene(sceneTable);
            				stageTable.setTitle("Table page");
            				stageTable.show();
            				((Stage)btnSignIn.getScene().getWindow()).close();
        			}

                }} while (rs2.next());
            } else {
               // no results back. warn the user.
            	System.out.println("empty");
            	lblLoginAndPass.setText("Incorrect, try again!");
            	lblLoginAndPass.setTextFill(Color.web("#FF0000"));
        		txtLogin.setText("");
        		txtPass.setText("");
            }
    		
    		}
    		

	public void initialize(){
        	
        	db= new DBConnector();
        }
    
    
    
    }
    
    
  
    

