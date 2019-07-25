package parsers;

import tree.NodoArchivo;

public class ParserTipos {

	public ParserTipos(NodoArchivo t) {
		tree = t;
	}
	
	public void parseTree() throws Exception {
		tree.parseaTipos();
	}
	
	private NodoArchivo tree;
}
