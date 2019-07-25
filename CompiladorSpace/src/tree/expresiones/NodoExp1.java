package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoExp1 extends NodoExpresion {
	
	public NodoExp1() {
		
	}
	
	public NodoExp1(String nom) {
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
