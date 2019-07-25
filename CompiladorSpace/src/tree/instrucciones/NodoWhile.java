package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.estructuras.*;
import tree.expresiones.NodoExpresion;

public class NodoWhile extends NodoInstruccion {
	
	public NodoWhile(int fila, int columna, NodoExpresion exp, NodoBloque bloq) {
		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		bloque = bloq;
	}
	
	public NodoWhile(int fila, int columna, NodoExpresion exp, NodoBloque bloq, NodoInstruccion sig) {

		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		bloque = bloq;
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("El bucle While que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
		
		if (bloque != null) {
			bloque.parseaId(tabla);
		} else {
			throw new Exception("El bucle While que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception {

		if (expresion != null) {
			if (expresion.parseaTipos().equals("bool")) {

				if (bloque != null) {
					bloque.parseaTipos();
				} else {
					throw new Exception("El bucle While que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
				}
				
				if (siguiente != null) {
					siguiente.parseaTipos();
				}
				return "OK";
			} else {
				throw new Exception("Error de tipos en la expresión del bucle While  que esta en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("El bucle While que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (bloque != null) {
			int s = bloque.reservaMemoria(dir, max);
			if (siguiente != null) {
				return siguiente.reservaMemoria(dir, s);
			} else {
				return s;
			}
		} else {
			throw new Exception("El bucle While que está en la linea " + fila + " y columna " + columna +" no tiene bloque de instrucciones.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = expresion.calculaLongDir(dir);
		longitud++;
		longitud += bloque.calculaLongDir(dir + longitud);
		longitud++;
		if (siguiente != null) {
			return longitud + siguiente.calculaLongDir(dir + longitud);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = expresion.generaCodigo();
		s += "{" + (direccion + expresion.getLongitud()) + "}fjp " + (bloque.getDireccion() + bloque.getLongitud() + 1) + ";\n";
		s += bloque.generaCodigo();
		s += "{" + (direccion + expresion.getLongitud() + 1 + bloque.getLongitud()) + "}ujp " + direccion + ";\n";
		if (siguiente != null) {
			s += siguiente.generaCodigo();
		}
		return s;
	}

	public NodoInstruccion getSiguiente() {
		return siguiente;
	}

	public NodoExpresion getExpresion() {
		return expresion;
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

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setExpresion(NodoExpresion expresion) {
		this.expresion = expresion;
	}

	public void setBloque(NodoBloque bloque) {
		this.bloque = bloque;
	}

	public void setSiguiente(NodoInstruccion siguiente) {
		this.siguiente = siguiente;
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
	
	private String nombre = "While";
	private NodoExpresion expresion;
	private NodoBloque bloque;
	private NodoInstruccion siguiente;
	private int longitud;
	private int direccion;
}
