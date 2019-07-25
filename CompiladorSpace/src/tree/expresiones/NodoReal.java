package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoReal extends NodoElem {
	
	public NodoReal(String tip, float val) {
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
		return "{" + direccion + "}ldc " + (int)valor + ";\n";
	}

	public String getTipo() {
		return tipo;
	}

	public float getValor() {
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

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String toString(int i) {
		return nombre + ": " + Float.toString(valor) + "\n";
	}

	private String nombre = "Real";
	private String tipo;
	private float valor;
	private int direccion;
	private int longitud;
}
