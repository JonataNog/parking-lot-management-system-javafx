package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import modelo.servicos.VagaServicos;

public class Main extends Application {

	private static Scene mainScene;
	public VagaServicos servicos = new VagaServicos();
	private List<Circle> listaCirculos = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		try {
			servicos.iniciaVagas();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("ParkingLotManagementSystem");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void criaCirculos(AnchorPane anchorPane){
		int auxX = 100;
		int auxY = 100;
		for(int i = 1; i <= 18; i++) {
			if(i <= 9) {
				Circle circle = new Circle();
				circle.setRadius(15);
				circle.setLayoutX(auxX);
				circle.setLayoutY(175);
				circle.setFill(javafx.scene.paint.Color.GREEN);
				anchorPane.getChildren().add(circle);
				listaCirculos.add(circle);
				auxX += 72;
			}else {
				Circle circle = new Circle();
				circle.setRadius(15);
				circle.setLayoutX(auxY);
				circle.setLayoutY(382);
				circle.setFill(javafx.scene.paint.Color.GREEN);
				anchorPane.getChildren().add(circle);
				listaCirculos.add(circle);
				auxY += 72;
			}
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}