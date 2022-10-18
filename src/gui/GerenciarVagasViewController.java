package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.entidades.Vaga;
import modelo.servicos.VagaServicos;

public class GerenciarVagasViewController implements Initializable, DataChangeListener{
	
	private VagaServicos servicos;
	private ObservableList<Vaga> obsListVaga;
	private static Vaga aux = new Vaga();
	
	@FXML
	private TableView<Vaga> tableViewVagas;
	
	@FXML
	private TableColumn<Vaga, String> tableColumnNumVaga;
	
	@FXML
	private TableColumn<Vaga, String> tableColumnNome;
	
	@FXML
	private TableColumn<Vaga, String> tableColumnPlaca;
	
	@FXML
	private TableColumn<Vaga, String> tableColumnTipoVeiculo;
	
	@FXML
	private TableColumn<Vaga, Vaga> tableColumnREMOVE;
	
	@FXML
	private TableColumn<Vaga, Vaga> tableColumnFINALIZAR;
	
	@FXML
	private Button btAlugar;
	
	@FXML
	private Button btFechar;
	
	private TextField txtHoraSaida = new TextField();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	@FXML
	private void onBtAlugarAction(ActionEvent evento) {
		Stage parentStage = Utils.currentStage(evento);
		Vaga obj = new Vaga();
		createAlugarVagaView(obj, "/gui/AlugarVagaView.fxml", parentStage);
	}
	
	@FXML
	private void onBtFecharAction(ActionEvent evento) {
		Utils.currentStage(evento).close();
	}
	
	@Override
	public void onDataChanged() {
		updateTableView();
	}
	
	public void setVagaServicos(VagaServicos servicos) {
		this.servicos = servicos;
	}
	
	private void initializeNodes() {
		tableColumnNumVaga.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		tableColumnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tableColumnTipoVeiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVeiculo"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewVagas.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if (servicos == null) {
			throw new IllegalStateException("Serviços está nulo");
		}
		List<Vaga> lista = servicos.getListaVagasOcupadas();
		obsListVaga = FXCollections.observableArrayList(lista);
		tableViewVagas.setItems(obsListVaga);
		initRemoveButtons();
		initFinalizarTxtFields();
	}
	
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Vaga, Vaga>() {
			private final Button button = new Button("Finalizar vaga");

			@Override
			protected void updateItem(Vaga obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				obj.setHoraSaida(aux.getHoraSaida());
				button.setOnAction(event -> removeEntidade(obj, event));
			}
		});
	}
	
	private void initFinalizarTxtFields() {
		tableColumnFINALIZAR.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnFINALIZAR.setCellFactory(param -> new TableCell<Vaga, Vaga>() {

			@Override
			protected void updateItem(Vaga obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(txtHoraSaida);
			}
			
		});
	}
	
	private void removeEntidade(Vaga obj, ActionEvent evento) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Você tem certeza que deseja finalizar a vaga?");
		
		if(result.get() == ButtonType.OK) {
			if(servicos == null) {
				throw new IllegalStateException("Serviços está nulo");
			}
			try {
				servicos.liberarVaga(obj);
				obj.setHoraSaida(txtHoraSaida.getText());
				obj.setValorTotal(servicos.valorTotal(obj));
				if(obj.getHoraSaida() == null || obj.getHoraSaida().trim().equals("")) {
					throw new Exception("Digite a hora da saída");
				}
				Stage parentStage = Utils.currentStage(evento);
				updateTableView();
				createReciboView(obj, "ReciboView.fxml", parentStage);
			}
			catch(Exception e) {
				Alerts.showAlert("Hora da saída em branco", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
	
	private void createAlugarVagaView(Vaga obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			AlugarVagaViewController controller = loader.getController();
			controller.setVaga(obj);
			controller.setVagaServicos(new VagaServicos());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com os dados da vaga");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void createReciboView(Vaga obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ReciboViewController controller = loader.getController();
			controller.setVaga(obj);
			controller.setReciboMensagens();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Recibo");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}