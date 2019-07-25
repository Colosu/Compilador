package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;
import tree.variables.NodoInsertaNombreArg;

public class NodoImplementacionF extends NodoImplementacion {
	
	public NodoImplementacionF(String id, int fila, int columna, NodoBloqueReturn bloq, NodoInsertaNombreArg Arg) {
		
		nombre = id;
		this.fila = fila;
		this.columna = columna;
		bloque = bloq;
		argumentos = Arg;
	}
	
	public NodoImplementacionF(String id, int fila, int columna, NodoBloqueReturn bloq, NodoInsertaNombreArg Arg, NodoImplementacion Cab) {
		
		nombre = id;
		this.fila = fila;
		this.columna = columna;
		bloque = bloq;
		argumentos = Arg;
		siguiente = Cab;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		Nodo dec;
		if ((dec = tabla.get(nombre)) == null) {
			throw new Exception("La función " + nombre + " implementada en la linea " + fila + " y columna " + columna +" no tiene declaración.");
		} else if (dec.getBloque() != null) {
			throw new Exception("La función " + nombre + " implementada en la linea " + fila + " y columna " + columna +" ya está declarada.");
		} else {
			this.tabla = new Hashmap<Nodo>(tabla);
			tipo = dec.getTipo();
			
			NodoInsertaNombreArg nom = argumentos;
			Nodo tipo = dec.getArgumentos();
			tabla.put(nombre, this);
			
			while(tipo != null && nom != null) {
				
				nom.setTipo(tipo.getTipo());
				nom.setTamaño(tipo.getTamaño());
				nom.setDims(tipo.getDims());
				if (this.tabla.putIfAbsent(nom.getNombre(), nom) != null) {
					throw new Exception("La función " + nombre + " tiene argumentos con el mismo nombre en la linea " + nom.getFila() + " y columna " + nom.getColumna() +".");
				} else {
					tipo = tipo.getSiguiente();
					nom = nom.getSiguiente();
				}
			}
			
			if (tipo != null || nom != null) {
				throw new Exception("El número de argumentos de la función " + nombre + " no coincide entre declaración y definición en la linea " + fila + " y columna " + columna +".");
			} else {
				
				if (bloque != null) {
					bloque.parseaIdFunc(this.tabla);
				}
				
				dec.setDefinicion(bloque);
			
				if (siguiente != null) {
					siguiente.parseaId(tabla);
				}
			}
		}
	}
	
	public String parseaTipos() throws Exception {

		if (bloque != null && bloque.getNreturn() != null) {
			if (bloque.parseaTipos().equals(tipo)) {

				if (siguiente != null) {
					siguiente.parseaTipos();
				}
				return "OK";
			} else {
				throw new Exception("La función " + nombre + " devuelve un tipo erróneo en la linea " + bloque.getNreturn().getFila() + " y columna " + bloque.getNreturn().getColumna() +".");
			}
		} else {
			throw new Exception("Función sin return en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public int reservaMemoria(int dir) throws Exception {

		tamañoArgs = 0;
		tamaño = 0;
		NodoInsertaNombreArg arg = argumentos;
		int tam = 0;
		int direccion = 5;
		while (arg != null) {
			tam = arg.getTamaño();
			arg.setDireccion(direccion);
			direccion += tam;
			tamañoArgs += tam;
			arg = arg.getSiguiente();
		}
		
		if (bloque != null) {
			tamaño += bloque.reservaMemoria(direccion, direccion);
		}
		
		if (siguiente != null) {
			return tamañoArgs + tamaño + siguiente.reservaMemoria(dir);
		}
		
		return tamañoArgs + tamaño;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		longitud = 1;
		direccion = dir;
		longitud += 1 + bloque.calculaLongDir(dir + longitud);
		if (siguiente != null) {
			return longitud + siguiente.calculaLongDir(dir + longitud);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = "{" + direccion + "}ssp " + tamaño + ";\n";
		s += bloque.generaCodigo();
		s += "{" + (direccion + 1 + bloque.getLongitud()) + "}retf;\n";
		if (siguiente != null) {
			s += siguiente.generaCodigo();	
		}
		return s;
	}
	
	public String getNombre() {
		return nombre;
	}

	public NodoInsertaNombreArg getArgumentos() {
		return argumentos;
	}

	public NodoImplementacion getSiguiente() {
		return siguiente;
	}

	public NodoBloqueReturn getBloque() {
		return bloque;
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

	public int getLongitud() {
		return longitud;
	}

	public int getTamañoArgs() {
		return tamañoArgs;
	}

	public void setTamañoArgs(int tamañoArgs) {
		this.tamañoArgs = tamañoArgs;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
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

	public void setBloque(NodoBloqueReturn bloque) {
		this.bloque = bloque;
	}

	public void setArgumentos(NodoInsertaNombreArg argumentos) {
		this.argumentos = argumentos;
	}

	public void setSiguiente(NodoImplementacion siguiente) {
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
	
	private String nombre;
	private String tipo;
	private int tamañoArgs;
	private int tamaño;
	private int direccion;
	private int longitud;
	private NodoBloqueReturn bloque;
	private NodoInsertaNombreArg argumentos;
	private NodoImplementacion siguiente;
	private Hashmap<Nodo> tabla;
}
