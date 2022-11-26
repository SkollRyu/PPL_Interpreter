grammar Infix;

// if there are multiple possibility, then visit it
// otherwise check the type

prog: dec+ EOF ;
dec:	Type Idfr '(' vardec ')' body ;
vardec:	(Type Idfr (',' Type Idfr)*)? ;
body:	'{' (Type Idfr ':=' expr ';')* ene '}';
// int a = 5, we can assign val for the idfr we have declare in vardec
block:	'{' ene '}';
// visit ene
ene:	expr (';' expr)*;

expr
:	Idfr                                # IdExpr
|	IntLit                              # IntExpr
|	BoolLit                             # BoolExpr
|	Idfr ':=' expr                      # AssignExpr
//  idfr := IDFR / idfr := BOOL / idfr := INT
|	'(' expr op=BinOP expr ')'             # BinOpExpr
// prev: BinOP == TYPE.BINOP // idfr == IDFR / idfr := BOOL / idfr := INT
|	Idfr '(' args ')'                   # CallFunExpr
|	block                               # BlockExpr
|	'if' expr 'then' block 'else' block # IfExpr
//  if  TYPE  then visit(
|	'while' expr 'do' block             # WhileExpr
|	'repeat' block 'until' expr         # ForExpr
|	'print' expr                        # PrintExpr
//   visit(ctx.expr())
|	'space'                             # SpaceExpr
|	'newline'                           # NewlineExpr
|	'skip'                              # SkipExpr
;

args:	(expr (',' expr)*)?;
BinOP:	'=='  | '<' | '>' | '<='  | '>='
|	 '+' | '-' | '*' | '/' | '&' | '|' | '^';
Type:	'int' | 'bool' | 'unit';
Idfr:	[a-z][a-zA-Z0-9_]*;
IntLit:	'0' | ('-'? [1-9][0-9]*);
BoolLit:	'true' | 'false';
WS     : [ \n\r\t]+ -> skip ;