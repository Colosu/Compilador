package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoDefine extends Nodo {
	
	public NodoDefine(NodoImplementacion imp) {
		
		implementaciones = imp;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (implementaciones != null) {
			implementaciones.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception{
		
		if (implementaciones != null) {
			return implementaciones.parseaTipos();
		} else {
			return "OK";
		}
	}
	
	public int reservaMemoria(int dir) throws Exception {
		if (implementaciones != null) {
			return implementaciones.reservaMemoria(dir);
		}
		return 0;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		longitud = 0;
		
		if (implementaciones != null) {
			longitud += implementaciones.calculaLongDir(dir);
		}
		
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		if (implementaciones != null) {
			return implementaciones.generaCodigo();
		} else {
			return "";
		}
	}

	public NodoImplementacion getImplementaciones() {
		return implementaciones;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setImplementaciones(NodoImplementacion implementaciones) {
		this.implementaciones = implementaciones;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(implementaciones != null) {
			resultado += guiones + implementaciones.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre = "Define";
	private NodoImplementacion implementaciones;
	private int longitud;
}
