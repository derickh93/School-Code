class Int extends Primary {
    int val;

    Int(final int i) {
        val = i;
    }

    @Override
    String TypeEval() {
        return "int";
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <primary> " + val);
    }
}