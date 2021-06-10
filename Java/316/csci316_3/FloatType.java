class FloatType extends Type {
    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " float");
    }
}