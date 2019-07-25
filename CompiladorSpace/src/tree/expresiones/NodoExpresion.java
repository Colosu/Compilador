package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoExpresion extends Nodo {
	
	public NodoExpresion() {
		
	}
	
	public NodoExpresion(String nom) {
		nombre = nom;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
	}
	
	public String parseaTipos() throws Exception{
		return null;
	}
	
	public String generaCodigoIzq() throws Exception {
		return null;
	}
	
	public int calculaLongDirIzq(int dir) throws Exception {
		return 0;
	}
	
	public boolean isDim() {
		return false;
	}

	public int getSizeT() throws Exception {
		return 1;
	}

	public int getLongitud() {
		return longitud;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public String toString(int i) {
		String resultado = nombre;
		
		return resultado;
	}
	
	private String nombre;
	private int longitud;
	private int direccion;
}
