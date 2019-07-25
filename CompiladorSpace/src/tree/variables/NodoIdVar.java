package tree.variables;

import hashmap.Hashmap;
import tree.Nodo;
import tree.expresiones.NodoExp5;

public abstract class NodoIdVar extends NodoExp5 {
	
	public NodoIdVar() {
		
	}
	
	public NodoIdVar(String nom) {
		nombre = nom;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		
	}
	
	public String parseaTipos() throws Exception{
		return null;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		return calculaLongDir(dir, 0);
	}
	
	public int calculaLongDir(int dir, int anid) throws Exception {
		return 0;
	}
	
	public int calculaLongDirIzq(int dir) throws Exception {
		return 0;
	}
	
	public String generaCodigo() throws Exception {
		return generaCodigo(0, 1, 0);
	}
	
	public String generaCodigo(int g, int d, int anid) throws Exception {
		return null;
	}
	
	public String generaCodigoIzq() throws Exception {
		return generaCodigoIzq(0, 1, 0);
	}
	
	public String generaCodigoIzq(int g, int d, int anid) throws Exception {
		return null;
	}
	
	public boolean isDim() {
		return false;
	}

	public int getSizeT() throws Exception {
		return 1;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setIdVar(NodoIdVar idVar) {
		
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		
		return resultado;
	}
	
	public String parseaTipos(int i) throws Exception{
		return null;
	}
	
	public int getSizeT(int i) throws Exception{
		return 0;
	}
	
	public int getDim(int i) throws Exception{
		return 0;
	}

	private String nombre;
}
