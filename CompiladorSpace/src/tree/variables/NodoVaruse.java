package tree.variables;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoVaruse extends NodoIdVar {
	
	public NodoVaruse(int fila, int columna, String nom) {

		this.fila = fila;
		this.columna = columna;
		nombre = nom;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		Nodo var;
		if ((var = tabla.get(nombre)) == null) {
			throw new Exception("La variable " + nombre + " que está en la linea " + fila + " y columna " + columna +" no ha sido declarada.");
		} else {
			tipo = var.getTipo();
			ref = var;
		}
	}
	
	public String parseaTipos() throws Exception{
		return tipo;
	}
	
	public int reservaMemoria(int dir) throws Exception {
		return 0;
	}
	
	public int calculaLongDir(int dir, int anid) throws Exception {
		longitud = 1;
		if (anid == 0) {
			longitud++;
		}
		direccion = dir;
		return longitud;
	}
	
	public int calculaLongDirIzq(int dir) throws Exception {
		longitud = 1;
		direccion = dir;
		return longitud;
	}
	
	public String generaCodigo(int g, int d, int anid) throws Exception {
		int p = ref.getDireccion();
		String s = "";
		if(ref.isGlobal()) {
			s = "{" + direccion + "}ldc " + p + ";\n";
		} else {
			s = "{" + direccion + "}lda 0 " + p + ";\n";
		}
		if (anid == 0) {
			s += "{" + (direccion + 1) + "}ind;\n";	
		}
		return s;
	}
	
	public String generaCodigoIzq(int g, int d, int anid) throws Exception {
		int p = ref.getDireccion();
		String s = "";
		if(ref.isGlobal()) {
			s = "{" + direccion + "}ldc " + p + ";\n";
		} else {
			s = "{" + direccion + "}lda 0 " + p + ";\n";
		}
		return s;
	}
	
	public boolean isDim() {
		if (ref.getTamaño() > 1) {
			return true;
		} else {
			return false;
		}
	}

	public String getTipo() {
		return tipo;
	}

	public int getLongitud() {
		return longitud;
	}

	public int getDireccion() {
		return direccion;
	}

	public int getSizeT() throws Exception {
		if (sizeT == 0) {
			sizeT = getSizeT(0);
		}
		return sizeT;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString(int i) {
		return nombre + "\n";
	}
	
	public String parseaTipos(int i) throws Exception{
		String solucion = "";
		int j = 0;
		char c;
		boolean copiar = true;
		while(j < tipo.length() && i > 0) {
			if (copiar) {
				c = tipo.charAt(j);
				if (c == '[') {
					copiar = false;
				} else {
					solucion += c;
				}
			} else {
				c = tipo.charAt(j);
				if (c == ']') {
					copiar = true;
					i--;
				}
			}
			j++;
		}
		for (int h = j; h < tipo.length(); h++) {
			solucion += tipo.charAt(h);
		}
		return solucion;
	}
	
	public int getSizeT(int i) throws Exception{
		
		Nodo n = ref;
		int tamaño = n.getTamaño();
		n = n.getDims();
		while (i > 0) {
			tamaño /= n.getIndice();
			n = n.getSiguiente();
			i--;
		}
		return tamaño;
	}
	
	public int getDim(int i) throws Exception{
		
		Nodo n = ref;
		n = n.getDims();
		while (i > 1) {
			n = n.getSiguiente();
			i--;
		}
		return n.getIndice();
	}
	
	private String tipo;
	private String nombre;
	private Nodo ref;
	private int longitud;
	private int direccion;
	private int sizeT;
}
