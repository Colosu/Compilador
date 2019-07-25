package tree.instrucciones;

import tree.Nodo;
import tree.variables.NodoDecDim;
import hashmap.Hashmap;

public class NodoDecTVar extends NodoInstruccion {
	
	public NodoDecTVar(String nom, int fila, int columna, String tip, NodoDecDim dim) {

		this.fila = fila;
		this.columna = columna;
		nombre = nom;
		dims = dim;
		setTipoString(tip);
	}
	
	public NodoDecTVar(String nom, int fila, int columna, String tip, NodoDecDim dim, NodoInstruccion sig) {

		this.fila = fila;
		this.columna = columna;
		nombre = nom;
		dims = dim;
		setTipoString(tip);
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (tabla.putIfAbsent(nombre, this) != null) {
			throw new Exception("La variable " + nombre + " que está en la linea " + fila + " y columna " + columna +" está duplicada.");
		} else {
			this.isGlobal = tabla.isGlobal();
			if (siguiente != null) {
				siguiente.parseaId(tabla);
			}
		}
	}
	
	public String parseaTipos() throws Exception {

		if (siguiente != null) {
			siguiente.parseaTipos();
		}
		return "OK";
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		direccion = dir;
		if (siguiente != null) {
			return siguiente.reservaMemoria(dir + tamaño, Math.max(dir + tamaño, max));
		} else {
			return Math.max(dir + tamaño, max);
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		if (siguiente != null) {
			return siguiente.calculaLongDir(dir);
		}
		return 0;
	}
	
	public String generaCodigo() throws Exception {
		if (siguiente != null) {
			return siguiente.generaCodigo();
		}
		return "";
	}

	public NodoInstruccion getSiguiente() {
		return siguiente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public NodoDecDim getDims() {
		return dims;
	}

	public int getTamaño() {
		return tamaño;
	}

	public int getDireccion() {
		return direccion;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setDims(NodoDecDim dims) {
		this.dims = dims;
	}

	public void setSiguiente(NodoInstruccion siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado = tipo.toString() + " " + nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(dims != null) {
			resultado += guiones + dims.toString(i+1);
		}
		
		String guiones2 = "";
		for (int j = i; j > 1; j--) {
			guiones2 += "-";
		}
		
		if(siguiente != null) {
			resultado += guiones2 + siguiente.toString(i);
		}
		
		return resultado;
	}
	
	private void setTipoString(String t) {
		tamaño = 1;
		tipo = t.toString();
		NodoDecDim d = dims;
		while (d != null) {
			tamaño *= d.getIndice();
			tipo += "[" + Integer.toString(d.getIndice()) + "]";
			d = d.getSiguiente();
		}
	}
	
	private String nombre;
	private String tipo;
	private int tamaño;
	private int direccion;
	private boolean isGlobal;
	private NodoDecDim dims;
	private NodoInstruccion siguiente;
}
