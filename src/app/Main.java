package app;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
		final Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Login page");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
