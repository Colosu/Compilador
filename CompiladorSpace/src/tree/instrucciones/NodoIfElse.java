package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.estructuras.*;
import tree.expresiones.NodoExpresion;

public class NodoIfElse extends NodoInstruccion {
	
	public NodoIfElse(int fila, int columna, NodoExpresion exp, NodoBloque bloq1, NodoBloque bloq2) {
		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		bloque1 = bloq1;
		bloque2 = bloq2;
	}
	
	public NodoIfElse(int fila, int columna, NodoExpresion exp, NodoBloque bloq1, NodoBloque bloq2, NodoInstruccion sig) {
		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		bloque1 = bloq1;
		bloque2 = bloq2;
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
		
		if (bloque1 != null) {
			bloque1.parseaId(tabla);
		} else {
			throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene un bloque de instrucciones.");
		}
		
		if (bloque2 != null) {
			bloque2.parseaId(tabla);
		} else {
			throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene un bloque de instrucciones.");
		}
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception {

		if (expresion != null) {
			if (expresion.parseaTipos().equals("bool")) {

				if (bloque1 != null) {
					bloque1.parseaTipos();
				} else {
					throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene un bloque de instrucciones.");
				}

				if (bloque2 != null) {
					bloque2.parseaTipos();
				} else {
					throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene un bloque de instrucciones.");
				}
				
				if (siguiente != null) {
					siguiente.parseaTipos();
				}
				return "OK";
			} else {
				throw new Exception("Error de tipos en la expresión del IfElse que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (bloque1 != null && bloque2 != null) {
			int s = bloque1.reservaMemoria(dir, max);
			int j = bloque2.reservaMemoria(dir, max);
			if (siguiente != null) {
				return siguiente.reservaMemoria(dir, Math.max(s, j));
			} else {
				return Math.max(s, j);
			}
		} else {
			throw new Exception("El IfElse que está en la linea " + fila + " y columna " + columna +" no tiene un bloque de instrucciones.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = expresion.calculaLongDir(dir);
		longitud++;
		longitud += bloque1.calculaLongDir(dir + longitud);
		longitud++;
		longitud += bloque2.calculaLongDir(dir + longitud);
		if (siguiente != null) {
			return longitud + siguiente.calculaLongDir(dir + longitud);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = expresion.generaCodigo();
		s += "{" + (direccion + expresion.getLongitud()) + "}fjp " + (bloque1.getDireccion() + bloque1.getLongitud() + 1) + ";\n";
		s += bloque1.generaCodigo();
		s += "{" + (direccion + expresion.getLongitud() + 1 + bloque1.getLongitud()) + "}ujp " + (bloque2.getDireccion() + bloque2.getLongitud()) + ";\n";
		s += bloque2.generaCodigo();
		if (siguiente != null) {
			s += siguiente.generaCodigo();
		}
		return s;
	}

	public NodoInstruccion getSiguiente() {
		return siguiente;
	}

	public NodoBloque getBloque2() {
		return bloque2;
	}

	public NodoExpresion getExpresion() {
		return expresion;
	}

	public NodoBloque getBloque1() {
		return bloque1;
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

	public void setBloque1(NodoBloque bloque1) {
		this.bloque1 = bloque1;
	}

	public void setBloque2(NodoBloque bloque2) {
		this.bloque2 = bloque2;
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
		
		if(bloque1 != null) {
			resultado += guiones + bloque1.toString(i+1);
		}
		
		if(bloque2 != null) {
			resultado += guiones + bloque2.toString(i+1);
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
	
	private String nombre = "IfElse";
	private NodoExpresion expresion;
	private NodoBloque bloque1;
	private NodoBloque bloque2;
	private NodoInstruccion siguiente;
	private int longitud;
	private int direccion;
}
