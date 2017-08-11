package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.AdminEmployeesModel;
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

public class AdminEmployeesController {

    @FXML
    private TableView<AdminEmployeesModel> tbEmployees;

    @FXML
    private TableColumn<AdminEmployeesModel, Integer> tbcID;

    @FXML
    private TableColumn<AdminEmployeesModel, String> tbcFirstname;

    @FXML
    private TableColumn<AdminEmployeesModel, String> tbcLastname;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private Label lblFirstname;

    @FXML
    private Label lblLastname;

    @FXML
    private Button btnInsert2;
    
    @FXML
    private Button btnUpdate2;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnDelete;
    @FXML
    void insert(MouseEvent event) {
    	lblFirstname.setDisable(false);
    	lblLastname.setDisable(false);
    	txtFirstname.setDisable(false);
    	txtLastname.setDisable(false);
    	btnInsert2.setDisable(false);
    }

    @FXML
    void insert2(MouseEvent event) throws ClassNotFoundException, SQLException {
    	if(txtFirstname.getText().equals("")){
			lblFirstname.setText("Fill name!");
			lblFirstname.setTextFill(Color.web("#FF0000"));
		}
    	else if(txtLastname.getText().equals("")){
			lblLastname.setText("Fill lastname!");
			lblLastname.setTextFill(Color.web("#FF0000"));
		}else{
    	
    		java.sql.PreparedStatement prepareStatement= null;
    		Connection conn= db.connection();
        	String sql = "insert into employees (firstname,lastname) values (?,?);";//tu skonczylam
        	prepareStatement=conn.prepareStatement(sql);
        	prepareStatement.setString(1, txtFirstname.getText());
        	prepareStatement.setString(2, txtLastname.getText());
        	prepareStatement.executeUpdate();
        	
    		
    		txtFirstname.setText("");
    		txtLastname.setText("");
    		lblFirstname.setDisable(true);
        	lblLastname.setDisable(true);
        	txtFirstname.setDisable(true);
    		txtLastname.setDisable(true);
    		btnInsert2.setDisable(false);
		}
    		
        	
    }
    
    public DBConnector db = new DBConnector();
    public ObservableList<AdminEmployeesModel>data=FXCollections.observableArrayList();
    @FXML
    void show(MouseEvent event) throws ClassNotFoundException {

    	try{
    		
    		Connection conn= db.connection();
    		data= FXCollections.observableArrayList();
    		ResultSet rs= conn.createStatement().executeQuery("select * from employees");
    		
    		while(rs.next()){
//    			System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
    			data.add(new AdminEmployeesModel(rs.getInt(1), rs.getString(2),rs.getString(3)));
    		}
    		
    	}catch(SQLException e){
    		System.out.println("Error"+ e);
    		
    	}
    	tbcID.setCellValueFactory(new PropertyValueFactory<AdminEmployeesModel,Integer>("id_employee"));
    	tbcFirstname.setCellValueFactory(new PropertyValueFactory<AdminEmployeesModel,String>("firstname"));
    	tbcLastname.setCellValueFactory(new PropertyValueFactory<AdminEmployeesModel,String>("lastname"));
    	
    	tbEmployees.setItems(null);
    	tbEmployees.setItems(data);
    	
    	lblFirstname.setDisable(true);
    	lblLastname.setDisable(true);
    	txtFirstname.setDisable(true);
    	txtLastname.setDisable(true);
    	btnInsert2.setDisable(true);
    	btnUpdate2.setDisable(true);
    }

    @FXML
    void update(MouseEvent event) {
    	btnInsert2.setDisable(true);
    	lblFirstname.setDisable(false);
    	lblLastname.setDisable(false);
    	txtFirstname.setDisable(false);
    	txtLastname.setDisable(false);
    	btnUpdate2.setDisable(false);
    	
    	txtFirstname.setText(tbEmployees.getSelectionModel().getSelectedItem().getFirstname());
    	txtLastname.setText(tbEmployees.getSelectionModel().getSelectedItem().getLastname());
    }
    
    
    @FXML
    void update2(MouseEvent event) throws ClassNotFoundException, SQLException {
    	java.sql.PreparedStatement prepareStatement= null;
    	int id_update=tbEmployees.getSelectionModel().getSelectedItem().getId_employee();
    	String sql = "update employees set firstname=?, lastname=? where id_employee=?;"; 
    	Connection conn= db.connection();
    	
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.setString(1, txtFirstname.getText());
    	prepareStatement.setString(2, txtLastname.getText());
    	prepareStatement.setInt(3, id_update);
    	prepareStatement.executeUpdate();
    	
    	txtFirstname.setText("");
		txtLastname.setText("");
		lblFirstname.setDisable(true);
    	lblLastname.setDisable(true);
    	txtFirstname.setDisable(true);
		txtLastname.setDisable(true);
		btnUpdate2.setDisable(false);
    }
    
    @FXML
    void back(MouseEvent event) throws IOException {
    	Stage stageTable = new Stage();
		Parent root= (Parent)FXMLLoader.load(getClass().getResource("/app/view/AdminChoiceView.fxml"));
		Scene sceneTable= new Scene(root);
		stageTable.setScene(sceneTable);
		stageTable.setTitle("Table page");
		stageTable.show();
		((Stage)btnBack.getScene().getWindow()).close();
    }

    @FXML
    void delete(MouseEvent event) throws ClassNotFoundException {
    	int id_del=tbEmployees.getSelectionModel().getSelectedItem().getId_employee();
    	try{
   		java.sql.PreparedStatement prepareStatement= null;
    	String sql = "delete from employees where id_employee="+id_del+";"; 
    	Connection conn= db.connection();
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.executeUpdate();
    	
    	
    	
    	}catch(SQLException e){
    	System.err.print("Error "+ e);
    	}
    }
}
