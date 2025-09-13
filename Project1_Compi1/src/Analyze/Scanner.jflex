package Analyze;
import java_cup.runtime.Symbol;
import Classes.LexicalError;
import java.util.ArrayList;

%%

// Configurations para el analyze

%{
    //Code java
    ArrayList<LexicalError> lexicalErrors = new ArrayList<>();
    public void AddLexicalError(int line, int column, String error){
        lexicalErrors.add(new LexicalError(line, column, error));
    }
    public ArrayList<LexicalError> getLexicalErrors() {
        return lexicalErrors;
    }
%}

//Directives
//Syntax: %directive
%class Scanner
%public
%cupsym Terminal
%cup
%char
%column
%full
%line
%unicode

//constructor
%init{
    yyline = 1;
    yychar = 1;
%init}

//regular expressions
UNUSED = [ \r\t]+ //Characters that is omitted
NUMBERS = [0-9]+(\.[0-9]+)?
COMMENT_SL = "//"([^\n\r]*)?
COMMENT_ML = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]
CONTENT = [^\n\r\"]
STRING = \"({CONTENT})*\"
ID = [a-zA-Z]+[0-9]*
%%

//Semantic rules
//{ER}  {return new Symbol(parameters);}
{UNUSED}        {}
"+"             {return new Symbol(Terminal.TK_Plus, yyline, yychar, yytext());}
{NUMBERS}       {return new Symbol(Terminal.TK_Number, yyline, yychar, yytext());}
"."             {return new Symbol(Terminal.TK_Point, yyline, yychar, yytext());}
"<"             {return new Symbol(Terminal.TK_LessT, yyline, yychar, yytext());}
">"             {return new Symbol(Terminal.TK_GreatherT, yyline, yychar, yytext());}
"="             {return new Symbol(Terminal.TK_Equal, yyline, yychar, yytext());}
"{"             {return new Symbol(Terminal.TK_Left_Brace, yyline, yychar, yytext());}
"}"             {return new Symbol(Terminal.TK_Right_Brace, yyline, yychar, yytext());}
","             {return new Symbol(Terminal.TK_Coma, yyline, yychar, yytext());}
";"             {return new Symbol(Terminal.TK_SemiColon, yyline, yychar, yytext());}
"|"             {return new Symbol(Terminal.TK_OR, yyline, yychar, yytext());}
":"             {return new Symbol(Terminal.TK_Colon, yyline, yychar, yytext());}
"/"             {return new Symbol(Terminal.TK_Slash, yyline, yychar, yytext());}
"("             {return new Symbol(Terminal.TK_Left_Parenthesis, yyline, yychar, yytext());}
")"             {return new Symbol(Terminal.TK_Right_Parenthesis, yyline, yychar, yytext());}
"#"             {return new Symbol(Terminal.TK_Hash, yyline, yychar, yytext());}
"$"             {return new Symbol(Terminal.TK_Dollar, yyline, yychar, yytext());}
"AFD"           {return new Symbol(Terminal.TK_KW_AFD, yyline, yychar, yytext());}
"AP"            {return new Symbol(Terminal.TK_KW_AP, yyline, yychar, yytext());}
"verAutomatas"  {return new Symbol(Terminal.TK_KW_VerAutomatas, yyline, yychar, yytext());}
"desc"          {return new Symbol(Terminal.TK_KW_Desc, yyline, yychar, yytext());}
"Nombre"        {return new Symbol(Terminal.TK_KW_Nombre, yyline, yychar, yytext());}
{STRING}        {return new Symbol(Terminal.TK_String, yyline, yychar, yytext());}
"N"             {return new Symbol(Terminal.TK_KW_NumberStates, yyline, yychar, yytext());}
"T"             {return new Symbol(Terminal.TK_KW_Alphabet, yyline, yychar, yytext());}
"I"             {return new Symbol(Terminal.TK_KW_InitialState, yyline, yychar, yytext());}
"A"             {return new Symbol(Terminal.TK_KW_AcceptStates, yyline, yychar, yytext());}
"P"            {return new Symbol(Terminal.TK_KW_Simbols, yyline, yychar, yytext());}
"Transiciones"  {return new Symbol(Terminal.TK_KW_Transiciones, yyline, yychar, yytext());}
{ID}            {return new Symbol(Terminal.TK_ID, yyline, yychar, yytext());}
"->"            {return new Symbol(Terminal.TK_Arrow, yyline, yychar, yytext());}
{COMMENT_SL}    {}
{COMMENT_ML}    {}
\n              {yychar = 1;}
.               {AddLexicalError(yyline, yychar, yytext());}

