package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;
import tree.variables.NodoDecDim;
import tree.variables.NodoInsertaTipoArg;

public class NodoCabecera extends Nodo {
	
	public NodoCabecera(String id, int fila, int columna, String tip, NodoDecDim dim, NodoInsertaTipoArg Arg) {
		
		nombre = id;
		this.fila = fila;
		this.columna = columna;
		dims = dim;
		argumentos = Arg;
		if (tip != null)
			setTipoString(tip);
	}
	
	public NodoCabecera(String id, int fila, int columna, String tip, NodoDecDim dim, NodoInsertaTipoArg Arg, NodoCabecera Cab) {
		
		nombre = id;
		this.fila = fila;
		this.columna = columna;
		dims = dim;
		argumentos = Arg;
		siguiente = Cab;
		if (tip != null)
			setTipoString(tip);
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (tabla.putIfAbsent(nombre, this) != null) {
			throw new Exception("El método " + nombre + " está declarado dos veces en la linea " + fila + " y columna " + columna +".");
		} else {
			if (siguiente != null) {
				siguiente.parseaId(tabla);
			}
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public NodoInsertaTipoArg getArgumentos() {
		return argumentos;
	}

	public NodoCabecera getSiguiente() {
		return siguiente;
	}

	public String getTipo() {
		return tipo;
	}

	public int getTamaño() {
		return tamaño;
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

	public void setArgumentos(NodoInsertaTipoArg argumentos) {
		this.argumentos = argumentos;
	}

	public void setSiguiente(NodoCabecera siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado;
		if (tipo != null) {
			resultado = tipo.toString() + " " + nombre + "\n";
		} else {
			resultado = "Proc " + nombre + "\n";
		}
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
	
	private void setTipoString(String t) {
		tipo = t.toString();
		NodoDecDim d = dims;
		while (d != null) {
			tipo += "[" + Integer.toString(d.getIndice()) + "]";
			d = d.getSiguiente();
		}
	}
	
	private String nombre;
	private String tipo;
	private int tamaño;
	private NodoDecDim dims;
	private NodoInsertaTipoArg argumentos;
	private NodoCabecera siguiente;
}
