package tree.variables;

import tree.Nodo;

public class NodoDecDim extends Nodo {
	
	public NodoDecDim(int ind) {
		indice = ind;
	}
	
	public NodoDecDim(int ind, NodoDecDim sig) {
		indice = ind;
		siguiente = sig;
	}
	
	public int getIndice() {
		return indice;
	}
	public NodoDecDim getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoDecDim siguiente) {
		this.siguiente = siguiente;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		resultado += guiones + Integer.toString(indice) + "\n";
		
		String guiones2 = "";
		for (int j = i; j > 1; j--) {
			guiones2 += "-";
		}
		
		if(siguiente != null) {
			resultado += guiones2 + siguiente.toString(i);
		}
		
		return resultado;
	}

	private String nombre = "DecDim";
	private int indice;
	private NodoDecDim siguiente;
}
