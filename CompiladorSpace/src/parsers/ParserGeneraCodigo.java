package parsers;

import java.io.Writer;
import tree.NodoArchivo;

public class ParserGeneraCodigo {

	public ParserGeneraCodigo(NodoArchivo t) {
		tree = t;
	}
	
	public void parseTree(Writer output) throws Exception {
		tree.reservaMemoria(5);
		tree.calculaLongDir(0);
		output.write(tree.generaCodigo());
		output.flush();
	}
	
	private NodoArchivo tree;
}
