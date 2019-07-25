{-# OPTIONS_GHC -w #-}
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

import Control.Applicative(Applicative(..))
import Control.Monad (ap)

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

-- parser produced by Happy Version 1.19.5

data HappyAbsSyn 
	= HappyTerminal (Token)
	| HappyErrorToken Int
	| HappyAbsSyn4 ([Instr])
	| HappyAbsSyn5 (Instr)

{- to allow type-synonyms as our monads (likely
 - with explicitly-specified bind and return)
 - in Haskell98, it seems that with
 - /type M a = .../, then /(HappyReduction M)/
 - is not allowed.  But Happy is a
 - code-generator that can just substitute it.
type HappyReduction m = 
	   Int 
	-> (Token)
	-> HappyState (Token) (HappyStk HappyAbsSyn -> [(Token)] -> m HappyAbsSyn)
	-> [HappyState (Token) (HappyStk HappyAbsSyn -> [(Token)] -> m HappyAbsSyn)] 
	-> HappyStk HappyAbsSyn 
	-> [(Token)] -> m HappyAbsSyn
-}

action_0,
 action_1,
 action_2,
 action_3,
 action_4,
 action_5,
 action_6,
 action_7,
 action_8,
 action_9,
 action_10,
 action_11,
 action_12,
 action_13,
 action_14,
 action_15,
 action_16,
 action_17,
 action_18,
 action_19,
 action_20,
 action_21,
 action_22,
 action_23,
 action_24,
 action_25,
 action_26,
 action_27,
 action_28,
 action_29,
 action_30,
 action_31,
 action_32,
 action_33,
 action_34,
 action_35,
 action_36,
 action_37,
 action_38,
 action_39,
 action_40,
 action_41,
 action_42,
 action_43,
 action_44,
 action_45,
 action_46,
 action_47,
 action_48,
 action_49,
 action_50,
 action_51,
 action_52,
 action_53,
 action_54,
 action_55,
 action_56,
 action_57,
 action_58,
 action_59,
 action_60,
 action_61,
 action_62,
 action_63,
 action_64,
 action_65,
 action_66,
 action_67,
 action_68,
 action_69,
 action_70,
 action_71,
 action_72,
 action_73,
 action_74,
 action_75,
 action_76 :: () => Int -> ({-HappyReduction (HappyIdentity) = -}
	   Int 
	-> (Token)
	-> HappyState (Token) (HappyStk HappyAbsSyn -> [(Token)] -> (HappyIdentity) HappyAbsSyn)
	-> [HappyState (Token) (HappyStk HappyAbsSyn -> [(Token)] -> (HappyIdentity) HappyAbsSyn)] 
	-> HappyStk HappyAbsSyn 
	-> [(Token)] -> (HappyIdentity) HappyAbsSyn)

happyReduce_1,
 happyReduce_2,
 happyReduce_3,
 happyReduce_4,
 happyReduce_5,
 happyReduce_6,
 happyReduce_7,
 happyReduce_8,
 happyReduce_9,
 happyReduce_10,
 happyReduce_11,
 happyReduce_12,
 happyReduce_13,
 happyReduce_14,
 happyReduce_15,
 happyReduce_16,
 happyReduce_17,
 happyReduce_18,
 happyReduce_19,
 happyReduce_20,
 happyReduce_21,
 happyReduce_22,
 happyReduce_23,
 happyReduce_24,
 happyReduce_25,
 happyReduce_26,
 happyReduce_27,
 happyReduce_28,
 happyReduce_29,
 happyReduce_30,
 happyReduce_31,
 happyReduce_32,
 happyReduce_33,
 happyReduce_34,
 happyReduce_35,
 happyReduce_36,
 happyReduce_37,
 happyReduce_38,
 happyReduce_39,
 happyReduce_40,
 happyReduce_41,
 happyReduce_42,
 happyReduce_43,
 happyReduce_44,
 happyReduce_45,
 happyReduce_46,
 happyReduce_47,
 happyReduce_48 :: () => ({-HappyReduction (HappyIdentity) = -}
	   Int 
	-> (Token)
	-> HappyState (Token) (HappyStk HappyAbsSyn -> [(Token)] -> (HappyIdentity) HappyAbsSyn)
	-> [HappyState (Token) (HappyStk HappyAbsSyn -> [(Token)] -> (HappyIdentity) HappyAbsSyn)] 
	-> HappyStk HappyAbsSyn 
	-> [(Token)] -> (HappyIdentity) HappyAbsSyn)

action_0 (4) = happyGoto action_3
action_0 _ = happyReduce_2

action_1 (4) = happyGoto action_2
action_1 _ = happyFail

action_2 (6) = happyShift action_5
action_2 (7) = happyShift action_6
action_2 (8) = happyShift action_7
action_2 (9) = happyShift action_8
action_2 (10) = happyShift action_9
action_2 (11) = happyShift action_10
action_2 (12) = happyShift action_11
action_2 (13) = happyShift action_12
action_2 (14) = happyShift action_13
action_2 (15) = happyShift action_14
action_2 (16) = happyShift action_15
action_2 (17) = happyShift action_16
action_2 (18) = happyShift action_17
action_2 (19) = happyShift action_18
action_2 (20) = happyShift action_19
action_2 (21) = happyShift action_20
action_2 (22) = happyShift action_21
action_2 (23) = happyShift action_22
action_2 (24) = happyShift action_23
action_2 (25) = happyShift action_24
action_2 (26) = happyShift action_25
action_2 (27) = happyShift action_26
action_2 (28) = happyShift action_27
action_2 (29) = happyShift action_28
action_2 (30) = happyShift action_29
action_2 (31) = happyShift action_30
action_2 (32) = happyShift action_31
action_2 (33) = happyShift action_32
action_2 (34) = happyShift action_33
action_2 (35) = happyShift action_34
action_2 (36) = happyShift action_35
action_2 (37) = happyShift action_36
action_2 (38) = happyShift action_37
action_2 (39) = happyShift action_38
action_2 (40) = happyShift action_39
action_2 (41) = happyShift action_40
action_2 (42) = happyShift action_41
action_2 (43) = happyShift action_42
action_2 (44) = happyShift action_43
action_2 (45) = happyShift action_44
action_2 (46) = happyShift action_45
action_2 (47) = happyShift action_46
action_2 (48) = happyShift action_47
action_2 (49) = happyShift action_48
action_2 (5) = happyGoto action_4
action_2 _ = happyFail

action_3 (6) = happyShift action_5
action_3 (7) = happyShift action_6
action_3 (8) = happyShift action_7
action_3 (9) = happyShift action_8
action_3 (10) = happyShift action_9
action_3 (11) = happyShift action_10
action_3 (12) = happyShift action_11
action_3 (13) = happyShift action_12
action_3 (14) = happyShift action_13
action_3 (15) = happyShift action_14
action_3 (16) = happyShift action_15
action_3 (17) = happyShift action_16
action_3 (18) = happyShift action_17
action_3 (19) = happyShift action_18
action_3 (20) = happyShift action_19
action_3 (21) = happyShift action_20
action_3 (22) = happyShift action_21
action_3 (23) = happyShift action_22
action_3 (24) = happyShift action_23
action_3 (25) = happyShift action_24
action_3 (26) = happyShift action_25
action_3 (27) = happyShift action_26
action_3 (28) = happyShift action_27
action_3 (29) = happyShift action_28
action_3 (30) = happyShift action_29
action_3 (31) = happyShift action_30
action_3 (32) = happyShift action_31
action_3 (33) = happyShift action_32
action_3 (34) = happyShift action_33
action_3 (35) = happyShift action_34
action_3 (36) = happyShift action_35
action_3 (37) = happyShift action_36
action_3 (38) = happyShift action_37
action_3 (39) = happyShift action_38
action_3 (40) = happyShift action_39
action_3 (41) = happyShift action_40
action_3 (42) = happyShift action_41
action_3 (43) = happyShift action_42
action_3 (44) = happyShift action_43
action_3 (45) = happyShift action_44
action_3 (46) = happyShift action_45
action_3 (47) = happyShift action_46
action_3 (48) = happyShift action_47
action_3 (49) = happyShift action_48
action_3 (54) = happyAccept
action_3 (5) = happyGoto action_4
action_3 _ = happyFail

action_4 (51) = happyShift action_71
action_4 _ = happyFail

action_5 (50) = happyShift action_70
action_5 _ = happyFail

action_6 (50) = happyShift action_67
action_6 (52) = happyShift action_68
action_6 (53) = happyShift action_69
action_6 _ = happyFail

action_7 _ = happyReduce_7

action_8 (50) = happyShift action_66
action_8 _ = happyFail

action_9 _ = happyReduce_9

action_10 (50) = happyShift action_65
action_10 _ = happyFail

action_11 (50) = happyShift action_64
action_11 _ = happyFail

action_12 (50) = happyShift action_63
action_12 _ = happyFail

action_13 _ = happyReduce_13

action_14 _ = happyReduce_14

action_15 _ = happyReduce_15

action_16 _ = happyReduce_16

action_17 _ = happyReduce_17

action_18 _ = happyReduce_18

action_19 _ = happyReduce_19

action_20 _ = happyReduce_20

action_21 _ = happyReduce_21

action_22 _ = happyReduce_22

action_23 _ = happyReduce_23

action_24 _ = happyReduce_24

action_25 _ = happyReduce_25

action_26 _ = happyReduce_26

action_27 _ = happyReduce_27

action_28 _ = happyReduce_28

action_29 (50) = happyShift action_62
action_29 _ = happyFail

action_30 (50) = happyShift action_61
action_30 _ = happyFail

action_31 (50) = happyShift action_60
action_31 _ = happyFail

action_32 (50) = happyShift action_59
action_32 _ = happyFail

action_33 (50) = happyShift action_58
action_33 _ = happyFail

action_34 (50) = happyShift action_57
action_34 _ = happyFail

action_35 (50) = happyShift action_56
action_35 _ = happyFail

action_36 (50) = happyShift action_55
action_36 _ = happyFail

action_37 _ = happyReduce_37

action_38 (50) = happyShift action_54
action_38 _ = happyFail

action_39 _ = happyReduce_39

action_40 _ = happyReduce_40

action_41 (50) = happyShift action_53
action_41 _ = happyFail

action_42 (50) = happyShift action_52
action_42 _ = happyFail

action_43 (50) = happyShift action_51
action_43 _ = happyFail

action_44 (50) = happyShift action_50
action_44 _ = happyFail

action_45 (50) = happyShift action_49
action_45 _ = happyFail

action_46 _ = happyReduce_46

action_47 _ = happyReduce_47

action_48 _ = happyReduce_48

action_49 _ = happyReduce_45

action_50 _ = happyReduce_44

action_51 _ = happyReduce_43

action_52 _ = happyReduce_42

action_53 _ = happyReduce_41

action_54 _ = happyReduce_38

action_55 _ = happyReduce_36

action_56 _ = happyReduce_35

action_57 (50) = happyShift action_76
action_57 _ = happyFail

action_58 _ = happyReduce_33

action_59 (50) = happyShift action_75
action_59 _ = happyFail

action_60 _ = happyReduce_31

action_61 _ = happyReduce_30

action_62 _ = happyReduce_29

action_63 (50) = happyShift action_74
action_63 _ = happyFail

action_64 (50) = happyShift action_73
action_64 _ = happyFail

action_65 (50) = happyShift action_72
action_65 _ = happyFail

action_66 _ = happyReduce_8

action_67 _ = happyReduce_4

action_68 _ = happyReduce_5

action_69 _ = happyReduce_6

action_70 _ = happyReduce_3

action_71 _ = happyReduce_1

action_72 _ = happyReduce_10

action_73 _ = happyReduce_11

action_74 _ = happyReduce_12

action_75 _ = happyReduce_32

action_76 _ = happyReduce_34

happyReduce_1 = happySpecReduce_3  4 happyReduction_1
happyReduction_1 _
	(HappyAbsSyn5  happy_var_2)
	(HappyAbsSyn4  happy_var_1)
	 =  HappyAbsSyn4
		 (happy_var_2 : happy_var_1
	)
happyReduction_1 _ _ _  = notHappyAtAll 

happyReduce_2 = happySpecReduce_0  4 happyReduction_2
happyReduction_2  =  HappyAbsSyn4
		 ([]
	)

happyReduce_3 = happySpecReduce_2  5 happyReduction_3
happyReduction_3 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (LDO (fst happy_var_2)
	)
happyReduction_3 _ _  = notHappyAtAll 

happyReduce_4 = happySpecReduce_2  5 happyReduction_4
happyReduction_4 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (LDC (Int (fst happy_var_2))
	)
happyReduction_4 _ _  = notHappyAtAll 

happyReduce_5 = happySpecReduce_2  5 happyReduction_5
happyReduction_5 _
	_
	 =  HappyAbsSyn5
		 (LDC (Bool True )
	)

happyReduce_6 = happySpecReduce_2  5 happyReduction_6
happyReduction_6 _
	_
	 =  HappyAbsSyn5
		 (LDC (Bool False)
	)

happyReduce_7 = happySpecReduce_1  5 happyReduction_7
happyReduction_7 _
	 =  HappyAbsSyn5
		 (IND
	)

happyReduce_8 = happySpecReduce_2  5 happyReduction_8
happyReduction_8 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (SRO (fst happy_var_2)
	)
happyReduction_8 _ _  = notHappyAtAll 

happyReduce_9 = happySpecReduce_1  5 happyReduction_9
happyReduction_9 _
	 =  HappyAbsSyn5
		 (STO
	)

happyReduce_10 = happySpecReduce_3  5 happyReduction_10
happyReduction_10 (HappyTerminal (Num happy_var_3))
	(HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (LOD (fst happy_var_2) (fst happy_var_3)
	)
happyReduction_10 _ _ _  = notHappyAtAll 

happyReduce_11 = happySpecReduce_3  5 happyReduction_11
happyReduction_11 (HappyTerminal (Num happy_var_3))
	(HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (LDA (fst happy_var_2) (fst happy_var_3)
	)
happyReduction_11 _ _ _  = notHappyAtAll 

happyReduce_12 = happySpecReduce_3  5 happyReduction_12
happyReduction_12 (HappyTerminal (Num happy_var_3))
	(HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (STR (fst happy_var_2) (fst happy_var_3)
	)
happyReduction_12 _ _ _  = notHappyAtAll 

happyReduce_13 = happySpecReduce_1  5 happyReduction_13
happyReduction_13 _
	 =  HappyAbsSyn5
		 (ADD
	)

happyReduce_14 = happySpecReduce_1  5 happyReduction_14
happyReduction_14 _
	 =  HappyAbsSyn5
		 (SUB
	)

happyReduce_15 = happySpecReduce_1  5 happyReduction_15
happyReduction_15 _
	 =  HappyAbsSyn5
		 (MUL
	)

happyReduce_16 = happySpecReduce_1  5 happyReduction_16
happyReduction_16 _
	 =  HappyAbsSyn5
		 (DIV
	)

happyReduce_17 = happySpecReduce_1  5 happyReduction_17
happyReduction_17 _
	 =  HappyAbsSyn5
		 (MOD
	)

happyReduce_18 = happySpecReduce_1  5 happyReduction_18
happyReduction_18 _
	 =  HappyAbsSyn5
		 (POW
	)

happyReduce_19 = happySpecReduce_1  5 happyReduction_19
happyReduction_19 _
	 =  HappyAbsSyn5
		 (NEG
	)

happyReduce_20 = happySpecReduce_1  5 happyReduction_20
happyReduction_20 _
	 =  HappyAbsSyn5
		 (AND
	)

happyReduce_21 = happySpecReduce_1  5 happyReduction_21
happyReduction_21 _
	 =  HappyAbsSyn5
		 (OR
	)

happyReduce_22 = happySpecReduce_1  5 happyReduction_22
happyReduction_22 _
	 =  HappyAbsSyn5
		 (NOT
	)

happyReduce_23 = happySpecReduce_1  5 happyReduction_23
happyReduction_23 _
	 =  HappyAbsSyn5
		 (EQU
	)

happyReduce_24 = happySpecReduce_1  5 happyReduction_24
happyReduction_24 _
	 =  HappyAbsSyn5
		 (GEQ
	)

happyReduce_25 = happySpecReduce_1  5 happyReduction_25
happyReduction_25 _
	 =  HappyAbsSyn5
		 (LEQ
	)

happyReduce_26 = happySpecReduce_1  5 happyReduction_26
happyReduction_26 _
	 =  HappyAbsSyn5
		 (LES
	)

happyReduce_27 = happySpecReduce_1  5 happyReduction_27
happyReduction_27 _
	 =  HappyAbsSyn5
		 (GRT
	)

happyReduce_28 = happySpecReduce_1  5 happyReduction_28
happyReduction_28 _
	 =  HappyAbsSyn5
		 (NEQ
	)

happyReduce_29 = happySpecReduce_2  5 happyReduction_29
happyReduction_29 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (UJP (fst happy_var_2)
	)
happyReduction_29 _ _  = notHappyAtAll 

happyReduce_30 = happySpecReduce_2  5 happyReduction_30
happyReduction_30 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (FJP (fst happy_var_2)
	)
happyReduction_30 _ _  = notHappyAtAll 

happyReduce_31 = happySpecReduce_2  5 happyReduction_31
happyReduction_31 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (IXJ (fst happy_var_2)
	)
happyReduction_31 _ _  = notHappyAtAll 

happyReduce_32 = happySpecReduce_3  5 happyReduction_32
happyReduction_32 (HappyTerminal (Num happy_var_3))
	(HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (CUP (fst happy_var_2) (fst happy_var_3)
	)
happyReduction_32 _ _ _  = notHappyAtAll 

happyReduce_33 = happySpecReduce_2  5 happyReduction_33
happyReduction_33 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (IXA (fst happy_var_2)
	)
happyReduction_33 _ _  = notHappyAtAll 

happyReduce_34 = happySpecReduce_3  5 happyReduction_34
happyReduction_34 (HappyTerminal (Num happy_var_3))
	(HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (CHK (fst happy_var_2) (fst happy_var_3)
	)
happyReduction_34 _ _ _  = notHappyAtAll 

happyReduce_35 = happySpecReduce_2  5 happyReduction_35
happyReduction_35 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (INC (fst happy_var_2)
	)
happyReduction_35 _ _  = notHappyAtAll 

happyReduce_36 = happySpecReduce_2  5 happyReduction_36
happyReduction_36 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (DEC (fst happy_var_2)
	)
happyReduction_36 _ _  = notHappyAtAll 

happyReduce_37 = happySpecReduce_1  5 happyReduction_37
happyReduction_37 _
	 =  HappyAbsSyn5
		 (DPL
	)

happyReduce_38 = happySpecReduce_2  5 happyReduction_38
happyReduction_38 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (LDD (fst happy_var_2)
	)
happyReduction_38 _ _  = notHappyAtAll 

happyReduce_39 = happySpecReduce_1  5 happyReduction_39
happyReduction_39 _
	 =  HappyAbsSyn5
		 (SLI
	)

happyReduce_40 = happySpecReduce_1  5 happyReduction_40
happyReduction_40 _
	 =  HappyAbsSyn5
		 (NEW
	)

happyReduce_41 = happySpecReduce_2  5 happyReduction_41
happyReduction_41 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (MOVS (fst happy_var_2)
	)
happyReduction_41 _ _  = notHappyAtAll 

happyReduce_42 = happySpecReduce_2  5 happyReduction_42
happyReduction_42 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (MOVD (fst happy_var_2)
	)
happyReduction_42 _ _  = notHappyAtAll 

happyReduce_43 = happySpecReduce_2  5 happyReduction_43
happyReduction_43 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (MST  (fst happy_var_2)
	)
happyReduction_43 _ _  = notHappyAtAll 

happyReduce_44 = happySpecReduce_2  5 happyReduction_44
happyReduction_44 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (SSP  (fst happy_var_2)
	)
happyReduction_44 _ _  = notHappyAtAll 

happyReduce_45 = happySpecReduce_2  5 happyReduction_45
happyReduction_45 (HappyTerminal (Num happy_var_2))
	_
	 =  HappyAbsSyn5
		 (SEP  (fst happy_var_2)
	)
happyReduction_45 _ _  = notHappyAtAll 

happyReduce_46 = happySpecReduce_1  5 happyReduction_46
happyReduction_46 _
	 =  HappyAbsSyn5
		 (RETF
	)

happyReduce_47 = happySpecReduce_1  5 happyReduction_47
happyReduction_47 _
	 =  HappyAbsSyn5
		 (RETP
	)

happyReduce_48 = happySpecReduce_1  5 happyReduction_48
happyReduction_48 _
	 =  HappyAbsSyn5
		 (STP
	)

happyNewToken action sts stk [] =
	action 54 54 notHappyAtAll (HappyState action) sts stk []

happyNewToken action sts stk (tk:tks) =
	let cont i = action i i tk (HappyState action) sts stk tks in
	case tk of {
	Ldo  _ -> cont 6;
	Ldc  _ -> cont 7;
	Ind  _ -> cont 8;
	Sro  _ -> cont 9;
	Sto  _ -> cont 10;
	Lod  _ -> cont 11;
	Lda  _ -> cont 12;
	Str  _ -> cont 13;
	Add  _ -> cont 14;
	Sub  _ -> cont 15;
	Mul  _ -> cont 16;
	Div  _ -> cont 17;
	Mod  _ -> cont 18;
	Pow  _ -> cont 19;
	Neg  _ -> cont 20;
	And  _ -> cont 21;
	Or   _ -> cont 22;
	Not  _ -> cont 23;
	Equ  _ -> cont 24;
	Geq  _ -> cont 25;
	Leq  _ -> cont 26;
	Les  _ -> cont 27;
	Grt  _ -> cont 28;
	Neq  _ -> cont 29;
	Ujp  _ -> cont 30;
	Fjp  _ -> cont 31;
	Ixj  _ -> cont 32;
	Cup  _ -> cont 33;
	Ixa  _ -> cont 34;
	Chk  _ -> cont 35;
	Inc  _ -> cont 36;
	Dec  _ -> cont 37;
	Dpl  _ -> cont 38;
	Ldd  _ -> cont 39;
	Sli  _ -> cont 40;
	New  _ -> cont 41;
	Movs _ -> cont 42;
	Movd _ -> cont 43;
	Mst  _ -> cont 44;
	Ssp  _ -> cont 45;
	Sep  _ -> cont 46;
	Retf _ -> cont 47;
	Retp _ -> cont 48;
	Stp  _ -> cont 49;
	Num happy_dollar_dollar -> cont 50;
	SemiColon _ -> cont 51;
	TRUE _ -> cont 52;
	FALSE _ -> cont 53;
	_ -> happyError' (tk:tks)
	}

happyError_ 54 tk tks = happyError' tks
happyError_ _ tk tks = happyError' (tk:tks)

newtype HappyIdentity a = HappyIdentity a
happyIdentity = HappyIdentity
happyRunIdentity (HappyIdentity a) = a

instance Functor HappyIdentity where
    fmap f (HappyIdentity a) = HappyIdentity (f a)

instance Applicative HappyIdentity where
    pure  = return
    (<*>) = ap
instance Monad HappyIdentity where
    return = HappyIdentity
    (HappyIdentity p) >>= q = q p

happyThen :: () => HappyIdentity a -> (a -> HappyIdentity b) -> HappyIdentity b
happyThen = (>>=)
happyReturn :: () => a -> HappyIdentity a
happyReturn = (return)
happyThen1 m k tks = (>>=) m (\a -> k a tks)
happyReturn1 :: () => a -> b -> HappyIdentity a
happyReturn1 = \a tks -> (return) a
happyError' :: () => [(Token)] -> HappyIdentity a
happyError' = HappyIdentity . happyError

revparser tks = happyRunIdentity happySomeParser where
  happySomeParser = happyThen (happyParse action_0 tks) (\x -> case x of {HappyAbsSyn4 z -> happyReturn z; _other -> notHappyAtAll })

happySeq = happyDontSeq


-- main function

parser = reverse . revparser

happyError :: [Token] -> a
happyError toks = error ("Syntax error in input file\n We show the next token\n\
                         \The second and third numbers \
                         \are its line and column): "++ show x )
   where  x:xs = toks
{-# LINE 1 "templates/GenericTemplate.hs" #-}
{-# LINE 1 "templates/GenericTemplate.hs" #-}
{-# LINE 1 "<built-in>" #-}
{-# LINE 1 "<command-line>" #-}
{-# LINE 8 "<command-line>" #-}
# 1 "/usr/include/stdc-predef.h" 1 3 4

# 17 "/usr/include/stdc-predef.h" 3 4











































{-# LINE 8 "<command-line>" #-}
{-# LINE 1 "/usr/lib64/ghc-7.10.3/include/ghcversion.h" #-}

















{-# LINE 8 "<command-line>" #-}
{-# LINE 1 "templates/GenericTemplate.hs" #-}
-- Id: GenericTemplate.hs,v 1.26 2005/01/14 14:47:22 simonmar Exp 

{-# LINE 13 "templates/GenericTemplate.hs" #-}

{-# LINE 46 "templates/GenericTemplate.hs" #-}








{-# LINE 67 "templates/GenericTemplate.hs" #-}

{-# LINE 77 "templates/GenericTemplate.hs" #-}

{-# LINE 86 "templates/GenericTemplate.hs" #-}

infixr 9 `HappyStk`
data HappyStk a = HappyStk a (HappyStk a)

-----------------------------------------------------------------------------
-- starting the parse

happyParse start_state = happyNewToken start_state notHappyAtAll notHappyAtAll

-----------------------------------------------------------------------------
-- Accepting the parse

-- If the current token is (1), it means we've just accepted a partial
-- parse (a %partial parser).  We must ignore the saved token on the top of
-- the stack in this case.
happyAccept (1) tk st sts (_ `HappyStk` ans `HappyStk` _) =
        happyReturn1 ans
happyAccept j tk st sts (HappyStk ans _) = 
         (happyReturn1 ans)

-----------------------------------------------------------------------------
-- Arrays only: do the next action

{-# LINE 155 "templates/GenericTemplate.hs" #-}

-----------------------------------------------------------------------------
-- HappyState data type (not arrays)



newtype HappyState b c = HappyState
        (Int ->                    -- token number
         Int ->                    -- token number (yes, again)
         b ->                           -- token semantic value
         HappyState b c ->              -- current state
         [HappyState b c] ->            -- state stack
         c)



-----------------------------------------------------------------------------
-- Shifting a token

happyShift new_state (1) tk st sts stk@(x `HappyStk` _) =
     let i = (case x of { HappyErrorToken (i) -> i }) in
--     trace "shifting the error token" $
     new_state i i tk (HappyState (new_state)) ((st):(sts)) (stk)

happyShift new_state i tk st sts stk =
     happyNewToken new_state ((st):(sts)) ((HappyTerminal (tk))`HappyStk`stk)

-- happyReduce is specialised for the common cases.

happySpecReduce_0 i fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happySpecReduce_0 nt fn j tk st@((HappyState (action))) sts stk
     = action nt j tk st ((st):(sts)) (fn `HappyStk` stk)

happySpecReduce_1 i fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happySpecReduce_1 nt fn j tk _ sts@(((st@(HappyState (action))):(_))) (v1`HappyStk`stk')
     = let r = fn v1 in
       happySeq r (action nt j tk st sts (r `HappyStk` stk'))

happySpecReduce_2 i fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happySpecReduce_2 nt fn j tk _ ((_):(sts@(((st@(HappyState (action))):(_))))) (v1`HappyStk`v2`HappyStk`stk')
     = let r = fn v1 v2 in
       happySeq r (action nt j tk st sts (r `HappyStk` stk'))

happySpecReduce_3 i fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happySpecReduce_3 nt fn j tk _ ((_):(((_):(sts@(((st@(HappyState (action))):(_))))))) (v1`HappyStk`v2`HappyStk`v3`HappyStk`stk')
     = let r = fn v1 v2 v3 in
       happySeq r (action nt j tk st sts (r `HappyStk` stk'))

happyReduce k i fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happyReduce k nt fn j tk st sts stk
     = case happyDrop (k - ((1) :: Int)) sts of
         sts1@(((st1@(HappyState (action))):(_))) ->
                let r = fn stk in  -- it doesn't hurt to always seq here...
                happyDoSeq r (action nt j tk st1 sts1 r)

happyMonadReduce k nt fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happyMonadReduce k nt fn j tk st sts stk =
      case happyDrop k ((st):(sts)) of
        sts1@(((st1@(HappyState (action))):(_))) ->
          let drop_stk = happyDropStk k stk in
          happyThen1 (fn stk tk) (\r -> action nt j tk st1 sts1 (r `HappyStk` drop_stk))

happyMonad2Reduce k nt fn (1) tk st sts stk
     = happyFail (1) tk st sts stk
happyMonad2Reduce k nt fn j tk st sts stk =
      case happyDrop k ((st):(sts)) of
        sts1@(((st1@(HappyState (action))):(_))) ->
         let drop_stk = happyDropStk k stk





             new_state = action

          in
          happyThen1 (fn stk tk) (\r -> happyNewToken new_state sts1 (r `HappyStk` drop_stk))

happyDrop (0) l = l
happyDrop n ((_):(t)) = happyDrop (n - ((1) :: Int)) t

happyDropStk (0) l = l
happyDropStk n (x `HappyStk` xs) = happyDropStk (n - ((1)::Int)) xs

-----------------------------------------------------------------------------
-- Moving to a new state after a reduction

{-# LINE 256 "templates/GenericTemplate.hs" #-}
happyGoto action j tk st = action j j tk (HappyState action)


-----------------------------------------------------------------------------
-- Error recovery ((1) is the error token)

-- parse error if we are in recovery and we fail again
happyFail (1) tk old_st _ stk@(x `HappyStk` _) =
     let i = (case x of { HappyErrorToken (i) -> i }) in
--      trace "failing" $ 
        happyError_ i tk

{-  We don't need state discarding for our restricted implementation of
    "error".  In fact, it can cause some bogus parses, so I've disabled it
    for now --SDM

-- discard a state
happyFail  (1) tk old_st (((HappyState (action))):(sts)) 
                                                (saved_tok `HappyStk` _ `HappyStk` stk) =
--      trace ("discarding state, depth " ++ show (length stk))  $
        action (1) (1) tk (HappyState (action)) sts ((saved_tok`HappyStk`stk))
-}

-- Enter error recovery: generate an error token,
--                       save the old token and carry on.
happyFail  i tk (HappyState (action)) sts stk =
--      trace "entering error recovery" $
        action (1) (1) tk (HappyState (action)) sts ( (HappyErrorToken (i)) `HappyStk` stk)

-- Internal happy errors:

notHappyAtAll :: a
notHappyAtAll = error "Internal Happy error\n"

-----------------------------------------------------------------------------
-- Hack to get the typechecker to accept our action functions







-----------------------------------------------------------------------------
-- Seq-ing.  If the --strict flag is given, then Happy emits 
--      happySeq = happyDoSeq
-- otherwise it emits
--      happySeq = happyDontSeq

happyDoSeq, happyDontSeq :: a -> b -> b
happyDoSeq   a b = a `seq` b
happyDontSeq a b = b

-----------------------------------------------------------------------------
-- Don't inline any functions from the template.  GHC has a nasty habit
-- of deciding to inline happyGoto everywhere, which increases the size of
-- the generated parser quite a bit.

{-# LINE 322 "templates/GenericTemplate.hs" #-}
{-# NOINLINE happyShift #-}
{-# NOINLINE happySpecReduce_0 #-}
{-# NOINLINE happySpecReduce_1 #-}
{-# NOINLINE happySpecReduce_2 #-}
{-# NOINLINE happySpecReduce_3 #-}
{-# NOINLINE happyReduce #-}
{-# NOINLINE happyMonadReduce #-}
{-# NOINLINE happyGoto #-}
{-# NOINLINE happyFail #-}

-- end of Happy Template.
