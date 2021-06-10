class While extends Statement {
    Expr expr;
    Statement statement;

    While(final Expr e, final Statement s) throws TypeError {
        expr = e;
        statement = s;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        if (expr.TypeEval().equals("boolean")) {
            return "correct";
        }

        throw new TypeError(
                "Type Error: non-boolean expression found in while-statement");
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <statement>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " while");
        expr.printParseTree(indent1);
        statement.printParseTree(indent1);
    }
}
