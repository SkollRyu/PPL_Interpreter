# PPL_Interpreter 


## Syntax Description
```
PROG →	DEC+
DEC →	TYPE IDFR "(" VARDEC ")" BODY
VARDEC →	(TYPE IDFR ("," TYPE IDFR)*)?
BODY →	"{" (TYPE IDFR ":=" EXP ";")* ENE "}"
BLOCK →	"{" ENE "}"
ENE →	EXP (";" EXP)*
EXP →	IDFR
|	INTLIT
|	BOOLLIT
|	IDFR ":=" EXP
|	"(" EXP BINOP EXP ")"
|	IDFR "(" ARGS ")"
|	BLOCK
|	"if" EXP "then" BLOCK "else" BLOCK
|	"while" EXP "do" BLOCK
|	"repeat" BLOCK "until" EXP
|	"print" EXP
|	"space"
|	"newline"
|	"skip"
ARGS →	(EXP ("," EXP)*)?
BINOP →	"=="  |  "<"  |  ">"  |  "<="  |  ">="
|	 "+"  |  "-"  |  "*"  |   "/"  |  "&"  |  "|"  | "^"
TYPE →	"int" | "bool" | "unit"
IDFR →	(an identifier)
INTLIT →	(an integer)
BOOLLIT →	"true" | "false"
```
