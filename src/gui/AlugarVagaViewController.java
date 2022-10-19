package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.listeners.DataChangeListener;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.entidades.Vaga;
import modelo.exceptions.ValidationException;
import modelo.servicos.VagaServicos;

public class AlugarVagaViewController implements Initializable {
	
	private Vaga entidade;
	private VagaServicos servicos;
	
	private List<DataChangeListener> dataChangeListerners = new ArrayList<>();
	
	@FXML
	private TextField txtPlaca;
	
	@FXML
	private TextField txtCor;
	
	@FXML
	private RadioButton rbCarro;
	
	@FXML
	private RadioButton rbMoto;
	
	@FXML
	private TextField txtNome;
	
	@FXML 
	private TextField txtCpf;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TextField txtNumVaga;
	
	@FXML
	private TextField txtHoraEntrada;
	
	@FXML
	private Label labelErroPlaca;
	
	@FXML 
	private Label labelErroCor;
	
	@FXML
	private Label labelErroTipo;
	
	@FXML
	private Label labelErroNome;
	
	@FXML
	private Label labelErroCpf;
	
	@FXML
	private Label labelErroTelefone;
	
	@FXML
	private Label labelErroNumVaga;
	
	@FXML
	private Label labelErroHoraSaida;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	private void onBtSalvarAction(ActionEvent evento) {
		if(entidade == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		if(servicos == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		
		try {
			
			entidade = getFormData();
			servicos.ocuparVaga(entidade);
			notifyDataChangeListeners();
			Utils.currentStage(evento).close();
			
		}
		catch(ValidationException e) {
			setErrorMessages(e.getErrors());
		}
	}
	
	@FXML
	private void onBtCancelarAction(ActionEvent evento) {
		Utils.currentStage(evento).close();
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListerners) {
			listener.onDataChanged();
		}
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListerners.add(listener);
	}
	
	private Vaga getFormData() {
		Vaga obj = new Vaga();
		
		ValidationException exception = new ValidationException("Erro de validação");
		
		if(txtPlaca.getText() == null || txtPlaca.getText().trim().equals("")) {
			exception.addError("placa", "Campo não pode ser vazio");
		}
		obj.getVeiculo().setPlaca(txtPlaca.getText());
		obj.setPlaca(txtPlaca.getText());
		
		if(txtCor.getText() == null || txtCor.getText().trim().equals("")) {
			exception.addError("cor", "Campo não pode ser vazio");
		}
		obj.getVeiculo().setCor(txtCor.getText());
		
		if(!rbCarro.isSelected() && !rbMoto.isSelected()) {
			exception.addError("tipo", "Selecione algum item");
		}else {
			if(rbCarro.isSelected()) {
				obj.getVeiculo().setTipo("Carro");
				obj.setTipoVeiculo("Carro");
			}else {
				obj.getVeiculo().setTipo("Moto");
				obj.setTipoVeiculo("Moto");
			}
		}
		
		if(txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addError("nome", "Campo não pode ser vazio");
		}
		obj.getCliente().setNome(txtNome.getText());
		obj.setNomeCliente(txtNome.getText());
		
		if(txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {
			exception.addError("cpf", "Campo não pode ser vazio");
		}
		obj.getCliente().setCpf(txtCpf.getText());
		
		if(txtTelefone.getText() == null || txtTelefone.getText().trim().equals("")) {
			exception.addError("telefone", "Campo não pode ser vazio");
		}
		obj.getCliente().setTelefone(txtTelefone.getText());
		
		if(txtNumVaga.getText() == null || txtNumVaga.getText().trim().equals("")) {
			exception.addError("numVaga", "Campo não pode ser vazio");
		}
		
		Integer aux = Utils.tryParseToInt(txtNumVaga.getText());
		obj.setNumero(Utils.tryParseToInt(txtNumVaga.getText()));
		if(obj.getNumero() == null) {
			exception.addError("vagaNumerica", "Digite apenas números");
		}
		if(aux != null) {
			if(aux < 1 || aux > 18) {
				exception.addError("vagaInvalida", "Vaga inválida");
			}else if(!servicos.listaEstaVazia()) {
				if(!servicos.estaDisponivel(obj))
					exception.addError("vagaIndisponivel", "Vaga indisponível");
			}
		}
		
		if(txtHoraEntrada.getText() == null || txtHoraEntrada.getText().trim().equals("")) {
			exception.addError("horaEntrada", "Campo não pode ser vazio");
		}
		obj.setHoraEntrada(txtHoraEntrada.getText());
		
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
		return obj;
	}
	
	public void updateFormData() {
		if (entidade == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		txtPlaca.setText(entidade.getVeiculo().getPlaca());
		txtCor.setText(entidade.getVeiculo().getCor());
		txtNome.setText(entidade.getCliente().getNome());
		txtCpf.setText(entidade.getCliente().getCpf());
		txtTelefone.setText(entidade.getCliente().getTelefone());
		txtHoraEntrada.setText(entidade.getHoraEntrada());
	}
	
	public void setVaga(Vaga entidade) {
		this.entidade = entidade;
	}
	
	public void setVagaServicos(VagaServicos servicos) {
		this.servicos = servicos;
	}
	
	public void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("placa")) {
			labelErroPlaca.setText(errors.get("placa"));
		}
		if(fields.contains("cor")) {
			labelErroCor.setText(errors.get("cor"));
		}
		if(fields.contains("tipo")) {
			labelErroTipo.setText(errors.get("tipo"));
		}
		if(fields.contains("nome")) {
			labelErroNome.setText(errors.get("nome"));
		}
		if(fields.contains("cpf")) {
			labelErroCpf.setText(errors.get("cpf"));
		}
		if(fields.contains("telefone")) {
			labelErroTelefone.setText(errors.get("telefone"));
		}
		if(fields.contains("numVaga")) {
			labelErroNumVaga.setText(errors.get("numVaga"));
		}
		if(fields.contains("vagaInvalida")) {
			labelErroNumVaga.setText(errors.get("vagaInvalida"));
		}
		if(fields.contains("vagaIndisponivel")) {
			labelErroNumVaga.setText(errors.get("vagaIndisponivel"));
		}
		if(fields.contains("vagaNumerica")) {
			labelErroNumVaga.setText(errors.get("vagaNumerica"));
		}
		if(fields.contains("horaEntrada")) {
			labelErroHoraSaida.setText(errors.get("horaEntrada"));
		}
	}
}