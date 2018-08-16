package co.com.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MensajeDesfragmentado {

	private List<String> caracteres;

	private Map<String, Long> frecuencyCaracters;

	private List<CaracterPeso> caracteresPeso;

	public void desfragmentaMensaje(String linea) {
		try {
			for (int i = 0; i < linea.length(); i++) {
				if (caracteres == null)
					caracteres = new ArrayList<>();
				caracteres.add(String.valueOf(linea.charAt(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void groupByCaracters() {
		try {
			frecuencyCaracters = caracteres.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
			for (Map.Entry<String, Long> entry : frecuencyCaracters.entrySet()) {
				if(caracteresPeso == null)
					caracteresPeso = new ArrayList<>();
				CaracterPeso aux = new CaracterPeso();
				aux.setCaracter(entry.getKey());
				aux.setTamanio(entry.getValue());
				caracteresPeso.add(aux);
			}
			caracteresPeso = caracteresPeso.stream().parallel()
			.sorted(Comparator.comparing(CaracterPeso::getTamanio).reversed())
			.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(List<String> caracteres) {
		this.caracteres = caracteres;
	}

	public Map<String, Long> getFrecuencyCaracters() {
		return frecuencyCaracters;
	}

	public void setFrecuencyCaracters(Map<String, Long> frecuencyCaracters) {
		this.frecuencyCaracters = frecuencyCaracters;
	}

	public List<CaracterPeso> getCaracteresPeso() {
		return caracteresPeso;
	}

	public void setCaracteresPeso(List<CaracterPeso> caracteresPeso) {
		this.caracteresPeso = caracteresPeso;
	}
	
	

}
