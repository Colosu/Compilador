package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoInstruccion extends Nodo {
	
	public NodoInstruccion() {
		
	}
	
	public NodoInstruccion(NodoInstruccion sig) {
		
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception {
		return null;
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		return max;
	}
	
	public String generaCodigo() throws Exception {
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public NodoInstruccion getSiguiente() {
		return siguiente;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSiguiente(NodoInstruccion siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		
		String guiones2 = "";
		for (int j = i; j > 1; j--) {
			guiones2 += "-";
		}
		
		if(siguiente != null) {
			resultado += guiones2 + siguiente.toString(i);
		}
		
		return resultado;
	}
	
	private String nombre;
	private NodoInstruccion siguiente;
	private int longitud;
}
