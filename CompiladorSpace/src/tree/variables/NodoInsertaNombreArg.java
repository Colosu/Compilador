package tree.variables;

import tree.Nodo;

public class NodoInsertaNombreArg extends Nodo {
	
	public NodoInsertaNombreArg(String id, NodoInsertaNombreArg sig) {
			nombre = id;
			siguiente = sig;
	}

	public String getNombre() {
		return nombre;
	}

	public NodoInsertaNombreArg getSiguiente() {
		return siguiente;
	}

	public String getTipo() {
		return tipo;
	}

	public int getTamaño() {
		return tamaño;
	}

	public int getDireccion() {
		return direccion;
	}

	public NodoDecDim getDims() {
		return dims;
	}

	public void setDims(NodoDecDim dims) {
		this.dims = dims;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSiguiente(NodoInsertaNombreArg siguiente) {
		this.siguiente = siguiente;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		
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
	private String tipo;
	private int tamaño;
	private int direccion;
	private NodoDecDim dims;
	private NodoInsertaNombreArg siguiente;
}
