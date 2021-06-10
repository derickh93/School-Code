class Do extends Statement {
    Statement statement;
    Expr expr;

    Do(final Statement s, final Expr e) throws TypeError {
        statement = s;
        expr = e;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        if (expr.TypeEval().equals("boolean")) {
            return "correct";
        }

        throw new TypeError(
                "Type Error: non-boolean expression found in do-while-statement");
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <statement>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " do");
        statement.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " while");
        expr.printParseTree(indent1);
    }
}
