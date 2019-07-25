package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoExp3 extends NodoExp2 {
	
	public NodoExp3() {
		
	}
	
	public NodoExp3(String nom) {
		nombre = nom;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
	}
	
	public String parseaTipos() throws Exception{
		return null;
	}

	public String toString(int i) {
		String resultado = nombre;
		
		return resultado;
	}
	
	private String nombre;
}
