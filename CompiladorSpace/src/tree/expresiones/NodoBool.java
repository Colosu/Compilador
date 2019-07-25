package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoBool extends NodoElem {
	
	public NodoBool(String tip, Boolean val) {
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
		return "{" + direccion + "}ldc " + Boolean.toString(valor) + ";\n";
	}

	public String getTipo() {
		return tipo;
	}

	public Boolean getValor() {
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

	public void setValor(Boolean valor) {
		this.valor = valor;
	}

	public String toString(int i) {
		return nombre + ": " + Boolean.toString(valor) + "\n";
	}

	private String nombre = "Bool";
	private String tipo;
	private Boolean valor;
	private int direccion;
	private int longitud;
}
