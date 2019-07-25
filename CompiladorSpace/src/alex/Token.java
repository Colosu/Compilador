package alex;

public class Token extends Object {
	
	public Token(int fila, int columna, String lexema) {
		this.lexema = lexema;
		this.fila = fila;
		this.columna = columna;
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