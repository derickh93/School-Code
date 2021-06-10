class SingleId extends IdList {
    Ident ident;

    SingleId(final Ident i) {
        ident = i;
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <id list>");
        ident.printParseTree(indent1);
    }
}
