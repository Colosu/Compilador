package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoPor extends NodoOp2 {
	
	public NodoPor(String nom, int fila, int columna) {
		nombre = nom;
		this.fila = fila;
		this.columna = columna;
	}
	
	public NodoPor(String nom, int fila, int columna, NodoExp2 exp1, NodoExp3 exp2) {
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
			if (expresion1.parseaTipos().equals("int") && expresion2.parseaTipos().equals("int")) {
				return "int";
			}
			if ((expresion1.parseaTipos().equals("int") || expresion1.parseaTipos().equals("float")) && (expresion2.parseaTipos().equals("int") || expresion2.parseaTipos().equals("float"))){
				return "float";
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
		s += "{" + (direccion + expresion1.getLongitud() + expresion2.getLongitud()) + "}mul;\n";
		return s;
	}

	public NodoExp2 getExpresion1() {
		return expresion1;
	}

	public NodoExp3 getExpresion2() {
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

	public void setExpresion1(NodoExp2 expresion1) {
		this.expresion1 = expresion1;
	}

	public void setExpresion2(NodoExp3 expresion2) {
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
	private NodoExp2 expresion1;
	private NodoExp3 expresion2;
	private int longitud;
	private int direccion;
}
