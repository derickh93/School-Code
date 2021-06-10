class OrExpr extends Expr {
    BoolTerm boolTerm;
    Expr expr;

    OrExpr(final BoolTerm b, final Expr e) throws TypeError {
        boolTerm = b;
        expr = e;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String e1Type = boolTerm.TypeEval(), e2Type = expr.TypeEval();

        // Anything being or'd must be a boolean.
        if (!e1Type.equals("boolean")) {
            throw new TypeError("Type Error: || operator cannot be applied to "
                    + e1Type);
        }

        // Anything being or'd must be a boolean.
        if (!e2Type.equals("boolean")) {
            throw new TypeError("Type Error: || operator cannot be applied to "
                    + e2Type);
        }

        // If none of the above worked, then e1 and e2 must be booleans.
        return "boolean";
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <expr>");
        boolTerm.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " ||");
        expr.printParseTree(indent1);
    }
}
