class SubE extends E {
    Term term;
    E e;

    SubE(final Term t, final E e_) {
        term = t;
        e = e_;
    }

    @Override
    String TypeEval() throws TypeError {
        final String e1Type = term.TypeEval(), e2Type = e.TypeEval();

        // e1 must be an int or a float
        if (!(e1Type.equals("int") || e1Type.equals("float"))) {
            throw new TypeError("- operator cannot be applied to " + e1Type);
        }

        // e2 must be an int or a float
        if (!(e2Type.equals("int") || e2Type.equals("float"))) {
            throw new TypeError("- operator cannot be applied to " + e2Type);
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

        LexAnalyzer.displayln(indent + indent.length() + " <E>");
        term.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " -");
        e.printParseTree(indent1);
    }
}