package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoNot extends NodoOp4 {
	
	public NodoNot(String nom, int fila, int columna) {
		nombre = nom;
		this.fila = fila;
		this.columna = columna;
	}
	
	public NodoNot(String nom, int fila, int columna, NodoExp4 exp) {
		nombre = nom;
		this.fila = fila;
		this.columna = columna;
		expresion = exp;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("Falta la expresión en la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public String parseaTipos() throws Exception{
		
		if (expresion != null) {
			if (expresion.parseaTipos().equals("bool")){
				return "bool";
			} else {
				throw new Exception("Tipo no válido para la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("Falta la expresión en la operación " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = expresion.calculaLongDir(dir);
		longitud++;
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = expresion.generaCodigo();
		s += "{" + (direccion + expresion.getLongitud()) + "}not;\n";
		return s;
	}

	public NodoExp4 getExpresion() {
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

	public void setExpresion(NodoExp4 expresion) {
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
	
	private String nombre;
	private NodoExp4 expresion;
	private int longitud;
	private int direccion;
}
