package modelo.servicos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gui.util.Utils;
import modelo.entidades.Vaga;

public class VagaServicos {
	
	private static List<Vaga> listaVagas = new ArrayList<>();
	private static List<Vaga> listaVagasOcupadas = new ArrayList<>();
	private final double preco = 5.5;
	
	public void iniciaVagas() {
		for(int i = 1; i <= 48; i++) {
			Vaga vaga = new Vaga(i);
			listaVagas.add(vaga);
		}
	}
	
	public boolean listaEstaVazia() {
		if(listaVagas.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void ocuparVaga(Vaga vaga) {
		encontraPorNumero(vaga).setDisponivel(false);
		listaVagasOcupadas.add(vaga);
	}
	
	public void liberarVaga(Vaga vaga) {
		encontraPorNumero(vaga).setDisponivel(true);
		listaVagasOcupadas.remove(vaga);
	}
	
	public boolean estaDisponivel(Vaga vaga) {
		if(encontraPorNumero(vaga).isDisponivel()) {
			return true;
		}
		return false;
	}
	
	private Vaga encontraPorNumero(Vaga vaga) {
		for(Vaga obj : listaVagas) {
			if(obj.getNumero().equals(vaga.getNumero())) {
				return obj;
			}
		}
		return null;
	}
	
	public double valorTotal(Vaga vaga) {
		int horas = calculaHoras(vaga);
		return horas * preco;
	}
	
	private Integer calculaHoras(Vaga vaga) {
		LocalTime localTimeInicio = LocalTime.parse(vaga.getHoraEntrada(), DateTimeFormatter.ofPattern("HH:mm"));
		LocalTime localTimeFim = LocalTime.parse(vaga.getHoraSaida(), DateTimeFormatter.ofPattern("HH:mm"));
		int horaInicio = localTimeInicio.getHour();
		int horaFim = localTimeFim.getHour();
		return horaFim-horaInicio;
	}

	public List<Vaga> getListaVagas() {
		return listaVagas;
	}
	
	public List<Vaga> getListaVagasOcupadas() {
		return listaVagasOcupadas;
	}
}