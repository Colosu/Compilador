package tree.variables;

import tree.Nodo;

public class NodoInsertaTipoArg extends Nodo {
	
	public NodoInsertaTipoArg(String tipo, NodoDecDim dim) {
			dims = dim;
			setTipoString(tipo);
			nombre = this.tipo;
	}
	
	public NodoInsertaTipoArg(String tipo, NodoDecDim dim, NodoInsertaTipoArg sig) {

			dims = dim;
			setTipoString(tipo);
			nombre = this.tipo;
			siguiente = sig;
	}

	public String getNombre() {
		return nombre;
	}

	public NodoInsertaTipoArg getSiguiente() {
		return siguiente;
	}

	public NodoDecDim getDims() {
		return dims;
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

	public void setDims(NodoDecDim dims) {
		this.dims = dims;
	}

	public void setSiguiente(NodoInsertaTipoArg siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado = nombre.toString() + "\n";
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
	private NodoDecDim dims;
	private NodoInsertaTipoArg siguiente;
}
