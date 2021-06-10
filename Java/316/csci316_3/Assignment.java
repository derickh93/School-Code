class Assignment extends Statement {
    String id;
    Expr expr;

    Assignment(final String s, final Expr e) throws TypeError {
        id = s;
        expr = e;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String x = Parser.types.get(id), E = expr.TypeEval();

        // The type of E (the expression being assigned) must be equal to the
        // variable's (x) type.
        // ... OR ...
        // You can assign an int to a variable of type float.
        if (x.equals(E) || x.equals("float") && E.equals("int")) {
            return "correct";
        }

        throw new TypeError("Type Error: " + E + " cannot be assigned to " + x
                + " variable " + id);
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";
        final String indent2 = indent + "  ";

        LexAnalyzer.displayln(indent + indent.length() + " <statement>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " <assignment>");
        LexAnalyzer.displayln(indent2 + indent2.length() + " " + id);
        LexAnalyzer.displayln(indent2 + indent2.length() + " =");
        expr.printParseTree(indent2);
    }
}
