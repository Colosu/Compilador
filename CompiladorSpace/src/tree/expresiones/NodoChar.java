package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoChar extends NodoElem {
	
	public NodoChar(String tip, char val) {
		tipo = tip;
		valor = val;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
	}
	
	public String parseaTipos() throws Exception{
		return tipo;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		longitud = 1;
		direccion = dir;
		return 1;
	}
	
	public String generaCodigo() throws Exception {
		return "{" + direccion + "}ldc " + charToInt() + ";\n";
	}

	public String getTipo() {
		return tipo;
	}

	public char getValor() {
		return valor;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	public String toString(int i) {
		return nombre + ": " + valor + "\n";
	}
	
	private int charToInt() {
		return (int)valor - 96;
	}

	private String nombre = "Char";
	private String tipo;
	private char valor;
	private int direccion;
	private int longitud;
}
