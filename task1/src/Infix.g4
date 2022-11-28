grammar Infix;

// if there are multiple possibility, then visit it
// otherwise check the type

prog: dec+ EOF ; // RuleIndex: 0
dec:	Type Idfr '(' vardec ')' body ; // 1
vardec:	(Type Idfr (',' Type Idfr)*)? ; // 2
body:	'{' (Type Idfr ':=' expr ';')* ene '}';
// int a = 5, we can assign val for the idfr we have declare in vardec
block:	'{' ene '}';
// visit ene
ene:	expr (';' expr)*;

expr
:   BoolLit                             # BoolExpr
|	Idfr                                # IdExpr
|	IntLit                              # IntExpr
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
BoolLit:	'true' | 'false';
Idfr:	[a-z][a-zA-Z0-9_]*;
IntLit:	'0' | ('-'? [1-9][0-9]*);
WS     : [ \n\r\t]+ -> skip ;