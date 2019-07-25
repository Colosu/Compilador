package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoExp5 extends NodoExp4 {
	
	public NodoExp5() {
		
	}
	
	public NodoExp5(String nom) {
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
