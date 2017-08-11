package app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.table.TableModel;
import app.model.EmployeeModel;
import app.model.LoginModel;
import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmployeeController {

	@FXML
	private TableView<EmployeeModel> tbPayments;

	@FXML
	private TableColumn<EmployeeModel, Integer> tbcMonth;

	@FXML
	private TableColumn<EmployeeModel, Double> tbcTotal;

	@FXML
	private Button btnShow;

	@FXML
	private Button btnInsert;

	@FXML
	private Label lblX;

	@FXML
	private Label lblY;

	@FXML
	private Label lblDeclaration;

	@FXML
	private Label lblZ;

	@FXML
	private TextField txtX;

	@FXML
	private TextField txtY;

	@FXML
	private TextField txtZ;

	@FXML
	private Button btnInsert2;

	@FXML
	private Label lblTotalBrutto;

	@FXML
	private Label lblTotalAmountBrutto;

	@FXML
	private Label lblSumBrutto;

	@FXML
	private Label lblResultBrutto;
	@FXML
	private Label lblMonth;

	@FXML
	private Button btnTotal;

	@FXML
	private TextField txtSum;
	@FXML
	private ComboBox cbMonths;

	@FXML
	private Label lblTotalNetto;

	@FXML
	private Label lblTotalAmountNetto;

	@FXML
	private Label lblSumNetto;

	@FXML
	private Label lblResultNetto;
	@FXML
	private Button btnLogOut;

	ObservableList<String> months = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12");

	@FXML
	void insert(MouseEvent event) {
		lblDeclaration.setDisable(false);
		lblMonth.setDisable(false);
		lblX.setDisable(false);
		lblY.setDisable(false);
		lblZ.setDisable(false);
		lblSumBrutto.setDisable(false);
		lblResultBrutto.setDisable(false);
		lblSumNetto.setDisable(false);
		lblResultNetto.setDisable(false);
		txtX.setDisable(false);
		txtY.setDisable(false);
		txtZ.setDisable(false);
		cbMonths.setDisable(false);
		btnInsert2.setDisable(false);
		lblResultBrutto.setText("0");
		lblResultNetto.setText("0");
		txtX.setText("");
		txtY.setText("");
		txtZ.setText("");
		cbMonths.setAccessibleText("1");
		lblTotalBrutto.setDisable(true);
		lblTotalAmountBrutto.setDisable(true);
		lblTotalAmountBrutto.setText("0");
		lblTotalNetto.setDisable(true);
		lblTotalAmountNetto.setDisable(true);
		lblTotalAmountNetto.setText("0");

	}

	public ObservableList<EmployeeModel> data1 = FXCollections.observableArrayList();

	@FXML
	void insert2(MouseEvent event) throws ClassNotFoundException, SQLException {
		double x;
		double y;
		double z;
		double sum = 0;
		Connection conn = db.connection();
		data1 = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement()
				.executeQuery("select rateX, rateY, rateZ from rates natural join logins where logins.login='"
						+ LoginController.zmienna + "'");

		while (rs.next()) {
			x = rs.getDouble(1) * Integer.parseInt(txtX.getText());
			y = rs.getDouble(2) * Integer.parseInt(txtY.getText());
			z = rs.getDouble(3) * Integer.parseInt(txtZ.getText());
			sum = x + y + z;
		}
		lblResultBrutto.setText(sum + "");
		lblResultNetto.setText((0.77 * sum) + "");
		System.out.println(sum);

		java.sql.PreparedStatement prepareStatement = null;
		String sqlId = "select id_employee from logins where  logins.login='" + LoginController.zmienna + "'";
		ResultSet rs4 = conn.createStatement().executeQuery(sqlId);
		String sql = "insert into payments (id_employee, month_name, total_brutto) values (?,?,?);";
		prepareStatement = conn.prepareStatement(sql);
		rs4.next();
		prepareStatement.setInt(1, rs4.getInt(1));
		prepareStatement.setInt(2, Integer.parseInt(cbMonths.getSelectionModel().getSelectedItem().toString()));
		prepareStatement.setDouble(3, sum);
		prepareStatement.executeUpdate();

		lblDeclaration.setDisable(true);
		lblMonth.setDisable(true);
		lblX.setDisable(true);
		lblY.setDisable(true);
		lblZ.setDisable(true);
		txtX.setDisable(true);
		txtY.setDisable(true);
		txtZ.setDisable(true);
		cbMonths.setDisable(true);
		btnInsert2.setDisable(true);
	}

	public DBConnector db = new DBConnector();
	public ObservableList<EmployeeModel> data = FXCollections.observableArrayList();

	@FXML
	void show(MouseEvent event) throws ClassNotFoundException {
		try {
			Connection conn = db.connection();
			data = FXCollections.observableArrayList();
			System.out.println(LoginController.zmienna);
			ResultSet rs = conn.createStatement().executeQuery(
					"select month_name, total_brutto, login, payments.id_employee from payments natural join logins where logins.login= '"
							+ LoginController.zmienna + "'");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "," + rs.getDouble(2));
				data.add(new EmployeeModel(rs.getInt(1), rs.getDouble(2)));
			}

		} catch (SQLException e) {
			System.out.println("Error" + e);
		}
		tbcMonth.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Integer>("month"));
		tbcTotal.setCellValueFactory(new PropertyValueFactory<EmployeeModel, Double>("total"));

		tbPayments.setItems(null);
		tbPayments.setItems(data);
		lblTotalAmountBrutto.setDisable(true);
		lblTotalBrutto.setDisable(true);
		lblTotalAmountBrutto.setText("0");
		lblTotalNetto.setDisable(true);
		lblTotalAmountNetto.setDisable(true);
		lblTotalAmountNetto.setText("0");
		lblResultBrutto.setText("0");
		lblResultNetto.setText("0");
		txtX.setText("");
		txtY.setText("");
		txtZ.setText("");
		lblMonth.setDisable(true);
		lblX.setDisable(true);
		lblY.setDisable(true);
		lblZ.setDisable(true);
		lblSumBrutto.setDisable(true);
		lblResultBrutto.setDisable(true);
		lblSumNetto.setDisable(true);
		lblResultNetto.setDisable(true);
		txtX.setDisable(true);
		txtY.setDisable(true);
		txtZ.setDisable(true);
		cbMonths.setDisable(true);
		btnInsert2.setDisable(true);
	}

	double total;

	@FXML
	void total(MouseEvent event) throws ClassNotFoundException {
		lblTotalAmountBrutto.setDisable(false);
		lblTotalBrutto.setDisable(false);
		lblTotalAmountNetto.setDisable(false);
		lblTotalNetto.setDisable(false);
		try {
			Connection conn = db.connection();
			data = FXCollections.observableArrayList();
			System.out.println(LoginController.zmienna);
			ResultSet rs = conn.createStatement()
					.executeQuery("select total_brutto from payments natural join logins where logins.login= '"
							+ LoginController.zmienna + "'");
			while (rs.next()) {
				total += rs.getDouble(1);
			}
			lblTotalAmountBrutto.setText(total + "");
			lblTotalAmountNetto.setText((0.77 * total) + "");
			total = 0;

		} catch (SQLException e) {
			System.out.println("Error" + e);
		}

		lblResultBrutto.setText("0");
		txtX.setText("");
		txtY.setText("");
		txtZ.setText("");
	}

	public void initialize() {
		cbMonths.setItems(months);
	}

	@FXML
	void logOut(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
		final Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Login page");
		stage.show();
		((Stage) btnLogOut.getScene().getWindow()).close();
	}

}
