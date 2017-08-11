package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.model.AdminLoginsModel;
import app.model.AdminRatesModel;
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

public class AdminRatesController {

    @FXML
    private TableView<AdminRatesModel> tbRates;

    @FXML
    private TableColumn<AdminRatesModel, Integer> tbcId;

    @FXML
    private TableColumn<AdminRatesModel, Integer> tbcId_employee;

    @FXML
    private TableColumn<AdminRatesModel, Double> tbcRateX;

    @FXML
    private TableColumn<AdminRatesModel, Double> tbcRateY;

    @FXML
    private TableColumn<AdminRatesModel, Double> tbcRateZ;

    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblRateX;

    @FXML
    private Label lblRateY;

    @FXML
    private Label lblRateZ;

    @FXML
    private TextField txtIdEmployee;

    @FXML
    private TextField txtRateX;

    @FXML
    private TextField txtRateY;

    @FXML
    private TextField txtRateZ;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnInsert2;

    @FXML
    private Button btnUpdate2;

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
    	int id_del=tbRates.getSelectionModel().getSelectedItem().getId_rate();
    	try{
   		java.sql.PreparedStatement prepareStatement= null;
    	String sql = "delete from rates where id_rate="+id_del+";"; 
    	Connection conn= db.connection();
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.executeUpdate();
    	
    	}catch(SQLException e){
    	System.err.print("Error "+ e);
    	}
    }

    @FXML
    void insert(MouseEvent event) {
    	lblEmployee.setDisable(false);
        txtIdEmployee.setDisable(false);
    	lblRateX.setDisable(false);
    	lblRateY.setDisable(false);
    	lblRateZ.setDisable(false);
    	txtRateX.setDisable(false);
    	txtRateY.setDisable(false);
    	txtRateZ.setDisable(false);
    	btnInsert2.setDisable(false);
    	btnUpdate2.setDisable(true);
    }

    @FXML
    	void insert2(MouseEvent event) throws ClassNotFoundException, SQLException {
        	
        	if(txtIdEmployee.getText().equals("")){
    			lblEmployee.setText("Missing id_employee!");
    			lblEmployee.setTextFill(Color.web("FF0000"));
    		}
        	else if(txtRateX.getText().equals("")){
    			lblRateX.setText("Missing rate X!");
    			lblRateX.setTextFill(Color.web("FF0000"));
    		}else if (txtRateY.getText().equals("")){
    			lblRateY.setText("Missing rate Y!");
    			lblRateY.setTextFill(Color.web("FF0000"));
    		}else if (txtRateZ.getText().equals("")){
    			lblRateZ.setText("Missing rate Z!");
    			lblRateZ.setTextFill(Color.web("FF0000"));
    		}else{
        	
        		java.sql.PreparedStatement prepareStatement= null;
        		Connection conn= db.connection();
            	String sql = "insert into rates (id_employee,rateX, rateY, rateZ) values (?,?,?,?);"; 
            	prepareStatement=conn.prepareStatement(sql);
            	prepareStatement.setInt(1, Integer.parseInt(txtIdEmployee.getText()));
            	prepareStatement.setDouble(2, Double.parseDouble(txtRateX.getText()));
            	prepareStatement.setDouble(3, Double.parseDouble(txtRateY.getText()));
            	prepareStatement.setDouble(4, Double.parseDouble(txtRateZ.getText()));
            	prepareStatement.executeUpdate();
            	
            	lblEmployee.setDisable(true);
                txtIdEmployee.setDisable(true);
                txtIdEmployee.setText("");
            	lblRateX.setDisable(true);
            	lblRateY.setDisable(true);
            	lblRateZ.setDisable(true);
            	txtRateX.setDisable(true);
            	txtRateX.setText("");
            	txtRateY.setDisable(true);
            	txtRateY.setText("");
            	txtRateZ.setDisable(true);
            	txtRateZ.setText("");
            	btnInsert2.setDisable(true);
            	btnUpdate2.setDisable(true);
            	lblEmployee.setTextFill(Color.web("000000"));
            	lblRateX.setTextFill(Color.web("000000"));
            	lblRateY.setTextFill(Color.web("000000"));
            	lblRateZ.setTextFill(Color.web("000000"));
            	
    		}
        		
    }

    public DBConnector db = new DBConnector();
    public ObservableList<AdminRatesModel>data=FXCollections.observableArrayList();
    @FXML
    void show(MouseEvent event) throws ClassNotFoundException, SQLException {
    		Connection conn= db.connection();
    		data= FXCollections.observableArrayList();
    		ResultSet rs= conn.createStatement().executeQuery("select * from rates");
    		while(rs.next()){
//    			System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
    			data.add(new AdminRatesModel(rs.getInt(1),rs.getInt(2), rs.getDouble(3),rs.getDouble(4),rs.getDouble(5)));
    		}
    		
    	
		tbcId.setCellValueFactory(new PropertyValueFactory<AdminRatesModel,Integer>("id_rate"));	
    	tbcId_employee.setCellValueFactory(new PropertyValueFactory<AdminRatesModel,Integer>("id_employee"));
    	tbcRateX.setCellValueFactory(new PropertyValueFactory<AdminRatesModel,Double>("rateX"));
    	tbcRateY.setCellValueFactory(new PropertyValueFactory<AdminRatesModel,Double>("rateY"));
    	tbcRateZ.setCellValueFactory(new PropertyValueFactory<AdminRatesModel,Double>("rateZ"));
    	tbRates.setItems(null);
    	tbRates.setItems(data);
    	
    	lblEmployee.setDisable(true);
        txtIdEmployee.setDisable(true);
    	lblRateX.setDisable(true);
    	lblRateY.setDisable(true);
    	lblRateZ.setDisable(true);
    	txtRateX.setDisable(true);
    	txtRateY.setDisable(true);
    	txtRateZ.setDisable(true);
    	btnInsert2.setDisable(true);
    	btnUpdate2.setDisable(true);
    }

    @FXML
    void update(MouseEvent event) {

    	lblEmployee.setDisable(true);
        txtIdEmployee.setDisable(true);
    	lblRateX.setDisable(false);
    	lblRateY.setDisable(false);
    	lblRateZ.setDisable(false);
    	txtRateX.setDisable(false);
    	txtRateY.setDisable(false);
    	txtRateZ.setDisable(false);
    	btnInsert2.setDisable(true);
    	btnUpdate2.setDisable(false);
    	
    	txtIdEmployee.setText("");
    	txtRateX.setText("");
    	txtRateY.setText("");
    	txtRateZ.setText("");
    	
    	txtIdEmployee.setText(tbRates.getSelectionModel().getSelectedItem().getId_employee()+"");
    	txtRateX.setText(tbRates.getSelectionModel().getSelectedItem().getRateX()+"");
    	txtRateY.setText(tbRates.getSelectionModel().getSelectedItem().getRateY()+"");
    	txtRateZ.setText(tbRates.getSelectionModel().getSelectedItem().getRateZ()+"");
    }

    @FXML
    void update2(MouseEvent event) throws ClassNotFoundException, SQLException {
    	java.sql.PreparedStatement prepareStatement= null;
    	int id_update=tbRates.getSelectionModel().getSelectedItem().getId_rate();
    	String sql = "update rates set id_employee=?, rateX=?, rateY=?, rateZ=? where id_rate=?;"; 
    	Connection conn= db.connection();
    	
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.setString(1, txtIdEmployee.getText());
    	prepareStatement.setDouble(2, Double.parseDouble(txtRateX.getText()));
    	prepareStatement.setDouble(3, Double.parseDouble(txtRateY.getText()));
    	prepareStatement.setDouble(4, Double.parseDouble(txtRateZ.getText()));
    	prepareStatement.setInt(5, id_update);
    	prepareStatement.executeUpdate();
    	
    	
    	lblEmployee.setDisable(true);
        txtIdEmployee.setDisable(true);
    	lblRateX.setDisable(true);
    	lblRateY.setDisable(true);
    	lblRateZ.setDisable(true);
    	txtRateX.setDisable(true);
    	txtRateY.setDisable(true);
    	txtRateZ.setDisable(true);
    	btnInsert2.setDisable(true);
    	btnUpdate2.setDisable(true);
    }

}
