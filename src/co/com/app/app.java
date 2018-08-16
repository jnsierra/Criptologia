package co.com.app;

import co.com.entity.ArchivoEntity;
import co.com.entity.TablaEquivalencias;

public class app {
	
	public static void main(String ... args) {
		try {
			ArchivoEntity arch = new ArchivoEntity();
			arch.setUrl("C:\\Users\\c38035\\Documents\\nicolas\\cripto.txt");
			//Interpreto el archivo (Encuentro el caracter mas usuado)
			arch.leerArchivo();
			//Se encuentra el valor con mayor repeticiones
			System.out.println("Se adiciona a la tabla de equivalencias el caracter mas usado: " + arch.getMensajeDesfragmentado().getCaracteresPeso().get(0).getCaracter() + " Numero de Veces: " + arch.getMensajeDesfragmentado().getCaracteresPeso().get(0).getTamanio() + " Por la: e" );
			TablaEquivalencias equi = new TablaEquivalencias();
			equi.addCaracterEqui(arch.getMensajeDesfragmentado().getCaracteresPeso().get(0).getCaracter(), "e");
			equi.mostrarTablaequivalencias();
			arch.remplazarCaracteres(equi.getCaracteresEquivalentes());
			//Buscamos la palabra (the) que es la mas usada del idioma ingles
			equi = arch.buscarPalabra(equi);
			equi.mostrarTablaequivalencias();
			arch.remplazarCaracteres(equi.getCaracteresEquivalentes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
