class Decrement extends Statement {
    String id;

    Decrement(final String s) throws TypeError {
        id = s;
        TypeEval();
    }

    @Override
    String TypeEval() throws TypeError {
        final String type = Parser.types.get(id);

        // -- can only be applied to variables of type float, or int.
        if (type.equals("float") || type.equals("int")) {
            return "correct";
        }

        throw new TypeError("Type Error: -- operator cannot be applied to "
                + type + " variable " + id);
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";
        final String indent2 = indent + "  ";

        LexAnalyzer.displayln(indent + indent.length() + " <statement>");
        LexAnalyzer.displayln(indent1 + indent1.length() + " <decrement>");
        LexAnalyzer.displayln(indent2 + indent2.length() + " " + id);
        LexAnalyzer.displayln(indent2 + indent2.length() + " --");
    }
}
