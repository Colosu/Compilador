{
module Lexer
   (
-----------------------------------------------------------------------------
-- |
-- Module      :  Lexer
-- Copyright   :  (c) Ricardo Peña, Jun. 2014, Universidad Complutense de Madrid
--                Lexer for the P-code input file
-- License     :  LGPL
--
-- Maintainer  :  ricardo@sip.ucm.es
-- Stability   :  provisional
-- Portability :  portable
--
--  It provides a lexical analiser for the P-machine code
--
--     . It expects a text file having a sequence of P-instructions,
--       each one ended by a ';'
--     . It returns a list of tokens
--
-----------------------------------------------------------------------------
--
   -- * main function String -> [Token]
   lexer,
   -- * Data token
   Token(..),
   )   where

}

%wrapper "posn"

$digit = 0-9
$lower = [a-z]
$upper = [A-Z]

tokens :-
   $white+                   ;                             -- white space
   \\ \\ .*                  ;                             -- line comment
   \{ ([\x00-\xff]#[\}])* \} ;                             -- block comment
   (\-?)$digit+        { \p s -> Num ((read s), p)}
   \;             { \p s -> SemiColon p}

-- Load and store

   ldo           { \p s -> Ldo  p }
   ldc           { \p s -> Ldc  p }
   ind           { \p s -> Ind  p }
   sro           { \p s -> Sro  p }
   sto           { \p s -> Sto  p }
   lod           { \p s -> Lod  p }
   lda           { \p s -> Lda  p }
   str           { \p s -> Str  p }

-- Arithmetic and logical ones

   add           { \p s -> Add  p }
   sub           { \p s -> Sub  p }
   mul           { \p s -> Mul  p }
   div           { \p s -> Div  p }
   mod           { \p s -> Mod  p }   
   pow           { \p s -> Pow  p }
   neg           { \p s -> Neg  p }
   and           { \p s -> And  p }
   or            { \p s -> Or   p }
   not           { \p s -> Not  p }
   equ           { \p s -> Equ  p }
   geq           { \p s -> Geq  p }
   leq           { \p s -> Leq  p }
   les           { \p s -> Les  p }
   grt           { \p s -> Grt  p }
   neq           { \p s -> Neq  p }

-- Jumps

   ujp           { \p s -> Ujp  p }
   fjp           { \p s -> Fjp  p }
   ixj           { \p s -> Ixj  p }
   cup           { \p s -> Cup  p }

-- Access to arrays and to the heap

   ixa           { \p s -> Ixa  p }
   chk           { \p s -> Chk  p }
   inc           { \p s -> Inc  p }
   dec           { \p s -> Dec  p }
   dpl           { \p s -> Dpl  p }
   ldd           { \p s -> Ldd  p }
   sli           { \p s -> Sli  p }
   new           { \p s -> New  p }

-- Procedures, functions, and main program

   movs          { \p s -> Movs p }
   movd          { \p s -> Movd p }
   mst           { \p s -> Mst  p }
   ssp           { \p s -> Ssp  p }
   sep           { \p s -> Sep  p }
   retf          { \p s -> Retf p }
   retp          { \p s -> Retp p }
   stp           { \p s -> Stp  p }

-- Boolean constants

   true           { \p s -> TRUE  p }
   false          { \p s -> FALSE p }



{

-- | Tokens receive the token position as argument
--

data Token =

-- Load and store

           Ldo   AlexPosn
       |   Ldc   AlexPosn
       |   Ind   AlexPosn
       |   Sro   AlexPosn
       |   Sto   AlexPosn
       |   Lod   AlexPosn
       |   Lda   AlexPosn
       |   Str   AlexPosn

-- Arithmetic and logical ones

       |   Add   AlexPosn
       |   Sub   AlexPosn
       |   Mul   AlexPosn
       |   Div   AlexPosn
       |   Mod   AlexPosn
	   |   Pow   AlexPosn
       |   Neg   AlexPosn
       |   And   AlexPosn
       |   Or    AlexPosn
       |   Not   AlexPosn
       |   Equ   AlexPosn
       |   Geq   AlexPosn
       |   Leq   AlexPosn
       |   Les   AlexPosn
       |   Grt   AlexPosn
       |   Neq   AlexPosn

-- Jumps

       |   Ujp   AlexPosn
       |   Fjp   AlexPosn
       |   Ixj   AlexPosn
       |   Cup   AlexPosn

-- Access to arrays and to the heap

       |   Ixa   AlexPosn
       |   Chk   AlexPosn
       |   Inc   AlexPosn
       |   Dec   AlexPosn
       |   Dpl   AlexPosn
       |   Ldd   AlexPosn
       |   Sli   AlexPosn
       |   New   AlexPosn

-- Procedures, functions, and main program

       |   Movs  AlexPosn
       |   Movd  AlexPosn
       |   Mst   AlexPosn
       |   Ssp   AlexPosn
       |   Sep   AlexPosn
       |   Retf  AlexPosn
       |   Retp  AlexPosn
       |   Stp   AlexPosn

-- Remaining tokens

       |   Num (Int, AlexPosn)
       |   SemiColon AlexPosn
       |   TRUE AlexPosn
       |   FALSE AlexPosn

       deriving (Show, Eq)


-- The main function

lexer = alexScanTokens

}
