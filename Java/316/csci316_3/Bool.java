class Bool extends Primary {
    boolean val;

    Bool(final boolean b) {
        val = b;
    }

    @Override
    String TypeEval() {
        return "boolean";
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <primary> " + val);
    }
}