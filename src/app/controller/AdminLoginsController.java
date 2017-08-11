package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.AdminEmployeesModel;
import app.model.AdminLoginsModel;
import app.model.EmployeeModel;
import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminLoginsController {

    @FXML
    private TableView<AdminLoginsModel> tbLogins;

    @FXML
    private TableColumn<AdminLoginsModel, Integer> tbcId;
    
    @FXML
    private TableColumn<AdminLoginsModel, Integer> tbcId_employee;
    @FXML
    private TableColumn<AdminLoginsModel, String> tbcLogin;

    @FXML
    private TableColumn<AdminLoginsModel, String> tbcPass;

    @FXML
    private TableColumn<AdminLoginsModel, String> tbcAccess;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblPass;

    @FXML
    private Label lblAccess;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtAccess;

    @FXML
    private Button btnInsert2;

    @FXML
    private Button btnUpdate2;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;
    
    @FXML
    private Label lblIdEmployee;

    @FXML
    private TextField txtIdEmployee;
    @FXML
    private Button btnBack;

    @FXML
    void back(MouseEvent event) throws IOException {
    	Stage stageTable = new Stage();
		Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminChoiceView.fxml"));
		Scene sceneTable= new Scene(root);
		stageTable.setScene(sceneTable);
		stageTable.setTitle("AdminChoiceView page");
		stageTable.show();
		((Stage)btnBack.getScene().getWindow()).close();
    }

    @FXML
    void delete(MouseEvent event) throws ClassNotFoundException {
    	int id_del=tbLogins.getSelectionModel().getSelectedItem().getId_login();
    	try{
   		java.sql.PreparedStatement prepareStatement= null;
    	String sql = "delete from logins where id_login="+id_del+";"; 
    	Connection conn= db.connection();
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.executeUpdate();
    	
    	}catch(SQLException e){
    	System.err.print("Error "+ e);
    	}
    }

    @FXML
    void insert(MouseEvent event) {
    	lblIdEmployee.setDisable(false);;
        txtIdEmployee.setDisable(false);;
    	lblLogin.setDisable(false);
    	lblPass.setDisable(false);
    	lblAccess.setDisable(false);
    	txtLogin.setDisable(false);
    	txtPass.setDisable(false);
    	txtAccess.setDisable(false);
    	btnInsert2.setDisable(false);
    	
    	txtIdEmployee.setText("");
    	txtLogin.setText("");
    	txtPass.setText("");
    	txtAccess.setText("");
    	
    }
    
    @FXML
   
    	void insert2(MouseEvent event) throws ClassNotFoundException, SQLException {
    	if(txtIdEmployee.getText().equals("")){
			lblIdEmployee.setText("Missing login!");
			lblIdEmployee.setTextFill(Color.web("FF0000"));
		}
    	else if(txtLogin.getText().equals("")){
			lblLogin.setText("Missing login!");
			lblLogin.setTextFill(Color.web("FF0000"));
		}
    	else if(txtPass.getText().equals("")){
			lblPass.setText("Missing password!");
			lblPass.setTextFill(Color.web("FF0000"));
		}else if (txtAccess.getText().equals("")){
			lblAccess.setText("Missing access!");
			lblAccess.setTextFill(Color.web("FF0000"));
		}else{
    	
    		java.sql.PreparedStatement prepareStatement= null;
    		Connection conn= db.connection();
        	String sql = "insert into logins (id_employee,login, pass, access) values (?,?,?,?);"; 
        	prepareStatement=conn.prepareStatement(sql);
        	prepareStatement.setString(1, txtIdEmployee.getText());
        	prepareStatement.setString(2, txtLogin.getText());
        	prepareStatement.setString(3, txtPass.getText());
        	prepareStatement.setString(4, txtAccess.getText());
        	prepareStatement.executeUpdate();
    		txtIdEmployee.setText("");
    		txtLogin.setText("");
    		txtPass.setText("");
    		txtAccess.setText("");
    		lblIdEmployee.setDisable(true);
            txtIdEmployee.setDisable(true);
    		lblLogin.setDisable(true);
        	lblPass.setDisable(true);
        	lblAccess.setDisable(true);
        	txtLogin.setDisable(true);
        	txtPass.setDisable(true);
        	txtAccess.setDisable(true);
        	btnInsert2.setDisable(true);
        	lblIdEmployee.setTextFill(Color.web("000000"));
        	lblLogin.setTextFill(Color.web("000000"));
        	lblPass.setTextFill(Color.web("000000"));
			lblAccess.setTextFill(Color.web("000000"));
		}
    }
    
    public DBConnector db = new DBConnector();
    public ObservableList<AdminLoginsModel>data=FXCollections.observableArrayList();
    @FXML
    void show(MouseEvent event) throws ClassNotFoundException {
try{
    		
    		Connection conn= db.connection();
    		data= FXCollections.observableArrayList();
    		ResultSet rs= conn.createStatement().executeQuery("select * from logins");
    		
    		while(rs.next()){
//    			System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
    			data.add(new AdminLoginsModel(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5)));
    		}
    		
    	}catch(SQLException e){
    		System.out.println("Error"+ e);
    		
    	}
		tbcId.setCellValueFactory(new PropertyValueFactory<AdminLoginsModel,Integer>("id_login"));
		tbcId_employee.setCellValueFactory(new PropertyValueFactory<AdminLoginsModel,Integer>("id_employee"));	
    	tbcLogin.setCellValueFactory(new PropertyValueFactory<AdminLoginsModel,String>("login"));
    	tbcPass.setCellValueFactory(new PropertyValueFactory<AdminLoginsModel,String>("pass"));
    	tbcAccess.setCellValueFactory(new PropertyValueFactory<AdminLoginsModel,String>("access"));
    	tbLogins.setItems(null);
    	tbLogins.setItems(data);
    	
    	lblIdEmployee.setDisable(true);
        txtIdEmployee.setDisable(true);
        txtIdEmployee.setText("");
    	lblLogin.setDisable(true);
    	lblPass.setDisable(true);
    	lblAccess.setDisable(true);
    	txtLogin.setDisable(true);
    	txtPass.setText("");
    	txtPass.setDisable(true);
    	txtPass.setText("");
    	txtAccess.setDisable(true);
    	txtAccess.setText("");
    	btnInsert2.setDisable(true);
    	btnUpdate2.setDisable(true);
    }

    @FXML
    void update(MouseEvent event) {
    	 
    	//lblIdEmployee.setDisable(false);;
        //txtIdEmployee.setDisable(false);;
    	lblLogin.setDisable(false);
    	lblPass.setDisable(false);
    	lblAccess.setDisable(false);
    	txtLogin.setDisable(false);
    	txtPass.setDisable(false);
    	txtAccess.setDisable(false);
    	btnUpdate2.setDisable(false);
    	
    	//txtIdEmployee.setText("");
    	txtLogin.setText("");
    	txtPass.setText("");
    	txtAccess.setText("");
    	
    	//txtIdEmployee.setText(tbLogins.getSelectionModel().getSelectedItem().getId_employee()+"");
    	txtLogin.setText(tbLogins.getSelectionModel().getSelectedItem().getLogin());
    	txtPass.setText(tbLogins.getSelectionModel().getSelectedItem().getPass());
    	txtAccess.setText(tbLogins.getSelectionModel().getSelectedItem().getAccess());
    }
    
    
    @FXML
    void update2(MouseEvent event) throws ClassNotFoundException, SQLException {
    	java.sql.PreparedStatement prepareStatement= null;
    	int id_update=tbLogins.getSelectionModel().getSelectedItem().getId_login();
    	String sql = "update logins set login=?, pass=?, access=? where id_login=?;"; 
    	Connection conn= db.connection();
    	
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.setString(1, txtLogin.getText());
    	prepareStatement.setString(2, txtPass.getText());
    	prepareStatement.setString(3, txtAccess.getText());
    	prepareStatement.setInt(4, id_update);
    	prepareStatement.executeUpdate();
    	
    	txtLogin.setText("");
    	txtPass.setText("");
    	txtAccess.setText("");
    	lblLogin.setDisable(true);
    	lblPass.setDisable(true);
    	lblAccess.setDisable(true);
    	txtLogin.setDisable(true);
    	txtPass.setDisable(true);
    	txtAccess.setDisable(true);
    	btnUpdate2.setDisable(true);
    }
    

}
