package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoExp2 extends NodoExp1 {
	
	public NodoExp2() {
		
	}
	
	public NodoExp2(String nom) {
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
