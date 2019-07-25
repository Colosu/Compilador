package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoElem extends NodoExp5 {
	
	public NodoElem() {
		
	}
	
	public NodoElem(String tip) {
		tipo = tip;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
	}
	
	public String parseaTipos() throws Exception{
		return tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(tipo != null) {
			resultado += guiones + tipo + "\n";
		}
		
		return resultado;
	}

	private String nombre = "Elemento";
	private String tipo;
}
