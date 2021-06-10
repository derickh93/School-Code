class IntType extends Type {
    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " int");
    }
}