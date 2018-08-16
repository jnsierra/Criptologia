package co.com.entity;

import java.util.HashMap;
import java.util.Map;

public class TablaEquivalencias {
	
	private Map<String, String> caracteresEquivalentes;

	public Map<String, String> getCaracteresEquivalentes() {
		return caracteresEquivalentes;
	}

	public void setCaracteresEquivalentes(Map<String, String> caracteresEquivalentes) {
		this.caracteresEquivalentes = caracteresEquivalentes;
	}
	
	
	public void addCaracterEqui(String caracter, String simbolo) {
		try {
			if(getCaracteresEquivalentes() == null) {
				setCaracteresEquivalentes(new HashMap<>());
			}
			getCaracteresEquivalentes().put(caracter, simbolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarTablaequivalencias() {
		try {
			getCaracteresEquivalentes().forEach((k,v) -> System.out.println("Caracter: ".concat(k).concat(" Simbolo: ").concat(v) ));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
