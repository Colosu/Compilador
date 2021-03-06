package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoNEQ extends NodoOp0 {
	
	public NodoNEQ(String nom, int fila, int columna) {
		nombre = nom;
		this.fila = fila;
		this.columna = columna;
	}
	
	public NodoNEQ(String nom, int fila, int columna, NodoExpresion exp1, NodoExp1 exp2) {
		nombre = nom;
		this.fila = fila;
		this.columna = columna;
		expresion1 = exp1;
		expresion2 = exp2;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion1 != null) {
			expresion1.parseaId(tabla);
		} else {
			throw new Exception("Falta una expresión en la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
		}
		
		if (expresion2 != null) {
			expresion2.parseaId(tabla);
		} else {
			throw new Exception("Falta una expresión en la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public String parseaTipos() throws Exception{
		
		if (expresion1 != null && expresion2 != null) {
			if (expresion1.parseaTipos().equals(expresion2.parseaTipos())){
				return "bool";
			} else {
				throw new Exception("Tipos no coincidentes en la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("Falta una expresión en la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = expresion2.calculaLongDir(dir);
		longitud += expresion1.calculaLongDir(dir + longitud);
		longitud++;
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = expresion2.generaCodigo();
		s += expresion1.generaCodigo();
		s += "{" + (direccion + expresion1.getLongitud() + expresion2.getLongitud()) + "}neq;\n";
		return s;
	}

	public NodoExpresion getExpresion1() {
		return expresion1;
	}

	public NodoExp1 getExpresion2() {
		return expresion2;
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

	public void setExpresion1(NodoExpresion expresion1) {
		this.expresion1 = expresion1;
	}

	public void setExpresion2(NodoExp1 expresion2) {
		this.expresion2 = expresion2;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(expresion1 != null) {
			resultado += guiones + expresion1.toString(i+1);
		}
		
		if(expresion2 != null) {
			resultado += guiones + expresion2.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre;
	private NodoExpresion expresion1;
	private NodoExp1 expresion2;
	private int longitud;
	private int direccion;
}
