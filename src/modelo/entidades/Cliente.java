package modelo.entidades;

import java.util.Objects;

public class Cliente {
	
	private String nome;
	private String telefone;
	private String cpf;
	private int fidelidade;
	
	public Cliente() {
	}

	public Cliente(String nome, String telefone, String cpf, int fidelidade) {
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.fidelidade = fidelidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getFidadelidade() {
		return fidelidade;
	}

	public void setFidadelidade(int fidelidade) {
		this.fidelidade = fidelidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", cpf=" + cpf + ", fidelidade=" + fidelidade
				+ "]";
	}

}
