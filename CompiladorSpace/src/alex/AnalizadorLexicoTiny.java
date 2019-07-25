package alex;
import errors.GestionErroresTiny;


public class AnalizadorLexicoTiny implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int yychar() {return yychar;}
  public int getLong() {return yy_buffer_index - yy_buffer_start;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoTiny (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoTiny (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoTiny () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NOT_ACCEPT,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NOT_ACCEPT,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NOT_ACCEPT,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NOT_ACCEPT,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"46:9,16,9,46:2,16,46:18,16,5,46:3,1,6,13,1:4,8,1,12,2,11,10:9,8:2,4,3,4,46:" +
"2,29,15,27,25,26,38,15:2,39,15:2,28,15,40,15:3,30,41,42,15:6,1,46,1:2,45,46" +
",22,34,33,44,20,21,14,35,36,14:2,23,14,37,32,31,14,18,24,17,19,14,43,14:3,8" +
",7,8,1,46:65409,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,91,
"0,1:2,2,1,3,4,5,1,6,4,1:2,4,7,8,9,10,11,12,5,7,13,2,14,15,16,1,17,18,1,19,2" +
"0,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,4" +
"5,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,7" +
"0,71,72,73,74,75,76,77,78")[0];

	private int yy_nxt[][] = unpackFromString(79,47,
"1,2,15,23:2,3,16,24,29,4,5,17,30,33,6,7,8,61,83,6,63,75,6:2,85,90,48,7:4,65" +
",6,67,68,6,18,6,7:3,84,7,77,89,30:2,-1:50,2,-1:53,5:2,9,-1:44,6:2,-1:2,6:2," +
"-1,6:29,-1:11,7:2,-1:2,7:2,-1,7:29,-1:11,9:2,-1:36,14:4,41,43,45,46,11,14:3" +
",-1,14:31,-1:4,14,-1:50,2,-1:52,9,-1:44,6:2,-1:2,6:2,-1,6:4,10,6:15,25,6:8," +
"-1:11,7:2,-1:2,7:2,-1,7:8,20,7:20,-1:14,12,-1:40,2,-1:49,6:2,-1:2,6:2,-1,10" +
",6:28,-1:10,27,7:2,-1:2,7:2,-1,7:29,-1:4,22,-1:9,12,-1:42,2,-1:47,6:2,-1:2," +
"6:2,-1,6:3,13,6:25,-1:4,22,-1:44,22:2,28:2,32,35,37,39,22:3,-1:2,22:31,-1:1" +
"2,6:2,-1:2,6:2,-1,6:3,10,6:25,-1:7,22,-1:50,6:2,-1:2,6:2,-1,6:16,10,6:12,-1" +
":8,22,-1:49,6:2,-1:2,6:2,-1,6,10,6:27,-1:10,22,-1:3,12,-1:43,6:2,-1:2,6:2,-" +
"1,6:6,10,6:22,-1:4,14,-1:53,6:2,-1:2,6:2,-1,6:20,10,6:8,-1:7,14,-1:50,6:2,-" +
"1:2,6:2,-1,6:18,10,6:10,-1:8,14,-1:40,14:4,41,43,45,46,21,14:3,-1,14:31,-1:" +
"12,6:2,-1:2,6:2,-1,6:2,31,6:26,-1:11,7:2,-1:2,7:2,-1,7:23,19,7:5,-1:11,6:2," +
"-1:2,6:2,-1,6:7,34,6:21,-1:11,7:2,-1:2,7:2,-1,7:25,26,7:3,-1:11,6:2,-1:2,6:" +
"2,-1,6:15,36,6:13,-1:11,7:2,-1:2,7:2,-1,7:9,26,7:19,-1:11,6:2,-1:2,6:2,-1,6" +
":5,38,6:23,-1:11,6:2,-1:2,6:2,-1,6:15,40,6:13,-1:11,6:2,-1:2,6:2,-1,6:7,31," +
"6:21,-1:11,6:2,-1:2,6:2,-1,6:5,25,6:23,-1:11,6:2,-1:2,6:2,-1,6:6,34,6:22,-1" +
":11,6:2,-1:2,6:2,-1,6,42,6:27,-1:11,6:2,-1:2,6:2,-1,6:16,44,6:12,-1:11,6:2," +
"-1:2,6:2,-1,6:6,25,6:22,-1:11,6:2,-1:2,6:2,-1,6,47,6:27,-1:11,7:2,-1:2,7:2," +
"-1,7:13,50,7:15,-1:11,6:2,-1:2,6:2,-1,6:6,49,6:22,-1:11,7:2,-1:2,7:2,-1,7:2" +
"3,52,7:5,-1:11,6:2,-1:2,6:2,-1,6,51,6:27,-1:11,7:2,-1:2,7:2,-1,7:13,52,7:15" +
",-1:11,6:2,-1:2,6:2,-1,6:5,49,6:12,53,6:10,-1:11,6:2,-1:2,6:2,-1,6:15,54,6:" +
"13,-1:11,6:2,-1:2,6:2,-1,6:6,55,6:22,-1:11,6:2,-1:2,6:2,-1,6:15,56,6:13,-1:" +
"11,6:2,-1:2,6:2,-1,6:19,57,6:9,-1:11,6:2,-1:2,6:2,-1,6:2,58,6:26,-1:11,6:2," +
"-1:2,6:2,-1,59,6:28,-1:11,6:2,-1:2,6:2,-1,6:2,60,6:26,-1:11,6:2,-1:2,6:2,-1" +
",6:5,69,70,6:22,-1:11,7:2,-1:2,7:2,-1,7:12,62,7:16,-1:11,6:2,-1:2,6:2,-1,6:" +
"18,71,6:10,-1:11,7:2,-1:2,7:2,-1,7:22,64,7:6,-1:11,6:2,-1:2,6:2,-1,72,6:28," +
"-1:11,7:2,-1:2,7:2,-1,7:12,66,7:16,-1:11,6:2,-1:2,6:2,-1,6:19,73,6:9,-1:11," +
"6:2,-1:2,6:2,-1,6:5,74,6:23,-1:11,6:2,-1:2,6:2,-1,6:3,79,6:25,-1:11,7:2,-1:" +
"2,7:2,-1,7:25,76,7:3,-1:11,6:2,-1:2,6:2,-1,6:26,81,6:2,-1:11,7:2,-1:2,7:2,-" +
"1,7:10,88,7:10,78,7:7,-1:11,6:2,-1:2,6:2,-1,6:4,82,6:24,-1:11,7:2,-1:2,7:2," +
"-1,7:11,80,7:17,-1:11,6:2,-1:2,6:2,-1,6:3,87,6:25,-1:11,7:2,-1:2,7:2,-1,7:9" +
",86,7:19,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return ops.unidadSimbolo();}
					case -3:
						break;
					case 3:
						{errores.errorLexico(fila(),yychar() - ops.getYychar() + 1,lexema());}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{return ops.unidadEntero();}
					case -6:
						break;
					case 6:
						{return ops.unidadIdVar();}
					case -7:
						break;
					case 7:
						{return ops.unidadIdFun();}
					case -8:
						break;
					case 8:
						{}
					case -9:
						break;
					case 9:
						{return ops.unidadReal();}
					case -10:
						break;
					case 10:
						{return ops.unidadPalabrasReservadas();}
					case -11:
						break;
					case 11:
						{}
					case -12:
						break;
					case 12:
						{return ops.unidadChar();}
					case -13:
						break;
					case 13:
						{return ops.unidadBool();}
					case -14:
						break;
					case 15:
						{return ops.unidadSimbolo();}
					case -15:
						break;
					case 16:
						{errores.errorLexico(fila(),yychar() - ops.getYychar() + 1,lexema());}
					case -16:
						break;
					case 17:
						{return ops.unidadEntero();}
					case -17:
						break;
					case 18:
						{return ops.unidadIdVar();}
					case -18:
						break;
					case 19:
						{return ops.unidadIdFun();}
					case -19:
						break;
					case 20:
						{return ops.unidadPalabrasReservadas();}
					case -20:
						break;
					case 21:
						{}
					case -21:
						break;
					case 23:
						{return ops.unidadSimbolo();}
					case -22:
						break;
					case 24:
						{errores.errorLexico(fila(),yychar() - ops.getYychar() + 1,lexema());}
					case -23:
						break;
					case 25:
						{return ops.unidadIdVar();}
					case -24:
						break;
					case 26:
						{return ops.unidadIdFun();}
					case -25:
						break;
					case 27:
						{return ops.unidadPalabrasReservadas();}
					case -26:
						break;
					case 29:
						{return ops.unidadSimbolo();}
					case -27:
						break;
					case 30:
						{errores.errorLexico(fila(),yychar() - ops.getYychar() + 1,lexema());}
					case -28:
						break;
					case 31:
						{return ops.unidadIdVar();}
					case -29:
						break;
					case 33:
						{errores.errorLexico(fila(),yychar() - ops.getYychar() + 1,lexema());}
					case -30:
						break;
					case 34:
						{return ops.unidadIdVar();}
					case -31:
						break;
					case 36:
						{return ops.unidadIdVar();}
					case -32:
						break;
					case 38:
						{return ops.unidadIdVar();}
					case -33:
						break;
					case 40:
						{return ops.unidadIdVar();}
					case -34:
						break;
					case 42:
						{return ops.unidadIdVar();}
					case -35:
						break;
					case 44:
						{return ops.unidadIdVar();}
					case -36:
						break;
					case 47:
						{return ops.unidadIdVar();}
					case -37:
						break;
					case 48:
						{return ops.unidadIdFun();}
					case -38:
						break;
					case 49:
						{return ops.unidadIdVar();}
					case -39:
						break;
					case 50:
						{return ops.unidadIdFun();}
					case -40:
						break;
					case 51:
						{return ops.unidadIdVar();}
					case -41:
						break;
					case 52:
						{return ops.unidadIdFun();}
					case -42:
						break;
					case 53:
						{return ops.unidadIdVar();}
					case -43:
						break;
					case 54:
						{return ops.unidadIdVar();}
					case -44:
						break;
					case 55:
						{return ops.unidadIdVar();}
					case -45:
						break;
					case 56:
						{return ops.unidadIdVar();}
					case -46:
						break;
					case 57:
						{return ops.unidadIdVar();}
					case -47:
						break;
					case 58:
						{return ops.unidadIdVar();}
					case -48:
						break;
					case 59:
						{return ops.unidadIdVar();}
					case -49:
						break;
					case 60:
						{return ops.unidadIdVar();}
					case -50:
						break;
					case 61:
						{return ops.unidadIdVar();}
					case -51:
						break;
					case 62:
						{return ops.unidadIdFun();}
					case -52:
						break;
					case 63:
						{return ops.unidadIdVar();}
					case -53:
						break;
					case 64:
						{return ops.unidadIdFun();}
					case -54:
						break;
					case 65:
						{return ops.unidadIdVar();}
					case -55:
						break;
					case 66:
						{return ops.unidadIdFun();}
					case -56:
						break;
					case 67:
						{return ops.unidadIdVar();}
					case -57:
						break;
					case 68:
						{return ops.unidadIdVar();}
					case -58:
						break;
					case 69:
						{return ops.unidadIdVar();}
					case -59:
						break;
					case 70:
						{return ops.unidadIdVar();}
					case -60:
						break;
					case 71:
						{return ops.unidadIdVar();}
					case -61:
						break;
					case 72:
						{return ops.unidadIdVar();}
					case -62:
						break;
					case 73:
						{return ops.unidadIdVar();}
					case -63:
						break;
					case 74:
						{return ops.unidadIdVar();}
					case -64:
						break;
					case 75:
						{return ops.unidadIdVar();}
					case -65:
						break;
					case 76:
						{return ops.unidadIdFun();}
					case -66:
						break;
					case 77:
						{return ops.unidadIdVar();}
					case -67:
						break;
					case 78:
						{return ops.unidadIdFun();}
					case -68:
						break;
					case 79:
						{return ops.unidadIdVar();}
					case -69:
						break;
					case 80:
						{return ops.unidadIdFun();}
					case -70:
						break;
					case 81:
						{return ops.unidadIdVar();}
					case -71:
						break;
					case 82:
						{return ops.unidadIdVar();}
					case -72:
						break;
					case 83:
						{return ops.unidadIdVar();}
					case -73:
						break;
					case 84:
						{return ops.unidadIdFun();}
					case -74:
						break;
					case 85:
						{return ops.unidadIdVar();}
					case -75:
						break;
					case 86:
						{return ops.unidadIdFun();}
					case -76:
						break;
					case 87:
						{return ops.unidadIdVar();}
					case -77:
						break;
					case 88:
						{return ops.unidadIdFun();}
					case -78:
						break;
					case 89:
						{return ops.unidadIdVar();}
					case -79:
						break;
					case 90:
						{return ops.unidadIdFun();}
					case -80:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
