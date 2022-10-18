package modelo.entidades;

import java.util.Objects;

public class Vaga {
    
    private Integer numero;
    private boolean disponivel = true;
    private Veiculo veiculo;
    private String placa;
    private String tipoVeiculo;
    private Cliente cliente;
    private String nomeCliente;
    private String horaEntrada;
    private String horaSaida;
    private Double valorTotal;
    
    public Vaga() {
        this.disponivel = true;
        this.cliente = new Cliente();
        this.veiculo = new Veiculo();
        this.placa = veiculo.getPlaca();
        this.tipoVeiculo = veiculo.getTipo();
        this.nomeCliente = cliente.getNome();
    }

    public Vaga(int numero){
        this.numero = numero;
        this.disponivel = true;
        this.cliente = null;
        this.veiculo = null;
    }
    
    public Vaga(int numero, Veiculo veiculo, Cliente cliente) {
		this.disponivel = true;
		this.numero = numero;
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.placa = veiculo.getPlaca();
        this.tipoVeiculo = veiculo.getTipo();
        this.nomeCliente = cliente.getNome();
	}

	public Integer getNumero(){
        return this.numero;
    }

    public Veiculo getVeiculo(){
        return this.veiculo;
    }

    public Cliente getCliente(){
        return this.cliente;
    }


    public boolean isDisponivel(){
        return this.disponivel;
    }

    public void setNumero(Integer numero){
        this.numero = numero;
    }

    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }

    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = veiculo.getPlaca();
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = veiculo.getTipo();
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = cliente.getNome();
	}
	
	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		return Objects.equals(numero, other.numero);
	}

}
