package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, int columna, String lexema) {
     System.out.println("ERROR fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado "+unidadLexica.lexema());
     System.exit(1);
   }
}
