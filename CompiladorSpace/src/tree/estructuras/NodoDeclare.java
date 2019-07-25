package tree.estructuras;

import hashmap.Hashmap;
import tree.Nodo;

public class NodoDeclare extends Nodo {
	
	public NodoDeclare(NodoCabecera Cab) {
		
		Cabecera = Cab;
	}
	
	public void parseaId(Hashmap<Nodo> tabla) throws Exception{
		if (Cabecera != null) {
			Cabecera.parseaId(tabla);
		}
	}

	public NodoCabecera getCabecera() {
		return Cabecera;
	}

	public void setCabecera(NodoCabecera cabecera) {
		Cabecera = cabecera;
	}

	public String toString(int i) {
		String resultado = nombre + "\n";
		String guiones = "";
		for (int j = i; j > 0; j--) {
			guiones += "-";
		}
		
		if(Cabecera != null) {
			resultado += guiones + Cabecera.toString(i+1);
		}
		
		return resultado;
	}
	
	private String nombre = "Declare";
	private NodoCabecera Cabecera;
}
