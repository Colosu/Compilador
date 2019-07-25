package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;
import tree.variables.NodoInsertaNombreArg;

public abstract class NodoImplementacion extends Nodo {
	
	public NodoImplementacion() {

	}
	
	public NodoImplementacion(String id, int fila, int columna, NodoInsertaNombreArg Arg) {
		
		nombre = id;
		this.fila = fila;
		this.columna = columna;
		argumentos = Arg;
	}
	
	public NodoImplementacion(String id, int fila, int columna, NodoInsertaNombreArg Arg, NodoImplementacion Cab) {
		
		nombre = id;
		this.fila = fila;
		this.columna = columna;
		argumentos = Arg;
		siguiente = Cab;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
	}
	
	public String parseaTipos() throws Exception{
		return null;
	}
	
	public int reservaMemoria(int dir) throws Exception {
		return 0;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		return 0;
	}
	
	public String generaCodigo() throws Exception {
		return null;
	}
	
	public String getNombre() {
		return nombre;
	}

	public NodoInsertaNombreArg getArgumentos() {
		return argumentos;
	}

	public NodoImplementacion getSiguiente() {
		return siguiente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setArgumentos(NodoInsertaNombreArg argumentos) {
		this.argumentos = argumentos;
	}

	public void setSiguiente(NodoImplementacion siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(argumentos != null) {
			resultado += guiones + argumentos.toString(i+1);
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
	
	private String nombre;
	private NodoInsertaNombreArg argumentos;
	private NodoImplementacion siguiente;
}
