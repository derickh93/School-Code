class AddE extends E {
    Term term;
    E e;

    AddE(final Term t, final E e_) throws TypeError {
        term = t;
        e = e_;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String e1Type = term.TypeEval(), e2Type = e.TypeEval();

        // If e1 isn't an int or float, throw error
        if (!(e1Type.equals("int") || e1Type.equals("float"))) {
            throw new TypeError("Type Error: + operator cannot be applied to "
                    + e1Type);
        }

        // If e2 isn't an int or float, throw error
        if (!(e2Type.equals("int") || e2Type.equals("float"))) {
            throw new TypeError("Type Error: + operator cannot be applied to "
                    + e2Type);
        }

        if (e1Type.equals("int") && e2Type.equals("float")
                || e1Type.equals("float") && e2Type.equals("int")
                || e1Type.equals("float") && e2Type.equals("float")) {
            return "float";
        }

        // If the none of the above worked, then e1 == int, and e2 == int
        return "int";
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <E>");
        term.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " +");
        e.printParseTree(indent1);
    }
}