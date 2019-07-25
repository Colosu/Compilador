package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.expresiones.NodoExpresion;

public class NodoReturn extends NodoInstruccion {
	
	public NodoReturn(int fila, int columna, NodoExpresion exp) {

		this.fila = fila;
		this.columna = columna;
		expresion = exp;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("El Return que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
	}
	
	public String parseaTipos() throws Exception {
		
		if (expresion != null) {
			return expresion.parseaTipos();
		} else {
			throw new Exception("El Return que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = expresion.calculaLongDir(dir);
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		return expresion.generaCodigo();
	}
	
	public NodoExpresion getExpresion() {
		return expresion;
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
			resultado += guiones + expresion.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre = "Return";
	private NodoExpresion expresion;
	private int longitud;
	private int direccion;
}
