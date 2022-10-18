package modelo.entidades;

import java.util.Objects;

public class Veiculo {
    private String placa;
    private String cor;
    private String tipo;
    
    public Veiculo() {
    }

    public Veiculo(String placa, String cor, String tipo){
        this.placa = placa;
        this.cor = cor;
        this.tipo = tipo;
    }
    
    public void setPlaca(String placa){
        this.placa = placa;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    public String getPlaca(){
        return this.placa;
    }

    public String getCor(){
        return this.cor;
    }
      
    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(placa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(placa, other.placa);
	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + this.placa + ", cor=" + this.cor + "]";
	}
}
