/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<program> --> <var declarations> <statement>
<var declarations> --> <var dec> ";" | <var dec> ";" <var declarations>
<var dec> --> <type> <id list>
<type> --> "int" | "float" | "boolean"
<id list> --> <id> | <id> "," <id list>
<statement> --> <assignment> | <increment> | <decrement> |
                <block> | <cond> | <while> | <do>
<assignment> --> <id> "=" <expr> ";"
<increment> --> <id> "++" ";" 
<decrement> --> <id> "--" ";" 
<block> --> "{" <s list> "}"
<cond> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ]
<while> --> "while" "(" <expr> ")" <statement>
<do> --> "do" <statement> "while" "(" <expr> ")"
<s list> --> <statement> | <statement> <s list>
<expr> --> <boolTerm> | <boolTerm> || <expr>
<boolTerm> --> <boolPrimary> | <boolPrimary> && <boolTerm>
<boolPrimary> --> <E> [ <rel op> <E> ]
<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
<E> --> <term> | <term> + <E> | <term> - <E>
<term> --> <primary> | <primary> * <term> | <primary> / <term>
<primary> --> <id> | <int> | <float> | <floatE> | <boolLiteral>
                   "(" <expr> ")" | - <primary> | ! <primary> 
<boolLiteral> --> "false" | "true" 

The definitions of the tokens are given in the lexical analyzer class file "LexAnalyzer.java". 

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

The program will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 
The string variable "indent" will keep track of the correct number of blanks for indentation and
will be passed to parse functions corresponding to syntactic categories.

 **/

import java.util.HashMap;

public abstract class Parser extends LexAnalyzer {
    static boolean syntaxErrorFound = false;

    static IntType int_type = new IntType();
    static FloatType float_type = new FloatType();
    static BooleanType boolean_type = new BooleanType();

    static String currentType;
    static HashMap<String, String> types = new HashMap<String, String>();

    public static Program program() throws TypeError {

        // <program> --> <var declarations> <statement>

        final VarDeclarations varDeclarations = Parser.varDeclarations();
        final Statement statement = Parser.statement();
        return new Program(varDeclarations, statement);
    }

    public static VarDeclarations varDeclarations()

    // <var declarations> --> <var dec> ";" | <var dec> ";" <var declarations>

    {
        final VarDec varDec = Parser.varDec();

        if (LexAnalyzer.state == State.Semicolon) {
            LexAnalyzer.getToken();
            if (LexAnalyzer.state == State.Keyword_int
                    || LexAnalyzer.state == State.Keyword_float
                    || LexAnalyzer.state == State.Keyword_boolean) {
                final VarDeclarations varDeclarations = Parser
                        .varDeclarations();
                return new MultipleVarDec(varDec, varDeclarations);
            } else {
                return new SingleVarDec(varDec);
            }
        } else {
            Parser.errorMsg(4);
            return null;
        }
    }

    public static VarDec varDec()

    // <var dec> --> <type> <id list>

    {
        final Type type = Parser.type();
        final IdList idList = Parser.idList();
        return new VarDec(type, idList);
    }

    public static Type type()

    // <type> --> "int" | "float" | "boolean"

    {
        switch (LexAnalyzer.state) {
        case Keyword_int:
            Parser.currentType = LexAnalyzer.t;
            LexAnalyzer.getToken();
            return Parser.int_type;
        case Keyword_float:
            Parser.currentType = LexAnalyzer.t;
            LexAnalyzer.getToken();
            return Parser.float_type;
        case Keyword_boolean:
            Parser.currentType = LexAnalyzer.t;
            LexAnalyzer.getToken();
            return Parser.boolean_type;
        default:
            Parser.errorMsg(10);
            return null;
        }
    }

    public static IdList idList()

    // <id list> --> <id> | <id> "," <id list>

    {
        final Ident ident = Parser.ident();

        if (LexAnalyzer.state == State.Comma) {
            LexAnalyzer.getToken();
            final IdList idList = Parser.idList();
            return new MultipleId(ident, idList);
        } else {
            return new SingleId(ident);
        }
    }

    public static Ident ident()

    {
        if (LexAnalyzer.state == State.Id) {
            if (Parser.types.get(LexAnalyzer.t) != null) {
                Parser.errorMsg(13);
            }

            Parser.types.put(LexAnalyzer.t, Parser.currentType);
            final String id = LexAnalyzer.t;
            LexAnalyzer.getToken();
            return new Ident(id);
        } else {
            Parser.errorMsg(12);
            return null;
        }
    }

    public static Statement statement() throws TypeError {

        // <statement> --> <assignment> | <increment> | <decrement> |
        // <block> | <cond> | <while> | <do>
        // <assignment> --> <id> "=" <expr> ";"
        // <increment> --> <id> "++" ";"
        // <decrement> --> <id> "--" ";"
        // <block> --> "{" <slist> "}"
        // <cond> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ]
        // <while> --> "while" "(" <expr> ")" <statement>
        // <do> --> "do" <statement> "while" "(" <expr> ")"

        switch (LexAnalyzer.state) {
        case Id: {
            final String id = LexAnalyzer.t;
            LexAnalyzer.getToken();

            if (LexAnalyzer.state == State.Assign) {
                LexAnalyzer.getToken();
                final Expr expr = Parser.expr();
                if (LexAnalyzer.state == State.Semicolon) {
                    LexAnalyzer.getToken();
                    return new Assignment(id, expr);
                } else {
                    Parser.errorMsg(4);
                }
            } else if (LexAnalyzer.state == State.Incr) {
                LexAnalyzer.getToken();
                if (LexAnalyzer.state == State.Semicolon) {
                    LexAnalyzer.getToken();
                    return new Increment(id);
                } else {
                    Parser.errorMsg(4);
                }
            } else if (LexAnalyzer.state == State.Decr) {
                LexAnalyzer.getToken();
                if (LexAnalyzer.state == State.Semicolon) {
                    LexAnalyzer.getToken();
                    return new Decrement(id);
                } else {
                    Parser.errorMsg(4);
                }
            } else {
                Parser.errorMsg(5);
            }
            return null;
        }
        case LBrace: {
            LexAnalyzer.getToken();
            final SList slist = Parser.slist();
            if (LexAnalyzer.state == State.RBrace) {
                LexAnalyzer.getToken();
                return new Block(slist);
            } else {
                Parser.errorMsg(3);
            }
            return null;
        }
        case Keyword_if: {
            LexAnalyzer.getToken();
            if (LexAnalyzer.state == State.LParen) {
                LexAnalyzer.getToken();
                final Expr expr = Parser.expr();
                if (LexAnalyzer.state == State.RParen) {
                    LexAnalyzer.getToken();
                    final Statement statement1 = Parser.statement();
                    if (LexAnalyzer.state == State.Keyword_else) {
                        LexAnalyzer.getToken();
                        final Statement statement2 = Parser.statement();
                        return new If2(expr, statement1, statement2);
                    } else {
                        return new If1(expr, statement1);
                    }
                } else {
                    Parser.errorMsg(7);
                }
            } else {
                Parser.errorMsg(8);
            }
            return null;
        }
        case Keyword_while: {
            LexAnalyzer.getToken();
            if (LexAnalyzer.state == State.LParen) {
                LexAnalyzer.getToken();
                final Expr expr = Parser.expr();
                if (LexAnalyzer.state == State.RParen) {
                    LexAnalyzer.getToken();
                    final Statement statement = Parser.statement();
                    return new While(expr, statement);
                } else {
                    Parser.errorMsg(7);
                }
            } else {
                Parser.errorMsg(8);
            }
            return null;
        }
        case Keyword_do: {
            LexAnalyzer.getToken();
            final Statement statement = Parser.statement();
            if (LexAnalyzer.state == State.Keyword_while) {
                LexAnalyzer.getToken();
                if (LexAnalyzer.state == State.LParen) {
                    LexAnalyzer.getToken();
                    final Expr expr = Parser.expr();
                    if (LexAnalyzer.state == State.RParen) {
                        LexAnalyzer.getToken();
                        return new Do(statement, expr);
                    } else {
                        Parser.errorMsg(7);
                    }
                } else {
                    Parser.errorMsg(8);
                }
            } else {
                Parser.errorMsg(9);
            }
            return null;
        }
        default:
            Parser.errorMsg(6);
            return null;
        }
    }

    public static SList slist() throws TypeError {

        // <slist> --> <statement> | <statement> <slist>

        final Statement statement = Parser.statement();

        if (LexAnalyzer.state == State.Id || LexAnalyzer.state == State.LBrace
                || LexAnalyzer.state == State.Keyword_if
                || LexAnalyzer.state == State.Keyword_while
                || LexAnalyzer.state == State.Keyword_do) {
            final SList slist = Parser.slist();
            return new MultipleStatement(statement, slist);
        } else {
            return new SingleStatement(statement);
        }
    }

    public static Expr expr() throws TypeError {

        // <expr> --> <boolTerm> | <boolTerm> || <expr>

        final BoolTerm boolterm = Parser.boolTerm();
        if (LexAnalyzer.state == State.Or) {
            LexAnalyzer.getToken();
            final Expr expr = Parser.expr();
            return new OrExpr(boolterm, expr);
        } else {
            return new SingleBoolTerm(boolterm);
        }
    }

    public static BoolTerm boolTerm() throws TypeError {

        // <boolTerm> --> <boolPrimary> | <boolPrimary> && <boolTerm>

        final BoolPrimary boolprimary = Parser.boolPrimary();
        if (LexAnalyzer.state == State.And) {
            LexAnalyzer.getToken();
            final BoolTerm boolterm = Parser.boolTerm();
            return new AndBoolTerm(boolprimary, boolterm);
        } else {
            return new SingleBoolPrimary(boolprimary);
        }
    }

    public static BoolPrimary boolPrimary() throws TypeError {

        // <boolPrimary> --> <E> [ <relop> <E> ]

        final E e1 = Parser.E();
        if (LexAnalyzer.state.compareTo(State.Lt) >= 0
                && LexAnalyzer.state.compareTo(State.Neq) <= 0)

        // state = Lt, Le, Gt, Ge, Eq, or Neq

        {
            final State relop = LexAnalyzer.state;
            LexAnalyzer.getToken();
            final E e2 = Parser.E();
            return new RelPrimary(e1, e2, relop);
        } else {
            return new SingleE(e1);
        }
    }

    public static E E() throws TypeError {

        // <E> --> <term> | <term> + <E> | <term> - <E>

        final Term term = Parser.term();
        if (LexAnalyzer.state == State.Add) {
            LexAnalyzer.getToken();
            final E e = Parser.E();
            return new AddE(term, e);
        } else if (LexAnalyzer.state == State.Sub) {
            LexAnalyzer.getToken();
            final E e = Parser.E();
            return new SubE(term, e);
        } else {
            return new SingleTerm(term);
        }
    }

    public static Term term() throws TypeError {

        // <term> --> <primary> | <primary> * <term> | <primary> / <term>

        final Primary primary = Parser.primary();
        if (LexAnalyzer.state == State.Mul) {
            LexAnalyzer.getToken();
            final Term term = Parser.term();
            return new MulTerm(primary, term);
        } else if (LexAnalyzer.state == State.Div) {
            LexAnalyzer.getToken();
            final Term term = Parser.term();
            return new DivTerm(primary, term);
        } else {
            return new SinglePrimary(primary);
        }
    }

    public static Primary primary() throws TypeError {

        // <primary> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "("
        // <expr> ")" | - <primary> | ! <primary>

        switch (LexAnalyzer.state) {
        case Id:
            final Id id = new Id(LexAnalyzer.t);
            LexAnalyzer.getToken();
            return id;

        case Int:
            final Int intElem = new Int(Integer.parseInt(LexAnalyzer.t));
            LexAnalyzer.getToken();
            return intElem;

        case Float:
        case FloatE:
            final Floatp floatElem = new Floatp(Float.parseFloat(LexAnalyzer.t));
            LexAnalyzer.getToken();
            return floatElem;

        case Keyword_false:
            LexAnalyzer.getToken();
            return new Bool(false);

        case Keyword_true:
            LexAnalyzer.getToken();
            return new Bool(true);

        case LParen:
            LexAnalyzer.getToken();
            final Expr expr = Parser.expr();
            if (LexAnalyzer.state == State.RParen) {
                LexAnalyzer.getToken();
                final Parenthesized paren = new Parenthesized(expr);
                return paren;
            } else {
                Parser.errorMsg(1);
                return null;
            }

        case Sub:
            LexAnalyzer.getToken();
            final Primary prim = Parser.primary();
            final NegPrimary negprim = new NegPrimary(prim);
            return negprim;

        case Inv:
            LexAnalyzer.getToken();
            final Primary prim_ = Parser.primary();
            final InvPrimary invprim = new InvPrimary(prim_);
            return invprim;

        default:
            Parser.errorMsg(2);
            return null;
        }
    }

    public static void errorMsg(final int i) {
        Parser.syntaxErrorFound = true;

        switch (i) {
        case 1:
            LexAnalyzer.displayln(" arith op or ) expected");
            return;
        case 2:
            LexAnalyzer.displayln(" id, int, float, (, -, or ! expected");
            return;
        case 3:
            LexAnalyzer.displayln(" } expected");
            return;
        case 4:
            LexAnalyzer.displayln(" ; expected");
            return;
        case 5:
            LexAnalyzer.displayln(" =, ++, or -- expected");
            return;
        case 6:
            LexAnalyzer.displayln(" id, {, if, while, or do expected");
            return;
        case 7:
            LexAnalyzer.displayln(" ) expected");
            return;
        case 8:
            LexAnalyzer.displayln(" ( expected");
            return;
        case 9:
            LexAnalyzer.displayln(" while expected");
            return;
        case 10:
            LexAnalyzer.displayln(" type name expected");
            return;
        case 11:
            LexAnalyzer.displayln(" , expected");
            return;
        case 12:
            LexAnalyzer.displayln(" id expected");
            return;
        case 13:
            LexAnalyzer.displayln("variable " + LexAnalyzer.t
                    + " already declared");
            return;
        }
    }

    public static void main(final String argv[]) {
        LexAnalyzer.setLex(argv[0], argv[1]);

        LexAnalyzer.getToken();

        String typeError = null;

        // If a TypeError is found at all, throw yourself out
        // of the program (so we don't continue and waste time parsing).
        try {
            Parser.program(); // build a parse tree
        } catch (final TypeError e) {
            // Save the message received.
            typeError = e.getMessage();
        }

        if (!Parser.syntaxErrorFound) {
            LexAnalyzer.displayln(Parser.types.toString());

            // Display the type error previously caught, if any.
            if (typeError != null) {
                LexAnalyzer.displayln(typeError);
            }
        }

        LexAnalyzer.closeIO();
    }
}
