package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;
import tree.instrucciones.NodoInstruccion;

public class NodoBloque extends Nodo {
	
	public NodoBloque() {
		
	}
	
	public NodoBloque(NodoInstruccion ins) {
		
		instrucciones = ins;
	}
	
	public NodoBloque(NodoInstruccion ins, boolean isGlobal) {
		
		instrucciones = ins;
		this.isGlobal = isGlobal;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		this.tabla = new Hashmap<Nodo>(tabla);
		this.tabla.setGlobal(isGlobal);
		
		if (instrucciones != null) {
			instrucciones.parseaId(this.tabla);
		}
	}
	
	public void parseaIdProc(Hashmap<Nodo> tabla) throws Exception{
		
		this.tabla = tabla;
		
		if (instrucciones != null) {
			instrucciones.parseaId(this.tabla);
		}
	}
	
	public String parseaTipos() throws Exception{
		
		if (instrucciones != null) {
			return instrucciones.parseaTipos();
		} else {
			return "OK";
		}
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		
		if (instrucciones != null) {
			tamaño = instrucciones.reservaMemoria(dir, max);
			return tamaño;
		}
		return max;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = 0;

		if (instrucciones != null) {
			longitud += instrucciones.calculaLongDir(dir);
		}
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = "";
		if (instrucciones != null) {
			s += instrucciones.generaCodigo();
		}
		return s;
	}

	public NodoInstruccion getInstrucciones() {
		return instrucciones;
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

	public void setInstrucciones(NodoInstruccion instrucciones) {
		this.instrucciones = instrucciones;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(instrucciones != null) {
			resultado += guiones + instrucciones.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre = "Bloque";
	private NodoInstruccion instrucciones;
	private Hashmap<Nodo> tabla;
	private boolean isGlobal = false;
	private int tamaño;//memoria
	private int longitud;//numero de lineas de codigo
	private int direccion;
}
