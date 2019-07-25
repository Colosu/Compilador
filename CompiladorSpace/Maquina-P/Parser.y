{
module Parser  (
-----------------------------------------------------------------------------
-- |
-- Module      :  Parser
-- Copyright   :  (c) Ricardo Peña, Jun. 2014, Universidad Complutense de Madrid
--                Parser for the P-code input file
-- License     :  LGPL
--
-- Maintainer  :  ricardo@sip.ucm.es
-- Stability   :  provisional
-- Portability :  portable
--
--   It provides a parser for a P-machine program
--     . It expects a list of tokens
--     . It returns a list of P-instructions, each one with its correct arguments
--
-----------------------------------------------------------------------------
--
               -- * Data types
               Val(..),
               Instr(..),
               -- * Main function [Token] -> [Instr]
               parser
               )  where

import  Lexer


-- The values stored in the data store

data Val = Int Int | Bool Bool | Null
     deriving (Eq, Show)

-- The instructions stored in the Code store

data Instr =

-- Load and store

             LDO Int
           | LDC Val
           | IND
           | SRO Int
           | STO
           | LOD Int Int
           | LDA Int Int
           | STR Int Int

-- Arithmetic and logical ones

           | ADD
           | SUB
           | MUL
           | DIV
           | MOD
		   | POW
           | NEG
           | AND
           | OR
           | NOT
           | EQU
           | GEQ
           | LEQ
           | LES
           | GRT
           | NEQ

-- Jumps

           | UJP Int
           | FJP Int
           | IXJ Int
           | CUP Int Int

-- Access to arrays and to the heap

           | IXA Int
           | CHK Int Int
           | INC Int
           | DEC Int
           | DPL
           | LDD Int
           | SLI
           | NEW

-- Procedures, functions, and main program

           | MOVS Int
           | MOVD Int
           | MST  Int
           | SSP  Int
           | SEP  Int
           | RETF
           | RETP
           | STP

           deriving (Eq, Show)



}
%name revparser
%tokentype { Token }
%token

-- Load and store

      ldo   { Ldo  _ }
      ldc   { Ldc  _ }
      ind   { Ind  _ }
      sro   { Sro  _ }
      sto   { Sto  _ }
      lod   { Lod  _ }
      lda   { Lda  _ }
      str   { Str  _ }

-- Arithmetic and logical ones

      add   { Add  _ }
      sub   { Sub  _ }
      mul   { Mul  _ }
      div   { Div  _ }
      mod   { Mod  _ }
	  pow   { Pow  _ }
      neg   { Neg  _ }
      and   { And  _ }
      or    { Or   _ }
      not   { Not  _ }
      equ   { Equ  _ }
      geq   { Geq  _ }
      leq   { Leq  _ }
      les   { Les  _ }
      grt   { Grt  _ }
      neq   { Neq  _ }

-- Jumps

      ujp   { Ujp  _ }
      fjp   { Fjp  _ }
      ixj   { Ixj  _ }
      cup   { Cup  _ }

-- Access to arrays and to the heap

      ixa   { Ixa  _ }
      chk   { Chk  _ }
      inc   { Inc  _ }
      dec   { Dec  _ }
      dpl   { Dpl  _ }
      ldd   { Ldd  _ }
      sli   { Sli  _ }
      new   { New  _ }

-- Procedures, functions, and main program

      movs  { Movs _ }
      movd  { Movd _ }
      mst   { Mst  _ }
      ssp   { Ssp  _ }
      sep   { Sep  _ }
      retf  { Retf _ }
      retp  { Retp _ }
      stp   { Stp  _ }

-- Remaining tokens
      num   { Num $$ }
      ';'   { SemiColon _ }
      true  { TRUE _ }
      false { FALSE _ }

 %%


-- a P-program
prog :: { [Instr] }
prog : prog Pinstruction ';'   { $2 : $1 }
  |                            { [] }

-- a P-instruction
Pinstruction :: { Instr }
Pinstruction :

-- Load and store

         ldo num      { LDO (fst $2) }
  |      ldc num      { LDC (Int (fst $2)) }
  |      ldc true     { LDC (Bool True ) }
  |      ldc false    { LDC (Bool False) }
  |      ind          { IND    }
  |      sro num      { SRO (fst $2) }
  |      sto          { STO    }
  |      lod num num  { LOD (fst $2) (fst $3) }
  |      lda num num  { LDA (fst $2) (fst $3) }
  |      str num num  { STR (fst $2) (fst $3) }

-- Arithmetic and logical ones

  |      add          { ADD    }
  |      sub          { SUB    }
  |      mul          { MUL    }
  |      div          { DIV    }
  |      mod          { MOD    }
  |      pow          { POW    }
  |      neg          { NEG    }
  |      and          { AND    }
  |      or           { OR    }
  |      not          { NOT    }
  |      equ          { EQU    }
  |      geq          { GEQ    }
  |      leq          { LEQ    }
  |      les          { LES    }
  |      grt          { GRT    }
  |      neq          { NEQ    }

-- Jumps

  |      ujp num      { UJP (fst $2)   }
  |      fjp num      { FJP (fst $2)   }
  |      ixj num      { IXJ (fst $2)   }
  |      cup num  num { CUP (fst $2) (fst $3) }

-- Access to arrays and to the heap

  |      ixa num      { IXA (fst $2)   }
  |      chk num num  { CHK (fst $2) (fst $3) }
  |      inc num      { INC (fst $2)   }
  |      dec num      { DEC (fst $2)   }
  |      dpl          { DPL    }
  |      ldd num      { LDD (fst $2)   }
  |      sli          { SLI    }
  |      new          { NEW    }

-- Procedures, functions, and main program

  |     movs num       { MOVS (fst $2) }
  |     movd num       { MOVD (fst $2) }
  |     mst  num       { MST  (fst $2) }
  |     ssp  num       { SSP  (fst $2) }
  |     sep  num       { SEP  (fst $2) }
  |     retf           { RETF    }
  |     retp           { RETP    }
  |     stp            { STP    }



{

-- main function

parser = reverse . revparser

happyError :: [Token] -> a
happyError toks = error ("Syntax error in input file\n We show the next token\n\
                         \The second and third numbers \
                         \are its line and column): "++ show x )
   where  x:xs = toks

}
