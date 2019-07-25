package tree.variables;

import hashmap.Hashmap;
import tree.Nodo;
import tree.expresiones.NodoExpresion;

public class NodoArgumento extends Nodo {
	
	public NodoArgumento(int fila, int columna, NodoExpresion exp, NodoArgumento sig) {

		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("Argumento sin expresión en la linea " + fila + " y columna " + columna +".");
		}
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception {
		
		if (expresion != null) {
			return expresion.parseaTipos();
		} else {
			throw new Exception("Argumento sin expresión en la linea " + fila + " y columna " + columna +".");
		}
	}

	public NodoExpresion getExpresion() {
		return expresion;
	}

	public NodoArgumento getSiguiente() {
		return siguiente;
	}

	public void setExpresion(NodoExpresion expresion) {
		this.expresion = expresion;
	}

	public void setSiguiente(NodoArgumento siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(expresion != null) {
			resultado += guiones + expresion.toString(i+1);
		}
		
		String guiones2 = "";
		for (int j = i; j > 1; j--) {
			guiones2 += "-";
		}
		
		if(siguiente != null) {
			resultado += guiones2 + siguiente.toString(i);
		}
		
		return resultado;
	}
	
	private String nombre = "Argumento";
	private NodoExpresion expresion;
	private NodoArgumento siguiente;
}
