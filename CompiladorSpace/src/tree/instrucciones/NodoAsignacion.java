package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.expresiones.NodoExpresion;
import tree.variables.NodoIdVar;;

public class NodoAsignacion extends NodoInstruccion {
	
	public NodoAsignacion(int fila, int columna, NodoIdVar var, NodoExpresion exp) {
		this.fila = fila;
		this.columna = columna;
		variable = var;
		expresion = exp;
	}
	
	public NodoAsignacion(int fila, int columna, NodoIdVar var, NodoExpresion exp, NodoInstruccion sig) {
		this.fila = fila;
		this.columna = columna;
		variable = var;
		expresion = exp;
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (variable != null) {
			variable.parseaId(tabla);
		} else {
			throw new Exception("La asignación que está en la linea " + fila + " y columna " + columna +" no tiene variable.");
		}
		
		if (expresion != null) {
			expresion.parseaId(tabla);
		} else {
			throw new Exception("La asignación que está en la linea " + fila + " y columna " + columna +" no tiene expresión.");
		}
		
		if (siguiente != null) {
			siguiente.parseaId(tabla);
		}
	}
	
	public String parseaTipos() throws Exception {

		if (variable != null) {
			if (variable.parseaTipos().equals(expresion.parseaTipos())) {

				if (siguiente != null) {
					siguiente.parseaTipos();
				}
				return "OK";
			} else {
				throw new Exception("Error de tipos en la asignación que está en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("La asignación que está en la linea " + fila + " y columna " + columna +" no tiene variable.");
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (variable != null) {
			if (siguiente != null) {
				return siguiente.reservaMemoria(dir, max);
			} else {
				return max;
			}
		} else {
			throw new Exception("La asignación que está en la linea " + fila + " y columna " + columna +" no tiene variable.");
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = variable.calculaLongDirIzq(dir);
		longitud += expresion.calculaLongDir(dir + longitud);
		longitud++;
		if (siguiente != null) {
			return longitud + siguiente.calculaLongDir(dir + longitud);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = variable.generaCodigoIzq(0, 1, 0);
		s += expresion.generaCodigo();
		s += "{" + (direccion + variable.getLongitud() + expresion.getLongitud()) + "}sto;\n";
		if (siguiente != null) {
			s += siguiente.generaCodigo();
		}
		return s;
	}

	public NodoInstruccion getSiguiente() {
		return siguiente;
	}

	public NodoIdVar getVariable() {
		return variable;
	}

	public NodoExpresion getExpresion() {
		return expresion;
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

	public void setVariable(NodoIdVar variable) {
		this.variable = variable;
	}

	public void setExpresion(NodoExpresion expresion) {
		this.expresion = expresion;
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
		
		if(variable != null) {
			resultado += guiones + variable.toString(i+1);
		}
		
		if(expresion != null) {
			resultado += guiones + expresion.toString(i+1);
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
	
	private String nombre = "Asignación";
	private NodoIdVar variable;
	private NodoExpresion expresion;
	private NodoInstruccion siguiente;
	private int longitud;
	private int direccion;
}
