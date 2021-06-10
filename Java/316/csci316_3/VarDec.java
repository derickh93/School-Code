class VarDec {
    Type type;
    IdList idList;

    VarDec(final Type t, final IdList i) {
        type = t;
        idList = i;
    }

    void printParseTree(final String indent) {
        final String indent1 = indent + " ";

        LexAnalyzer.displayln(indent + indent.length() + " <var dec>");
        type.printParseTree(indent1);
        idList.printParseTree(indent1);
    }
}
