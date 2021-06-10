class MultipleId extends IdList {
    Ident ident;
    IdList idList;

    MultipleId(final Ident i, final IdList il) {
        ident = i;
        idList = il;
    }

    @Override
    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <id list>");
        ident.printParseTree(indent1);
        idList.printParseTree(indent1);
    }
}
