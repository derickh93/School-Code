class NegPrimary extends Primary {
    Primary primary;

    NegPrimary(final Primary p) {
        primary = p;
    }

    @Override
    String TypeEval() throws TypeError {
        final String type = primary.TypeEval();

        // The type being negated must be an int, or float.
        // Ex: -5, -1, -10
        // Ex: -true, will throw an error
        if (type.equals("int") || type.equals("float")) {
            return type;
        }

        throw new TypeError("Type Error: - operator cannot be applied to "
                + type);
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <primary>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " -");
        primary.printParseTree(indent1);
    }
}