package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoExp4 extends NodoExp3 {
	
	public NodoExp4() {
		
	}
	
	public NodoExp4(String nom) {
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
