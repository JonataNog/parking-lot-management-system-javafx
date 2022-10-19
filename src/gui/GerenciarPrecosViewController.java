package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.servicos.VagaServicos;

public class GerenciarPrecosViewController implements Initializable {
	
	private VagaServicos servicos;
	
	@FXML
	private TextField txtHora;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
	}
	
	@FXML
	private void onBtSalvarAction(ActionEvent evento) {
		Double preco = Utils.tryParseToDouble(txtHora.getText());
		servicos.setPreco(preco);
		Utils.currentStage(evento).close();
	}
	
	@FXML
	private void onBtCancelarAction(ActionEvent evento) {
		Utils.currentStage(evento).close();
	}
	
	public void setVagaServicos(VagaServicos servicos) {
		this.servicos = servicos;
	}


}
