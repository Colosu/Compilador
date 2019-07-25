package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoOp1 extends NodoExp1 {
	
	public NodoOp1() {
		
	}
	
	public NodoOp1(String nom) {
		nombre = nom;
	}
	
	public void evalua() {
		
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion1 != null) {
			expresion1.parseaId(tabla);
		}
		
		if (expresion2 != null) {
			expresion2.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception{
		return null;
	}

	public NodoExp1 getExpresion1() {
		return expresion1;
	}

	public NodoExp2 getExpresion2() {
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

	public void setExpresion1(NodoExp1 expresion1) {
		this.expresion1 = expresion1;
	}

	public void setExpresion2(NodoExp2 expresion2) {
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
	private NodoExp1 expresion1;
	private NodoExp2 expresion2;
	private int longitud;
	private int direccion;
}
