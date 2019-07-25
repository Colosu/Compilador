package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
	
	public UnidadLexica(int fila, int columna, int clase, String lexema) {
		super(clase,new Token(fila, columna, lexema));
		this.lexema = lexema;
		this.fila = fila;
		this.columna = columna;
	}
	
	public int clase () {
		return sym;
	}
	
	public String lexema() {
		return lexema;
	}
	
	public int fila() {
		return fila;
	}
	
	public int columna() {
		return columna;
	}
	
	private String lexema;
	private int fila;
	private int columna;
}