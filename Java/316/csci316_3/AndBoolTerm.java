class AndBoolTerm extends BoolTerm {
    BoolPrimary boolPrimary;
    BoolTerm boolTerm;

    AndBoolTerm(final BoolPrimary bp, final BoolTerm bt) throws TypeError {
        boolPrimary = bp;
        boolTerm = bt;
    }

    @Override
    String TypeEval() throws TypeError {
        final String e1Type = boolPrimary.TypeEval(), e2Type = boolTerm.TypeEval();

        if (!e1Type.equals("boolean")) {
            throw new TypeError("Type Error: && operator cannot be applied to "
                    + e1Type);
        }

        if (!e2Type.equals("boolean")) {
            throw new TypeError("Type Error: && operator cannot be applied to "
                    + e2Type);
        }

        // If none of the above worked, then e1 and e2 must be booleans
        return "boolean";
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <boolTerm>");
        boolPrimary.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " &&");
        boolTerm.printParseTree(indent1);
    }
}
