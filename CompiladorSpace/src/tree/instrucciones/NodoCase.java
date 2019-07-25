package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.estructuras.*;

public class NodoCase extends Nodo {
	
	public NodoCase() {
		
	}
	
	public NodoCase(int fila, int columna, int ind, NodoBloque bloq) {

		this.fila = fila;
		this.columna = columna;
		nombre += " " + Integer.toString(ind) ;
		indice = ind;
		bloque = bloq;
	}
	
	public NodoCase(int fila, int columna, int ind, NodoBloque bloq, NodoCase sig) {

		this.fila = fila;
		this.columna = columna;
		nombre += " " + Integer.toString(ind);
		indice = ind;
		bloque = bloq;
		siguiente = sig;
	}
	
	public void pasaDefault(NodoDefault def) {
		if(siguiente != null) {
			siguiente.pasaDefault(def);
		} else {
			siguiente = def;
		}
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (bloque != null) {
			bloque.parseaId(tabla);
		} else {
			throw new Exception("El Case que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		} else {
			throw new Exception("El Case que está en la linea " + fila + " y columna " + columna +" no tiene Case/Default siguiente.");
		}
	}
	
	public String parseaTipos(int i) throws Exception {

		if (bloque != null) {
			if (bloque.parseaTipos() == "OK" && indice == i) {

				if (siguiente != null) {
					siguiente.parseaTipos(i + 1);
				} else {
					throw new Exception("El Case que está en la linea " + fila + " y columna " + columna +" no tiene Case/Default siguiente.");
				}
				return "OK";
			} else {
				throw new Exception("Error de tipos en el Case que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("El Case que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (bloque != null) {
			int s = bloque.reservaMemoria(dir, max);
			if (siguiente != null) {
				return siguiente.reservaMemoria(dir, s);
			} else {
				throw new Exception("El Case que está en la linea " + fila + " y columna " + columna +" no tiene Case/Default siguiente.");
			}
		} else {
			throw new Exception("El Case que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = bloque.calculaLongDir(dir);
		if (siguiente != null) {
			return longitud + 1 + siguiente.calculaLongDir(dir + longitud + 1);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		return bloque.generaCodigo();
	}

	public NodoBloque getBloque() {
		return bloque;
	}

	public int getIndice() {
		return indice;
	}

	public NodoCase getSiguiente() {
		return siguiente;
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

	public void setSiguiente(NodoCase siguiente) {
		this.siguiente = siguiente;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public void setBloque(NodoBloque bloque) {
		this.bloque = bloque;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(bloque != null) {
			resultado += guiones + bloque.toString(i+1);
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
	
	private String nombre = "Case";
	private int indice;
	private NodoBloque bloque;
	private NodoCase siguiente;
	private int longitud;
	private int direccion;
}
