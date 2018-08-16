package co.com.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArchivoEntity {
	
	private MensajeCifrado mensajeCifrado;
	private MensajeDesfragmentado mensajeDesfragmentado;
	
	private String mensajeActual;
	
	private String url;

	public MensajeCifrado getMensajeCifrado() {
		return mensajeCifrado;
	}

	public void setMensajeCifrado(MensajeCifrado mensajeCifrado) {
		this.mensajeCifrado = mensajeCifrado;
	}
	
	public MensajeDesfragmentado getMensajeDesfragmentado() {
		return mensajeDesfragmentado;
	}

	public void setMensajeDesfragmentado(MensajeDesfragmentado mensajeDesfragmentado) {
		this.mensajeDesfragmentado = mensajeDesfragmentado;
	}

	public void leerArchivo() {
		Path path = Paths.get(url);
		try (Stream<String> stream = Files.lines(path)) {
		   setMensajeCifrado(new MensajeCifrado(stream.findFirst().get()));
		   setMensajeDesfragmentado(new MensajeDesfragmentado());
		   getMensajeDesfragmentado().desfragmentaMensaje(getMensajeCifrado().getMensaje());
		   getMensajeDesfragmentado().groupByCaracters();
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public void remplazarCaracteres(Map<String, String> equivalencia) {
		try {
			setMensajeActual(getMensajeCifrado().getMensaje());
			equivalencia.forEach( (k,v) -> this.cambiarMensaje(v,k) );
			System.out.println("Este es el mensaje despues de remplazar la tabla de equivalencias: " + getMensajeActual());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cambiarMensaje(String caracter, String simbolo) {
		//System.out.println("Antes " + getMensajeActual());
		//setMensajeActual( getMensajeActual().replaceAll(simbolo, caracter) );
		String mensajeFinal = "";
		for(int i = 0; i < getMensajeActual().length() ; i++ ) {
			if( getMensajeActual().charAt(i) == simbolo.charAt(0) ) {
				mensajeFinal = mensajeFinal.concat(caracter);
			}else {
				mensajeFinal = mensajeFinal.concat(""+getMensajeActual().charAt(i));
			}
		}
		setMensajeActual(mensajeFinal);
		//System.out.println("Despues " + getMensajeActual());
	}

	public String getMensajeActual() {
		return mensajeActual;
	}

	public void setMensajeActual(String mensajeActual) {
		this.mensajeActual = mensajeActual;
	}
	
	//FUNCION CON LA CUAL BUSCA LA PALABRA THE 
	public TablaEquivalencias buscarPalabra(TablaEquivalencias equi) {
		//Encuentro todas las partes en donde tenga la letra e
		List<Integer> coincidencias = new ArrayList<>();
		Boolean buscando= Boolean.TRUE;
		Integer inicio = 0;
		while(buscando) {
			Integer found = getMensajeActual().indexOf("e", inicio);
			if(found.equals(Integer.valueOf("-1"))) {
				buscando = Boolean.FALSE;
			}else {
				coincidencias.add(found);
				inicio = found + 1;
			}
		}
		//Obtengo los dos caracteres anteriores a la e
		List<String> caracteresEncontrados = new ArrayList<>();
		caracteresEncontrados = coincidencias
				.stream()
				.parallel()
				.map(p -> {
					String aux = "";
					aux = getMensajeActual().substring(p-2, p+1);
					return aux;
				}).collect(Collectors.toList());
		caracteresEncontrados.stream().parallel().forEach(System.out::println);
		//Elimino las coincidencias que tenga dos ee concecutivas
		System.out.println("*******************************************");
		System.out.println("*******************************************");
		caracteresEncontrados =  caracteresEncontrados.stream().parallel()
		.filter(ce -> {
				if(ce.indexOf("ee") == -1)
					return true;
				else
					return false;					
			})
		.collect(Collectors.toList());
		caracteresEncontrados.stream().parallel().forEach(System.out::println);
		//Agrupo la cantidad de coincidencias
		Map<String, Long> cantidadAgrupaciones = caracteresEncontrados.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		List<FragmentosCantidad> listaAgrupacion = new ArrayList<>();
		for (Map.Entry<String, Long> entry : cantidadAgrupaciones.entrySet()) {
			FragmentosCantidad aux = new FragmentosCantidad();
			aux.setFragmento(entry.getKey());
			aux.setCantidad(entry.getValue());
			listaAgrupacion.add(aux);
		}
		listaAgrupacion = listaAgrupacion.stream().parallel()
				.sorted(Comparator.comparing(FragmentosCantidad::getCantidad).reversed())
				.collect(Collectors.toList());
		System.out.println("*******************************************");
		System.out.println("*******************************************");
		System.out.println("Se encuentra que la mejor coincidencia para la palabra THE es:...".concat(listaAgrupacion.get(0).getFragmento()).concat("... con ").concat(listaAgrupacion.get(0).getCantidad().toString()).concat( " repeticiones") );
		//listaAgrupacion.forEach(la -> System.out.println("Valor " + la.getFragmento() + " Cantidad: " + la.getCantidad()));
		equi.addCaracterEqui(""+listaAgrupacion.get(0).getFragmento().charAt(0),"t");
		equi.addCaracterEqui(""+listaAgrupacion.get(0).getFragmento().charAt(1),"h");
		return equi;
	}
}
