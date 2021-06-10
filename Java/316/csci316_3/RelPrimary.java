class RelPrimary extends BoolPrimary {
    static String[] relop_st = { "<", "<=", ">", ">=", "==", "!=" };

    E e1;
    E e2;
    State relop;

    RelPrimary(final E e_1, final E e_2, final State rel) throws TypeError {
        e1 = e_1;
        e2 = e_2;
        relop = rel;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String e1Type = e1.TypeEval(), e2Type = e2.TypeEval();
        final String op = RelPrimary.relop_st[relop.ordinal() - 9];

        if (op.equals("==") || op.equals("!=")) {
            // Allowed:
            // boolean == boolean
            // {int, float} == {int, float}
            if (e1Type.equals("boolean")
                    && e2Type.equals("boolean")
                    || ((e1Type.equals("int") || e1Type.equals("float")) 
                            && (e2Type.equals("int") || e2Type.equals("float")))) {
                return "boolean";
            }

            throw new TypeError("Type Error: " + op + " operator cannot be applied to ("
                    + e1Type + ", " + e2Type + ")");
        }

        // If we fell down here, then op must be <, >, >=, or <=

        // e1 must be either an int, float
        if (!(e1Type.equals("int") || e1Type.equals("float"))) {
            throw new TypeError("Type Error: " + op + " operator cannot be applied to "
                    + e1Type);
        }

        // e1 must be either an int, float
        if (!(e2Type.equals("int") || e2Type.equals("float"))) {
            throw new TypeError("Type Error: " + op + " operator cannot be applied to "
                    + e2Type);
        }

        return "boolean";
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <boolPrimary>");
        e1.printParseTree(indent1);
        LexAnalyzer.displayln(indent1 + indent1.length() + " "
                + RelPrimary.relop_st[relop.ordinal() - 9]);
        e2.printParseTree(indent1);
    }
}