package tree;

import hashmap.Hashmap;
import tree.estructuras.NodoBloque;
import tree.variables.NodoDecDim;

public abstract class Nodo {
	
	public Nodo() {
			
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception {
		
	}
	
	public String parseaTipos() throws Exception {
		return null;
		
	}
	
	public int calculaLongDir(int dir) throws Exception {
		return 0;
	}
	
	public String generaCodigo() throws Exception {
		return "";
	}
	
	public String getTipo() {
		return null;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public void setDefinicion(NodoBloque definicion) {
		
	}
	
	public Nodo getArgumentos() {
		return null;
	}
	
	public int getDireccion() {
		return 0;
	}
	
	public int getTamaño() {
		return 0;
	}
	
	public int getTamañoArgs() {
		return 0;
	}

	public NodoDecDim getDims() {
		return null;
	}
	
	public int getIndice() {
		return 0;
	}
	public Nodo getSiguiente() {
		return null;
	}

	public boolean isGlobal() {
		return false;
	}

	public NodoBloque getBloque() {
		return null;
	}
	
	protected int fila;
	protected int columna;
}
