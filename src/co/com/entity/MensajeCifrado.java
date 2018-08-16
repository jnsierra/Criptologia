package co.com.entity;

public class MensajeCifrado {
	
	public MensajeCifrado(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void printMsn() {
		System.out.println("Mensaje: " + getMensaje());
	}

}
