package space;

import java.io.*;
import alex.AnalizadorLexicoTiny;
import asint.parser;
import parsers.ParserGeneraCodigo;
import parsers.ParserId;
import parsers.ParserTipos;

public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main (String[] args) throws Exception {
		
		if (args.length > 1) {
			try {
				Reader input = new InputStreamReader(new FileInputStream(args[0]));
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				ParserId parserid = new ParserId();
				parser asint = new parser(alex);
				asint.parse();
				input.close();
				parserid.parseTree();
				ParserTipos parsertipos = new ParserTipos(parserid.getTree());
				parsertipos.parseTree();
				Writer output = new OutputStreamWriter(new FileOutputStream(args[1]));
				ParserGeneraCodigo generador = new ParserGeneraCodigo(parserid.getTree());
				generador.parseTree(output);
				output.close();
				System.out.println("Compilación realizada con éxito.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.err.println("Faltan argumentos.");
		}
	}

}
