class If2 extends Statement {
    Expr expr;
    Statement statement1;
    Statement statement2;

    If2(final Expr e, final Statement s1, final Statement s2) throws TypeError {
        expr = e;
        statement1 = s1;
        statement2 = s2;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        if (expr.TypeEval().equals("boolean")) {
            return "correct";
        }

        throw new TypeError(
                "Type Error: non-boolean expression found in if-statement");
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <statement>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " if");
        expr.printParseTree(indent1);
        statement1.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " else");
        statement2.printParseTree(indent1);
    }
}
