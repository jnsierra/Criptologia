package co.com.entity;

public class CaracterPeso {

	private String caracter;
	private Long tamanio;

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Long getTamanio() {
		return tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

	@Override
	public String toString() {
		return "CaracterPeso [caracter=" + caracter + ", tamanio=" + tamanio + "]";
	}

}
