package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;
import tree.instrucciones.NodoInstruccion;
import tree.instrucciones.NodoReturn;

public class NodoBloqueReturn extends NodoBloque {
	
	public NodoBloqueReturn(NodoInstruccion ins, NodoReturn ret) {
		
		instrucciones = ins;
		nreturn = ret;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		this.tabla = new Hashmap<Nodo>(tabla);
		
		if (instrucciones != null) {
			instrucciones.parseaId(this.tabla);
		}
		
		if (nreturn != null) {
			nreturn.parseaId(this.tabla);
		}
	}
	
	public void parseaIdFunc(Hashmap<Nodo> tabla) throws Exception{
		
		this.tabla = tabla;
		
		if (instrucciones != null) {
			instrucciones.parseaId(this.tabla);
		}
		
		if (nreturn != null) {
			nreturn.parseaId(this.tabla);
		}
	}
	
	public String parseaTipos() throws Exception{
		if (instrucciones != null) {
			instrucciones.parseaTipos();
		}
		
		return nreturn.parseaTipos();
	}
	
	public int reservaMemoria(int dir, int max) throws Exception {
		
		if (instrucciones != null) {
			tamaño = instrucciones.reservaMemoria(dir, max);
			return tamaño;
		}
		tamaño = max;
		return tamaño;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		direccion = dir;
		longitud = 0;

		if (instrucciones != null) {
			longitud += instrucciones.calculaLongDir(dir);
		}
		longitud += nreturn.calculaLongDir(dir + longitud);
		longitud ++;
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = "";
		if (instrucciones != null) {
			s += instrucciones.generaCodigo();
		}
		s += nreturn.generaCodigo();
		s += "{" + (direccion + longitud - 1) + "}str 0 0;\n";
		return s;
	}

	public NodoReturn getNreturn() {
		return nreturn;
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

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setNreturn(NodoReturn nreturn) {
		this.nreturn = nreturn;
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
		
		if(nreturn != null) {
			resultado += guiones + nreturn.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre = "BloqueReturn";
	private NodoReturn nreturn;
	private NodoInstruccion instrucciones;
	private Hashmap<Nodo> tabla;
	private int tamaño;
	private int longitud;
	private int direccion;
}
