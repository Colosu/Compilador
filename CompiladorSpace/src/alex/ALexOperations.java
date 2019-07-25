package alex;

import asint.sym;

public class ALexOperations {
	
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
		fila = 0;
		yychar = 0;
	}
	
	public UnidadLexica unidadSimbolo() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		
		switch (alex.lexema()) {
		case "+": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.MAS,"+");
		}
		case "-": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.MENOS,"-");
		}
		case "*": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.POR,"*");
		}
		case "/": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.DIV,"/");
		}
		case "=": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.IGUAL,"=");
		}
		case ">": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.GT,">");
		}
		case "<": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.LT,"<");
		}
		case ">=": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.GET,">=");
		}
		case "<=": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.LET,"<=");
		}
		case "==": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.EQ,"==");
		}
		case "!=": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.NEQ,"!=");
		}
		case "^": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.POW,"^");
		}
		case "%": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.MOD,"%");
		}
		case "&&": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.AND,"&&");
		}
		case "||": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.OR,"||");
		}
		case "~": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.NOT,"~");
		}
		case "(": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.PARENTESISAPERTURA,"(");
		}
		case ")": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.PARENTESISCIERRE,")");
		}
		case "{\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.LLAVEAPERTURA,"{");
		}
		case "{": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.LLAVEAPERTURA,"{");
		}
		case "}\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.LLAVECIERRE,"}");
		}
		case "}": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.LLAVECIERRE,"}");
		}
		case ";\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.PUNTOCOMA,";");
		}
		case ";": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.PUNTOCOMA,";");
		}
		case ",\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.COMA,",");
		}
		case ",": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.COMA,",");
		}
		case "[": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.CORCHETEAPERTURA,"[");
		}
		case "]": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.CORCHETECIERRE,"]");
		}
		case ":\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.DOSPUNTOS,":");
		}
		case ":": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.DOSPUNTOS,":");
		}
		default: {
			System.out.println("Error, simbolo no reconocido en la línea " + alex.fila() + " y columna " + (alex.yychar() - yychar + 1) + ".");
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.EOF,"<EOF>");
		}
		}
	}
	
	public UnidadLexica unidadEntero() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.INTELEMENT,alex.lexema());
	}
	
	public UnidadLexica unidadReal() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.REALELEMENT,alex.lexema());
	}
	
	public UnidadLexica unidadChar() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.CHARELEMENT,alex.lexema());
	}
	
	public UnidadLexica unidadBool() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.BOOLELEMENT,alex.lexema());
	}
	
	public UnidadLexica unidadPalabrasReservadas() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		switch (alex.lexema()) {
		case "DECLARE\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.DECLARE,"DECLARE");
		}
		case "proc": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.PROC,"proc");
		}
		case "bool": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.BOOL,"bool");
		}
		case "char": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.CHAR,"char");
		}
		case "int": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.INT,"int");
		}
		case "float": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.REAL,"float");
		}
		case "DEFINE\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.DEFINE,"DEFINE");
		}
		case "return": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.RETURN,"return");
		}
		case "START\n": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.START,"START");
		}
		case "END": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.END,"END");
		}
		case "if": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.IF,"if");
		}
		case "else": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.ELSE,"else");
		}
		case "while": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.WHILE,"while");
		}
		case "switch": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.SWITCH,"switch");
		}
		case "case": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.CASE,"case");
		}
		case "default": {
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.DEFAULT,"default");
		}
		default: {
			System.out.println("Error, palabra reservada no reconocida en la línea " + alex.fila() + " y columna " + (alex.yychar() - yychar + 1) + ".");
			return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.EOF,"<EOF>");
		}
		}
	}
	
	public UnidadLexica unidadIdVar() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.ID,alex.lexema());
	}
	
	public UnidadLexica unidadIdFun() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.IDFP,alex.lexema());
	}
	
	public UnidadLexica unidadEof() {
		if (alex.fila() > fila) {
			fila = alex.fila();
			yychar = alex.yychar();
		}
		return new UnidadLexica(alex.fila(),alex.yychar() - yychar + 1,sym.EOF,"<EOF>");
	}
	
	public int getYychar() {
		return yychar;
	}

	private AnalizadorLexicoTiny alex;
	private static int fila;
	private static int yychar;
}