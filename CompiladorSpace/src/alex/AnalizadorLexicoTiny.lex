package alex;

import errors.GestionErroresTiny;

%%
%cup
%char
%line
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int yychar() {return yychar;}
  public int getLong() {return yy_buffer_index - yy_buffer_start;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

simbolo = ("+"|"-"|"*"|"/"|"="|">"|"<"|">""="|"<""="|"=""="|"!""="|"^"|"%"|"&""&"|"|""|"|"~"|"("|")"|"{"\n|"{"|"}"\n|"}"|";"\n|";"|","\n|","|"["|"]"|":"\n|":")
letraMin = [a-z]
letraMay = [A-Z]
letra  = ({letraMin}|{letraMay})
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEnt = {digitoPositivo}{digito}*
parteDec = {digito}*
int = ({parteEnt}|0)
real = ({int}|{int}"."{parteDec})
char = "'"({letra}|{digito}|{simbolo}|{separador}|\n)"'"
bool = (true|false)
palabrasReservadas = (DECLARE\n|proc|bool|char|int|float|DEFINE\n|return|START\n|END|if|else|while|switch|case|default)
separador = [" "\t\r]
saltodelinea = [\n]
comentario = "/""/"({letra}|{digito}|{simbolo}|{separador}|".")*{saltodelinea}
idVar = {letraMin}({digito}|{letra}|"_")*
idFun = {letraMay}({digito}|{letra}|"_")*
%%
{simbolo}				{return ops.unidadSimbolo();}
{int}					{return ops.unidadEntero();}
{real}					{return ops.unidadReal();}
{char}					{return ops.unidadChar();}
{bool}					{return ops.unidadBool();}
{palabrasReservadas}	{return ops.unidadPalabrasReservadas();}
{separador}             {}
{saltodelinea}			{}
{comentario}            {}
{idVar}					{return ops.unidadIdVar();}
{idFun}					{return ops.unidadIdFun();}
[^]						{errores.errorLexico(fila(),yychar() - ops.getYychar() + 1,lexema());}