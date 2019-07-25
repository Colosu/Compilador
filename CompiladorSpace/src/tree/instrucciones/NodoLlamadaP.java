package tree.instrucciones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.variables.NodoArgumento;

public class NodoLlamadaP extends NodoInstruccion {
	
	public NodoLlamadaP(int fila, int columna, String nom, NodoArgumento args) {
		this.fila = fila;
		this.columna = columna;
		nombre = nom;
		argumentos = args;
	}
	
	public NodoLlamadaP(int fila, int columna, String nom, NodoArgumento args, NodoInstruccion sig) {
		this.fila = fila;
		this.columna = columna;
		nombre = nom;
		argumentos = args;
		siguiente = sig;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		Nodo def;
		if ((def = tabla.get(nombre)) == null) {
			throw new Exception("El procedimiento " + nombre + " que está en la linea " + fila + " y columna " + columna +" no tiene definición.");
		} else {
			if(def.getTipo() != null) {
				throw new Exception("ERROR fila " + fila + " columna " + columna +": Elemento inesperado llamada a función.");
			}
			
			this.tabla = tabla;
			this.isGlobal = tabla.isGlobal();
			
			NodoArgumento val = argumentos;
			Nodo nom = def.getArgumentos();
			
			while(nom != null && val != null) {
				val.parseaId(tabla);
				nom = nom.getSiguiente();
				val = val.getSiguiente();
			}
			
			if (nom != null || val != null) {
				throw new Exception("El número de argumentos del procedimiento " + nombre + " no coincide entre su uso en la linea " + fila + " y columna " + columna +" y su declaración.");
			} else {
			
				if (siguiente != null) {
					siguiente.parseaId(tabla);
				}
			}
		}
	}
	
	public String parseaTipos() throws Exception {
			
		NodoArgumento val = argumentos;
		ref = tabla.get(nombre);
		Nodo nom = ref.getArgumentos();
		
		while(nom != null && val != null) {
			if (val.parseaTipos() != nom.getTipo()) {
				throw new Exception("Error de tipos en la llamada al procedimiento " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
			}
			nom = nom.getSiguiente();
			val = val.getSiguiente();
		}
		
		if (nom != null || val != null) {
			throw new Exception("El número de argumentos del procedimiento " + nombre + " no coincide entre su uso en la linea " + fila + " y columna " + columna +" y su declaración.");
		} else {
			
			if (siguiente != null) {
			siguiente.parseaTipos();
			}
			return "OK";
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		if (siguiente != null) {
			return siguiente.reservaMemoria(dir, max);
		} else {
			return max;
		}
	}
	
	public int calculaLongDir(int dir) throws Exception {
		longitud = 1;
		direccion = dir;
		NodoArgumento arg = argumentos;
		while (arg != null) {
			if (arg.getExpresion().isDim() && arg.getExpresion().getSizeT() > 1) {
				longitud += arg.getExpresion().calculaLongDirIzq(dir + longitud);
				longitud++;
			} else {
				longitud += arg.getExpresion().calculaLongDir(dir + longitud);
			}
			arg = arg.getSiguiente();
		}
		longitud++;
		if (siguiente != null) {
			return longitud + siguiente.calculaLongDir(dir + longitud);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = "";
		if(isGlobal) {
			s += "{" + direccion + "}mst 0;\n";	
		} else {
			s += "{" + direccion + "}mst 1;\n";
		}
		NodoArgumento arg = argumentos;
		int l = 0;
		while (arg != null) {
			l += arg.getExpresion().getLongitud();
			if (arg.getExpresion().isDim() && arg.getExpresion().getSizeT() > 1) {
				s += arg.getExpresion().generaCodigoIzq();
				s += "{" + (direccion + l + 1) + "}movs " + arg.getExpresion().getSizeT() + ";\n";
				l++;
			} else {
				s += arg.getExpresion().generaCodigo();
			}
			arg = arg.getSiguiente();
		}
		
		s += "{" + (direccion + longitud - 1) + "}cup " + ref.getTamañoArgs() + " " + ref.getDireccion() + ";\n";
		
		if (siguiente != null) {
			s += siguiente.generaCodigo();	
		}
		return s;
	}

	public String getNombre() {
		return nombre;
	}

	public NodoArgumento getArgument() {
		return argumentos;
	}

	public NodoInstruccion getSiguiente() {
		return siguiente;
	}

	public int getDireccion() {
		return direccion;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setArgumentos(NodoArgumento argumentos) {
		this.argumentos = argumentos;
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
	private NodoArgumento argumentos;
	private NodoInstruccion siguiente;
	private Nodo ref;
	private Hashmap<Nodo> tabla;
	private int longitud;
	private int direccion;
	private boolean isGlobal;
}
