package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoParentesis extends NodoExp5 {
	
	public NodoParentesis(int fila, int columna, NodoExpresion exp) {
		expresion = exp;
		this.fila = fila;
		this.columna = columna;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("El If que está en la linea " + fila + " y columna " + columna +" está vacío.");
		}
	}
	
	public String parseaTipos() throws Exception{
		
		if (expresion != null) {
			return expresion.parseaTipos();
		} else {
			throw new Exception("El If que está en la linea " + fila + " y columna " + columna +" está vacío.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		return expresion.calculaLongDir(dir);
	}
	
	public String generaCodigo() throws Exception {
		return expresion.generaCodigo();
	}
	
	public NodoExpresion getExpresion() {
		return expresion;
	}
	
	public int getLongitud() {
		return expresion.getLongitud();
	}

	public void setExpresion(NodoExpresion expresion) {
		this.expresion = expresion;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(expresion != null) {
			resultado += guiones + expresion.toString(i);
		}
		
		return resultado;
	}

	private String nombre = "Paréntesis";
	private NodoExpresion expresion;
}
