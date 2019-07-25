package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;

public abstract class NodoOp4 extends NodoExp4 {
	
	public NodoOp4() {
		
	}
	
	public NodoOp4(String nom) {
		nombre = nom;
	}
	
	public void evalua() {
		
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception{
		return null;
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
