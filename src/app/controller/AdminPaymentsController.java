package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;

import app.model.AdminPaymentsModel;
import app.model.AdminRatesModel;
import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminPaymentsController {

    @FXML
    private TableView<AdminPaymentsModel> tbPayments;

    @FXML
    private TableColumn<AdminPaymentsModel, Integer> tbcId_Payment;

    @FXML
    private TableColumn<AdminPaymentsModel, Integer> tbcId_Employee;

    @FXML
    private TableColumn<AdminPaymentsModel, Integer> tbcMonth;

    @FXML
    private TableColumn<AdminPaymentsModel, Double> btcTotal;

    @FXML
    private Button show;
    @FXML
    private Button btnBack;

    @FXML
    private Label lblTotalByEmpl;

    @FXML
    private Label lblResultsByEmpl;

    @FXML
    private Label lblTotalByMonth;

    @FXML
    private Label lblResultByMonth;

    @FXML
    private ComboBox<Integer> cbEmployee;

    @FXML
    private ComboBox cbMonth;
        
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
    
    static ObservableList<Integer> employee;
	
    public DBConnector db = new DBConnector();
    public void initialize() throws ClassNotFoundException, SQLException{
        Connection conn= db.connection();
    	String sql="select id_employee from employees";
    	ResultSet rs=conn.createStatement().executeQuery(sql);
    	
    	ArrayList <Integer> lista= new ArrayList<>();
    	while(rs.next()) {
		lista.add(rs.getInt(1));
		}
    	 employee=FXCollections.observableArrayList(lista);
    	cbEmployee.setItems(employee);
     	cbMonth.setItems(months);
    	
    }
    public DBConnector db2 = new DBConnector();
    @FXML
    void chooseEmpl(ActionEvent event) throws ClassNotFoundException, SQLException {
    	double sum=0;
        Connection conn= db2.connection();
        int id_del=Integer.parseInt(cbEmployee.getSelectionModel().getSelectedItem().toString());
    	java.sql.PreparedStatement prepareStatement= null;
    	String sql="select total_brutto from payments where id_employee=?";
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.setInt(1,id_del);
    	ResultSet rs=prepareStatement.executeQuery();
		
		while(rs.next()){
			sum+=rs.getDouble(1);	
		}
		System.out.println(sum);
		lblResultsByEmpl.setText(sum+"");
		
    }
    

    public DBConnector db3 = new DBConnector();
    public ObservableList<Integer>months=FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
    @FXML
    void chooseMonth(ActionEvent event) throws ClassNotFoundException, SQLException {
    	double sum=0;
        Connection conn= db2.connection();
        int id_choose=Integer.parseInt(cbMonth.getSelectionModel().getSelectedItem().toString());
    	java.sql.PreparedStatement prepareStatement= null;
    	String sql="select total_brutto from payments where month_name=?";
    	prepareStatement=conn.prepareStatement(sql);
    	prepareStatement.setInt(1,id_choose);
    	ResultSet rs=prepareStatement.executeQuery();
		
		while(rs.next()){
			sum+=rs.getDouble(1);	
		}
		System.out.println(sum);
		lblResultByMonth.setText(sum+"");
    }

    public DBConnector db1 = new DBConnector();
    public ObservableList<AdminPaymentsModel>data=FXCollections.observableArrayList();
    @FXML
    void show(MouseEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn= db1.connection();
		data= FXCollections.observableArrayList();
		ResultSet rs= conn.createStatement().executeQuery("select * from payments");
		while(rs.next()){
			data.add(new AdminPaymentsModel(rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getDouble(4)));
		}
		
	tbcId_Payment.setCellValueFactory(new PropertyValueFactory<AdminPaymentsModel,Integer>("id_payment"));	
	tbcId_Employee.setCellValueFactory(new PropertyValueFactory<AdminPaymentsModel,Integer>("id_employee"));
	tbcMonth.setCellValueFactory(new PropertyValueFactory<AdminPaymentsModel,Integer>("month_name"));
	btcTotal.setCellValueFactory(new PropertyValueFactory<AdminPaymentsModel,Double>("total_brutto"));
	tbPayments.setItems(null);
	tbPayments.setItems(data);
    }

}
