# PPL_Interpreter 

## Description
In our simple programming language, the main function is a function int main(...), which may occur anywhere in the function declarations. The return value of a function is the value of the final expression in its body. All arguments to functions are passed by value, and the only identifiers defined in any function are as follows:

- The names of other functions (which may be defined before or after the current function in the source file);
- The parameters taken by the current function;
- The local variables (defined at the top of the current function's body). 

## Syntax Description
### The grammar
Here is the language syntax, given by the following context free grammar (note that we also make use of repetition operators * and +) with initial non-terminal symbol PROG. 

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
### Examples
#### Example #1
```
  int fun(int x, int y, int z) {
    if (x == y) then { z } else { 0 } }

  int main() { fun(1, 2, 3) }
```
#### Example #2
```
  int main(int n) { fibo(n) }
  int fibo(int n) {
    if (n < 2)
    then { n } 
    else { (fibo((n - 1)) + fibo((n - 2))) } }
```
#### Example #3
```
  unit doLoop (int i, int a) {
    while (i <= 100) do {
      a := (a + i);
      i := (i + 1) } }

  int main() {
    doLoop(0, 5);
    print space;
    1337 }
```
#### Example #4
```
  int main() { print fact(10); 100 }

  int fact(int n) {
    if (n == 0)
    then { 1 } 
    else { (n * fact((n - 1))) } }
```
