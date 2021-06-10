class MulTerm extends Term {
    Primary primary;
    Term term;

    MulTerm(final Primary p, final Term t) throws TypeError {
        primary = p;
        term = t;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String e1Type = primary.TypeEval(), e2Type = term.TypeEval();

        // e1 must be an int, or float.
        if (!(e1Type.equals("int") || e1Type.equals("float"))) {
            throw new TypeError("Type Error: * operator cannot be applied to "
                    + e1Type);
        }

        // e2 must be an int, or float.
        if (!(e2Type.equals("int") || e2Type.equals("float"))) {
            throw new TypeError("Type Error: * operator cannot be applied to "
                    + e2Type);
        }

        if (e1Type.equals("int") && e2Type.equals("float")
                || e1Type.equals("float") && e2Type.equals("int")
                || e1Type.equals("float") && e2Type.equals("float")) {
            return "float";
        }

        // If none of the above worked, then e1 and e2 must be ints.
        return "int";
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <term>");
        primary.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " *");
        term.printParseTree(indent1);
    }
}