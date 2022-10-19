package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
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
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.entidades.Vaga;
import modelo.servicos.VagaServicos;

public class MainViewController implements Initializable, DataChangeListener{
	
	private List<Circle> listaCirculos = new ArrayList<>();
	VagaServicos servicos = new VagaServicos();
	
	@FXML
	private Circle circulo1;
	
	@FXML
	private Circle circulo2;
	
	@FXML
	private Circle circulo3;
	
	@FXML
	private Circle circulo4;
	
	@FXML
	private Circle circulo5;

	@FXML
	private Circle circulo6;
	
	@FXML
	private Circle circulo7;
	
	@FXML
	private Circle circulo8;
	
	@FXML
	private Circle circulo9;
	
	@FXML
	private Circle circulo10;
	
	@FXML
	private Circle circulo11;
	
	@FXML
	private Circle circulo12;
	
	@FXML
	private Circle circulo13;
	
	@FXML
	private Circle circulo14;
	
	@FXML
	private Circle circulo15;
	
	@FXML
	private Circle circulo16;
	
	@FXML
	private Circle circulo17;
	
	@FXML
	private Circle circulo18;
	
	@FXML
	private Button btGerenciarVagas;
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		preencheLista();
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
			controller.subscribeDataChangeListener(this);
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

	@Override
	public void onDataChanged() {
		atualizaCirculos();
	}
	
	private void atualizaCirculos() {
		List<Vaga> listaAux = servicos.getListaVagas();
		for (Vaga vaga : listaAux) {
			if(vaga.isDisponivel()) {
				listaCirculos.get(vaga.getNumero()-1).setFill(javafx.scene.paint.Color.GREEN);
			}else {
				listaCirculos.get(vaga.getNumero()-1).setFill(javafx.scene.paint.Color.RED);
			}
		}
	}
	
	private void preencheLista() {
		listaCirculos.add(circulo1);
		listaCirculos.add(circulo2);
		listaCirculos.add(circulo3);
		listaCirculos.add(circulo4);
		listaCirculos.add(circulo5);
		listaCirculos.add(circulo6);
		listaCirculos.add(circulo7);
		listaCirculos.add(circulo8);
		listaCirculos.add(circulo9);
		listaCirculos.add(circulo10);
		listaCirculos.add(circulo11);
		listaCirculos.add(circulo12);
		listaCirculos.add(circulo13);
		listaCirculos.add(circulo14);
		listaCirculos.add(circulo15);
		listaCirculos.add(circulo16);
		listaCirculos.add(circulo17);
		listaCirculos.add(circulo18);
	}

}