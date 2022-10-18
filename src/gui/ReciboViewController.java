package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.entidades.Vaga;

public class ReciboViewController implements Initializable{

	private Vaga entidade;
	
	@FXML
	private Label labelNome;
	
	@FXML
	private Label labelTelefone;
	
	@FXML
	private Label labelCpf;
	
	@FXML
	private Label labelTipo;
	
	@FXML
	private Label labelCor;
	
	@FXML
	private Label labelPlaca;
	
	@FXML
	private Label labelNumero;
	
	@FXML
	private Label labelHoraEntrada;
	
	@FXML
	private Label labelHoraSaida;
	
	@FXML
	private Label labelValorTotal;
	
	@FXML
	private Button btFechar;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	@FXML
	private void onBtFecharAction(ActionEvent evento) {
		Utils.currentStage(evento).close();
	}
	
	public void setReciboMensagens() {
		labelNome.setText(entidade.getNomeCliente());
		labelTelefone.setText(entidade.getCliente().getTelefone());
		labelCpf.setText(entidade.getCliente().getCpf());
		labelTipo.setText(entidade.getVeiculo().getTipo());
		labelCor.setText(entidade.getVeiculo().getCor());
		labelPlaca.setText(entidade.getPlaca());
		labelNumero.setText(Integer.toString(entidade.getNumero()));
		labelHoraEntrada.setText(entidade.getHoraEntrada());
		labelHoraSaida.setText(entidade.getHoraSaida());
		labelValorTotal.setText(Double.toString(entidade.getValorTotal()));
	}
	
	public void setVaga(Vaga entidade) {
		this.entidade = entidade;
	}
	
}