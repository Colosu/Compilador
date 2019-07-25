package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.estructuras.*;

public class NodoDefault extends NodoCase {
	
	public NodoDefault(int fila, int columna, NodoBloque bloq) {
		this.fila = fila;
		this.columna = columna;
		bloque = bloq;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (bloque != null) {
			bloque.parseaId(tabla);
		} else {
			throw new Exception("El Default que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
	}
	
	public String parseaTipos(int i) throws Exception {

		if (bloque != null) {
			if (bloque.parseaTipos().equals("OK")) {
				max = i - 1;
				return "OK";
			} else {
				throw new Exception("Error de tipos en el Default que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("El Default que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (bloque != null) {
			return bloque.reservaMemoria(dir, max);
		} else {
			throw new Exception("El Default que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = bloque.calculaLongDir(dir);
		return longitud + 1;
	}
	
	public String generaCodigo() throws Exception {
		return bloque.generaCodigo();
	}

	public NodoBloque getBloque() {
		return bloque;
	}

	public int getLongitud() {
		return longitud;
	}

	public int getDireccion() {
		return direccion;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
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
		
		return resultado;
	}
	
	private String nombre = "Default";
	private NodoBloque bloque;
	private int longitud;
	private int direccion;
	private int max;
}
