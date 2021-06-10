class Parenthesized extends Primary {
    Expr expr;

    Parenthesized(final Expr e) {
        expr = e;
    }

    @Override
    String TypeEval() throws TypeError {
        return expr.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <primary>");
        expr.printParseTree(indent + " ");
    }
}