class InvPrimary extends Primary {
    Primary primary;

    InvPrimary(final Primary p) {
        primary = p;
    }

    @Override
    String TypeEval() throws TypeError {
        final String type = primary.TypeEval();

        // The type that is being inverted must be a boolean.
        // Ex. !true == false
        // Ex. !10, throws an error
        if (type.equals("boolean")) {
            return type;
        }

        throw new TypeError("Type Error: ! operator cannot be applied to "
                + type);
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <primary>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " !");
        primary.printParseTree(indent1);
    }
}