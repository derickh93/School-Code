class SinglePrimary extends Term {
    Primary primary;

    SinglePrimary(final Primary p) {
        primary = p;
    }

    @Override
    String TypeEval() throws TypeError {
        return primary.TypeEval();
    }

    @Override
    void printParseTree(final String indent) {
        LexAnalyzer.displayln(indent + indent.length() + " <term>");
        primary.printParseTree(indent + " ");
    }
}