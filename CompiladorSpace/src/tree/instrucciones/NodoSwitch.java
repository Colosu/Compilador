package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.expresiones.NodoExpresion;

public class NodoSwitch extends NodoInstruccion {
	
	public NodoSwitch(int fila, int columna, NodoExpresion exp, NodoCase cas, NodoDefault def) {
		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		if (cas != null) {
			cases = cas;
			cases.pasaDefault(def);
			this.def = def;
		} else {
			this.def = def;
			cases = def;
		}
	}
	
	public NodoSwitch(int fila, int columna, NodoExpresion exp, NodoCase cas, NodoInstruccion sig, NodoDefault def) {

		this.fila = fila;
		this.columna = columna;
		expresion = exp;
		if (cas != null) {
			cases = cas;
			cases.pasaDefault(def);
			this.def = def;
		} else {
			this.def = def;
			cases = def;
		}
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("El Switch que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
		
		if (cases != null) {
			cases.parseaId(tabla);
		} else {
			throw new Exception("El Switch que está en la linea " + fila + " y columna " + columna +" no tiene cases.");
		}
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception {

		if (expresion != null) {
			if (expresion.parseaTipos().equals("int")) {

				if (cases != null) {
					cases.parseaTipos(1);
				} else {
					throw new Exception("El Switch que está en la linea " + fila + " y columna " + columna +" no tiene cases.");
				}
				
				if (siguiente != null) {
					siguiente.parseaTipos();
				}
				return "OK";
			} else {
				throw new Exception("Error de tipos en la expresión del Switch que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("El Switch que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (cases != null) {
			int s = cases.reservaMemoria(dir, max);
			if (siguiente != null) {
				return siguiente.reservaMemoria(dir, s);
			} else {
				return s;
			}
		} else {
			throw new Exception("El Switch que está en la linea " + fila + " y columna " + columna +" no tiene cases.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = expresion.calculaLongDir(dir);
		longitud += 10;
		longCases += cases.calculaLongDir(dir + longitud);
		longitud += longCases;
		longitud += def.getMax();
		if (siguiente != null) {
			return longitud + siguiente.calculaLongDir(dir + longitud);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		int i = def.getMax();
		String s = expresion.generaCodigo();
		s += "{" + (direccion + expresion.getLongitud()) + "}dpl;\n";
		s += "{" + (direccion + expresion.getLongitud() + 1) + "}ldc 1;\n";
		s += "{" + (direccion + expresion.getLongitud() + 2) + "}geq;\n";
		s += "{" + (direccion + expresion.getLongitud() + 3) + "}fjp " + def.getDireccion() + ";\n";
		s += "{" + (direccion + expresion.getLongitud() + 4) + "}dpl;\n";
		s += "{" + (direccion + expresion.getLongitud() + 5) + "}ldc " + i + ";\n";
		s += "{" + (direccion + expresion.getLongitud() + 6) + "}leq;\n";
		s += "{" + (direccion + expresion.getLongitud() + 7) + "}fjp " + def.getDireccion() + ";\n";
		s += "{" + (direccion + expresion.getLongitud() + 8) + "}neg;\n";
		s += "{" + (direccion + expresion.getLongitud() + 9) + "}ixj " + (direccion + expresion.getLongitud() + longCases + i + 10) + ";\n";
		NodoCase c = cases;
		int l = 0;
		while (c != null) {
			s += c.generaCodigo();
			l += c.getLongitud();
			s += "{" + (direccion + expresion.getLongitud() + 10 + l) + "}ujp " + (this.direccion + this.longitud) + ";\n";
			l++;
			c = c.getSiguiente();
		}
		c = cases;
		String saltos = "";
		while (i > 0) {
			saltos = "{" + (direccion + expresion.getLongitud() + 10 + longCases + i - 1) + "}ujp " + c.getDireccion() + ";\n" + saltos;
			c = c.getSiguiente();
			i--;
		}
		s += saltos;
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

	public NodoCase getCases() {
		return cases;
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

	public void setExpresion(NodoExpresion expresion) {
		this.expresion = expresion;
	}

	public void setCases(NodoCase cases) {
		this.cases = cases;
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
		
		if(cases != null) {
			resultado += guiones + cases.toString(i+1);
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
	
	private String nombre = "Switch";
	private NodoExpresion expresion;
	private NodoCase cases;
	private NodoDefault def;
	private NodoInstruccion siguiente;
	private int longitud;
	private int direccion;
	private int longCases;
}
