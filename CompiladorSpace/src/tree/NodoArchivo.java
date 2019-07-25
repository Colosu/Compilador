package tree;

import hashmap.Hashmap;
import tree.estructuras.*;

public class NodoArchivo extends Nodo {
	
	public NodoArchivo(NodoDeclare Dec, NodoDefine Def, NodoBloque Ma) {
		
		Declare = Dec;
		Define = Def;
		Main = Ma;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		if (Declare != null) {
			Declare.parseaId(tabla);
		}
		if (Define != null) {
			Define.parseaId(tabla);
		}
		if (Main != null) {
			Main.parseaId(tabla);
		}
		this.tabla = tabla;
	}
	
	public String parseaTipos() throws Exception {

		if (Define != null && Main != null) {
			if (Define.parseaTipos().equals("OK") && Main.parseaTipos().equals("OK")) {
				return "OK";
			}
		}
		return "OK";
	}
	
	public int reservaMemoria(int dir) throws Exception {
		if (Define != null && Main != null) {
			Define.reservaMemoria(dir);
			tamaño = Main.reservaMemoria(dir, dir);
			return tamaño;
		}
		return 0;
	}
	
	public int calculaLongDir(int dir) throws Exception {
		longitud = 1;

		if (Main != null) {
			longitud += Main.calculaLongDir(dir + longitud);
		}
		longitud ++;
		
		if (Define != null) {
			Define.calculaLongDir(dir + longitud);
		}
		
		return longitud;
	}
	
	public String generaCodigo() throws Exception {
		String s = "{0}ssp " + tamaño + ";\n";
		
		if (Main != null) {
			s += Main.generaCodigo();
		}
		s += "{" + (1 + Main.getLongitud()) + "}stp;\n";
		
		if (Define != null) {
			s += Define.generaCodigo();
		}
		return s;
		
	}

	public NodoDeclare getDeclare() {
		return Declare;
	}

	public NodoDefine getDefine() {
		return Define;
	}
	
	public NodoBloque getMain() {
		
		return Main;
	}

	public Hashmap<Nodo> getTabla() {
		return tabla;
	}

	public int getTamaño() {
		return tamaño;
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

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public void setTabla(Hashmap<Nodo> tabla) {
		this.tabla = tabla;
	}

	public void setDeclare(NodoDeclare declare) {
		Declare = declare;
	}

	public void setDefine(NodoDefine define) {
		Define = define;
	}

	public void setMain(NodoBloque main) {
		Main = main;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(Declare != null) {
			resultado += guiones + Declare.toString(i+1);
		}
		if(Define != null) {
			resultado += guiones + Define.toString(i+1);
		}
		if(Main != null) {
			resultado += guiones + Main.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre = "Archivo";
	private int tamaño;
	private NodoDeclare Declare;
	private NodoDefine Define;
	private NodoBloque Main;
	private Hashmap<Nodo> tabla;
	private int longitud;
	private int direccion;
}
