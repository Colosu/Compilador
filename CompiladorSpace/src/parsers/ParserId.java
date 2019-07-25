package parsers;

import hashmap.Hashmap;
import tree.Nodo;
import tree.NodoArchivo;

public class ParserId {

	public ParserId() {
		m = this;
	}

	public ParserId(NodoArchivo t) {
		m = this;
		tree = t;
	}
	
	public void parseTree() throws Exception {
		Hashmap<Nodo> tabla = new Hashmap<Nodo>();
		tree.parseaId(tabla);
	}
	
	public static void diTree(NodoArchivo t) {
		m.setTree(t);
	}
	
	public void setTree(NodoArchivo t) {
		tree = t;
	}
	
	public NodoArchivo getTree() {
		return tree;
	}

	private NodoArchivo tree;
	private static ParserId m;
}
