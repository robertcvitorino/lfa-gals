Token:
:[\n\t\r\s]+
SHOW: show
LOG: log
VARIABLE: [A-Za-z]*
NUMBER: [0-1]+
ADD: \+
SUBTRACT: \-
MULTIPLY: \*
DIVIDE: "/"
EXPONENTIATION: "A"
EQUAL: "="
OPEN_PAREN: "("
CLOSE_PAREN: ")"
EOL: ";"

Gramática:
<inicio> ::= <inicio> <comando> | <comando>;

<comando> ::= VARIABLE #11 EQUAL <soma_sub> EOL #10
           | SHOW OPEN_PAREN <soma_sub> CLOSE_PAREN EOL #9
           | <soma_sub>;

<soma_sub> ::= <soma_sub> ADD <produto> #2
            | <soma_sub> SUBTRACT <produto> #5
            | <produto>;

<produto> ::= <produto> MULTIPLY <potencia> #3
           | <produto> DIVIDE <potencia> #6
           | <potencia>;

<potencia> ::= <valor> EXPONENTIATION <valor> #7
            | LOG <valor> #8
            | <valor>;

<valor> ::= OPEN_PAREN <soma_sub> CLOSE_PAREN
         | NUMBER #1
         | VARIABLE #4;
