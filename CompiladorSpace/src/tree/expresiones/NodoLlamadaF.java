package tree.expresiones;

import hashmap.Hashmap;
import tree.Nodo;
import tree.variables.NodoArgumento;

public class NodoLlamadaF extends NodoExp5 {
	
	public NodoLlamadaF(int fila, int columna, String nom, NodoArgumento args) {
		this.fila = fila;
		this.columna = columna;
		nombre = nom;
		argumentos = args;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		Nodo def;
		if ((def = tabla.get(nombre)) == null) {
			throw new Exception("La función " + nombre + " que está en la linea " + fila + " y columna " + columna +" no tiene definición.");
		} else {
			
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
				throw new Exception("El número de argumentos de la función " + nombre + " no coincide entre su uso en la linea " + fila + " y columna " + columna +" y su declaración.");
			}
		}
	}
	
	public String parseaTipos() throws Exception {
			
		NodoArgumento val = argumentos;
		ref = tabla.get(nombre);
		Nodo nom = ref.getArgumentos();
		
		while(nom != null && val != null) {
			if (!val.parseaTipos().equals(nom.getTipo())) {
				throw new Exception("Error de tipos en la llamada a la función " + nombre + " que está en la linea " + fila + " y columna " + columna +".");
			}
			nom = nom.getSiguiente();
			val = val.getSiguiente();
		}
		
		if (nom != null || val != null) {
			throw new Exception("El número de argumentos de la función " + nombre + " no coincide entre su uso en la linea " + fila + " y columna " + columna +" y su declaración.");
		} else {
			return ref.getTipo();
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
		return s;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public NodoArgumento getArgument() {
		return argumentos;
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setArgumentos(NodoArgumento argumentos) {
		this.argumentos = argumentos;
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
		
		return resultado;
	}
	
	private String nombre;
	private String tipo;
	private int direccion;
	private int longitud;
	private NodoArgumento argumentos;
	private Hashmap<Nodo> tabla;
	private Nodo ref;
	private boolean isGlobal;
}
