package tree.variables;

import hashmap.Hashmap;
import tree.Nodo;
import tree.expresiones.*;

public class NodoDim extends NodoIdVar {
	
	public NodoDim(int fila, int columna, NodoExpresion ind) {

		this.fila = fila;
		this.columna = columna;
		indice = ind;
	}
	
	public NodoDim(int fila, int columna, NodoExpresion ind, NodoIdVar id) {

		this.fila = fila;
		this.columna = columna;
		indice = ind;
		idVar = id;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
		if (indice != null) {
			indice.parseaId(tabla);
		} else {
			throw new Exception("Dimensión sin expresión en la linea " + fila + " y columna " + columna +".");
		}
			
		if (idVar != null) {
			idVar.parseaId(tabla);
		} else {
			throw new Exception("Dimensión sin variable en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public String parseaTipos() throws Exception {
		return parseaTipos(0);
	}
	
	public int calculaLongDir(int dir, int anid) throws Exception {
		direccion = dir;
		longitud = idVar.calculaLongDir(dir, anid + 1);
		longitud += indice.calculaLongDir(dir + longitud);
		longitud += 2;
		if (anid == 0) {
			longitud++;
		}
		return longitud;
	}
	
	public int calculaLongDirIzq(int dir) throws Exception {
		direccion = dir;
		longitud = idVar.calculaLongDirIzq(dir);
		longitud += indice.calculaLongDir(dir + longitud);
		longitud += 2;
		return longitud;
	}
	
	public String generaCodigo(int g, int d, int anid) throws Exception {
		int tamDim = getDim(0); //D_j;
		if (anid == 0) {
			g = getSizeT(0);
		}
		String s = idVar.generaCodigo(g, d*tamDim, anid + 1);
		s += indice.generaCodigo();
		s += "{" + (direccion + idVar.getLongitud() + indice.getLongitud()) + "}chk 0 " + (tamDim - 1) + ";\n";
		s += "{" + (direccion + idVar.getLongitud() + indice.getLongitud() + 1) + "}ixa " + (g*d) + ";\n";
		if (anid == 0) {
			s += "{" + (direccion + idVar.getLongitud() + indice.getLongitud() + 2) + "}ind;\n";
		}
		return s;
	}
	
	public String generaCodigoIzq(int g, int d, int anid) throws Exception {
		int tamDim = getDim(0); //D_j;
		if (anid == 0) {
			g = getSizeT(0);
		}
		String s = idVar.generaCodigoIzq(g, d*tamDim, anid + 1);
		s += indice.generaCodigo();
		s += "{" + (direccion + idVar.getLongitud() + indice.getLongitud()) + "}chk 0 " + (tamDim - 1) + ";\n";
		s += "{" + (direccion + idVar.getLongitud() + indice.getLongitud() + 1) + "}ixa " + (g*d) + ";\n";
		return s;
	}
	
	public boolean isDim() {
		return true;
	}
	
	public NodoExpresion getExpresion() {
		return indice;
	}
	public NodoIdVar getIdVar() {
		return idVar;
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

	public void setSizeT(int sizeT) {
		this.sizeT = sizeT;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setIndice(NodoExpresion indice) {
		this.indice = indice;
	}
	public void setIdVar(NodoIdVar idVar) {
		if (this.idVar == null) {
			this.idVar = idVar;
		} else {
			this.idVar.setIdVar(idVar);
		}
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(indice != null) {
			resultado += guiones + indice.toString(i+1);
		}
		
		if(idVar != null) {
			resultado += guiones + idVar.toString(i+1);
		}
		
		return resultado;
	}
	
	public String parseaTipos(int i) throws Exception{
		
		if (indice != null && indice.parseaTipos().equals("int")) {
			if(idVar != null) {
				return idVar.parseaTipos(i+1);
			} else {
				throw new Exception("Dimensión sin variable en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("Error de tipos en el acceso al array que está en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public int getSizeT(int i) throws Exception{
		
		if (indice != null && indice.parseaTipos().equals("int")) {
			if(idVar != null) {
				sizeT = idVar.getSizeT(i+1);
				return sizeT;
			} else {
				throw new Exception("Dimensión sin variable en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("Error de tipos en el acceso al array que está en la linea " + fila + " y columna " + columna +".");
		}
	}
	
	public int getDim(int i) throws Exception{
		
		if (indice != null && indice.parseaTipos().equals("int")) {
			if(idVar != null) {
				return idVar.getDim(i+1);
			} else {
				throw new Exception("Dimensión sin variable en la linea " + fila + " y columna " + columna +".");
			}
		} else {
			throw new Exception("Error de tipos en el acceso al array que está en la linea " + fila + " y columna " + columna +".");
		}
	}

	private String nombre = "Dim";
	private NodoExpresion indice;
	private NodoIdVar idVar;
	private int longitud;
	private int direccion;
	private int sizeT;
}
