package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.servicos.VagaServicos;

public class MainViewController implements Initializable{
	
	@FXML
	private Button btGerenciarVagas;
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
	
	@FXML
	public void onBtGerenciarVagasAction(ActionEvent evento) {
		Stage parentStage = Utils.currentStage(evento);
		criarGerenciarVagasView("/gui/GerenciarVagasView.fxml", parentStage);
	}
	
	private void criarGerenciarVagasView(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			GerenciarVagasViewController controller = loader.getController();
			controller.setVagaServicos(new VagaServicos());
			
			controller.updateTableView();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com os dados para finalizar a vaga");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro pra carregar a view", e.getMessage(), AlertType.ERROR);
		}
	}
}