package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.entidades.Vaga;

public class FinalizarVagaViewController implements Initializable{
	
	private Vaga vaga;
	
	@FXML
	private TextField txtHoraSaida;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	@FXML
	private void onBtSalvarAction(ActionEvent evento) {
		vaga.setHoraSaida(txtHoraSaida.getText());
		Utils.currentStage(evento).close();
	}
	
	@FXML
	private void onBtCancelarAction(ActionEvent evento) {
		Utils.currentStage(evento).close();
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

}
